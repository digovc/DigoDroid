package com.digosofter.digodroid.database;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.dominio.DominioAndroidMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.OnChangeArg;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public abstract class ViewAndroid extends TabelaAndroid<DominioAndroidMain>
{
  private TabelaAndroid<?> _tbl;

  protected ViewAndroid(String strNome, TabelaAndroid tbl, DataBaseAndroid dbeAndroid)
  {
    super(strNome, null, dbeAndroid);

    this.setTbl(tbl);
  }

  @Override
  protected void addAppLstTbl()
  {
    // Impede que uma view seja adicionada para a lista de
    // tabelas da aplicação.
  }

  @Override
  public void apagar(int intRegistroId)
  {
    if (intRegistroId < 1)
    {
      return;
    }

    if (this.getTbl() == null)
    {
      return;
    }

    this.getTbl().apagar(intRegistroId);

    OnChangeArg arg = new OnChangeArg();

    arg.setIntRegistroId(intRegistroId);

    this.dispararEvtOnApagarReg(arg);
  }

  private void atualizarTbl()
  {
    if (this.getTbl() == null)
    {
      return;
    }

    this.getTbl().addViw(this);
  }

  @Override
  public void criar()
  {
    if (this.getIntRawFileId() == 0)
    {
      return;
    }

    String sql = "drop view if exists _viw_nome;";

    sql = sql.replace("_viw_nome", this.getSqlNome());

    this.getDbe().execSql(sql);
    this.getDbe().execSql(this.getSqlSelect());
  }

  protected abstract int getIntRawFileId();

  private String getSqlSelect()
  {
    try
    {
      if (this.getIntRawFileId() == 0)
      {
        return Utils.STR_VAZIA;
      }

      return IOUtils.toString(AppAndroid.getI().getCnt().getResources().openRawResource(this.getIntRawFileId()), "UTF-8");
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }

    return null;
  }

  public TabelaAndroid<?> getTbl()
  {
    return _tbl;
  }

  @Override
  public void setStrNome(final String strNome)
  {
    super.setStrNome(strNome);

    if (Utils.getBooStrVazia(strNome))
    {
      return;
    }

    this.setStrNomeExibicao(strNome.replace("viw_", ""));
  }

  private void setTbl(TabelaAndroid<?> tbl)
  {
    if (_tbl == tbl)
    {
      return;
    }

    _tbl = tbl;

    this.atualizarTbl();
  }
}