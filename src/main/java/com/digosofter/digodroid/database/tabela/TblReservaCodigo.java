package com.digosofter.digodroid.database.tabela;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.database.dominio.DominioAndroidMain;
import com.digosofter.digodroid.server.message.RspCodigoReserva;
import com.digosofter.digojava.database.Coluna;

import java.util.Calendar;
import java.util.List;

public class TblReservaCodigo extends TabelaAndroid<DominioAndroidMain>
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
  private ColunaAndroid _clnIntQuantidadeUtilizado;
  private ColunaAndroid _clnIntReservaCodigoServerId;
  private ColunaAndroid _clnSqlTblNome;

  private TblReservaCodigo()
  {
    super("tbl_reserva_codigo", AppAndroid.getI().getDbe());
  }

  private ColunaAndroid getClnIntCodigoInicial()
  {
    if (_clnIntCodigoInicial != null)
    {
      return _clnIntCodigoInicial;
    }

    _clnIntCodigoInicial = new ColunaAndroid("int_codigo_inicial", this, Coluna.EnmTipo.INTEGER);

    return _clnIntCodigoInicial;
  }

  private ColunaAndroid getClnIntQuantidadeDisponibilizado()
  {
    if (_clnIntQuantidadeDisponibilizado != null)
    {
      return _clnIntQuantidadeDisponibilizado;
    }

    _clnIntQuantidadeDisponibilizado = new ColunaAndroid("int_quantidade_disponibilizado", this, Coluna.EnmTipo.INTEGER);

    return _clnIntQuantidadeDisponibilizado;
  }

  private ColunaAndroid getClnIntQuantidadeUtilizado()
  {
    if (_clnIntQuantidadeUtilizado != null)
    {
      return _clnIntQuantidadeUtilizado;
    }

    _clnIntQuantidadeUtilizado = new ColunaAndroid("int_quantidade_utilizado", this, Coluna.EnmTipo.INTEGER);

    return _clnIntQuantidadeUtilizado;
  }

  private ColunaAndroid getClnIntReservaCodigoServerId()
  {
    if (_clnIntReservaCodigoServerId != null)
    {
      return _clnIntReservaCodigoServerId;
    }

    _clnIntReservaCodigoServerId = new ColunaAndroid("int_reserva_codigo_server_id", this, Coluna.EnmTipo.INTEGER);

    return _clnIntReservaCodigoServerId;
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

  @Override
  protected void inicializarLstCln(final List<Coluna> lstCln)
  {
    super.inicializarLstCln(lstCln);

    lstCln.add(this.getClnIntCodigoInicial());
    lstCln.add(this.getClnIntQuantidadeDisponibilizado());
    lstCln.add(this.getClnIntQuantidadeUtilizado());
    lstCln.add(this.getClnIntReservaCodigoServerId());
    lstCln.add(this.getClnSqlTblNome());
  }

  public void reservarCodigo(final RspCodigoReserva rsp)
  {
    if (rsp == null)
    {
      return;
    }

    if (rsp.getMsg() == null)
    {
      return;
    }

    this.limparDados();

    this.getClnBooAtivo().setBooValor(true);
    this.getClnDttAlteracao().setDttValor(Calendar.getInstance());
    this.getClnDttCadastro().setDttValor(Calendar.getInstance());
    this.getClnIntCodigoInicial().setIntValor(rsp.getIntCodigoInicial());
    this.getClnIntQuantidadeDisponibilizado().setIntValor(rsp.getMsg().getIntQuantidadeDisponibilizado());
    this.getClnIntQuantidadeUtilizado().setIntValor(0);
    this.getClnIntReservaCodigoServerId().setIntValor(rsp.getIntReservaCodigoId());
    this.getClnSqlTblNome().setStrValor(rsp.getMsg().getTbl().getSqlNome());

    this.salvar();
  }
}