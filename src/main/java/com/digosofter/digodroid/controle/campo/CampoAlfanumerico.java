package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digojava.OnValorAlteradoListener;
import com.digosofter.digojava.OnValorAlteradoArg;
import com.digosofter.digodroid.controle.textbox.TextBoxGeral;
import com.digosofter.digodroid.erro.ErroAndroid;

public class CampoAlfanumerico extends CampoMain implements OnValorAlteradoListener {

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
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txt;
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.inicializarTxt();

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void inicializarTxt() {

    try {

      this.getTxt().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void montarLayout() {

    super.montarLayout();

    try {

      this.addView(this.getTxt());

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onValorAlterado(final Object objSender, final OnValorAlteradoArg arg) {

    try {

      if (arg == null) {

        return;
      }

      if (objSender.equals(this.getTxt())) {

        this.setStrValor(arg.getStrValor());
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void receberFoco() {

    this.getTxt().receberFoco();
  }

  @Override
  public void setEventos() {

    super.setEventos();

    this.getTxt().addEvtOnValorAlteradoListener(this);
  }
}
