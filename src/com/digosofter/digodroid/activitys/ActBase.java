package com.digosofter.digodroid.activitys;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

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

			getSupportFragmentManager().beginTransaction().add(intIdPnlContainer, objFragmento).commit();

			this.findViewById(0);
			
			// FIM AÇÕES
		} catch (Exception ex) {
		} finally {
		}
	}

	@Override
	public View findViewById(int id) {
		return super.findViewById(id);
	}
	
	protected abstract void montarLayout();

	protected abstract void setEventos();

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
