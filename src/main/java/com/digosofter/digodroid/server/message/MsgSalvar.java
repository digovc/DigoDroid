package com.digosofter.digodroid.server.message;

public class MsgSalvar extends MsgTabelaBase<RspSalvar>
{
  private String _jsnLstObjDominio;

  private String getJsnLstObjDominio()
  {
    return _jsnLstObjDominio;
  }

  @Override
  protected void onResposta(final RspSalvar rsp)
  {
  }

  public void setJsnLstObjDominio(String jsnLstObjDominio)
  {
    _jsnLstObjDominio = jsnLstObjDominio;
  }
}