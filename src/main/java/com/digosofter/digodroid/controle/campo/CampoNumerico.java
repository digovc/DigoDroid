package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.textbox.TextBoxGeral;

public class CampoNumerico extends CampoAlfanumerico
{
  public CampoNumerico(Context context)
  {
    super(context);
  }

  public CampoNumerico(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public CampoNumerico(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.getTxt().setEnmFormato(TextBoxGeral.EnmFormato.NUMERICO_INTEIRO);
  }
}