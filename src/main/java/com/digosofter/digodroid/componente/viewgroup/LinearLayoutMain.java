package com.digosofter.digodroid.componente.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.componente.IComponenteMain;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;

public abstract class LinearLayoutMain extends LinearLayout implements IComponenteMain
{

  public LinearLayoutMain(Context context)
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

  public LinearLayoutMain(Context context, AttributeSet attrs)
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

  public LinearLayoutMain(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
    try
    {
      this.inicializar(attrs);
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
    this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
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
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
  {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    this.finalizar();
  }

  @Override
  public void setEventos()
  {
  }

  /**
   * Insere espaçamento interno neste componente.
   *
   * @param intPaddingPx Valor em "pixels" que será convertido em "density pixels" e indicará o espaçamento interno.
   */
  public void setIntPadding(int intPaddingPx)
  {
    try
    {
      intPaddingPx = UtilsAndroid.dpToPx(TemaDefault.getI().getIntEspacamento(), this.getContext());
      this.setPadding(intPaddingPx, intPaddingPx, intPaddingPx, intPaddingPx);
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