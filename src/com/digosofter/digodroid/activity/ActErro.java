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
      if (_err == null) {
        _err = (Erro) this.getIntent().getSerializableExtra("Erro");
      }
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

    try {
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return R.layout.act_erro;
  }

  private TextView getTxtAppNome() {

    try {
      if (_txtAppNome == null) {
        _txtAppNome = (TextView) this.findViewById(R.id.actErro_txtAppNome);
      }
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
      if (_txtErroMensagem == null) {
        _txtErroMensagem = (TextView) this.findViewById(R.id.actErro_txtErroMensagem);
      }
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
      if (_txtErroTitulo == null) {
        _txtErroTitulo = (TextView) this.findViewById(R.id.actErro_txtErroTitulo);
      }
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
    String strErroMensagem;
    try {
      this.getTxtAppNome().setText(App.getI().getStrNome());
      this.getTxtErroTitulo().setText(this.getErr().getStrNome());
      if (!Util.getBooStrVazia(this.getErr().getStrMensagemDetalhes())) {
        strErroMensagem = "";
        strErroMensagem += this.getErr().getStrMensagem();
        strErroMensagem += "\n\nDetalhes: ";
        strErroMensagem += this.getErr().getStrMensagemDetalhes();
        this.getTxtErroMensagem().setText(strErroMensagem);
      }
      else {
        this.getTxtErroMensagem().setText(this.getErr().getStrMensagem());
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }
}
