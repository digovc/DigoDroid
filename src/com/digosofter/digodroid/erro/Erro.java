package com.digosofter.digodroid.erro;

import java.io.Serializable;

import android.content.Intent;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.activitys.ActErro;

public class Erro extends Objeto implements Serializable {
	// CONSTANTES

	private static final long serialVersionUID = 1L;

	// FIM CONSTANTES

	// ATRIBUTOS

	private boolean _booMostrarUsuario = true;

	public boolean getBooMostrarUsuario() {
		return _booMostrarUsuario;
	}

	public void setBooMostrarUsuario(boolean booMostrarUsuario) {
		_booMostrarUsuario = booMostrarUsuario;
	}

	private String _strMensagem = "Erro do sistema.";

	public String getStrMensagem() {
		return _strMensagem;
	}

	public void setStrMensagem(String strMensagem) {
		_strMensagem = strMensagem;
	}

	private String _strMensagemDetalhes;

	public String getStrMensagemDetalhes() {
		return _strMensagemDetalhes;
	}

	private void setStrMensagemDetalhes(String strMensagemDetalhes) {
		_strMensagemDetalhes = strMensagemDetalhes;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public Erro(String strMensagem, String strMensagemDetalhes) {
		// VARIÁVEIS

		Intent objIntent;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (!Utils.getBooIsEmptyNull(strMensagem)) {
				this.setStrMensagem(strMensagem);
			}

			if (!Utils.getBooIsEmptyNull(strMensagemDetalhes)) {
				this.setStrMensagemDetalhes(strMensagemDetalhes);
			}

			if (this.getBooMostrarUsuario()) {

				objIntent = new Intent(App.getApp().getActMain(), ActErro.class);
				objIntent.putExtra("Erro", this);

				App.getApp().getActMain().startActivity(objIntent);
			}

			// FIM AÇÕES
		} catch (Exception ex) {
		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
