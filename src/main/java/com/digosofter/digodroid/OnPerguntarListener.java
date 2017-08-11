package com.digosofter.digodroid;

import java.util.EventListener;

public interface OnPerguntarListener extends EventListener
{
  void onNao(final String strPergunta);

  void onSim(final String strPergunta);
}