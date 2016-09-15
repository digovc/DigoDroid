package com.digosofter.digodroid.sinc.message;

import com.digosofter.digodroid.database.tabela.TblSincronizavelMain;

public abstract class MsgTabelaBase<T extends RespostaMain> extends MessageMain<T>
{
  private String _sqlTblServerNome;
  private transient TblSincronizavelMain _tbl;

  private String getSqlTblServerNome()
  {
    return _sqlTblServerNome;
  }

  public TblSincronizavelMain getTbl()
  {
    return _tbl;
  }

  private void setSqlTblServerNome(String sqlTblServerNome)
  {
    _sqlTblServerNome = sqlTblServerNome;
  }

  public void setTbl(TblSincronizavelMain tbl)
  {
    if (_tbl == tbl)
    {
      return;
    }

    _tbl = tbl;

    if (_tbl == null)
    {
      return;
    }

    this.setSqlTblServerNome(_tbl.getSqlServerNome());
  }
}