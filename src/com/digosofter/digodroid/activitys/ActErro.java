package com.digosofter.digodroid.activitys;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.erro.Erro;

public class ActErro extends ActBase {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private Erro _err;

	public Erro getErr() {
		return _err;
	}

	private void setErr(Erro err) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			_err = err;

			this.getTxtAppNome().setText(App.getApp().getStrNome());
			this.getTxtErroTitulo().setText(_err.getStrNome());

			if (_err.getStrMensagemDetalhes() != null) {
				this.getTxtErroMensagem().setText(
						_err.getStrMensagem() + "\n\nDetalhes: " + _err.getStrMensagemDetalhes());
			} else {
				this.getTxtErroMensagem().setText(_err.getStrMensagem());
			}

			// FIM A��ES
		} catch (Exception ex) {
		} finally {
		}
	}

	private TextView _txtAppNome;

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

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}

		return _txtAppNome;
	}

	private TextView _txtErroMensagem;

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

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}

		return _txtErroMensagem;
	}

	private TextView _txtErroTitulo;

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

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}

		return _txtErroTitulo;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// M�TODOS

	@Override
	protected void montarLayout() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

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

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}
	}

	// FIM M�TODOS

	// EVENTOS

	public void actErro_btnIgnorarOnClick(View objView) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.finish();

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

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

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES
		
			this.getMenuInflater().inflate(R.menu.act_erro, menu);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}

		return true;
	}

	// FIM EVENTOS

}
