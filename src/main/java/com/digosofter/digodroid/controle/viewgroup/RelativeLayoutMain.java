package com.digosofter.digodroid.controle.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.digosofter.digodroid.controle.IControleMain;

public abstract class RelativeLayoutMain extends RelativeLayout implements IControleMain
{
  public RelativeLayoutMain(Context context)
  {
    super(context);

    this.iniciar(null);
  }

  public RelativeLayoutMain(Context context, AttributeSet attrs)
  {
    super(context, attrs);

    this.iniciar(attrs);
  }

  public RelativeLayoutMain(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);

    this.iniciar(attrs);
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