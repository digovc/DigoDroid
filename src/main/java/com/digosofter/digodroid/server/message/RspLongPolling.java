package com.digosofter.digodroid.server.message;

public class RspLongPolling extends RespostaMain
{
  private boolean _booRegistroNovo;

  public boolean getBooRegistroNovo()
  {
    return _booRegistroNovo;
  }

  public void setBooRegistroNovo(boolean booRegistroNovo)
  {
    _booRegistroNovo = booRegistroNovo;
  }
}