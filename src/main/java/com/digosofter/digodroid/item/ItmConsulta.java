package com.digosofter.digodroid.item;

import java.util.ArrayList;
import java.util.List;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.DbColuna;

import android.database.Cursor;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItmConsulta extends ItmMain {

  private int _intRegistroId;
  private List<ItmCampo> _lstItmCampo;
  private LinearLayout _pnlCampoContainer;
  private DbTabelaAndroid<?> _tbl;
  private TextView _txtId;
  private TextView _txtNome;
  private View _viwLinha1;

  public ItmConsulta(boolean booTelaConsulta) {

    try {

      this.setBooTelaConsulta(booTelaConsulta);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void carregarDados(Cursor crs) {

    try {

      if (crs == null) {

        return;
      }

      if (this.getTbl() == null) {

        return;
      }

      this.setStrNome(crs.getString(crs.getColumnIndex(this.getTbl().getClnNome().getStrNomeSql())));
      this.setIntRegistroId(crs.getInt(crs.getColumnIndex(this.getTbl().getClnChavePrimaria().getStrNomeSql())));

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

      for (DbColuna cln : this.getTbl().getLstCln()) {

        if (cln == null) {

          continue;
        }

        if (cln.getBooChavePrimaria()) {

          continue;
        }

        if (cln.getBooNome()) {

          continue;
        }

        if (!cln.getBooVisivelConsulta() && this.getBooTelaConsulta()) {

          continue;
        }

        this.carregarDadosItem(crs, cln, this.getBooTelaConsulta());
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

      itmCampo = new ItmCampo(this.getBooTelaConsulta());

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

    boolean booResultado;

    try {

      booResultado = false;

      if (Utils.getBooStrVazia(strTermo)) {

        return true;
      }

      if (this.getBooContemTermoId(strTermo)) {

        booResultado = true;
      }

      if (this.getBooContemTermoNome(strTermo)) {

        booResultado = true;
      }

      if (this.getBooContemTermoItem(strTermo)) {

        booResultado = true;
      }

      return booResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  private boolean getBooContemTermoId(String strTermo) {

    try {

      if (Utils.getBooStrVazia(strTermo)) {

        return false;
      }

      if (!Utils.getBooNumeral(strTermo)) {

        return false;
      }

      if (this.getIntRegistroId() == Integer.valueOf(strTermo)) {

        return true;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  private boolean getBooContemTermoItem(String strTermo) {

    try {

      if (Utils.getBooStrVazia(strTermo)) {

        return false;
      }

      for (ItmCampo itmCampo : this.getLstItmCampo()) {

        if (itmCampo == null) {

          continue;
        }

        if (itmCampo.getBooContemTermo(strTermo)) {

          return true;
        }
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  private boolean getBooContemTermoNome(String strTermo) {

    try {

      if (Utils.getBooStrVazia(strTermo)) {

        return false;
      }

      if (Utils.getBooStrVazia(this.getStrNome())) {

        return false;
      }

      if (this.getStrNome().contains(strTermo)) {

        return true;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  @Override
  protected int getIntLayoutId() {

    return R.layout.itm_consulta;
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

      new ErroAndroid("Erro inesperado.\n", ex);
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

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _pnlCampoContainer;
  }

  private DbTabelaAndroid<?> getTbl() {

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

  private View getViwLinha1() {

    try {

      if (_viwLinha1 != null) {

        return _viwLinha1;
      }

      _viwLinha1 = this.getViw().findViewById(R.id.itmConsulta_lin1);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _viwLinha1;
  }

  @Override
  protected void limparLayout() {

    super.limparLayout();

    try {

      _pnlCampoContainer = null;
      _txtId = null;
      _txtNome = null;
      _viwLinha1 = null;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void montarLayout() {

    super.montarLayout();

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

      if (this.getLstItmCampo().size() < 1) {

        this.getPnlCampoContainer().setVisibility(View.GONE);
        this.getViwLinha1().setVisibility(View.GONE);
        return;
      }

      for (ItmCampo itmCampo : this.getLstItmCampo()) {

        if (itmCampo == null) {

          continue;
        }

        this.montarLayoutItem(itmCampo);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarLayoutItem(ItmCampo itmCampo) {

    try {

      if (itmCampo == null) {

        return;
      }

      if (Utils.getBooStrVazia(itmCampo.getStrValor())) {

        return;
      }

      itmCampo.montarLayout();

      this.getPnlCampoContainer().addView(itmCampo.getViw());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setIntRegistroId(int intRegistroId) {

    _intRegistroId = intRegistroId;
  }

  public void setTbl(DbTabelaAndroid<?> tbl) {

    _tbl = tbl;
  }
}