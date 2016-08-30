package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.textbox.TextBoxGeral;

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
}