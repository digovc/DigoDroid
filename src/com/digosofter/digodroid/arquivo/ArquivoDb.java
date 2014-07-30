package com.digosofter.digodroid.arquivo;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DataBase;
import com.digosofter.digodroid.erro.Erro;

public class ArquivoDb extends Arquivo {

  public ArquivoDb(DataBase objDataBase) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setDirCompleto(App.getI().getCnt().getDatabasePath(objDataBase.getStrNome())
          .getPath());

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }
}
