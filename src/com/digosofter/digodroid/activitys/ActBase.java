package com.digosofter.digodroid.activitys;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.erro.Erro;

public abstract class ActBase extends ActionBarActivity {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS
	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// MÉTODOS

	protected void addFragmento(int intIdPnlContainer, Fragment objFragmento) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.getSupportFragmentManager().beginTransaction().add(intIdPnlContainer, objFragmento).commit();

			// FIM AÇÕES
		} catch (Exception ex) {
			
			new Erro(App.getApp().getStrTextoPadrao(113), ex.getMessage());
			
		} finally {
		}
	}

	protected abstract void montarLayout();

	protected abstract void setEventos();

	// FIM MÉTODOS

	// EVENTOS

	// FIM EVENTOS
}
