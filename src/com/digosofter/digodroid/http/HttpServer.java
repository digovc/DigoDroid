package com.digosofter.digodroid.http;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.erro.Erro;

public class HttpServer extends NanoHTTPD {

  public HttpServer() {

    super(8080);

    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES
      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  // DESTRUTOR
  // FIM DESTRUTOR

  @Override
  public Response serve(IHTTPSession session) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES
      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return super.serve(session);
  }

}
