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

	// MÉTODOS

	protected void addFragmento(int intIdPnlContainer, Fragment objFragmento) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			getSupportFragmentManager().beginTransaction().add(intIdPnlContainer, objFragmento).commit();

			// FIM AÇÕES
		} catch (Exception ex) {
		} finally {
		}
	}

	protected abstract void montarLayout();

	protected abstract void setEventos();

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
