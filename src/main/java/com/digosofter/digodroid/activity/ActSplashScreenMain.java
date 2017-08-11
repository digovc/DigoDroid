package com.digosofter.digodroid.activity;

import android.content.Intent;

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

    this.inicializarApp();

    this.inicializarDbe();

    this.inicializarActPrincipal();
  }

  private void inicializarActPrincipal()
  {
    this.inicializarActPrincipalDelay();

    //    new Handler().postDelayed(new Runnable()
    //    {
    //      @Override
    //      public void run()
    //      {
    //        ActSplashScreenMain.this.inicializarActPrincipalDelay();
    //      }
    //    }, 1500);
  }

  private void inicializarActPrincipalDelay()
  {
    if (this.getClsActPrincipal() == null)
    {
      return;
    }

    this.startActivity(new Intent(this, this.getClsActPrincipal()));

    this.finish();
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
}