package com.digosofter.digodroid.componente.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.erro.ErroAndroid;

/**
 * Created by Rodrigo on 02/12/2015.
 */
public class PainelDetalheCabecalho extends PainelGeral
{

  public PainelDetalheCabecalho(Context context)
  {
    super(context);
  }

  public PainelDetalheCabecalho(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public PainelDetalheCabecalho(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void inicializar()
  {
    super.inicializar();
    try
    {
      this.setBackgroundColor(this.getContext().getResources().getColor(R.color.cor_tema));
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }
}