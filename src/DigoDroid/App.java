package DigoDroid;

import DigoDroid.DataBase.DataBase;
import android.app.Activity;
import android.widget.Toast;

public abstract class App extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private Activity _actMain;

	public Activity getActMain() {
		return _actMain;
	}

	public void setActMain(Activity actMain) {
		_actMain = actMain;
	}

	private DataBase _objDataBase;

	public DataBase getobjDataBase() {
		return _objDataBase;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// M�TODOS
	public void inicializar() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this._objDataBase = new DataBase(this.getStrNome(), this
					.getActMain().getApplicationContext());

			// FIM A��ES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
	}

	public void mostraMensagemCurta(String strMensagem) {
		// VARI�VEIS

		int intTempo = Toast.LENGTH_SHORT;

		// FIM VARI�VEIS
		try {
			// A��ES

			Toast toast = Toast.makeText(this.getActMain()
					.getApplicationContext(), strMensagem, intTempo);
			toast.show();

			// FIM A��ES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
	}

	public void mostraMensagemLonga(String strMensagem) {
		// VARI�VEIS

		int intTempo = Toast.LENGTH_LONG;

		// FIM VARI�VEIS
		try {
			// A��ES

			Toast toast = Toast.makeText(this.getActMain()
					.getApplicationContext(), strMensagem, intTempo);
			toast.show();

			// FIM A��ES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
