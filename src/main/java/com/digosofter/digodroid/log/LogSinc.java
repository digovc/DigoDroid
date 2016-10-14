package com.digosofter.digodroid.log;

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


}