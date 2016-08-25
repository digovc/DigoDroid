package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.balysv.materialripple.MaterialRippleLayout;
import com.digosofter.digodroid.controle.IControleMain;

public class PainelRipple extends MaterialRippleLayout implements IControleMain
{
  public PainelRipple(final Context context)
  {
    super(context);

    this.iniciar(null);
  }

  public PainelRipple(final Context context, final AttributeSet attrs)
  {
    super(context, attrs);

    this.iniciar(attrs);
  }

  public PainelRipple(final Context context, final AttributeSet attrs, final int defStyle)
  {
    super(context, attrs, defStyle);

    this.iniciar(attrs);
  }

  @Override
  public void finalizar()
  {
  }

  @Override
  public void inicializar(final AttributeSet ats)
  {
  }

  @Override
  public void inicializar()
  {
  }

  @Override
  public void iniciar(final AttributeSet ats)
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