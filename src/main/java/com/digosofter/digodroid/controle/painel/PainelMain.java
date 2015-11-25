package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.digosofter.digodroid.controle.ControleMain;
import com.digosofter.digojava.erro.Erro;

public abstract class PainelMain extends ControleMain {

  public PainelMain(Context context) {

    super(context);
  }

  public PainelMain(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public PainelMain(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void finalizar() {

    super.finalizar();

    try {

      this.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
      this.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }
}
