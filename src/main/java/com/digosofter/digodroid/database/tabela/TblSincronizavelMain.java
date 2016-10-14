package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.DataBaseAndroid;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.dominio.DominioSincronizavelMain;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.server.message.MsgPesquisar;
import com.digosofter.digodroid.server.message.MsgSalvar;
import com.digosofter.digodroid.server.message.RspPesquisar;
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
  private ColunaAndroid _clnIntServerId;
  private ColunaAndroid _clnStrAparelhoId;
  private MsgSalvar _msgSalvar;
  private MsgPesquisar _msgSincronizar;
  private String _sqlServerNome;
  private SrvSincMain _srvSinc;

  protected TblSincronizavelMain(final String strNome, final DataBaseAndroid dbeAndroid)
  {
    super(strNome, dbeAndroid);
  }

  private ColunaAndroid getClnBooSincronizado()
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

  private ColunaAndroid getClnIntServerId()
  {
    if (_clnIntServerId != null)
    {
      return _clnIntServerId;
    }

    _clnIntServerId = new ColunaAndroid("int_server_id", this, Coluna.EnmTipo.BIGINT);

    return _clnIntServerId;
  }

  private ColunaAndroid getClnStrAparelhoId()
  {
    if (_clnStrAparelhoId != null)
    {
      return _clnStrAparelhoId;
    }

    _clnStrAparelhoId = new ColunaAndroid("str_aparelho_id", this, Coluna.EnmTipo.TEXT);

    return _clnStrAparelhoId;
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
    this.getClnStrAparelhoId().setStrValorDefault(Aparelho.getI().getStrDeviceId());
  }

  @Override
  protected int inicializarLstCln(int intOrdem)
  {
    intOrdem = super.inicializarLstCln(intOrdem);

    this.getClnBooSincronizado().setIntOrdem(++intOrdem);
    this.getClnBooSincronizar().setIntOrdem(++intOrdem);
    this.getClnIntServerId().setIntOrdem(++intOrdem);
    this.getClnStrAparelhoId().setIntOrdem(++intOrdem);

    return intOrdem;
  }

  public void processar(final RspPesquisar rspPesquisar)
  {
    if (rspPesquisar == null)
    {
      return;
    }

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
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "A quantidade de registro recebidos difere da quantidade de registros enviados pela pesquisa.");
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Salvamento de %s registros iniciado na tabela %s.", arrJsnElement.length, this.getStrNomeExibicao()));

    for (JsonElement jsnElement : arrJsnElement)
    {
      this.processar(rspPesquisar, jsnElement);
    }

    this.sincronizarFinalizar(rspPesquisar);
  }

  private void processar(final RspPesquisar rspPesquisar, final JsonElement jsnElement)
  {
    if (jsnElement == null)
    {
      return;
    }

    T objDominio = Json.getI().fromJson(jsnElement, this.getClsDominio());

    this.processar(objDominio, rspPesquisar);
  }

  private void processar(final T objDominio, final RspPesquisar rspPesquisar)
  {
    if (objDominio == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro ao converter o item do resultado da pesquisa para salvar na tabela %s.", this.getStrNomeExibicao()));
      return;
    }

    objDominio.setBooSincronizado(true);
    objDominio.setIntServerId(objDominio.getIntId());

    this.salvar(objDominio);

    rspPesquisar.addObjDominioSincronizado(objDominio);

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Registro %s salvo com sucesso na tabela %s.", objDominio.getIntId(), this.getStrNomeExibicao()));
  }

  private void processar(final RspPesquisar rspPesquisar, final T objDominio)
  {
    if (objDominio == null)
    {
      return;
    }

    this.salvar(objDominio);
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

    if (this.getMsgSincronizar() != null)
    {
      return;
    }

    // TODO: Validar o status da conexão com o servidor.
    this.sincronizarReceber();
    this.sincronizarEnviar();
  }

  private void sincronizarEnviar()
  {
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

    this.setMsgSalvar(new MsgSalvar());

    this.getMsgSalvar().setTbl(this);
    this.getMsgSalvar().setJsnLstObjDominio(Json.getI().toJson(lstObjDominio));

    this.getSrvSinc().getSrvHttpSinc().enviar(this.getMsgSalvar());
  }

  private void sincronizarFinalizar(final RspPesquisar rspPesquisar)
  {
    TblSincronizacao.getI().atualizarRecebimento(this, rspPesquisar);
    this.setMsgSincronizar(null);
  }

  private void sincronizarReceber()
  {
    this.setMsgSincronizar(new MsgPesquisar());

    this.getMsgSincronizar().setTbl(this);
    this.getMsgSincronizar().setDttUltimoRecebimento(TblSincronizacao.getI().getDttUltimoRecebimento(this));

    this.getSrvSinc().getSrvHttpSinc().enviar(this.getMsgSincronizar());
  }
}