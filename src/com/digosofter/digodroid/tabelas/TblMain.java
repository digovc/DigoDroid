package com.digosofter.digodroid.tabelas;

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
		return _clnDttAlteracao;
	}

	private void setClnDttAlteracao(DbColuna clnDttAlteracao) {
		_clnDttAlteracao = clnDttAlteracao;
	}

	private DbColuna _clnDttCadastro;

	public DbColuna getClnDttCadastro() {
		return _clnDttCadastro;
	}

	private void setClnDttCadastro(DbColuna clnDttCadastro) {
		_clnDttCadastro = clnDttCadastro;
	}

	private DbColuna _clnDttExclusao;

	public DbColuna getClnDttExclusao() {
		return _clnDttExclusao;
	}

	private void setClnDttExclusao(DbColuna clnDttExclusao) {
		_clnDttExclusao = clnDttExclusao;
	}

	private DbColuna _clnIntId;

	public DbColuna getClnIntId() {
		return _clnIntId;
	}

	private void setClnIntId(DbColuna clnIntId) {
		_clnIntId = clnIntId;
	}

	private DbColuna _clnIntUsuarioAlteracaoId;

	public DbColuna getClnIntUsuarioAlteracaoId() {
		return _clnIntUsuarioAlteracaoId;
	}

	private void setClnIntUsuarioAlteracaoId(DbColuna clnIntUsuarioAlteracaoId) {
		_clnIntUsuarioAlteracaoId = clnIntUsuarioAlteracaoId;
	}

	private DbColuna _clnIntUsuarioCadastroId;

	public DbColuna getClnIntUsuarioCadastroId() {
		return _clnIntUsuarioCadastroId;
	}

	private void setClnIntUsuarioCadastroId(DbColuna clnIntUsuarioCadastroId) {
		_clnIntUsuarioCadastroId = clnIntUsuarioCadastroId;
	}

	private DbColuna _clnIntUsuarioExclusaoId;

	public DbColuna getClnIntUsuarioExclusaoId() {
		return _clnIntUsuarioExclusaoId;
	}

	private void setClnIntUsuarioExclusaoId(DbColuna clnIntUsuarioExclusaoId) {
		_clnIntUsuarioExclusaoId = clnIntUsuarioExclusaoId;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public TblMain(String strNome) {
		// VARIÁVEIS

		super(strNome);

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setClnDttAlteracao(new DbColuna("dttAlteracao", this));
			this.getClnDttAlteracao().setEnmTipo(EnmTipo.TEXT);
			this.getClnDttAlteracao().setStrNomeExibicao("Alteração");

			this.setClnDttCadastro(new DbColuna("dttCadastro", this));
			this.getClnDttCadastro().setEnmTipo(EnmTipo.TEXT);
			this.getClnDttCadastro().setStrNomeExibicao("Alteração");

			this.setClnDttExclusao(new DbColuna("dttExclusao", this));
			this.getClnDttExclusao().setEnmTipo(EnmTipo.TEXT);
			this.getClnDttExclusao().setStrNomeExibicao("Alteração");

			this.setClnIntId(new DbColuna("intId", this));
			this.getClnIntId().setBooChavePrimaria(true);
			this.getClnIntId().setEnmTipo(EnmTipo.INTEGER);
			this.getClnIntId().setStrNomeExibicao("código");

			this.setClnIntUsuarioAlteracaoId(new DbColuna("IntUsuarioAlteracaoId", this));
			this.getClnIntUsuarioAlteracaoId().setEnmTipo(EnmTipo.INTEGER);

			this.setClnIntUsuarioCadastroId(new DbColuna("IntUsuarioCadastroId", this));
			this.getClnIntUsuarioCadastroId().setEnmTipo(EnmTipo.INTEGER);

			this.setClnIntUsuarioExclusaoId(new DbColuna("IntUsuarioExclusaoId", this));
			this.getClnIntUsuarioExclusaoId().setEnmTipo(EnmTipo.INTEGER);

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
