package com.digosofter.digodroid.sinc;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.service.SrvSincMain;
import com.digosofter.digodroid.sinc.message.MessageMain;
import com.digosofter.digodroid.sinc.message.MsgWelcome;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.log.Log;

public class ServerHttpSinc
{
  private static ServerHttpSinc _i;

  public static ServerHttpSinc getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new ServerHttpSinc();

    return _i;
  }

  private RequestQueue _objRequestQueue;
  private SrvSincMain _srvSincMain;
  private String _urlServer;

  private ServerHttpSinc()
  {
  }

  public void enviar(final MessageMain msg)
  {
    if (Utils.getBooStrVazia(this.getSrvSincronizacao().getUrlServer())) // TODO: Validar o status do servidor apenas uma vez.
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "A url do servidor de sincronização não está configurada.");
      return;
    }

    if (msg == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "Uma mensagem vazia não pode ser enviada para o servidor de sincronização.");
      return;
    }

    String url = this.getSrvSincronizacao().getUrlServer().concat("/").concat(msg.getClass().getSimpleName().toLowerCase());

    this.getObjRequestQueue().add(new SincJsonRequest(msg, url));
  }

  private void enviarWelcome()
  {
    LogSinc.getI().addLog(Log.EnmTipo.INFO, "Enviando mensagem de boas vindas para o servidor.");
    this.enviar(new MsgWelcome());
  }

  private RequestQueue getObjRequestQueue()
  {
    if (_objRequestQueue != null)
    {
      return _objRequestQueue;
    }

    _objRequestQueue = Volley.newRequestQueue(this.getSrvSincronizacao());

    return _objRequestQueue;
  }

  private SrvSincMain getSrvSincronizacao()
  {
    return _srvSincMain;
  }

  private String getUrlServer()
  {
    return _urlServer;
  }

  private void inicializar()
  {
    LogSinc.getI().addLog(Log.EnmTipo.INFO, "Inicializando o servidor de sincronização.");
    this.enviarWelcome();
  }

  public void iniciar()
  {
    this.inicializar();
  }

  public void setSrvSincronizacao(SrvSincMain srvSincMain)
  {
    _srvSincMain = srvSincMain;
  }

  private void setUrlServer(String urlServer)
  {
    _urlServer = urlServer;
  }
}