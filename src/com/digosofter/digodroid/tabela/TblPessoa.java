package com.digosofter.digodroid.tabela;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class TblPessoa extends TblMain {

  private DbColuna _clnStrNome;
  private DbColuna _clnStrSobrenome;

  public TblPessoa() {

    super("tblPessoa");
    try {
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(135), ex);
    }
    finally {
    }
  }

  public DbColuna getClnStrNome() {

    try {
      if (_clnStrNome == null) {
        _clnStrNome = new DbColuna("strNome", this, EnmTipo.TEXT);
        _clnStrNome.setBooClnNome(true);
        _clnStrNome.setBooOrdemCadastro(true);
        _clnStrNome.setBooVisivelCadastro(true);
        _clnStrNome.setStrNomeExibicao("nome");
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _clnStrNome;
  }

  public DbColuna getClnStrSobrenome() {

    try {
      if (_clnStrSobrenome == null) {
        _clnStrSobrenome = new DbColuna("strSobrenome", this, EnmTipo.TEXT);
        _clnStrSobrenome.setBooVisivelCadastro(true);
        _clnStrSobrenome.setStrNomeExibicao("sobrenome");
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _clnStrSobrenome;
  }

  @Override
  protected int inicializarColunas(int intOrdem) {

    try {
      intOrdem = super.inicializarColunas(intOrdem);
      this.getClnStrNome().setIntOrdem(++intOrdem);
      this.getClnStrSobrenome().setIntOrdem(++intOrdem);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return intOrdem;
  }
}
