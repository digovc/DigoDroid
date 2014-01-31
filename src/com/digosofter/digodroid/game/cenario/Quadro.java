package com.digosofter.digodroid.game.cenario;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public class Quadro extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private int _intAnimacaoPosicao;

	private int getIntAnimacaoPosicao() {
		return _intAnimacaoPosicao;
	}

	private void setIntAnimacaoPosicao(int intAnimacaoPosicao) {
		_intAnimacaoPosicao = intAnimacaoPosicao;
	}

	private int _intX;

	private int getIntX() {
		return _intX;
	}

	public void setIntX(int intX) {
		_intX = intX;
	}

	private int _intY;

	private int getIntY() {
		return _intY;
	}

	private void setIntY(int intY) {
		_intY = intY;
	}

	private Camada _objCamada;

	private Camada getObjCamada() {
		return _objCamada;
	}

	private void setObjCamada(Camada objCamada) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			_objCamada = objCamada;
			_objCamada.getLstObjQuadro().add(this);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public Quadro(Camada objCamada) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setObjCamada(objCamada);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// DESTRUTOR
	// FIM DESTRUTOR

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
