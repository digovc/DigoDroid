package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.textbox.TextBoxGeral;
import com.digosofter.digojava.erro.Erro;

public class CampoAlfanumerico extends CampoMain {

  private TextBoxGeral _txt;

  public CampoAlfanumerico(Context context) {

    super(context);
  }

  public CampoAlfanumerico(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public CampoAlfanumerico(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  protected TextBoxGeral getTxt() {

    try {

      if (_txt != null) {

        return _txt;
      }

      _txt = new TextBoxGeral(this.getContext());

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

      this.inicializarTxt();

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void inicializarTxt() {

    try {

      this.getTxt().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

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
}
