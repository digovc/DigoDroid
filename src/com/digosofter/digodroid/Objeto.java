package com.digosofter.digodroid;

import com.digosofter.digodroid.erro.Erro;

public abstract class Objeto {
	// CONSTANTES

	private static int INT_INDEX;

	// FIM CONSTANTES

	// ATRIBUTOS

	private int _intIndexObjeto = Objeto.INT_INDEX;

	public int getIntIndexObjeto() {
		return _intIndexObjeto;
	}

	private String _strDescricao;

	public String getStrDescricao() {
		return _strDescricao;
	}

	public void setStrDescricao(String strDescricao) {
		_strDescricao = strDescricao;
	}

	private String _strNome;

	public String getStrNome() {
		return _strNome;
	}

	public void setStrNome(String strNome) {
		_strNome = strNome;
	}

	private String _strNomeExibicao;

	public String getStrNomeExibicao() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_strNomeExibicao == null) {
				_strNomeExibicao = _strNome;
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _strNomeExibicao;
	}

	public void setStrNomeExibicao(String strNomeExibicao) {
		_strNomeExibicao = strNomeExibicao;
	}

	private String _strNomeSimplificado;

	public String getStrNomeSimplificado() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			_strNomeSimplificado = Utils.getStrSimplificada(_strNome);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _strNomeSimplificado;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public Objeto() {
		try {
			// VARIÁVEIS

			Objeto.INT_INDEX++;

			// FIM VARIÁVEIS

			// AÇÕES

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(108), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
