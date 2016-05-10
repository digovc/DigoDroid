package com.digosofter.digodroid.controle.label;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;

public class LabelGeral extends TextView implements IControleMain
{

  private int _corTexto;
  private TemaDefault.EnmFonteTamanho _enmFonteTamanho;
  private int _intTexto;

  public LabelGeral(Context context)
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

  public LabelGeral(Context context, AttributeSet attrs)
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

  public LabelGeral(Context context, AttributeSet attrs, int defStyleAttr)
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

  private void atualizarCorTexto()
  {
    try
    {
      this.setTextColor(this.getCorTexto());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void atualizarEnmFonteTamanho()
  {
    try
    {
      this.setTextSize(TypedValue.COMPLEX_UNIT_SP, TemaDefault.getI().enmFonteTamanhoToInt(this.getEnmFonteTamanho()));
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
    finally
    {
    }
    return _intTexto;
  }

  @Override
  public void inicializar()
  {
    try
    {
      this.setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.NORMAL);
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
  public void inicializar(AttributeSet ats)
  {
    int intFonteTamanho;
    TypedArray objTypedArray;
    try
    {
      if (ats == null)
      {
        return;
      }
      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.LabelGeral);
      intFonteTamanho = objTypedArray.getInt(R.styleable.LabelGeral_enmFonteTamanho, 2);
      this.setEnmFonteTamanho(TemaDefault.getI().intToEnmFonteTamanho(intFonteTamanho));
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

  /**
   * Indica a cor do texto.
   *
   * @param corTexto Cor do texto.
   */
  public void setCorTexto(int corTexto)
  {
    try
    {
      _corTexto = corTexto;
      this.atualizarCorTexto();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void setEnmFonteTamanho(TemaDefault.EnmFonteTamanho enmFonteTamanho)
  {
    try
    {
      _enmFonteTamanho = enmFonteTamanho;
      this.atualizarEnmFonteTamanho();
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

  public void setIntTexto(int intTexto)
  {
    try
    {
      _intTexto = intTexto;
      this.setText(String.valueOf(_intTexto));
    }
    catch (Exception ex)
    {
      this.setText(null);
    }
    finally
    {
    }
  }
}