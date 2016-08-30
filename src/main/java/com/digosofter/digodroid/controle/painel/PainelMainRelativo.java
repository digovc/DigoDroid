package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.viewgroup.RelativeLayoutMain;

public abstract class PainelMainRelativo extends RelativeLayoutMain
{
  public PainelMainRelativo(Context cnt)
  {
    super(cnt);
  }

  public PainelMainRelativo(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public PainelMainRelativo(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }
}