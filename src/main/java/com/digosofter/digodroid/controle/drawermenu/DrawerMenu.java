package com.digosofter.digodroid.controle.drawermenu;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

import com.digosofter.digodroid.erro.ErroAndroid;

public final class DrawerMenu extends DrawerLayout {

  public DrawerMenu(Context context) {

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

  public DrawerMenu(Context context, AttributeSet attrs) {

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

  public DrawerMenu(Context context, AttributeSet attrs, int defStyle) {

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

  protected void finalizar() {

  }

  /**
   * Chamado de dentro do construtor para fazer qualquer inicialização que seja necessária neste controle. Neste momento
   * do ciclo os parâmetros de layout do controle ainda não foram carregados, portanto a propriedade {@link
   * android.view.ViewGroup.LayoutParams} não está inicializada.
   */
  protected void inicializar() {

  }

  /**
   * Chamado de dentro do construtor para fazer qualquer inicialização que seja necessária neste controle.
   *
   * @param ats Conjunto de atributos que foram declarados no XML de layout que contém este controle.
   */
  protected void inicializar(AttributeSet ats) {

  }

  private void iniciar(AttributeSet ats) {

    try {

      this.inicializar(ats);
      this.inicializar();
      this.montarLayout();
      this.setEventos();
      this.finalizar();

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void montarLayout() {

  }

  /**
   * Responsável por configurar os eventos deste controle e de seus filhos.
   */
  protected void setEventos() {

  }
}