package com.digosofter.digodroid.server.message;

import android.os.AsyncTask;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.server.ServerHttpSincMain;
import com.digosofter.digodroid.service.SrvSincMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.json.Json;
import com.digosofter.digojava.log.Log;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class MessageMain<T extends RespostaMain> implements Response.Listener, Response.ErrorListener
{
  private transient Class<T> _clsResposta;
  private int _intTimeOut;
  private List<OnMsgRespostaListener> _lstEvtOnMsgRespostaListener;
  private transient T _rsp;
  private transient ServerHttpSincMain _srvHttpSinc;
  private String _strAparelhoId;

  protected MessageMain()
  {
    this.iniciar();
  }

  public void addEvtOnMsgRespostaListener(OnMsgRespostaListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnMsgRespostaListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnMsgRespostaListener().add(evt);
  }

  private void dispararEvtOnMsgRespostaListener(final boolean booErro)
  {
    if (this.getLstEvtOnMsgRespostaListener().isEmpty())
    {
      return;
    }

    for (OnMsgRespostaListener evt : this.getLstEvtOnMsgRespostaListener())
    {
      if (evt == null)
      {
        continue;
      }

      if (booErro)
      {
        evt.onRespostaErro(this);
      }
      else
      {
        evt.onRespostaSucesso(this);
      }
    }
  }

  private void finalizarSincronizacao()
  {
    if (SrvSincMain.getI() == null)
    {
      return;
    }

    SrvSincMain.getI().setBooParar(true);
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

  public int getIntTimeOut()
  {
    if (_intTimeOut != 0)
    {
      return _intTimeOut;
    }

    _intTimeOut = (15 * 1000);

    return _intTimeOut;
  }

  private List<OnMsgRespostaListener> getLstEvtOnMsgRespostaListener()
  {
    if (_lstEvtOnMsgRespostaListener != null)
    {
      return _lstEvtOnMsgRespostaListener;
    }

    _lstEvtOnMsgRespostaListener = new ArrayList<>();

    return _lstEvtOnMsgRespostaListener;
  }

  protected T getRsp()
  {
    return _rsp;
  }

  private ServerHttpSincMain getSrvHttpSinc()
  {
    return _srvHttpSinc;
  }

  public String getStrAparelhoId()
  {
    return _strAparelhoId;
  }

  protected void inicializar()
  {
    this._intTimeOut = this.getIntTimeOut();

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
        try
        {
          MessageMain.this.onErrorResponseLocal(objVolleyError);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        finally
        {
          MessageMain.this.getSrvHttpSinc().removerMsgPendente(MessageMain.this);

          MessageMain.this.dispararEvtOnMsgRespostaListener(true);

          MessageMain.this.finalizarSincronizacao();
        }
      }
    });
  }

  private void onErrorResponseLocal(final VolleyError objVolleyError)
  {
    if ((objVolleyError instanceof NetworkError) || (objVolleyError instanceof NoConnectionError))
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro de sincronização (%s). Erro de conexão.", this.getClsResposta().getSimpleName()));
      return;
    }

    if (objVolleyError instanceof TimeoutError)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro de sincronização (%s). O tempo limite de espera foi atingido.", this.getClsResposta().getSimpleName()));
      return;
    }

    if ((objVolleyError == null) || (Utils.getBooStrVazia(objVolleyError.getMessage())))
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro de sincronização (%s). Motivo desconhecido (%s).", this.getClsResposta().getSimpleName(), objVolleyError.getClass().getSimpleName()));
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.ERRO, "Erro de sincronização. ".concat(objVolleyError.getMessage()));
  }

  @Override
  public void onResponse(final Object objResponse)
  {
    AsyncTask.execute(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          MessageMain.this.onResponseLocal(objResponse);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        finally
        {
          MessageMain.this.getSrvHttpSinc().removerMsgPendente(MessageMain.this);

          MessageMain.this.dispararEvtOnMsgRespostaListener(false);
        }
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

    this.setRsp(rsp);

    this.onResposta(rsp);
  }

  protected void onResponseServidorError(final T rsp)
  {
    LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro de sincronização (%s) no servidor: %s", rsp.getClass().getSimpleName(), rsp.getStrCritica()));
  }

  protected abstract void onResposta(final T rsp);

  public void removerEvtOnMsgRespostaListener(OnMsgRespostaListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnMsgRespostaListener().remove(evt);
  }

  private void setRsp(T rsp)
  {
    _rsp = rsp;
  }

  public void setSrvHttpSinc(ServerHttpSincMain srvHttpSinc)
  {
    _srvHttpSinc = srvHttpSinc;
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