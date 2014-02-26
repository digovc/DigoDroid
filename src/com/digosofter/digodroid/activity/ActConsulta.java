package com.digosofter.digodroid.activity;

import android.content.Context;
import android.content.Intent;
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
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.adapter.AdpCadastro;
import com.digosofter.digodroid.database.DbTabela;
import com.digosofter.digodroid.erro.Erro;
import com.digosofter.digodroid.item.ItmConsulta;

public class ActConsulta extends ActMain {

	public enum EnmResultadoTipo {
		REGISTRO_SELECIONADO, VOLTAR
	}

	private AdpCadastro _adpCadastro;

	private EditText _edtPesquisa;

	private ListView _pnlTblLista;

	private DbTabela _tbl;

	private TextView _txtTblDescricao;

	private AdpCadastro getAdpCadastro() {
		return _adpCadastro;
	}

	private EditText getEdtPesquisa() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_edtPesquisa == null) {
				_edtPesquisa = (EditText) this.findViewById(R.id.actConsulta_edtPesquisa);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _edtPesquisa;
	}

	public ListView getPnlTblLista() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_pnlTblLista == null) {

				_pnlTblLista = (ListView) findViewById(R.id.actConsulta_pnlTblLista);
				_pnlTblLista.setCacheColorHint(Color.TRANSPARENT);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _pnlTblLista;
	}

	public DbTabela getTbl() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_tbl == null) {
				_tbl = App.getI().getTblSelecionada();
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _tbl;
	}

	private TextView getTxtTblDescricao() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_txtTblDescricao == null) {
				_txtTblDescricao = (TextView) this.findViewById(R.id.actConsulta_pnlPesquisa);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _txtTblDescricao;
	}

	@Override
	protected void montarLayout() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.montarLayoutTitulo();
			this.montarLayoutLista();

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(114), ex.getMessage());

		} finally {
		}
	}

	private void montarLayoutLista() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setAdpCadastro(new AdpCadastro(this, this.getTbl().getLstItmConsulta()));
			this.getPnlTblLista().setAdapter(this.getAdpCadastro());
			this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private void montarLayoutTitulo() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setTitle(this.getTbl().getStrNomeExibicao());

			if (!Utils.getBooIsEmptyNull(this.getTbl().getStrDescricao())) {

				this.getTxtTblDescricao().setText(this.getTbl().getStrDescricao());
				this.getTxtTblDescricao().setVisibility(View.VISIBLE);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setContentView(R.layout.act_consulta);
			this.montarLayout();
			this.setEventos();
			this.recuperarUltimaPesquisa();

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

	/**
	 * Recupera a �ltima pesquisa feita na tabela da tela de consulta.
	 */
	private void recuperarUltimaPesquisa() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (Utils.getBooIsEmptyNull(this.getTbl().getStrPesquisaActConsulta())) {
				return;
			}

			this.getEdtPesquisa().setText(this.getTbl().getStrPesquisaActConsulta());

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private void setAdpCadastro(AdpCadastro adpCadastro) {
		_adpCadastro = adpCadastro;
	}

	@Override
	protected void setEventos() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.getEdtPesquisa().addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable s) {
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					ActConsulta.this.getAdpCadastro().getFilter().filter(s);
				}
			});

			this.getPnlTblLista().setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// VARI�VEIS

					ItmConsulta objItem;
					Intent objIntent;

					// FIM VARI�VEIS
					try {
						// A��ES

						ActConsulta.this.getTbl().setStrPesquisaActConsulta(ActConsulta.this.getEdtPesquisa().getText().toString());

						objItem = (ItmConsulta) ActConsulta.this.getPnlTblLista().getItemAtPosition(position);

						objIntent = new Intent();
						objIntent.putExtra("id", objItem.getStrItemId());

						ActConsulta.this.setResult(ActConsulta.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(), objIntent);

						finish();

						// FIM A��ES
					} catch (Exception ex) {

						new Erro(App.getI().getStrTextoPadrao(115), ex.getMessage());

					} finally {
					}
				}
			});

			this.getPnlTblLista().setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
					// VARI�VEIS

					ItmConsulta objItem;
					Intent objIntent;

					// FIM VARI�VEIS
					try {
						// A��ES

						if (ActConsulta.this.getTbl().getClsActFrm() != null) {

							objItem = (ItmConsulta) ActConsulta.this.getPnlTblLista().getItemAtPosition(position);

							objIntent = new Intent(ActConsulta.this.getApplicationContext(), ActConsulta.this.getTbl().getClsActFrm());
							objIntent.putExtra("id", objItem.getStrItemId());

							ActConsulta.this.setResult(ActConsulta.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(), objIntent);
							ActConsulta.this.startActivity(objIntent);

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

			this.getPnlTblLista().setOnScrollListener(new OnScrollListener() {

				@Override
				public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				}

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
			});

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(116), ex.getMessage());

		} finally {
		}
	}

}
