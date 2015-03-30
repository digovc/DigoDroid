package com.digosofter.digodroid.item;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.DbColuna;

public class ItmCampo extends ItmMain {

  private DbColuna _cln;
  private String _strValor;
  private TextView _txtNome;
  private TextView _txtValor;
  private View _viw;

  public void carregarDados(Cursor crs) {

    try {

      if (crs == null) {

        return;
      }

      this.setStrNome(this.getCln().getStrNomeExibicao() + ": ");
      this.setStrValor(crs.getString(crs.getColumnIndex(this.getCln().getStrNomeSql())));
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

  private TextView getTxtNome() {

    try {

      if (_txtNome != null) {

        return _txtNome;
      }

      _txtNome = (TextView) this.getViw().findViewById(R.id.itmCampo_txtNome);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txtNome;
  }

  private TextView getTxtValor() {

    try {

      if (_txtValor != null) {

        return _txtValor;
      }

      _txtValor = (TextView) this.getViw().findViewById(R.id.itmCampo_txtValor);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txtValor;
  }

  @SuppressLint("InflateParams")
  public View getViw() {

    try {

      if (_viw != null) {

        return _viw;
      }

      _viw = LayoutInflater.from(AppAndroid.getI().getCnt()).inflate(R.layout.itm_campo, null);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _viw;
  }

  public void montarLayout() {

    try {

      this.setViw(null);
      this.getTxtNome().setText(this.getStrNome());
      this.getTxtValor().setText(this.getStrValor());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
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

  private void setTxtNome(TextView txtNome) {

    _txtNome = txtNome;
  }

  private void setTxtValor(TextView txtValor) {

    _txtValor = txtValor;
  }

  private void setViw(View viw) {

    try {

      _viw = viw;

      this.setTxtNome(null);
      this.setTxtValor(null);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}