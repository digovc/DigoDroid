package com.digosofter.digodroid.item;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Util;
import com.digosofter.digodroid.erro.Erro;

public class ItmConsulta extends Objeto {

  private String _strCampo001;
  private String _strCampo001Nome;
  private String _strCampo001Valor;
  private String _strCampo002;
  private String _strCampo002Nome;
  private String _strCampo002Valor;
  private String _strCampo003;
  private String _strCampo003Nome;
  private String _strCampo003Valor;
  private String _strItemId;

  public boolean getBooContemString(String strFiltro) {

    boolean booContemString = false;
    try {
      if (this.getStrNome().contains(strFiltro)) {
        booContemString = true;
      }
      if (this.getStrCampo001Valor().contains(strFiltro)
          && this.getStrCampo001Valor().length() == strFiltro.length()) {
        booContemString = true;
      }
      if (this.getStrCampo002Valor().contains(strFiltro)) {
        booContemString = true;
      }
      if (this.getStrCampo003Valor().contains(strFiltro)) {
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
      _strCampo001 = this.getStrCampo001Nome() + ": " + this.getStrCampo001Valor();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo001;
  }

  public String getStrCampo001Nome() {

    return _strCampo001Nome;
  }

  public String getStrCampo001Valor() {

    try {
      if (_strCampo001Valor == null) {
        _strCampo001Valor = Util.STR_VAZIA;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo001Valor.replace("true", "Sim").replace("false", "Não");
  }

  public String getstrCampo002() {

    try {
      _strCampo002 = this.getStrCampo002Nome() + ": " + this.getStrCampo002Valor();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo002;
  }

  public String getStrCampo002Nome() {

    return _strCampo002Nome;
  }

  public String getStrCampo002Valor() {

    try {
      if (_strCampo002Valor == null) {
        _strCampo002Valor = Util.STR_VAZIA;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo002Valor.replace("true", "Sim").replace("false", "Não");
  }

  public String getstrCampo003() {

    try {
      _strCampo003 = this.getStrCampo003Nome() + ": " + this.getStrCampo003Valor();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo003;
  }

  public String getStrCampo003Nome() {

    return _strCampo003Nome;
  }

  public String getStrCampo003Valor() {

    try {
      if (_strCampo003Valor == null) {
        _strCampo003Valor = Util.STR_VAZIA;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strCampo003Valor.replace("true", "Sim").replace("false", "Não");
  }

  public String getStrItemId() {

    return _strItemId;
  }

  public void setStrCampo001Nome(String strCampo001Nome) {

    _strCampo001Nome = strCampo001Nome;
  }

  public void setStrCampo001Valor(String strCampo001Valor) {

    _strCampo001Valor = strCampo001Valor;
  }

  public void setStrCampo002Nome(String strCampo002Nome) {

    _strCampo002Nome = strCampo002Nome;
  }

  public void setStrCampo002Valor(String strCampo002Valor) {

    _strCampo002Valor = strCampo002Valor;
  }

  public void setStrCampo003Nome(String strCampo003Nome) {

    _strCampo003Nome = strCampo003Nome;
  }

  public void setStrCampo003Valor(String strCampo003Valor) {

    _strCampo003Valor = strCampo003Valor;
  }

  public void setStrItemId(String strItemId) {

    _strItemId = strItemId;
  }
}
