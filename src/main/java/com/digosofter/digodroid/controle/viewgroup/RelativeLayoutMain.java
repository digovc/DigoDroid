package com.digosofter.digodroid.controle.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.erro.ErroAndroid;

public abstract class RelativeLayoutMain extends RelativeLayout implements IControleMain
{

  public RelativeLayoutMain(Context context)
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

  public RelativeLayoutMain(Context context, AttributeSet attrs)
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

  public RelativeLayoutMain(Context context, AttributeSet attrs, int defStyleAttr)
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
    //    TypedArray objTypedArray;
    //
    //    try {
    //
    //      if (ats == null) {
    //
    //        return;
    //      }
    //
    //      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.ControleMain);
    //
    //      this.setStrTitulo(objTypedArray.getString(R.styleable.ControleMain_strTitulo));
    //
    //    } catch (Exception ex) {
    //
    //      new ErroAndroid("Erro inesperado.\n", ex);
    //    } finally {
    //    }
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
      this.inicializar(ats);
      this.inicializar();
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