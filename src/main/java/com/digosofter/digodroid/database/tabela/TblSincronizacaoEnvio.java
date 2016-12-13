package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.database.dominio.DominioAndroidMain;
import com.digosofter.digodroid.database.dominio.DominioSincronizavelMain;
import com.digosofter.digodroid.server.message.RspSalvar;
import com.digosofter.digojava.database.Coluna;

import java.util.Calendar;
import java.util.List;

class TblSincronizacaoEnvio extends TblAndroidMain<DominioAndroidMain>
{
  private static TblSincronizacaoEnvio _i;

  public static TblSincronizacaoEnvio getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new TblSincronizacaoEnvio();

    return _i;
  }

  private ColunaAndroid _clnDttEnvio;
  private ColunaAndroid _clnIntRegistroId;
  private ColunaAndroid _clnSqlTblNome;
  private ColunaAndroid _clnStrCritica;

  private TblSincronizacaoEnvio()
  {
    super("tbl_sincronizacao_envio", AppAndroid.getI().getDbe());
  }

  private ColunaAndroid getClnDttEnvio()
  {
    if (_clnDttEnvio != null)
    {
      return _clnDttEnvio;
    }

    _clnDttEnvio = new ColunaAndroid("dtt_envio", this, Coluna.EnmTipo.DATE_TIME);

    return _clnDttEnvio;
  }

  private ColunaAndroid getClnIntRegistroId()
  {
    if (_clnIntRegistroId != null)
    {
      return _clnIntRegistroId;
    }

    _clnIntRegistroId = new ColunaAndroid("int_registro_id", this, Coluna.EnmTipo.BIGINT);

    return _clnIntRegistroId;
  }

  private ColunaAndroid getClnSqlTblNome()
  {
    if (_clnSqlTblNome != null)
    {
      return _clnSqlTblNome;
    }

    _clnSqlTblNome = new ColunaAndroid("sql_tbl_nome", this, Coluna.EnmTipo.TEXT);

    return _clnSqlTblNome;
  }

  private ColunaAndroid getClnStrCritica()
  {
    if (_clnStrCritica != null)
    {
      return _clnStrCritica;
    }

    _clnStrCritica = new ColunaAndroid("str_critica", this, Coluna.EnmTipo.TEXT);

    return _clnStrCritica;
  }

  @Override
  protected void inicializarLstCln(final List<Coluna> lstCln)
  {
    super.inicializarLstCln(lstCln);

    lstCln.add(this.getClnDttEnvio());
    lstCln.add(this.getClnIntRegistroId());
    lstCln.add(this.getClnSqlTblNome());
    lstCln.add(this.getClnStrCritica());
  }

  void salvarEnvio(final TblSincronizavelMain tbl, final RspSalvar rspSalvar, final DominioSincronizavelMain objDominio)
  {
    if (tbl == null)
    {
      return;
    }

    if (rspSalvar == null)
    {
      return;
    }

    if (objDominio == null)
    {
      return;
    }

    this.limparOrdem();

    this.getClnBooAtivo().setBooValor(true);
    this.getClnDttAlteracao().setDttValor(Calendar.getInstance());
    this.getClnDttCadastro().setDttValor(Calendar.getInstance());
    this.getClnDttEnvio().setDttValor(Calendar.getInstance());
    this.getClnIntRegistroId().setIntValor(objDominio.getIntId());
    this.getClnIntUsuarioAlteracaoId().setIntValor(objDominio.getIntUsuarioCadastroId());
    this.getClnIntUsuarioCadastroId().setIntValor(objDominio.getIntUsuarioCadastroId());
    this.getClnSqlTblNome().setStrValor(tbl.getSqlNome());
    this.getClnStrCritica().setStrValor(objDominio.getStrSincCritica());

    this.salvar();
  }
}