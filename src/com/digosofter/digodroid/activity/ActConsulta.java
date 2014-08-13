package com.digosofter.digodroid.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.digosofter.digodroid.Util;
import com.digosofter.digodroid.adapter.AdpCadastro;
import com.digosofter.digodroid.database.DbTabela;
import com.digosofter.digodroid.erro.Erro;
import com.digosofter.digodroid.item.ItmConsulta;

public class ActConsulta extends ActMain {

  public enum EnmResultadoTipo {
    NONE,
    REGISTRO_SELECIONADO,
    VOLTAR
  }

  private AdpCadastro _adpCadastro;
  private EditText _edtPesquisa;
  private ListView _pnlTblLista;
  private DbTabela _tbl;
  private TextView _txtTblDescricao;

  public ActConsulta() {

    try {
      this.setResult(EnmResultadoTipo.NONE.ordinal());
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private AdpCadastro getAdpCadastro() {

    return _adpCadastro;
  }

  private EditText getEdtPesquisa() {

    try {
      if (_edtPesquisa != null) {
        return _edtPesquisa;
      }
      _edtPesquisa = this.getEditText(R.id.actConsulta_edtPesquisa);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _edtPesquisa;
  }

  @Override
  protected int getIntLayoutId() {

    return R.layout.act_consulta;
  }

  public ListView getPnlTblLista() {

    try {
      if (_pnlTblLista != null) {
        return _pnlTblLista;
      }
      _pnlTblLista = this.getListView(R.id.actConsulta_pnlTblLista);
      _pnlTblLista.setCacheColorHint(Color.TRANSPARENT);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _pnlTblLista;
  }

  public DbTabela getTbl() {

    try {
      if (_tbl != null) {
        return _tbl;
      }
      _tbl = App.getI().getTblSelec();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _tbl;
  }

  private TextView getTxtTblDescricao() {

    try {
      if (_txtTblDescricao != null) {
        return _txtTblDescricao;
      }
      _txtTblDescricao = this.getTextView(R.id.actConsulta_pnlPesquisa);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _txtTblDescricao;
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();
    try {
      this.montarLayoutTitulo();
      this.montarLayoutLista();
      this.recuperarUltimaPesquisa();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(114), ex);
    }
    finally {
    }
  }

  private void montarLayoutLista() {

    try {
      this.setAdpCadastro(new AdpCadastro(this, this.getTbl().getLstItmConsulta()));
      this.getPnlTblLista().setAdapter(this.getAdpCadastro());
      this.getActionBar().setDisplayHomeAsUpEnabled(true);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private void montarLayoutTitulo() {

    try {
      this.setTitle(this.getTbl().getStrNomeExibicao());
      if (!Util.getBooStrVazia(this.getTbl().getStrDescricao())) {
        this.getTxtTblDescricao().setText(this.getTbl().getStrDescricao());
        this.getTxtTblDescricao().setVisibility(View.VISIBLE);
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu objMenu) {

    MenuInflater objMenuInflater;
    try {
      objMenuInflater = this.getMenuInflater();
      objMenuInflater.inflate(R.menu.act_cadastro_action_bar, objMenu);
      objMenu.getItem(0).setVisible(this.getTbl().getBooPermitirCadastroNovo());
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(117), ex);
    }
    finally {
    }
    return super.onCreateOptionsMenu(objMenu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem itm) {

    try {

      if (super.onOptionsItemSelected(itm)) {
        return true;
      }

      if (itm.getItemId() == R.id.actCadastro_actionBar_itmNovo) {
        this.startActivity(new Intent(this, this.getTbl().getClsActCadastro()));
        return true;
      }

      if (itm.getItemId() == android.R.id.home) {
        this.onBackPressed();
        return true;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return false;
  }

  /**
   * Recupera a última pesquisa feita na tabela da tela de consulta.
   */
  private void recuperarUltimaPesquisa() {

    try {
      if (Util.getBooStrVazia(this.getTbl().getStrPesquisaActConsulta())) {
        return;
      }
      this.getEdtPesquisa().setText(this.getTbl().getStrPesquisaActConsulta());
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private void setAdpCadastro(AdpCadastro adpCadastro) {

    _adpCadastro = adpCadastro;
  }

  @Override
  protected void setEventos() {

    super.setEventos();
    try {
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

          ItmConsulta objItem;
          Intent objIntent;
          try {
            ActConsulta.this.getTbl().setStrPesquisaActConsulta(
                ActConsulta.this.getEdtPesquisa().getText().toString());
            objItem = (ItmConsulta) ActConsulta.this.getPnlTblLista().getItemAtPosition(position);
            objIntent = new Intent();
            objIntent.putExtra("id", objItem.getStrItemId());
            ActConsulta.this.setResult(ActConsulta.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(),
                objIntent);
            ActConsulta.this.finish();
          }
          catch (Exception ex) {
            new Erro(App.getI().getStrTextoPadrao(115), ex);
          }
          finally {
          }
        }
      });
      this.getPnlTblLista().setOnItemLongClickListener(new OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

          ItmConsulta objItem;
          Intent objIntent;
          try {
            if (ActConsulta.this.getTbl().getClsActCadastro() == null) {
              return false;
            }
            objItem = (ItmConsulta) ActConsulta.this.getPnlTblLista().getItemAtPosition(position);
            objIntent = new Intent(ActConsulta.this.getApplicationContext(), ActConsulta.this
                .getTbl().getClsActCadastro());
            objIntent.putExtra("id", objItem.getStrItemId());
            ActConsulta.this.setResult(ActConsulta.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(),
                objIntent);
            ActConsulta.this.startActivity(objIntent);
          }
          catch (Exception ex) {
            new Erro(App.getI().getStrTextoPadrao(115), ex);
          }
          finally {
          }
          return false;
        }
      });
      this.getPnlTblLista().setOnScrollListener(new OnScrollListener() {

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
            int totalItemCount) {

        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

          InputMethodManager objIm;
          try {
            objIm = (InputMethodManager) ActConsulta.this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
            objIm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
          }
          catch (Exception ex) {
            new Erro(App.getI().getStrTextoPadrao(0), ex);
          }
          finally {
          }
        }
      });
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(116), ex);
    }
    finally {
    }
  }
}
