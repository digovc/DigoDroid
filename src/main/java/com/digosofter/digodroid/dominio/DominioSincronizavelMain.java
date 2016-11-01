package com.digosofter.digodroid.dominio;

public abstract class DominioSincronizavelMain extends DominioAndroidMain
{
  private boolean _booSincronizado;
  private boolean _booSincronizar;
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

  public void setStrAparelhoId(String strAparelhoId)
  {
    _strAparelhoId = strAparelhoId;
  }

  public void setStrSincCritica(String strSincCritica)
  {
    _strSincCritica = strSincCritica;
  }
}