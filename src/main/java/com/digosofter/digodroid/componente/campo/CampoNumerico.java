package com.digosofter.digodroid.componente.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.componente.textbox.TextBoxGeral;
import com.digosofter.digojava.Utils;

public class CampoNumerico extends CampoAlfanumerico
{
  public CampoNumerico(Context cnt)
  {
    super(cnt);
  }

  public CampoNumerico(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public CampoNumerico(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.getTxt().setEnmFormato(TextBoxGeral.EnmFormato.NUMERICO_INTEIRO);
  }

  @Override
  public void setStrValor(final String strValor)
  {
    super.setStrValor(strValor);

    if ("0".equals(strValor))
    {
      this.setStrValor(null);
    }

    if ("0.0".equals(strValor))
    {
      this.setStrValor(null);
    }

    if (!Utils.getBooStrVazia(strValor) && strValor.endsWith(".0"))
    {
      this.setStrValor(Utils.removerUltimaLetra(strValor, 2));
    }
  }
}