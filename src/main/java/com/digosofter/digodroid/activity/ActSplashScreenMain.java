package com.digosofter.digodroid.activity;

import android.content.Intent;
import android.os.Handler;

import com.digosofter.digodroid.AppAndroid;

public abstract class ActSplashScreenMain extends ActMain
{
  protected abstract AppAndroid getApp();

  @Override
  protected boolean getBooMostrarMenu()
  {
    return false;
  }

  protected abstract Class getClsActPrincipal();

  @Override
  protected void inicializar()
  {
    super.inicializar();

    new Handler().postDelayed(new Runnable()
    {
      @Override
      public void run()
      {
        ActSplashScreenMain.this.inicializarDelayed();
      }
    }, 350);
  }

  @Override
  protected void inicializarActionBar()
  {
    // super.inicializarActionBar();
  }

  private void inicializarApp()
  {
    this.getApp().iniciar(this);
  }

  private void inicializarDbe()
  {
    if (AppAndroid.getI() == null)
    {
      return;
    }
  }

  private void inicializarDelayed()
  {
    this.inicializarApp();

    this.inicializarDbe();

    if (this.getClsActPrincipal() == null)
    {
      return;
    }

    this.startActivity(new Intent(this, this.getClsActPrincipal()));

    this.finish();
  }
}