package com.digosofter.digodroid.controle.radiobutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.controle.campo.CampoMain;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public class RadioButtonGeral extends RadioButton implements IControleMain {

  private String _strTitulo;

  public RadioButtonGeral(Context context) {

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

  public RadioButtonGeral(Context context, AttributeSet attrs) {

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

  public RadioButtonGeral(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);

    try {

      this.iniciar(attrs);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void atualizarStrTitulo() {

    try {

      this.setText((!Utils.getBooStrVazia(this.getStrTitulo())) ? this.getStrTitulo() : CampoMain.STR_TITULO_DESCONHECIDO);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

  }

  @Override
  public void finalizar() {

  }

  private String getStrTitulo() {

    return _strTitulo;
  }

  @Override
  public void inicializar(AttributeSet ats) {

  }

  @Override
  public void inicializar() {

    try {

      this.setStrTitulo(CampoMain.STR_TITULO_DESCONHECIDO);

      this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void iniciar(AttributeSet ats) {

    try {

      this.inicializar();
      this.inicializar(ats);
      this.montarLayout();
      this.setEventos();

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void montarLayout() {

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    try {

      this.finalizar();

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

  /**
   * Indica o título deste controle.
   *
   * @param strTitulo Título deste controle.
   */
  public void setStrTitulo(String strTitulo) {

    try {

      _strTitulo = strTitulo;

      this.atualizarStrTitulo();

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}