package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.dominio.DominioAndroidMain;
import com.digosofter.digodroid.sinc.message.RspPesquisar;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Coluna;

import java.util.Calendar;

public class TblSincronizacao extends TabelaAndroid<DominioAndroidMain>
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

  private ColunaAndroid _clnDttUltimoRecebimento;
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

    this.recuperar(this.getClnSqlTblNome(), tbl.getSqlNome());

    this.getClnDttUltimoRecebimento().setDttValor(Calendar.getInstance());
    this.getClnSqlTblNome().setStrValor(tbl.getSqlNome());

    this.salvar();

    rspPesquisar.setIntSincronizacaoId(this.getClnIntId().getIntValor());

    TblSincronizacaoItem.getI().atualizarRecebimento(tbl, rspPesquisar);
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

    return ((TblSincronizacao) this.recuperar(this.getClnSqlTblNome(), tbl.getSqlNome())).getClnDttUltimoRecebimento().getDttValor();
  }

  @Override
  protected int inicializarLstCln(int intOrdem)
  {
    intOrdem = super.inicializarLstCln(intOrdem);

    this.getClnDttUltimoRecebimento().setIntOrdem(++intOrdem);
    this.getClnSqlTblNome().setIntOrdem(++intOrdem);

    return intOrdem;
  }
}