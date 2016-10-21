package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.UtilsAndroid;

public class PainelConteudo extends PainelGeral
{
  public PainelConteudo(Context cnt)
  {
    super(cnt);
  }

  public PainelConteudo(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public PainelConteudo(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    int intPadding = UtilsAndroid.dpToPx(10, this.getContext());

    this.setPadding(intPadding, intPadding, intPadding, intPadding);
  }
}