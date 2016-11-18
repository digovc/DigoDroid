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
    int i = (INT_LOOP_DELAY / 100);
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

      SystemClock.sleep(i * 100);
      t += (i * 100);
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

    if (this.getDbe() == null)
    {
      this.setBooParar(true);
      return;
    }

    if (this.getSrvHttpSinc() == null)
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
    if (Utils.getBooStrVazia(this.getSrvHttpSinc().getUrlServer()))
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "A url do servidor de sincronização não está configurada.");
      this.setBooParar(true);
      return;
    }

    this.sincronizar();
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
    if (AppAndroid.getI() == null)
    {
      return;
    }

    if (AppAndroid.getI().getDbe() == null)
    {
      return;
    }

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
}