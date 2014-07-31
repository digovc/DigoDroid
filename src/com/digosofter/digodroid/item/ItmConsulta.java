package com.digosofter.digodroid.item;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Util;
import com.digosofter.digodroid.erro.Erro;

public class ItmConsulta extends Objeto {

  private String _strCampo1;
  private String _strCampo1Nome;
  private String _strCampo1Valor;
  private String _strCampo2;
  private String _strCampo2Nome;
  private String _strCampo2Valor;
  private String _strCampo3;
  private String _strCampo3Nome;
  private String _strCampo3Valor;
  private String _strItemId;

  public boolean getBooContemString(String strFiltro) {

    boolean booContemString = false;
    try {
      if (this.getStrNome().contains(strFiltro)) {
        booContemString = true;
      }
      if (this.getStrCampo1Valor().contains(strFiltro)
          && this.getStrCampo1Valor().length() == strFiltro.length()) {
        booContemString = true;
      }
      if (this.getStrCampo2Valor().contains(strFiltro)) {
        booContemString = true;
      }
      if (this.getStrCampo3Valor().contains(strFiltro)) {
        booContemString = true;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(132), ex);
    }
    finally {
    }
    return booContemString;
  }

  public String getStrCampo001() {

    try {
      _strCampo1 = this.getStrCampo1Nome() + ": " + this.getStrCampo1Valor();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo1;
  }

  public String getStrCampo1Nome() {

    return _strCampo1Nome;
  }

  public String getStrCampo1Valor() {

    try {
      if (_strCampo1Valor == null) {
        _strCampo1Valor = Util.STR_VAZIA;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo1Valor.replace("true", "Sim").replace("false", "Não");
  }

  public String getstrCampo2() {

    try {
      _strCampo2 = this.getStrCampo2Nome() + ": " + this.getStrCampo2Valor();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo2;
  }

  public String getStrCampo2Nome() {

    return _strCampo2Nome;
  }

  public String getStrCampo2Valor() {

    try {
      if (_strCampo2Valor == null) {
        _strCampo2Valor = Util.STR_VAZIA;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo2Valor.replace("true", "Sim").replace("false", "Não");
  }

  public String getstrCampo3() {

    try {
      _strCampo3 = this.getStrCampo3Nome() + ": " + this.getStrCampo3Valor();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo3;
  }

  public String getStrCampo3Nome() {

    return _strCampo3Nome;
  }

  public String getStrCampo3Valor() {

    try {
      if (_strCampo3Valor == null) {
        _strCampo3Valor = Util.STR_VAZIA;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo3Valor.replace("true", "Sim").replace("false", "Não");
  }

  public String getStrItemId() {

    return _strItemId;
  }

  public void setStrCampo1Nome(String strCampo1Nome) {

    _strCampo1Nome = strCampo1Nome;
  }

  public void setStrCampo1Valor(String strCampo1Valor) {

    _strCampo1Valor = strCampo1Valor;
  }

  public void setStrCampo2Nome(String strCampo2Nome) {

    _strCampo2Nome = strCampo2Nome;
  }

  public void setStrCampo2Valor(String strCampo2Valor) {

    _strCampo2Valor = strCampo2Valor;
  }

  public void setStrCampo3Nome(String strCampo3Nome) {

    _strCampo3Nome = strCampo3Nome;
  }

  public void setStrCampo3Valor(String strCampo3Valor) {

    _strCampo3Valor = strCampo3Valor;
  }

  public void setStrItemId(String strItemId) {

    _strItemId = strItemId;
  }
}
