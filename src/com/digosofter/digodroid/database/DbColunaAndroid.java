package com.digosofter.digodroid.database;

import android.view.MenuItem;
import android.view.SubMenu;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.database.DbColuna;

public class DbColunaAndroid extends DbColuna {

  private MenuItem _mniOrdenar;

  public DbColunaAndroid(String strNome, DbTabelaAndroid tbl, EnmTipo enmTipo) {

    super(strNome, tbl, enmTipo);
  }

  public MenuItem getMniOrdenar() {

    return _mniOrdenar;
  }

  public void processarMenuOrdenar(MenuItem mni) {

    try {

      if (mni == null) {

        return;
      }

      if (!mni.equals(this.getMniOrdenar())) {

        return;
      }

      ((DbColunaAndroid) this.getTbl().getClnOrdem()).getMniOrdenar().setChecked(false);

      this.setBooOrdem(true);
      this.getMniOrdenar().setChecked(true);

      ((DbTabelaAndroid) this.getTbl()).getMniOrdemDecrescente().setChecked(this.getBooOrdemDecrescente());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setMniOrdenar(MenuItem mniOrdenar) {

    _mniOrdenar = mniOrdenar;
  }

  public void montarMenuOrdenar(SubMenu smn) {

    try {

      if (smn == null) {

        return;
      }

      this.setMniOrdenar(smn.add(this.getStrNomeExibicao()));
      this.getMniOrdenar().setChecked(this.getBooOrdem());
      this.getMniOrdenar().setCheckable(true);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}