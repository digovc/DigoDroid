package com.digosofter.digodroid.tabela;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class TblCliente extends TblMain {

  private DbColuna _clnIntPessoaId;

  public TblCliente() {

    super("tbl_cliente");
  }

  public DbColuna getClnIntPessoaId() {

    try {
      if (_clnIntPessoaId != null) {
        return _clnIntPessoaId;
      }
      _clnIntPessoaId = new DbColuna("int_pessoa_id", this, EnmTipo.INTEGER);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _clnIntPessoaId;
  }

  @Override
  protected int inicializarColunas(int intOrdem) {

    try {
      intOrdem = super.inicializarColunas(intOrdem);
      this.getClnIntPessoaId().setIntOrdem(++intOrdem);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return intOrdem;
  }
}
