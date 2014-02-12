package com.digosofter.digodroid.tabela;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class TblCliente extends TblMain {
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

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public TblCliente() {

		super("tblCliente");

		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(133), ex.getMessage());

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
