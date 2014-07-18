package com.digosofter.digodroid.erro;

import java.io.Serializable;

import android.content.Intent;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.activity.ActErro;

public class Erro extends Exception implements Serializable {

  private static final long serialVersionUID = 1L;

  private boolean _booMostrarUsuario = true;
  private String _strMensagem = "Erro do sistema.";
  private String _strMensagemDetalhes;
  private String _strNome;

  public Erro(String strMensagem, Exception ex) {
    // VARI�VEIS

    Intent objIntent;

    // FIM VARI�VEIS
    try {
      // A��ES

      if (!Utils.getBooStrVazia(strMensagem)) {
        this.setStrMensagem(strMensagem);
      }

      if (ex != null && !Utils.getBooStrVazia(ex.getMessage())) {
        this.setStrMensagemDetalhes(ex.getMessage());
      }

      if (this.getBooMostrarUsuario()) {

        objIntent = new Intent(App.getI().getActMain(), ActErro.class);
        objIntent.putExtra("Erro", this);

        App.getI().getActMain().startActivity(objIntent);
      }

      this.imprimirConsole();

      // FIM A��ES
    } catch (Exception e) {
    } finally {
    }
  }

  private void imprimirConsole() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      System.out.println(this.getStrMensagem());
      System.out.println(this.getStrMensagemDetalhes());

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

  }

  public boolean getBooMostrarUsuario() {
    return _booMostrarUsuario;
  }

  public String getStrMensagem() {
    return _strMensagem;
  }

  public String getStrMensagemDetalhes() {
    return _strMensagemDetalhes;
  }

  public String getStrNome() {
    return _strNome;
  }

  public void setBooMostrarUsuario(boolean booMostrarUsuario) {
    _booMostrarUsuario = booMostrarUsuario;
  }

  public void setStrMensagem(String strMensagem) {
    _strMensagem = strMensagem;
  }

  private void setStrMensagemDetalhes(String strMensagemDetalhes) {
    _strMensagemDetalhes = strMensagemDetalhes;
  }

}
