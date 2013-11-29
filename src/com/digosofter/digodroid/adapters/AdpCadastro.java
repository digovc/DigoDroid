package com.digosofter.digodroid.adapters;

import java.util.ArrayList;

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
import com.digosofter.digodroid.itens.ItmCadastro;

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

	private ArrayList<ItmCadastro> _lstObjItmCadastro;

	private ArrayList<ItmCadastro> getLstObjItmCadastro() {
		return _lstObjItmCadastro;
	}

	private void setLstObjItmCadastro(ArrayList<ItmCadastro> lstObjItmCadastro) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			_lstObjItmCadastro = lstObjItmCadastro;
			
			if (this.getLstObjItmCadastroSemFiltro() == null) {
				this.setLstObjItmCadastroSemFiltro(_lstObjItmCadastro);
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}
	}

	private ArrayList<ItmCadastro> _lstObjItmCadastroSemFiltro;

	private ArrayList<ItmCadastro> getLstObjItmCadastroSemFiltro() {
		return _lstObjItmCadastroSemFiltro;
	}

	private void setLstObjItmCadastroSemFiltro(ArrayList<ItmCadastro> lstObjItmCadastroSemFiltro) {
		_lstObjItmCadastroSemFiltro = lstObjItmCadastroSemFiltro;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public AdpCadastro(Context context, ArrayList<ItmCadastro> lstObjItmCadastro) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setLstObjItmCadastro(lstObjItmCadastro);
			this.setObjLayoutInflater(LayoutInflater.from(context));

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS

	@Override
	public int getCount() {
		return this.getLstObjItmCadastro().size();
	}

	@Override
	public Object getItem(int position) {
		return this.getLstObjItmCadastro().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// VARIÁVEIS

		ItmCadastro objItmCadastro = this.getLstObjItmCadastro().get(position);
		View objView = this.getObjLayoutInflater().inflate(R.layout.itm_cadastro, null);

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			TextView txtRegistroNome = (TextView) objView.findViewById(R.id.itmCadastro_txtNome);
			TextView txtRegistroCampo001 = (TextView) objView.findViewById(R.id.itmCadastro_txtCampo001);
			TextView txtRegistroCampo002 = (TextView) objView.findViewById(R.id.itmCadastro_txtCampo002);
			TextView txtRegistroCampo003 = (TextView) objView.findViewById(R.id.itmCadastro_txtCampo003);

			txtRegistroNome.setText(objItmCadastro.getStrNomeExibicao());

			txtRegistroCampo001.setText(objItmCadastro.getStrCampo001());
			if (objItmCadastro.getStrCampo001Valor() == null
					|| objItmCadastro.getStrCampo001Valor().equals(Utils.STRING_VAZIA)) {
				txtRegistroCampo001.setVisibility(View.GONE);
			}

			txtRegistroCampo002.setText(objItmCadastro.getstrCampo002());
			if (objItmCadastro.getStrCampo002Valor() == null
					|| objItmCadastro.getStrCampo002Valor().equals(Utils.STRING_VAZIA)) {
				txtRegistroCampo002.setVisibility(View.GONE);
			}

			txtRegistroCampo003.setText(objItmCadastro.getstrCampo003());
			if (objItmCadastro.getStrCampo003Valor() == null
					|| objItmCadastro.getStrCampo003Valor().equals(Utils.STRING_VAZIA)) {
				txtRegistroCampo003.setVisibility(View.GONE);
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTexto(0), ex.getMessage());

		} finally {
		}

		return objView;
	}

	@Override
	public Filter getFilter() {

		this.setLstObjItmCadastro(this.getLstObjItmCadastroSemFiltro());

		return new Filter() {

			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				// Now we have to inform the adapter about the new list filtered
				if (results.count == 0) {
					AdpCadastro.this.setLstObjItmCadastro(new ArrayList<ItmCadastro>());
					notifyDataSetChanged();
				} else {
					AdpCadastro.this.setLstObjItmCadastro((ArrayList<ItmCadastro>) results.values);
					notifyDataSetChanged();
				}
			}

			@Override
			protected FilterResults performFiltering(CharSequence strFiltro) {
				FilterResults objFilterResults = new FilterResults();
				// We implement here the filter logic
				if (strFiltro == null || strFiltro.length() == 0) {
					// No filter implemented we return all the list
					objFilterResults.values = AdpCadastro.this.getLstObjItmCadastroSemFiltro();
					objFilterResults.count = AdpCadastro.this.getLstObjItmCadastroSemFiltro().size();
				} else {
					// We perform filtering operation
					ArrayList<ItmCadastro> lstItmCadastro = new ArrayList<ItmCadastro>();

					for (ItmCadastro itmCadastro : AdpCadastro.this.getLstObjItmCadastro()) {
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

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
