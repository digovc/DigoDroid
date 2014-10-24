package com.digosofter.digodroid.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public class Http extends Objeto {

  public enum EnmStatus {
    CONCLUIDO,
    EM_ANDAMENTO,
    NONE,
  }

  private EnmStatus _enmStatus = EnmStatus.NONE;

  private HttpResponse _objHttpResponse;

  private String _strJson;

  private String _url;

  private EnmStatus getEnmStatus() {

    return _enmStatus;
  }

  private HttpResponse getObjHttpResponse() {

    return _objHttpResponse;
  }

  private String getStrJson() {

    return _strJson;
  }

  private String getUrl() {

    return _url;
  }

  public void postJson() {

    Thread thr;

    try {

      this.setEnmStatus(EnmStatus.EM_ANDAMENTO);

      thr = new Thread() {

        @Override
        public void run() {

          HttpClient objHttpClient;
          HttpPost objHttppost;

          try {

            objHttppost = new HttpPost(Http.this.getUrl());
            objHttppost.setHeader("json", Http.this.getStrJson());

            objHttpClient = new DefaultHttpClient();

            Http.this.setObjHttpResponse(objHttpClient.execute(objHttppost));

          }
          catch (Exception ex) {

            new Erro(App.getI().getStrTextoPadrao(0), ex);

          }
          finally {
            Http.this.setEnmStatus(EnmStatus.CONCLUIDO);
          }
        };
      };

      thr.start();

      do {
        Thread.sleep(1);
      }
      while (Http.this.getEnmStatus() == EnmStatus.EM_ANDAMENTO);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

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

  public void setStrJson(String strJson) {

    _strJson = strJson;
  }

  public void setUrl(String url) {

    _url = url;
  }
}
