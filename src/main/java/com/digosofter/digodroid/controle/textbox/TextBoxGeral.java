package com.digosofter.digodroid.controle.textbox;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.digosofter.digojava.erro.Erro;

public class TextBoxGeral extends TextBoxMain {

  public TextBoxGeral(Context context) {

    super(context);
  }

  public TextBoxGeral(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public TextBoxGeral(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void inicializar() {

    super.inicializar();

    try {

      this.getTxt().setText("Olá mundo.");

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }
}