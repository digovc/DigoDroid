package DigoDroid.DataBase;

import DigoDroid.Objeto;

public class DbColuna extends Objeto {
	// CONSTANTES

	public enum EnmTipo {
		INTEGER, TEXT, NONE, REAL, NUMERIC
	}

	// FIM CONSTANTES

	// ATRIBUTOS

	private DbTabela _objDbTabela;

	public DbTabela getObjDbTabela() {
		return _objDbTabela;
	}

	public void setObjDbTabela(DbTabela objDbTabela) {
		_objDbTabela = objDbTabela;
		_objDbTabela.getLstObjDbColuna().add(this);
	}

	private EnmTipo _objEnmTipo = EnmTipo.TEXT;

	public EnmTipo getObjEnmTipo() {
		return _objEnmTipo;
	}

	public void setObjEnmTipo(EnmTipo objEnmTipo) {
		_objEnmTipo = objEnmTipo;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public DbColuna(String strNome, DbTabela objDbTabela, EnmTipo objEnmTipo) {
		// VARIÁVEIS

		this.setStrNome(strNome);
		this.setObjDbTabela(objDbTabela);
		this.setObjEnmTipo(objEnmTipo);

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
