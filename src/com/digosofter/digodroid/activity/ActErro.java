package com.digosofter.digodroid.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.R;
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

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	public Erro getErr() {
		return _err;
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

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

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

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

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

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _txtErroTitulo;
	}

	@Override
	protected void montarLayout() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			setContentView(R.layout.act_erro);
			this.setErr((Erro) getIntent().getSerializableExtra("Erro"));
			this.montarLayout();

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return true;
	}

	private void setErr(Erro err) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			_err = err;

			this.getTxtAppNome().setText(App.getI().getStrNome());
			this.getTxtErroTitulo().setText(_err.getStrNome());

			if (_err.getStrMensagemDetalhes() != null) {
				this.getTxtErroMensagem().setText(_err.getStrMensagem() + "\n\nDetalhes: " + _err.getStrMensagemDetalhes());
			} else {
				this.getTxtErroMensagem().setText(_err.getStrMensagem());
			}

			// FIM A��ES
		} catch (Exception ex) {
		} finally {
		}
	}

	@Override
	protected void setEventos() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

}
