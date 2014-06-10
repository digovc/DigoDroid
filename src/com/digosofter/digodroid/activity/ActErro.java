package com.digosofter.digodroid.activity;

import android.view.View;
import android.widget.TextView;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;

public class ActErro extends ActMain {

  private Erro _err;

  private TextView _txtAppNome;

  private TextView _txtErroMensagem;

  private TextView _txtErroTitulo;

  public void actErro_btnIgnorarOnClick(View objView) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.finish();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public Erro getErr() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_err == null) {
        _err = (Erro) this.getIntent().getSerializableExtra("Erro");
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _err;
  }

  @Override
  protected int getIntLayoutId() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES
      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return R.layout.act_erro;
  }

  private TextView getTxtAppNome() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_txtAppNome == null) {
        _txtAppNome = (TextView) this.findViewById(R.id.actErro_txtAppNome);
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _txtAppNome;
  }

  private TextView getTxtErroMensagem() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_txtErroMensagem == null) {
        _txtErroMensagem = (TextView) this.findViewById(R.id.actErro_txtErroMensagem);
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _txtErroMensagem;
  }

  private TextView getTxtErroTitulo() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_txtErroTitulo == null) {
        _txtErroTitulo = (TextView) this.findViewById(R.id.actErro_txtErroTitulo);
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _txtErroTitulo;
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();

    // VARI�VEIS

    String strErroMensagem;

    // FIM VARI�VEIS
    try {
      // A��ES

      this.getTxtAppNome().setText(App.getI().getStrNome());
      this.getTxtErroTitulo().setText(this.getErr().getStrNome());

      if (!Utils.getBooIsEmptyNull(this.getErr().getStrMensagemDetalhes())) {

        strErroMensagem = "";
        strErroMensagem += this.getErr().getStrMensagem();
        strErroMensagem += "\n\nDetalhes: ";
        strErroMensagem += this.getErr().getStrMensagemDetalhes();

        this.getTxtErroMensagem().setText(strErroMensagem);

      } else {

        this.getTxtErroMensagem().setText(this.getErr().getStrMensagem());

      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

}
