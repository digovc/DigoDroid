package com.digosofter.digodroid.server.message;

public class MsgLongPolling extends MessageMain<RspLongPolling>
{
  @Override
  public int getIntTimeOut()
  {
    // return super.getIntTimeOut();

    //    return (5 * 60 * 1000);
    return (60 * 1000);
  }

  @Override
  protected void onResposta(final RspLongPolling rsp)
  {
  }
}