package com.digosofter.digodroid.componente.textbox;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;

import com.digosofter.digodroid.componente.IComponenteMain;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.OnValorAlteradoArg;
import com.digosofter.digojava.OnValorAlteradoListener;

import java.util.ArrayList;
import java.util.List;

public class TextBoxGeral extends EditText implements IComponenteMain
{

  public enum EnmFormato
  {
    /**
     * Permite a entrada de qualquer tecla.
     */
    ALFANUMERICO,

    /**
     * Permite a entrada apenas de valores numéricos inteiros (sem casas decimais).
     */
    NUMERICO_INTEIRO,

    /**
     * Permite a entrada apenas de valores numéricos que possuam casas decimais.
     */
    NUMERICO_PONTO_FLUTUANTE,
  }

  private boolean _booSomenteLeitura;
  private boolean _booValor;
  private double _dblValor;
  private EnmFormato _enmFormato;
  private int _intValor;
  private List<OnValorAlteradoListener> _lstEvtOnValorAlteradoListener;
  private String _strValor;
  private String _strValorAnterior;

  public TextBoxGeral(Context context)
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

  public TextBoxGeral(Context context, AttributeSet attrs)
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

  public TextBoxGeral(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
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

  public void addEvtOnValorAlteradoListener(OnValorAlteradoListener evt)
  {
    try
    {
      if (evt == null)
      {
        return;
      }
      if (this.getLstEvtOnValorAlteradoListener().contains(evt))
      {
        return;
      }
      this.getLstEvtOnValorAlteradoListener().add(evt);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void atualizarEnmFormato()
  {
    try
    {
      switch (this.getEnmFormato())
      {
        case ALFANUMERICO:
          this.atualizarEnmFormatoAlfanumerico();
          return;
        case NUMERICO_INTEIRO:
          this.atualizarEnmFormatoNumericoInteiro();
          return;
        case NUMERICO_PONTO_FLUTUANTE:
          this.atualizarEnmFormatoNumericoPontoFlutuante();
          return;
      }
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void atualizarEnmFormatoAlfanumerico()
  {
    try
    {
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void atualizarEnmFormatoNumericoInteiro()
  {
    try
    {
      this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void atualizarEnmFormatoNumericoPontoFlutuante()
  {
    try
    {
      this.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void atualizarStrValor()
  {
    try
    {
      if (!((this.getStrValor() != null) ? this.getStrValor().equals(this.getText().toString()) : (this.getText().toString() == null)))
      {
        this.setText(this.getStrValor());
        return;
      }
      this.dispararEvtOnValorAlteradoListener();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void dispararEvtOnValorAlteradoListener()
  {
    OnValorAlteradoArg arg;
    try
    {
      if (this.getLstEvtOnValorAlteradoListener().isEmpty())
      {
        return;
      }
      if ((this.getStrValor() != null) ? (this.getStrValor().equals(this.getStrValorAnterior())) : (this.getStrValorAnterior() == null))
      {
        return;
      }
      arg = new OnValorAlteradoArg();
      arg.setStrValor(this.getStrValor());
      arg.setStrValorAnterior(this.getStrValorAnterior());
      for (OnValorAlteradoListener evt : this.getLstEvtOnValorAlteradoListener())
      {
        if (evt == null)
        {
          continue;
        }
        evt.onValorAlterado(this, arg);
      }
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

  private boolean getBooSomenteLeitura()
  {
    return _booSomenteLeitura;
  }

  public boolean getBooValor()
  {
    try
    {
      _booValor = Boolean.valueOf(this.getStrValor());
    }
    catch (Exception ex)
    {
      _booValor = false;
    }
    finally
    {
    }
    return _booValor;
  }

  public double getDblValor()
  {
    try
    {
      _dblValor = Double.valueOf(this.getStrValor());
    }
    catch (Exception ex)
    {
      _dblValor = 0;
    }
    finally
    {
    }
    return _dblValor;
  }

  private EnmFormato getEnmFormato()
  {
    return _enmFormato;
  }

  public int getIntValor()
  {
    return _intValor = (int) this.getDblValor();
  }

  private List<OnValorAlteradoListener> getLstEvtOnValorAlteradoListener()
  {
    try
    {
      if (_lstEvtOnValorAlteradoListener != null)
      {
        return _lstEvtOnValorAlteradoListener;
      }
      _lstEvtOnValorAlteradoListener = new ArrayList<>();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _lstEvtOnValorAlteradoListener;
  }

  public String getStrValor()
  {
    return _strValor;
  }

  private String getStrValorAnterior()
  {
    return _strValorAnterior;
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
  }

  @Override
  public void inicializar()
  {
    try
    {
      this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
      this.setMaxLines(1);
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
  }

  /**
   * Limpa o texto atual deste componente.
   */
  public void limparTexto()
  {
    try
    {
      this.setText(null);
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
  protected void onTextChanged(final CharSequence objCharSequence, final int intStart, final int intLengthBefore, final int intLengthAfter)
  {
    super.onTextChanged(objCharSequence, intStart, intLengthBefore, intLengthAfter);
    try
    {
      this.setStrValor((objCharSequence != null) ? objCharSequence.toString() : null);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void receberFoco()
  {
    try
    {
      this.post(new Runnable()
      {
        @Override
        public void run()
        {
          TextBoxGeral.this.requestFocus();
        }
      });
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void removerEvtOnValorAlteradoListener(OnValorAlteradoListener evt)
  {
    try
    {
      if (evt == null)
      {
        return;
      }
      this.getLstEvtOnValorAlteradoListener().remove(evt);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void setBooSomenteLeitura(boolean booSomenteLeitura)
  {
    try
    {
      _booSomenteLeitura = booSomenteLeitura;
      // TODO: Implementar somente leitura.
      //      this.atualizarBooSomenteLeitura();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void setBooValor(boolean booValor)
  {
    try
    {
      _booValor = booValor;
      this.setStrValor(String.valueOf(_booValor));
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void setDblValor(double dblValor)
  {
    try
    {
      _dblValor = dblValor;
      this.setStrValor(String.valueOf(_dblValor));
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void setEnmFormato(EnmFormato enmFormato)
  {
    try
    {
      _enmFormato = enmFormato;
      this.atualizarEnmFormato();
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

  public void setIntValor(int intValor)
  {
    try
    {
      _intValor = intValor;
      this.setDblValor(_intValor);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void setStrValor(String strValor)
  {
    try
    {
      this.setStrValorAnterior(_strValor);
      _strValor = strValor;
      this.atualizarStrValor();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void setStrValorAnterior(String strValorAnterior)
  {
    _strValorAnterior = strValorAnterior;
  }
}