package com.digosofter.digodroid.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.Util;
import com.digosofter.digodroid.erro.Erro;
import com.digosofter.digodroid.item.ItmConsulta;

public class AdpCadastro extends BaseAdapter implements Filterable {

  private List<ItmConsulta> _lstItmCadastro;
  private List<ItmConsulta> _lstItmCadastroSemFiltro;
  private LayoutInflater _objLayoutInflater;

  public AdpCadastro(Context cnt, List<ItmConsulta> lstItmCadastro) {

    try {
      this.setLstItmCadastro(lstItmCadastro);
      this.setObjLayoutInflater(LayoutInflater.from(cnt));
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  @Override
  public int getCount() {

    return this.getLstItmCadastro().size();
  }

  @Override
  public Filter getFilter() {

    this.setLstItmCadastro(this.getLstItmCadastroSemFiltro());
    return new Filter() {

      @Override
      protected FilterResults performFiltering(CharSequence strFiltro) {

        FilterResults objFilterResults = new FilterResults();
        if (strFiltro == null || strFiltro.length() == 0) {
          objFilterResults.values = AdpCadastro.this.getLstItmCadastroSemFiltro();
          objFilterResults.count = AdpCadastro.this.getLstItmCadastroSemFiltro().size();
        }
        else {
          ArrayList<ItmConsulta> lstItmCadastro = new ArrayList<ItmConsulta>();
          for (ItmConsulta itmCadastro : AdpCadastro.this.getLstItmCadastro()) {
            if (itmCadastro.getBooContemTermo(strFiltro.toString())) {
              lstItmCadastro.add(itmCadastro);
            }
          }
          objFilterResults.values = lstItmCadastro;
          objFilterResults.count = lstItmCadastro.size();
        }
        return objFilterResults;
      }

      @SuppressWarnings("unchecked")
      @Override
      protected void publishResults(CharSequence constraint, FilterResults results) {

        if (results.count == 0) {
          AdpCadastro.this.setLstItmCadastro(new ArrayList<ItmConsulta>());
          notifyDataSetChanged();
          return;
        }
        AdpCadastro.this.setLstItmCadastro((ArrayList<ItmConsulta>) results.values);
        notifyDataSetChanged();
      }
    };
  }

  @Override
  public Object getItem(int position) {

    return this.getLstItmCadastro().get(position);
  }

  @Override
  public long getItemId(int position) {

    return position;
  }

  private List<ItmConsulta> getLstItmCadastro() {

    return _lstItmCadastro;
  }

  private List<ItmConsulta> getLstItmCadastroSemFiltro() {

    return _lstItmCadastroSemFiltro;
  }

  private LayoutInflater getObjLayoutInflater() {

    return _objLayoutInflater;
  }

  @Override
  public View getView(int position, View viwReciclada, ViewGroup viwParent) {

    ItmConsulta itmCadastro;
    View viw = null;
    TextView txtRegistroNome;
    TextView txtRegistroCampo001;
    TextView txtRegistroCampo002;
    TextView txtRegistroCampo003;
    try {
      if (viwReciclada == null) {
        // viw = this.getObjLayoutInflater().inflate(R.layout.itm_cadastro,
        // null);
        viw = this.getObjLayoutInflater().inflate(R.layout.itm_cadastro, viwParent);
      }
      else {
        viw = viwReciclada;
      }
      txtRegistroNome = (TextView) viw.findViewById(R.id.itmCadastro_txtNome);
      txtRegistroCampo001 = (TextView) viw.findViewById(R.id.itmCadastro_txtCampo001);
      txtRegistroCampo002 = (TextView) viw.findViewById(R.id.itmCadastro_txtCampo002);
      txtRegistroCampo003 = (TextView) viw.findViewById(R.id.itmCadastro_txtCampo003);
      itmCadastro = this.getLstItmCadastro().get(position);
      txtRegistroNome.setText(itmCadastro.getStrNomeExibicao());
      txtRegistroCampo001.setText(itmCadastro.getStrCampo001());
      if (Util.getBooStrVazia(itmCadastro.getStrCampo1Valor())) {
        txtRegistroCampo001.setVisibility(View.GONE);
      }
      txtRegistroCampo002.setText(itmCadastro.getstrCampo2());
      if (Util.getBooStrVazia(itmCadastro.getStrCampo2Valor())) {
        txtRegistroCampo002.setVisibility(View.GONE);
      }
      txtRegistroCampo003.setText(itmCadastro.getstrCampo3());
      if (Util.getBooStrVazia(itmCadastro.getStrCampo3Valor())) {
        txtRegistroCampo003.setVisibility(View.GONE);
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return viw;
  }

  private void setLstItmCadastro(List<ItmConsulta> lstItmCadastro) {

    try {
      _lstItmCadastro = lstItmCadastro;
      if (this.getLstItmCadastroSemFiltro() == null) {
        this.setLstItmCadastroSemFiltro(_lstItmCadastro);
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private void setLstItmCadastroSemFiltro(List<ItmConsulta> lstItmCadastroSemFiltro) {

    _lstItmCadastroSemFiltro = lstItmCadastroSemFiltro;
  }

  private void setObjLayoutInflater(LayoutInflater objLayoutInflater) {

    _objLayoutInflater = objLayoutInflater;
  }
}
