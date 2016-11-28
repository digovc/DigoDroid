package com.digosofter.digodroid.server.message;

import com.digosofter.digodroid.AppAndroid;

public class MsgWelcome extends MessageMain<RspWelcome>
{
  @Override
  protected void onResposta(final RspWelcome rspWelcome)
  {
    if (AppAndroid.getI() == null)
    {
      return;
    }

    AppAndroid.getI().processarRspWelcome(rspWelcome);
  }
}