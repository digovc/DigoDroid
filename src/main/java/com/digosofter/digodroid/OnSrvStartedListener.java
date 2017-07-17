package com.digosofter.digodroid;

import com.digosofter.digodroid.service.ServiceMain;

import java.util.EventListener;

public interface OnSrvStartedListener extends EventListener
{
  void onSrvStarted(final ServiceMain srv);
}