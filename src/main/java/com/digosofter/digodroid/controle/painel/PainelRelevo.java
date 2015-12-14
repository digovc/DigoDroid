package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;

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

  private void finalizarMargin() {

    int intMarginDp;
    MarginLayoutParams objMarginLayoutParams;

    try {

      intMarginDp = UtilsAndroid.dpToPx(TemaDefault.getI().getIntMargin(), this.getContext());

      objMarginLayoutParams = new MarginLayoutParams(this.getWidth(), this.getHeight());

      objMarginLayoutParams.setMargins(intMarginDp, intMarginDp, intMarginDp, intMarginDp);

      this.setLayoutParams(objMarginLayoutParams);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.setBackgroundColor(TemaDefault.getI().getCorFundo1());
//      this.setIntPadding(TemaDefault.getI().getIntPadding());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }
}
