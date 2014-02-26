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

		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(134), ex.getMessage());

		} finally {
		}
	}

	public DbColuna getClnDttAlteracao() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_clnDttAlteracao == null) {

				_clnDttAlteracao = new DbColuna("dttAlteracao", this);
				_clnDttAlteracao.setEnmTipo(EnmTipo.TEXT);
				_clnDttAlteracao.setStrNomeExibicao("Alteração");
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnDttAlteracao;
	}

	public DbColuna getClnDttCadastro() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_clnDttCadastro == null) {

				_clnDttCadastro = new DbColuna("dttCadastro", this);
				_clnDttCadastro.setEnmTipo(EnmTipo.TEXT);
				_clnDttCadastro.setStrNomeExibicao("Alteração");
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnDttCadastro;
	}

	public DbColuna getClnDttExclusao() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_clnDttExclusao == null) {

				_clnDttExclusao = new DbColuna("dttExclusao", this);
				_clnDttExclusao.setEnmTipo(EnmTipo.TEXT);
				_clnDttExclusao.setStrNomeExibicao("Alteração");
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnDttExclusao;
	}

	public DbColuna getClnIntId() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_clnIntId == null) {

				_clnIntId = new DbColuna("intId", this);
				_clnIntId.setBooChavePrimaria(true);
				_clnIntId.setEnmTipo(EnmTipo.INTEGER);
				_clnIntId.setStrNomeExibicao("código");
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnIntId;
	}

	public DbColuna getClnIntUsuarioAlteracaoId() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_clnIntUsuarioAlteracaoId == null) {

				_clnIntUsuarioAlteracaoId = new DbColuna("IntUsuarioAlteracaoId", this);
				_clnIntUsuarioAlteracaoId.setEnmTipo(EnmTipo.INTEGER);
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnIntUsuarioAlteracaoId;
	}

	public DbColuna getClnIntUsuarioCadastroId() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_clnIntUsuarioCadastroId == null) {

				_clnIntUsuarioCadastroId = new DbColuna("IntUsuarioCadastroId", this);
				_clnIntUsuarioCadastroId.setEnmTipo(EnmTipo.INTEGER);
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnIntUsuarioCadastroId;
	}

	public DbColuna getClnIntUsuarioExclusaoId() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_clnIntUsuarioExclusaoId == null) {

				_clnIntUsuarioExclusaoId = new DbColuna("IntUsuarioExclusaoId", this);
				_clnIntUsuarioExclusaoId.setEnmTipo(EnmTipo.INTEGER);
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnIntUsuarioExclusaoId;
	}

}
