package com.digosofter.digodroid.game.cenario;

import java.util.ArrayList;
import java.util.List;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public abstract class Cenario extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private int _intQtdQuadrosHorizontal = 20;

	private int getIntQtdQuadrosHorizontal() {
		return _intQtdQuadrosHorizontal;
	}

	private void setIntQtdQuadrosHorizontal(int intQtdQuadrosHorizontal) {
		_intQtdQuadrosHorizontal = intQtdQuadrosHorizontal;
	}

	private int _intQtdQuadrosVertical = 15;

	private int getIntQtdQuadrosVertical() {
		return _intQtdQuadrosVertical;
	}

	private void setIntQtdQuadrosVertical(int intQtdQuadrosVertical) {
		_intQtdQuadrosVertical = intQtdQuadrosVertical;
	}

	private int _intTamHorizontal = 500;

	private int getIntTamHorizontal() {
		return _intTamHorizontal;
	}

	private void setIntTamHorizontal(int intTamHorizontal) {
		_intTamHorizontal = intTamHorizontal;
	}

	private int _intTamVertical = 375;

	private int getIntTamVertical() {
		return _intTamVertical;
	}

	private void setIntTamVertical(int intTamVertical) {
		_intTamVertical = intTamVertical;
	}

	private List<Camada> _lstObjCamada;

	public List<Camada> getLstObjCamada() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_lstObjCamada == null) {
				_lstObjCamada = new ArrayList<Camada>();
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _lstObjCamada;
	}

	private void setLstObjCamada(List<Camada> lstObjCamada) {
		_lstObjCamada = lstObjCamada;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// DESTRUTOR
	// FIM DESTRUTOR

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
