package com.digosofter.digodroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.digosofter.digodroid.MensagemUsuario.EnmLingua;
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

	private Context _context;

	public Context getContext() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			_context = this.getActMain().getApplicationContext();

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(this.getStrMensagemUsuario(101), ex.getMessage());

		} finally {
		}

		return _context;
	}

	private int _intVersao;

	public int getIntVersao() {
		return _intVersao;
	}

	public void setIntVersao(int intVersao) {
		_intVersao = intVersao;
	}

	private List<MensagemUsuario> _lstObjMensagemUsuario;

	private List<MensagemUsuario> getLstObjMensagemUsuario() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_lstObjMensagemUsuario == null) {
				_lstObjMensagemUsuario = new ArrayList<MensagemUsuario>();

				// Mensagens

				_lstObjMensagemUsuario.add(new MensagemUsuario("Erro inesperado..", 0));
				_lstObjMensagemUsuario.add(new MensagemUsuario("Erro ao tentar recuperar o IMEI do aparelho.", 100));
				_lstObjMensagemUsuario
						.add(new MensagemUsuario("Erro ao tentar recuperar contexto do aplicativo.", 101));
				_lstObjMensagemUsuario.add(new MensagemUsuario("Erro ao tentar recuperar banco de dados principal.",
						102));
				_lstObjMensagemUsuario.add(new MensagemUsuario("Erro ao tentar recuperar mensagem de usuário.", 103));
				_lstObjMensagemUsuario.add(new MensagemUsuario("Erro ao tentar mostrar notificação na tela.", 104));

				// Fim mensagens

			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(this.getStrMensagemUsuario(0), ex.getMessage());

		} finally {
		}

		return _lstObjMensagemUsuario;
	}

	private List<DbTabela> _lstTbl = new ArrayList<DbTabela>();

	public List<DbTabela> getLstTbl() {
		return _lstTbl;
	}

	public void setLstTbl(List<DbTabela> lstTbl) {
		_lstTbl = lstTbl;
	}

	private DataBase _objDataBasePrincipal;

	public DataBase getObjDataBasePrincipal() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_objDataBasePrincipal == null) {
				_objDataBasePrincipal = new DataBase(this.getStrNomeSimplificado(), this.getActMain()
						.getApplicationContext());
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(102), ex.getMessage());

		} finally {
		}

		return _objDataBasePrincipal;
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

	public String getStrMensagemUsuario(int intId, EnmLingua enmLingua) {
		// VARIÁVEIS

		String strMensagemUsuarioResultado = Utils.STRING_VAZIA;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			for (MensagemUsuario objMensagemUsuario : this.getLstObjMensagemUsuario()) {
				if (objMensagemUsuario.getIntId() == intId && objMensagemUsuario.getEnmLingua() == enmLingua) {
					strMensagemUsuarioResultado = objMensagemUsuario.getStrTexto();
					break;
				}
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(this.getStrTexto(103), ex.getMessage());

		} finally {
		}

		return strMensagemUsuarioResultado;
	}

	public String getStrMensagemUsuario(int intId) {
		return this.getStrMensagemUsuario(intId, EnmLingua.PORTUGUES);
	}

	public String getStrTexto(int intId) {
		return this.getStrMensagemUsuario(intId);
	}

	public void mostrarNoficacao(String strMensagem) {
		// VARIÁVEIS

		int intTempo = Toast.LENGTH_SHORT;
		Toast objToast;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (strMensagem.length() > 25) {
				intTempo = Toast.LENGTH_LONG;
			}

			objToast = Toast.makeText(this.getActMain().getApplicationContext(), strMensagem, intTempo);
			objToast.show();

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(this.getStrTexto(104), ex.getMessage());

		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
