package com.digosofter.digodroid.database;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Dominio;
import com.digosofter.digojava.database.OnChangeArg;

import org.apache.commons.io.IOUtils;

public abstract class ViewAndroid extends TabelaAndroid<Dominio> {

  private TabelaAndroid<?> _tbl;

  protected ViewAndroid(String strNome, TabelaAndroid tbl) {

    super(strNome, null);

    try {

      this.setTbl(tbl);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void addAppLstTbl() {

    // Impede que uma view seja adicionada para a lista de
    // tabelas da aplicação.
  }

  @Override
  public void apagar(int intRegistroId) {

    OnChangeArg arg;

    try {

      if (this.getTbl() == null) {

        return;
      }

      this.getTbl().apagar(intRegistroId);

      arg = new OnChangeArg();
      arg.setIntRegistroId(intRegistroId);

      this.dispararOnApagarReg(arg);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void atualizarTbl() {

    try {

      if (this.getTbl() == null) {

        return;
      }

      this.getTbl().addViw(this);
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

  protected TabelaAndroid<?> getTbl() {

    return _tbl;
  }

  private void setTbl(TabelaAndroid<?> tbl) {

    try {

      _tbl = tbl;

      this.atualizarTbl();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}