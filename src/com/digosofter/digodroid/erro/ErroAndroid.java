package com.digosofter.digodroid.erro;

import java.io.Serializable;

import android.content.Intent;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActErro;
import com.digosofter.digojava.erro.Erro;

public class ErroAndroid extends Erro implements Serializable {

  private static final long serialVersionUID = 1L;
  private boolean _booMostrarUsuario = true;

  public ErroAndroid(String strMsg, Exception ex) {

    super(strMsg, ex);

    Intent itt;

    try {

      if (!this.getBooMostrarUsuario()) {

        return;
      }

      itt = new Intent(AppAndroid.getI().getCnt(), ActErro.class);
      itt.putExtra(ActErro.STR_EXTRA_IN_OBJ_ERRO, this);

      AppAndroid.getI().getActMain().startActivity(itt);
    }
    catch (Exception e) {
    }
    finally {
    }
  }

  private boolean getBooMostrarUsuario() {

    return _booMostrarUsuario;
  }

  public void setBooMostrarUsuario(boolean booMostrarUsuario) {

    _booMostrarUsuario = booMostrarUsuario;
  }
}