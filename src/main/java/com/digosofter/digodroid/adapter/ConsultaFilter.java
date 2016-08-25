package com.digosofter.digodroid.adapter;

import android.widget.Filter;

import com.digosofter.digodroid.erro.ErroAndroid;

class ConsultaFilter extends Filter
{
  private AdapterConsulta _adpConsulta;

  public ConsultaFilter(AdapterConsulta adpConsulta)
  {
    this.setAdpConsulta(adpConsulta);
  }

  private AdapterConsulta getAdpConsulta()
  {
    return _adpConsulta;
  }

  @Override
  protected FilterResults performFiltering(CharSequence arrChrFiltro)
  {
    FilterResults objFilterResults;

    if (this.getAdpConsulta() == null)
    {
      return null;
    }
    if (this.getAdpConsulta().getTbl() == null)
    {
      return null;
    }
    if (arrChrFiltro == null)
    {
      this.getAdpConsulta().getTbl().setStrPesquisa(null);
      return null;
    }
    if (arrChrFiltro.length() == 0)
    {
      this.getAdpConsulta().getTbl().setStrPesquisa(null);
      return null;
    }
    this.getAdpConsulta().getTbl().setStrPesquisa(arrChrFiltro.toString());

    return null;
  }

  @Override
  protected void publishResults(CharSequence strFiltro, FilterResults objFilterResults)
  {
    try
    {
      if (this.getAdpConsulta() == null)
      {
        return;
      }
      this.getAdpConsulta().atualizarLista();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
      this.getAdpConsulta().notifyDataSetChanged();
    }
  }

  private void setAdpConsulta(AdapterConsulta adpConsulta)
  {
    _adpConsulta = adpConsulta;
  }
}