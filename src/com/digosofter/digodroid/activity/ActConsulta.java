package com.digosofter.digodroid.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.adapter.AdpConsulta;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digodroid.item.ItmConsulta;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.TblOnChangeArg;
import com.digosofter.digojava.database.TblOnChangeListener;

public class ActConsulta extends ActMain implements OnItemClickListener, OnItemLongClickListener, TblOnChangeListener {

  public enum EnmResultadoTipo {

    NONE,
    REGISTRO_SELECIONADO,
    VOLTAR
  }

  public static final String STR_EXTRA_IN_BOO_LIMPAR_LISTA_AO_SAIR = "boo_limpar_lista_ao_sair";
  public static final String STR_EXTRA_OUT_INT_REGISTRO_ID = "int_registro_id";

  private AdpConsulta _adpCadastro;
  private EditText _edtPesquisa;
  private TextWatcher _evtEdtPesquisa_TextWatcher;
  private ItmConsulta _itmSelec;
  private ListView _pnlLista;
  private LinearLayout _pnlPesquisa;
  private DbTabelaAndroid _tbl;
  private TextView _txtTblDescricao;
  private TextView _txtVazio;

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

  public void atualizarLista() {

    try {

      this.getAdpCadastro().atualizarLista();
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

  @Override
  protected int getIntLayoutId() {

    return R.layout.act_consulta;
  }

  private ItmConsulta getItmSelec() {

    return _itmSelec;
  }

  private ListView getPnlLista() {

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

  private LinearLayout getPnlPesquisa() {

    try {

      if (_pnlPesquisa != null) {

        return _pnlPesquisa;
      }

      _pnlPesquisa = this.getLinearLayout(R.id.actConsulta_pnlPesquisa);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _pnlPesquisa;
  }

  public DbTabelaAndroid getTbl() {

    try {

      if (_tbl != null) {

        return _tbl;
      }

      _tbl = (DbTabelaAndroid) AppAndroid.getI().getTblSelec();
      _tbl.addEvtTblOnChangeListener(this);
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

  private TextView getTxtVazio() {

    try {

      if (_txtVazio != null) {

        return _txtVazio;
      }

      _txtVazio = this.getTextView(R.id.actConsulta_txtVazio);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txtVazio;
  }

  private void limparListaAoSair() {

    try {

      if (this.getTbl() == null) {

        return;
      }

      if (!this.getIntent().getBooleanExtra(ActConsulta.STR_EXTRA_IN_BOO_LIMPAR_LISTA_AO_SAIR, false)) {

        return;
      }

      this.getTbl().limparListaConsulta();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();

    try {

      this.montarLayoutTitulo();
      this.montarLayoutLista();
      this.montarLayoutVazio();
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

  private void montarLayoutVazio() {

    try {

      if (this.getAdpCadastro().getCount() > 0) {

        this.getPnlLista().setVisibility(View.VISIBLE);
        this.getTxtVazio().setVisibility(View.GONE);
        return;
      }

      this.getPnlLista().setVisibility(View.GONE);
      this.getTxtVazio().setVisibility(View.VISIBLE);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onAdicionarReg(TblOnChangeArg arg) {

    try {

      this.getAdpCadastro().atualizarLista();
      this.montarLayoutVazio();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onApagarReg(TblOnChangeArg arg) {

    try {

      if (arg.getIntRegistroId() < 1) {

        return;
      }

      this.getAdpCadastro().apagar(arg.getIntRegistroId());
      this.montarLayoutVazio();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onAtualizarReg(TblOnChangeArg arg) {

    try {

      this.getAdpCadastro().atualizarLista();
      this.montarLayoutVazio();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public boolean onContextItemSelected(MenuItem mni) {

    try {

      if (mni == null) {

        return true;
      }

      if (this.getTbl() == null) {

        return true;
      }

      this.getTbl().processarMenuItem(this, mni, this.getItmSelec().getIntRegistroId());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return super.onContextItemSelected(mni);
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

      this.getTbl().montarMenuItem(mnu, this.getItmSelec().getIntRegistroId(), true);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void onCreateOptionsMenuAdicionar(Menu mnu) {

    MenuItem mni;

    try {

      if (mnu == null) {

        return;
      }

      if (this.getTbl() == null) {

        return;
      }

      if (this.getTbl().getClsActCadastro() == null) {

        return;
      }

      if (!this.getTbl().getBooMenuAdicionar()) {

        return;
      }

      mni = mnu.add(DbTabelaAndroid.STR_MENU_ADICIONAR);
      mni.setIcon(R.drawable.ic_adicionar);
      mni.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void onCreateOptionsMenuPesquisar(Menu mnu) {

    MenuItem mni;

    try {

      if (mnu == null) {

        return;
      }

      mni = mnu.add(DbTabelaAndroid.STR_MENU_PESQUISAR);
      mni.setCheckable(true);
      mni.setIcon(R.drawable.ic_pesquisar);
      mni.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void onDestroy() {

    super.onDestroy();

    try {

      this.getTbl().removeEvtTblOnChangeListener(this);
      this.limparListaAoSair();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onItemClick(AdapterView<?> viwParent, View viw, int intPosition, long intId) {

    Intent itt;
    ItmConsulta itmConsulta;

    try {

      itmConsulta = (ItmConsulta) this.getAdpCadastro().getItem(intPosition);

      if (itmConsulta == null) {

        return;
      }

      if (itmConsulta.getIntRegistroId() < 1) {

        return;
      }

      itt = new Intent();
      itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_REGISTRO_ID, itmConsulta.getIntRegistroId());

      this.getTbl().setStrPesquisa(this.getEdtPesquisa().getText().toString());
      this.setResult(ActConsulta.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(), itt);
      this.finish();

    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(115), ex);
    }
    finally {
    }
  }

  @Override
  public boolean onItemLongClick(AdapterView<?> viwParent, View viw, int intPosition, long intId) {

    ItmConsulta itmConsulta;

    try {

      itmConsulta = (ItmConsulta) this.getPnlLista().getItemAtPosition(intPosition);

      if (viw == null) {

        return false;
      }

      if (itmConsulta == null) {

        return false;
      }

      if (itmConsulta.getIntRegistroId() < 1) {

        return false;
      }

      this.setItmSelec(itmConsulta);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem mni) {

    try {

      if (super.onOptionsItemSelected(mni)) {

        return true;
      }

      if (DbTabelaAndroid.STR_MENU_PESQUISAR.equals(mni.getTitle())) {

        this.onOptionsItemSelectedPesquisar(mni);
        return true;
      }

      this.getTbl().processarMenu(this, mni);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return false;
  }

  private void onOptionsItemSelectedPesquisar(MenuItem mni) {

    try {

      this.getPnlPesquisa().setVisibility(!mni.isChecked() ? View.VISIBLE : View.GONE);

      mni.setChecked(!mni.isChecked());

      if (this.getPnlPesquisa().getVisibility() == View.VISIBLE) {

        this.getEdtPesquisa().requestFocus();
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu mnu) {

    super.onPrepareOptionsMenu(mnu);

    try {

      mnu.clear();

      this.onCreateOptionsMenuPesquisar(mnu);
      this.onCreateOptionsMenuAdicionar(mnu);
      this.getTbl().montarMenu(mnu);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return true;
  }

  private void recuperarUltimaPesquisa() {

    try {

      if (Utils.getBooStrVazia(this.getTbl().getStrPesquisa())) {

        this.getPnlPesquisa().setVisibility(View.GONE);
        return;
      }

      this.getEdtPesquisa().setText(this.getTbl().getStrPesquisa());
      this.getPnlPesquisa().setVisibility(View.VISIBLE);
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

      this.getEdtPesquisa().addTextChangedListener(this.getEvtEdtPesquisa_TextWatcher());

      this.getPnlLista().setLongClickable(true);
      this.getPnlLista().setOnItemClickListener(this);
      this.getPnlLista().setOnItemLongClickListener(this);

      this.registerForContextMenu(this.getPnlLista());

      this.recuperarUltimaPesquisa();
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