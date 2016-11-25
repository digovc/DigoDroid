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
  private ColunaAndroid _clnIntServerSincronizacaoId;
  private ColunaAndroid _clnSqlTblNome;

  private ColunaAndroid _clnStrCritica;

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
    this.getClnIntServerSincronizacaoId().setIntValor(rspPesquisar.getIntSincronizacaoId());
    this.getClnSqlTblNome().setStrValor(tbl.getSqlNome());
    this.getClnStrCritica().setStrValor(rspPesquisar.getStrCritica());

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

  private ColunaAndroid getClnIntServerSincronizacaoId()
  {
    if (_clnIntServerSincronizacaoId != null)
    {
      return _clnIntServerSincronizacaoId;
    }

    _clnIntServerSincronizacaoId = new ColunaAndroid("int_sincronizacao_id", this, Coluna.EnmTipo.BIGINT);

    return _clnIntServerSincronizacaoId;
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

    this.limparOrdem();

    this.getClnIntId().setEnmOrdem(Coluna.EnmOrdem.DECRESCENTE);

    List<Filtro> lstFil = new ArrayList<>();

    lstFil.add(new Filtro(this.getClnBooSincCompleto(), true));
    lstFil.add(new Filtro(this.getClnIntRegistroQuantidade(), 0, Filtro.EnmOperador.MAIOR));
    lstFil.add(new Filtro(this.getClnSqlTblNome(), tbl.getSqlNome()));
    lstFil.add(new Filtro(this.getClnStrCritica(), Filtro.EnmOperador.IS_NOT_NULL));

    return ((TblSincronizacao) this.recuperar(this.getClnSqlTblNome(), tbl.getSqlNome())).getClnDttUltimoRecebimento().getDttValor();
  }

  @Override
  protected void inicializarLstCln(List<Coluna> lstCln)
  {
    super.inicializarLstCln(lstCln);

    lstCln.add(this.getClnBooSincCompleto());
    lstCln.add(this.getClnDttUltimoRecebimento());
    lstCln.add(this.getClnIntRegistroQuantidade());
    lstCln.add(this.getClnIntServerSincronizacaoId());
    lstCln.add(this.getClnSqlTblNome());
    lstCln.add(this.getClnStrCritica());
  }
}