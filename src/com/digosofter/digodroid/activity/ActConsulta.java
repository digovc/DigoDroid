package com.digosofter.digodroid.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

public class ActConsulta extends ActMain {

  public enum EnmResultadoTipo {

    NONE,
    REGISTRO_SELECIONADO,
    VOLTAR
  }

  public static final String STR_EXTRA_OUT_REGISTRO_ID = "registro_id";

  private AdpConsulta _adpCadastro;
  private EditText _edtPesquisa;
  private TextWatcher _evtEdtPesquisa_TextWatcher;
  private OnItemClickListener _evtPnlTblLista_OnItemClickListener;
  private ItmConsulta _itmSelec;
  private ListView _pnlLista;
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

  protected void abrirActDetalhe(int intRegistroId) {

    Intent itt;

    try {

      if (intRegistroId < 1) {

        return;
      }

      itt = new Intent(this, ActDetalhe.class);
      itt.putExtra(ActDetalhe.STR_EXTRA_IN_INT_REGISTRO_ID, intRegistroId);

      this.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
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

      new ErroAndroid("Erro inesperado.\n", ex);
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

  private TextWatcher getEvtEdtPesquisa_TextWatcher() {

    try {

      if (_evtEdtPesquisa_TextWatcher != null) {

        return _evtEdtPesquisa_TextWatcher;
      }

      _evtEdtPesquisa_TextWatcher = new TextWatcher() {

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
      };
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _evtEdtPesquisa_TextWatcher;
  }

  private OnItemClickListener getEvtPnlTblLista_OnItemClickListener() {

    try {

      if (_evtPnlTblLista_OnItemClickListener != null) {

        return _evtPnlTblLista_OnItemClickListener;
      }

      _evtPnlTblLista_OnItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> advParent, View viw, int intPosition, long intId) {

          ItmConsulta itm;

          try {

            itm = (ItmConsulta) ActConsulta.this.getPnlLista().getItemAtPosition(intPosition);
            ActConsulta.this.onItemClick(viw, itm);
          }

          catch (Exception ex) {

            new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(115), ex);
          }
          finally {
          }
        }
      };
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _evtPnlTblLista_OnItemClickListener;
  }

  @Override
  protected int getIntLayoutId() {

    return R.layout.act_consulta;
  }

  private ItmConsulta getItmSelec() {

    return _itmSelec;
  }

  public ListView getPnlLista() {

    try {

      if (_pnlLista != null) {

        return _pnlLista;
      }

      _pnlLista = this.getListView(R.id.actConsulta_pnlLista);

      _pnlLista.setCacheColorHint(Color.TRANSPARENT);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _pnlLista;
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

      this.getPnlLista().setAdapter(this.getAdpCadastro());
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
  public boolean onContextItemSelected(MenuItem itmMenu) {

    try {

      if (itmMenu == null) {

        return true;
      }

      if (this.getTbl() == null) {

        return true;
      }

      if (itmMenu.getTitle().equals(DbTabelaAndroid.STR_OPCAO_SELECIONAR)) {

        this.processarOpcaoSelecionar();
        return true;
      }

      this.getTbl().processarOpcao(this, itmMenu.getTitle().toString(), this.getItmSelec().getIntRegistroId());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return super.onContextItemSelected(itmMenu);
  }

  @Override
  public void onCreateContextMenu(ContextMenu mnu, View viw, ContextMenuInfo objContextMenuInfo) {

    super.onCreateContextMenu(mnu, viw, objContextMenuInfo);

    try {

      if (this.getTbl() == null) {

        return;
      }

      if (this.getItmSelec() == null) {

        return;
      }

      if (this.getItmSelec().getIntRegistroId() < 1) {

        return;
      }

      mnu.setHeaderTitle(this.getItmSelec().getStrNome());

      this.getTbl().montarMenu(mnu, this.getItmSelec().getIntRegistroId());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
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

  protected void onItemClick(View viw, ItmConsulta itm) {

    try {

      if (viw == null) {

        return;
      }

      if (itm == null) {

        return;
      }

      if (itm.getIntRegistroId() < 1) {

        return;
      }

      this.setItmSelec(itm);
      this.openContextMenu(viw);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
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

  private void processarOpcaoSelecionar() {

    Intent itt;

    try {

      if (this.getItmSelec() == null) {

        return;
      }

      if (this.getItmSelec().getIntRegistroId() < 1) {

        return;
      }

      itt = new Intent();
      itt.putExtra(ActConsulta.STR_EXTRA_OUT_REGISTRO_ID, this.getItmSelec().getIntRegistroId());

      this.getTbl().setStrPesquisa(this.getEdtPesquisa().getText().toString());
      this.setResult(ActConsulta.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(), itt);
      this.finish();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void recuperarUltimaPesquisa() {

    try {

      if (Utils.getBooStrVazia(this.getTbl().getStrPesquisa())) {

        return;
      }

      this.getEdtPesquisa().setText(this.getTbl().getStrPesquisa());
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

      this.registerForContextMenu(this.getPnlLista());

      this.getPnlLista().setLongClickable(false);
      this.getEdtPesquisa().addTextChangedListener(this.getEvtEdtPesquisa_TextWatcher());
      this.getPnlLista().setOnItemClickListener(this.getEvtPnlTblLista_OnItemClickListener());
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(116), ex);
    }
    finally {
    }
  }

  private void setItmSelec(ItmConsulta itmSelec) {

    _itmSelec = itmSelec;
  }
}