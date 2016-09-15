package com.digosofter.digodroid.service;

import android.os.SystemClock;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.tabela.TblSincronizavelMain;
import com.digosofter.digodroid.sinc.ServerHttpSinc;
import com.digosofter.digojava.database.Tabela;

public abstract class SrvSincMain extends ServiceMain
{
  private static final int INT_LOOP_DELAY = 5000;
  private String _urlServer;

  public SrvSincMain()
  {
    super("Serviço de sincronização");
  }

  public abstract String getUrlServer();

  @Override
  protected void inicializar()
  {
    super.inicializar();

    ServerHttpSinc.getI().setSrvSincronizacao(this);

    ServerHttpSinc.getI().iniciar();
  }

  private void loop()
  {
    this.sincronizar();
  }

  @Override
  protected void servico()
  {
    super.servico();

    while (!this.getBooParar())
    {
      this.loop();

      SystemClock.sleep(INT_LOOP_DELAY);
    }
  }

  private void sincronizar(final Tabela tbl)
  {
    if (tbl == null)
    {
      return;
    }

    if (!(tbl instanceof TblSincronizavelMain))
    {
      return;
    }

    ((TblSincronizavelMain) tbl).sincronizar();
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

    for (Tabela tbl : AppAndroid.getI().getDbe().getLstTbl())
    {
      this.sincronizar(tbl);
    }
  }
}