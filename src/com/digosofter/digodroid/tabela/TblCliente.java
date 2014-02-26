package com.digosofter.digodroid.tabela;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class TblCliente extends TblMain {

	private DbColuna _clnIntPessoaId;

	public TblCliente() {

		super("tblCliente");

		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(133), ex.getMessage());

		} finally {
		}
	}

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

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

}
