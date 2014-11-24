package com.digosofter.digodroid.erro;

import java.io.Serializable;

import android.content.Intent;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActErro;
import com.digosofter.digojava.erro.Erro;

public class AndroidErro extends Erro implements Serializable {

  private static final long serialVersionUID = 1L;
  private boolean _booMostrarUsuario = true;

  public AndroidErro(String strMsg, Exception ex) {

    super(strMsg, ex);

    Intent objIntent;

    try {

      if (!this.getBooMostrarUsuario()) {

        return;
      }

      objIntent = new Intent(AppAndroid.getI().getCnt(), ActErro.class);
      objIntent.putExtra("Erro", this);

      AppAndroid.getI().getActMain().startActivity(objIntent);
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
