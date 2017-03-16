package com.digosofter.digodroid.controle.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.design.TemaDefault;

public abstract class LinearLayoutMain extends LinearLayout implements IControleMain
{
  public LinearLayoutMain(Context cnt)
  {
    super(cnt);

    this.iniciar(null);
  }

  public LinearLayoutMain(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);

    this.iniciar(atr);
  }

  public LinearLayoutMain(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);

    this.iniciar(atr);
  }

  @Override
  public void finalizar()
  {
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
  }

  @Override
  public void inicializar()
  {
//    this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
  }

  @Override
  public void iniciar(AttributeSet ats)
  {
    this.inicializar(ats);
    this.inicializar();
    this.montarLayout();
    this.setEventos();
  }

  @Override
  public void montarLayout()
  {
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
  {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    this.finalizar();
  }

  @Override
  public void setEventos()
  {
  }

  /**
   * Insere espaçamento interno neste componente.
   */
  public void setIntPadding()
  {
    int intPaddingPx = UtilsAndroid.dpToPx(TemaDefault.getI().getIntEspacamento(), this.getContext());

    this.setPadding(intPaddingPx, intPaddingPx, intPaddingPx, intPaddingPx);
  }
}