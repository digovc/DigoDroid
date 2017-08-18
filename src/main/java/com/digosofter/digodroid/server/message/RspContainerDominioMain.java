package com.digosofter.digodroid.server.message;

public abstract class RspContainerDominioMain<T extends MessageMain> extends RespostaMain<T>
{
  private boolean _booCompactado;
  private int _intRegistroQuantidade;
  private String _jsnLstObjDominio;

  boolean getBooCompactado()
  {
    return _booCompactado;
  }

  public int getIntRegistroQuantidade()
  {
    return _intRegistroQuantidade;
  }

  public String getJsnLstObjDominio()
  {
    return _jsnLstObjDominio;
  }

  public void setBooCompactado(boolean booCompactado)
  {
    _booCompactado = booCompactado;
  }

  private void setIntRegistroQuantidade(int intRegistroQuantidade)
  {
    _intRegistroQuantidade = intRegistroQuantidade;
  }

  void setJsnLstObjDominio(String jsnData)
  {
    _jsnLstObjDominio = jsnData;
  }
}