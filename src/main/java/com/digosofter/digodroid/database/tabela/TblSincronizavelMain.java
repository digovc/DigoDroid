package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.database.DataBaseAndroid;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.dominio.DominioSincronizavelMain;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digodroid.sinc.ServerHttpSinc;
import com.digosofter.digodroid.sinc.message.MsgPesquisar;
import com.digosofter.digodroid.sinc.message.RspPesquisar;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.json.Json;
import com.digosofter.digojava.log.Log;
import com.google.gson.JsonElement;

public abstract class TblSincronizavelMain<T extends DominioSincronizavelMain> extends TabelaAndroid<T>
{
  private MsgPesquisar _msgSincronizar;
  private String _sqlServerNome;

  protected TblSincronizavelMain(final String strNome, final DataBaseAndroid dbeAndroid)
  {
    super(strNome, dbeAndroid);
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

  public void processar(final RspPesquisar rspPesquisar)
  {
    if (rspPesquisar == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(rspPesquisar.getJsnLstObjDominio()))
    {
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
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "Pesquisa retornou nenhum registro.");
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

    if (objDominio == null)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, "Erro ao converter o item do resultado da pesquisa.");
      return;
    }

    this.salvar(objDominio);

    rspPesquisar.addObjDominioSincronizado(objDominio);
  }

  private void processar(final RspPesquisar rspPesquisar, final T objDominio)
  {
    if (objDominio == null)
    {
      return;
    }

    this.salvar(objDominio, false);
  }

  private void setMsgSincronizar(MsgPesquisar msgSincronizar)
  {
    _msgSincronizar = msgSincronizar;
  }

  public void sincronizar()
  {
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
  }

  private void sincronizarFinalizar(final RspPesquisar rspPesquisar)
  {
    this.setMsgSincronizar(null);

  }

  private void sincronizarReceber()
  {
    if (Utils.getBooStrVazia(this.getSqlNome()))
    {
      return;
    }

    this.setMsgSincronizar(new MsgPesquisar());

    this.getMsgSincronizar().setTbl(this);
    this.getMsgSincronizar().setDttUltimoRecebimento(TblSincronizacao.getI().getDttUltimoRecebimento(this));

    ServerHttpSinc.getI().enviar(this.getMsgSincronizar());
  }
}