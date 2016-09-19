package com.digosofter.digodroid.dominio;

public abstract class DominioSincronizavelMain extends DominioAndroidMain
{
  private boolean _booSincronizado;
  private boolean _booSincronizar;
  private int _intServerId;
  private String _strAparelhoId;

  public boolean getBooSincronizado()
  {
    return _booSincronizado;
  }

  public boolean getBooSincronizar()
  {
    return _booSincronizar;
  }

  public int getIntServerId()
  {
    return _intServerId;
  }

  public String getStrAparelhoId()
  {
    return _strAparelhoId;
  }

  public void setBooSincronizado(boolean booSincronizado)
  {
    _booSincronizado = booSincronizado;
  }

  public void setBooSincronizar(boolean booSincronizar)
  {
    _booSincronizar = booSincronizar;
  }

  public void setIntServerId(int intServerId)
  {
    _intServerId = intServerId;
  }

  public void setStrAparelhoId(String strAparelhoId)
  {
    _strAparelhoId = strAparelhoId;
  }
}