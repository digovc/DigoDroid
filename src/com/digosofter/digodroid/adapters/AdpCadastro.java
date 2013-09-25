package com.digosofter.digodroid.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;
import com.digosofter.digodroid.itens.ItmCadastro;

public class AdpCadastro extends BaseAdapter {

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

	private ArrayList<ItmCadastro> getlstObjItmCadastro() {
		return _lstObjItmCadastro;
	}

	private void setlstObjItmCadastro(ArrayList<ItmCadastro> lstObjItmCadastro) {
		_lstObjItmCadastro = lstObjItmCadastro;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public AdpCadastro(Context context, ArrayList<ItmCadastro> lstObjItmCadastro) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setlstObjItmCadastro(lstObjItmCadastro);
			this.setObjLayoutInflater(LayoutInflater.from(context));

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS

	@Override
	public int getCount() {
		return this.getlstObjItmCadastro().size();
	}

	@Override
	public Object getItem(int position) {
		return this.getlstObjItmCadastro().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// VARIÁVEIS

		ItmCadastro objItmCadastro = this.getlstObjItmCadastro().get(position);
		View objView = this.getObjLayoutInflater().inflate(R.layout.itm_cadastro, null);

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			TextView txtRegistroNome = (TextView) objView.findViewById(R.id.itmCadastro_txtNome);
			TextView txtRegistroCampo001 = (TextView) objView.findViewById(R.id.itmCadastro_txtCampo001);
			TextView txtRegistroCampo002 = (TextView) objView.findViewById(R.id.itmCadastro_txtCampo002);
			TextView txtRegistroCampo003 = (TextView) objView.findViewById(R.id.itmCadastro_txtCampo003);

			txtRegistroNome.setText(objItmCadastro.getStrNomeExibicao());

			txtRegistroCampo001.setText(objItmCadastro.getstrCampo001());
			if (objItmCadastro.getStrCampo001Valor() == null) {
				txtRegistroCampo001.setVisibility(View.GONE);
			}
			
			txtRegistroCampo002.setText(objItmCadastro.getstrCampo002());
			if (objItmCadastro.getStrCampo002Valor() == null) {
				txtRegistroCampo002.setVisibility(View.GONE);
			}
			
			txtRegistroCampo003.setText(objItmCadastro.getstrCampo003());
			if (objItmCadastro.getStrCampo003Valor() == null) {
				txtRegistroCampo003.setVisibility(View.GONE);
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}

		return objView;
	}

	// FIM EVENTOS
}
