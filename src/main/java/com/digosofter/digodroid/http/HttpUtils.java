package com.digosofter.digodroid.http;

import com.digosofter.digojava.Objeto;
import com.digosofter.digojava.Utils;

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
    if (Utils.getBooStrVazia(uri))
    {
      return null;
    }

    HttpResponse objHttpResponseResultado = null;

    HttpPost objHttppost = new HttpPost(uri);

    try
    {
      objHttppost.setEntity(new UrlEncodedFormEntity(lstNameValuePair));

      HttpClient objHttpclient = new DefaultHttpClient();

      objHttpResponseResultado = objHttpclient.execute(objHttppost);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }

    return objHttpResponseResultado;
  }
}