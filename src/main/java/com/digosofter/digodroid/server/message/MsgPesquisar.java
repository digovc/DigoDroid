package com.digosofter.digodroid.server.message;

import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.log.Log;

import java.util.Calendar;

public class MsgPesquisar extends MsgTabelaBase<RspPesquisar>
{
  private Calendar _dttUltimoRecebimento;

  public Calendar getDttUltimoRecebimento()
  {
    return _dttUltimoRecebimento;
  }

  @Override
  protected void onResposta(final RspPesquisar rsp)
  {
    if (this.getTbl() == null)
    {
      return;
    }

    if (rsp == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(rsp.getJsnLstObjDominio()))
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Não havia dados na pesquisa a serem salvos na tabela %s.", this.getTbl().getStrNomeExibicao()));
      return;
    }

    this.getTbl().processar(rsp);
  }

  public void setDttUltimoRecebimento(Calendar dttUltimoRecebimento)
  {
    _dttUltimoRecebimento = dttUltimoRecebimento;
  }
}