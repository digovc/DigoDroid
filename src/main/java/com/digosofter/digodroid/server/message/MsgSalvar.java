package com.digosofter.digodroid.server.message;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.log.LogSinc;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.log.Log;

public class MsgSalvar extends MsgTabelaBase<RspSalvar>
{
  private String _jsnLstObjDominio;

  private String getJsnLstObjDominio()
  {
    return _jsnLstObjDominio;
  }
  
  @Override
  protected void onResposta(final RspSalvar rsp)
  {
    if (this.getTbl() == null)
    {
      return;
    }

    if (rsp == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(rsp.getStrCritica()))
    {
      this.getTbl().processarSalvar(rsp);
      return;
    }

    String strErro = String.format("Erro ao tentar salvar os dados da tabela %s.\n$s", this.getTbl().getStrNomeExibicao(), rsp.getStrCritica());

    AppAndroid.getI().notificar(strErro);

    LogSinc.getI().addLog(Log.EnmTipo.ERRO, strErro);
  }

  public void setJsnLstObjDominio(String jsnLstObjDominio)
  {
    _jsnLstObjDominio = jsnLstObjDominio;
  }
}