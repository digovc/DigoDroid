package com.digosofter.digodroid.activitys;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.VideoView;

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

			new Erro(App.getI().getStrTextoPadrao(113), ex.getMessage());

		} finally {
		}
	}

	protected View getView(int intId) {
		// VARIÁVEIS

		View viewResultado = null;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			viewResultado = this.findViewById(intId);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return viewResultado;
	}

	public Button getButton(int intId) {
		return (Button) this.getView(intId);
	}
	
	public CheckBox getCheckBox(int intId) {
		return (CheckBox) this.getView(intId);
	}
	
	public EditText getEditText(int intId) {
		return (EditText) this.getView(intId);
	}
	
	public ImageView getImageView(int intId) {
		return (ImageView) this.getView(intId);
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
	
	public VideoView getVideoView(int intId) {
		return (VideoView) this.getView(intId);
	}

	protected abstract void montarLayout();

	protected abstract void setEventos();

	// FIM MÉTODOS

	// EVENTOS

	// FIM EVENTOS
}
