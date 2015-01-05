package com.digosofter.digodroid.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.erro.AndroidErro;
import com.digosofter.digojava.Objeto;

public class HttpCliente extends Objeto {

  public enum EnmStatus {
    CONCLUIDO,
    EM_ANDAMENTO,
    NONE,
  }

  private EnmStatus _enmStatus = EnmStatus.NONE;
  private HttpResponse _objHttpResponse;
  private String _jsn;
  private String _strResposta;
  private String _url;

  private EnmStatus getEnmStatus() {

    return _enmStatus;
  }

  public HttpResponse getObjHttpResponse() {

    return _objHttpResponse;
  }

  private String getJsn() {

    return _jsn;
  }

  public String getStrResposta() {

    try {
      if (this.getObjHttpResponse() == null) {
        return UtilsAndroid.STR_VAZIA;
      }
      _strResposta = EntityUtils.toString(this.getObjHttpResponse().getEntity());
    }
    catch (Exception ex) {
      new AndroidErro(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strResposta;
  }

  private String getUrl() {

    return _url;
  }

  /**
   * Envia um objeto "json" para o servidor indicado no atributo "url" e colocar
   * deixa a resposta dispon�vel no atributo "strResposta".
   */
  public void postJson(String jsn) {

    Thread thr;
    try {
      this.setEnmStatus(EnmStatus.EM_ANDAMENTO);
      this.setJsn(jsn);
      thr = new Thread() {

        @Override
        public void run() {

          HttpClient objHttpClient;
          HttpPost objHttppost;
          try {
            objHttppost = new HttpPost(HttpCliente.this.getUrl());
            objHttppost.setHeader("json", HttpCliente.this.getJsn());
            objHttpClient = new DefaultHttpClient();
            HttpCliente.this.setObjHttpResponse(objHttpClient.execute(objHttppost));
          }
          catch (Exception ex) {
            new AndroidErro(AppAndroid.getI().getStrTextoPadrao(0), ex);
          }
          finally {
            HttpCliente.this.setEnmStatus(EnmStatus.CONCLUIDO);
          }
        };
      };
      thr.start();
      do {
        // TODO: Definir se fazer isso ass�ncrono seria melhor.
        Thread.sleep(10);
      }
      while (HttpCliente.this.getEnmStatus() == EnmStatus.EM_ANDAMENTO);
    }
    catch (Exception ex) {
      new AndroidErro(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private void setEnmStatus(EnmStatus enmStatus) {

    _enmStatus = enmStatus;
  }

  private void setObjHttpResponse(HttpResponse objHttpResponse) {

    _objHttpResponse = objHttpResponse;
  }

  private void setJsn(String jsn) {

    _jsn = jsn;
  }

  public void setUrl(String url) {

    _url = url;
  }
}
