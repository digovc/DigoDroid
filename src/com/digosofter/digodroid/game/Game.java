package com.digosofter.digodroid.game;

import java.util.List;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.MsgUsuario;
import com.digosofter.digodroid.erro.Erro;

public abstract class Game extends App {

  @Override
  public List<MsgUsuario> getLstMsgUsuario() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES
      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return null;
  }

}
