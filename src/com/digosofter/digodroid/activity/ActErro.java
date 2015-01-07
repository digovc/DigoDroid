package com.digosofter.digodroid.activity;

import android.view.View;
import android.widget.TextView;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.erro.Erro;

public class ActErro extends ActMain {

  private static boolean _booIgnorarTodos;
  private static int _intQtdErroVisivel;

  private ErroAndroid _err;
  private TextView _txtAppNome;
  private TextView _txtErroMensagem;
  private TextView _txtErroTitulo;

  public ActErro() {

    try {

      this.setIntQtdErroVisivel(this.getIntQtdErroVisivel() + 1);
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void actErro_btnIgnorar_onClick(View viw) {

    try {

      this.finish();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  public void actErro_btnIgnorarTodos_onClick(View viw) {

    try {

      this.setbooIgnorarTodos(true);
      this.finish();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private boolean getBooIgnorarTodos() {

    return _booIgnorarTodos;
  }

  public ErroAndroid getErr() {

    try {

      if (_err != null) {

        return _err;
      }

      _err = (ErroAndroid) this.getIntent().getSerializableExtra("Erro");
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _err;
  }

  @Override
  protected int getIntLayoutId() {

    return R.layout.act_erro;
  }

  private int getIntQtdErroVisivel() {

    return _intQtdErroVisivel;
  }

  private TextView getTxtAppNome() {

    try {

      if (_txtAppNome != null) {

        return _txtAppNome;
      }

      _txtAppNome = this.getTextView(R.id.actErro_txtAppNome);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _txtAppNome;
  }

  private TextView getTxtErroMensagem() {

    try {

      if (_txtErroMensagem != null) {

        return _txtErroMensagem;
      }

      _txtErroMensagem = this.getTextView(R.id.actErro_txtErroMensagem);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _txtErroMensagem;
  }

  private TextView getTxtErroTitulo() {

    try {

      if (_txtErroTitulo != null) {

        return _txtErroTitulo;
      }

      _txtErroTitulo = this.getTextView(R.id.actErro_txtErroTitulo);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _txtErroTitulo;
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();

    String strFormatada;

    try {

      this.montarLayoutIgnorarTodos();
      this.getTxtAppNome().setText(AppAndroid.getI().getStrNome());
      this.getTxtErroTitulo().setText(this.getErr().getStrNome());

      if (Utils.getBooStrVazia(this.getErr().getStrMsgDetalhe())) {

        this.getTxtErroMensagem().setText(this.getErr().getStrMsg());
        return;
      }

      strFormatada = "_msg\n\nDetalhes: _detalhe";

      strFormatada = strFormatada.replace("_msg", this.getErr().getStrMsg());
      strFormatada = strFormatada.replace("_detalhe", this.getErr().getStrMsgDetalhe());

      this.getTxtErroMensagem().setText(strFormatada);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private void montarLayoutIgnorarTodos() {

    try {

      if (this.getIntQtdErroVisivel() > 1) {

        this.getButton(R.id.actErro_btnIgnorarTodos).setVisibility(View.VISIBLE);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void onDestroy() {

    super.onDestroy();

    try {

      this.setIntQtdErroVisivel(this.getIntQtdErroVisivel() - 1);
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void onStart() {

    super.onStart();

    try {

      if (this.getBooIgnorarTodos()) {

        this.finish();
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setbooIgnorarTodos(boolean booIgnorarTodos) {

    _booIgnorarTodos = booIgnorarTodos;
  }

  private void setIntQtdErroVisivel(int intQtdErroVisivel) {

    try {

      _intQtdErroVisivel = intQtdErroVisivel;

      if (_intQtdErroVisivel == 0) {

        this.setbooIgnorarTodos(false);
      }
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}
