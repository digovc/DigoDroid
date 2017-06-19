package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.DbeAndroidMain;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.database.dominio.DominioSincronizavelMain;
import com.digosofter.digodroid.log.LogErro;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.server.message.MsgCodigoReserva;
import com.digosofter.digodroid.server.message.MsgPesquisar;
import com.digosofter.digodroid.server.message.MsgSalvar;
import com.digosofter.digodroid.server.message.MsgTabelaBase;
import com.digosofter.digodroid.server.message.RespostaMain;
import com.digosofter.digodroid.server.message.RspCodigoReserva;
import com.digosofter.digodroid.server.message.RspPesquisar;
import com.digosofter.digodroid.server.message.RspSalvar;
import com.digosofter.digodroid.service.SrvSincMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Coluna;
import com.digosofter.digojava.database.Filtro;
import com.digosofter.digojava.json.Json;
import com.digosofter.digojava.log.Log;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public abstract class TblSincronizavelMain<T extends DominioSincronizavelMain> extends TblAndroidMain<T>
{
  private boolean _booEditavel;
  private boolean _booReceberDados;
  private ColunaAndroid _clnBooSincronizado;
  private ColunaAndroid _clnBooSincronizar;
  private ColunaAndroid _clnIntReservaCodigoId;
  private ColunaAndroid _clnStrAparelhoId;
  private ColunaAndroid _clnStrSincCritica;
  private List<Filtro> _lstFilSincronizacao;
  private List<TblSincronizavelMain> _lstTblDependencia;
  private MsgCodigoReserva _msgCodigoReserva;
  private MsgPesquisar _msgPesquisar;
  private MsgSalvar _msgSalvar;
  private String _sqlServerNome;
  private SrvSincMain _srvSinc;

  protected TblSincronizavelMain(final String strNome, final DbeAndroidMain dbeAndroid)
  {
    super(strNome, dbeAndroid);
  }

  private boolean getBooEditavel()
  {
    return _booEditavel;
  }

  private boolean getBooReceberDados()
  {
    return _booReceberDados;
  }

  public ColunaAndroid getClnBooSincronizado()
  {
    if (_clnBooSincronizado != null)
    {
      return _clnBooSincronizado;
    }

    _clnBooSincronizado = new ColunaAndroid("boo_sincronizado", this, Coluna.EnmTipo.BOOLEAN);

    return _clnBooSincronizado;
  }

  private ColunaAndroid getClnBooSincronizar()
  {
    if (_clnBooSincronizar != null)
    {
      return _clnBooSincronizar;
    }

    _clnBooSincronizar = new ColunaAndroid("boo_sincronizar", this, Coluna.EnmTipo.BOOLEAN);

    return _clnBooSincronizar;
  }

  ColunaAndroid getClnIntReservaCodigoId()
  {
    if (_clnIntReservaCodigoId != null)
    {
      return _clnIntReservaCodigoId;
    }

    _clnIntReservaCodigoId = new ColunaAndroid("int_reserva_codigo_id", this, Coluna.EnmTipo.BIGINT);

    return _clnIntReservaCodigoId;
  }

  public ColunaAndroid getClnStrAparelhoId()
  {
    if (_clnStrAparelhoId != null)
    {
      return _clnStrAparelhoId;
    }

    _clnStrAparelhoId = new ColunaAndroid("str_aparelho_id", this, Coluna.EnmTipo.TEXT);

    return _clnStrAparelhoId;
  }

  private ColunaAndroid getClnStrSincCritica()
  {
    if (_clnStrSincCritica != null)
    {
      return _clnStrSincCritica;
    }

    _clnStrSincCritica = new ColunaAndroid("str_sinc_critica", this, Coluna.EnmTipo.TEXT);

    return _clnStrSincCritica;
  }

  protected int getIntCodigoDisponivelQuantidade()
  {
    return TblReservaCodigo.getI().getIntCodigoDisponivelQuantidade(this);
  }

  protected abstract int getIntReservaCodigoQuantidade();

  protected abstract int getIntSincMesRetroativo();

  protected abstract int getIntSincRegistroLimite();

  private List<Filtro> getLstFilSincronizacao()
  {
    if (_lstFilSincronizacao != null)
    {
      return _lstFilSincronizacao;
    }

    _lstFilSincronizacao = new ArrayList<>();

    _lstFilSincronizacao.add(new Filtro(this.getClnBooSincronizado(), false));
    _lstFilSincronizacao.add(new Filtro(this.getClnBooSincronizar(), true));

    this.inicializarLstFilSincronizacao(_lstFilSincronizacao);

    return _lstFilSincronizacao;
  }

  private List<TblSincronizavelMain> getLstTblDependencia()
  {
    if (_lstTblDependencia != null)
    {
      return _lstTblDependencia;
    }

    _lstTblDependencia = new ArrayList<>();

    this.inicializarLstTblDependencia(_lstTblDependencia);

    return _lstTblDependencia;
  }

  private MsgCodigoReserva getMsgCodigoReserva()
  {
    return _msgCodigoReserva;
  }

  private MsgPesquisar getMsgPesquisar()
  {
    return _msgPesquisar;
  }

  private MsgSalvar getMsgSalvar()
  {
    return _msgSalvar;
  }

  public String getSqlServerNome()
  {
    if (_sqlServerNome != null)
    {
      return _sqlServerNome;
    }

    _sqlServerNome = this.getSqlNome();

    return _sqlServerNome;
  }

  private SrvSincMain getSrvSinc()
  {
    return _srvSinc;
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.setBooReceberDados(true);

    this.getClnBooSincronizado().setBooValorDefault(false);
    this.getClnBooSincronizar().setBooValorDefault(true);
  }

  @Override
  protected void inicializarLstCln(List<Coluna> lstCln)
  {
    super.inicializarLstCln(lstCln);

    lstCln.add(this.getClnBooSincronizado());
    lstCln.add(this.getClnBooSincronizar());
    lstCln.add(this.getClnIntReservaCodigoId());
    lstCln.add(this.getClnStrAparelhoId());
    lstCln.add(this.getClnStrSincCritica());
  }

  protected void inicializarLstFilSincronizacao(final List<Filtro> lstFilSincronizacao)
  {

  }

  protected void inicializarLstTblDependencia(final List<TblSincronizavelMain> lstTblDependencia)
  {

  }

  public <T extends RespostaMain> void onServidorErrroSinc(final MsgTabelaBase<T> msg, final RespostaMain rsp)
  {
    if (msg == null)
    {
      return;
    }

    if (msg.getClass().equals(MsgCodigoReserva.class))
    {
      this.onServidorErrroSincMsgCodigoReserva((MsgCodigoReserva) msg, (RspCodigoReserva) rsp);
      return;
    }

    if (msg.getClass().equals(MsgPesquisar.class))
    {
      this.onServidorErrroSincMsgPesquisar((MsgPesquisar) msg, (RspPesquisar) rsp);
      return;
    }

    if (msg.getClass().equals(MsgSalvar.class))
    {
      this.onServidorErrroSincMsgSalvar((MsgSalvar) msg, (RspSalvar) rsp);
      return;
    }
  }

  private void onServidorErrroSincMsgCodigoReserva(final MsgCodigoReserva msg, final RspCodigoReserva rsp)
  {
    this.setMsgCodigoReserva(null);
  }

  private void onServidorErrroSincMsgPesquisar(final MsgPesquisar msg, final RspPesquisar rsp)
  {
    this.setMsgPesquisar(null);

    TblSincronizacaoRecebimento.getI().salvarRecebimento(this, rsp);
  }

  private void onServidorErrroSincMsgSalvar(final MsgSalvar msg, final RspSalvar rsp)
  {
    this.setMsgSalvar(null);

    TblSincronizacaoEnvio.getI().salvarEnvioServidorErro(this, rsp);
  }

  protected void prepararRetornoSincronizacao(final T objDominio)
  {
  }

  protected void prepararSincronizacao(final T objDominio)
  {
    if (objDominio == null)
    {
      return;
    }

    objDominio.setStrAparelhoId(Aparelho.getI().getStrDeviceId());
    objDominio.setStrSincCritica(null);
  }

  public void processarPesquisa(final MsgPesquisar msgPesquisar, final RspPesquisar rspPesquisar)
  {
    if (rspPesquisar == null)
    {
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, "Analisando resposta de recebimento do servidor.");

    if (rspPesquisar.getIntRegistroQuantidade() < 1)
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, "A pesquisa não retornou nenhum registro.");
      return;
    }

    if (Utils.getBooStrVazia(rspPesquisar.getJsnLstObjDominio()))
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, "A pesquisa não retornou nenhum registro.");
      return;
    }

    JsonElement[] arrJsnElement = Json.getI().fromJson(rspPesquisar.getJsnLstObjDominio(), JsonElement[].class);

    if (arrJsnElement == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "Erro ao converter o resultado da pesquisa.");
      return;
    }

    if (arrJsnElement.length < 1)
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, "A pesquisa não retornou nenhum registro.");
      return;
    }

    if (arrJsnElement.length != rspPesquisar.getIntRegistroQuantidade())
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "A quantidade de registros recebidos difere da quantidade de registros enviados pela pesquisa.");
      return;
    }

    if (msgPesquisar.getIntPesquisaParte() == 1)
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("O servidor encontrou %s registro para tabela %s.", rspPesquisar.getIntRegistroQuantidadeTotal(), this.getStrNomeExibicao()));
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Salvamento de %s registros iniciado na tabela %s.", arrJsnElement.length, this.getStrNomeExibicao()));

    for (JsonElement jsnElement : arrJsnElement)
    {
      if (this.getSrvSinc().getBooParar())
      {
        return;
      }

      this.processarPesquisa(rspPesquisar, jsnElement);
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Salvo %s registros na tabela %s.", arrJsnElement.length, this.getStrNomeExibicao()));

    this.processarPesquisaFinalizar(msgPesquisar, rspPesquisar);
  }

  private void processarPesquisa(final RspPesquisar rspPesquisar, final JsonElement jsnObjDominio)
  {
    if (jsnObjDominio == null)
    {
      return;
    }

    T objDominio = Json.getI().fromJson(jsnObjDominio, this.getClsDominio());

    this.processarPesquisa(rspPesquisar, objDominio);
  }

  private void processarPesquisa(final RspPesquisar rspPesquisar, final T objDominio)
  {
    if (objDominio == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro ao converter o consulta_item do resultado da pesquisa para salvar na tabela %s.", this.getStrNomeExibicao()));
      return;
    }

    objDominio.setBooSincronizado(true);

    this.salvar(objDominio);

    rspPesquisar.addObjDominioSincronizado(objDominio);

    // LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Registro %s salvo com sucesso na tabela %s.", objDominio.getIntId(), this.getStrNomeExibicao()));
  }

  private void processarPesquisaFinalizar(final MsgPesquisar msgPesquisar, final RspPesquisar rspPesquisar)
  {
    TblSincronizacaoRecebimento.getI().salvarRecebimento(this, rspPesquisar);

    this.processarPesquisaFinalizarIncompleto(msgPesquisar, rspPesquisar);
  }

  private void processarPesquisaFinalizarIncompleto(final MsgPesquisar msgPesquisar, final RspPesquisar rspPesquisar)
  {
    if (rspPesquisar.getBooSincCompleto())
    {
      return;
    }

    int intResto = (rspPesquisar.getIntRegistroQuantidadeTotal() - msgPesquisar.getIntPesquisaParte() * msgPesquisar.getIntSincRegistroLimite());

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Restam %s registros a serem salvos na tabela %s.", intResto, this.getStrNomeExibicao()));

    this.getSrvSinc().setBooReiniciarLoop(true);

    this.setMsgPesquisar(rspPesquisar.getMsg());

    this.getMsgPesquisar().setIntPesquisaParte(this.getMsgPesquisar().getIntPesquisaParte() + 1);

    this.getSrvSinc().getSrvHttpSinc().enviar(this.getMsgPesquisar());
  }

  public void processarReservarCodigo(final RspCodigoReserva rsp)
  {
    if (rsp == null)
    {
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, "Analisando resposta de reserva de código do servidor.");

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("O servidor disponibilizou %s códigos a serem usados na tabela %s", rsp.getMsg().getIntQuantidadeDisponibilizado(), this.getStrNomeExibicao()));

    TblReservaCodigo.getI().reservarCodigo(rsp);
  }

  public void processarSalvar(final RspSalvar rspSalvar)
  {
    if (rspSalvar == null)
    {
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Analisando resposta de sincronização da tabela %s.", this.getStrNomeExibicao()));

    if (rspSalvar.getIntRegistroQuantidade() < 1)
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("A resposta de sincronização da tabela %s está vazia.", this.getStrNomeExibicao()));
      return;
    }

    if (Utils.getBooStrVazia(rspSalvar.getJsnLstObjDominio()))
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("A resposta de sincronização da tabela %s está vazia.", this.getStrNomeExibicao()));
      return;
    }

    JsonElement[] arrJsnElement = Json.getI().fromJson(rspSalvar.getJsnLstObjDominio(), JsonElement[].class);

    if (arrJsnElement == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro ao converter o resultado da resposta de sincronização da tabela %s.", this.getStrNomeExibicao()));
      return;
    }

    if (arrJsnElement.length < 1)
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("A resposta de sincronização da tabela %s não retornou nenhum registro.", this.getStrNomeExibicao()));
      return;
    }

    if (arrJsnElement.length != rspSalvar.getIntRegistroQuantidade())
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "A quantidade de registros recebidos difere da quantidade de registros enviados para salvamento.");
      return;
    }

    for (JsonElement jsnElement : arrJsnElement)
    {
      this.processarSalvar(rspSalvar, jsnElement);
    }
  }

  private void processarSalvar(final RspSalvar rspSalvar, final JsonElement jsnObjDominio)
  {
    if (jsnObjDominio == null)
    {
      return;
    }

    T objDominio = Json.getI().fromJson(jsnObjDominio, this.getClsDominio());

    this.processarSalvar(rspSalvar, objDominio);
  }

  private void processarSalvar(final RspSalvar rspSalvar, final T objDominio)
  {
    this.prepararRetornoSincronizacao(objDominio);

    if (objDominio == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro ao converter o item da consulta da tabela %s.", this.getStrNomeExibicao()));
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Analisando a resposta de sincronização do registro %s da tabela %s.", objDominio.getIntId(), this.getStrNomeExibicao()));

    if (Utils.getBooStrVazia(objDominio.getStrSincCritica()))
    {
      objDominio.setBooSincronizado(true);
      objDominio.setStrSincCritica(Coluna.STR_VALOR_NULO);
    }
    else
    {
      objDominio.setBooSincronizado(false);
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro ao sincronizar o registro %s da tabela %s. %s", objDominio.getIntId(), this.getStrNomeExibicao(), objDominio.getStrSincCritica()));
    }

    this.salvar(objDominio);

    if (objDominio.getBooSincronizado())
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Registro %s da tabela %s sincronizado com sucesso.", objDominio.getIntId(), this.getStrNomeExibicao()));
    }

    TblSincronizacaoEnvio.getI().salvarEnvio(this, rspSalvar, objDominio);
  }

  @Override
  public TblAndroidMain salvar()
  {
    if (this.getClnIntId().getIntValor() > 0)
    {
      return super.salvar();
    }

    TblReservaCodigo.getI().prepararProximoCodigoDisponivel(this);

    if (this.getClnIntId().getIntValor() < 1)
    {
      LogErro.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro ao tentar recuperar o código reservado para a tabela %s.", this.getStrNomeExibicao()));
      return this;
    }

    return super.salvar();
  }

  protected void setBooEditavel(final boolean booEditavel)
  {
    _booEditavel = booEditavel;
  }

  public void setBooReceberDados(boolean booReceberDados)
  {
    _booReceberDados = booReceberDados;
  }

  public void setMsgCodigoReserva(MsgCodigoReserva msgCodigoReserva)
  {
    _msgCodigoReserva = msgCodigoReserva;
  }

  public void setMsgPesquisar(MsgPesquisar msgPesquisar)
  {
    _msgPesquisar = msgPesquisar;
  }

  public void setMsgSalvar(MsgSalvar msgSalvar)
  {
    _msgSalvar = msgSalvar;
  }

  private void setSrvSinc(final SrvSincMain srvSinc)
  {
    _srvSinc = srvSinc;
  }

  public void sincronizar(final SrvSincMain srvSinc)
  {
    if (srvSinc == null)
    {
      return;
    }

    this.setSrvSinc(srvSinc);

    this.sincronizarReceber();
    this.sincronizarEnviar();
    this.sincronizarReservarCodigo();
  }

  private void sincronizarEnviar()
  {
    this.sincronizarEnviarDependencia();

    if (!this.sincronizarEnviarValidar())
    {
      return;
    }

    List<T> lstObjDominio = this.pesquisarDominio(this.getLstFilSincronizacao());

    if (lstObjDominio == null)
    {
      return;
    }

    if (lstObjDominio.isEmpty())
    {
      return;
    }

    for (T objDominio : lstObjDominio)
    {
      this.prepararSincronizacao(objDominio);
    }

    this.setMsgSalvar(new MsgSalvar());

    this.getMsgSalvar().setJsnLstObjDominio(Json.getI().toJson(lstObjDominio));
    this.getMsgSalvar().setTbl(this);

    this.getSrvSinc().getSrvHttpSinc().enviar(this.getMsgSalvar());
  }

  private void sincronizarEnviarDependencia()
  {
    for (TblSincronizavelMain tblDependencia : this.getLstTblDependencia())
    {
      tblDependencia.sincronizarEnviar();

      try
      {
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  private boolean sincronizarEnviarValidar()
  {
    if (!this.getBooEditavel())
    {
      return false;
    }

    if (this.getMsgSalvar() != null)
    {
      return false;
    }

    if (this.contar(this.getLstFilSincronizacao()) < 1)
    {
      return false;
    }

    return true;
  }

  private void sincronizarReceber()
  {
    if (!this.getBooReceberDados())
    {
      return;
    }

    if (this.getMsgPesquisar() != null)
    {
      return;
    }

    this.setMsgPesquisar(new MsgPesquisar());

    this.getMsgPesquisar().setIntMesRetroativo(this.getIntSincMesRetroativo());
    this.getMsgPesquisar().setDttUltimoRecebimento(TblSincronizacaoRecebimento.getI().getDttUltimoRecebimento(this));
    this.getMsgPesquisar().setIntPesquisaParte(1);
    this.getMsgPesquisar().setIntSincRegistroLimite(this.getIntSincRegistroLimite());
    this.getMsgPesquisar().setTbl(this);

    this.getSrvSinc().getSrvHttpSinc().enviar(this.getMsgPesquisar());
  }

  private void sincronizarReservarCodigo()
  {
    if (!this.getBooEditavel())
    {
      return;
    }

    if (this.getMsgCodigoReserva() != null)
    {
      return;
    }

    if (this.getIntCodigoDisponivelQuantidade() > this.getIntReservaCodigoQuantidade())
    {
      return;
    }

    this.setMsgCodigoReserva(new MsgCodigoReserva());

    this.getMsgCodigoReserva().setTbl(this);
    this.getMsgCodigoReserva().setIntQuantidadeDisponibilizado(this.getIntReservaCodigoQuantidade());

    this.getSrvSinc().getSrvHttpSinc().enviar(this.getMsgCodigoReserva());
  }
}