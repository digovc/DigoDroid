package com.digosofter.digodroid.database;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Dominio;
import com.digosofter.digojava.database.OnChangeArg;
import com.digosofter.digojava.erro.Erro;

import org.apache.commons.io.IOUtils;

public abstract class ViewAndroid extends TabelaAndroid<Dominio>
{
  private TabelaAndroid<?> _tbl;

  protected ViewAndroid(String strNome, TabelaAndroid tbl)
  {
    super(strNome, null);

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
    OnChangeArg arg;

    if (this.getTbl() == null)
    {
      return;
    }
    this.getTbl().apagar(intRegistroId);
    arg = new OnChangeArg();
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
    String sql;

    if (this.getIntRawFileId() == 0)
    {
      return;
    }
    sql = "drop view if exists _viw_nome;";
    sql = sql.replace("_viw_nome", this.getSqlNome());
    this.getObjDb().execSql(sql);
    this.getObjDb().execSql(this.getSqlSelect());
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
    catch (Exception ex)
    {
      new Erro("Erro inesperado.\n", ex);
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
    _tbl = tbl;
    this.atualizarTbl();
  }
}