package com.digosofter.digodroid;

import com.digosofter.digojava.Objeto;

import org.apache.commons.net.ftp.FTPClient;

public class Ftp extends Objeto
{
  private FTPClient _ftpClient;
  private String _strLogin;
  private String _strPassword;
  private String _url;

  public Ftp(String url, String strLogin, String strPassword)
  {
    this.setUrl(url);
    this.setStrLogin(strLogin);
    this.setStrPassword(strPassword);
  }

  protected FTPClient getFtpClient()
  {
    if (_ftpClient != null)
    {
      return _ftpClient;
    }
    _ftpClient = new FTPClient();

    return _ftpClient;
  }

  protected String getStrLogin()
  {
    return _strLogin;
  }

  protected String getStrPassword()
  {
    return _strPassword;
  }

  protected String getUrl()
  {
    return _url;
  }

  private void setStrLogin(String strLogin)
  {
    _strLogin = strLogin;
  }

  private void setStrPassword(String strPassword)
  {
    _strPassword = strPassword;
  }

  private void setUrl(String url)
  {
    _url = url;
  }
}
