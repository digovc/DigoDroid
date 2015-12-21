package com.digosofter.digodroid.controle.linha;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.painel.PainelGeral;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;

public class LinhaGeral extends PainelGeral {

  public enum EnmDisposicao {

    HORIZONTAL,
    VERTICAL,
  }

  private EnmDisposicao _enmDisposicao;

  public LinhaGeral(Context context) {

    super(context);

    try {

      this.iniciar(null);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public LinhaGeral(Context context, AttributeSet attrs) {

    super(context, attrs);

    try {

      this.iniciar(attrs);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public LinhaGeral(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);

    try {

      this.iniciar(attrs);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void atualizarEnmDisposicao() {

    try {

      this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

      if (EnmDisposicao.HORIZONTAL.equals(this.getEnmDisposicao())) {

        this.getLayoutParams().height = 1;
        return;
      }

      this.getLayoutParams().width = 1;

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void finalizar() {

    super.finalizar();

    try {

      this.atualizarEnmDisposicao();

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private EnmDisposicao getEnmDisposicao() {

    return _enmDisposicao;
  }

  @Override
  public void inicializar(AttributeSet ats) {

    super.inicializar(ats);

    TypedArray objTypedArray;

    try {

      if (ats == null) {

        return;
      }

      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.LinhaGeral);

      this.setEnmDisposicao(this.intToEnmDisposicao(objTypedArray.getInt(R.styleable.LinhaGeral_enmDisposicao, 0)));

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.setBackgroundColor(TemaDefault.getI().getCorBorda1());
      this.setEnmDisposicao(EnmDisposicao.HORIZONTAL);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private EnmDisposicao intToEnmDisposicao(int intDisposicao) {

    return (intDisposicao == 0) ? EnmDisposicao.HORIZONTAL : EnmDisposicao.VERTICAL;
  }

  /**
   * Indica se a linha vai ficar disposta horizontalmente ou verticalmente.
   *
   * @param enmDisposicao Valor enumerado que indica se a linha é horizontal ou vertical.
   */
  public void setEnmDisposicao(EnmDisposicao enmDisposicao) {

    _enmDisposicao = enmDisposicao;
  }
}