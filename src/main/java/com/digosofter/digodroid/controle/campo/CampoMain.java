package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.controle.painel.PainelLinha;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public abstract class CampoMain extends PainelLinha {

  public static final String STR_TITULO_DESCONHECIDO = "<desconhecido>";
  private LabelGeral _lblTitulo;
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

  private LabelGeral getLblTitulo() {

    try {

      if (_lblTitulo != null) {

        return _lblTitulo;
      }

      _lblTitulo = new LabelGeral(this.getContext());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _lblTitulo;
  }

  private String getStrTitulo() {

    return _strTitulo;
  }

  @Override
  public void inicializar(AttributeSet ats) {

    super.inicializar(ats);

    TypedArray objTypedArray;

    try {

      if (ats == null) {

        return;
      }

      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.strTitulo);

      this.setStrTitulo(objTypedArray.getString(R.styleable.strTitulo_strTitulo));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.getLblTitulo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(30, this.getContext())));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void montarLayout() {

    super.montarLayout();

    try {

      this.addView(this.getLblTitulo());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void setStrTitulo(String strTitulo) {

    try {

      _strTitulo = strTitulo;

      this.getLblTitulo().setStrTexto((!Utils.getBooStrVazia(_strTitulo)) ? _strTitulo : "Desconhecido");

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }
}