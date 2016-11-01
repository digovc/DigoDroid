package com.digosofter.digodroid.arquivo;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.DbeAndroidMain;

public class ArquivoDb extends AndroidArquivo
{
  public ArquivoDb(DbeAndroidMain objDataBase)
  {
    this.setDirCompleto(AppAndroid.getI().getCnt().getDatabasePath(objDataBase.getStrNome()).getPath());
  }
}