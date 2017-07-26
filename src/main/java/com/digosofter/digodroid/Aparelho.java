package com.digosofter.digodroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.digosofter.digodroid.activity.ActCodigoBarra;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.log.LogErro;
import com.digosofter.digojava.Objeto;
import com.digosofter.digojava.Utils;

public class Aparelho extends Objeto
{
  public static final int REQUEST_IMAGE_CAPTURE = 500;

  private static Aparelho _i;

  public static Aparelho getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new Aparelho();

    return _i;
  }

  private OnCodigoBarraSucessoListener _evtOnCodigoBarraSucesso;
  private TelephonyManager _objTelephonyManager;
  private Point _pntTelaTamanho;
  private String _strDeviceId;
  private String _strImei;

  public void abrirLink(String url)
  {
    if (!Utils.getBooUrlValida(url))
    {
      LogErro.getI().addLog(AppAndroid.getI().getActPrincipal(), new Exception("URL inválida."));
      return;
    }

    if (!url.startsWith("http://"))
    {
      url = "http://".concat(url);
    }

    AppAndroid.getI().getActPrincipal().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
  }

  public void abrirMapa(String strEnderecoCompleto)
  {
    if (Utils.getBooStrVazia(strEnderecoCompleto))
    {
      return;
    }

    String urlMap = "http://maps.google.co.in/maps?q=".concat(strEnderecoCompleto);

    Intent itt = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMap));

    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    AppAndroid.getI().getActPrincipal().getApplicationContext().getApplicationContext().startActivity(itt);
  }

  public void compartilhar(ActMain act, String strAssunto, String strConteudo)
  {
    if (act == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(strAssunto))
    {
      return;
    }

    if (Utils.getBooStrVazia(strConteudo))
    {
      return;
    }

    Intent itt = new Intent(android.content.Intent.ACTION_SEND);

    itt.setType("text/plain");

    itt.putExtra(android.content.Intent.EXTRA_SUBJECT, strAssunto);
    itt.putExtra(android.content.Intent.EXTRA_TEXT, strConteudo);

    act.startActivity(Intent.createChooser(itt, strAssunto));
  }

  public void discar(String strNumero)
  {
    if (Utils.getBooStrVazia(strNumero))
    {
      return;
    }

    strNumero = Utils.simplificar(strNumero);

    Intent itt = new Intent(Intent.ACTION_DIAL);

    itt.setData(Uri.parse("tel:" + strNumero));
    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    AppAndroid.getI().getActPrincipal().startActivity(itt);
  }

  /**
   * Abre o aplicativo padrão para envio de email.
   */
  public void enviarEmail(ActMain act, Intent itt)
  {
    if (act == null)
    {
      return;
    }

    if (itt == null)
    {
      itt = new Intent();
    }

    itt.setAction(Intent.ACTION_SENDTO);
    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    act.startActivity(Intent.createChooser(itt, "Enviar email"));
  }

  /**
   * Verifica se o armazenamento externo está disponível para escrita.
   *
   * @return True caso o armazenamento externo está disponível para escrita, false caso contrário.
   */
  public boolean getBooArmazenamentoExternoEscrever()
  {
    return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
  }

  /**
   * Verifica se o armazenamento externo está disponível para leitura.
   *
   * @return True caso o armazenamento externo está disponível para leitura, false caso contrário.
   */
  public boolean getBooArmazenamentoExternoLer()
  {
    String strState = Environment.getExternalStorageState();

    if (Environment.MEDIA_MOUNTED.equals(strState))
    {
      return true;
    }

    return Environment.MEDIA_MOUNTED_READ_ONLY.equals(strState);
  }

  public boolean getBooConectado()
  {
    ConnectivityManager objConnectivityManager = (ConnectivityManager) AppAndroid.getI().getActPrincipal().getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo objNetworkInfo = objConnectivityManager.getActiveNetworkInfo();

    return ((objNetworkInfo != null) && objNetworkInfo.isConnected());
  }

  public OnCodigoBarraSucessoListener getEvtOnCodigoBarraSucesso()
  {
    return _evtOnCodigoBarraSucesso;
  }

  private TelephonyManager getObjTelephonyManager()
  {
    if (_objTelephonyManager != null)
    {
      return _objTelephonyManager;
    }

    if (AppAndroid.getI().getActPrincipal() == null)
    {
      return null;
    }

    _objTelephonyManager = (TelephonyManager) AppAndroid.getI().getActPrincipal().getSystemService(Context.TELEPHONY_SERVICE);

    return _objTelephonyManager;
  }

  /**
   * Retorna um objeto Point com o tamanho da tela do aparelho em pixels.
   *
   * @return Objeto Point com o tamanho da tela do aparelho em pixels.
   */
  public Point getPntTelaTamanho()
  {
    if (_pntTelaTamanho != null)
    {
      return _pntTelaTamanho;
    }

    _pntTelaTamanho = new Point();

    AppAndroid.getI().getActPrincipal().getWindowManager().getDefaultDisplay().getSize(_pntTelaTamanho);

    return _pntTelaTamanho;
  }

  /**
   * Retorna uma "string 64-bit" única para cada aparelho.
   */
  public String getStrDeviceId()
  {
    if (!Utils.getBooStrVazia(_strDeviceId))
    {
      return _strDeviceId;
    }

    if (AppAndroid.getI().getActPrincipal() == null)
    {
      return null;
    }

    _strDeviceId = Secure.getString(AppAndroid.getI().getActPrincipal().getContentResolver(), Secure.ANDROID_ID);

    return _strDeviceId;
  }

  public String getStrImei()
  {
    if (!Utils.getBooStrVazia(_strImei))
    {
      return _strImei;
    }

    if (this.getObjTelephonyManager() == null)
    {
      return null;
    }

    _strImei = this.getObjTelephonyManager().getDeviceId();

    return _strImei;
  }

  public void lerCodigoBarra(final ActMain act, final OnCodigoBarraSucessoListener evtOnCodigoBarraSucesso)
  {
    if (evtOnCodigoBarraSucesso == null)
    {
      return;
    }

    this.setEvtOnCodigoBarraSucesso(evtOnCodigoBarraSucesso);

    act.startActivity(new Intent(act, ActCodigoBarra.class));
  }

  public void ligar(String strNumero)
  {
    if (AppAndroid.getI().getActPrincipal() == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(strNumero))
    {
      return;
    }

    strNumero = Utils.simplificar(strNumero);

    Intent itt = new Intent(Intent.ACTION_CALL);

    itt.setData(Uri.parse("tel:" + strNumero));
    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    AppAndroid.getI().getActPrincipal().startActivity(itt);
  }

  private void setEvtOnCodigoBarraSucesso(OnCodigoBarraSucessoListener evtOnCodigoBarraSucesso)
  {
    _evtOnCodigoBarraSucesso = evtOnCodigoBarraSucesso;
  }

  public void tirarFoto(ActMain act)
  {
    if (act == null)
    {
      return;
    }

    Intent ittTirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    if (ittTirarFoto.resolveActivity(act.getPackageManager()) == null)
    {
      return;
    }

    act.startActivityForResult(ittTirarFoto, REQUEST_IMAGE_CAPTURE);
  }

  /**
   * Vibra o aparelho caso exista hardware para tal.
   *
   * @param arrIntMs Array de inteiros que representam os milissegundos que o aparelho irá virar e parar.
   */
  public void vibrar(long[] arrIntMs)
  {
    if (arrIntMs == null)
    {
      return;
    }

    if (AppAndroid.getI().getActPrincipal() == null)
    {
      return;
    }

    ((Vibrator) AppAndroid.getI().getActPrincipal().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(arrIntMs, -1);
  }

}