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

	// MÉTODOS
	public void inicializar() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this._objDataBase = new DataBase(this.getStrNome(), this
					.getActMain().getApplicationContext());

			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	public void mostraMensagemCurta(String strMensagem) {
		// VARIÁVEIS

		int intTempo = Toast.LENGTH_SHORT;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			Toast toast = Toast.makeText(this.getActMain()
					.getApplicationContext(), strMensagem, intTempo);
			toast.show();

			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	public void mostraMensagemLonga(String strMensagem) {
		// VARIÁVEIS

		int intTempo = Toast.LENGTH_LONG;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			Toast toast = Toast.makeText(this.getActMain()
					.getApplicationContext(), strMensagem, intTempo);
			toast.show();

			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
