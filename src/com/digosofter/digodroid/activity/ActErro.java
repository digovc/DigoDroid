package com.digosofter.digodroid.activity;

import android.view.View;
import android.widget.TextView;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.Util;
import com.digosofter.digodroid.erro.Erro;

public class ActErro extends ActMain {

  private Erro _err;
  private TextView _txtAppNome;
  private TextView _txtErroMensagem;
  private TextView _txtErroTitulo;

  public void actErro_btnIgnorarOnClick(View objView) {

    try {
      this.finish();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  public Erro getErr() {

    try {
      if (_err != null) {
        return _err;
      }
      _err = (Erro) this.getIntent().getSerializableExtra("Erro");
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _err;
  }

  @Override
  protected int getIntLayoutId() {

    return R.layout.act_erro;
  }

  private TextView getTxtAppNome() {

    try {
      if (_txtAppNome != null) {
        return _txtAppNome;
      }
      _txtAppNome = this.getTextView(R.id.actErro_txtAppNome);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
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
      new Erro(App.getI().getStrTextoPadrao(0), ex);
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
      new Erro(App.getI().getStrTextoPadrao(0), ex);
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
      this.getTxtAppNome().setText(App.getI().getStrNome());
      this.getTxtErroTitulo().setText(this.getErr().getStrNome());
      if (Util.getBooStrVazia(this.getErr().getStrMsgDetalhe())) {
        this.getTxtErroMensagem().setText(this.getErr().getStrMsg());
        return;
      }
      strFormatada = "_msg\n\nDetalhes: _detalhe";
      strFormatada = strFormatada.replace("_msg", this.getErr().getStrMsg());
      strFormatada = strFormatada.replace("_detalhe", this.getErr().getStrMsgDetalhe());
      this.getTxtErroMensagem().setText(strFormatada);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }
}
