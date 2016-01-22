package com.digosofter.digodroid.arquivo;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.arquivo.Arquivo;

public abstract class AndroidArquivo extends Arquivo {

  public void copiar(final ActMain act, final String dirDestino) {

    // TODO: Implementar a requisição da permissão em tempo de execução para resolver isso.
    AppAndroid.getI().notificar("Função não implementada para esta versão do Android.");
  }

  private void mostrarMensagemSalvo() {

    String msg;

    try {

      msg = "Arquivo '_arq_nome' salvo com sucesso.";
      msg = msg.replace("_arq_nome", this.getDirCompleto());
      AppAndroid.getI().notificar(msg);
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