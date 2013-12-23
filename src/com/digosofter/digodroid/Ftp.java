package com.digosofter.digodroid;

import org.apache.commons.net.ftp.FTPClient;

import com.digosofter.digodroid.erro.Erro;

public class Ftp extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private FTPClient _objFtpClient;

	private FTPClient getObjFtpClient() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_objFtpClient == null) {
				_objFtpClient = new FTPClient();
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(105), ex.getMessage());

		} finally {
		}

		return _objFtpClient;
	}

	private String _strLogin;

	private String getStrLogin() {
		return _strLogin;
	}

	private void setStrLogin(String strLogin) {
		_strLogin = strLogin;
	}

	private String _strPassword;

	private String getStrPassword() {
		return _strPassword;
	}

	private void setStrPassword(String strPassword) {
		_strPassword = strPassword;
	}

	private String _strUrl;

	private String getStrUrl() {
		return _strUrl;
	}

	private void setStrUrl(String strUrl) {
		_strUrl = strUrl;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public Ftp(String strUrl, String strLogin, String strPassword) {
		// VARI�VEIS

		this.setStrUrl(strUrl);
		this.setStrLogin(strLogin);
		this.setStrPassword(strPassword);

		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(106), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS
	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
