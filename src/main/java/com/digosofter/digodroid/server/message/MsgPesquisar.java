package com.digosofter.digodroid.server.message;

import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.log.Log;

import java.util.Calendar;

public class MsgPesquisar extends MsgTabelaBase<RspPesquisar>
{
  private boolean _booPrimeiraPesquisa;
  private Calendar _dttUltimoRecebimento;
  private int _intPesquisaParte;
  private int _intSincRegistroLimite;

  public boolean getBooPrimeiraPesquisa()
  {
    return _booPrimeiraPesquisa;
  }

  public Calendar getDttUltimoRecebimento()
  {
    return _dttUltimoRecebimento;
  }

  public int getIntPesquisaParte()
  {
    return _intPesquisaParte;
  }

  public int getIntSincRegistroLimite()
  {
    return _intSincRegistroLimite;
  }

  @Override
  protected void onResposta(final RspPesquisar rsp)
  {
    if (this.getTbl() == null)
    {
      return;
    }

    this.getTbl().setMsgPesquisar(null);

    if (rsp == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(rsp.getJsnLstObjDominio()))
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Não havia dados na pesquisa a serem salvos na tabela %s.", this.getTbl().getStrNomeExibicao()));
      return;
    }

    this.getTbl().processarPesquisa(rsp);
  }

  public void setBooPrimeiraPesquisa(boolean booPrimeiraPesquisa)
  {
    _booPrimeiraPesquisa = booPrimeiraPesquisa;
  }

  public void setDttUltimoRecebimento(Calendar dttUltimoRecebimento)
  {
    _dttUltimoRecebimento = dttUltimoRecebimento;
  }

  public void setIntPesquisaParte(int intPesquisaParte)
  {
    _intPesquisaParte = intPesquisaParte;
  }

  public void setIntSincRegistroLimite(int intSincRegistroLimite)
  {
    _intSincRegistroLimite = intSincRegistroLimite;
  }
}