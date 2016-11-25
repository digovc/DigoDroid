package com.digosofter.digodroid.service;

import android.os.SystemClock;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.DbeAndroidMain;
import com.digosofter.digodroid.database.tabela.TblSincronizavelMain;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.server.ServerHttpSincMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.log.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class SrvSincMain<T extends ServerHttpSincMain> extends ServiceMain
{
  private static final int INT_LOOP_DELAY = (1000 * 60 * 5);

  private static SrvSincMain _i;

  public static SrvSincMain getI()
  {
    return _i;
  }

  private List<TblSincronizavelMain> _lstTbl;

  public SrvSincMain()
  {
    super("Serviço de sincronização");

    this.setI(this);
  }

  private void atualizarI(final SrvSincMain srvSinc)
  {
    if (AppAndroid.getI() == null)
    {
      return;
    }

    if (srvSinc != null)
    {
      AppAndroid.getI().dispararEvtOnSrvSincCreateListener(this);
      return;
    }

    AppAndroid.getI().dispararEvtOnSrvSincDestroyListener(this);
  }

  private void esperarIntervaloLoop()
  {
    int t = 0;

    while (true)
    {
      if (t >= INT_LOOP_DELAY)
      {
        return;
      }

      if (this.getBooParar())
      {
        return;
      }

      SystemClock.sleep(100);

      t += (100);
    }
  }

  @Override
  protected void finalizar()
  {
    super.finalizar();

    this.setI(null);
  }

  protected abstract DbeAndroidMain getDbe();

  private List<TblSincronizavelMain> getLstTbl()
  {
    if (_lstTbl != null)
    {
      return _lstTbl;
    }

    _lstTbl = new ArrayList<>();

    this.inicializarLstTbl(_lstTbl);

    return _lstTbl;
  }

  public abstract T getSrvHttpSinc();

  @Override
  protected void inicializar()
  {
    super.inicializar();

    if (!this.validarInicializacao())
    {
      this.setBooParar(true);
      return;
    }

    this.inicializarServerHttpSinc();
  }

  protected abstract void inicializarLstTbl(final List<TblSincronizavelMain> lstTbl);

  private void inicializarServerHttpSinc()
  {
    this.getSrvHttpSinc().setSrvSinc(this);

    this.getSrvHttpSinc().iniciar();
  }

  private void loop()
  {
    this.sincronizar();
  }

  protected void notificarErroServerUrl()
  {
    if (AppAndroid.getI() == null)
    {
      return;
    }

    AppAndroid.getI().notificar("O endereço do servidor não foi configurado.");
  }

  @Override
  protected void processarErro(final Exception ex)
  {
    super.processarErro(ex);

    if (ex == null)
    {
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro no serviço de sincronização:\n%s", ex.getMessage()));
  }

  @Override
  protected void servico()
  {
    super.servico();

    while (!this.getBooParar())
    {
      this.loop();

      this.esperarIntervaloLoop();
    }
  }

  private void setI(SrvSincMain i)
  {
    if (_i == i)
    {
      return;
    }

    _i = i;

    this.atualizarI(i);
  }

  private void sincronizar()
  {
    for (TblSincronizavelMain tbl : this.getLstTbl())
    {
      if (this.getBooParar())
      {
        return;
      }

      this.sincronizar(tbl);
    }
  }

  private void sincronizar(final TblSincronizavelMain tbl)
  {
    if (tbl == null)
    {
      return;
    }

    tbl.sincronizar(this);
  }

  private boolean validarInicializacao()
  {
    if (this.getDbe() == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "O banco de dados de sincronização não foi indicado.");
      return false;
    }

    if (this.getSrvHttpSinc() == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "O servidor de sincronização não foi indicado.");
      return false;
    }

    if (Utils.getBooStrVazia(this.getSrvHttpSinc().getUrlServer()))
    {
      this.notificarErroServerUrl();
      return false;
    }

    return true;
  }
}