package com.digosofter.digodroid;

import com.digosofter.digodroid.erro.Erro;

public class MensagemUsuario extends Objeto {
	// CONSTANTES

	public enum EnmLingua {
		INGLES, PORTUGUES
	}

	// FIM CONSTANTES

	// ATRIBUTOS

	private EnmLingua _enmLingua = EnmLingua.PORTUGUES;

	public EnmLingua getEnmLingua() {
		return _enmLingua;
	}

	private void setEnmLingua(EnmLingua enmLingua) {
		_enmLingua = enmLingua;
	}

	private int _intId;

	public int getIntId() {
		return _intId;
	}

	private void setIntId(int intId) {
		_intId = intId;
	}

	private String _strTexto;

	public String getStrTexto() {
		return _strTexto;
	}

	private void setStrTexto(String strTexto) {
		_strTexto = strTexto;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public MensagemUsuario(String strTexto, int intId) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setIntId(intId);
			this.setStrTexto(strTexto);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(107), ex.getMessage());

		} finally {
		}
	}

	public MensagemUsuario(String strTexto, int intId, EnmLingua enmLingua) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setIntId(intId);
			this.setStrTexto(strTexto);
			this.setEnmLingua(enmLingua);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(107), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
