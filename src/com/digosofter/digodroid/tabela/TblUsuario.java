package com.digosofter.digodroid.tabela;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class TblUsuario extends TblMain {

  private DbColuna _clnIntPessoaId;
  private DbColuna _clnStrLogin;
  private DbColuna _clnStrPassword;

  public TblUsuario() {

    super("tblUsuario");

    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES
      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(136), ex);

    } finally {
    }
  }

  public DbColuna getClnIntPessoaId() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_clnIntPessoaId == null) {
        _clnIntPessoaId = new DbColuna("intPessoaId", this, EnmTipo.INTEGER);
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _clnIntPessoaId;
  }

  public DbColuna getClnStrLogin() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_clnStrLogin == null) {

        _clnStrLogin = new DbColuna("strLogin", this, EnmTipo.TEXT);
        _clnStrLogin.setStrNomeExibicao("login");
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _clnStrLogin;
  }

  public DbColuna getClnStrPassword() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_clnStrPassword == null) {

        _clnStrPassword = new DbColuna("strPassword", this, EnmTipo.TEXT);
        _clnStrPassword.setStrNomeExibicao("Password");
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _clnStrPassword;
  }

  @Override
  protected int inicializarColunas(int intOrdem) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      intOrdem = super.inicializarColunas(intOrdem);

      this.getClnIntPessoaId().setIntOrdem(++intOrdem);
      this.getClnStrLogin().setIntOrdem(++intOrdem);
      this.getClnStrPassword().setIntOrdem(++intOrdem);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return intOrdem;
  }

}
