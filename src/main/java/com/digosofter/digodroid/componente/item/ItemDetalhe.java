package com.digosofter.digodroid.componente.item;

import android.content.Context;

import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digojava.Utils;

public class ItemDetalhe extends ItemCampo
{
  public ItemDetalhe(Context cnt, ColunaAndroid cln)
  {
    super(cnt, cln);
  }

  public void carregarDados()
  {
    if (this.getCln() == null)
    {
      return;
    }

    String strValorFormatado = (!Utils.getBooStrVazia(this.getCln().getStrValorExibicao())) ? this.getCln().getStrValorExibicao() : null;

    this.getLblRegistroNome().setText(this.getCln().getStrNomeExibicao() + ": ");
    this.getLblRegistroValor().setText(strValorFormatado);
  }
}