package com.digosofter.digodroid.http;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;

public class HttpCliente extends Objeto {

  public enum EnmStatus {
    CONCLUIDO,
    EM_ANDAMENTO,
    NONE,
  }

  private EnmStatus _enmStatus = EnmStatus.NONE;
  private HttpResponse _objHttpResponse;
  private String _strJson;
  private String _url;
  private String _strResposta;

  public String getStrResposta() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (this.getObjHttpResponse() == null) {
        return Utils.STRING_VAZIA;
      }

      _strResposta = EntityUtils.toString(this.getObjHttpResponse().getEntity());

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _strResposta;
  }

  private EnmStatus getEnmStatus() {
    return _enmStatus;
  }

  public HttpResponse getObjHttpResponse() {
    return _objHttpResponse;
  }

  private String getStrJson() {
    return _strJson;
  }

  private String getUrl() {
    return _url;
  }

  public void postJson(String strJson) {
    // VARI�VEIS

    Thread thr;

    // FIM VARI�VEIS
    try {
      // A��ES

      this.setEnmStatus(EnmStatus.EM_ANDAMENTO);
      this.setStrJson(strJson);

      thr = new Thread() {

        @Override
        public void run() {
          // VARI�VEIS

          HttpClient objHttpClient;
          HttpPost objHttppost;

          // FIM VARI�VEIS
          try {
            // A��ES

            objHttppost = new HttpPost(HttpCliente.this.getUrl());
            objHttppost.setHeader("json", HttpCliente.this.getStrJson());

            objHttpClient = new DefaultHttpClient();

            HttpCliente.this.setObjHttpResponse(objHttpClient.execute(objHttppost));

            // FIM A��ES
          } catch (Exception ex) {

            new Erro(App.getI().getStrTextoPadrao(0), ex);

          } finally {
            HttpCliente.this.setEnmStatus(EnmStatus.CONCLUIDO);
          }
        };
      };

      thr.start();

      do {
        Thread.sleep(1);
      } while (HttpCliente.this.getEnmStatus() == EnmStatus.EM_ANDAMENTO);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  private void setEnmStatus(EnmStatus enmStatus) {
    _enmStatus = enmStatus;
  }

  private void setObjHttpResponse(HttpResponse objHttpResponse) {
    _objHttpResponse = objHttpResponse;
  }

  private void setStrJson(String strJson) {
    _strJson = strJson;
  }

  public void setUrl(String url) {
    _url = url;
  }
}
