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
    NONE,
    CONCLUIDO,
    EM_ANDAMENTO,
  }

  private EnmStatus _enmStatus = EnmStatus.NONE;

  private EnmStatus getEnmStatus() {
    return _enmStatus;
  }

  private HttpResponse _objHttpResponse;

  private HttpResponse getObjHttpResponse() {
    return _objHttpResponse;
  }

  private void setObjHttpResponse(HttpResponse objHttpResponse) {
    _objHttpResponse = objHttpResponse;
  }

  private void setEnmStatus(EnmStatus enmStatus) {
    _enmStatus = enmStatus;
  }

  private String _url;

  private String getUrl() {
    return _url;
  }

  public void setUrl(String url) {
    _url = url;
  }

  private String _strJson;

  private String getStrJson() {
    return _strJson;
  }

  public void setStrJson(String strJson) {
    _strJson = strJson;
  }

  public void postJson() {
    // VARIÁVEIS

    Thread thr;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.setEnmStatus(EnmStatus.EM_ANDAMENTO);

      thr = new Thread() {
        @Override
        public void run() {
          // VARIÁVEIS

          HttpClient objHttpClient;
          HttpPost objHttppost;

          // FIM VARIÁVEIS
          try {
            // AÇÕES

            objHttppost = new HttpPost(Http.this.getUrl());
            objHttppost.setHeader("json", Http.this.getStrJson());

            objHttpClient = new DefaultHttpClient();

            Http.this.setObjHttpResponse(objHttpClient.execute(objHttppost));

            // FIM AÇÕES
          } catch (Exception ex) {

            new Erro(App.getI().getStrTextoPadrao(0), ex);

          } finally {
            Http.this.setEnmStatus(EnmStatus.CONCLUIDO);
          }
        };
      };

      thr.start();

      do {
        Thread.sleep(1);
      } while (Http.this.getEnmStatus() == EnmStatus.EM_ANDAMENTO);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }
}
