package com.digosofter.digodroid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;
import com.digosofter.digojava.Utils;

public class Aparelho extends Objeto {

  private static Aparelho i;

  public static Aparelho getI() {

    try {

      if (i != null) {

        return i;
      }

      i = new Aparelho();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return i;
  }

  private Context _cnt;
  private TelephonyManager _objTelManager;
  private String _strDeviceId;
  private String _strImei;

  public void abrirMapa(String strEnderecoCompleto) {

    Intent itt;
    String urlMap;

    try {

      urlMap = "http://maps.google.co.in/maps?q=";
      urlMap += strEnderecoCompleto;

      itt = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMap));
      itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      this.getCnt().getApplicationContext().getApplicationContext().startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  /**
   * Abre o aplicativo padrão para envio de email.
   */
  public void enviarEmail(String strEmail) {

    Uri uri;
    Intent itt;

    try {

      if (Utils.getBooStrVazia(strEmail)) {

        return;
      }

      uri = Uri.parse("mailto:" + strEmail);

      itt = new Intent(Intent.ACTION_SENDTO, uri);
      itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      itt.putExtra(Intent.EXTRA_SUBJECT, "Customer comments/questions");

      this.getCnt().startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  public boolean getBooConectado() {

    boolean booResultado = false;
    ConnectivityManager objCm;
    NetworkInfo objNetworkInfo;

    try {

      objCm = (ConnectivityManager) this.getCnt().getSystemService(Context.CONNECTIVITY_SERVICE);
      objNetworkInfo = objCm.getActiveNetworkInfo();

      booResultado = objNetworkInfo != null ? objNetworkInfo.isConnected() : false;

      if (!booResultado) {

        AppAndroid.getI().mostrarNoficacao("Aparelho não conectado.");
      }
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return booResultado;
  }

  private Context getCnt() {

    try {

      if (_cnt != null) {

        return _cnt;
      }

      _cnt = AppAndroid.getI().getCnt();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _cnt;
  }

  private TelephonyManager getObjTelManager() {

    try {

      if (_objTelManager != null) {

        return _objTelManager;
      }

      _objTelManager = (TelephonyManager) this.getCnt().getSystemService(Context.TELEPHONY_SERVICE);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _objTelManager;
  }

  /**
   * Retorna uma "string 64-bit" única para cada aparelho.
   */
  public String getStrDeviceId() {

    try {

      if (!Utils.getBooStrVazia(_strDeviceId)) {

        return _strDeviceId;
      }

      _strDeviceId = Secure.getString(this.getCnt().getContentResolver(), Secure.ANDROID_ID);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _strDeviceId;
  }

  public String getStrImei() {

    try {

      if (!Utils.getBooStrVazia(_strImei)) {

        return _strImei;
      }

      _strImei = this.getObjTelManager().getDeviceId();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrMsgUsuarioPadrao(100), ex);
    }
    finally {
    }

    return _strImei;
  }

  public void ligarNumero(String strNumero) {

    Intent objIntent;

    try {

      objIntent = new Intent(Intent.ACTION_CALL);

      objIntent.setData(Uri.parse("tel:" + strNumero));
      objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      this.getCnt().startActivity(objIntent);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }
}
