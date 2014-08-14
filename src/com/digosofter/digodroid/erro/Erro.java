package com.digosofter.digodroid.erro;

import java.io.Serializable;

import android.content.Intent;
import android.util.Log;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.activity.ActErro;

public class Erro extends Exception implements Serializable {

  private static final long serialVersionUID = 1L;
  private boolean _booMostrarUsuario = true;
  private String _strMsg;
  private String _strMsgDetalhe;
  private String _strNome;

  public Erro(String strMsg, Exception ex) {

    Intent objIntent;
    try {
      if (!Utils.getBooStrVazia(strMsg)) {
        this.setStrMsg(strMsg);
      }
      if (ex != null && !Utils.getBooStrVazia(ex.getMessage())) {
        this.setStrMsgDetalhe(ex.getMessage());
      }
      if (!this.getBooMostrarUsuario()) {
        return;
      }
      objIntent = new Intent(App.getI().getCnt(), ActErro.class);
      objIntent.putExtra("Erro", this);
      App.getI().getActMain().startActivity(objIntent);
      this.imprimirConsole();
    }
    catch (Exception e) {
    }
    finally {
    }
  }

  private boolean getBooMostrarUsuario() {

    return _booMostrarUsuario;
  }

  public String getStrMsg() {

    try {
      if (!Utils.getBooStrVazia(_strMsg)) {
        return _strMsg;
      }
      _strMsg = "Erro do sistema.";
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return _strMsg;
  }

  public String getStrMsgDetalhe() {

    try {
      if (!Utils.getBooStrVazia(_strMsgDetalhe)) {
        return _strMsgDetalhe;
      }
      _strMsgDetalhe = "Sem mais detalhes do erro.";
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return _strMsgDetalhe;
  }

  public String getStrNome() {

    return _strNome;
  }

  private void imprimirConsole() {

    try {
      System.out.println(this.getStrMsg());
      System.out.println(this.getStrMsgDetalhe());
      Log.println(Log.ERROR, this.getStrMsg(), this.getStrMsgDetalhe());
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  public void setBooMostrarUsuario(boolean booMostrarUsuario) {

    _booMostrarUsuario = booMostrarUsuario;
  }

  public void setStrMsg(String strMsg) {

    _strMsg = strMsg;
  }

  private void setStrMsgDetalhe(String strMsgDetalhe) {

    _strMsgDetalhe = strMsgDetalhe;
  }
}
