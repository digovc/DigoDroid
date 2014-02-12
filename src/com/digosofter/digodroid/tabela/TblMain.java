package com.digosofter.digodroid.tabela;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DbColuna;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.database.DbTabela;
import com.digosofter.digodroid.erro.Erro;

public abstract class TblMain extends DbTabela {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private DbColuna _clnDttAlteracao;

	public DbColuna getClnDttAlteracao() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_clnDttAlteracao == null) {

				_clnDttAlteracao = new DbColuna("dttAlteracao", this);
				_clnDttAlteracao.setEnmTipo(EnmTipo.TEXT);
				_clnDttAlteracao.setStrNomeExibicao("Altera��o");
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnDttAlteracao;
	}

	private DbColuna _clnDttCadastro;

	public DbColuna getClnDttCadastro() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_clnDttCadastro == null) {

				_clnDttCadastro = new DbColuna("dttCadastro", this);
				_clnDttCadastro.setEnmTipo(EnmTipo.TEXT);
				_clnDttCadastro.setStrNomeExibicao("Altera��o");
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnDttCadastro;
	}

	private DbColuna _clnDttExclusao;

	public DbColuna getClnDttExclusao() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_clnDttExclusao == null) {

				_clnDttExclusao = new DbColuna("dttExclusao", this);
				_clnDttExclusao.setEnmTipo(EnmTipo.TEXT);
				_clnDttExclusao.setStrNomeExibicao("Altera��o");
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnDttExclusao;
	}

	private DbColuna _clnIntId;

	public DbColuna getClnIntId() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_clnIntId == null) {

				_clnIntId = new DbColuna("intId", this);
				_clnIntId.setBooChavePrimaria(true);
				_clnIntId.setEnmTipo(EnmTipo.INTEGER);
				_clnIntId.setStrNomeExibicao("c�digo");
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnIntId;
	}

	private DbColuna _clnIntUsuarioAlteracaoId;

	public DbColuna getClnIntUsuarioAlteracaoId() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_clnIntUsuarioAlteracaoId == null) {

				_clnIntUsuarioAlteracaoId = new DbColuna("IntUsuarioAlteracaoId", this);
				_clnIntUsuarioAlteracaoId.setEnmTipo(EnmTipo.INTEGER);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnIntUsuarioAlteracaoId;
	}

	private DbColuna _clnIntUsuarioCadastroId;

	public DbColuna getClnIntUsuarioCadastroId() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_clnIntUsuarioCadastroId == null) {

				_clnIntUsuarioCadastroId = new DbColuna("IntUsuarioCadastroId", this);
				_clnIntUsuarioCadastroId.setEnmTipo(EnmTipo.INTEGER);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnIntUsuarioCadastroId;
	}

	private DbColuna _clnIntUsuarioExclusaoId;

	public DbColuna getClnIntUsuarioExclusaoId() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_clnIntUsuarioExclusaoId == null) {

				_clnIntUsuarioExclusaoId = new DbColuna("IntUsuarioExclusaoId", this);
				_clnIntUsuarioExclusaoId.setEnmTipo(EnmTipo.INTEGER);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnIntUsuarioExclusaoId;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public TblMain(String strNome) {

		super(strNome);

		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(134), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS
	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS

}
