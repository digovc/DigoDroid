package com.digosofter.digodroid;

import org.apache.commons.net.ftp.FTPClient;

import com.digosofter.digodroid.erro.Erro;

public class Ftp extends Objeto {

  private FTPClient _ftpClient;
  private String _strLogin;
  private String _strPassword;
  private String _url;

  public Ftp(String url, String strLogin, String strPassword) {

    try {
      this.setUrl(url);
      this.setStrLogin(strLogin);
      this.setStrPassword(strPassword);
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected FTPClient getFtpClient() {

    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES
      if (_ftpClient != null) {
        return _ftpClient;
      }
      _ftpClient = new FTPClient();
      // FIM AÇÕES
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(105), ex);
    }
    finally {
    }
    return _ftpClient;
  }

  protected String getStrLogin() {

    return _strLogin;
  }

  protected String getStrPassword() {

    return _strPassword;
  }

  protected String getUrl() {

    return _url;
  }

  private void setStrLogin(String strLogin) {

    _strLogin = strLogin;
  }

  private void setStrPassword(String strPassword) {

    _strPassword = strPassword;
  }

  private void setUrl(String url) {

    _url = url;
  }
}
