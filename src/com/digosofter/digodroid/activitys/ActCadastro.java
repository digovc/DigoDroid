package com.digosofter.digodroid.activitys;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
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

	private AdpCadastro _adpCadastro;

	private AdpCadastro getAdpCadastro() {
		return _adpCadastro;
	}

	private void setAdpCadastro(AdpCadastro adpCadastro) {
		_adpCadastro = adpCadastro;
	}

	private EditText _edtPesquisa;

	private EditText getEdtPesquisa() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_edtPesquisa == null) {
				_edtPesquisa = (EditText) this.findViewById(R.id.actCadastro_edtPesquisa);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _edtPesquisa;
	}

	private ListView _objListView;

	public ListView getObjListView() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_objListView == null) {
				_objListView = (ListView) findViewById(R.id.actCadastro_pnlTbl);
				_objListView.setCacheColorHint(Color.TRANSPARENT);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _objListView;
	}

	private DbTabela _tbl;

	public DbTabela getTbl() {
		return _tbl;
	}

	private void setTbl(DbTabela tbl) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			_tbl = tbl;
			this.setTitle(_tbl.getStrNomeExibicao());

			if (_tbl.getStrDescricao() != null) {
				this.getTxtTblDescricao().setText(_tbl.getStrDescricao());
				this.getTxtTblDescricao().setVisibility(View.VISIBLE);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private TextView _txtTblDescricao;

	private TextView getTxtTblDescricao() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_txtTblDescricao == null) {
				_txtTblDescricao = (TextView) this.findViewById(R.id.actCadastro_pnlPesquisa);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _txtTblDescricao;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// M�TODOS

	@Override
	protected void montarLayout() {
		// VARI�VEIS

		ArrayList<ItmCadastro> lstObjItmCadastro;
		ItmCadastro itmCadastro;
		Cursor objCursor;

		// FIM VARI�VEIS
		try {
			// A��ES

			lstObjItmCadastro = new ArrayList<ItmCadastro>();
			objCursor = this.getTbl().getCrsDadosTelaCadastro();

			if (objCursor != null && objCursor.moveToFirst()) {
				do {

					itmCadastro = new ItmCadastro();
					itmCadastro.setIntItemId(objCursor.getInt(objCursor.getColumnIndex(this.getTbl().getClnChavePrimaria().getStrNomeSimplificado())));

					if (this.getTbl().getClnNome().getClnReferencia() != null) {
						itmCadastro.setStrNome(objCursor.getString(objCursor.getColumnIndex("_strNomeB")));
					} else {
						itmCadastro.setStrNome(objCursor.getString(objCursor.getColumnIndex(this.getTbl().getClnNome().getStrNomeSimplificado())));
					}

					for (int intColunaIndex = 0; intColunaIndex <= 4; intColunaIndex++) {

						if (intColunaIndex < objCursor.getColumnCount()) {

							switch (intColunaIndex) {
							case 2:
								itmCadastro.setStrCampo001Nome(this.getTbl().getStrClnNomePeloNomeSimplificado(objCursor.getColumnName(intColunaIndex)));
								itmCadastro.setStrCampo001Valor(objCursor.getString(intColunaIndex));
								break;
							case 3:
								itmCadastro.setStrCampo002Nome(this.getTbl().getStrClnNomePeloNomeSimplificado(objCursor.getColumnName(intColunaIndex)));
								itmCadastro.setStrCampo002Valor(objCursor.getString(intColunaIndex));
								break;
							case 4:
								itmCadastro.setStrCampo003Nome(this.getTbl().getStrClnNomePeloNomeSimplificado(objCursor.getColumnName(intColunaIndex)));
								itmCadastro.setStrCampo003Valor(objCursor.getString(intColunaIndex));
								break;
							}
						}
					}

					lstObjItmCadastro.add(itmCadastro);

				} while (objCursor.moveToNext());
			}

			this.setAdpCadastro(new AdpCadastro(this, lstObjItmCadastro));
			this.getObjListView().setAdapter(this.getAdpCadastro());
			this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(114), ex.getMessage());

		} finally {
		}
	}

	@Override
	protected void setEventos() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.getEdtPesquisa().addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					ActCadastro.this.getAdpCadastro().getFilter().filter(s);
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}

				@Override
				public void afterTextChanged(Editable s) {
				}
			});

			this.getObjListView().setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// VARI�VEIS

					ItmCadastro objItem;
					Intent objIntentResult;

					// FIM VARI�VEIS
					try {
						// A��ES

						objItem = (ItmCadastro) ActCadastro.this.getObjListView().getItemAtPosition(position);

						objIntentResult = new Intent();
						objIntentResult.putExtra("intId", objItem.getIntItemId());

						setResult(ActCadastro.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(), objIntentResult);

						finish();

						// FIM A��ES
					} catch (Exception ex) {

						new Erro(App.getI().getStrTextoPadrao(115), ex.getMessage());

					} finally {
					}
				}
			});

			this.getObjListView().setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
					// VARI�VEIS

					ItmCadastro objItem;
					Intent objIntent;

					// FIM VARI�VEIS
					try {
						// A��ES

						if (ActCadastro.this.getTbl().getClsActFrm() != null) {

							objItem = (ItmCadastro) ActCadastro.this.getObjListView().getItemAtPosition(position);

							objIntent = new Intent(ActCadastro.this.getApplicationContext(), ActCadastro.this.getTbl().getClsActFrm());
							objIntent.putExtra("intId", objItem.getIntItemId());

							ActCadastro.this.setResult(ActCadastro.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(), objIntent);
							ActCadastro.this.startActivity(objIntent);

							// finish();
						}

						// FIM A��ES
					} catch (Exception ex) {

						new Erro(App.getI().getStrTextoPadrao(115), ex.getMessage());

					} finally {
					}

					return false;
				}
			});

			this.getObjListView().setOnScrollListener(new OnScrollListener() {

				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					// VARI�VEIS

					InputMethodManager inputManager;

					// FIM VARI�VEIS
					try {
						// A��ES

						inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

						// FIM A��ES
					} catch (Exception ex) {

						new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

					} finally {
					}
				}

				@Override
				public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				}
			});

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(116), ex.getMessage());

		} finally {
		}
	}

	// FIM M�TODOS

	// EVENTOS

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setContentView(R.layout.act_cadastro);
			this.setTbl(App.getI().getTblSelecionada());
			this.montarLayout();
			this.setEventos();

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(117), ex.getMessage());

		} finally {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu objMenu) {
		// VARI�VEIS

		MenuInflater objMenuInflater;

		// FIM VARI�VEIS
		try {
			// A��ES

			objMenuInflater = this.getMenuInflater();
			objMenuInflater.inflate(R.menu.act_cadastro_action_bar, objMenu);

			objMenu.getItem(0).setVisible(this.getTbl().getBooPermitirCadastroNovo());

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(117), ex.getMessage());

		} finally {
		}

		return super.onCreateOptionsMenu(objMenu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (item.getItemId() == R.id.actCadastro_actionBar_itmNovo) {
				this.startActivity(new Intent(this, this.getTbl().getClsActFrm()));
			} else if (item.getItemId() == android.R.id.home) {
				this.onBackPressed();
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return super.onOptionsItemSelected(item);
	}

	// FIM EVENTOS

}
