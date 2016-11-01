package com.digosofter.digodroid.arquivo;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.DbeAndroidBase;

public class ArquivoDb extends AndroidArquivo
{
  public ArquivoDb(DbeAndroidBase objDataBase)
  {
    this.setDirCompleto(AppAndroid.getI().getCnt().getDatabasePath(objDataBase.getStrNome()).getPath());
  }
}