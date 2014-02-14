package com.digosofter.digodroid.http.server;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.erro.Erro;

public class HttpServer extends NanoHTTPD {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS
	// FIM ATRIBUTOS

	// CONSTRUTORES

	public HttpServer() {

		super(8080);

		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES
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

	@Override
	public Response serve(IHTTPSession session) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return super.serve(session);
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
