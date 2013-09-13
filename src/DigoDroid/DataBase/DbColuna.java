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
		// VARI�VEIS

		this.setStrNome(strNome);
		this.setObjDbTabela(objDbTabela);
		this.setObjEnmTipo(objEnmTipo);

		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS
	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
