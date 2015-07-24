package com.digosofter.digodroid.adapter;

import java.util.ArrayList;
import java.util.List;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digodroid.item.ItmConsulta;

import android.widget.Filter;

class ConsultaFilter extends Filter {

  private AdpConsulta _adpConsulta;

  public ConsultaFilter(AdpConsulta adpConsulta) {

    try {

      this.setAdpConsulta(adpConsulta);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private AdpConsulta getAdpConsulta() {

    return _adpConsulta;
  }

  @Override
  protected FilterResults performFiltering(CharSequence strFiltro) {

    FilterResults objFilterResults = new FilterResults();

    try {

      if (strFiltro == null || strFiltro.length() == 0) {

        objFilterResults.values = this.getAdpConsulta().getLstItmConsultaSemFiltro();
        objFilterResults.count = this.getAdpConsulta().getLstItmConsultaSemFiltro().size();
      }
      else {

        List<ItmConsulta> lstItmConsulta = new ArrayList<ItmConsulta>();

        for (ItmConsulta itm : this.getAdpConsulta().getLstItmConsulta()) {

          if (itm == null) {

            continue;
          }

          if (!itm.getBooContemTermo(strFiltro.toString())) {

            continue;
          }

          lstItmConsulta.add(itm);
        }

        objFilterResults.values = lstItmConsulta;
        objFilterResults.count = lstItmConsulta.size();
      }

      return objFilterResults;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void publishResults(CharSequence strFiltro, FilterResults objFilterResults) {

    try {

      if (objFilterResults.count == 0) {

        this.getAdpConsulta().setLstItmConsulta(new ArrayList<ItmConsulta>());
        this.getAdpConsulta().notifyDataSetChanged();

        return;
      }

      this.getAdpConsulta().setLstItmConsulta((ArrayList<ItmConsulta>) objFilterResults.values);
      this.getAdpConsulta().notifyDataSetChanged();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setAdpConsulta(AdpConsulta adpConsulta) {

    _adpConsulta = adpConsulta;
  }
}