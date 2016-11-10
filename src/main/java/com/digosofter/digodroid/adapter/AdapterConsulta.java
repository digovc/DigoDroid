package com.digosofter.digodroid.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Filter;

import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.controle.item.ItemConsulta;
import com.digosofter.digodroid.database.TblAndroidMain;

public class AdapterConsulta extends CursorAdapter
{
  private ActConsulta _actConsulta;
  private Cursor _crsConsulta;
  private TblAndroidMain<?> _tbl;

  public AdapterConsulta(ActConsulta actConsulta)
  {
    super(actConsulta, null, 0);

    this.setActConsulta(actConsulta);

    this.iniciar();
  }

  private void atualizarCrsConsulta(final Cursor crsConsulta)
  {
    Cursor crsConsultaAnterior = this.swapCursor(crsConsulta);

    if (crsConsultaAnterior == null)
    {
      return;
    }

    if (crsConsultaAnterior.isClosed())
    {
      return;
    }

    crsConsultaAnterior.close();
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

    if (this.getActConsulta() == null)
    {
      return;
    }

    this.setCrsConsulta(this.getTbl().pesquisarConsulta(this.getActConsulta()));
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

  private Cursor getCrsConsulta()
  {
    return _crsConsulta;
  }

  @Override
  public Filter getFilter()
  {
    return new ConsultaFilter(this);
  }

  TblAndroidMain<?> getTbl()
  {
    if (_tbl != null)
    {
      return _tbl;
    }

    if (this.getActConsulta() == null)
    {
      return null;
    }

    return _tbl = this.getActConsulta().getTbl();
  }

  private void inicializar()
  {
    this.inicializarCrsConsulta();
  }

  private void inicializarCrsConsulta()
  {
    if (this.getTbl() == null)
    {
      return;
    }

    this.setCrsConsulta(this.getTbl().pesquisarConsulta(this.getActConsulta()));
  }

  private void iniciar()
  {
    this.inicializar();
  }

  @Override
  public View newView(Context cnt, Cursor crs, ViewGroup viwParent)
  {
    return new ItemConsulta(cnt, this.getTbl());
  }

  private void setActConsulta(ActConsulta actConsulta)
  {
    if (_actConsulta == actConsulta)
    {
      return;
    }

    _actConsulta = actConsulta;
  }

  private void setCrsConsulta(final Cursor crsConsulta)
  {
    if (_crsConsulta == crsConsulta)
    {
      return;
    }

    _crsConsulta = crsConsulta;

    this.atualizarCrsConsulta(crsConsulta);
  }
}