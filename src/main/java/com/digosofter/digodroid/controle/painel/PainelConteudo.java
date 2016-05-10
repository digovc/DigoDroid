package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;

public class PainelConteudo extends PainelGeral
{

  public PainelConteudo(Context context)
  {
    super(context);
  }

  public PainelConteudo(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public PainelConteudo(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void inicializar()
  {
    super.inicializar();
    int intPadding;
    try
    {
      intPadding = UtilsAndroid.dpToPx(10, this.getContext());
      this.setPadding(intPadding, intPadding, intPadding, intPadding);
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