package com.digosofter.digodroid.server.message;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.log.Log;

import java.io.IOException;
import java.util.Calendar;

public class MsgPesquisar extends MsgTabelaBase<RspPesquisar>
{
  private boolean _booPrimeiraPesquisa;
  private Calendar _dttUltimoRecebimento;
  private int _intMesRetroativo;
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

  public int getIntMesRetroativo()
  {
    return _intMesRetroativo;
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

    if (rsp == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(rsp.getJsnLstObjDominio()))
    {
      LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Não havia dados na pesquisa a serem salvos na tabela %s.", this.getTbl().getStrNomeExibicao()));
      return;
    }

    if (!rsp.getBooCompactado())
    {
      this.getTbl().processarPesquisa(this, rsp);
      return;
    }

    try
    {
      rsp.setJsnLstObjDominio(UtilsAndroid.descomprimir(rsp.getJsnLstObjDominio()));
    }
    catch (IOException e)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro ao tentar descomprimir os dados da tabela %s.", this.getTbl().getStrNomeExibicao()));
      return;
    }

    this.getTbl().processarPesquisa(this, rsp);
  }

  private void setBooPrimeiraPesquisa(boolean booPrimeiraPesquisa)
  {
    _booPrimeiraPesquisa = booPrimeiraPesquisa;
  }

  public void setDttUltimoRecebimento(Calendar dttUltimoRecebimento)
  {
    _dttUltimoRecebimento = dttUltimoRecebimento;

    this.setBooPrimeiraPesquisa(_dttUltimoRecebimento == null);
  }

  public void setIntMesRetroativo(int intMesRetroativo)
  {
    _intMesRetroativo = intMesRetroativo;
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