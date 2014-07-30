package com.digosofter.digodroid.game.cenario;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public class Quadro extends Objeto {

  private int _intAnimacaoPosicao;
  private int _intX;
  private int _intY;
  private Camada _objCamada;

  public Quadro(Camada objCamada) {

    try {
      this.setObjCamada(objCamada);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private int getIntAnimacaoPosicao() {

    return _intAnimacaoPosicao;
  }

  private int getIntX() {

    return _intX;
  }

  private int getIntY() {

    return _intY;
  }

  private Camada getObjCamada() {

    return _objCamada;
  }

  private void setIntAnimacaoPosicao(int intAnimacaoPosicao) {

    _intAnimacaoPosicao = intAnimacaoPosicao;
  }

  public void setIntX(int intX) {

    _intX = intX;
  }

  private void setIntY(int intY) {

    _intY = intY;
  }

  private void setObjCamada(Camada objCamada) {

    try {
      _objCamada = objCamada;
      _objCamada.getLstObjQuadro().add(this);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }
  // DESTRUTOR
  // FIM DESTRUTOR
}
