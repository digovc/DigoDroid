package com.digosofter.digodroid.componente.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.componente.campo.CampoAlfanumerico;

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