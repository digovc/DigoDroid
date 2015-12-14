package com.digosofter.digodroid.adapter;

import android.widget.Filter;

import com.digosofter.digodroid.controle.item.ItemConsulta;
import com.digosofter.digodroid.erro.ErroAndroid;

import java.util.ArrayList;
import java.util.List;

class ConsultaFilter extends Filter {

  private AdpConsulta _adpConsulta;

  public ConsultaFilter(AdpConsulta adpConsulta) {

    try {

      this.setAdpConsulta(adpConsulta);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
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

        //        objFilterResults.values = this.getAdpConsulta().getLstItmConsultaSemFiltro();
        //        objFilterResults.count = this.getAdpConsulta().getLstItmConsultaSemFiltro().size();

      } else {

        List<ItemConsulta> lstItmConsulta = new ArrayList<ItemConsulta>();

        //        for (ItemConsulta itm : this.getAdpConsulta().getLstItmConsulta()) {
        //
        //          if (itm == null) {
        //
        //            continue;
        //          }
        //
        //          if (!itm.getBooContemTermo(strFiltro.toString())) {
        //
        //            continue;
        //          }
        //
        //          lstItmConsulta.add(itm);
        //        }
        //
        //        objFilterResults.values = lstItmConsulta;
        //        objFilterResults.count = lstItmConsulta.size();
      }

      return objFilterResults;

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void publishResults(CharSequence strFiltro, FilterResults objFilterResults) {

    try {

      if (objFilterResults.count == 0) {

        //        this.getAdpConsulta().setLstItmConsulta(new ArrayList<ItemConsulta>());
        this.getAdpConsulta().atualizarLista();

        return;
      }

      //      this.getAdpConsulta().setLstItmConsulta((ArrayList<ItemConsulta>) objFilterResults.values);
      this.getAdpConsulta().atualizarLista();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void setAdpConsulta(AdpConsulta adpConsulta) {

    _adpConsulta = adpConsulta;
  }
}