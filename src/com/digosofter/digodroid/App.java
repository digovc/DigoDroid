package com.digosofter.digodroid;

import android.app.Activity;
import android.widget.Toast;

import com.digosofter.digodroid.database.DataBase;
import com.digosofter.digodroid.database.DbTabela;

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

	private static App _app;

	public static App getApp() {
		return _app;
	}

	private static void setApp(App app) {
		_app = app;
	}

	private DataBase _objDataBasePrincipal;

	public DataBase getObjDataBasePrincipal() {
		if (_objDataBasePrincipal == null) {
			_objDataBasePrincipal = new DataBase(this.getStrNomeSimplificado(), this.getActMain()
					.getApplicationContext());
		}
		return _objDataBasePrincipal;
	}

	private DbTabela _tblSelecionada;

	public DbTabela getTblSelecionada() {
		return _tblSelecionada;
	}

	public void setTblSelecionada(DbTabela tblSelecionada) {
		_tblSelecionada = tblSelecionada;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public App() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			App.setApp(this);

			// FIM A��ES
		} catch (Exception ex) {
		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS

	public void inicializar() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this._objDataBasePrincipal = new DataBase(this.getStrNomeSimplificado(), this.getActMain()
					.getApplicationContext());

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

			Toast toast = Toast.makeText(this.getActMain().getApplicationContext(), strMensagem, intTempo);
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

			Toast toast = Toast.makeText(this.getActMain().getApplicationContext(), strMensagem, intTempo);
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
