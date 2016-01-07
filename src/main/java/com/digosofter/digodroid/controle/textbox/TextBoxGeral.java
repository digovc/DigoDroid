package com.digosofter.digodroid.controle.textbox;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;

import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.erro.ErroAndroid;

public class TextBoxGeral extends EditText implements IControleMain {

  public enum EnmFormato {

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
  private EnmFormato _enmFormato;

  public TextBoxGeral(Context context) {

    super(context);

    try {

      this.iniciar(null);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public TextBoxGeral(Context context, AttributeSet attrs) {

    super(context, attrs);

    try {

      this.iniciar(attrs);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public TextBoxGeral(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);

    try {

      this.iniciar(null);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void atualizarEnmFormato() {

    try {

      switch (this.getEnmFormato()) {

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
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void atualizarEnmFormatoAlfanumerico() {

    try {

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void atualizarEnmFormatoNumericoInteiro() {

    try {

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void atualizarEnmFormatoNumericoPontoFlutuante() {

    try {

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void destruir() {

  }

  @Override
  public void finalizar() {

  }

  private boolean getBooSomenteLeitura() {

    return _booSomenteLeitura;
  }

  private EnmFormato getEnmFormato() {

    return _enmFormato;
  }

  @Override
  public void inicializar(AttributeSet ats) {

  }

  @Override
  public void inicializar() {

    try {

      this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
      this.setMaxLines(1);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void iniciar(AttributeSet ats) {

  }

  @Override
  public void montarLayout() {

  }

  public void setBooSomenteLeitura(boolean booSomenteLeitura) {

    try {

      _booSomenteLeitura = booSomenteLeitura;

      // TODO: Implementar somente leitura.
      //      this.atualizarBooSomenteLeitura();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void setEnmFormato(EnmFormato enmFormato) {

    try {

      _enmFormato = enmFormato;

      this.atualizarEnmFormato();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void setEventos() {

  }
}