package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.textbox.TextBoxMain;
import com.digosofter.digojava.erro.Erro;

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
  protected void inicializar() {

    super.inicializar();

    try {

      this.getTxt().setEnmFormato(TextBoxMain.EnmFormato.NUMERICO_INTEIRO);

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }
}