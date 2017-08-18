package com.digosofter.digodroid.database;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.database.dominio.DominioAndroidMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Coluna;
import com.digosofter.digojava.database.OnChangeArg;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;

public abstract class ViewAndroid extends TblAndroidMain<DominioAndroidMain>
{
  private ColunaAndroid _clnIntId;
  private TblAndroidMain _tbl;
  private TblAndroidMain _tblPrincipal;

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

  @Override
  public void criar()
  {
    if (!this.getTbl().getBooRecemCriada())
    {
      return;
    }

    if (this.getIntRawFileId() == 0)
    {
      return;
    }

    this.getDbe().execSql(String.format("drop view if exists %s;", this.getSqlNome()));

    this.getDbe().execSql(this.getSqlView());
  }

  @Override
  public ColunaAndroid getClnBooAtivo()
  {
    return null;
  }

  @Override
  public ColunaAndroid getClnDttAlteracao()
  {
    return null;
  }

  @Override
  public ColunaAndroid getClnDttCadastro()
  {
    return null;
  }

  public ColunaAndroid getClnIntId()
  {
    if (_clnIntId != null)
    {
      return _clnIntId;
    }

    _clnIntId = new ColunaAndroid(this.getSqlClnIntIdNome(), Coluna.EnmTipo.BIGINT);

    return _clnIntId;
  }

  @Override
  public ColunaAndroid getClnIntUsuarioAlteracaoId()
  {
    return null;
  }

  @Override
  public ColunaAndroid getClnIntUsuarioCadastroId()
  {
    return null;
  }

  @Override
  public ColunaAndroid getClnStrObservacao()
  {
    return null;
  }

  @Override
  public Class<? extends ActMain> getClsActCadastro()
  {
    return this.getTbl().getClsActCadastro();
  }

  protected abstract int getIntRawFileId();

  protected abstract String getSqlClnIntIdNome();

  private String getSqlView()
  {
    try
    {
      if (this.getIntRawFileId() == 0)
      {
        return Utils.STR_VAZIA;
      }

      return IOUtils.toString(AppAndroid.getI().getActAtual().getResources().openRawResource(this.getIntRawFileId()), "UTF-8");
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }

    return null;
  }

  public TblAndroidMain getTbl()
  {
    return _tbl;
  }

  public TblAndroidMain getTblPrincipal()
  {
    if (_tblPrincipal != null)
    {
      return _tblPrincipal;
    }

    _tblPrincipal = this.getTbl();

    return _tblPrincipal;
  }

  @Override
  protected void inicializarLstCln(List<Coluna> lstCln)
  {
    // super.inicializarLstCln(intOrdem);

    lstCln.add(this.getClnIntId());
  }

  void iniciar(final TblAndroidMain tbl)
  {
    this.setTbl(tbl);

    this.iniciar(tbl.getDbe());
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

  private void setTbl(TblAndroidMain<?> tbl)
  {
    _tbl = tbl;
  }
}