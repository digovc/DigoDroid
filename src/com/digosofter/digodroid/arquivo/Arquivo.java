package com.digosofter.digodroid.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;

public abstract class Arquivo extends Objeto {

  private String _dir;
  private String _dirCompleto;
  private String _strConteudo;

  /**
   * Faz uma c�pia deste arquivo para outra diret�rio.
   */
  public void copiar(String dirDestino) {
    // VARI�VEIS

    FileInputStream filOriginal = null;
    FileOutputStream filCopia = null;
    byte[] arrBytBuffer;
    int intLength;

    // FIM VARI�VEIS
    try {
      // A��ES

      filOriginal = new FileInputStream(this.getDirCompleto());
      filCopia = new FileOutputStream(dirDestino + "/" + this.getStrNome());

      arrBytBuffer = new byte[1024];

      while ((intLength = filOriginal.read(arrBytBuffer)) > 0) {
        filCopia.write(arrBytBuffer, 0, intLength);
      }

      filOriginal.close();
      filCopia.close();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  private void criarDiretorio() {
    // VARI�VEIS

    File fil;

    // FIM VARI�VEIS
    try {
      // A��ES

      fil = new File(this.getDir());
      fil.mkdirs();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

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

  private void mostrarMensagemSalvo() {
    // VARI�VEIS

    String strMensagem;

    // FIM VARI�VEIS
    try {
      // A��ES

      strMensagem = Utils.STRING_VAZIA;
      strMensagem = "Arquivo \"_arq_nome\" salvo com sucesso.";
      strMensagem = strMensagem.replace("_arq_nome", this.getDirCompleto());

      App.getI().mostrarNoficacao(strMensagem);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  /**
   * Salva o arquivo no diret�rio indicado no atributo "dirCompleto".
   */
  public void salvar() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.criarDiretorio();
      FileWriter out = new FileWriter(new File(this.getDirCompleto()));
      out.write(this.getStrConteudo());
      out.close();

      this.mostrarMensagemSalvo();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setDir(String dir) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      _dir = dir;

      _dirCompleto = Utils.STRING_VAZIA;
      _dirCompleto += _dir;
      _dirCompleto += "//";
      _dirCompleto += this.getStrNome();

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
      _dir = fil.getPath().replace(this.getStrNome(), Utils.STRING_VAZIA);

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
