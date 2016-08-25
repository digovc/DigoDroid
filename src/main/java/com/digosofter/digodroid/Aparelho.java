package com.digosofter.digodroid;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digojava.Objeto;
import com.digosofter.digojava.Utils;

public class Aparelho extends Objeto
{
  private static Aparelho i;

  public static Aparelho getI()
  {
    if (i != null)
    {
      return i;
    }
    i = new Aparelho();

    return i;
  }

  private Context _cnt;
  private TelephonyManager _objTelephonyManager;
  private Point _pntTelaTamanho;
  private String _strDeviceId;
  private String _strImei;

  public void abrirMapa(String strEnderecoCompleto)
  {
    Intent itt;
    String urlMap;

    urlMap = "http://maps.google.co.in/maps?q=";
    urlMap += strEnderecoCompleto;
    itt = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMap));
    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    this.getCnt().getApplicationContext().getApplicationContext().startActivity(itt);
  }

  public void discar(String strNumero)
  {
    Intent itt;

    if (Utils.getBooStrVazia(strNumero))
    {
      return;
    }
    strNumero = Utils.simplificar(strNumero);
    itt = new Intent(Intent.ACTION_DIAL);
    itt.setData(Uri.parse("tel:" + strNumero));
    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    this.getCnt().startActivity(itt);
  }

  /**
   * Abre o aplicativo padrão para envio de email.
   */
  public void enviarEmail(ActMain act, Intent itt)
  {
    Uri uri;

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
    String strState;

    strState = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(strState))
    {
      return true;
    }
    if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(strState))
    {
      return true;
    }

    return false;
  }

  public boolean getBooConectado()
  {
    boolean booResultado = false;
    ConnectivityManager objConnectivityManager;
    NetworkInfo objNetworkInfo;

    objConnectivityManager = (ConnectivityManager) this.getCnt().getSystemService(Context.CONNECTIVITY_SERVICE);
    objNetworkInfo = objConnectivityManager.getActiveNetworkInfo();
    booResultado = objNetworkInfo != null && objNetworkInfo.isConnected();

    if (!booResultado)
    {
      AppAndroid.getI().notificar("Aparelho não conectado.");
    }

    return booResultado;
  }

  private Context getCnt()
  {
    if (_cnt != null)
    {
      return _cnt;
    }
    _cnt = AppAndroid.getI().getCnt();

    return _cnt;
  }

  private TelephonyManager getObjTelephonyManager()
  {
    if (_objTelephonyManager != null)
    {
      return _objTelephonyManager;
    }
    _objTelephonyManager = (TelephonyManager) this.getCnt().getSystemService(Context.TELEPHONY_SERVICE);

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
    _strDeviceId = Secure.getString(this.getCnt().getContentResolver(), Secure.ANDROID_ID);

    return _strDeviceId;
  }

  public String getStrImei()
  {
    if (!Utils.getBooStrVazia(_strImei))
    {
      return _strImei;
    }
    _strImei = this.getObjTelephonyManager().getDeviceId();

    return _strImei;
  }

  @TargetApi(Build.VERSION_CODES.M)
  public void ligar(String strNumero)
  {
    Intent itt;

    if (this.getCnt().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
    {
      return;
    }

    if (Utils.getBooStrVazia(strNumero))
    {
      return;
    }
    strNumero = Utils.simplificar(strNumero);
    itt = new Intent(Intent.ACTION_CALL);
    itt.setData(Uri.parse("tel:" + strNumero));
    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    this.getCnt().startActivity(itt);
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
    ((Vibrator) this.getCnt().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(arrIntMs, -1);
  }
}