package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.database.DbeAndroidMain;
import com.digosofter.digodroid.database.dominio.DominioEditavelMain;

public abstract class TblEditavelMain<T extends DominioEditavelMain> extends TblSincronizavelMain<T>
{
  protected TblEditavelMain(final String strNome, final DbeAndroidMain dbeAndroid)
  {
    super(strNome, dbeAndroid);
  }
}