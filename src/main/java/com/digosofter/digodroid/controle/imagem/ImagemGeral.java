package com.digosofter.digodroid.controle.imagem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.erro.ErroAndroid;

public class ImagemGeral extends ImageView implements IControleMain {

  public ImagemGeral(Context context) {

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

  public ImagemGeral(Context context, AttributeSet attrs) {

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

  public ImagemGeral(Context context, AttributeSet attrs, int defStyleAttr) {

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

  @Override
  public void finalizar() {

  }

  @Override
  public void inicializar(AttributeSet ats) {

  }

  @Override
  public void inicializar() {

    try {

      this.setScaleType(ScaleType.FIT_XY);

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

  @Override
  public void setEventos() {

  }
}