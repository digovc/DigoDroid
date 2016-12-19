package com.digosofter.digodroid.log;

import android.content.Context;
import android.content.Intent;

import com.digosofter.digodroid.activity.ActErro;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digojava.log.Log;

import java.util.ArrayList;
import java.util.List;

public class LogErro extends LogManagerAndroidMain
{
  private static LogErro _i;

  public static LogErro getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new LogErro();

    return _i;
  }

  private List<ActErro> _lstActErro;

  private LogErro()
  {
    super("Log de erros");
  }

  private void abrirActErro(final Context cnt, final Exception ex)
  {
    Intent itt = new Intent(cnt, ActErro.class);

    itt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    itt.putExtra(ActErro.STR_EXTRA_INT_STR_ERRO_DESCRICAO, this.getStrDescricao(ex.getStackTrace()));
    itt.putExtra(ActErro.STR_EXTRA_INT_STR_ERRO_TITULO, ex.getMessage());

    cnt.startActivity(itt);
  }

  public void addActErro(final ActErro actErro)
  {
    if (actErro == null)
    {
      return;
    }

    if (this.getLstActErro().contains(actErro))
    {
      return;
    }

    this.getLstActErro().add(actErro);
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

  public List<ActErro> getLstActErro()
  {
    if (_lstActErro != null)
    {
      return _lstActErro;
    }

    _lstActErro = new ArrayList<>();

    return _lstActErro;
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

  public void ignorarTodos()
  {
    while (!this.getLstActErro().isEmpty())
    {
      this.ignorarTodos(this.getLstActErro().get(0));
    }
  }

  private void ignorarTodos(final ActErro actErro)
  {
    if (actErro == null)
    {
      return;
    }

    this.removerActErro(actErro);

    actErro.setResult(0, new Intent().putExtra(ActMain.STR_EXTRA_OUT_BOO_FECHAR, true));

    actErro.finish();
  }

  public void removerActErro(final ActErro actErro)
  {
    if (!this.getLstActErro().contains(actErro))
    {
      return;
    }

    this.getLstActErro().remove(actErro);
  }
}