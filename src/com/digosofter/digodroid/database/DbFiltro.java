package com.digosofter.digodroid.database;

import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public class DbFiltro extends Objeto {
	// CONSTANTES

	public enum EnmOperador {
		IGUAL, MAIOR, MENOR, MAIOR_IGUAL, MENOR_IGUAL
	}

	// FIM CONSTANTES

	// ATRIBUTOS

	private EnmOperador _enmOperador = EnmOperador.IGUAL;

	public EnmOperador getEnmOperador() {
		return _enmOperador;
	}

	public String getStrOperador() {
		String strOperador = "=";
		switch (this.getEnmOperador()) {
		case IGUAL:
			strOperador = "=";
			break;
		case MAIOR:
			strOperador = ">";
			break;
		case MAIOR_IGUAL:
			strOperador = ">=";
			break;
		case MENOR:
			strOperador = "<";
			break;
		case MENOR_IGUAL:
			strOperador = "<=";
			break;
		default:
			strOperador = "=";
			break;
		}
		return strOperador;
	}

	public void setEnmOperador(EnmOperador enmOperador) {
		_enmOperador = enmOperador;
	}

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

			new Erro("Erro ao instanciar objeto do tipo 'DbFiltro'.\n", ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
