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
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			_err = err;

			this.getTxtAppNome().setText(App.getApp().getStrNome());
			this.getTxtErroTitulo().setText(_err.getStrNome());

			if (_err.getStrMensagemDetalhes() != null) {
				this.getTxtErroMensagem().setText(
						_err.getStrMensagem() + "\n\nDetalhes: " + _err.getStrMensagemDetalhes());
			} else {
				this.getTxtErroMensagem().setText(_err.getStrMensagem());
			}

			// FIM AÇÕES
		} catch (Exception ex) {
		} finally {
		}
	}

	private TextView _txtAppNome;

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

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}

		return _txtAppNome;
	}

	private TextView _txtErroMensagem;

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

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}

		return _txtErroMensagem;
	}

	private TextView _txtErroTitulo;

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

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}

		return _txtErroTitulo;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// MÉTODOS

	@Override
	protected void montarLayout() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}
	}

	@Override
	protected void setEventos() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}
	}

	// FIM MÉTODOS

	// EVENTOS

	public void actErro_btnIgnorarOnClick(View objView) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.finish();

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			setContentView(R.layout.act_erro);
			this.setErr((Erro) getIntent().getSerializableExtra("Erro"));
			this.montarLayout();

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
		
			this.getMenuInflater().inflate(R.menu.act_erro, menu);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}

		return true;
	}

	// FIM EVENTOS

}
