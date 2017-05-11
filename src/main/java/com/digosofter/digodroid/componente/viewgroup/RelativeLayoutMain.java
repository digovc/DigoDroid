package com.digosofter.digodroid.componente.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.digosofter.digodroid.componente.IControleMain;

public abstract class RelativeLayoutMain extends RelativeLayout implements IControleMain
{
  public RelativeLayoutMain(Context cnt)
  {
    super(cnt);

    this.iniciar(null);
  }

  public RelativeLayoutMain(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);

    this.iniciar(atr);
  }

  public RelativeLayoutMain(Context cnt, AttributeSet atr, int intDefStyleAttr)
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
  protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec)
  {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    this.finalizar();
  }

  @Override
  public void setEventos()
  {
  }
}