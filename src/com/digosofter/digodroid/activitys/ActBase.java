package com.digosofter.digodroid.activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public abstract class ActBase extends FragmentActivity {
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

			// FIM A��ES
		} catch (Exception ex) {
		} finally {
		}
	}

	protected abstract void montarLayout();

	protected abstract void setEventos();

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
