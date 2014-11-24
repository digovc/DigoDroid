package com.digosofter.digodroid;

import org.apache.commons.net.ftp.FTPClient;

import com.digosofter.digodroid.erro.AndroidErro;
import com.digosofter.digojava.Objeto;

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
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected FTPClient getFtpClient() {

    try {
      if (_ftpClient != null) {
        return _ftpClient;
      }
      _ftpClient = new FTPClient();
    }
    catch (Exception ex) {
      new AndroidErro(AppAndroid.getI().getStrTextoPadrao(105), ex);
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
