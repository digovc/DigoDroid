package com.digosofter.digodroid;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.digosofter.digodroid.erro.Erro;

public class Ftp extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private FTPClient _objFtpClient;

	private FTPClient getObjFtpClient() {
		if (_objFtpClient == null) {
			_objFtpClient = new FTPClient();
		}
		return _objFtpClient;
	}

	private void setObjFtpClient(FTPClient objFtpClient) {
		_objFtpClient = objFtpClient;
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
		// VARIÁVEIS

		this.setStrUrl(strUrl);
		this.setStrLogin(strLogin);
		this.setStrPassword(strPassword);

		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro ao instanciar objeto do tipo 'Ftp'.\n" , ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS

	public void downloadFile(String strFileName, String dirSalvar) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro ao fazer download do FTP.\n" , ex.getMessage());

		} finally {
		}
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
