package com.digosofter.digodroid;

public abstract class Objeto {
	// CONSTANTES

	public static int INTCONTAGEM;

	// FIM CONSTANTES

	// ATRIBUTOS

	private int _intId = Objeto.INTCONTAGEM;

	public int getIntId() {
		return _intId;
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
		if (_strNomeExibicao == null) {
			_strNomeExibicao = _strNome;
		}
		return _strNomeExibicao;
	}

	public void setStrNomeExibicao(String strNomeExibicao) {
		_strNomeExibicao = strNomeExibicao;
	}

	public String getStrNomeSimplificado() {
		return Utils.getStrSimplificada(_strNome);
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public Objeto() {
		try {
			// VARIÁVEIS

			Objeto.INTCONTAGEM++;

			// FIM VARIÁVEIS

			// AÇÕES

			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
