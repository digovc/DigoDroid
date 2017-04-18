package com.digosofter.digodroid.log;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digojava.log.Log;

public class LogSinc extends LogManagerAndroidMain
{
  private static LogSinc _i;

  public static LogSinc getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new LogSinc();

    return _i;
  }

  private LogSinc()
  {
    super("Log de sincronização");
  }

  @Override
  public void addLog(final Log log)
  {
    super.addLog(log);

    this.addLogErro(log);
  }

  private void addLogErro(final Log logErro)
  {
    if (logErro == null)
    {
      return;
    }

    if (!Log.EnmTipo.ERRO.equals(logErro.getEnmTipo()))
    {
      return;
    }

    LogErro.getI().addLog(AppAndroid.getI().getActPrincipal(), new Exception(logErro.getStrLog()));
  }
}