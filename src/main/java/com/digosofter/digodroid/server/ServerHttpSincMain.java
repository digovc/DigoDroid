package com.digosofter.digodroid.server;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.log.LogErro;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.server.message.MessageMain;
import com.digosofter.digodroid.server.message.MsgLongPolling;
import com.digosofter.digodroid.server.message.MsgWelcome;
import com.digosofter.digodroid.server.message.RespostaMain;
import com.digosofter.digodroid.service.SrvSincMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.log.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class ServerHttpSincMain
{
  protected final String STR_ERRO_URL_SERVER_VAZIO = "O endereço do servidor não foi configurado.";

  private int _intMsgQuantidadeMaxima;
  private List<OnProgressoChangedListener> _lstEvtOnProgressoChangedListener;
  private ArrayList<MessageMain> _lstMsgPendente;
  private ArrayList<String> _lstUrlServer;
  private RequestQueue _objRequestQueue;
  private SrvSincMain _srvSinc;
  private String _urlServerAtual;

  public void addEvtOnProgressoChangedListener(OnProgressoChangedListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnProgressoChangedListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnProgressoChangedListener().add(evt);
  }

  public void aguardarSolicitacaoPendente()
  {

    try
    {
      Thread.sleep(1000);

      while (!this.getLstMsgPendente().isEmpty())
      {
        Thread.sleep(150);
      }
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  private void dispararEvtOnProgressoChangedListener()
  {
    if (this.getLstEvtOnProgressoChangedListener().isEmpty())
    {
      return;
    }

    float fltProgresso = 0;

    if (this.getLstMsgPendente().size() > 0)
    {
      fltProgresso = (((float) this.getIntMsgQuantidadeMaxima() - this.getLstMsgPendente().size()) / (float) this.getIntMsgQuantidadeMaxima());
    }

    for (OnProgressoChangedListener evt : this.getLstEvtOnProgressoChangedListener())
    {
      if (evt == null)
      {
        continue;
      }

      evt.onProgressoChanged(this, fltProgresso);
    }
  }

  public void enviar(final MessageMain msg)
  {
    if (Utils.getBooStrVazia(this.getUrlServerAtual()))
    {
      return;
    }

    if (msg == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "Uma mensagem vazia não pode ser enviada para o servidor de sincronização.");
      return;
    }

    msg.setSrvHttpSinc(this);

    this.getLstMsgPendente().add(msg);

    this.setIntMsgQuantidadeMaxima(this.getIntMsgQuantidadeMaxima() + 1);

    this.dispararEvtOnProgressoChangedListener();

    String url = this.getUrlServerAtual().concat("/").concat(msg.getClass().getSimpleName().toLowerCase());

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Enviando uma solicitação do tipo \"%s\" para o servidor.", msg.getClass().getSimpleName()));

    this.getObjRequestQueue().add(new SincJsonRequest(msg, url));
  }

  public boolean enviarDiverso(final ActMain act, final MessageMain msg)
  {
    if (msg == null)
    {
      return false;
    }

    if (!Aparelho.getI().getBooConectado())
    {
      LogErro.getI().addLog("O aparelho não está conectado.");
      return false;
    }

    if (this.getUrlServerAtual() == null)
    {
      LogErro.getI().addLog("O endereço do servidor não foi indicado ou não foi possível se conectar a ele.");
      return false;
    }

    msg.setSrvHttpSinc(this);

    String url = this.getUrlServerAtual().concat("/").concat(msg.getClass().getSimpleName().toLowerCase());

    Volley.newRequestQueue(act).add(new SincJsonRequest(msg, url));

    return true;
  }

  public void enviarLongPolling()
  {
    this.enviar(new MsgLongPolling());
  }

  private void enviarWelcome()
  {
    if (Utils.getBooStrVazia(this.getUrlServerAtual()))
    {
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Enviando mensagem de boas vindas para o servidor (%s).", this.getUrlServerAtual()));

    this.enviar(new MsgWelcome());
  }

  private int getIntMsgQuantidadeMaxima()
  {
    return _intMsgQuantidadeMaxima;
  }

  private List<OnProgressoChangedListener> getLstEvtOnProgressoChangedListener()
  {
    if (_lstEvtOnProgressoChangedListener != null)
    {
      return _lstEvtOnProgressoChangedListener;
    }

    _lstEvtOnProgressoChangedListener = new ArrayList<>();

    return _lstEvtOnProgressoChangedListener;
  }

  private ArrayList<MessageMain> getLstMsgPendente()
  {
    if (_lstMsgPendente != null)
    {
      return _lstMsgPendente;
    }

    _lstMsgPendente = new ArrayList<MessageMain>();

    return _lstMsgPendente;
  }

  protected ArrayList<String> getLstUrlServer()
  {
    if (_lstUrlServer != null)
    {
      return _lstUrlServer;
    }

    _lstUrlServer = new ArrayList<>();

    this.inicializarLstUrlServer(_lstUrlServer);

    return _lstUrlServer;
  }

  private RequestQueue getObjRequestQueue()
  {
    if (_objRequestQueue != null)
    {
      return _objRequestQueue;
    }

    _objRequestQueue = Volley.newRequestQueue(AppAndroid.getI().getActPrincipal());

    return _objRequestQueue;
  }

  public SrvSincMain getSrvSinc()
  {
    return _srvSinc;
  }

  private String getUrlServerAtual()
  {
    if (_urlServerAtual != null)
    {
      return _urlServerAtual;
    }

    if (this.getLstUrlServer().isEmpty())
    {
      return null;
    }

    for (String urlServer : this.getLstUrlServer())
    {
      if (!this.validarUrlServer(urlServer))
      {
        continue;
      }

      return _urlServerAtual = urlServer;
    }

    return _urlServerAtual;
  }

  private void inicializar()
  {
    LogSinc.getI().addLog(Log.EnmTipo.INFO, "Inicializando o servidor de sincronização.");

    this.enviarWelcome();
  }

  protected abstract void inicializarLstUrlServer(final ArrayList<String> lstUrlServer);

  public void iniciar()
  {
    this.inicializar();
  }

  protected void notificarUrlServidorVazio()
  {
    LogSinc.getI().addLog(Log.EnmTipo.ERRO, STR_ERRO_URL_SERVER_VAZIO);
  }

  public void removerEvtOnProgressoChangedListener(OnProgressoChangedListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnProgressoChangedListener().remove(evt);
  }

  public <T extends RespostaMain> void removerMsgPendente(final MessageMain msg)
  {
    this.getLstMsgPendente().remove(msg);

    this.dispararEvtOnProgressoChangedListener();
  }
  
  private void setIntMsgQuantidadeMaxima(int intMsgQuantidadeMaxima)
  {
    _intMsgQuantidadeMaxima = intMsgQuantidadeMaxima;
  }

  public void setSrvSinc(SrvSincMain srvSinc)
  {
    _srvSinc = srvSinc;
  }

  public boolean validarInicializacao()
  {
    if (this.getLstUrlServer().isEmpty())
    {
      this.notificarUrlServidorVazio();
      return false;
    }

    if (Utils.getBooStrVazia(this.getUrlServerAtual()))
    {
      LogErro.getI().addLog(this.getSrvSinc(), new Exception("Não foi possível se comunicar com o servidor. Ele pode estar desligado ou o endereço utilizado está incorreto."));
      return false;
    }

    return true;
  }

  private boolean validarUrlServer(final String urlServer)
  {
    if (Utils.getBooStrVazia(urlServer))
    {
      return false;
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Validando o servidor \"%s.\".", urlServer));

    String strPong = Utils.downloadString(urlServer + "/ping");

    boolean booResultado = "pong".equals(strPong);

    if (booResultado)
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Utilizando o link \"%s.\" para se comunicar com o servidor.", urlServer));
    }
    else
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Servidor \"%s.\" não responde.", urlServer));
    }

    return booResultado;
  }
}