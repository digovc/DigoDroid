package com.digosofter.digodroid.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.DbeAndroidMain;
import com.digosofter.digodroid.database.tabela.TblSincronizavelMain;
import com.digosofter.digodroid.log.LogErro;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.server.ServerHttpSincMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.log.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class SrvSincMain<T extends ServerHttpSincMain> extends ServiceMain
{
  private static final int INT_LOOP_DELAY = (1000 * 60 * 5);
  private static final int INT_NOTIFICACAO_ID = 1010;

  private static SrvSincMain _i;

  public static SrvSincMain getI()
  {
    return _i;
  }

  private List<TblSincronizavelMain> _lstTbl;
  private NotificationCompat.Builder _objNotificationBuilder;

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
    }
    else
    {
      AppAndroid.getI().dispararEvtOnSrvSincDestroyListener(this);
    }
  }

  private void esperarIntervaloLoop()
  {
    int t = 0;

    while (true)
    {
      if (this.getBooParar())
      {
        return;
      }

      if (t >= INT_LOOP_DELAY)
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

    this.finalizarNotificar();
  }

  private void finalizarNotificar()
  {
    AppAndroid.getI().notificar("Serviço de sincronização finalizado.");

    ((NotificationManager) this.getSystemService(NOTIFICATION_SERVICE)).cancel(INT_NOTIFICACAO_ID);
  }

  protected abstract DbeAndroidMain getDbe();

  protected abstract int getIntNotificacaoIconId();

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

  private NotificationCompat.Builder getObjNotificationBuilder()
  {
    if (_objNotificationBuilder != null)
    {
      return _objNotificationBuilder;
    }

    PendingIntent objPendingIntent = PendingIntent.getActivity(this, 0, (new Intent(this, AppAndroid.getI().getActPrincipal().getClass())), PendingIntent.FLAG_UPDATE_CURRENT);

    _objNotificationBuilder = new NotificationCompat.Builder(this);

    _objNotificationBuilder.setContentIntent(objPendingIntent);
    _objNotificationBuilder.setContentIntent(objPendingIntent);
    _objNotificationBuilder.setContentText("Sincronização ligada.");
    _objNotificationBuilder.setContentTitle("Ideal");
    _objNotificationBuilder.setOngoing(true);
    _objNotificationBuilder.setSmallIcon(this.getIntNotificacaoIconId());

    return _objNotificationBuilder;
  }

  public abstract T getSrvHttpSinc();

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.inicializarNotificar();

    this.inicializarServerHttpSinc();

    if (!this.validarInicializacao())
    {
      this.setBooParar(true);
    }
  }

  protected abstract void inicializarLstTbl(final List<TblSincronizavelMain> lstTbl);

  private void inicializarNotificar()
  {
    AppAndroid.getI().notificar("Serviço de sincronização inicializado.");

    ((NotificationManager) this.getSystemService(NOTIFICATION_SERVICE)).notify(INT_NOTIFICACAO_ID, this.getObjNotificationBuilder().build());
  }

  private void inicializarServerHttpSinc()
  {
    this.getSrvHttpSinc().setSrvSinc(this);

    this.getSrvHttpSinc().iniciar();
  }

  private void loop()
  {
    this.sincronizar();
  }

  protected void notificarUrlServidorVazio()
  {
    LogSinc.getI().addLog(Log.EnmTipo.INFO, "O endereço do servidor de sincronização não foi indicado.");
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

  protected void sincronizar()
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

    if (!Aparelho.getI().getBooConectado())
    {
      LogErro.getI().addLog(this, new Exception("O aparelho não está conectado."));
      return false;
    }

    if (Utils.getBooStrVazia(this.getSrvHttpSinc().getUrlServer()))
    {
      this.notificarUrlServidorVazio();
      return false;
    }

    if (!this.getSrvHttpSinc().pingServer(this.getSrvHttpSinc().getUrlServer()))
    {
      LogErro.getI().addLog(this, new Exception(String.format("Não foi possível se conectar ao servidor (%s).", this.getSrvHttpSinc().getUrlServer())));
      return false;
    }

    return true;
  }
}