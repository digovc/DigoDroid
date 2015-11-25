package com.digosofter.digodroid.controle;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.digosofter.digojava.erro.Erro;

public abstract class ControleMain extends LinearLayout {

  public ControleMain(Context context) {

    super(context);

    try {

      this.inicializarLocal(null);

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  public ControleMain(Context context, AttributeSet attrs) {

    super(context, attrs);

    try {

      this.inicializarLocal(attrs);

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  public ControleMain(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);

    try {

      this.inicializar(attrs);

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  /**
   * Responsável por fazer os ajustes finais antes de desenhar este controle na tela.
   */
  protected void finalizar() {

  }

  /**
   * Chamado de dentro do construtor para fazer qualquer inicialização que seja necessária neste controle.
   *
   * @param ats Conjunto de atributos que foram declarados no XML de layout que contém este controle.
   */
  protected void inicializar(AttributeSet ats) {

  }

  /**
   * Chamado de dentro do construtor para fazer qualquer inicialização que seja necessária neste controle.
   * Neste momento do ciclo os parâmetros de layout do controle ainda não foram carregados, portanto a propriedade
   * {@link android.view.ViewGroup.LayoutParams} não está inicializada.
   */
  protected void inicializar() {

    try {

      this.setOrientation(LinearLayout.VERTICAL);

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void inicializarLocal(AttributeSet ats) {

    try {

      this.inicializar(ats);
      this.inicializar();
      this.montarLayout();
      this.setEventos();

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  /**
   * Responsável por montar o layout dos controles filhos deste, caso seja um controle complexo.
   */
  protected void montarLayout() {

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    try {

      this.finalizar();

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  /**
   * Responsável por configurar os eventos deste controle e de seus filhos.
   */
  protected void setEventos() {

  }
}
