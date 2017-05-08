package com.digosofter.digodroid.activity;

import java.util.EventListener;

public interface OnRequestPermissionResultListener extends EventListener
{
  void onRequestPermissionResult(Object objSender, OnRequestPermissionResultArg arg);
}