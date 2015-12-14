package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.erro.Erro;

public class PainelLinha extends PainelMain {

  private int _intNivelQuantidade;

  public PainelLinha(Context context) {

    super(context);
  }

  public PainelLinha(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public PainelLinha(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  @Override
  public void finalizar() {

    super.finalizar();

    try {

      this.getLayoutParams().height = UtilsAndroid.dpToPx(this.getIntNivelQuantidade() * TemaDefault.getI().getIntHeightNivel(), this.getContext());
      this.getLayoutParams().width = LayoutParams.MATCH_PARENT;

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private int getIntNivelQuantidade() {

    return _intNivelQuantidade;
  }

  @Override
  public void inicializar() {

    super.inicializar();

    int intPaddingDp;

    try {

      intPaddingDp = UtilsAndroid.dpToPx(TemaDefault.getI().getIntPadding(), this.getContext());

      this.setPadding(intPaddingDp, intPaddingDp, intPaddingDp, intPaddingDp);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void inicializar(AttributeSet ats) {

    super.inicializar(ats);

    TypedArray objTypedArray;

    try {

      if (ats == null) {

        return;
      }

      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.PainelLinha);

      this.setIntNivelQuantidade(objTypedArray.getInt(R.styleable.PainelLinha_intNivelQuantidade, 1));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void setIntNivelQuantidade(int intNivelQuantidade) {

    _intNivelQuantidade = intNivelQuantidade;
  }
}