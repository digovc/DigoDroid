package com.digosofter.digodroid.item;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.activity.ActCadastroMain;
import com.digosofter.digodroid.activity.ActDetalhe;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.DbColuna;

public class ItmConsulta extends ItmMain {

  private OnClickListener _evtImgAlterar_OnClickListener;
  private OnClickListener _evtImgDetalhe_OnClickListener;
  private ImageView _imgAlterar;
  private ImageView _imgDetalhe;
  private int _intRegistroId;
  private List<ItmCampo> _lstItmCampo;
  private LinearLayout _pnlCampoContainer;
  private DbTabelaAndroid _tbl;
  private TextView _txtId;
  private TextView _txtNome;
  private View _viw;

  private void alterar(View viw) {

    Intent itt;

    try {

      if (viw == null) {

        return;
      }

      if (this.getIntRegistroId() < 1) {

        return;
      }

      if (this.getTbl().getClsActCadastro() == null) {

        return;
      }

      itt = new Intent(viw.getContext(), this.getTbl().getClsActCadastro());
      itt.putExtra(ActCadastroMain.EXTRA_IN_INT_REGISTRO_ID, this.getIntRegistroId());

      viw.getContext().startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

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

  private void detalhar(View viw) {

    Intent itt;

    try {

      if (viw == null) {

        return;
      }

      if (this.getIntRegistroId() < 1) {

        return;
      }

      itt = new Intent(viw.getContext(), ActDetalhe.class);
      itt.putExtra(ActDetalhe.EXTRA_IN_INT_REGISTRO_ID, this.getIntRegistroId());

      viw.getContext().startActivity(itt);
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

      if (this.getBooContemTermoNome(strTermo)) {

        return true;
      }

      if (this.getBooContemTermoItem(strTermo)) {

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

  private OnClickListener getEvtImgAlterar_OnClickListener() {

    try {

      if (_evtImgAlterar_OnClickListener != null) {

        return _evtImgAlterar_OnClickListener;
      }

      _evtImgAlterar_OnClickListener = new OnClickListener() {

        @Override
        public void onClick(View viw) {

          try {

            ItmConsulta.this.alterar(viw);
          }
          catch (Exception ex) {

            new ErroAndroid("Erro inesperado.\n", ex);
          }
          finally {
          }
        }
      };
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _evtImgAlterar_OnClickListener;
  }

  private OnClickListener getEvtImgDetalhe_OnClickListener() {

    try {

      if (_evtImgDetalhe_OnClickListener != null) {

        return _evtImgDetalhe_OnClickListener;
      }

      _evtImgDetalhe_OnClickListener = new OnClickListener() {

        @Override
        public void onClick(View viw) {

          try {

            ItmConsulta.this.detalhar(viw);
          }
          catch (Exception ex) {

            new ErroAndroid("Erro inesperado.\n", ex);
          }
          finally {
          }
        }
      };
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _evtImgDetalhe_OnClickListener;
  }

  private ImageView getImgAlterar() {

    try {

      if (_imgAlterar != null) {

        return _imgAlterar;
      }

      _imgAlterar = (ImageView) this.getViw().findViewById(R.id.itmConsulta_imgAlterar);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _imgAlterar;
  }

  private ImageView getImgDetalhe() {

    try {

      if (_imgDetalhe != null) {

        return _imgDetalhe;
      }

      _imgDetalhe = (ImageView) this.getViw().findViewById(R.id.itmConsulta_imgDetalhe);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _imgDetalhe;
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

  @Override
  public void montarLayout() {

    super.montarLayout();

    try {

      if (this.getViw() == null) {

        return;
      }

      if (this.getTbl().getClsActCadastro() == null) {

        this.getImgAlterar().setVisibility(View.GONE);
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

      itmCampo.montarLayout();

      this.getPnlCampoContainer().addView(itmCampo.getViw());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void setEventos() {

    super.setEventos();

    try {

      this.getImgAlterar().setOnClickListener(this.getEvtImgAlterar_OnClickListener());
      this.getImgDetalhe().setOnClickListener(this.getEvtImgDetalhe_OnClickListener());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setImgAlterar(ImageView imgAlterar) {

    _imgAlterar = imgAlterar;
  }

  private void setImgDetalhe(ImageView imgDetalhe) {

    _imgDetalhe = imgDetalhe;
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

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void zerarLayout() {

    try {

      this.setImgAlterar(null);
      this.setImgDetalhe(null);
      this.setPnlCampoContainer(null);
      this.setTxtId(null);
      this.setTxtNome(null);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}