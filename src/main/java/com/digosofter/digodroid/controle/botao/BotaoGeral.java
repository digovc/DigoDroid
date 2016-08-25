package com.digosofter.digodroid.controle.botao;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.digosofter.digodroid.controle.IControleMain;

public class BotaoGeral extends Button implements IControleMain
{
  public BotaoGeral(final Context context)
  {
    super(context);

    this.iniciar(null);
  }

  public BotaoGeral(final Context context, final AttributeSet attrs)
  {
    super(context, attrs);

    this.iniciar(attrs);
  }

  public BotaoGeral(final Context context, final AttributeSet attrs, final int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);

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