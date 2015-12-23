package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public class PainelGrupo extends PainelRelevo {

  private LabelGeral _lblTitulo;
  private String _strTitulo = "<desconhecido>";

  public PainelGrupo(Context context) {

    super(context);
  }

  public PainelGrupo(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public PainelGrupo(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  private void atualizarStrTitulo() {

    try {

      this.getLblTitulo().setStrTexto((!Utils.getBooStrVazia(_strTitulo)) ? _strTitulo : "<desconhecido>");

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

  }

  private LabelGeral getLblTitulo() {

    try {

      if (_lblTitulo != null) {

        return _lblTitulo;
      }

      _lblTitulo = new LabelGeral(this.getContext());

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lblTitulo;
  }

  public String getStrTitulo() {

    return _strTitulo;
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.setBackgroundColor(Color.LTGRAY);

      this.inicializarLblTitulo();

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void inicializar(AttributeSet ats) {

    super.inicializar(ats);

    TypedArray objTypedArray;

    try {

      if (ats == null) {

        return;
      }

      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.View);

      this.setStrTitulo(objTypedArray.getString(R.styleable.View_strTitulo));

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void inicializarLblTitulo() {

    int intPadding;

    try {

      intPadding = UtilsAndroid.dpToPx(TemaDefault.getI().getIntPadding(), this.getContext());

      this.getLblTitulo().setBackgroundColor(this.getContext().getResources().getColor(R.color.cor_tema, null));
      this.getLblTitulo().setGravity(Gravity.CENTER_VERTICAL);
      this.getLblTitulo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(30, this.getContext())));
      this.getLblTitulo().setPadding(intPadding, 0, intPadding, 0);
      this.getLblTitulo().setCorTexto(Color.WHITE);
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

      this.addView(this.getLblTitulo());

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void setStrTitulo(String strTitulo) {

    try {

      _strTitulo = strTitulo;

      this.atualizarStrTitulo();

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}