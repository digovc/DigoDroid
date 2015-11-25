package com.digosofter.digodroid.controle.checkbox;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.digosofter.digodroid.controle.ControleMain;
import com.digosofter.digojava.erro.Erro;

public abstract class CheckBoxMain extends ControleMain {

  private CheckBox _ckb;

  public CheckBoxMain(Context context) {

    super(context);
  }

  public CheckBoxMain(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public CheckBoxMain(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  private CheckBox getCkb() {

    try {

      if (_ckb != null) {

        return _ckb;
      }

      _ckb = new CheckBox(this.getContext());
    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }

    return _ckb;
  }

  @Override
  protected void inicializar() {

    super.inicializar();

    try {

      this.inicializarCkb();

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void inicializarCkb() {

    try {

      this.getCkb().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();

    try {

      this.addView(this.getCkb());

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }
}