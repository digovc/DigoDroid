package com.digosofter.digodroid.activity;

import java.util.EventListener;

public interface OnActivityResultListener extends EventListener
{
  void onActivityResult(Object objSender, OnActivityResultArg arg);
}