package com.digosofter.digodroid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.digosofter.digodroid.erro.Erro;

public class Aparelho extends Objeto {

  private static Aparelho i;

  public static Aparelho getI() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (i == null) {
        i = new Aparelho();
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return i;
  }

  private Context _cnt;
  private TelephonyManager _objTelManager;
  private String _strId;
  private String _strImei;

  public void abrirMapa(String strEnderecoCompleto) {
    // VARI�VEIS

    Intent itt;
    String urlMap;

    // FIM VARI�VEIS
    try {
      // A��ES

      urlMap = "http://maps.google.co.in/maps?q=";
      urlMap += strEnderecoCompleto;

      itt = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMap));
      itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      this.getCnt().getApplicationContext().getApplicationContext().startActivity(itt);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  /**
   * Abre o aplicativo padr�o para envio de email.
   */
  public void enviarEmail(String strEmail) {
    // VARI�VEIS

    Uri uri;
    Intent itt;

    // FIM VARI�VEIS
    try {
      // A��ES

      if (Utils.getBooStrVazia(strEmail)) {
        return;
      }

      uri = Uri.parse("mailto:" + strEmail);
      itt = new Intent(Intent.ACTION_SENDTO, uri);
      itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      itt.putExtra(Intent.EXTRA_SUBJECT, "Customer comments/questions");

      this.getCnt().startActivity(itt);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public boolean getBooConectado() {
    // VARI�VEIS

    boolean booResultado = false;
    ConnectivityManager objCm;
    NetworkInfo objNetworkInfo;

    // FIM VARI�VEIS
    try {
      // A��ES

      objCm = (ConnectivityManager) this.getCnt().getSystemService(Context.CONNECTIVITY_SERVICE);
      objNetworkInfo = objCm.getActiveNetworkInfo();

      booResultado = objNetworkInfo == null;

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return booResultado;
  }

  private Context getCnt() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_cnt != null) {
        return _cnt;
      }

      _cnt = App.getI().getCnt();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _cnt;
  }

  private TelephonyManager getObjTelManager() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_objTelManager != null) {
        return _objTelManager;
      }

      _objTelManager = (TelephonyManager) this.getCnt().getSystemService(Context.TELEPHONY_SERVICE);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _objTelManager;
  }

  /**
   * Retorna uma "string 64-bit" �nica para cada aparelho.
   */
  public String getStrId() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (!Utils.getBooStrVazia(_strId)) {
        return _strId;
      }

      _strId = Secure.getString(this.getCnt().getContentResolver(), Secure.ANDROID_ID);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _strId;
  }

  public String getStrImei() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (!Utils.getBooStrVazia(_strImei)) {
        return _strImei;
      }
      _strImei = this.getObjTelManager().getDeviceId();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrMsgUsuarioPadrao(100), ex);

    } finally {
    }

    return _strImei;
  }

  public void ligarNumero(String strNumero) {
    // VARI�VEIS

    Intent objIntent;

    // FIM VARI�VEIS
    try {
      // A��ES

      objIntent = new Intent(Intent.ACTION_CALL);
      objIntent.setData(Uri.parse("tel:" + strNumero));
      objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      this.getCnt().startActivity(objIntent);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

}
