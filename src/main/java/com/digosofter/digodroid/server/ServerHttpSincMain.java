package com.digosofter.digodroid.server;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.server.message.MessageMain;
import com.digosofter.digodroid.server.message.MsgWelcome;
import com.digosofter.digodroid.service.SrvSincMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.log.Log;

public abstract class ServerHttpSincMain
{
  private int _intPing;
  private RequestQueue _objRequestQueue;
  private Response.ErrorListener _onPingErrorResponse;
  private Response.Listener<String> _onPingResponse;
  private SrvSincMain _srvSinc;

  public void enviar(final MessageMain msg)
  {
    if (Utils.getBooStrVazia(this.getUrlServer()))
    {
      return;
    }

    if (msg == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "Uma mensagem vazia não pode ser enviada para o servidor de sincronização.");
      return;
    }

    String url = this.getUrlServer().concat("/").concat(msg.getClass().getSimpleName().toLowerCase());

    this.getObjRequestQueue().add(new SincJsonRequest(msg, url));
  }

  private void enviarWelcome()
  {
    if (Utils.getBooStrVazia(this.getUrlServer()))
    {
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Enviando mensagem de boas vindas para o servidor (%s).", this.getUrlServer()));

    this.enviar(new MsgWelcome());
  }

  private int getIntPing()
  {
    return _intPing;
  }

  protected RequestQueue getObjRequestQueue()
  {
    if (_objRequestQueue != null)
    {
      return _objRequestQueue;
    }

    _objRequestQueue = Volley.newRequestQueue(this.getSrvSinc());

    return _objRequestQueue;
  }

  private Response.ErrorListener getOnPingErrorResponse()
  {
    if (_onPingErrorResponse != null)
    {
      return _onPingErrorResponse;
    }

    _onPingErrorResponse = new Response.ErrorListener()
    {
      @Override
      public void onErrorResponse(final VolleyError objVolleyError)
      {
        ServerHttpSincMain.this.setIntPing(0);
      }
    };

    return _onPingErrorResponse;
  }

  private Response.Listener<String> getOnPingResponse()
  {
    if (_onPingResponse != null)
    {
      return _onPingResponse;
    }

    _onPingResponse = new Response.Listener<String>()
    {
      @Override
      public void onResponse(final String strResposta)
      {
        if ("pong".equals(strResposta))
        {
          ServerHttpSincMain.this.setIntPing(1);
        }
        else
        {
          ServerHttpSincMain.this.setIntPing(0);
        }
      }
    };

    return _onPingResponse;
  }

  public SrvSincMain getSrvSinc()
  {
    return _srvSinc;
  }

  public abstract String getUrlServer();

  private void inicializar()
  {
    LogSinc.getI().addLog(Log.EnmTipo.INFO, "Inicializando o servidor de sincronização.");

    this.enviarWelcome();
  }

  public void iniciar()
  {
    this.inicializar();
  }

  public boolean pingServer(final String urlServer)
  {
    if (Utils.getBooStrVazia(urlServer))
    {
      return false;
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Tentando estabelecer comunicação com o servidor (%s).", urlServer));

    this.setIntPing(-1);

    StringRequest objStringRequest = new StringRequest(Request.Method.GET, urlServer.concat("/ping"), this.getOnPingResponse(), this.getOnPingErrorResponse());

    this.getObjRequestQueue().add(objStringRequest);

    while (this.getIntPing() < 0)
    {
      try
      {
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }

    boolean booResultado = (this.getIntPing() > 0) ? true : false;

    if (booResultado)
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Conexão estabelecida com sucesso no servidor (%s).", urlServer));
    }
    else
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Falha ao tentar estabelecer comunicação com o servidor (%s).", urlServer));
    }

    return booResultado;
  }

  private void setIntPing(int intPing)
  {
    _intPing = intPing;
  }

  public void setSrvSinc(SrvSincMain srvSinc)
  {
    _srvSinc = srvSinc;
  }
}