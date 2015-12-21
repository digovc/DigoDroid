package com.digosofter.digodroid.controle.item;

import android.content.Context;
import android.database.Cursor;

import com.digosofter.digodroid.database.DbColunaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public class ItemDetalhe extends ItemCampo {

  public ItemDetalhe(Context cnt, DbColunaAndroid cln) {

    super(cnt, cln, null);
  }

  @Override
  protected void carregarDados(Cursor crs) {

    super.carregarDados(crs);

    String strValorFormatado;

    try {

      if (this.getCln() == null) {

        return;
      }

      strValorFormatado = (!Utils.getBooStrVazia(this.getCln().getStrValorExibicao())) ? this.getCln().getStrValorExibicao() : "-";

      this.getLblRegistroValor().setStrTexto(strValorFormatado);
      this.getLblRegistroNome().setStrTexto(this.getCln().getStrNomeExibicao());

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}
