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
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (i == null) {
        i = new Aparelho();
      }

      // FIM AÇÕES
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
    // VARIÁVEIS

    Intent itt;
    String urlMap;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      urlMap = "http://maps.google.co.in/maps?q=";
      urlMap += strEnderecoCompleto;

      itt = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMap));
      itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      this.getCnt().getApplicationContext().getApplicationContext().startActivity(itt);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  /**
   * Abre o aplicativo padrão para envio de email.
   */
  public void enviarEmail(String strEmail) {
    // VARIÁVEIS

    Uri uri;
    Intent itt;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (Utils.getBooStrVazia(strEmail)) {
        return;
      }

      uri = Uri.parse("mailto:" + strEmail);
      itt = new Intent(Intent.ACTION_SENDTO, uri);
      itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      itt.putExtra(Intent.EXTRA_SUBJECT, "Customer comments/questions");

      this.getCnt().startActivity(itt);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public boolean getBooConectado() {
    // VARIÁVEIS

    boolean booResultado = false;
    ConnectivityManager objCm;
    NetworkInfo objNetworkInfo;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      objCm = (ConnectivityManager) this.getCnt().getSystemService(Context.CONNECTIVITY_SERVICE);
      objNetworkInfo = objCm.getActiveNetworkInfo();

      booResultado = objNetworkInfo == null;

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return booResultado;
  }

  private Context getCnt() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_cnt != null) {
        return _cnt;
      }

      _cnt = App.getI().getCnt();

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _cnt;
  }

  private TelephonyManager getObjTelManager() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_objTelManager != null) {
        return _objTelManager;
      }

      _objTelManager = (TelephonyManager) this.getCnt().getSystemService(Context.TELEPHONY_SERVICE);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _objTelManager;
  }

  /**
   * Retorna uma "string 64-bit" única para cada aparelho.
   */
  public String getStrId() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (!Utils.getBooStrVazia(_strId)) {
        return _strId;
      }

      _strId = Secure.getString(this.getCnt().getContentResolver(), Secure.ANDROID_ID);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _strId;
  }

  public String getStrImei() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (!Utils.getBooStrVazia(_strImei)) {
        return _strImei;
      }
      _strImei = this.getObjTelManager().getDeviceId();

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrMsgUsuarioPadrao(100), ex);

    } finally {
    }

    return _strImei;
  }

  public void ligarNumero(String strNumero) {
    // VARIÁVEIS

    Intent objIntent;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      objIntent = new Intent(Intent.ACTION_CALL);
      objIntent.setData(Uri.parse("tel:" + strNumero));
      objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      this.getCnt().startActivity(objIntent);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

}
