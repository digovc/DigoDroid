package com.digosofter.digodroid.controle.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.erro.ErroAndroid;

public class ScrollGeral extends ScrollView implements IControleMain
{

  public ScrollGeral(Context context)
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

  public ScrollGeral(Context context, AttributeSet attrs)
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

  public ScrollGeral(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
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