package com.digosofter.digodroid.game.cenario;

import java.util.ArrayList;
import java.util.List;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public abstract class Cenario extends Objeto {

	private int _intQtdQuadrosHorizontal = 20;

	private int _intQtdQuadrosVertical = 15;

	private int _intTamHorizontal = 500;

	private int _intTamVertical = 375;

	private List<Camada> _lstObjCamada;

	private int getIntQtdQuadrosHorizontal() {
		return _intQtdQuadrosHorizontal;
	}

	private int getIntQtdQuadrosVertical() {
		return _intQtdQuadrosVertical;
	}

	private int getIntTamHorizontal() {
		return _intTamHorizontal;
	}

	private int getIntTamVertical() {
		return _intTamVertical;
	}

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

	private void setIntQtdQuadrosHorizontal(int intQtdQuadrosHorizontal) {
		_intQtdQuadrosHorizontal = intQtdQuadrosHorizontal;
	}

	private void setIntQtdQuadrosVertical(int intQtdQuadrosVertical) {
		_intQtdQuadrosVertical = intQtdQuadrosVertical;
	}

	private void setIntTamHorizontal(int intTamHorizontal) {
		_intTamHorizontal = intTamHorizontal;
	}

	private void setIntTamVertical(int intTamVertical) {
		_intTamVertical = intTamVertical;
	}

	private void setLstObjCamada(List<Camada> lstObjCamada) {
		_lstObjCamada = lstObjCamada;
	}

	// DESTRUTOR
	// FIM DESTRUTOR

}
