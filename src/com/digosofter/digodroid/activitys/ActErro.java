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

			TextView txtAppNome = (TextView) this.findViewById(R.id.actErro_txtAppNome);
			TextView txtErroTitulo = (TextView) this.findViewById(R.id.actErro_txtErroTitulo);
			TextView txtErroMensagem = (TextView) this.findViewById(R.id.actErro_txtErroMensagem);

			txtAppNome.setText(App.getApp().getStrNome());
			txtErroTitulo.setText(_err.getStrNome());
			txtErroMensagem.setText(_err.getStrMensagem() + "\n\nInforma��o t�cnica: " + _err.getStrMensagemDetalhes());

			// FIM A��ES
		} catch (Exception ex) {
		} finally {
		}
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
		} finally {
		}
	}

	@Override
	protected void setEventos() {
		// TODO Auto-generated method stub

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

			new Erro("Erro inesperado.\n" , ex.getMessage());

		} finally {
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			super.onCreate(savedInstanceState);
			setContentView(R.layout.act_erro);

			this.montarLayout();

			this.setErr((Erro) getIntent().getSerializableExtra("Erro"));

			// FIM A��ES
		} catch (Exception ex) {
		} finally {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_erro, menu);
		return true;
	}

	// FIM EVENTOS

}
