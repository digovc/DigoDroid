package com.digosofter.digodroid.tabela;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class TblUsuario extends TblMain {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private DbColuna _clnIntPessoaId;

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

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnIntPessoaId;
	}

	private DbColuna _clnStrLogin;

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

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnStrLogin;
	}

	private DbColuna _clnStrPassword;

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

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnStrPassword;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public TblUsuario() {

		super("tblUsuario");

		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(136), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS

	@Override
	protected void inicializarColunas() {
		// VARIÁVEIS

		int intOrdem;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			intOrdem = 0;

			this.getClnDttAlteracao().setIntOrdem(++intOrdem);
			this.getClnDttCadastro().setIntOrdem(++intOrdem);
			this.getClnDttExclusao().setIntOrdem(++intOrdem);
			this.getClnIntId().setIntOrdem(++intOrdem);
			this.getClnIntPessoaId().setIntOrdem(++intOrdem);
			this.getClnIntUsuarioAlteracaoId().setIntOrdem(++intOrdem);
			this.getClnIntUsuarioCadastroId().setIntOrdem(++intOrdem);
			this.getClnIntUsuarioExclusaoId().setIntOrdem(++intOrdem);
			this.getClnStrLogin().setIntOrdem(++intOrdem);
			this.getClnStrPassword().setIntOrdem(++intOrdem);


			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
