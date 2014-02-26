package com.digosofter.digodroid;

import org.apache.commons.net.ftp.FTPClient;

import com.digosofter.digodroid.erro.Erro;

public class Ftp extends Objeto {

	private FTPClient _objFtpClient;

	private String _strLogin;

	private String _strPassword;

	private String _strUrl;

	public Ftp(String strUrl, String strLogin, String strPassword) {
		// VARIÁVEIS

		this.setStrUrl(strUrl);
		this.setStrLogin(strLogin);
		this.setStrPassword(strPassword);

		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(106), ex.getMessage());

		} finally {
		}
	}

	protected FTPClient getObjFtpClient() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_objFtpClient == null) {
				_objFtpClient = new FTPClient();
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(105), ex.getMessage());

		} finally {
		}

		return _objFtpClient;
	}

	protected String getStrLogin() {
		return _strLogin;
	}

	protected String getStrPassword() {
		return _strPassword;
	}

	protected String getStrUrl() {
		return _strUrl;
	}

	private void setStrLogin(String strLogin) {
		_strLogin = strLogin;
	}

	private void setStrPassword(String strPassword) {
		_strPassword = strPassword;
	}

	private void setStrUrl(String strUrl) {
		_strUrl = strUrl;
	}

}
