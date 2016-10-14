package com.digosofter.digodroid.server.message;

public abstract class RespostaMain<T extends MessageMain>
{
  private T _msg;
  private String _strCritica;

  protected T getMsg()
  {
    return _msg;
  }

  public String getStrCritica()
  {
    return _strCritica;
  }

  void setMsg(T msg)
  {
    _msg = msg;
  }

  public void setStrCritica(String strCritica)
  {
    _strCritica = strCritica;
  }
}