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

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.adapter.AdpConsulta;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digodroid.item.ItmConsulta;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.erro.Erro;

public class ActConsulta extends ActMain {

  public enum EnmResultadoTipo {

    NONE,
    REGISTRO_SELECIONADO,
    VOLTAR
  }

  public static final String EXTRA_OUT_REGISTRO_ID = "registro_id";

  private AdpConsulta _adpCadastro;
  private EditText _edtPesquisa;
  private ListView _pnlTblLista;
  private DbTabelaAndroid _tbl;
  private TextView _txtTblDescricao;

  public ActConsulta() {

    try {

      this.setResult(EnmResultadoTipo.NONE.ordinal());
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private AdpConsulta getAdpCadastro() {

    try {

      if (_adpCadastro != null) {

        return _adpCadastro;
      }

      _adpCadastro = new AdpConsulta();

      _adpCadastro.setActConsulta(this);
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }

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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _pnlTblLista;
  }

  public DbTabelaAndroid getTbl() {

    try {

      if (_tbl != null) {

        return _tbl;
      }

      _tbl = (DbTabelaAndroid) AppAndroid.getI().getTblSelec();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(114), ex);
    }
    finally {
    }
  }

  private void montarLayoutLista() {

    try {

      this.getPnlTblLista().setAdapter(this.getAdpCadastro());
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private void montarLayoutTitulo() {

    try {

      this.setTitle(this.getTbl().getStrNomeExibicao());

      if (Utils.getBooStrVazia(this.getTbl().getStrDescricao())) {

        return;
      }

      this.getTxtTblDescricao().setText(this.getTbl().getStrDescricao());
      this.getTxtTblDescricao().setVisibility(View.VISIBLE);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu objMenu) {

    MenuInflater objMenuInflater;

    try {

      objMenuInflater = this.getMenuInflater();
      objMenuInflater.inflate(R.menu.act_consulta_action_bar, objMenu);

      objMenu.getItem(0).setVisible(this.getTbl().getBooPermitirCadastroNovo());
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(117), ex);
    }
    finally {
    }

    return super.onCreateOptionsMenu(objMenu);
  }

  protected void onItemClick(ItmConsulta itm) {

    Intent objIntent;

    try {

      objIntent = new Intent();
      objIntent.putExtra(ActConsulta.EXTRA_OUT_REGISTRO_ID, itm.getIntRegistroId());

      ActConsulta.this.setResult(ActConsulta.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(), objIntent);
      ActConsulta.this.finish();
    }
    catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem itm) {

    try {

      if (super.onOptionsItemSelected(itm)) {

        return true;
      }

      if (itm.getItemId() == R.id.actConsultaAction_btnNovo) {

        this.startActivity(new Intent(this, this.getTbl().getClsActCadastro()));
        return true;
      }

      if (itm.getItemId() == android.R.id.home) {

        this.onBackPressed();
        return true;
      }
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
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

      if (Utils.getBooStrVazia(this.getTbl().getStrPesquisaActConsulta())) {

        return;
      }

      this.getEdtPesquisa().setText(this.getTbl().getStrPesquisaActConsulta());
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
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
        public void onItemClick(AdapterView<?> advParent, View viw, int intPosition, long intId) {

          ItmConsulta itm;

          try {

            ActConsulta.this.getTbl().setStrPesquisaActConsulta(ActConsulta.this.getEdtPesquisa().getText().toString());

            itm = (ItmConsulta) ActConsulta.this.getPnlTblLista().getItemAtPosition(intPosition);

            ActConsulta.this.onItemClick(itm);
          }

          catch (Exception ex) {

            new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(115), ex);
          }
          finally {
          }
        }
      });
      this.getPnlTblLista().setOnItemLongClickListener(new OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> advParent, View viw, int intPosition, long intId) {

          Intent itt;
          ItmConsulta itm;

          try {

            if (ActConsulta.this.getTbl().getClsActCadastro() == null) {

              return false;
            }

            itm = (ItmConsulta) ActConsulta.this.getPnlTblLista().getItemAtPosition(intPosition);

            itt = new Intent(ActConsulta.this, ActConsulta.this.getTbl().getClsActCadastro());
            itt.putExtra(ActCadastroMain.EXTRA_IN_INT_REGISTRO_ID, itm.getIntRegistroId());

            ActConsulta.this.setResult(ActConsulta.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(), itt);
            ActConsulta.this.startActivity(itt);
          }
          catch (Exception ex) {

            new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(115), ex);
          }
          finally {
          }

          return false;
        }
      });

      this.getPnlTblLista().setOnScrollListener(new OnScrollListener() {

        @Override
        public void onScroll(AbsListView viw, int intFirstVisibleItem, int intVisibleItemCount, int intTotalItemCount) {

        }

        @Override
        public void onScrollStateChanged(AbsListView viw, int intScrollState) {

          InputMethodManager imm;

          try {

            imm = (InputMethodManager) ActConsulta.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
          }
          catch (Exception ex) {

            new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
          }
          finally {
          }
        }
      });
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(116), ex);
    }
    finally {
    }
  }
}
