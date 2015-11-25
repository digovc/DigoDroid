package com.digosofter.digodroid.controle.textbox;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.digosofter.digodroid.controle.ControleMain;
import com.digosofter.digojava.erro.Erro;

public abstract class TextBoxMain extends ControleMain {

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

  private EnmFormato _enmFormato;
  private EditText _txt;

  public TextBoxMain(Context context) {

    super(context);
  }

  public TextBoxMain(Context context, AttributeSet attrs) {

    super(context, attrs);

    this.montarLayout();
  }

  public TextBoxMain(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);

    this.montarLayout();
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

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void atualizarEnmFormatoAlfanumerico() {

    try {

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void atualizarEnmFormatoNumericoInteiro() {

    try {

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void atualizarEnmFormatoNumericoPontoFlutuante() {

    try {

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }

  }

  private EnmFormato getEnmFormato() {

    return _enmFormato;
  }

  protected EditText getTxt() {

    try {

      if (_txt != null) {

        return _txt;
      }

      _txt = new EditText(this.getContext());

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }

    return _txt;
  }

  @Override
  protected void inicializar() {

    super.inicializar();

    try {

      this.getTxt().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
      this.getTxt().setMaxLines(1);

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();

    try {

      this.addView(this.getTxt());

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  public void setEnmFormato(EnmFormato enmFormato) {

    try {

      _enmFormato = enmFormato;

      this.atualizarEnmFormato();

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }
}