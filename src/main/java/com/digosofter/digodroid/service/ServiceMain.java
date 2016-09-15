package com.digosofter.digodroid.service;

import android.app.IntentService;
import android.content.Intent;

import com.digosofter.digodroid.AppAndroid;

public abstract class ServiceMain extends IntentService
{
  private boolean _booParar;
  private Intent _itt;

  public ServiceMain(String strNome)
  {
    super(AppAndroid.getI().getStrNomeExibicao() + " - " + strNome);
  }

  protected void finalizar()
  {
  }

  protected boolean getBooParar()
  {
    return _booParar;
  }

  protected Intent getItt()
  {
    return _itt;
  }

  protected void inicializar()
  {
  }

  @Override
  protected void onHandleIntent(Intent itt)
  {
    this.setItt(itt);

    try
    {
      this.inicializar();
      this.servico();
      this.finalizar();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  protected void servico()
  {
  }

  /**
   * Avisa este serviço para parar assim que possível.
   *
   * @param booParar Avisa este serviço para parar assim que possível.
   */
  public void setBooParar(boolean booParar)
  {
    _booParar = booParar;
  }

  private void setItt(Intent itt)
  {
    _itt = itt;
  }
}