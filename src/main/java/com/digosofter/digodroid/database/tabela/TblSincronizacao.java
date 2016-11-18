package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.database.dominio.DominioAndroidMain;
import com.digosofter.digodroid.server.message.RspPesquisar;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Coluna;
import com.digosofter.digojava.database.Filtro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TblSincronizacao extends TblAndroidMain<DominioAndroidMain>
{
  private static TblSincronizacao _i;

  public static TblSincronizacao getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new TblSincronizacao();

    return _i;
  }

  private ColunaAndroid _clnBooSincCompleto;
  private ColunaAndroid _clnDttUltimoRecebimento;
  private ColunaAndroid _clnIntRegistroQuantidade;
  private ColunaAndroid _clnIntSincronizacaoId;
  private ColunaAndroid _clnSqlTblNome;

  private TblSincronizacao()
  {
    super("tbl_sincronizacao", AppAndroid.getI().getDbe());
  }

  void atualizarRecebimento(final TblSincronizavelMain tbl, final RspPesquisar rspPesquisar)
  {
    if (tbl == null)
    {
      return;
    }

    this.limparDados();

    this.getClnBooSincCompleto().setBooValor(rspPesquisar.getBooSincCompleto());
    this.getClnDttUltimoRecebimento().setDttValor(Calendar.getInstance());
    this.getClnIntRegistroQuantidade().setIntValor(rspPesquisar.getIntRegistroQuantidade());
    this.getClnIntSincronizacaoId().setIntValor(rspPesquisar.getIntSincronizacaoId());
    this.getClnSqlTblNome().setStrValor(tbl.getSqlNome());

    rspPesquisar.setIntSincronizacaoId(this.salvar().getClnIntId().getIntValor());
  }

  private ColunaAndroid getClnBooSincCompleto()
  {
    if (_clnBooSincCompleto != null)
    {
      return _clnBooSincCompleto;
    }

    _clnBooSincCompleto = new ColunaAndroid("boo_sinc_completo", this, Coluna.EnmTipo.BOOLEAN);

    return _clnBooSincCompleto;
  }

  private ColunaAndroid getClnDttUltimoRecebimento()
  {
    if (_clnDttUltimoRecebimento != null)
    {
      return _clnDttUltimoRecebimento;
    }

    _clnDttUltimoRecebimento = new ColunaAndroid("dtt_ultimo_recebimento", this, Coluna.EnmTipo.DATE_TIME);

    return _clnDttUltimoRecebimento;
  }

  private ColunaAndroid getClnIntRegistroQuantidade()
  {
    if (_clnIntRegistroQuantidade != null)
    {
      return _clnIntRegistroQuantidade;
    }

    _clnIntRegistroQuantidade = new ColunaAndroid("int_registro_quantidade", this, Coluna.EnmTipo.INTEGER);

    return _clnIntRegistroQuantidade;
  }

  private ColunaAndroid getClnIntSincronizacaoId()
  {
    if (_clnIntSincronizacaoId != null)
    {
      return _clnIntSincronizacaoId;
    }

    _clnIntSincronizacaoId = new ColunaAndroid("int_sincronizacao_id", this, Coluna.EnmTipo.BIGINT, TblSincronizacao.getI().getClnIntId());

    return _clnIntSincronizacaoId;
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

  public Calendar getDttUltimoRecebimento(final TblSincronizavelMain tbl)
  {
    if (tbl == null)
    {
      return null;
    }

    if (Utils.getBooStrVazia(tbl.getSqlNome()))
    {
      return null;
    }

    List<Filtro> lstFil = new ArrayList<>();

    lstFil.add(new Filtro(this.getClnSqlTblNome(), tbl.getSqlNome()));
    lstFil.add(new Filtro(this.getClnBooSincCompleto(), true));

    return ((TblSincronizacao) this.recuperar(this.getClnSqlTblNome(), tbl.getSqlNome())).getClnDttUltimoRecebimento().getDttValor();
  }

  @Override
  protected void inicializarLstCln(List<Coluna> lstCln)
  {
    super.inicializarLstCln(lstCln);

    lstCln.add(this.getClnBooSincCompleto());
    lstCln.add(this.getClnDttUltimoRecebimento());
    lstCln.add(this.getClnIntRegistroQuantidade());
    lstCln.add(this.getClnIntSincronizacaoId());
    lstCln.add(this.getClnSqlTblNome());
  }
}