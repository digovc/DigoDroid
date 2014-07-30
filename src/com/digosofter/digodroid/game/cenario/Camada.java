package com.digosofter.digodroid.game.cenario;

import java.util.ArrayList;
import java.util.List;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public class Camada extends Objeto {

  private int _intZ;
  private List<Quadro> _lstObjQuadro;
  private Cenario _objCenario;

  public Camada(Cenario objCenario) {

    try {
      this.setObjCenario(objCenario);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private int getIntZ() {

    return _intZ;
  }

  public List<Quadro> getLstObjQuadro() {

    try {
      if (_lstObjQuadro == null) {
        _lstObjQuadro = new ArrayList<Quadro>();
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _lstObjQuadro;
  }

  private Cenario getObjCenario() {

    return _objCenario;
  }

  public void setIntZ(int intZ) {

    _intZ = intZ;
  }

  private void setLstObjQuadro(List<Quadro> lstObjQuadro) {

    _lstObjQuadro = lstObjQuadro;
  }

  private void setObjCenario(Cenario objCenario) {

    try {
      _objCenario = objCenario;
      _objCenario.getLstObjCamada().add(this);
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
