package com.digosofter.digodroid.server;

import java.util.EventListener;

public interface OnProgressoChangedListener extends EventListener
{
  void onProgressoChanged(final ServerHttpSincMain srvHttp, final float fltProgresso);
}
