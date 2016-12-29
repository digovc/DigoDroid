package com.digosofter.digodroid.server.message;

public class RspWelcome extends RespostaMain<MsgWelcome>
{
  private int _intPessoaEmpresaId;
  private String _strServerVersao;

  public int getIntPessoaEmpresaId()
  {
    return _intPessoaEmpresaId;
  }

  public String getStrServerVersao()
  {
    return _strServerVersao;
  }

  public void setIntPessoaEmpresaId(int intPessoaEmpresaId)
  {
    _intPessoaEmpresaId = intPessoaEmpresaId;
  }

  public void setStrServerVersao(String strServerVersao)
  {
    _strServerVersao = strServerVersao;
  }
}