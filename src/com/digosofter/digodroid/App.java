package com.digosofter.digodroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.digosofter.digodroid.database.DataBase;
import com.digosofter.digodroid.database.DbTabela;
import com.digosofter.digodroid.erro.Erro;

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

	private boolean _booAtualizado;

	public boolean getBooAtualizado() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro ao verificar se está atualizado.\n" + ex.getMessage());

		} finally {
		}
		return _booAtualizado;
	}

	private Context _context;

	public Context getContext() {
		_context = this.getActMain().getApplicationContext();
		return _context;
	}

	private int _intVersao;

	public int getIntVersao() {
		return _intVersao;
	}

	public void setIntVersao(int intVersao) {
		_intVersao = intVersao;
	}

	private List<DbTabela> _lstTbl = new ArrayList<DbTabela>();

	public List<DbTabela> getlstTbl() {
		return _lstTbl;
	}

	public void setlstTbl(List<DbTabela> lstTbl) {
		_lstTbl = lstTbl;
	}

	private DataBase _objDataBasePrincipal;

	public DataBase getObjDataBasePrincipal() {
		if (_objDataBasePrincipal == null) {
			_objDataBasePrincipal = new DataBase(this.getStrNomeSimplificado(), this.getActMain()
					.getApplicationContext());
		}
		return _objDataBasePrincipal;
	}

	private String _strUrlUpdate;

	public String getstrUrlUpdate() {
		return _strUrlUpdate;
	}

	public void setStrUrlUpdate(String strUrlUpdate) {
		_strUrlUpdate = strUrlUpdate;
	}

	private String _strVersaoExibicao;

	public String getStrVersaoExibicao() {
		return _strVersaoExibicao;
	}

	public void setStrVersaoExibicao(String strVersaoExibicao) {
		_strVersaoExibicao = strVersaoExibicao;
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
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			App.setApp(this);

			// FIM AÇÕES
		} catch (Exception ex) {
		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS

	public void mostrarMensagemCurta(String strMensagem) {
		// VARIÁVEIS

		int intTempo = Toast.LENGTH_SHORT;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			Toast toast = Toast.makeText(this.getActMain().getApplicationContext(), strMensagem, intTempo);
			toast.show();

			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	public void mostrarMensagemLonga(String strMensagem) {
		// VARIÁVEIS

		int intTempo = Toast.LENGTH_LONG;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			Toast toast = Toast.makeText(this.getActMain().getApplicationContext(), strMensagem, intTempo);
			toast.show();

			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	public void update() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
