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

public class AdapterConsulta extends CursorAdapter
{
  private ActConsulta _actConsulta;
  private TabelaAndroid<?> _tbl;

  public AdapterConsulta(ActConsulta actConsulta, Cursor crs)
  {
    super(actConsulta, crs, false);

    this.setActConsulta(actConsulta);
  }

  private void atualizarActConsulta()
  {
    if (this.getActConsulta() == null)
    {
      return;
    }

    this.setTbl(this.getActConsulta().getTbl());
  }

  /**
   * Busca novamente o cursor atualizado no banco de dados.
   */
  public void atualizarLista()
  {
    if (this.getTbl() == null)
    {
      return;
    }

    this.changeCursor(this.getTbl().pesquisarConsulta());
  }

  @Override
  public void bindView(View viwItem, Context cnt, Cursor crs)
  {
    ((ItemConsulta) viwItem).carregarDados(crs);
  }

  private ActConsulta getActConsulta()
  {
    return _actConsulta;
  }

  @Override
  public Filter getFilter()
  {
    return new ConsultaFilter(this);
  }

  TabelaAndroid<?> getTbl()
  {
    return _tbl;
  }

  @Override
  public View newView(Context cnt, Cursor crs, ViewGroup viwParent)
  {
    return new ItemConsulta(cnt, this.getTbl());
  }

  private void setActConsulta(ActConsulta actConsulta)
  {
    _actConsulta = actConsulta;

    this.atualizarActConsulta();
  }

  private void setTbl(TabelaAndroid<?> tbl)
  {
    _tbl = tbl;
  }
}