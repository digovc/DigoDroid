package com.digosofter.digodroid.server.message;

import java.util.EventListener;

public interface OnMsgRespostaListener extends EventListener
{
  void onRespostaErro(MessageMain msgSender);

  void onRespostaSucesso(MessageMain msgSender);
}