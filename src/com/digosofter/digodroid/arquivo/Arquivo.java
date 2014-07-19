package com.digosofter.digodroid.arquivo;

import java.io.File;
import java.io.OutputStreamWriter;

import android.content.Context;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;

public abstract class Arquivo extends Objeto {

  private String _dir;
  private String _dirCompleto;
  private String _strConteudo;

  public String getDir() {
    return _dir;
  }

  public String getDirCompleto() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (!Utils.getBooStrVazia(_dirCompleto)) {
        return _dirCompleto;
      }

      _dirCompleto = Utils.STRING_VAZIA;
      _dirCompleto += this.getDir();
      _dirCompleto += this.getStrNome();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _dirCompleto;
  }

  public String getStrConteudo() {
    return _strConteudo;
  }

  /**
   * Salva o arquivo no diret�rio indicado no atributo "dirCompleto".
   */
  public void salvar() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(App.getI().getContext()
          .openFileOutput(this.getStrNome(), Context.MODE_PRIVATE));
      outputStreamWriter.write(this.getStrConteudo());
      outputStreamWriter.close();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setDir(String dir) {
    // VARI�VEIS

    String dirCompleto;

    // FIM VARI�VEIS
    try {
      // A��ES

      _dir = dir;

      dirCompleto = Utils.STRING_VAZIA;
      dirCompleto += _dir;
      dirCompleto += "//";
      dirCompleto += this.getStrNome();

      this.setDirCompleto(dirCompleto);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setDirCompleto(String dirCompleto) {
    // VARI�VEIS

    File fil;

    // FIM VARI�VEIS
    try {
      // A��ES

      _dirCompleto = dirCompleto;

      fil = new File(_dirCompleto);

      this.setStrNome(fil.getName());
      this.setDir(fil.getPath());

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setStrConteudo(String strConteudo) {
    _strConteudo = strConteudo;
  }

}
