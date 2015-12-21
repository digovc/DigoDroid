package com.digosofter.digodroid.service;

import android.app.IntentService;
import android.content.Intent;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.App;

public abstract class SrvMain extends IntentService {

  private Intent _itt;

  public SrvMain(String strNome) {

    super(AppAndroid.getI().getStrNomeExibicao() + " - " + strNome);
  }

  protected void finalizar() {

  }

  protected Intent getItt() {

    return _itt;
  }

  /**
   * Responsável pela inicialização de propriedades antes de iniciar o serviço.
   *
   * @return True caso o serviço foi inicializado corretamente e pode iniciar, ou False caso contrário, o que parará o
   * serviço imediatamente.
   */
  protected boolean inicializar() {

    return true;
  }

  @Override
  protected void onHandleIntent(Intent itt) {

    try {

      this.setItt(itt);

      if (!this.inicializar()) {

        return;
      }

      this.servico();

    }
    catch (Exception ex) {

      new ErroAndroid(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {

      this.finalizar();
    }

  }

  protected void servico() {

  }

  private void setItt(Intent itt) {

    _itt = itt;
  }
}