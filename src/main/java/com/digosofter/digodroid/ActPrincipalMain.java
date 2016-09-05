package com.digosofter.digodroid;

import com.digosofter.digodroid.activity.ActMain;

public abstract class ActPrincipalMain extends ActMain
{
  protected abstract AppAndroid getAppAndroid();

  @Override
  protected void inicializar()
  {
    this.inicializarApp();

    super.inicializar();

    this.getActionBar().setDisplayShowTitleEnabled(false);
    this.getActionBar().setDisplayShowHomeEnabled(false);
  }

  private void inicializarApp()
  {
    if (this.getAppAndroid() == null)
    {
      return;
    }

    this.getAppAndroid().setActPrincipal(this);
  }

}