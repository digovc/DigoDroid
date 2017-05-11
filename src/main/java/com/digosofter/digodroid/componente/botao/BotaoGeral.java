package com.digosofter.digodroid.componente.botao;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.digosofter.digodroid.componente.IControleMain;

public class BotaoGeral extends Button implements IControleMain
{
  public BotaoGeral(final Context cnt)
  {
    super(cnt);

    this.iniciar(null);
  }

  public BotaoGeral(final Context cnt, final AttributeSet atr)
  {
    super(cnt, atr);

    this.iniciar(atr);
  }

  public BotaoGeral(final Context cnt, final AttributeSet atr, final int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);

    this.iniciar(atr);
  }

  @Override
  public void finalizar()
  {
  }

  @Override
  public void inicializar(final AttributeSet ats)
  {
    this.setAllCaps(false);
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