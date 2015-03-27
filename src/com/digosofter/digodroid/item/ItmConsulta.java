package com.digosofter.digodroid.item;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.DbColuna;
import com.digosofter.digojava.erro.Erro;

public class ItmConsulta extends Objeto {

  private int _intRegistroId;
  private List<ItmCampo> _lstItmCampo;
  private LinearLayout _pnlCampoContainer;
  private DbTabelaAndroid _tbl;
  private TextView _txtNome;
  private View _viw;

  public void carregarDados(Cursor crs) {

    try {

      if (crs == null) {

        return;
      }

      if (this.getTbl() == null) {

        return;
      }

      this.setStrNome(crs.getString(crs.getColumnIndex(this.getTbl().getClnNome().getStrNomeSimplificado())));
      this.setIntRegistroId(crs.getInt(crs.getColumnIndex(this.getTbl().getClnChavePrimaria().getStrNomeSimplificado())));

      this.carregarDadosItem(crs);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void carregarDadosItem(Cursor crs) {

    try {

      if (crs == null) {

        return;
      }

      if (this.getTbl() == null) {

        return;
      }

      for (DbColuna cln : this.getTbl().getLstClnConsulta()) {

        if (cln == null) {

          continue;
        }

        if (!cln.getBooVisivelConsulta()) {

          continue;
        }

        this.carregarDadosItem(crs, cln);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void carregarDadosItem(Cursor crs, DbColuna cln) {

    ItmCampo itmCampo;

    try {

      if (crs == null) {

        return;
      }

      if (cln == null) {

        return;
      }

      if (!cln.getBooVisivelConsulta()) {

        return;
      }

      itmCampo = new ItmCampo();

      itmCampo.setCln(cln);
      itmCampo.carregarDados(crs);

      this.getLstItmCampo().add(itmCampo);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public boolean getBooContemTermo(String strTermo) {

    try {

      if (Utils.getBooStrVazia(strTermo)) {

        return true;
      }

      // TODO: Resolver.
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
    return false;
  }

  public int getIntRegistroId() {

    return _intRegistroId;
  }

  private List<ItmCampo> getLstItmCampo() {

    try {

      if (_lstItmCampo != null) {

        return _lstItmCampo;
      }

      _lstItmCampo = new ArrayList<ItmCampo>();
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lstItmCampo;
  }

  private LinearLayout getPnlCampoContainer() {

    try {

      if (_pnlCampoContainer != null) {

        return _pnlCampoContainer;
      }

      _pnlCampoContainer = (LinearLayout) this.getViw().findViewById(R.id.itmConsulta_pnlCampoContainer);
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _pnlCampoContainer;
  }

  private DbTabelaAndroid getTbl() {

    return _tbl;
  }

  private TextView getTxtNome() {

    try {

      if (_txtNome != null) {

        return _txtNome;
      }

      if (this.getViw() == null) {

        return null;
      }

      _txtNome = (TextView) this.getViw().findViewById(R.id.itmConsulta_txtNome);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txtNome;
  }

  private View getViw() {

    return _viw;
  }

  public void montarLayout() {

    try {

      if (this.getViw() == null) {

        return;
      }

      this.getTxtNome().setText(this.getStrNome());

      this.montarLayoutItem();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarLayoutItem() {

    try {

      if (this.getViw() == null) {

        return;
      }

      // this.getViw().fin
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setIntRegistroId(int intRegistroId) {

    _intRegistroId = intRegistroId;
  }

  public void setTbl(DbTabelaAndroid tbl) {

    _tbl = tbl;
  }

  public void setViw(View viw) {

    _viw = viw;
  }
}