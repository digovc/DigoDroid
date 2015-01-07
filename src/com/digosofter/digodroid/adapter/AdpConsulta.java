package com.digosofter.digodroid.adapter;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digodroid.item.ItmConsulta;

public class AdpConsulta extends BaseAdapter implements Filterable {

  private List<ItmConsulta> _lstItmConsulta;
  private List<ItmConsulta> _lstItmConsultaSemFiltro;
  private LayoutInflater _objLayoutInflater;

  public AdpConsulta(Context cnt, List<ItmConsulta> lstItmConsulta) {

    try {

      this.setLstItmConsulta(lstItmConsulta);
      this.setObjLayoutInflater(LayoutInflater.from(cnt));
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  @Override
  public int getCount() {

    return this.getLstItmConsulta().size();
  }

  @Override
  public Filter getFilter() {

    this.setLstItmConsulta(this.getLstItmConsultaSemFiltro());
    return new Filter() {

      @Override
      protected FilterResults performFiltering(CharSequence strFiltro) {

        FilterResults objFilterResults = new FilterResults();

        if (strFiltro == null || strFiltro.length() == 0) {

          objFilterResults.values = AdpConsulta.this.getLstItmConsultaSemFiltro();
          objFilterResults.count = AdpConsulta.this.getLstItmConsultaSemFiltro().size();
        }
        else {

          ArrayList<ItmConsulta> lstItmConsulta = new ArrayList<ItmConsulta>();

          for (ItmConsulta itm : AdpConsulta.this.getLstItmConsulta()) {

            if (itm.getBooContemTermo(strFiltro.toString())) {

              lstItmConsulta.add(itm);
            }
          }

          objFilterResults.values = lstItmConsulta;
          objFilterResults.count = lstItmConsulta.size();
        }

        return objFilterResults;
      }

      @SuppressWarnings("unchecked")
      @Override
      protected void publishResults(CharSequence constraint, FilterResults results) {

        try {

          if (results.count == 0) {

            AdpConsulta.this.setLstItmConsulta(new ArrayList<ItmConsulta>());
            AdpConsulta.this.notifyDataSetChanged();

            return;
          }

          AdpConsulta.this.setLstItmConsulta((ArrayList<ItmConsulta>) results.values);
          AdpConsulta.this.notifyDataSetChanged();
        }
        catch (Exception ex) {

          new ErroAndroid("Erro inesperado.\n", ex);
        }
        finally {
        }
      }
    };
  }

  @Override
  public Object getItem(int position) {

    return this.getLstItmConsulta().get(position);
  }

  @Override
  public long getItemId(int position) {

    return position;
  }

  private List<ItmConsulta> getLstItmConsulta() {

    return _lstItmConsulta;
  }

  private List<ItmConsulta> getLstItmConsultaSemFiltro() {

    return _lstItmConsultaSemFiltro;
  }

  private LayoutInflater getObjLayoutInflater() {

    return _objLayoutInflater;
  }

  @SuppressLint("InflateParams")
  @Override
  public View getView(int position, View viwReciclada, ViewGroup viwParent) {

    ItmConsulta itmCadastro;
    TextView txtRegistroCampo001;
    TextView txtRegistroCampo002;
    TextView txtRegistroCampo003;
    TextView txtRegistroNome;
    View viwResultado = null;

    try {

      if (viwReciclada == null) {

        viwResultado = this.getObjLayoutInflater().inflate(R.layout.itm_consulta, null);
      }
      else {

        viwResultado = viwReciclada;
      }

      txtRegistroNome = (TextView) viwResultado.findViewById(R.id.itmCadastro_txtNome);

      txtRegistroCampo001 = (TextView) viwResultado.findViewById(R.id.itmCadastro_txtCampo001);
      txtRegistroCampo002 = (TextView) viwResultado.findViewById(R.id.itmCadastro_txtCampo002);
      txtRegistroCampo003 = (TextView) viwResultado.findViewById(R.id.itmCadastro_txtCampo003);

      itmCadastro = this.getLstItmConsulta().get(position);

      txtRegistroNome.setText(itmCadastro.getStrNomeExibicao());
      txtRegistroCampo001.setText(itmCadastro.getStrCampo001());

      if (UtilsAndroid.getBooStrVazia(itmCadastro.getStrCampo1Valor())) {

        txtRegistroCampo001.setVisibility(View.GONE);
      }

      txtRegistroCampo002.setText(itmCadastro.getstrCampo2());

      if (UtilsAndroid.getBooStrVazia(itmCadastro.getStrCampo2Valor())) {

        txtRegistroCampo002.setVisibility(View.GONE);
      }

      txtRegistroCampo003.setText(itmCadastro.getstrCampo3());

      if (UtilsAndroid.getBooStrVazia(itmCadastro.getStrCampo3Valor())) {

        txtRegistroCampo003.setVisibility(View.GONE);
      }
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return viwResultado;
  }

  private void setLstItmConsulta(List<ItmConsulta> lstItmConsulta) {

    try {

      _lstItmConsulta = lstItmConsulta;

      if (this.getLstItmConsultaSemFiltro() == null) {

        this.setLstItmConsultaSemFiltro(_lstItmConsulta);
      }
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private void setLstItmConsultaSemFiltro(List<ItmConsulta> lstItmConsultaSemFiltro) {

    _lstItmConsultaSemFiltro = lstItmConsultaSemFiltro;
  }

  private void setObjLayoutInflater(LayoutInflater objLayoutInflater) {

    _objLayoutInflater = objLayoutInflater;
  }
}
