package com.digosofter.digodroid.activity;

import android.content.Intent;

import java.util.EventListener;

public interface OnActivityResultListener extends EventListener {

  void onActivityResult(Object objSender, OnActivityResultArg arg);
}