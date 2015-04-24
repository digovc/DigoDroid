package com.digosofter.digodroid.database;

import org.apache.commons.io.IOUtils;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.App;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.TblOnChangeArg;

public abstract class DbViewAndroid extends DbTabelaAndroid {

  private DbTabelaAndroid _tbl;

  protected DbViewAndroid(String strNome) {

    super(strNome);

    try {

      App.getI().getLstTbl().remove(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void apagar(int intRegistroId) {

    TblOnChangeArg arg;

    try {

      if (this.getTbl() == null) {

        return;
      }

      this.getTbl().apagar(intRegistroId);

      arg = new TblOnChangeArg();
      arg.setIntRegistroId(intRegistroId);

      this.OnApagarRegDispatcher(arg);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void criar() {

    String sql;

    try {

      if (this.getIntRawFileId() == 0) {

        return;
      }

      sql = "drop view if exists _viw_nome;";
      sql = sql.replace("_viw_nome", this.getStrNomeSql());

      AppAndroid.getI().getObjDbPrincipal().execSql(sql);

      sql = "create view if not exists _viw_nome as _select;";
      sql = sql.replace("_viw_nome", this.getStrNomeSql());
      sql = sql.replace("_select", this.getSqlSelect());

      AppAndroid.getI().getObjDbPrincipal().execSql(sql);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(124), ex);
    }
    finally {
    }
  }

  protected abstract int getIntRawFileId();

  private String getSqlSelect() {

    String strResultado = Utils.STR_VAZIA;

    try {

      if (this.getIntRawFileId() == 0) {

        return Utils.STR_VAZIA;
      }

      strResultado = IOUtils.toString(AppAndroid.getI().getCnt().getResources().openRawResource(this.getIntRawFileId()), "UTF-8");
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return strResultado;
  }

  protected DbTabelaAndroid getTbl() {

    return _tbl;
  }

  public void setTbl(DbTabelaAndroid tbl) {

    try {

      _tbl = tbl;

      if (_tbl == null) {

        return;
      }

      if (_tbl.getLstViwAndroid().contains(this)) {

        return;
      }

      _tbl.getLstViwAndroid().add(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}