package com.digosofter.digodroid.service;

import android.app.IntentService;
import android.content.Intent;

import com.digosofter.digodroid.AppAndroid;

public abstract class ServiceMain extends IntentService
{
  private boolean _booParar;
  private Intent _itt;
  private String _strNome;

  public ServiceMain(String strNome)
  {
    super(strNome);

    this.setStrNome(strNome);
  }

  protected void finalizar()
  {
  }

  public boolean getBooParar()
  {
    return _booParar;
  }

  protected Intent getItt()
  {
    return _itt;
  }

  private String getStrNome()
  {
    return _strNome;
  }

  protected void inicializar()
  {
    this.inicializarApp();
  }

  private void inicializarApp()
  {
    if (AppAndroid.getI() == null)
    {
      return;
    }

    AppAndroid.getI().dispararEvtOnSrvStartedListener(this);
  }

  @Override
  protected void onHandleIntent(Intent itt)
  {
    this.setItt(itt);

    try
    {
      this.inicializar();
      this.servico();
    }
    catch (Exception ex)
    {
      this.processarErro(ex);
    }
    finally
    {
      this.finalizar();
    }
  }

  protected void processarErro(final Exception ex)
  {
    if (ex == null)
    {
      return;
    }

    ex.printStackTrace();

    if (AppAndroid.getI() == null)
    {
      return;
    }

    AppAndroid.getI().notificar(String.format("Erro no serviço %s. Reinicie a aplicação e se o problema persistir entre em contato com o administrador do sistema.", this.getStrNome()));
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

  private void setStrNome(String strNome)
  {
    _strNome = strNome;
  }
}