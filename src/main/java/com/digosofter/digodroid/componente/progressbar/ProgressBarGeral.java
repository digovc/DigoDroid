package com.digosofter.digodroid.componente.progressbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.componente.IControleMain;

public class ProgressBarGeral extends ProgressBar implements IControleMain
{
  public ProgressBarGeral(final Context cnt, final AttributeSet atr, final int defStyle)
  {
    super(cnt, atr, android.R.attr.progressBarStyleHorizontal);

    this.iniciar(atr);
  }

  public ProgressBarGeral(final Context cnt, final AttributeSet atr)
  {
    super(cnt, atr);

    this.iniciar(atr);
  }

  public ProgressBarGeral(final Context cnt)
  {
    super(cnt);

    this.iniciar(null);
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
    this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(10, this.getContext())));
  }

  @Override
  public void iniciar(AttributeSet ats)
  {
    this.inicializar();
    this.inicializar(ats);
    this.montarLayout();
    this.setEventos();
  }

  @Override
  public void montarLayout()
  {
  }

  @Override
  protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
  {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    this.finalizar();
  }

  @Override
  public void setEventos()
  {
  }
}