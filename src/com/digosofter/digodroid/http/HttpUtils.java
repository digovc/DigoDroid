package com.digosofter.digodroid.http;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.AndroidErro;
import com.digosofter.digojava.Objeto;

public abstract class HttpUtils extends Objeto {

  public static HttpResponse postData(String uri, List<NameValuePair> lstNameValuePair) {
    // VARIÁVEIS

    HttpClient objHttpclient;
    HttpPost objHttppost;
    HttpResponse objHttpResponseResultado = null;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      objHttpResponseResultado = null;

      objHttppost = new HttpPost(uri);
      objHttppost.setEntity(new UrlEncodedFormEntity(lstNameValuePair));

      objHttpclient = new DefaultHttpClient();
      objHttpResponseResultado = objHttpclient.execute(objHttppost);

      // FIM AÇÕES
    } catch (Exception ex) {

      new AndroidErro(AppAndroid.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return objHttpResponseResultado;
  }
}
