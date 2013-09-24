package com.digosofter.digodroid.database;

import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public class DbFiltro extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private DbColuna _clnFiltro;

	public DbColuna getClnFiltro() {
		return _clnFiltro;
	}

	private void setClnFiltro(DbColuna clnFiltro) {
		_clnFiltro = clnFiltro;
	}

	private String _strFiltro;

	public String getStrFiltro() {
		return _strFiltro;
	}

	private void setStrFiltro(String strFiltro) {
		_strFiltro = strFiltro;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public DbFiltro(DbColuna clnFiltro, String strFiltro) {
		// VARIÁVEIS

		this.setClnFiltro(clnFiltro);
		this.setStrFiltro(strFiltro);

		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
