package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.erro.Erro;

public class PainelGrupo extends PainelRelevo {

  private TextView _lblTitulo;
  private String _strTitulo;

  public PainelGrupo(Context context) {

    super(context);
  }

  public PainelGrupo(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public PainelGrupo(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
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

      this.setBackgroundColor(Color.LTGRAY);

      this.inicializarLblTitulo();

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void inicializarLblTitulo() {

    int intPadding;

    try {

      intPadding = UtilsAndroid.dpToPx(TemaDefault.getI().getIntPadding(), this.getContext());

      this.getLblTitulo().setBackgroundColor(TemaDefault.getI().getCorTema());
      this.getLblTitulo().setGravity(Gravity.CENTER_VERTICAL);
      this.getLblTitulo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(30, this.getContext())));
      this.getLblTitulo().setPadding(intPadding, 0, intPadding, 0);
      this.getLblTitulo().setTextColor(Color.WHITE);

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