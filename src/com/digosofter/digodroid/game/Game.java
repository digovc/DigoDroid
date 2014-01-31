package com.digosofter.digodroid.game;

import java.util.List;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.MensagemUsuario;
import com.digosofter.digodroid.erro.Erro;

public abstract class Game extends App {

	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS
	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// MÉTODOS

	@Override
	public List<MensagemUsuario> getLstObjMensagemUsuario() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return null;
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
