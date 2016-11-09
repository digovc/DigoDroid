package com.digosofter.digodroid.server.message;

public class RspCodigoReserva extends RespostaMain<MsgCodigoReserva>
{
  private int _intCodigoInicial;
  private int _intReservaCodigoId;

  public int getIntCodigoInicial()
  {
    return _intCodigoInicial;
  }

  public int getIntReservaCodigoId()
  {
    return _intReservaCodigoId;
  }

  public void setIntCodigoInicial(int intCodigoInicial)
  {
    _intCodigoInicial = intCodigoInicial;
  }

  public void setIntReservaCodigoId(int intReservaCodigoId)
  {
    _intReservaCodigoId = intReservaCodigoId;
  }
}