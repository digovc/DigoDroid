package com.digosofter.digodroid.http;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;

public abstract class HttpUtils extends Objeto
{

  public static HttpResponse postData(String uri, List<NameValuePair> lstNameValuePair)
  {
    // VARIÁVEIS
    HttpClient objHttpclient;
    HttpPost objHttppost;
    HttpResponse objHttpResponseResultado = null;
    // FIM VARIÁVEIS
    try
    {
      // AÇÕES
      objHttpResponseResultado = null;
      objHttppost = new HttpPost(uri);
      objHttppost.setEntity(new UrlEncodedFormEntity(lstNameValuePair));
      objHttpclient = new DefaultHttpClient();
      objHttpResponseResultado = objHttpclient.execute(objHttppost);
      // FIM AÇÕES
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);

    }
    finally
    {
    }
    return objHttpResponseResultado;
  }
}