package com.digosofter.digodroid.database.tabela;

import android.database.Cursor;

import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.database.dominio.DominioAndroidMain;
import com.digosofter.digodroid.server.message.RspCodigoReserva;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.ColunaMain;
import com.digosofter.digojava.database.Filtro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TblReservaCodigo extends TblAndroidMain<DominioAndroidMain>
{
  private static TblReservaCodigo _i;

  public static TblReservaCodigo getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new TblReservaCodigo();

    return _i;
  }

  private ColunaAndroid _clnIntCodigoInicial;
  private ColunaAndroid _clnIntQuantidadeDisponibilizado;
  private ColunaAndroid _clnIntQuantidadeRestante;
  private ColunaAndroid _clnIntReservaCodigoServerId;
  private ColunaAndroid _clnSqlTabelaNome;

  public boolean getBooCodigoDisponivel(final TblAndroidMain tbl)
  {
    if (tbl == null)
    {
      return false;
    }

    if (Utils.getBooStrVazia(tbl.getSqlNome()))
    {
      return false;
    }

    List<Filtro> lstFil = new ArrayList<>();

    lstFil.add(new Filtro(this.getClnBooAtivo(), true));
    lstFil.add(new Filtro(this.getClnIntQuantidadeDisponibilizado(), 0, Filtro.EnmOperador.MAIOR));
    lstFil.add(new Filtro(this.getClnIntQuantidadeRestante(), 0, Filtro.EnmOperador.MAIOR));
    lstFil.add(new Filtro(this.getClnSqlTabelaNome(), tbl.getSqlNome()));

    Cursor crs = this.pesquisar(lstFil);

    if (crs == null)
    {
      return false;
    }

    if (!crs.moveToFirst())
    {
      return false;
    }

    return true;
  }

  private ColunaAndroid getClnIntCodigoInicial()
  {
    if (_clnIntCodigoInicial != null)
    {
      return _clnIntCodigoInicial;
    }

    _clnIntCodigoInicial = new ColunaAndroid("int_codigo_inicial", ColunaMain.EnmTipo.INTEGER);

    return _clnIntCodigoInicial;
  }

  private ColunaAndroid getClnIntQuantidadeDisponibilizado()
  {
    if (_clnIntQuantidadeDisponibilizado != null)
    {
      return _clnIntQuantidadeDisponibilizado;
    }

    _clnIntQuantidadeDisponibilizado = new ColunaAndroid("int_quantidade_disponibilizado", ColunaMain.EnmTipo.INTEGER);

    return _clnIntQuantidadeDisponibilizado;
  }

  private ColunaAndroid getClnIntQuantidadeRestante()
  {
    if (_clnIntQuantidadeRestante != null)
    {
      return _clnIntQuantidadeRestante;
    }

    _clnIntQuantidadeRestante = new ColunaAndroid("int_quantidade_restante", ColunaMain.EnmTipo.INTEGER);

    return _clnIntQuantidadeRestante;
  }

  private ColunaAndroid getClnIntReservaCodigoServerId()
  {
    if (_clnIntReservaCodigoServerId != null)
    {
      return _clnIntReservaCodigoServerId;
    }

    _clnIntReservaCodigoServerId = new ColunaAndroid("int_reserva_codigo_server_id", ColunaMain.EnmTipo.INTEGER);

    return _clnIntReservaCodigoServerId;
  }

  private ColunaAndroid getClnSqlTabelaNome()
  {
    if (_clnSqlTabelaNome != null)
    {
      return _clnSqlTabelaNome;
    }

    _clnSqlTabelaNome = new ColunaAndroid("sql_tabela_nome", ColunaMain.EnmTipo.TEXT);

    return _clnSqlTabelaNome;
  }

  int getIntCodigoDisponivelQuantidade(final TblSincronizavelMain tbl)
  {
    if (tbl == null)
    {
      return 0;
    }

    ArrayList<Filtro> lstFil = new ArrayList<>();

    lstFil.add(new Filtro(this.getClnBooAtivo(), true));
    lstFil.add(new Filtro(this.getClnIntQuantidadeDisponibilizado(), 0, Filtro.EnmOperador.MAIOR));
    lstFil.add(new Filtro(this.getClnIntQuantidadeRestante(), 0, Filtro.EnmOperador.MAIOR));
    lstFil.add(new Filtro(this.getClnSqlTabelaNome(), tbl.getSqlNome()));

    Cursor crs = this.pesquisar(lstFil);

    if (crs == null)
    {
      return 0;
    }

    if (!crs.moveToFirst())
    {
      return 0;
    }

    int intResultado = 0;

    do
    {
      intResultado += crs.getInt(crs.getColumnIndex(this.getClnIntQuantidadeRestante().getSqlNome()));
    }
    while (crs.moveToNext());

    crs.close();

    return intResultado;
  }

  @Override
  protected void inicializarLstCln(final List<ColunaMain> lstCln)
  {
    super.inicializarLstCln(lstCln);

    lstCln.add(this.getClnIntCodigoInicial());
    lstCln.add(this.getClnIntQuantidadeDisponibilizado());
    lstCln.add(this.getClnIntQuantidadeRestante());
    lstCln.add(this.getClnIntReservaCodigoServerId());
    lstCln.add(this.getClnSqlTabelaNome());
  }

  public void prepararProximoCodigoDisponivel(final TblSincronizavelMain tbl)
  {
    if (tbl == null)
    {
      return;
    }

    tbl.getClnIntId().limpar();
    tbl.getClnIntReservaCodigoId().limpar();

    if (Utils.getBooStrVazia(tbl.getSqlNome()))
    {
      return;
    }

    this.limparOrdem();

    this.getClnIntId().setEnmOrdem(ColunaMain.EnmOrdem.CRESCENTE);

    ArrayList<Filtro> lstFil = new ArrayList<>();

    lstFil.add(new Filtro(this.getClnBooAtivo(), true));
    lstFil.add(new Filtro(this.getClnIntQuantidadeDisponibilizado(), 0, Filtro.EnmOperador.MAIOR));
    lstFil.add(new Filtro(this.getClnIntQuantidadeRestante(), 0, Filtro.EnmOperador.MAIOR));
    lstFil.add(new Filtro(this.getClnSqlTabelaNome(), tbl.getSqlNome()));

    try
    {
      this.recuperar(lstFil);

      if (this.getClnIntId().getIntValor() < 1)
      {
        return;
      }

      int intCodigoProximo = (this.getClnIntCodigoInicial().getIntValor() + this.getClnIntQuantidadeDisponibilizado().getIntValor() - this.getClnIntQuantidadeRestante().getIntValor());

      tbl.getClnIntId().setIntValor(intCodigoProximo);
      tbl.getClnIntReservaCodigoId().setIntValor(this.getClnIntReservaCodigoServerId().getIntValor());

      this.getClnIntQuantidadeRestante().setIntValor(this.getClnIntQuantidadeRestante().getIntValor() - 1);

      this.salvar();
    }
    finally
    {
      this.liberarThread();
    }
  }

  void reservarCodigo(final RspCodigoReserva rsp)
  {
    if (rsp == null)
    {
      return;
    }

    if (rsp.getMsg() == null)
    {
      return;
    }

    try
    {
      this.limparDados();

      this.getClnBooAtivo().setBooValor(true);
      this.getClnDttAlteracao().setDttValor(Calendar.getInstance());
      this.getClnDttCadastro().setDttValor(Calendar.getInstance());
      this.getClnIntCodigoInicial().setIntValor(rsp.getIntCodigoInicial());
      this.getClnIntQuantidadeDisponibilizado().setIntValor(rsp.getMsg().getIntQuantidadeDisponibilizado());
      this.getClnIntQuantidadeRestante().setIntValor(rsp.getMsg().getIntQuantidadeDisponibilizado()); // TODO: Recuperar a quantidade restante do servidor.
      this.getClnIntReservaCodigoServerId().setIntValor(rsp.getIntReservaCodigoId());
      this.getClnSqlTabelaNome().setStrValor(rsp.getMsg().getTbl().getSqlNome());

      this.salvar();
    }
    finally
    {
      this.liberarThread();
    }
  }
}