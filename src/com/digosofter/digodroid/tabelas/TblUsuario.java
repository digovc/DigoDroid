package com.digosofter.digodroid.tabelas;

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
		return _clnIntPessoaId;
	}

	private void setClnIntPessoaId(DbColuna clnIntPessoaId) {
		_clnIntPessoaId = clnIntPessoaId;
	}

	private DbColuna _clnStrLogin;

	public DbColuna getClnStrLogin() {
		return _clnStrLogin;
	}

	private void setClnStrLogin(DbColuna clnStrLogin) {
		_clnStrLogin = clnStrLogin;
	}

	private DbColuna _clnStrPassword;

	public DbColuna getClnStrPassword() {
		return _clnStrPassword;
	}

	private void setClnStrPassword(DbColuna clnStrPassword) {
		_clnStrPassword = clnStrPassword;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public TblUsuario() {

		super("tblUsuario");

		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setClnIntPessoaId(new DbColuna("intPessoaId", this, EnmTipo.INTEGER));

			this.setClnStrLogin(new DbColuna("strLogin", this, EnmTipo.TEXT));
			this.getClnStrLogin().setStrNomeExibicao("login");

			this.setClnStrPassword(new DbColuna("strPassword", this, EnmTipo.TEXT));
			this.getClnStrPassword().setStrNomeExibicao("Password");

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(136), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS
	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
