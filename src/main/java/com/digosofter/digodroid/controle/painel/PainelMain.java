package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.viewgroup.LinearLayoutMain;

public abstract class PainelMain extends LinearLayoutMain
{
  public PainelMain(Context cnt)
  {
    super(cnt);
  }

  public PainelMain(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public PainelMain(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }
}