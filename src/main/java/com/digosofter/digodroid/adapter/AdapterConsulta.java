package com.digosofter.digodroid.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Filter;

import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.controle.item.ItemConsulta;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;

public class AdapterConsulta extends CursorAdapter {

  private ActConsulta _actConsulta;
  private TabelaAndroid<?> _tbl;

  public AdapterConsulta(ActConsulta actConsulta, Cursor crs) {

    super(actConsulta, crs, false);

    try {

      this.setActConsulta(actConsulta);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void atualizarActConsulta() {

    try {

      if (this.getActConsulta() == null) {

        return;
      }

      this.setTbl(this.getActConsulta().getTbl());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Busca novamente o cursor atualizado no banco de dados.
   */
  public void atualizarLista() {

    try {

      if (this.getTbl() == null) {

        return;
      }

      this.changeCursor(this.getTbl().pesquisarConsulta());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void bindView(View viwItem, Context cnt, Cursor crs) {

    ((ItemConsulta) viwItem).carregarDados(crs);
  }

  private ActConsulta getActConsulta() {

    return _actConsulta;
  }

  @Override
  public Filter getFilter() {

    return new ConsultaFilter(this);
  }

  TabelaAndroid<?> getTbl() {

    return _tbl;
  }

  @Override
  public View newView(Context cnt, Cursor crs, ViewGroup viwParent) {

    return new ItemConsulta(cnt, this.getTbl());
  }

  private void setActConsulta(ActConsulta actConsulta) {

    try {

      _actConsulta = actConsulta;

      this.atualizarActConsulta();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setTbl(TabelaAndroid<?> tbl) {

    _tbl = tbl;
  }
}