package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.design.TemaDefault;

public class PainelLinha extends PainelMain
{
  private int _intNivelQuantidade;

  public PainelLinha(Context cnt)
  {
    super(cnt);
  }

  public PainelLinha(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public PainelLinha(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  @Override
  public void finalizar()
  {
    super.finalizar();

    this.getLayoutParams().height = UtilsAndroid.dpToPx(this.getIntNivelQuantidade() * TemaDefault.getI().getIntHeightNivel(), this.getContext());
    this.getLayoutParams().width = LayoutParams.MATCH_PARENT;
  }

  private int getIntNivelQuantidade()
  {
    return _intNivelQuantidade;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    int intPaddingDp = UtilsAndroid.dpToPx(TemaDefault.getI().getIntEspacamento(), this.getContext());

    this.setPadding(intPaddingDp, intPaddingDp, intPaddingDp, intPaddingDp);
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
    super.inicializar(ats);

    if (ats == null)
    {
      return;
    }

    TypedArray objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.PainelLinha);

    this.setIntNivelQuantidade(objTypedArray.getInt(R.styleable.PainelLinha_intNivelQuantidade, 1));
  }

  protected void setIntNivelQuantidade(int intNivelQuantidade)
  {
    _intNivelQuantidade = intNivelQuantidade;
  }
}