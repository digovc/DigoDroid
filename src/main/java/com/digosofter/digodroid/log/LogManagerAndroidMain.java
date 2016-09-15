package com.digosofter.digodroid.log;

import com.digosofter.digojava.log.Log;
import com.digosofter.digojava.log.LogManagerMain;

public abstract class LogManagerAndroidMain extends LogManagerMain
{
  protected LogManagerAndroidMain(final String strNome)
  {
    super(strNome);
  }

  @Override
  protected void onLogAdicionado(final Log log)
  {
    // super.onLogAdicionado(log);

    if (log == null)
    {
      return;
    }

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

}