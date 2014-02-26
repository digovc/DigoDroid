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
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;
import com.digosofter.digodroid.item.ItmConsulta;

public class AdpCadastro extends BaseAdapter implements Filterable {

	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private LayoutInflater _objLayoutInflater;

	private LayoutInflater getObjLayoutInflater() {
		return _objLayoutInflater;
	}

	private void setObjLayoutInflater(LayoutInflater objLayoutInflater) {
		_objLayoutInflater = objLayoutInflater;
	}

	private List<ItmConsulta> _lstItmCadastro;

	private List<ItmConsulta> getLstItmCadastro() {
		return _lstItmCadastro;
	}

	private void setLstItmCadastro(List<ItmConsulta> lstItmCadastro) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			_lstItmCadastro = lstItmCadastro;

			if (this.getLstItmCadastroSemFiltro() == null) {
				this.setLstItmCadastroSemFiltro(_lstItmCadastro);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private List<ItmConsulta> _lstItmCadastroSemFiltro;

	private List<ItmConsulta> getLstItmCadastroSemFiltro() {
		return _lstItmCadastroSemFiltro;
	}

	private void setLstItmCadastroSemFiltro(List<ItmConsulta> lstItmCadastroSemFiltro) {
		_lstItmCadastroSemFiltro = lstItmCadastroSemFiltro;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public AdpCadastro(Context context, List<ItmConsulta> lstItmCadastro) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setLstItmCadastro(lstItmCadastro);
			this.setObjLayoutInflater(LayoutInflater.from(context));

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS

	@Override
	public int getCount() {
		return this.getLstItmCadastro().size();
	}

	@Override
	public Object getItem(int position) {
		return this.getLstItmCadastro().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// VARI�VEIS

		ItmConsulta objItmCadastro = this.getLstItmCadastro().get(position);
		View objView = this.getObjLayoutInflater().inflate(R.layout.itm_cadastro, null);

		// FIM VARI�VEIS
		try {
			// A��ES

			TextView txtRegistroNome = (TextView) objView.findViewById(R.id.itmCadastro_txtNome);
			TextView txtRegistroCampo001 = (TextView) objView.findViewById(R.id.itmCadastro_txtCampo001);
			TextView txtRegistroCampo002 = (TextView) objView.findViewById(R.id.itmCadastro_txtCampo002);
			TextView txtRegistroCampo003 = (TextView) objView.findViewById(R.id.itmCadastro_txtCampo003);

			txtRegistroNome.setText(objItmCadastro.getStrNomeExibicao());

			txtRegistroCampo001.setText(objItmCadastro.getStrCampo001());
			if (objItmCadastro.getStrCampo001Valor() == null || objItmCadastro.getStrCampo001Valor().equals(Utils.STRING_VAZIA)) {
				txtRegistroCampo001.setVisibility(View.GONE);
			}

			txtRegistroCampo002.setText(objItmCadastro.getstrCampo002());
			if (objItmCadastro.getStrCampo002Valor() == null || objItmCadastro.getStrCampo002Valor().equals(Utils.STRING_VAZIA)) {
				txtRegistroCampo002.setVisibility(View.GONE);
			}

			txtRegistroCampo003.setText(objItmCadastro.getstrCampo003());
			if (objItmCadastro.getStrCampo003Valor() == null || objItmCadastro.getStrCampo003Valor().equals(Utils.STRING_VAZIA)) {
				txtRegistroCampo003.setVisibility(View.GONE);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return objView;
	}

	@Override
	public Filter getFilter() {

		this.setLstItmCadastro(this.getLstItmCadastroSemFiltro());

		return new Filter() {

			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				// Now we have to inform the adapter about the new list filtered
				if (results.count == 0) {
					AdpCadastro.this.setLstItmCadastro(new ArrayList<ItmConsulta>());
					notifyDataSetChanged();
				} else {
					AdpCadastro.this.setLstItmCadastro((ArrayList<ItmConsulta>) results.values);
					notifyDataSetChanged();
				}
			}

			@Override
			protected FilterResults performFiltering(CharSequence strFiltro) {

				FilterResults objFilterResults = new FilterResults();
				// We implement here the filter logic
				if (strFiltro == null || strFiltro.length() == 0) {
					// No filter implemented we return all the list
					objFilterResults.values = AdpCadastro.this.getLstItmCadastroSemFiltro();
					objFilterResults.count = AdpCadastro.this.getLstItmCadastroSemFiltro().size();
				} else {
					// We perform filtering operation
					ArrayList<ItmConsulta> lstItmCadastro = new ArrayList<ItmConsulta>();

					for (ItmConsulta itmCadastro : AdpCadastro.this.getLstItmCadastro()) {
						if (itmCadastro.getBooContemString(strFiltro.toString())) {
							lstItmCadastro.add(itmCadastro);
						}
					}
					objFilterResults.values = lstItmCadastro;
					objFilterResults.count = lstItmCadastro.size();
				}

				return objFilterResults;
			}
		};
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
