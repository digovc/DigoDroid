package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.controle.ControleMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.erro.Erro;

public abstract class CampoMain extends ControleMain {

  private TextView _lblTitulo;
  private String _strTitulo;

  public CampoMain(Context context) {

    super(context);
  }

  public CampoMain(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public CampoMain(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void finalizar() {

    super.finalizar();

    try {

      this.getLayoutParams().height = LayoutParams.MATCH_PARENT;
      this.getLayoutParams().width = LayoutParams.MATCH_PARENT;

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private TextView getLblTitulo() {

    try {

      if (_lblTitulo != null) {

        return _lblTitulo;
      }

      _lblTitulo = new TextView(this.getContext());

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }

    return _lblTitulo;
  }

  private String getStrTitulo() {

    return _strTitulo;
  }

  protected void inicializar(AttributeSet ats) {

    super.inicializar(ats);

    TypedArray objTypedArray;

    try {

      if (ats == null) {

        return;
      }

      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.ControleMain);

      this.setStrTitulo(objTypedArray.getString(R.styleable.ControleMain_strTitulo));

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  protected void inicializar() {

    super.inicializar();

    try {

      this.getLblTitulo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(30, this.getContext())));

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();

    try {

      this.addView(this.getLblTitulo());

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void setStrTitulo(String strTitulo) {

    try {

      _strTitulo = strTitulo;

      this.getLblTitulo().setText((!Utils.getBooStrVazia(_strTitulo)) ? _strTitulo : "Desconhecido");

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }
}
