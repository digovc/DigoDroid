package com.digosofter.digodroid.game.cenario;

import java.util.ArrayList;
import java.util.List;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public class Camada extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private int _intZ;

	private int getIntZ() {
		return _intZ;
	}

	public void setIntZ(int intZ) {
		_intZ = intZ;
	}

	private List<Quadro> _lstObjQuadro;

	public List<Quadro> getLstObjQuadro() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_lstObjQuadro==null) {
				_lstObjQuadro =new ArrayList<Quadro>();
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _lstObjQuadro;
	}

	private void setLstObjQuadro(List<Quadro> lstObjQuadro) {
		_lstObjQuadro = lstObjQuadro;
	}

	private Cenario _objCenario;

	private Cenario getObjCenario() {
		return _objCenario;
	}

	private void setObjCenario(Cenario objCenario) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			_objCenario = objCenario;
			_objCenario.getLstObjCamada().add(this);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public Camada(Cenario objCenario) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setObjCenario(objCenario);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// DESTRUTOR
	// FIM DESTRUTOR

	// M�TODOS
	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
