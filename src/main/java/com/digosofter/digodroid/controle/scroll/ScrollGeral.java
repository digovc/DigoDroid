package com.digosofter.digodroid.controle.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.digosofter.digodroid.controle.IControleMain;

public class ScrollGeral extends ScrollView implements IControleMain
{
  public ScrollGeral(Context context)
  {
    super(context);

    this.iniciar(null);
  }

  public ScrollGeral(Context context, AttributeSet attrs)
  {
    super(context, attrs);

    this.iniciar(attrs);
  }

  public ScrollGeral(Context context, AttributeSet attrs, int defStyleAttr)
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
    this.inicializar();
    this.inicializar(ats);
    this.montarLayout();
    this.setEventos();
  }

  @Override
  public void montarLayout()
  {
  }

  /**
   * Move o scroll para o seu fundo.
   */
  public void moverBaixo()
  {
    this.fullScroll(ScrollView.FOCUS_DOWN);
  }

  /**
   * Move o scroll para o seu topo.
   */
  public void moverCima()
  {
    this.fullScroll(ScrollView.FOCUS_UP);
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
}