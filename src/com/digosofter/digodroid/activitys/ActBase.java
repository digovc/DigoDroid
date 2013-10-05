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

	// M�TODOS

	protected void addFragmento(int intIdPnlContainer, Fragment objFragmento) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			getSupportFragmentManager().beginTransaction().add(intIdPnlContainer, objFragmento).commit();

			this.findViewById(0);
			
			// FIM A��ES
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

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
