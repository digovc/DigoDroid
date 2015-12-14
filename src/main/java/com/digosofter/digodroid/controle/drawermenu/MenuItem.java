package com.digosofter.digodroid.controle.drawermenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.controle.imagem.ImagemGeral;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.controle.painel.PainelGeral;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;

public class MenuItem extends PainelGeral implements View.OnClickListener {

  private static final int INT_MENU_ITEM_HEIGHT = 60;

  private ImagemGeral _imgIcone;
  private LabelGeral _lblTitulo;

  private String _strTitulo;

  public MenuItem(Context context) {

    super(context);
  }

  public MenuItem(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public MenuItem(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  @Override
  public void finalizar() {

    super.finalizar();

    try {

      this.getLayoutParams().height = UtilsAndroid.dpToPx(INT_MENU_ITEM_HEIGHT, this.getContext());
      this.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private ImagemGeral getImgIcone() {

    try {

      if (_imgIcone != null) {

        return _imgIcone;
      }

      _imgIcone = new ImagemGeral(this.getContext());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _imgIcone;
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

  public String getStrTitulo() {

    return _strTitulo;
  }

  @Override
  public void inicializar(AttributeSet ats) {

    super.inicializar(ats);

    try {

      this.inicializarImgIcone(ats);
      this.inicializarLblTitulo(ats);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.setOrientation(LinearLayout.HORIZONTAL);

      this.inicializarImgIcone();
      this.inicializarLblTitulo();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void inicializarImgIcone(AttributeSet ats) {

    TypedArray objTypedArray;

    try {

      if (ats == null) {

        return;
      }

      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.MenuItem);

      this.getImgIcone().setImageDrawable(objTypedArray.getDrawable(R.styleable.MenuItem_srcIcone));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void inicializarImgIcone() {

    int intTamanhoDp;

    try {

      intTamanhoDp = UtilsAndroid.dpToPx(INT_MENU_ITEM_HEIGHT, this.getContext());

      this.getImgIcone().setLayoutParams(new ViewGroup.LayoutParams(intTamanhoDp, intTamanhoDp));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void inicializarLblTitulo(AttributeSet ats) {

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

  private void inicializarLblTitulo() {

    try {

      this.getLblTitulo().setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.PEQUENO);
      this.getLblTitulo().setGravity(Gravity.CENTER_VERTICAL);
      this.getLblTitulo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

  }

  @Override
  public void montarLayout() {

    super.montarLayout();

    try {

      this.addView(this.getImgIcone());
      this.addView(this.getLblTitulo());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void onClick(View v) {

    try {

      AppAndroid.getI().onMenuItemClick(this);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void setEventos() {

    super.setEventos();

    try {

      this.setOnClickListener(this);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void setStrTitulo(String strTitulo) {

    try {

      _strTitulo = strTitulo;

      this.getLblTitulo().setText(_strTitulo);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }
}