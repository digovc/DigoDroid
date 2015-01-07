package com.digosofter.digodroid.arquivo;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.DataBaseAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;

public class ArquivoDb extends AndroidArquivo {

  public ArquivoDb(DataBaseAndroid objDataBase) {

    try {

      this.setDirCompleto(AppAndroid.getI().getCnt().getDatabasePath(objDataBase.getStrNome()).getPath());
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }
}
