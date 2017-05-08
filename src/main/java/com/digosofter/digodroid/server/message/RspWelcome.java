package com.digosofter.digodroid.server.message;

public class RspWelcome extends RespostaMain<MsgWelcome>
{
  private String _jsnData;
  private String _strServerVersao;

  public String getJsnData()
  {
    return _jsnData;
  }

  public String getStrServerVersao()
  {
    return _strServerVersao;
  }

  public void setJsnData(String jsnData)
  {
    _jsnData = jsnData;
  }

  public void setStrServerVersao(String strServerVersao)
  {
    _strServerVersao = strServerVersao;
  }
}