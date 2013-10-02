package com.digosofter.digodroid.itens;

import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;

public class ItmCadastro extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private int _intItemId;

	public int getIntItemId() {
		return _intItemId;
	}

	public void setIntItemId(int intItemId) {
		_intItemId = intItemId;
	}

	private String _strCampo001;

	public String getstrCampo001() {
		_strCampo001 = this.getStrCampo001Nome() + ": " + this.getStrCampo001Valor();
		return _strCampo001;
	}

	private String _strCampo001Nome;

	public String getStrCampo001Nome() {
		return _strCampo001Nome;
	}

	public void setStrCampo001Nome(String strCampo001Nome) {
		_strCampo001Nome = strCampo001Nome;
	}

	private String _strCampo001Valor;

	public String getStrCampo001Valor() {
		if (_strCampo001Valor == null) {
			_strCampo001Valor = Utils.STRING_VAZIA;
		}
		return _strCampo001Valor.replace("true", "Sim").replace("false", "N�o");
	}

	public void setStrCampo001Valor(String strCampo001Valor) {
		_strCampo001Valor = strCampo001Valor;
	}

	private String _strCampo002;

	public String getstrCampo002() {
		_strCampo002 = this.getStrCampo002Nome() + ": " + this.getStrCampo002Valor();
		return _strCampo002;
	}

	private String _strCampo002Nome;

	public String getStrCampo002Nome() {
		return _strCampo002Nome;
	}

	public void setStrCampo002Nome(String strCampo002Nome) {
		_strCampo002Nome = strCampo002Nome;
	}

	private String _strCampo002Valor;

	public String getStrCampo002Valor() {
		if (_strCampo002Valor == null) {
			_strCampo002Valor = Utils.STRING_VAZIA;
		}
		return _strCampo002Valor.replace("true", "Sim").replace("false", "N�o");
	}

	public void setStrCampo002Valor(String strCampo002Valor) {
		_strCampo002Valor = strCampo002Valor;
	}

	private String _strCampo003;

	public String getstrCampo003() {
		_strCampo003 = this.getStrCampo003Nome() + ": " + this.getStrCampo003Valor();
		return _strCampo003;
	}

	private String _strCampo003Nome;

	public String getStrCampo003Nome() {
		return _strCampo003Nome;
	}

	public void setStrCampo003Nome(String strCampo003Nome) {
		_strCampo003Nome = strCampo003Nome;
	}

	private String _strCampo003Valor;

	public String getStrCampo003Valor() {
		if (_strCampo003Valor == null) {
			_strCampo003Valor = Utils.STRING_VAZIA;
		}
		return _strCampo003Valor.replace("true", "Sim").replace("false", "N�o");
	}

	public void setStrCampo003Valor(String strCampo003Valor) {
		_strCampo003Valor = strCampo003Valor;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// M�TODOS

	public boolean getBooContemString(String strFiltro) {
		// VARI�VEIS

		boolean booContemString = false;

		// FIM VARI�VEIS
		try {
			// A��ES

			if (this.getStrNome().contains(strFiltro)) {
				booContemString = true;
			}
			if (this.getStrCampo001Valor().contains(strFiltro)) {
				booContemString = true;
			}
			if (this.getStrCampo002Valor().contains(strFiltro)) {
				booContemString = true;
			}
			if (this.getStrCampo003Valor().contains(strFiltro)) {
				booContemString = true;
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro ao verificar filtro no item da lista.\n" , ex.getMessage());

		} finally {
		}
		return booContemString;
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
