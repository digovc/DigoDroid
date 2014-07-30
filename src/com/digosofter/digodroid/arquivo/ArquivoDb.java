package com.digosofter.digodroid.arquivo;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.database.DataBase;
import com.digosofter.digodroid.erro.Erro;

public class ArquivoDb extends Arquivo {

  public ArquivoDb(DataBase objDataBase) {

    try {
      this.setDirCompleto(App.getI().getCnt().getDatabasePath(objDataBase.getStrNome()).getPath());
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }
}
