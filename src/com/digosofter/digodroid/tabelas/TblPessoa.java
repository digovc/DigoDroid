package com.digosofter.digodroid.tabelas;

import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class TblPessoa extends TblMain {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private DbColuna _clnStrNome;

	public DbColuna getClnStrNome() {
		return _clnStrNome;
	}

	private void setClnStrNome(DbColuna clnStrNome) {
		_clnStrNome = clnStrNome;
	}

	private DbColuna _clnStrSobrenome;

	public DbColuna getClnStrSobrenome() {
		return _clnStrSobrenome;
	}

	private void setClnStrSobrenome(DbColuna clnStrSobrenome) {
		_clnStrSobrenome = clnStrSobrenome;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public TblPessoa() {
		// VARIÁVEIS

		super("tblPessoa");

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setClnStrNome(new DbColuna("strNome", this, EnmTipo.TEXT));
			this.getClnStrNome().setBooClnNome(true);
			this.getClnStrNome().setBooOrdemCadastro(true);
			this.getClnStrNome().setBooVisivelCadastro(true);
			this.getClnStrNome().setStrNomeExibicao("nome");

			this.setClnStrSobrenome(new DbColuna("strSobrenome", this, EnmTipo.TEXT));
			this.getClnStrSobrenome().setBooVisivelCadastro(true);
			this.getClnStrSobrenome().setStrNomeExibicao("sobrenome");

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n", ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
