package com.digosofter.digodroid.controle.progressbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.erro.ErroAndroid;

public class ProgressBarGeral extends ProgressBar implements IControleMain {

  public ProgressBarGeral(Context context) {

    super(context, null, android.R.attr.progressBarStyleHorizontal);

    try {

      this.iniciar(null);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public ProgressBarGeral(Context context, AttributeSet attrs) {

    super(context, attrs, android.R.attr.progressBarStyleHorizontal);

    try {

      this.iniciar(attrs);
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

  @Override
  public void inicializar(AttributeSet ats) {

  }

  @Override
  public void inicializar() {

    try {

      this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(10, this.getContext())));
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
  protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

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
}