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
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.finish();

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public Erro getErr() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_err == null) {
        _err = (Erro) this.getIntent().getSerializableExtra("Erro");
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _err;
  }

  @Override
  protected int getIntLayoutId() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES
      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return R.layout.act_erro;
  }

  private TextView getTxtAppNome() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_txtAppNome == null) {
        _txtAppNome = (TextView) this.findViewById(R.id.actErro_txtAppNome);
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _txtAppNome;
  }

  private TextView getTxtErroMensagem() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_txtErroMensagem == null) {
        _txtErroMensagem = (TextView) this.findViewById(R.id.actErro_txtErroMensagem);
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _txtErroMensagem;
  }

  private TextView getTxtErroTitulo() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_txtErroTitulo == null) {
        _txtErroTitulo = (TextView) this.findViewById(R.id.actErro_txtErroTitulo);
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _txtErroTitulo;
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();

    // VARIÁVEIS

    String strErroMensagem;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

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

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

}
