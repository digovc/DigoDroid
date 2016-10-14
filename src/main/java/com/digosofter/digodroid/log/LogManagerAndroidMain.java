package com.digosofter.digodroid.log;

import android.content.Intent;

import com.digosofter.digodroid.activity.ActLog;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digojava.log.Log;
import com.digosofter.digojava.log.LogManagerMain;

import java.util.ArrayList;
import java.util.List;

public abstract class LogManagerAndroidMain extends LogManagerMain
{
  private static List<LogManagerAndroidMain> _lstLogManager;

  public static LogManagerAndroidMain getLogManager(final int intObjetoId)
  {
    if (intObjetoId < 1)
    {
      return null;
    }

    for (LogManagerAndroidMain logManager : LogManagerAndroidMain.getLstLogManager())
    {
      if (logManager == null)
      {
        continue;
      }

      if (logManager.getIntObjetoId() != intObjetoId)
      {
        continue;
      }

      return logManager;
    }

    return null;
  }

  private ActLog _actLog;

  protected LogManagerAndroidMain(final String strNome)
  {
    super(strNome);

    LogManagerAndroidMain.getLstLogManager().add(this);
  }

  public void abrirActDetalhe(final ActMain act)
  {
    if (act == null)
    {
      return;
    }

    Intent itt = new Intent(act, ActLog.class);

    itt.putExtra(ActLog.STR_EXTRA_INT_LOG_MANAGER_OBJETO_ID, this.getIntObjetoId());

    act.startActivity(itt);
  }

  private ActLog getActLog()
  {
    return _actLog;
  }

  private static List<LogManagerAndroidMain> getLstLogManager()
  {
    if (_lstLogManager != null)
    {
      return _lstLogManager;
    }

    _lstLogManager = new ArrayList<>();

    return _lstLogManager;
  }

  @Override
  protected void onLogAdicionado(final Log log)
  {
    // super.onLogAdicionado(log);

    if (log == null)
    {
      return;
    }

    this.onLogAdicionadoActLog(log);

    switch (log.getEnmTipo())
    {
      case DEBUG:
        android.util.Log.d(this.getStrNome(), log.getStrLogFormatado());
        return;

      case ERRO:
        android.util.Log.e(this.getStrNome(), log.getStrLogFormatado());
        return;

      default:
        android.util.Log.i(this.getStrNome(), log.getStrLogFormatado());
        return;
    }
  }

  private void onLogAdicionadoActLog(final Log log)
  {
    if (this.getActLog() == null)
    {
      return;
    }

    this.getActLog().atualizarLog(this.getStrLogResumido());
  }

  public void setActLog(ActLog actLog)
  {
    _actLog = actLog;
  }
}