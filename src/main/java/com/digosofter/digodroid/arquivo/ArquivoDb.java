package com.digosofter.digodroid.arquivo;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.DataBaseAndroid;

public class ArquivoDb extends AndroidArquivo
{
  public ArquivoDb(DataBaseAndroid objDataBase)
  {
    this.setDirCompleto(AppAndroid.getI().getCnt().getDatabasePath(objDataBase.getStrNome()).getPath());
  }
}
