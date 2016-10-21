package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.util.AttributeSet;

public class CampoTexto extends CampoAlfanumerico
{
  public CampoTexto(final Context cnt)
  {
    super(cnt);
  }

  public CampoTexto(final Context cnt, final AttributeSet atr)
  {
    super(cnt, atr);
  }

  public CampoTexto(final Context cnt, final AttributeSet atr, final int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.setIntNivelQuantidade(2);
  }
}