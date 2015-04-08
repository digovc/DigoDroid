package com.digosofter.digodroid.item;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;

public class ItmMain extends Objeto {

  public void montarLayout() {

    try {

      this.setEventos();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void setEventos() {

    try {

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}