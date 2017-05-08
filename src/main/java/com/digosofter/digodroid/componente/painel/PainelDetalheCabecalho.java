package com.digosofter.digodroid.componente.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.design.TemaDefault;

public class PainelDetalheCabecalho extends PainelGeral
{
  public PainelDetalheCabecalho(Context cnt)
  {
    super(cnt);
  }

  public PainelDetalheCabecalho(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public PainelDetalheCabecalho(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.setBackgroundColor(AppAndroid.getI().getObjTema().getCorTema());
  }
}