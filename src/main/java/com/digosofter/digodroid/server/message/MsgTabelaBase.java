package com.digosofter.digodroid.server.message;

import com.digosofter.digodroid.database.tabela.TblSincronizavelMain;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digojava.log.Log;

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

  @Override
  protected void onResponseServidorError(final T rsp)
  {
    // super.onResponseServidorError(rsp);

    if (this.getTbl() == null)
    {
      return;
    }

    LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Erro de sincronização (%s) na tabela %s no servidor: %s", rsp.getClass().getSimpleName(), this.getTbl().getStrNomeExibicao(), rsp.getStrCritica()));

    this.getTbl().onServidorErrroSinc(this, rsp);
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