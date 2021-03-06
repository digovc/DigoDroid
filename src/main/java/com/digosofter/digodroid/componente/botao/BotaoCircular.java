package com.digosofter.digodroid.componente.botao;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.componente.IControleMain;

import at.markushi.ui.CircleButton;

public class BotaoCircular extends CircleButton implements IControleMain
{
  public BotaoCircular(final Context cnt)
  {
    super(cnt);

    this.iniciar(null);
  }

  public BotaoCircular(final Context cnt, final AttributeSet atr)
  {
    super(cnt, atr);

    this.iniciar(atr);
  }

  public BotaoCircular(final Context cnt, final AttributeSet atr, final int defStyle)
  {
    super(cnt, atr, defStyle);

    this.iniciar(atr);
  }

  @Override
  public void finalizar()
  {
  }

  @Override
  public void inicializar()
  {
  }

  @Override
  public void inicializar(final AttributeSet ats)
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