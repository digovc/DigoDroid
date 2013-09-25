package com.digosofter.digodroid.activitys;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.adapters.AdpCadastro;
import com.digosofter.digodroid.database.DbTabela;
import com.digosofter.digodroid.erro.Erro;
import com.digosofter.digodroid.itens.ItmCadastro;

public class ActCadastro extends ActBase {
	// CONSTANTES

	public enum EnmResultadoTipo {
		VOLTAR, REGISTRO_SELECIONADO
	}

	// FIM CONSTANTES

	// ATRIBUTOS

	private ListView _objListView;

	public ListView getObjListView() {
		if (_objListView == null) {
			_objListView = (ListView) findViewById(R.id.actCadastro_pnlTabela);
		}
		return _objListView;
	}

	private DbTabela _tbl;

	public DbTabela getTbl() {
		return _tbl;
	}

	private void setTbl(DbTabela tbl) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			_tbl = tbl;

			TextView txtTabelaTitulo = (TextView) this.findViewById(R.id.actCadastro_txtTabelaTitulo);
			TextView txtTabelaDescricao = (TextView) this.findViewById(R.id.actCadastro_txtTabelaDescricao);

			txtTabelaTitulo.setText(_tbl.getStrNomeExibicao());
			if (_tbl.getStrDescricao() != null) {
				txtTabelaDescricao.setText(_tbl.getStrDescricao());
				txtTabelaDescricao.setVisibility(View.VISIBLE);
			}

			// FIM AÇÕES
		} catch (Exception ex) {
			new Erro("Erro ao carregar tabela.\n" + ex.getMessage());
		} finally {
		}
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// MÉTODOS

	@Override
	protected void montarLayout() {
		// VARIÁVEIS

		ArrayList<ItmCadastro> lstObjItmCadastro = new ArrayList<ItmCadastro>();
		ItmCadastro itmCadastro = null;
		Cursor objCursor = this.getTbl().getCursorDadosTelaCadastro();

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (objCursor != null) {
				if (objCursor.moveToFirst()) {
					do {
						itmCadastro = new ItmCadastro();
						itmCadastro.setIntItemId(objCursor.getInt(objCursor.getColumnIndex(this.getTbl()
								.getClnChavePrimaria().getStrNomeSimplificado())));
						if (this.getTbl().getClnNome().getClnReferencia() != null) {
							itmCadastro.setStrNome(objCursor.getString(objCursor.getColumnIndex("_strNomeB")));
						} else {
							itmCadastro.setStrNome(objCursor.getString(objCursor.getColumnIndex(this.getTbl()
									.getClnNome().getStrNomeSimplificado())));
						}
						for (int intColunaIndex = 0; intColunaIndex <= 4; intColunaIndex++) {
							if (intColunaIndex < objCursor.getColumnCount()) {
								switch (intColunaIndex) {
								case 2:
									itmCadastro.setStrCampo001Nome(this.getTbl().getStrClnNomePeloNomeSimplificado(
											objCursor.getColumnName(intColunaIndex)));
									itmCadastro.setStrCampo001Valor(objCursor.getString(intColunaIndex));
									break;
								case 3:
									itmCadastro.setStrCampo002Nome(this.getTbl().getStrClnNomePeloNomeSimplificado(
											objCursor.getColumnName(intColunaIndex)));
									itmCadastro.setStrCampo002Valor(objCursor.getString(intColunaIndex));
									break;
								case 4:
									itmCadastro.setStrCampo003Nome(this.getTbl().getStrClnNomePeloNomeSimplificado(
											objCursor.getColumnName(intColunaIndex)));
									itmCadastro.setStrCampo003Valor(objCursor.getString(intColunaIndex));
									break;
								}
							}
						}
						lstObjItmCadastro.add(itmCadastro);

					} while (objCursor.moveToNext());
				}
			}
			AdpCadastro adpCadastro = new AdpCadastro(this, lstObjItmCadastro);
			this.getObjListView().setAdapter(adpCadastro);
			this.getObjListView().setCacheColorHint(Color.TRANSPARENT);

			// FIM AÇÕES
		} catch (Exception ex) {
			new Erro("Erro ao montar layout.\n" + ex.getMessage());
		} finally {
		}
	}

	@Override
	protected void setEventos() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.getObjListView().setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// VARIÁVEIS
					// FIM VARIÁVEIS
					try {
						// AÇÕES

						Object objTemp = getObjListView().getItemAtPosition(position);
						ItmCadastro objItem = (ItmCadastro) objTemp;
						Intent objIntentResult = new Intent();
						objIntentResult.putExtra("intId", objItem.getIntItemId());
						setResult(ActCadastro.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(), objIntentResult);
						finish();

						// FIM AÇÕES
					} catch (Exception ex) {

						new Erro("Erro inesperado.\n" + ex.getMessage());

					} finally {
					}
				}
			});

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro ao setar os eventos.\n" + ex.getMessage());

		} finally {
		}
	}

	// FIM MÉTODOS

	// EVENTOS

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			super.onCreate(savedInstanceState);
			setContentView(R.layout.act_cadastro);
			this.setTbl(App.getApp().getTblSelecionada());

			this.montarLayout();
			this.setEventos();

			// FIM AÇÕES
		} catch (Exception ex) {
			new Erro("Erro ao criar tela de cadastro.\n" + ex.getMessage());
		} finally {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_cadastro, menu);
		return true;
	}

	// FIM EVENTOS

}
