package com.digosofter.digodroid.activitys;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.erro.Erro;

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

			this.getSupportFragmentManager().beginTransaction().add(intIdPnlContainer, objFragmento).commit();

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(113), ex.getMessage());

		} finally {
		}
	}

	protected View getView(int intId) {
		// VARI�VEIS

		View viewResultado = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			viewResultado = this.findViewById(intId);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return viewResultado;
	}

	public EditText getEditText(int intId) {
		return (EditText) this.getView(intId);
	}

	public LinearLayout getLinearLayout(int intId) {
		return (LinearLayout) this.getView(intId);
	}
	
	public RadioButton getRadioButton(int intId) {
		return (RadioButton) this.getView(intId);
	}	
	
	public RadioGroup getRadioGroup(int intId) {
		return (RadioGroup) this.getView(intId);
	}	
	
	public TextView getTextView(int intId) {
		return (TextView) this.getView(intId);
	}

	protected abstract void montarLayout();

	protected abstract void setEventos();

	// FIM M�TODOS

	// EVENTOS

	// FIM EVENTOS
}
