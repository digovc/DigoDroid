package com.digosofter.digodroid.game;

import java.util.List;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.MensagemUsuario;
import com.digosofter.digodroid.erro.Erro;

public abstract class Game extends App {

	@Override
	public List<MensagemUsuario> getLstObjMensagemUsuario() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return null;
	}

}
