package com.digosofter.digodroid.database;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.erro.Erro;

public abstract class DbView extends DbTabela {

  public DbView(String strNome) {

    super(strNome);
    try {
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }
}
