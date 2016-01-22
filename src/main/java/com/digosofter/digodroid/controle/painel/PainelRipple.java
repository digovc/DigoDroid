package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.util.AttributeSet;

import com.balysv.materialripple.MaterialRippleLayout;
import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.erro.ErroAndroid;

public class PainelRipple extends MaterialRippleLayout implements IControleMain {

  public PainelRipple(final Context context) {

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

  public PainelRipple(final Context context, final AttributeSet attrs) {

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

  public PainelRipple(final Context context, final AttributeSet attrs, final int defStyle) {

    super(context, attrs, defStyle);

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
  public void inicializar(final AttributeSet ats) {

  }

  @Override
  public void inicializar() {

  }

  @Override
  public void iniciar(final AttributeSet ats) {

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
  protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {

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