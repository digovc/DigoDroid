package com.digosofter.digodroid.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digodroid.item.ItmConsulta;

public class AdpConsulta extends BaseAdapter implements Filterable {

  private ActConsulta _actConsulta;
  private List<ItmConsulta> _lstItmConsulta;
  private List<ItmConsulta> _lstItmConsultaSemFiltro;
  private DbTabelaAndroid _tbl;

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

      this.setLstItmConsulta(null);
      this.setLstItmConsultaSemFiltro(null);
      this.getTbl().limparListaConsulta();
      this.notifyDataSetChanged();
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
  public Object getItem(int intPosicao) {

    return this.getLstItmConsulta().get(intPosicao);
  }

  @Override
  public long getItemId(int intPosicao) {

    return this.getLstItmConsulta().get(intPosicao).getIntRegistroId();
  }

  private List<ItmConsulta> getLstItmConsulta() {

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

  private List<ItmConsulta> getLstItmConsultaSemFiltro() {

    return _lstItmConsultaSemFiltro;
  }

  private DbTabelaAndroid getTbl() {

    try {

      if (_tbl != null) {

        return _tbl;
      }

      if (this.getActConsulta() == null) {

        return (DbTabelaAndroid) AppAndroid.getI().getTblSelec();
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
  @SuppressLint("InflateParams")
  public View getView(int intPosicao, View viwReciclada, ViewGroup viwParent) {

    ItmConsulta itm;
    View viwResultado = null;

    try {

      viwResultado = viwReciclada == null ? LayoutInflater.from(this.getActConsulta()).inflate(R.layout.itm_consulta, null) : viwReciclada;

      itm = this.getLstItmConsulta().get(intPosicao);

      itm.setViw(viwResultado);
      itm.montarLayout();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return viwResultado;
  }

  public void setActConsulta(ActConsulta actConsulta) {

    _actConsulta = actConsulta;
  }

  private void setLstItmConsulta(List<ItmConsulta> lstItmConsulta) {

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