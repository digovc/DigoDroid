package com.digosofter.digodroid.activity;

import java.util.EventListener;

public interface OnResultListener extends EventListener
{
  void onActivityResult(Object objSender, OnActivityResultArg arg);
}