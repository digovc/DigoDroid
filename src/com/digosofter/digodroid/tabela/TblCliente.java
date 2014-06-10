package com.digosofter.digodroid.tabela;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class TblCliente extends TblMain {

  private DbColuna _clnIntPessoaId;

  public TblCliente() {

    super("tblCliente");

    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES
      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(133), ex);

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

  @Override
  protected int inicializarColunas(int intOrdem) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      intOrdem = super.inicializarColunas(intOrdem);

      this.getClnDttAlteracao().setIntOrdem(++intOrdem);
      this.getClnDttCadastro().setIntOrdem(++intOrdem);
      this.getClnDttExclusao().setIntOrdem(++intOrdem);
      this.getClnIntId().setIntOrdem(++intOrdem);
      this.getClnIntPessoaId().setIntOrdem(++intOrdem);
      this.getClnIntUsuarioAlteracaoId().setIntOrdem(++intOrdem);
      this.getClnIntUsuarioCadastroId().setIntOrdem(++intOrdem);
      this.getClnIntUsuarioExclusaoId().setIntOrdem(++intOrdem);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return intOrdem;
  }

}
