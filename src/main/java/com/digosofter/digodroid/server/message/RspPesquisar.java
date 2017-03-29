package com.digosofter.digodroid.server.message;

import com.digosofter.digodroid.database.dominio.DominioAndroidMain;

import java.util.ArrayList;
import java.util.List;

public class RspPesquisar extends RspContainerDominioMain<MsgPesquisar>
{
  private boolean _booSincCompleto;
  private int _intRegistroQuantidadeTotal;
  private int _intSincronizacaoId;
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
  }

  public boolean getBooSincCompleto()
  {
    return _booSincCompleto;
  }

  public int getIntRegistroQuantidadeTotal()
  {
    return _intRegistroQuantidadeTotal;
  }

  public int getIntSincronizacaoId()
  {
    return _intSincronizacaoId;
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

  public void setBooSincCompleto(boolean booSincCompleto)
  {
    _booSincCompleto = booSincCompleto;
  }

  public void setIntRegistroQuantidadeTotal(int intRegistroQuantidadeTotal)
  {
    _intRegistroQuantidadeTotal = intRegistroQuantidadeTotal;
  }

  public void setIntSincronizacaoId(int intSincronizacaoId)
  {
    _intSincronizacaoId = intSincronizacaoId;
  }
}