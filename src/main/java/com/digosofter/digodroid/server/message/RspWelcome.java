package com.digosofter.digodroid.server.message;

public class RspWelcome extends RespostaMain<MsgWelcome>
{
  private String _strEmpresaNome;
  private String _strServerVersao;

  public String getStrEmpresaNome()
  {
    return _strEmpresaNome;
  }

  public String getStrServerVersao()
  {
    return _strServerVersao;
  }

  public void setStrEmpresaNome(String strEmpresaNome)
  {
    _strEmpresaNome = strEmpresaNome;
  }

  public void setStrServerVersao(String strServerVersao)
  {
    _strServerVersao = strServerVersao;
  }
}