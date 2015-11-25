package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digojava.erro.Erro;

public class PainelRelevo extends PainelMain {

  public PainelRelevo(Context context) {

    super(context);
  }

  public PainelRelevo(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public PainelRelevo(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void finalizar() {

    super.finalizar();

    try {

      this.finalizarMargin();

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void finalizarMargin() {

    int intMarginDp;
    MarginLayoutParams objMarginLayoutParams;

    try {

      intMarginDp = UtilsAndroid.dpToPx(TemaDefault.getI().getIntMargin(), this.getContext());

      objMarginLayoutParams = MarginLayoutParams.class.cast(this.getLayoutParams());

      objMarginLayoutParams.setMargins(intMarginDp, intMarginDp, intMarginDp, intMarginDp);

      this.setLayoutParams(objMarginLayoutParams);

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  protected void inicializar() {

    super.inicializar();

    try {

      this.setBackgroundColor(TemaDefault.getI().getCorFundo1());

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }
}
