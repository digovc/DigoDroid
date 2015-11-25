package com.digosofter.digodroid.item;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;

public abstract class ItmMain extends Objeto {

  private boolean _booTelaConsulta;
  private View _viw;

  protected boolean getBooTelaConsulta() {

    return _booTelaConsulta;
  }

  protected abstract int getIntLayoutId();

  @SuppressLint("InflateParams")
  public View getViw() {

    try {

      if (_viw != null) {

        return _viw;
      }

      _viw = LayoutInflater.from(AppAndroid.getI().getCnt()).inflate(this.getIntLayoutId(), null);
    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _viw;
  }

  protected void limparLayout() {

    try {

      _viw = null;
    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  public void montarLayout() {

    try {

      this.limparLayout();
      this.setEventos();
    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  protected void setBooTelaConsulta(boolean booTelaConsulta) {

    _booTelaConsulta = booTelaConsulta;
  }

  protected void setEventos() {

    try {

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }
}