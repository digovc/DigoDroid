package com.digosofter.digodroid.server.message;

public abstract class RspContainerDominioMain<T extends MessageMain> extends RespostaMain<T>
{
  private int _intRegistroQuantidade;
  private String _jsnLstObjDominio;

  public int getIntRegistroQuantidade()
  {
    return _intRegistroQuantidade;
  }

  public String getJsnLstObjDominio()
  {
    return _jsnLstObjDominio;
  }

  private void setIntRegistroQuantidade(int intRegistroQuantidade)
  {
    _intRegistroQuantidade = intRegistroQuantidade;
  }

  private void setJsnLstObjDominio(String jsnData)
  {
    _jsnLstObjDominio = jsnData;
  }
}