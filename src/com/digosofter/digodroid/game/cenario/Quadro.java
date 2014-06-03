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
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setObjCamada(objCamada);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
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
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      _objCamada = objCamada;
      _objCamada.getLstObjQuadro().add(this);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }
  }

  // DESTRUTOR
  // FIM DESTRUTOR

}
