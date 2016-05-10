package com.digosofter.digodroid.controle.botao;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.erro.ErroAndroid;

import at.markushi.ui.CircleButton;

public class BotaoCircular extends CircleButton implements IControleMain
{

  public BotaoCircular(final Context context)
  {
    super(context);
    try
    {
      this.iniciar(null);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public BotaoCircular(final Context context, final AttributeSet attrs)
  {
    super(context, attrs);
    try
    {
      this.iniciar(attrs);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public BotaoCircular(final Context context, final AttributeSet attrs, final int defStyle)
  {
    super(context, attrs, defStyle);
    try
    {
      this.iniciar(attrs);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
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
    try
    {
      this.inicializar();
      this.inicializar(ats);
      this.montarLayout();
      this.setEventos();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void montarLayout()
  {
  }

  @Override
  protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec)
  {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    try
    {
      this.finalizar();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void setEventos()
  {
  }
}