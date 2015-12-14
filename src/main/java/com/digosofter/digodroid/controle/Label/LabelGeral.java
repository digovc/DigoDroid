package com.digosofter.digodroid.controle.label;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;

public class LabelGeral extends TextView implements IControleMain {

  private int _corTexto;
  private TemaDefault.EnmFonteTamanho _enmFonteTamanho;
  private int _intLinhaQuantidade;
  private int _intTexto;
  private String _strTexto;

  public LabelGeral(Context context) {

    super(context);

    try {

      this.iniciar(null);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  public LabelGeral(Context context, AttributeSet attrs) {

    super(context, attrs);

    try {

      this.iniciar(null);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  public LabelGeral(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);

    try {

      this.iniciar(null);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void atualizarCorTexto() {

    try {

      this.setTextColor(this.getCorTexto());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void atualizarEnmFonteTamanho() {

    try {

      this.setTextSize(TypedValue.COMPLEX_UNIT_SP, TemaDefault.getI().enmFonteTamanhoToInt(this.getEnmFonteTamanho()));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

  }

  protected void atualizarIntLinhaQuantidade() {

    try {

      this.setMaxLines(this.getIntLinhaQuantidade());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void finalizar() {

  }

  private int getCorTexto() {

    return _corTexto;
  }

  private TemaDefault.EnmFonteTamanho getEnmFonteTamanho() {

    return _enmFonteTamanho;
  }

  private int getIntLinhaQuantidade() {

    return _intLinhaQuantidade;
  }

  public int getIntTexto() {

    try {

      _intTexto = Integer.valueOf(this.getStrTexto());

    } catch (Exception ex) {

      return 0;
    } finally {
    }

    return _intTexto;
  }

  public String getStrTexto() {

    try {

      _strTexto = this.getText().toString();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _strTexto;
  }

  @Override
  public void inicializar() {

    try {

      this.setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.NORMAL);

      this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void inicializar(AttributeSet ats) {

    int intFonteTamanho;
    TypedArray objTypedArray;

    try {

      if (ats == null) {

        return;
      }

      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.LabelGeral);

      intFonteTamanho = objTypedArray.getInt(R.styleable.LabelGeral_enmFonteTamanho, 2);

      this.setEnmFonteTamanho(TemaDefault.getI().intToEnmFonteTamanho(intFonteTamanho));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void iniciar(AttributeSet ats) {

    try {

      this.inicializar();
      this.inicializar(ats);
      this.montarLayout();
      this.setEventos();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void montarLayout() {

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    try {

      this.finalizar();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  /**
   * Indica a cor do texto.
   *
   * @param corTexto Cor do texto.
   */
  public void setCorTexto(int corTexto) {

    try {

      _corTexto = corTexto;

      this.atualizarCorTexto();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  public void setEnmFonteTamanho(TemaDefault.EnmFonteTamanho enmFonteTamanho) {

    try {

      _enmFonteTamanho = enmFonteTamanho;

      this.atualizarEnmFonteTamanho();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void setEventos() {

  }

  /**
   * Indica a quantidade máxima de linhas que este controle pode ter.
   *
   * @param intLinhaQuantidade Quantidade máxima de linhas que este controle pode ter.
   */
  public void setIntLinhaQuantidade(int intLinhaQuantidade) {

    try {

      _intLinhaQuantidade = intLinhaQuantidade;

      this.atualizarIntLinhaQuantidade();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  public void setIntTexto(int intTexto) {

    try {

      _intTexto = intTexto;

      this.setStrTexto(String.valueOf(_intTexto));

    } catch (Exception ex) {

      this.setStrTexto(null);
    } finally {
    }
  }

  public void setStrTexto(String strTexto) {

    try {

      _strTexto = strTexto;

      this.setText(_strTexto);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }
}