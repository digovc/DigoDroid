package com.digosofter.digodroid;

import java.util.EventListener;

public interface OnInserirTextoListener extends EventListener
{
  void onCancelar(final String strTitulo);

  void onOk(final String strTitulo, final String strTexto);
}