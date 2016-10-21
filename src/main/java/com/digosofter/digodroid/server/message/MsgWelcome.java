package com.digosofter.digodroid.server.message;

import com.digosofter.digodroid.Aparelho;

public class MsgWelcome extends MessageMain<RspWelcome>
{
  private String _strAparelhoId;

  public String getStrAparelhoId()
  {
    return _strAparelhoId;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.setStrAparelhoId(Aparelho.getI().getStrDeviceId());
  }

  @Override
  protected void onResposta(final RspWelcome rsp)
  {

  }

  public void setStrAparelhoId(String strAparelhoId)
  {
    _strAparelhoId = strAparelhoId;
  }
}