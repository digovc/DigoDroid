package com.digosofter.digodroid.tabela;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.database.DbTabela;
import com.digosofter.digodroid.erro.Erro;

public abstract class TblMain extends DbTabela {

  private DbColuna _clnDttAlteracao;

  private DbColuna _clnDttCadastro;

  private DbColuna _clnDttExclusao;

  private DbColuna _clnIntId;

  private DbColuna _clnIntUsuarioAlteracaoId;

  private DbColuna _clnIntUsuarioCadastroId;

  private DbColuna _clnIntUsuarioExclusaoId;

  public TblMain(String strNome) {

    super(strNome);

    try {

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(134), ex);

    }
    finally {
    }
  }

  public DbColuna getClnDttAlteracao() {

    try {

      if (_clnDttAlteracao == null) {

        _clnDttAlteracao = new DbColuna("dttAlteracao", this);
        _clnDttAlteracao.setEnmTipo(EnmTipo.TEXT);
        _clnDttAlteracao.setStrNomeExibicao("Altera��o");
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _clnDttAlteracao;
  }

  public DbColuna getClnDttCadastro() {

    try {

      if (_clnDttCadastro == null) {

        _clnDttCadastro = new DbColuna("dttCadastro", this);
        _clnDttCadastro.setEnmTipo(EnmTipo.TEXT);
        _clnDttCadastro.setStrNomeExibicao("Altera��o");
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _clnDttCadastro;
  }

  public DbColuna getClnDttExclusao() {

    try {

      if (_clnDttExclusao == null) {

        _clnDttExclusao = new DbColuna("dttExclusao", this);
        _clnDttExclusao.setEnmTipo(EnmTipo.TEXT);
        _clnDttExclusao.setStrNomeExibicao("Altera��o");
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _clnDttExclusao;
  }

  public DbColuna getClnIntId() {

    try {

      if (_clnIntId == null) {

        _clnIntId = new DbColuna("intId", this);
        _clnIntId.setBooChavePrimaria(true);
        _clnIntId.setEnmTipo(EnmTipo.INTEGER);
        _clnIntId.setStrNomeExibicao("c�digo");
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _clnIntId;
  }

  public DbColuna getClnIntUsuarioAlteracaoId() {

    try {

      if (_clnIntUsuarioAlteracaoId == null) {

        _clnIntUsuarioAlteracaoId = new DbColuna("IntUsuarioAlteracaoId", this);
        _clnIntUsuarioAlteracaoId.setEnmTipo(EnmTipo.INTEGER);
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _clnIntUsuarioAlteracaoId;
  }

  public DbColuna getClnIntUsuarioCadastroId() {

    try {

      if (_clnIntUsuarioCadastroId == null) {

        _clnIntUsuarioCadastroId = new DbColuna("IntUsuarioCadastroId", this);
        _clnIntUsuarioCadastroId.setEnmTipo(EnmTipo.INTEGER);
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _clnIntUsuarioCadastroId;
  }

  public DbColuna getClnIntUsuarioExclusaoId() {

    try {

      if (_clnIntUsuarioExclusaoId == null) {

        _clnIntUsuarioExclusaoId = new DbColuna("IntUsuarioExclusaoId", this);
        _clnIntUsuarioExclusaoId.setEnmTipo(EnmTipo.INTEGER);
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _clnIntUsuarioExclusaoId;
  }

  @Override
  protected int inicializarColunas(int intOrdem) {

    try {

      intOrdem = super.inicializarColunas(intOrdem);

      this.getClnDttAlteracao().setIntOrdem(++intOrdem);
      this.getClnDttCadastro().setIntOrdem(++intOrdem);
      this.getClnDttExclusao().setIntOrdem(++intOrdem);
      this.getClnIntId().setIntOrdem(++intOrdem);
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
