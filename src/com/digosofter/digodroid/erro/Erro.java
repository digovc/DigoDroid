package com.digosofter.digodroid.erro;

import java.io.Serializable;

import android.content.Intent;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.activity.ActErro;

public class Erro extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean _booMostrarUsuario = true;

	private String _strMensagem = "Erro do sistema.";

	private String _strMensagemDetalhes;

	private String _strNome;

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

				objIntent = new Intent(App.getI().getActMain(), ActErro.class);
				objIntent.putExtra("Erro", this);

				App.getI().getActMain().startActivity(objIntent);
			}

			// FIM AÇÕES
		} catch (Exception ex) {
		} finally {
		}
	}

	public boolean getBooMostrarUsuario() {
		return _booMostrarUsuario;
	}

	public String getStrMensagem() {
		return _strMensagem;
	}

	public String getStrMensagemDetalhes() {
		return _strMensagemDetalhes;
	}

	public String getStrNome() {
		return _strNome;
	}

	public void setBooMostrarUsuario(boolean booMostrarUsuario) {
		_booMostrarUsuario = booMostrarUsuario;
	}

	public void setStrMensagem(String strMensagem) {
		_strMensagem = strMensagem;
	}

	private void setStrMensagemDetalhes(String strMensagemDetalhes) {
		_strMensagemDetalhes = strMensagemDetalhes;
	}

}
