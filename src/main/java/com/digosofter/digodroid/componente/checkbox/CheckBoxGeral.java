package com.digosofter.digodroid.controle.checkbox;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.digosofter.digodroid.controle.IControleMain;

public class CheckBoxGeral extends CheckBox implements IControleMain
{
  public CheckBoxGeral(Context cnt)
  {
    super(cnt);

    this.iniciar(null);
  }

  public CheckBoxGeral(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);

    this.iniciar(atr);
  }

  public CheckBoxGeral(Context cnt, AttributeSet atr, int intDefStyleAttr)
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