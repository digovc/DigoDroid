package com.digosofter.digodroid.database;

import com.digosofter.digodroid.App;
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

	private String _strCondicao = "AND";

	public String getStrCondicao() {
		return _strCondicao;
	}

	public void setStrCondicao(String strCondicao) {
		_strCondicao = strCondicao;
	}

	private String _strFiltro;

	public String getStrFiltro() {
		return _strFiltro;
	}

	private void setStrFiltro(String strFiltro) {
		_strFiltro = strFiltro;
	}

	private String _strOperador;

	public String getStrOperador() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			switch (this.getEnmOperador()) {
			case IGUAL:
				_strOperador = "=";
				break;
			case MAIOR:
				_strOperador = ">";
				break;
			case MAIOR_IGUAL:
				_strOperador = ">=";
				break;
			case MENOR:
				_strOperador = "<";
				break;
			case MENOR_IGUAL:
				_strOperador = "<=";
				break;
			default:
				_strOperador = "=";
				break;
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}

		return _strOperador;
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

			new Erro(App.getApp().getStrTexto(121), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
