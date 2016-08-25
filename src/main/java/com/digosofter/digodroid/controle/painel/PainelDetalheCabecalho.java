package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.R;

public class PainelDetalheCabecalho extends PainelGeral
{
  public PainelDetalheCabecalho(Context context)
  {
    super(context);
  }

  public PainelDetalheCabecalho(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public PainelDetalheCabecalho(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.setBackgroundColor(this.getContext().getResources().getColor(R.color.cor_tema));
  }
}