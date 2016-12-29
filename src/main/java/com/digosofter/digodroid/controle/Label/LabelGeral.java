package com.digosofter.digodroid.controle.label;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.design.TemaDefault;

public class LabelGeral extends TextView implements IControleMain
{
  private TemaDefault.EnmFonteTamanho _enmFonteTamanho;

  public LabelGeral(Context cnt)
  {
    super(cnt);

    this.iniciar(null);
  }

  public LabelGeral(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);

    this.iniciar(atr);
  }

  public LabelGeral(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);

    this.iniciar(atr);
  }

  private void atualizarEnmFonteTamanho(final TemaDefault.EnmFonteTamanho enmFonteTamanho)
  {
    this.setTextSize(TypedValue.COMPLEX_UNIT_SP, TemaDefault.getI().enmFonteTamanhoToInt(enmFonteTamanho));
  }

  @Override
  public void finalizar()
  {
  }

  private TemaDefault.EnmFonteTamanho getEnmFonteTamanho()
  {
    return _enmFonteTamanho;
  }

  @Override
  public void inicializar()
  {
    this.setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.NORMAL);
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
    if (ats == null)
    {
      return;
    }

    TypedArray objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.LabelGeral);

    int intFonteTamanho = objTypedArray.getInt(R.styleable.LabelGeral_enmFonteTamanho, 2);

    this.setEnmFonteTamanho(TemaDefault.getI().intToEnmFonteTamanho(intFonteTamanho));
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

  public void setEnmFonteTamanho(TemaDefault.EnmFonteTamanho enmFonteTamanho)
  {
    if (_enmFonteTamanho == enmFonteTamanho)
    {
      return;
    }

    _enmFonteTamanho = enmFonteTamanho;

    this.atualizarEnmFonteTamanho(enmFonteTamanho);
  }

  @Override
  public void setEventos()
  {
  }
}