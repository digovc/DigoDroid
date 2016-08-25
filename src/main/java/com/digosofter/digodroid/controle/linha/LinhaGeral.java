package com.digosofter.digodroid.controle.linha;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.painel.PainelGeral;

public class LinhaGeral extends PainelGeral
{
  public enum EnmDisposicao
  {
    HORIZONTAL,
    VERTICAL,
  }

  private EnmDisposicao _enmDisposicao;

  public LinhaGeral(Context context)
  {
    super(context);

    this.iniciar(null);
  }

  public LinhaGeral(Context context, AttributeSet attrs)
  {
    super(context, attrs);

    this.iniciar(attrs);
  }

  public LinhaGeral(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);

    this.iniciar(attrs);
  }

  @Override
  public void finalizar()
  {
    super.finalizar();

    this.getLayoutParams().height = LayoutParams.MATCH_PARENT;
    this.getLayoutParams().width = LayoutParams.MATCH_PARENT;
    if (EnmDisposicao.HORIZONTAL.equals(this.getEnmDisposicao()))
    {
      this.getLayoutParams().height = 1;
      return;
    }
    this.getLayoutParams().width = 1;
  }

  private EnmDisposicao getEnmDisposicao()
  {
    return _enmDisposicao;
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
    super.inicializar(ats);
    TypedArray objTypedArray;

    if (ats == null)
    {
      return;
    }
    objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.LinhaGeral);
    this.setEnmDisposicao(this.intToEnmDisposicao(objTypedArray.getInt(R.styleable.LinhaGeral_enmDisposicao, 0)));
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.setBackgroundColor(this.getContext().getResources().getColor(R.color.cor_borda));
    this.setEnmDisposicao(EnmDisposicao.HORIZONTAL);
  }

  private EnmDisposicao intToEnmDisposicao(int intDisposicao)
  {
    return (intDisposicao == 0) ? EnmDisposicao.HORIZONTAL : EnmDisposicao.VERTICAL;
  }

  /**
   * Indica se a linha vai ficar disposta horizontalmente ou verticalmente.
   *
   * @param enmDisposicao Valor enumerado que indica se a linha é horizontal ou vertical.
   */
  public void setEnmDisposicao(EnmDisposicao enmDisposicao)
  {
    _enmDisposicao = enmDisposicao;
  }
}