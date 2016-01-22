package com.digosofter.digodroid.preference;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.digosofter.digodroid.erro.ErroAndroid;

public abstract class FrgPreferenceMain extends PreferenceFragment {

  protected void inicializar() {

  }

  private void iniciar() {

    try {

      this.inicializar();
      this.montarLayout();
      this.setEventos();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void montarLayout() {

  }

  @Override
  public void onCreate(final Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    try {

      this.iniciar();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void setEventos() {

  }
}