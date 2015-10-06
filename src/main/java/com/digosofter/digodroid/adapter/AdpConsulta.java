package com.digosofter.digodroid.adapter;

import java.util.List;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digodroid.item.ItmConsulta;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public class AdpConsulta extends BaseAdapter implements Filterable {

  private ActConsulta _actConsulta;
  private List<ItmConsulta> _lstItmConsulta;
  private List<ItmConsulta> _lstItmConsultaSemFiltro;
  private ConsultaFilter _objConsultaFilter;
  private DbTabelaAndroid<?> _tbl;

  public void apagar(int intRegistroId) {

    try {

      if (intRegistroId < 1) {

        return;
      }

      for (ItmConsulta itm : this.getLstItmConsulta()) {

        if (itm == null) {

          continue;
        }

        if (itm.getIntRegistroId() != intRegistroId) {

          continue;
        }

        this.getLstItmConsulta().remove(itm);
        break;
      }

      this.notifyDataSetChanged();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void atualizarLista() {

    try {

      this.getActConsulta().runOnUiThread(new Runnable() {

        @Override
        public void run() {

          AdpConsulta.this.atualizarListaLocal();
        }
      });
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void atualizarListaLocal() {

    try {

      AdpConsulta.this.setLstItmConsulta(null);
      AdpConsulta.this.getTbl().limparListaConsulta();
      AdpConsulta.this.notifyDataSetChanged();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private ActConsulta getActConsulta() {

    return _actConsulta;
  }

  @Override
  public int getCount() {

    try {

      return this.getLstItmConsulta().size();
    }
    catch (Exception ex) {

      return 0;
    }
    finally {
    }
  }

  @Override
  public Filter getFilter() {

    try {

      this.setLstItmConsulta(this.getLstItmConsultaSemFiltro());

      return this.getObjConsultaFilter();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  @Override
  public Object getItem(int intPosicao) {

    return this.getLstItmConsulta().get(intPosicao);
  }

  @Override
  public long getItemId(int intPosicao) {

    return this.getLstItmConsulta().get(intPosicao).getIntRegistroId();
  }

  List<ItmConsulta> getLstItmConsulta() {

    try {

      if (_lstItmConsulta != null) {

        return _lstItmConsulta;
      }

      _lstItmConsulta = this.getTbl().getLstItmConsulta();

      this.setLstItmConsultaSemFiltro(_lstItmConsulta);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lstItmConsulta;
  }

  List<ItmConsulta> getLstItmConsultaSemFiltro() {

    return _lstItmConsultaSemFiltro;
  }

  private ConsultaFilter getObjConsultaFilter() {

    try {

      if (_objConsultaFilter != null) {

        return _objConsultaFilter;
      }

      _objConsultaFilter = new ConsultaFilter(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _objConsultaFilter;
  }

  private DbTabelaAndroid<?> getTbl() {

    try {

      if (_tbl != null) {

        return _tbl;
      }

      if (this.getActConsulta() == null) {

        return (DbTabelaAndroid<?>) AppAndroid.getI().getTblSelec();
      }

      _tbl = this.getActConsulta().getTbl();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _tbl;
  }

  @Override
  @SuppressLint({ "InflateParams", "ViewHolder" })
  public View getView(int intPosicao, View viwReciclada, ViewGroup viwParent) {

    ItmConsulta itm = null;

    try {

      itm = this.getLstItmConsulta().get(intPosicao);

      itm.montarLayout();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return itm.getViw();
  }

  public void setActConsulta(ActConsulta actConsulta) {

    _actConsulta = actConsulta;
  }

  void setLstItmConsulta(List<ItmConsulta> lstItmConsulta) {

    try {

      _lstItmConsulta = lstItmConsulta;

      if (this.getLstItmConsultaSemFiltro() != null) {

        return;
      }

      this.setLstItmConsultaSemFiltro(_lstItmConsulta);
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
}