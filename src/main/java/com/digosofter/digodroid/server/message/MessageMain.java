package com.digosofter.digodroid.server.message;

import android.os.AsyncTask;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.json.Json;
import com.digosofter.digojava.log.Log;

import java.lang.reflect.ParameterizedType;

public abstract class MessageMain<T extends RespostaMain> implements Response.Listener, Response.ErrorListener
{
  private transient Class<T> _clsResposta;
  private String _strAparelhoId;

  protected MessageMain()
  {
    this.iniciar();
  }

  private Class<T> getClsResposta()
  {
    if (_clsResposta != null)
    {
      return _clsResposta;
    }

    if (this.getClass().getGenericSuperclass() == null)
    {
      return null;
    }

    if (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments() == null)
    {
      return null;
    }

    if (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments().length < 1)
    {
      return null;
    }

    _clsResposta = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    return _clsResposta;
  }

  public String getStrAparelhoId()
  {
    return _strAparelhoId;
  }

  protected void inicializar()
  {
    this.setStrAparelhoId(Aparelho.getI().getStrDeviceId());
  }

  private final void iniciar()
  {
    this.inicializar();
  }

  @Override
  public void onErrorResponse(final VolleyError objVolleyError)
  {
    AsyncTask.execute(new Runnable()
    {
      @Override
      public void run()
      {
        MessageMain.this.onErrorResponseLocal(objVolleyError);
      }
    });
  }

  private void onErrorResponseLocal(final VolleyError objVolleyError)
  {
    if (objVolleyError == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro de sincronização (%s). Motivo desconhecido.", this.getClsResposta().getSimpleName()));
      return;
    }

    if (Utils.getBooStrVazia(objVolleyError.getMessage()))
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro de sincronização (%s). Motivo desconhecido.", this.getClsResposta().getSimpleName()));
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.ERRO, "Erro de sincronização. ".concat(objVolleyError.getMessage()));
  }

  @Override
  public final void onResponse(final Object objResponse)
  {
    AsyncTask.execute(new Runnable()
    {
      @Override
      public void run()
      {
        MessageMain.this.onResponseLocal(objResponse);
      }
    });
  }

  private void onResponseLocal(final Object objResponse)
  {
    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Resposta de sincronização (%s) recebida.", this.getClsResposta().getSimpleName()));

    if (objResponse == null)
    {
      return;
    }

    T rsp = Json.getI().fromJson(objResponse.toString(), this.getClsResposta());

    if (rsp == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Resposta de sincronização (%s) vazia.", this.getClsResposta().getSimpleName()));
      return;
    }

    rsp.setMsg(this);

    if (!Utils.getBooStrVazia(rsp.getStrCritica()))
    {
      this.onResponseServidorError(rsp);
      return;
    }

    this.onResposta(rsp);
  }

  protected void onResponseServidorError(final T rsp)
  {
    LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro de sincronização (%s) no servidor: %s", rsp.getClass().getSimpleName(), rsp.getStrCritica()));
  }

  protected abstract void onResposta(final T rsp);

  private void setStrAparelhoId(String strAparelhoId)
  {
    _strAparelhoId = strAparelhoId;
  }

  public String toJson()
  {
    return Json.getI().toJson(this);
  }
}