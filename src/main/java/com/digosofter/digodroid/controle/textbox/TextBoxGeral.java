package com.digosofter.digodroid.controle.textbox;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;

import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digojava.OnValorAlteradoArg;
import com.digosofter.digojava.OnValorAlteradoListener;
import com.digosofter.digojava.Utils;

import java.util.ArrayList;
import java.util.List;

public class TextBoxGeral extends EditText implements IControleMain
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

  public TextBoxGeral(Context cnt)
  {
    super(cnt);

    this.iniciar(null);
  }

  public TextBoxGeral(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);

    this.iniciar(atr);
  }

  public TextBoxGeral(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);

    this.iniciar(null);
  }

  public void addEvtOnValorAlteradoListener(OnValorAlteradoListener evt)
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

  private void atualizarEnmFormato(final EnmFormato enmFormato)
  {
    switch (enmFormato)
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

  private void atualizarEnmFormatoAlfanumerico()
  {
  }

  private void atualizarEnmFormatoNumericoInteiro()
  {
    this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
  }

  private void atualizarEnmFormatoNumericoPontoFlutuante()
  {
    this.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
  }

  private void atualizarStrValor(final String strValor)
  {
    if (!((strValor != null) ? strValor.equals(this.getText().toString()) : (this.getText().toString() == null)))
    {
      this.setText(strValor);
      return;
    }

    this.dispararEvtOnValorAlteradoListener();
  }

  private void dispararEvtOnValorAlteradoListener()
  {
    if (this.getLstEvtOnValorAlteradoListener().isEmpty())
    {
      return;
    }

    if ((this.getStrValor() != null) ? (this.getStrValor().equals(this.getStrValorAnterior())) : (this.getStrValorAnterior() == null))
    {
      return;
    }

    OnValorAlteradoArg arg = new OnValorAlteradoArg();

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
    _booValor = Utils.getBoo(this.getStrValor());

    return _booValor;
  }

  public double getDblValor()
  {
    if (Utils.getBooStrVazia(this.getStrValor()))
    {
      return 0;
    }

    _dblValor = Double.valueOf(this.getStrValor());

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
    if (_lstEvtOnValorAlteradoListener != null)
    {
      return _lstEvtOnValorAlteradoListener;
    }

    _lstEvtOnValorAlteradoListener = new ArrayList<>();

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
    this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    this.setMaxLines(1);
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
    this.setText(null);
  }

  @Override
  public void montarLayout()
  {
  }

  @Override
  protected void onTextChanged(final CharSequence objCharSequence, final int intStart, final int intLengthBefore, final int intLengthAfter)
  {
    super.onTextChanged(objCharSequence, intStart, intLengthBefore, intLengthAfter);

    this.setStrValor((objCharSequence != null) ? objCharSequence.toString() : null);
  }

  public void receberFoco()
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

  public void removerEvtOnValorAlteradoListener(OnValorAlteradoListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnValorAlteradoListener().remove(evt);
  }

  public void setBooSomenteLeitura(boolean booSomenteLeitura)
  {
    _booSomenteLeitura = booSomenteLeitura;

    this.setEnabled(!_booSomenteLeitura);
  }

  public void setBooValor(boolean booValor)
  {
    _booValor = booValor;

    this.setStrValor(String.valueOf(_booValor));
  }

  public void setDblValor(double dblValor)
  {
    _dblValor = dblValor;

    this.setStrValor(String.valueOf(_dblValor));
  }

  public void setEnmFormato(EnmFormato enmFormato)
  {
    if (_enmFormato == enmFormato)
    {
      return;
    }

    _enmFormato = enmFormato;

    this.atualizarEnmFormato(enmFormato);
  }

  @Override
  public void setEventos()
  {
  }

  public void setIntValor(int intValor)
  {
    _intValor = intValor;

    this.setDblValor(_intValor);
  }

  public void setStrValor(String strValor)
  {
    if (_strValor == strValor)
    {
      return;
    }

    this.setStrValorAnterior(_strValor);

    _strValor = strValor;

    this.atualizarStrValor(strValor);
  }

  private void setStrValorAnterior(String strValorAnterior)
  {
    _strValorAnterior = strValorAnterior;
  }
}