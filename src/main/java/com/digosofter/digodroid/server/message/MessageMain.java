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
    return _clsResposta;
  }

  public String getStrAparelhoId()
  {
    return _strAparelhoId;
  }

  protected void inicializar()
  {
    this.setClsResposta((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    this.setStrAparelhoId(Aparelho.getI().getStrDeviceId());
  }

  private final void iniciar()
  {
    this.inicializar();
  }

  protected void notificarErroServidor(final T rsp)
  {
    LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro de sincronização (%s) no servidor: %s", rsp.getClass().getSimpleName(), rsp.getStrCritica()));
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
      this.notificarErroServidor(rsp);
      return;
    }

    this.onResposta(rsp);
  }

  protected abstract void onResposta(final T rsp);

  private void setClsResposta(Class<T> clsResposta)
  {
    _clsResposta = clsResposta;
  }

  private void setStrAparelhoId(String strAparelhoId)
  {
    _strAparelhoId = strAparelhoId;
  }

  public String toJson()
  {
    return Json.getI().toJson(this);
  }
}