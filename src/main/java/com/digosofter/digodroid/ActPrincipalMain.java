package com.digosofter.digodroid;

import com.digosofter.digodroid.activity.ActMain;

public abstract class ActPrincipalMain extends ActMain
{
  @Override
  protected void abrirMenu()
  {
    if (!this.getBooMostrarMenu())
    {
      return;
    }
    
    super.abrirMenu();
  }

  protected abstract AppAndroid getAppAndroid();

  @Override
  protected void inicializar()
  {
    this.inicializarApp();

    super.inicializar();

    this.getActionBar().setDisplayShowTitleEnabled(false);
    this.getActionBar().setDisplayShowHomeEnabled(false);

    this.setTitle(null);
  }

  private void inicializarApp()
  {
    this.getAppAndroid().setActPrincipal(this);
  }

  @Override
  public boolean onOptionsItemSelected(final android.view.MenuItem mni)
  {
    switch (mni.getItemId())
    {
      case android.R.id.home:
        this.abrirMenu();
        return true;
    }

    if (super.onOptionsItemSelected(mni))
    {
      return true;
    }

    return false;
  }

}