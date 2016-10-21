package com.digosofter.digodroid.log;

import android.content.Context;
import android.content.Intent;

import com.digosofter.digodroid.activity.ActErro;
import com.digosofter.digojava.log.Log;

public class LogErroAndroid extends LogManagerAndroidMain
{
  private static LogErroAndroid _i;

  public static LogErroAndroid getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new LogErroAndroid();

    return _i;
  }

  private LogErroAndroid()
  {
    super("Log de erros");
  }

  private void abrirActErro(final Context cnt, final Exception ex)
  {
    Intent itt = new Intent(cnt, ActErro.class);

    itt.putExtra(ActErro.STR_EXTRA_INT_STR_ERRO_TITULO, ex.getMessage());
    itt.putExtra(ActErro.STR_EXTRA_INT_STR_ERRO_DESCRICAO, this.getStrDescricao(ex.getStackTrace()));

    cnt.startActivity(itt);
  }

  public void addLog(final Context cnt, final Exception ex)
  {
    if (ex == null)
    {
      return;
    }

    super.addLog(Log.EnmTipo.ERRO, ex.getMessage());

    this.abrirActErro(cnt, ex);
  }

  private String getStrDescricao(final StackTraceElement[] arrObjStackTracelElement)
  {
    if (arrObjStackTracelElement == null)
    {
      return null;
    }

    if (arrObjStackTracelElement.length < 1)
    {
      return null;
    }

    StringBuilder stbResultado = new StringBuilder();

    for (StackTraceElement objStackTraceElement : arrObjStackTracelElement)
    {
      stbResultado.append(objStackTraceElement.toString());
      stbResultado.append("\n");
    }

    return stbResultado.toString();
  }
}