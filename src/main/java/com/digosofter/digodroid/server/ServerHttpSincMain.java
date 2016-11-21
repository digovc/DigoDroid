package com.digosofter.digodroid.server;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.server.message.MessageMain;
import com.digosofter.digodroid.server.message.MsgWelcome;
import com.digosofter.digodroid.service.SrvSincMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.log.Log;

public abstract class ServerHttpSincMain
{
  private RequestQueue _objRequestQueue;
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
    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Enviando mensagem de boas vindas para o servidor (%s).", this.getUrlServer()));

    this.enviar(new MsgWelcome());
  }

  private RequestQueue getObjRequestQueue()
  {
    if (_objRequestQueue != null)
    {
      return _objRequestQueue;
    }

    _objRequestQueue = Volley.newRequestQueue(this.getSrvSinc());

    return _objRequestQueue;
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

  public void setSrvSinc(SrvSincMain srvSinc)
  {
    _srvSinc = srvSinc;
  }
}