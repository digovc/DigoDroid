package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.DbeAndroidMain;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.dominio.DominioSincronizavelMain;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.server.message.MsgPesquisar;
import com.digosofter.digodroid.server.message.MsgSalvar;
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

public abstract class TblSincronizavelMain<T extends DominioSincronizavelMain> extends TabelaAndroid<T>
{
  private ColunaAndroid _clnBooSincronizado;
  private ColunaAndroid _clnBooSincronizar;
  private ColunaAndroid _clnStrAparelhoId;
  private ColunaAndroid _clnStrSincCritica;
  private MsgSalvar _msgSalvar;
  private MsgPesquisar _msgSincronizar;
  private String _sqlServerNome;
  private SrvSincMain _srvSinc;

  protected TblSincronizavelMain(final String strNome, final DbeAndroidMain dbeAndroid)
  {
    super(strNome, dbeAndroid);
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

  private MsgSalvar getMsgSalvar()
  {
    return _msgSalvar;
  }

  private MsgPesquisar getMsgSincronizar()
  {
    return _msgSincronizar;
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

    this.getClnBooSincronizado().setBooValorDefault(false);
    this.getClnBooSincronizar().setBooValorDefault(true);
  }

  @Override
  protected void inicializarLstCln(List<Coluna> lstCln)
  {
    super.inicializarLstCln(lstCln);

    lstCln.add(this.getClnBooSincronizado());
    lstCln.add(this.getClnBooSincronizar());
    lstCln.add(this.getClnStrAparelhoId());
    lstCln.add(this.getClnStrSincCritica());
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

  public void processarPesquisa(final RspPesquisar rspPesquisar)
  {
    if (rspPesquisar == null)
    {
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.ERRO, "Analisando resposta de recebimento do servidor.");

    if (rspPesquisar.getIntRegistroQuantidade() < 1)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "A pesquisa não retornou nenhum registro.");
      return;
    }

    if (Utils.getBooStrVazia(rspPesquisar.getJsnLstObjDominio()))
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "A pesquisa não retornou nenhum registro.");
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
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "A pesquisa não retornou nenhum registro.");
      return;
    }

    if (arrJsnElement.length != rspPesquisar.getIntRegistroQuantidade())
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "A quantidade de registros recebidos difere da quantidade de registros enviados pela pesquisa.");
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Salvamento de %s registros iniciado na tabela %s.", arrJsnElement.length, this.getStrNomeExibicao()));

    for (JsonElement jsnElement : arrJsnElement)
    {
      this.processarPesquisa(rspPesquisar, jsnElement);
    }

    this.processarPesquisaFinalizar(rspPesquisar);
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

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Registro %s salvo com sucesso na tabela %s.", objDominio.getIntId(), this.getStrNomeExibicao()));
  }

  private void processarPesquisaFinalizar(final RspPesquisar rspPesquisar)
  {
    TblSincronizacao.getI().atualizarRecebimento(this, rspPesquisar);

    this.setMsgSincronizar(null);
  }

  public void processarSalvar(final RspSalvar rspSalvar)
  {
    if (rspSalvar == null)
    {
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Analisando resposta de sincronização da tabela %s.", this.getStrNomeExibicao()));

    if (rspSalvar.getIntRegistroQuantidade() < 1)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("A resposta de sincronização da tabela %s está vazia.", this.getStrNomeExibicao()));
      return;
    }

    if (Utils.getBooStrVazia(rspSalvar.getJsnLstObjDominio()))
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("A resposta de sincronização da tabela %s está vazia.", this.getStrNomeExibicao()));
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
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("A resposta de sincronização da tabela %s não retornou nenhum registro.", this.getStrNomeExibicao()));
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

    this.processarSalvarFinalizar(rspSalvar);
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
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro ao converter o consulta_item do resultado do envio da tabela %s.", this.getStrNomeExibicao()));
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Analisando a resposta de sincronização do registro %s da tabela %s.", objDominio.getIntId(), this.getStrNomeExibicao()));

    if (Utils.getBooStrVazia(objDominio.getStrSincCritica()))
    {
      objDominio.setBooSincronizado(true);
      objDominio.setStrSincCritica(Coluna.STR_VALOR_NULO);
    }
    else
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro ao sincronizar o registro %s da tabela %s. %s", objDominio.getIntId(), this.getStrNomeExibicao(), objDominio.getStrSincCritica()));
    }

    this.salvar(objDominio);

    if (objDominio.getBooSincronizado())
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Registro %s da tabela %s sincronizado com sucesso.", objDominio.getIntId(), this.getStrNomeExibicao()));
    }
  }

  private void processarSalvarFinalizar(final RspSalvar rspSalvar)
  {
    // TODO: Persistir no banco de dados essa sincronização.

    this.setMsgSalvar(null);
  }

  private void setMsgSalvar(MsgSalvar msgSalvar)
  {
    _msgSalvar = msgSalvar;
  }

  private void setMsgSincronizar(MsgPesquisar msgSincronizar)
  {
    _msgSincronizar = msgSincronizar;
  }

  private void setSrvSinc(final SrvSincMain srvSinc)
  {
    if (_srvSinc == srvSinc)
    {
      return;
    }

    _srvSinc = srvSinc;
  }

  public void sincronizar(final SrvSincMain srvSinc)
  {
    this.setSrvSinc(srvSinc);

    if (this.getSrvSinc() == null)
    {
      return;
    }

    if (!this.getBooSincronizada())
    {
      return;
    }

    // TODO: Validar o status da conexão com o servidor.
    this.sincronizarReceber();
    this.sincronizarEnviar();
  }

  private void sincronizarEnviar()
  {
    if (this.getMsgSalvar() != null)
    {
      return;
    }

    List<Filtro> lstFil = new ArrayList<>();

    lstFil.add(new Filtro(this.getClnBooSincronizado(), false));
    lstFil.add(new Filtro(this.getClnBooSincronizar(), true));

    List<T> lstObjDominio = this.pesquisarDominio(lstFil);

    if (lstObjDominio == null)
    {
      return;
    }

    if (lstObjDominio.size() < 1)
    {
      return;
    }

    for (T objDominio : lstObjDominio)
    {
      this.prepararSincronizacao(objDominio);
    }

    this.setMsgSalvar(new MsgSalvar());

    this.getMsgSalvar().setTbl(this);
    this.getMsgSalvar().setJsnLstObjDominio(Json.getI().toJson(lstObjDominio));

    this.getSrvSinc().getSrvHttpSinc().enviar(this.getMsgSalvar());
  }

  private void sincronizarReceber()
  {
    if (this.getMsgSincronizar() != null)
    {
      return;
    }

    this.setMsgSincronizar(new MsgPesquisar());

    this.getMsgSincronizar().setTbl(this);
    this.getMsgSincronizar().setDttUltimoRecebimento(TblSincronizacao.getI().getDttUltimoRecebimento(this));

    this.getSrvSinc().getSrvHttpSinc().enviar(this.getMsgSincronizar());
  }
}