package com.digosofter.digodroid;

import java.util.EventListener;

public interface OnValorAlterado extends EventListener {

  void onValorAlterado(Object objSender, OnValorAlteradoArg arg);
}