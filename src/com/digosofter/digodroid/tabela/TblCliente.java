package com.digosofter.digodroid.tabela;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class TblCliente extends TblMain {

  private DbColuna _clnIntPessoaId;

  public TblCliente() {

    super("tblCliente");
    try {
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(133), ex);
    }
    finally {
    }
  }

  public DbColuna getClnIntPessoaId() {

    try {
      if (_clnIntPessoaId == null) {
        _clnIntPessoaId = new DbColuna("intPessoaId", this, EnmTipo.INTEGER);
      }
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
      this.getClnDttAlteracao().setIntOrdem(++intOrdem);
      this.getClnDttCadastro().setIntOrdem(++intOrdem);
      this.getClnDttExclusao().setIntOrdem(++intOrdem);
      this.getClnIntId().setIntOrdem(++intOrdem);
      this.getClnIntPessoaId().setIntOrdem(++intOrdem);
      this.getClnIntUsuarioAlteracaoId().setIntOrdem(++intOrdem);
      this.getClnIntUsuarioCadastroId().setIntOrdem(++intOrdem);
      this.getClnIntUsuarioExclusaoId().setIntOrdem(++intOrdem);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return intOrdem;
  }
}
