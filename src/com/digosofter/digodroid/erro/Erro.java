package com.digosofter.digodroid.erro;

import java.io.Serializable;

import android.content.Intent;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.activitys.ActErro;

public class Erro extends Objeto implements Serializable {
	// CONSTANTES
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

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public Erro(String strMensagem) {
		// VARIÁVEIS

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (strMensagem != Utils.STRING_VAZIA) {
				this.setStrMensagem(strMensagem);
			}
			if (this.getBooMostrarUsuario()) {
				Intent objIntent = new Intent(App.getApp().getActMain(), ActErro.class);
				objIntent.putExtra("Erro", this);
				App.getApp().getActMain().startActivity(objIntent);
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			int intTempo = 5;

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
