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
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.DbColuna;
import com.digosofter.digojava.erro.Erro;

public class ItmConsulta extends ItmMain {

  private int _intRegistroId;
  private List<ItmCampo> _lstItmCampo;
  private LinearLayout _pnlCampoContainer;
  private DbTabelaAndroid _tbl;
  private TextView _txtId;
  private TextView _txtNome;
  private View _viw;

  public void carregarDados(Cursor crs, boolean booSomenteClnConsulta) {

    try {

      if (crs == null) {

        return;
      }

      if (this.getTbl() == null) {

        return;
      }

      this.setStrNome(crs.getString(crs.getColumnIndex(this.getTbl().getClnNome().getStrNomeSql())));
      this.setIntRegistroId(crs.getInt(crs.getColumnIndex(this.getTbl().getClnChavePrimaria().getStrNomeSql())));

      this.carregarDadosItem(crs, booSomenteClnConsulta);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void carregarDadosItem(Cursor crs, boolean booSomenteClnConsulta) {

    try {

      if (crs == null) {

        return;
      }

      if (this.getTbl() == null) {

        return;
      }

      for (DbColuna cln : this.getTbl().getLstCln()) {

        if (cln == null) {

          continue;
        }

        if (cln.getBooChavePrimaria()) {

          continue;
        }

        if (cln.getBooClnNome()) {

          continue;
        }

        if (!cln.getBooVisivelConsulta() && booSomenteClnConsulta) {

          continue;
        }

        this.carregarDadosItem(crs, cln, booSomenteClnConsulta);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void carregarDadosItem(Cursor crs, DbColuna cln, boolean booSomenteClnConsulta) {

    ItmCampo itmCampo;

    try {

      if (crs == null) {

        return;
      }

      if (!cln.getBooVisivelConsulta() && booSomenteClnConsulta) {

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

  public List<ItmCampo> getLstItmCampo() {

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

  private TextView getTxtId() {

    try {

      if (_txtId != null) {

        return _txtId;
      }

      if (this.getViw() == null) {

        return null;
      }

      _txtId = (TextView) this.getViw().findViewById(R.id.itmConsulta_txtId);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txtId;
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

  public View getViw() {

    return _viw;
  }

  public void montarLayout() {

    try {

      if (this.getViw() == null) {

        return;
      }

      this.getTxtId().setText(String.valueOf(this.getIntRegistroId()) + " ");
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

      this.getPnlCampoContainer().removeAllViews();

      for (ItmCampo itmCampo : this.getLstItmCampo()) {

        if (itmCampo == null) {

          continue;
        }

        this.montarLayoutItem(itmCampo);
      }
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarLayoutItem(ItmCampo itmCampo) {

    try {

      if (itmCampo == null) {

        return;
      }

      itmCampo.montarLayout();

      this.getPnlCampoContainer().addView(itmCampo.getViw());
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

  private void setPnlCampoContainer(LinearLayout pnlCampoContainer) {

    _pnlCampoContainer = pnlCampoContainer;
  }

  public void setTbl(DbTabelaAndroid tbl) {

    _tbl = tbl;
  }

  private void setTxtId(TextView txtId) {

    _txtId = txtId;

  }

  private void setTxtNome(TextView txtNome) {

    _txtNome = txtNome;
  }

  public void setViw(View viw) {

    try {

      _viw = viw;

      this.zerarLayout();
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void zerarLayout() {

    try {

      this.setPnlCampoContainer(null);
      this.setTxtId(null);
      this.setTxtNome(null);
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}