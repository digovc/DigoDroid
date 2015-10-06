package com.digosofter.digodroid.arquivo;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.arquivo.Arquivo;

public abstract class AndroidArquivo extends Arquivo {

  private void mostrarMensagemSalvo() {

    String msg;

    try {

      msg = "Arquivo '_arq_nome' salvo com sucesso.";
      msg = msg.replace("_arq_nome", this.getDirCompleto());
      AppAndroid.getI().mostrarNotificacao(msg);
    }
    catch (Exception ex) {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  @Override
  public void salvar() {

    super.salvar();

    try {

      this.mostrarMensagemSalvo();
    }
    catch (Exception ex) {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

}
