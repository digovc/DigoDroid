package com.digosofter.digodroid.database;

import android.view.MenuItem;
import android.view.SubMenu;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.database.DbColuna;

public class DbColunaAndroid extends DbColuna {

  private MenuItem _mniCampo;
  private MenuItem _mniOrdenar;

  public DbColunaAndroid(String strNome, DbTabelaAndroid tbl, EnmTipo enmTipo) {

    super(strNome, tbl, enmTipo);
  }

  MenuItem getMniCampo() {

    return _mniCampo;
  }

  MenuItem getMniOrdenar() {

    return _mniOrdenar;
  }

  void montarMenuCampo(SubMenu smn) {

    try {

      if (smn == null) {

        return;
      }

      if (this.getBooChavePrimaria()) {

        return;
      }

      if (this.getBooClnNome()) {

        return;
      }

      this.setMniCampo(smn.add(this.getStrNomeExibicao()));
      this.getMniCampo().setChecked(this.getBooVisivelConsulta());
      this.getMniCampo().setCheckable(true);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  void montarMenuOrdenar(SubMenu smn) {

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

  void processarMenuCampo(MenuItem mni) {

    try {

      if (mni == null) {

        return;
      }

      if (!mni.equals(this.getMniCampo())) {

        return;
      }

      this.getMniCampo().setChecked(!this.getMniCampo().isChecked());
      this.setBooVisivelConsulta(this.getMniCampo().isChecked());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  void processarMenuOrdenar(MenuItem mni) {

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

  private void setMniCampo(MenuItem mniCampo) {

    _mniCampo = mniCampo;
  }

  private void setMniOrdenar(MenuItem mniOrdenar) {

    _mniOrdenar = mniOrdenar;
  }
}