package com.digosofter.digodroid.item;

import com.digosofter.digodroid.erro.ErroAndroid;

import android.view.View;

public class ItmDetalheGrupo extends ItmMain {

  private View _viw;

  public View getViw() {

    try {

      if (_viw != null) {

        return _viw;
      }

      // _viw = new View();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _viw;
  }

}
