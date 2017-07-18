package com.digosofter.digodroid;

import java.util.EventListener;

public interface OnCodigoBarraSucessoListener extends EventListener
{
  void onCodigoBarraSucesso(final String strCodigoBarra);
}