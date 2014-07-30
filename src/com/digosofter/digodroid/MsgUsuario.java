package com.digosofter.digodroid;

import com.digosofter.digodroid.erro.Erro;

public class MsgUsuario extends Objeto {

  public enum EnmLingua {
    INGLES,
    PORTUGUES
  }

  private EnmLingua _enmLingua = EnmLingua.PORTUGUES;

  private int _intId;

  private String _strTexto;

  public MsgUsuario(String strTexto, int intId) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setIntId(intId);
      this.setStrTexto(strTexto);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(107), ex);

    } finally {
    }
  }

  public MsgUsuario(String strTexto, int intId, EnmLingua enmLingua) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setIntId(intId);
      this.setStrTexto(strTexto);
      this.setEnmLingua(enmLingua);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(107), ex);

    } finally {
    }
  }

  public EnmLingua getEnmLingua() {
    return _enmLingua;
  }

  public int getIntId() {
    return _intId;
  }

  public String getStrTexto() {
    return _strTexto;
  }

  private void setEnmLingua(EnmLingua enmLingua) {
    _enmLingua = enmLingua;
  }

  private void setIntId(int intId) {
    _intId = intId;
  }

  private void setStrTexto(String strTexto) {
    _strTexto = strTexto;
  }

}
