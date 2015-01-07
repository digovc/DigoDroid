package com.digosofter.digodroid.item;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;

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

  private String formatarValor(String strValor) {

    try {

      if (UtilsAndroid.getBooStrVazia(strValor)) {

        return UtilsAndroid.STR_VAZIA;
      }

      strValor = strValor.replace("true", "Sim");
      strValor = strValor.replace("false", "Não");
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return strValor;
  }

  public boolean getBooContemTermo(String strTermo) {

    boolean booResultado = false;

    try {

      if (this.getStrNome().contains(strTermo)) {

        booResultado = true;
      }

      if (this.getStrCampo1Valor().contains(strTermo) && this.getStrCampo1Valor().length() == strTermo.length()) {

        booResultado = true;
      }

      if (this.getStrCampo2Valor().contains(strTermo)) {

        booResultado = true;
      }

      if (this.getStrCampo3Valor().contains(strTermo)) {

        booResultado = true;
      }
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(132), ex);
    }
    finally {
    }

    return booResultado;
  }

  public String getStrCampo001() {

    try {

      if (!UtilsAndroid.getBooStrVazia(_strCampo1)) {

        return _strCampo1;
      }

      _strCampo1 = this.getStrCampo1Nome() + ": " + this.getStrCampo1Valor();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
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

      if (!UtilsAndroid.getBooStrVazia(_strCampo1Valor)) {

        return _strCampo1Valor;
      }

      _strCampo1Valor = this.formatarValor(_strCampo1Valor);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _strCampo1Valor;
  }

  public String getstrCampo2() {

    try {

      if (!UtilsAndroid.getBooStrVazia(_strCampo2)) {

        return _strCampo2;
      }

      _strCampo2 = this.getStrCampo2Nome() + ": " + this.getStrCampo2Valor();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
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

      if (!UtilsAndroid.getBooStrVazia(_strCampo2Valor)) {

        return _strCampo2Valor;
      }

      _strCampo2Valor = this.formatarValor(_strCampo2Valor);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _strCampo2Valor;
  }

  public String getstrCampo3() {

    try {

      if (!UtilsAndroid.getBooStrVazia(_strCampo3)) {

        return _strCampo3;
      }

      _strCampo3 = this.getStrCampo3Nome() + ": " + this.getStrCampo3Valor();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
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

      if (!UtilsAndroid.getBooStrVazia(_strCampo3Valor)) {

        return _strCampo3Valor;
      }

      _strCampo3Valor = this.formatarValor(_strCampo3Valor);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _strCampo3Valor;
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