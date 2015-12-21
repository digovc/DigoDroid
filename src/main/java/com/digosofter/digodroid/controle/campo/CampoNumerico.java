package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.textbox.TextBoxGeral;
import com.digosofter.digodroid.erro.ErroAndroid;

public class CampoNumerico extends CampoAlfanumerico {

  public CampoNumerico(Context context) {

    super(context);
  }

  public CampoNumerico(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public CampoNumerico(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.getTxt().setEnmFormato(TextBoxGeral.EnmFormato.NUMERICO_INTEIRO);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}