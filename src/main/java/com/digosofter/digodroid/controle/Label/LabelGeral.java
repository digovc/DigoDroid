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
  private int _corTexto;
  private TemaDefault.EnmFonteTamanho _enmFonteTamanho;
  private int _intTexto;

  public LabelGeral(Context context)
  {
    super(context);

    this.iniciar(null);

  }

  public LabelGeral(Context context, AttributeSet attrs)
  {
    super(context, attrs);

    this.iniciar(attrs);
  }

  public LabelGeral(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);

    this.iniciar(attrs);
  }

  private void atualizarCorTexto()
  {
    this.setTextColor(this.getCorTexto());
  }

  private void atualizarEnmFonteTamanho()
  {
    this.setTextSize(TypedValue.COMPLEX_UNIT_SP, TemaDefault.getI().enmFonteTamanhoToInt(this.getEnmFonteTamanho()));
  }

  @Override
  public void finalizar()
  {
  }

  private int getCorTexto()
  {
    return _corTexto;
  }

  private TemaDefault.EnmFonteTamanho getEnmFonteTamanho()
  {
    return _enmFonteTamanho;
  }

  public int getIntTexto()
  {
    try
    {
      _intTexto = Integer.valueOf(this.getText().toString());
    }
    catch (Exception ex)
    {
      return 0;
    }

    return _intTexto;
  }

  @Override
  public void inicializar()
  {
    this.setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.NORMAL);
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
    int intFonteTamanho;
    TypedArray objTypedArray;

    if (ats == null)
    {
      return;
    }
    objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.LabelGeral);
    intFonteTamanho = objTypedArray.getInt(R.styleable.LabelGeral_enmFonteTamanho, 2);
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

  /**
   * Indica a cor do texto.
   *
   * @param corTexto Cor do texto.
   */
  public void setCorTexto(int corTexto)
  {
    _corTexto = corTexto;
    this.atualizarCorTexto();
  }

  public void setEnmFonteTamanho(TemaDefault.EnmFonteTamanho enmFonteTamanho)
  {
    _enmFonteTamanho = enmFonteTamanho;
    this.atualizarEnmFonteTamanho();
  }

  @Override
  public void setEventos()
  {
  }

  public void setIntTexto(int intTexto)
  {
    _intTexto = intTexto;
    this.setText(String.valueOf(_intTexto));
  }
}