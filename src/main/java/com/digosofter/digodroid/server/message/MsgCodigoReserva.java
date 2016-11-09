package com.digosofter.digodroid.server.message;

import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digojava.log.Log;

public class MsgCodigoReserva extends MsgTabelaBase<RspCodigoReserva>
{
  private int _intQuantidadeDisponibilizado = 10;

  public int getIntQuantidadeDisponibilizado()
  {
    return _intQuantidadeDisponibilizado;
  }

  @Override
  protected void onResposta(final RspCodigoReserva rsp)
  {
    if (this.getTbl() == null)
    {
      return;
    }

    if (rsp == null)
    {
      return;
    }

    if (rsp.getIntReservaCodigoId() < 1)
    {
      LogSinc.getI().addLog(Log.EnmTipo.ERRO, String.format("Não foi possível reservar códigos para a tabela %s.", this.getTbl().getStrNomeExibicao()));
      return;
    }

    this.getTbl().processarReservarCodigo(rsp);
  }

  public void setIntQuantidadeDisponibilizado(int intQuantidadeDisponibilizado)
  {
    _intQuantidadeDisponibilizado = intQuantidadeDisponibilizado;
  }
}