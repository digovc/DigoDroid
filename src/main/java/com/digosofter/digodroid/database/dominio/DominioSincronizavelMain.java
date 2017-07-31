package com.digosofter.digodroid.database.dominio;

public abstract class DominioSincronizavelMain extends DominioAndroidMain
{
  private boolean _booSincronizado;
  private boolean _booSincronizar;
  private int _intReservaCodigoId;
  private String _strAparelhoId;
  private String _strSincCritica;

  public boolean getBooSincronizado()
  {
    return _booSincronizado;
  }

  public boolean getBooSincronizar()
  {
    return _booSincronizar;
  }

  public int getIntReservaCodigoId()
  {
    return _intReservaCodigoId;
  }

  public String getStrAparelhoId()
  {
    return _strAparelhoId;
  }

  public String getStrSincCritica()
  {
    return _strSincCritica;
  }

  public void setBooSincronizado(boolean booSincronizado)
  {
    _booSincronizado = booSincronizado;
  }

  public void setBooSincronizar(boolean booSincronizar)
  {
    _booSincronizar = booSincronizar;
  }

  public void setIntReservaCodigoId(int intReservaCodigoId)
  {
    _intReservaCodigoId = intReservaCodigoId;
  }

  public void setStrAparelhoId(String strAparelhoId)
  {
    _strAparelhoId = strAparelhoId;
  }

  public void setStrSincCritica(String strSincCritica)
  {
    _strSincCritica = strSincCritica;
  }
}