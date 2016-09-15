package com.digosofter.digodroid.sinc.message;

import com.digosofter.digodroid.dominio.DominioAndroidMain;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digojava.log.Log;

import java.util.ArrayList;
import java.util.List;

public class RspPesquisar extends RespostaMain<MsgPesquisar>
{
  private String _jsnLstObjDominio;
  private List<DominioAndroidMain> _lstObjDominioSincronizado;

  public void addObjDominioSincronizado(DominioAndroidMain objDominio)
  {
    if (objDominio == null)
    {
      return;
    }

    if (this.getLstObjDominioSincronizado().contains(objDominio))
    {
      return;
    }

    this.getLstObjDominioSincronizado().add(objDominio);

    LogSinc.getI().addLog(Log.EnmTipo.INFO, String.format("Registro %s salvo com sucesso na tabela %s.", objDominio.getIntId(), this.getMsg().getTbl().getStrNomeExibicao()));
  }

  public String getJsnLstObjDominio()
  {
    return _jsnLstObjDominio;
  }

  private List<DominioAndroidMain> getLstObjDominioSincronizado()
  {
    if (_lstObjDominioSincronizado != null)
    {
      return _lstObjDominioSincronizado;
    }

    _lstObjDominioSincronizado = new ArrayList<>();

    return _lstObjDominioSincronizado;
  }

  private void setJsnLstObjDominio(String jsnData)
  {
    _jsnLstObjDominio = jsnData;
  }
}