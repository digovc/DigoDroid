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

  private Context _objContext;

  private TelephonyManager _objTelephonyManager;

  private String _strId;

  private String _strImei;

  public void abrirMapa(String strEnderecoCompleto) {
    // VARIÁVEIS

    Intent objIntent;
    String strMap = "http://maps.google.co.in/maps?q=";

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      strMap += strEnderecoCompleto;

      objIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strMap));
      objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      this.getObjContext().getApplicationContext().getApplicationContext().startActivity(objIntent);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  /**
   * Abre o aplicativo padrão para envio de email.
   *
   */
  public void enviarEmail(String strEmail) {
    // VARIÁVEIS

    Uri uri;
    Intent objIntent;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (Utils.getBooStrVazia(strEmail)) {
        return;
      }

      uri = Uri.parse("mailto:" + strEmail);
      objIntent = new Intent(Intent.ACTION_SENDTO, uri);
      objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      objIntent.putExtra(Intent.EXTRA_SUBJECT, "Customer comments/questions");
      this.getObjContext().startActivity(objIntent);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public boolean getBooConectado() {
    // VARIÁVEIS

    boolean booConectado = false;
    ConnectivityManager objConnectivityManager;
    NetworkInfo objNetworkInfo;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      objConnectivityManager = (ConnectivityManager) App.getI().getContext()
          .getSystemService(Context.CONNECTIVITY_SERVICE);
      objNetworkInfo = objConnectivityManager.getActiveNetworkInfo();

      if (objNetworkInfo == null) {
        booConectado = false;
      } else {
        booConectado = true;
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return booConectado;
  }

  private Context getObjContext() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_objContext != null) {
        return _objContext;
      }

      _objContext = App.getI().getContext();

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
    return _objContext;
  }

  private TelephonyManager getObjTelephonyManager() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_objTelephonyManager != null) {
        return _objTelephonyManager;
      }

      _objTelephonyManager = (TelephonyManager) App.getI().getActMain()
          .getSystemService(Context.TELEPHONY_SERVICE);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _objTelephonyManager;
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

      _strId = Secure.getString(this.getObjContext().getContentResolver(), Secure.ANDROID_ID);

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
      _strImei = this.getObjTelephonyManager().getDeviceId();

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrMensagemUsuarioPadrao(100), ex);

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

      this.getObjContext().getApplicationContext().getApplicationContext().startActivity(objIntent);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

}
