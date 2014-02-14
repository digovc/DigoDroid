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

		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
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

	@Override
	public Response serve(IHTTPSession session) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return super.serve(session);
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
