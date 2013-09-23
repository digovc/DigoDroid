package com.digosofter.digodroid.database;

import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;

public class DbColuna extends Objeto {
	// CONSTANTES

	public enum EnmTipo {
		INTEGER, TEXT, NONE, REAL, NUMERIC
	}

	// FIM CONSTANTES

	// ATRIBUTOS

	private boolean _booChavePrimaria = false;

	public boolean getBooChavePrimaria() {
		return _booChavePrimaria;
	}

	public void setBooChavePrimaria(boolean booChavePrimaria) {
		for (DbColuna cln : this.getTbl().getLstObjDbColuna()) {
			cln._booChavePrimaria = false;
		}
		_booChavePrimaria = booChavePrimaria;
	}

	private boolean _booClnNome = false;

	public boolean getBooClnNome() {
		return _booClnNome;
	}

	public void setBooClnNome(boolean booClnNome) {
		for (DbColuna cln : this.getTbl().getLstObjDbColuna()) {
			cln._booClnNome = false;
		}
		_booClnNome = booClnNome;
	}

	private boolean _booOrdemCadastro = false;

	public boolean getBooOrdemCadastro() {
		return _booOrdemCadastro;
	}

	public void setBooOrdemCadastro(boolean booOrdemCadastro) {
		_booOrdemCadastro = booOrdemCadastro;
		if (_booOrdemCadastro) {
			for (DbColuna cln : this.getTbl().getLstObjDbColuna()) {
				if (cln.getIntId() != this.getIntId()) {
					cln.setBooOrdemCadastro(false);
				}
			}
		}
	}

	private boolean _booVisivelCadastro = true;

	public boolean getBooVisivelCadastro() {
		return _booVisivelCadastro;
	}

	public void setBooVisivelCadastro(boolean booVisivelCadastro) {
		_booVisivelCadastro = booVisivelCadastro;
	}

	private DbColuna _clnReferencia;

	public DbColuna getClnReferencia() {
		return _clnReferencia;
	}

	public void setClnReferencia(DbColuna clnReferencia) {
		_clnReferencia = clnReferencia;
	}

	private EnmTipo _enmTipo = EnmTipo.TEXT;

	public EnmTipo getEnmTipo() {
		return _enmTipo;
	}

	public String getStrSqlTipo() {
		switch (this.getEnmTipo()) {
		case INTEGER:
			return "INTEGER";
		case NONE:
			return "NONE";
		case NUMERIC:
			return "NUMERIC";
		case REAL:
			return "REAL";
		case TEXT:
			return "TEXT";
		default:
			return "TEXT";
		}
	}

	public void setEnmTipo(EnmTipo enmTipo) {
		_enmTipo = enmTipo;
	}

	private String _strValor;

	public String getStrValor() {
		return _strValor;
	}

	public String getStrValorMonetario() {
		return Utils.getStrValorMonetario(Double.parseDouble(_strValor));
	}

	public void setStrValor(String strValor) {
		_strValor = strValor;
	}

	private DbTabela _tbl;

	public DbTabela getTbl() {
		return _tbl;
	}

	public void setTbl(DbTabela tbl) {
		_tbl = tbl;
		_tbl.getLstObjDbColuna().add(this);
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public DbColuna(String strNome, DbTabela objDbTabela) {
		// VARIÁVEIS

		this.setStrNome(strNome);
		this.setTbl(objDbTabela);

		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	public DbColuna(String strNome, DbTabela objDbTabela, EnmTipo objEnmTipo) {
		// VARIÁVEIS

		this.setStrNome(strNome);
		this.setTbl(objDbTabela);
		this.setEnmTipo(objEnmTipo);

		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	public DbColuna(String strNome, DbTabela objDbTabela, EnmTipo objEnmTipo, DbColuna clnReferencia) {
		// VARIÁVEIS

		this.setStrNome(strNome);
		this.setTbl(objDbTabela);
		this.setEnmTipo(objEnmTipo);
		this.setClnReferencia(clnReferencia);

		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
