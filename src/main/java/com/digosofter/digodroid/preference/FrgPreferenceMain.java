package com.digosofter.digodroid.preference;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public abstract class FrgPreferenceMain extends PreferenceFragment
{
  protected void inicializar()
  {
    this.addPreferencesFromResource(this.getIntLayoutId());
  }

  protected abstract int getIntLayoutId();

  private void iniciar()
  {
    this.inicializar();
    this.montarLayout();
    this.setEventos();
  }

  protected void montarLayout()
  {
  }

  @Override
  public void onCreate(final Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    this.iniciar();
  }

  protected void setEventos()
  {
  }
}