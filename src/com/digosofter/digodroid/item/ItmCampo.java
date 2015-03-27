package com.digosofter.digodroid.item;

import android.database.Cursor;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.DbColuna;

public class ItmCampo extends Objeto {

  private DbColuna _cln;
  private String _strValor;

  public void carregarDados(Cursor crs) {

    try {

      if (crs == null) {

        return;
      }

      this.setStrValor(crs.getString(crs.getColumnIndex(this.getCln().getStrNomeSimplificado())));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private DbColuna getCln() {

    return _cln;
  }

  private String getStrValor() {

    return _strValor;
  }

  public void setCln(DbColuna cln) {

    _cln = cln;
  }

  private void setStrValor(String strValor) {

    try {

      _strValor = strValor;

      if (Utils.getBooStrVazia(_strValor)) {

        return;
      }

      if (this.getCln() == null) {

        return;
      }

      this.getCln().setStrValor(_strValor);

      _strValor = this.getCln().getStrValorExibicao();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}