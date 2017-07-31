package com.digosofter.digodroid.server.message;

import java.util.GregorianCalendar;

public abstract class RespostaMain<T extends MessageMain>
{
  private GregorianCalendar _dttServidor;
  private T _msg;
  private String _strCritica;

  public GregorianCalendar getDttServidor()
  {
    return _dttServidor;
  }

  public T getMsg()
  {
    return _msg;
  }

  public String getStrCritica()
  {
    return _strCritica;
  }

  public void setDttServidor(GregorianCalendar dttServidor)
  {
    _dttServidor = dttServidor;
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