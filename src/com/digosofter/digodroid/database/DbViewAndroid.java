package com.digosofter.digodroid.database;

import org.apache.commons.io.IOUtils;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public abstract class DbViewAndroid extends DbTabelaAndroid {

  protected DbViewAndroid(String strNome) {

    super(strNome);
  }

  @Override
  protected void criar() {

    String sql;

    try {

      if (this.getIntRawFileId() == 0) {

        return;
      }

      sql = "drop view if exists _viw_nome;";
      sql = sql.replace("_viw_nome", this.getStrNomeSimplificado());

      AppAndroid.getI().getObjDbPrincipal().execSqlSemRetorno(sql);

      sql = "create view if not exists _viw_nome as _select;";
      sql = sql.replace("_viw_nome", this.getStrNomeSimplificado());
      sql = sql.replace("_select", this.getSqlSelect());

      AppAndroid.getI().getObjDbPrincipal().execSqlSemRetorno(sql);
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
}