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

    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES
      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(136), ex);

    } finally {
    }
  }

  public DbColuna getClnIntPessoaId() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_clnIntPessoaId == null) {
        _clnIntPessoaId = new DbColuna("intPessoaId", this, EnmTipo.INTEGER);
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _clnIntPessoaId;
  }

  public DbColuna getClnStrLogin() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_clnStrLogin == null) {

        _clnStrLogin = new DbColuna("strLogin", this, EnmTipo.TEXT);
        _clnStrLogin.setStrNomeExibicao("login");
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _clnStrLogin;
  }

  public DbColuna getClnStrPassword() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_clnStrPassword == null) {

        _clnStrPassword = new DbColuna("strPassword", this, EnmTipo.TEXT);
        _clnStrPassword.setStrNomeExibicao("Password");
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _clnStrPassword;
  }

  @Override
  protected int inicializarColunas(int intOrdem) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      intOrdem = super.inicializarColunas(intOrdem);

      this.getClnIntPessoaId().setIntOrdem(++intOrdem);
      this.getClnStrLogin().setIntOrdem(++intOrdem);
      this.getClnStrPassword().setIntOrdem(++intOrdem);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return intOrdem;
  }

}
