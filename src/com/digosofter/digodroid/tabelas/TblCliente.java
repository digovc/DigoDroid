package com.digosofter.digodroid.tabelas;

import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class TblCliente extends TblMain {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private DbColuna _clnIntPessoaId;

	private DbColuna getClnIntPessoaId() {
		return _clnIntPessoaId;
	}

	private void setClnIntPessoaId(DbColuna clnIntPessoaId) {
		_clnIntPessoaId = clnIntPessoaId;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public TblCliente() {
		// VARI�VEIS

		super("tblCliente");

		// FIM VARI�VEIS
		try {
			// A��ES

			this.setClnIntPessoaId(new DbColuna("intPessoaId", this, EnmTipo.INTEGER));

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n", ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS
	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
