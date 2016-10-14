package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.dominio.DominioAndroidMain;
import com.digosofter.digodroid.server.message.RspPesquisar;
import com.digosofter.digojava.database.Coluna;

public class TblSincronizacaoItem extends TabelaAndroid<DominioAndroidMain>
{
  private static TblSincronizacaoItem _i;

  public static TblSincronizacaoItem getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new TblSincronizacaoItem();

    return _i;
  }

  private ColunaAndroid _clnIntRegistroQuantidade;
  private ColunaAndroid _clnIntSincronizacaoId;

  private TblSincronizacaoItem()
  {
    super("tbl_sincronizacao_item", AppAndroid.getI().getDbe());
  }

  void atualizarRecebimento(final TblSincronizavelMain tbl, final RspPesquisar rspPesquisar)
  {
    if (rspPesquisar == null)
    {
      return;
    }

    if (rspPesquisar.getIntSincronizacaoId() < 1)
    {
      return;
    }

    this.limparColunas();

    this.getClnIntRegistroQuantidade().setIntValor(rspPesquisar.getIntRegistroQuantidade());
    this.getClnIntSincronizacaoId().setIntValor(rspPesquisar.getIntSincronizacaoId());

    this.salvar();
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

  @Override
  protected int inicializarLstCln(int intOrdem)
  {
    intOrdem = super.inicializarLstCln(intOrdem);

    this.getClnIntRegistroQuantidade().setIntOrdem(++intOrdem);
    this.getClnIntSincronizacaoId().setIntOrdem(++intOrdem);

    return intOrdem;
  }
}