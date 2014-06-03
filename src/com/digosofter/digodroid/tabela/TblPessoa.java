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

    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES
      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(135), ex.getMessage());

    } finally {
    }
  }

  public DbColuna getClnStrNome() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_clnStrNome == null) {

        _clnStrNome = new DbColuna("strNome", this, EnmTipo.TEXT);
        _clnStrNome.setBooClnNome(true);
        _clnStrNome.setBooOrdemCadastro(true);
        _clnStrNome.setBooVisivelCadastro(true);
        _clnStrNome.setStrNomeExibicao("nome");
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _clnStrNome;
  }

  public DbColuna getClnStrSobrenome() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_clnStrSobrenome == null) {

        _clnStrSobrenome = new DbColuna("strSobrenome", this, EnmTipo.TEXT);
        _clnStrSobrenome.setBooVisivelCadastro(true);
        _clnStrSobrenome.setStrNomeExibicao("sobrenome");
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _clnStrSobrenome;
  }

  @Override
  protected void inicializarColunas() {
    // VARIÁVEIS

    int intOrdem;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

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

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }
  }

}
