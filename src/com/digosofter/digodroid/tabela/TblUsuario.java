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
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_clnIntPessoaId == null) {
				_clnIntPessoaId = new DbColuna("intPessoaId", this, EnmTipo.INTEGER);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnIntPessoaId;
	}

	private DbColuna _clnStrLogin;

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

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnStrLogin;
	}

	private DbColuna _clnStrPassword;

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

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnStrPassword;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public TblUsuario() {

		super("tblUsuario");

		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(136), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS

	@Override
	protected void inicializarColunas() {
		// VARI�VEIS

		int intOrdem;

		// FIM VARI�VEIS
		try {
			// A��ES

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


			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
