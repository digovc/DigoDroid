package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.database.dominio.DominioAndroidMain;
import com.digosofter.digodroid.database.dominio.DominioSincronizavelMain;
import com.digosofter.digodroid.server.message.RspSalvar;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Coluna;

import java.util.Calendar;
import java.util.List;

public class TblSincronizacaoEnvio extends TblAndroidMain<DominioAndroidMain>
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

  private ColunaAndroid _ClnSqlTabelaNome;
  private ColunaAndroid _clnBooSucesso;
  private ColunaAndroid _clnDttEnvio;
  private ColunaAndroid _clnIntRegistroId;
  private ColunaAndroid _clnStrCritica;
  private ColunaAndroid _clnStrTblNomeExibicao;

  private ColunaAndroid getClnBooSucesso()
  {
    if (_clnBooSucesso != null)
    {
      return _clnBooSucesso;
    }

    _clnBooSucesso = new ColunaAndroid("boo_sucesso", Coluna.EnmTipo.BOOLEAN);

    return _clnBooSucesso;
  }

  private ColunaAndroid getClnDttEnvio()
  {
    if (_clnDttEnvio != null)
    {
      return _clnDttEnvio;
    }

    _clnDttEnvio = new ColunaAndroid("dtt_envio", Coluna.EnmTipo.DATE_TIME);

    return _clnDttEnvio;
  }

  private ColunaAndroid getClnIntRegistroId()
  {
    if (_clnIntRegistroId != null)
    {
      return _clnIntRegistroId;
    }

    _clnIntRegistroId = new ColunaAndroid("int_registro_id", Coluna.EnmTipo.BIGINT);

    return _clnIntRegistroId;
  }

  private ColunaAndroid getClnSqlTabelaNome()
  {
    if (_ClnSqlTabelaNome != null)
    {
      return _ClnSqlTabelaNome;
    }

    _ClnSqlTabelaNome = new ColunaAndroid("sql_tabela_nome", Coluna.EnmTipo.TEXT);

    return _ClnSqlTabelaNome;
  }

  private ColunaAndroid getClnStrCritica()
  {
    if (_clnStrCritica != null)
    {
      return _clnStrCritica;
    }

    _clnStrCritica = new ColunaAndroid("str_critica", Coluna.EnmTipo.TEXT);

    return _clnStrCritica;
  }

  private ColunaAndroid getclnStrTblNomeExibicao()
  {
    if (_clnStrTblNomeExibicao != null)
    {
      return _clnStrTblNomeExibicao;
    }

    _clnStrTblNomeExibicao = new ColunaAndroid("str_tabela_nome_exibicao", Coluna.EnmTipo.TEXT);

    return _clnStrTblNomeExibicao;
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.setStrNomeExibicao("Log de envio");

    this.getClnBooSucesso().setBooVisivelConsulta(true);

    this.getClnDttAlteracao().setBooVisivelConsulta(true);

    this.getClnDttCadastro().setBooVisivelConsulta(true);

    this.getClnDttEnvio().setBooVisivelConsulta(true);

    this.getClnIntId().setEnmOrdem(Coluna.EnmOrdem.DECRESCENTE);

    this.getClnIntRegistroId().setBooVisivelConsulta(true);

    this.getClnSqlTabelaNome().setStrNomeExibicao("Tabela (nome interno)");

    this.getClnStrCritica().setStrNomeExibicao("Crítica");

    this.getclnStrTblNomeExibicao().setBooNome(true);
    this.getclnStrTblNomeExibicao().setStrNomeExibicao("Tabela");
  }

  @Override
  protected void inicializarLstCln(final List<Coluna> lstCln)
  {
    super.inicializarLstCln(lstCln);

    lstCln.add(this.getClnBooSucesso());
    lstCln.add(this.getClnDttEnvio());
    lstCln.add(this.getClnIntRegistroId());
    lstCln.add(this.getClnSqlTabelaNome());
    lstCln.add(this.getClnStrCritica());
    lstCln.add(this.getclnStrTblNomeExibicao());
  }

  synchronized void salvarEnvio(final TblSincronizavelMain tbl, final RspSalvar rspSalvar, final DominioSincronizavelMain objDominio)
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

    try
    {
      this.limparDados();

      this.getClnBooAtivo().setBooValor(true);
      this.getClnBooSucesso().setBooValor(Coluna.STR_VALOR_NULO.equals(objDominio.getStrSincCritica()));
      this.getClnDttAlteracao().setDttValor(Calendar.getInstance());
      this.getClnDttCadastro().setDttValor(Calendar.getInstance());
      this.getClnDttEnvio().setDttValor(Calendar.getInstance());
      this.getClnIntRegistroId().setIntValor(objDominio.getIntId());
      this.getClnIntUsuarioAlteracaoId().setIntValor(objDominio.getIntUsuarioCadastroId());
      this.getClnIntUsuarioCadastroId().setIntValor(objDominio.getIntUsuarioCadastroId());
      this.getClnSqlTabelaNome().setStrValor(tbl.getSqlNome());
      this.getClnStrCritica().setStrValor(objDominio.getStrSincCritica());
      this.getclnStrTblNomeExibicao().setStrValor(tbl.getStrNomeExibicao());

      this.salvar();
    }
    finally
    {
      this.liberarThread();
    }
  }

  synchronized void salvarEnvioServidorErro(final TblSincronizavelMain tbl, final RspSalvar rsp)
  {
    if (tbl == null)
    {
      return;
    }

    if (rsp == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(rsp.getStrCritica()))
    {
      return;
    }

    try
    {
      this.limparDados();

      this.getClnBooAtivo().setBooValor(true);
      this.getClnBooSucesso().setBooValor(false);
      this.getClnDttAlteracao().setDttValor(Calendar.getInstance());
      this.getClnDttCadastro().setDttValor(Calendar.getInstance());
      this.getClnDttEnvio().setDttValor(Calendar.getInstance());
      this.getClnSqlTabelaNome().setStrValor(tbl.getSqlNome());
      this.getClnStrCritica().setStrValor(rsp.getStrCritica());

      this.salvar();
    }
    finally
    {
      this.liberarThread();
    }
  }
}