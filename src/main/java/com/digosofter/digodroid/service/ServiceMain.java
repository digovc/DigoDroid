package com.digosofter.digodroid.service;

import android.app.IntentService;
import android.content.Intent;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.App;

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

  /**
   * Responsável pela inicialização de propriedades antes de iniciar o serviço.
   */
  protected void inicializar()
  {
  }

  private void iniciar()
  {
    this.inicializar();
    this.setEventos();
  }

  @Override
  protected void onHandleIntent(Intent itt)
  {
    try
    {
      this.setItt(itt);

      if (!this.validarDados())
      {
        this.setBooParar(true);
        return;
      }

      this.iniciar();

      this.servico();
    }
    catch (Exception ex)
    {
      new ErroAndroid(App.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
      this.finalizar();
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

  protected void setEventos()
  {

  }

  private void setItt(Intent itt)
  {
    _itt = itt;
  }

  protected boolean validarDados()
  {

    return true;
  }
}