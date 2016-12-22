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

public class TblSincronizacaoRecebimento extends TblAndroidMain<DominioAndroidMain>
{
  private static TblSincronizacaoRecebimento _i;

  public static TblSincronizacaoRecebimento getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new TblSincronizacaoRecebimento();

    return _i;
  }

  private ColunaAndroid _clnBooSincCompleto;
  private ColunaAndroid _clnDttRecebimento;
  private ColunaAndroid _clnIntRegistroQuantidade;
  private ColunaAndroid _clnIntServerSincronizacaoId;
  private ColunaAndroid _clnSqlTabelaNome;
  private ColunaAndroid _clnStrCritica;
  private ColunaAndroid _clnStrTabelaNomeExibicao;

  private TblSincronizacaoRecebimento()
  {
    super("tbl_sincronizacao_recebimento", AppAndroid.getI().getDbe());
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

  private ColunaAndroid getClnDttRecebimento()
  {
    if (_clnDttRecebimento != null)
    {
      return _clnDttRecebimento;
    }

    _clnDttRecebimento = new ColunaAndroid("dtt_recebimento", this, Coluna.EnmTipo.DATE_TIME);

    return _clnDttRecebimento;
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

  private ColunaAndroid getClnSqlTabelaNome()
  {
    if (_clnSqlTabelaNome != null)
    {
      return _clnSqlTabelaNome;
    }

    _clnSqlTabelaNome = new ColunaAndroid("sql_tabela_nome", this, Coluna.EnmTipo.TEXT);

    return _clnSqlTabelaNome;
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

  private ColunaAndroid getClnStrTabelaNomeExibicao()
  {
    if (_clnStrTabelaNomeExibicao != null)
    {
      return _clnStrTabelaNomeExibicao;
    }

    _clnStrTabelaNomeExibicao = new ColunaAndroid("str_tabela_nome_exibicao", this, Coluna.EnmTipo.TEXT);

    return _clnStrTabelaNomeExibicao;
  }

  Calendar getDttUltimoRecebimento(final TblSincronizavelMain tbl)
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
    lstFil.add(new Filtro(this.getClnSqlTabelaNome(), tbl.getSqlNome()));
    lstFil.add(new Filtro(this.getClnStrCritica(), Filtro.EnmOperador.IS_NULL));

    this.recuperar(lstFil);

    if (this.getClnIntId().getIntValor() < 1)
    {
      return null;
    }

    if (this.getClnDttRecebimento().getDttValor() == null)
    {
      return null;
    }

    this.getClnDttRecebimento().getDttValor().add(Calendar.MINUTE, -15);

    return this.getClnDttRecebimento().getDttValor();
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.setStrNomeExibicao("Log recebimento");

    this.getClnBooSincCompleto().setBooVisivelConsulta(true);
    this.getClnBooSincCompleto().setStrNomeExibicao("Sincronização parcial");

    this.getClnDttAlteracao().setBooVisivelConsulta(true);

    this.getClnDttCadastro().setBooVisivelConsulta(true);

    this.getClnIntRegistroQuantidade().setBooVisivelConsulta(true);
    this.getClnIntRegistroQuantidade().setStrNomeExibicao("Registro (quantidade)");

    this.getClnIntServerSincronizacaoId().setStrNomeExibicao("Código da sincronização (servidor)");

    this.getClnSqlTabelaNome().setStrNomeExibicao("Tabela (nome interno)");

    this.getClnStrCritica().setStrNomeExibicao("Crítica");

    this.getClnStrTabelaNomeExibicao().setBooNome(true);
    this.getClnStrTabelaNomeExibicao().setStrNomeExibicao("Tabela");
  }

  @Override
  protected void inicializarLstCln(List<Coluna> lstCln)
  {
    super.inicializarLstCln(lstCln);

    lstCln.add(this.getClnBooSincCompleto());
    lstCln.add(this.getClnDttRecebimento());
    lstCln.add(this.getClnIntRegistroQuantidade());
    lstCln.add(this.getClnIntServerSincronizacaoId());
    lstCln.add(this.getClnSqlTabelaNome());
    lstCln.add(this.getClnStrCritica());
    lstCln.add(this.getClnStrTabelaNomeExibicao());
  }

  void salvarRecebimento(final TblSincronizavelMain tbl, final RspPesquisar rspPesquisar)
  {
    if (tbl == null)
    {
      return;
    }

    if (rspPesquisar == null)
    {
      return;
    }

    this.limparDados();

    this.getClnBooSincCompleto().setBooValor(rspPesquisar.getBooSincCompleto());
    this.getClnDttRecebimento().setDttValor(Calendar.getInstance());
    this.getClnIntRegistroQuantidade().setIntValor(rspPesquisar.getIntRegistroQuantidade());
    this.getClnIntServerSincronizacaoId().setIntValor(rspPesquisar.getIntSincronizacaoId());
    this.getClnSqlTabelaNome().setStrValor(tbl.getSqlNome());
    this.getClnStrCritica().setStrValor(rspPesquisar.getStrCritica());
    this.getClnStrTabelaNomeExibicao().setStrValor(tbl.getStrNomeExibicao());

    rspPesquisar.setIntSincronizacaoId(this.salvar().getClnIntId().getIntValor());
  }
}