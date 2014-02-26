package com.digosofter.digodroid.game.cenario;

import java.util.ArrayList;
import java.util.List;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public class Camada extends Objeto {

	private int _intZ;

	private List<Quadro> _lstObjQuadro;

	private Cenario _objCenario;

	public Camada(Cenario objCenario) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setObjCenario(objCenario);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private int getIntZ() {
		return _intZ;
	}

	public List<Quadro> getLstObjQuadro() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_lstObjQuadro == null) {
				_lstObjQuadro = new ArrayList<Quadro>();
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _lstObjQuadro;
	}

	private Cenario getObjCenario() {
		return _objCenario;
	}

	public void setIntZ(int intZ) {
		_intZ = intZ;
	}

	private void setLstObjQuadro(List<Quadro> lstObjQuadro) {
		_lstObjQuadro = lstObjQuadro;
	}

	private void setObjCenario(Cenario objCenario) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			_objCenario = objCenario;
			_objCenario.getLstObjCamada().add(this);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// DESTRUTOR
	// FIM DESTRUTOR

}
