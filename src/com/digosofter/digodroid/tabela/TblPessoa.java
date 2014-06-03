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

    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES
      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(135), ex.getMessage());

    } finally {
    }
  }

  public DbColuna getClnStrNome() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_clnStrNome == null) {

        _clnStrNome = new DbColuna("strNome", this, EnmTipo.TEXT);
        _clnStrNome.setBooClnNome(true);
        _clnStrNome.setBooOrdemCadastro(true);
        _clnStrNome.setBooVisivelCadastro(true);
        _clnStrNome.setStrNomeExibicao("nome");
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _clnStrNome;
  }

  public DbColuna getClnStrSobrenome() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_clnStrSobrenome == null) {

        _clnStrSobrenome = new DbColuna("strSobrenome", this, EnmTipo.TEXT);
        _clnStrSobrenome.setBooVisivelCadastro(true);
        _clnStrSobrenome.setStrNomeExibicao("sobrenome");
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _clnStrSobrenome;
  }

  @Override
  protected void inicializarColunas() {
    // VARI�VEIS

    int intOrdem;

    // FIM VARI�VEIS
    try {
      // A��ES

      intOrdem = 1;

      this.getClnDttAlteracao().setIntOrdem(++intOrdem);
      this.getClnDttCadastro().setIntOrdem(++intOrdem);
      this.getClnDttExclusao().setIntOrdem(++intOrdem);
      this.getClnIntId().setIntOrdem(++intOrdem);
      this.getClnIntUsuarioAlteracaoId().setIntOrdem(++intOrdem);
      this.getClnIntUsuarioCadastroId().setIntOrdem(++intOrdem);
      this.getClnIntUsuarioExclusaoId().setIntOrdem(++intOrdem);
      this.getClnStrNome().setIntOrdem(++intOrdem);
      this.getClnStrSobrenome().setIntOrdem(++intOrdem);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }
  }

}
