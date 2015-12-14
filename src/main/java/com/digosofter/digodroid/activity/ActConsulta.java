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
import com.digosofter.digodroid.controle.item.ItemConsulta;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.OnChangeArg;
import com.digosofter.digojava.database.OnChangeListener;

/**/
public class ActConsulta extends ActMain implements OnItemClickListener, OnItemLongClickListener, OnChangeListener, TextWatcher {

  public enum EnmResultadoTipo {

    NONE,
    REGISTRO_SELECIONADO,
    VOLTAR,
  }

  public static final String STR_EXTRA_IN_BOO_LIMPAR_LISTA_AO_SAIR = "boo_limpar_lista_ao_sair";
  public static final String STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL = "boo_registro_selecionavel";
  public static final String STR_EXTRA_IN_INT_REGISTRO_REF_ID = "int_registro_ref_id";
  public static final String STR_EXTRA_IN_INT_TBL_OBJETO_ID = "int_tbl_objeto_id";
  public static final String STR_EXTRA_OUT_INT_REGISTRO_ID = "int_registro_id";
  public static final String STR_EXTRA_OUT_INT_TBL_OBJETO_ID = "int_tbl_objeto_id";

  private AdpConsulta _adpCadastro;
  private boolean _booRegistroSelecionavel;
  private EditText _edtPesquisa;
  private int _intRegistroRefId = -1;
  private ItemConsulta _itmSelec;
  private ListView _pnlLista;
  private LinearLayout _pnlPesquisa;
  private DbTabelaAndroid<?> _tbl;
  private TextView _txtTblDescricao;
  private TextView _txtVazio;

  public ActConsulta() {

    try {

      this.setResult(EnmResultadoTipo.NONE.ordinal());

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
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

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void afterTextChanged(Editable s) {

  }

  public void atualizarLista() {

    try {

      this.getAdpCadastro().atualizarLista();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  private AdpConsulta getAdpCadastro() {

    try {

      if (_adpCadastro != null) {

        return _adpCadastro;
      }

      if (this.getTbl() == null) {

        return null;
      }

      _adpCadastro = new AdpConsulta(this, this.getTbl().pesquisarConsulta());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _adpCadastro;
  }

  private boolean getBooRegistroSelecionavel() {

    try {

      _booRegistroSelecionavel = this.getIntent().getBooleanExtra(ActConsulta.STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL, true);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _booRegistroSelecionavel;
  }

  private EditText getEdtPesquisa() {

    try {

      if (_edtPesquisa != null) {

        return _edtPesquisa;
      }

      _edtPesquisa = this.getView(R.id.actConsulta_edtPesquisa, EditText.class);

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }

    return _edtPesquisa;
  }

  @Override
  protected int getIntLayoutId() {

    return R.layout.act_consulta;
  }

  private int getIntRegistroRefId() {

    try {

      if (_intRegistroRefId > -1) {

        return _intRegistroRefId;
      }

      _intRegistroRefId = this.getIntent().getIntExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, 0);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _intRegistroRefId;
  }

  private ItemConsulta getItmSelec() {

    return _itmSelec;
  }

  private ListView getPnlLista() {

    try {

      if (_pnlLista != null) {

        return _pnlLista;
      }

      _pnlLista = this.getView(R.id.actConsulta_pnlLista, ListView.class);

      _pnlLista.setCacheColorHint(Color.TRANSPARENT);

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }

    return _pnlLista;
  }

  private LinearLayout getPnlPesquisa() {

    try {

      if (_pnlPesquisa != null) {

        return _pnlPesquisa;
      }

      _pnlPesquisa = this.getView(R.id.actConsulta_pnlPesquisa, LinearLayout.class);

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }

    return _pnlPesquisa;
  }

  public DbTabelaAndroid<?> getTbl() {

    int intTblObjetoId;

    try {

      if (_tbl != null) {

        return _tbl;
      }

      intTblObjetoId = this.getIntent().getIntExtra(STR_EXTRA_IN_INT_TBL_OBJETO_ID, -1);

      if (intTblObjetoId < 0) {

        return null;
      }

      _tbl = AppAndroid.getI().getTbl(intTblObjetoId);

      if (_tbl == null) {

        return null;
      }

      _tbl.addOnChangeListener(this);

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }

    return _tbl;
  }

  private TextView getTxtTblDescricao() {

    try {

      if (_txtTblDescricao != null) {

        return _txtTblDescricao;
      }

      _txtTblDescricao = this.getView(R.id.actConsulta_pnlPesquisa, TextView.class);

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }

    return _txtTblDescricao;
  }

  private TextView getTxtVazio() {

    try {

      if (_txtVazio != null) {

        return _txtVazio;
      }

      _txtVazio = this.getView(R.id.actConsulta_txtVazio, TextView.class);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _txtVazio;
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();

    try {

      this.montarLayoutTitulo();
      this.montarLayoutLista();
      this.montarLayoutVazio();

      this.montarLayoutAbrirCadastro();

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(114), ex);
    } finally {
    }
  }

  private void montarLayoutAbrirCadastro() {

    Intent itt;

    try {

      if (this.getTbl() == null) {

        return;
      }

      if (!this.getTbl().getBooMenuAdicionar()) {

        return;
      }

      if (this.getTbl().getClsActCadastro() == null) {

        return;
      }

      if (!this.getTbl().getBooAbrirCadastroAuto()) {

        return;
      }

      itt = new Intent(this, this.getTbl().getClsActCadastro());

      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, this.getIntent().getIntExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, this.getIntRegistroRefId()));

      this.startActivity(itt);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void montarLayoutLista() {

    try {

      this.getPnlLista().setAdapter(this.getAdpCadastro());

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }
  }

  private void montarLayoutTitulo() {

    try {

      if (this.getTbl() == null) {

        this.setTitle("<desconhecido>");
        return;
      }

      this.setTitle(this.getTbl().getStrNomeExibicao());

      if (Utils.getBooStrVazia(this.getTbl().getStrDescricao())) {

        return;
      }

      this.getTxtTblDescricao().setText(this.getTbl().getStrDescricao());
      this.getTxtTblDescricao().setVisibility(View.VISIBLE);

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }
  }

  private void montarLayoutVazio() {

    try {

      this.runOnUiThread(new Runnable() {

        @Override
        public void run() {

          ActConsulta.this.montarLayoutVazioLocal();
        }
      });

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  protected void montarLayoutVazioLocal() {

    try {

      if (this.getAdpCadastro().getCount() > 0) {

        this.getPnlLista().setVisibility(View.VISIBLE);
        this.getTxtVazio().setVisibility(View.GONE);
        return;
      }

      this.getPnlLista().setVisibility(View.GONE);
      this.getTxtVazio().setVisibility(View.VISIBLE);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void onAdicionarReg(OnChangeArg arg) {

    try {

      this.montarLayoutVazio();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void onApagarReg(OnChangeArg arg) {

    try {

      if (arg.getIntRegistroId() < 1) {

        return;
      }

      this.atualizarLista();
      this.montarLayoutVazio();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void onAtualizarReg(OnChangeArg arg) {

    try {

      this.atualizarLista();
      this.montarLayoutVazio();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
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

      if (this.getItmSelec() == null) {

        return true;
      }

      this.getTbl().processarMenuItem(this, mni, this.getItmSelec().getIntRegistroId());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
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

      mnu.setHeaderTitle(this.getItmSelec().getStrRegistroNome());

      this.getTbl().montarMenuItem(mnu, this.getItmSelec().getIntRegistroId(), true);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
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

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
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

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  protected void onDestroy() {

    super.onDestroy();

    try {

      this.getTbl().removerOnChangeListener(this);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void onItemClick(AdapterView<?> objAdapterView, View viw, int intPosicao, long intRegistroId) {

    try {

      if (this.getBooRegistroSelecionavel()) {

        this.onItemClickRegistroSelecionar((int) intRegistroId);
        return;
      }

      this.onItemClickDetalhar((int) intRegistroId);

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(115), ex);
    } finally {
    }
  }

  private void onItemClickDetalhar(int intRegistroId) {

    try {

      this.getTbl().abrirActDetalhe(this, intRegistroId);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void onItemClickRegistroSelecionar(int intRegistroId) {

    Intent itt;

    try {

      if (intRegistroId < 1) {

        return;
      }

      if (this.getTbl() == null) {

        return;
      }

      itt = new Intent();

      itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_REGISTRO_ID, intRegistroId);
      itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_TBL_OBJETO_ID, this.getTbl().getIntObjetoId());

      this.getTbl().setStrPesquisa(this.getEdtPesquisa().getText().toString());
      this.setResult(ActConsulta.EnmResultadoTipo.REGISTRO_SELECIONADO.ordinal(), itt);
      this.finish();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public boolean onItemLongClick(AdapterView<?> viwParent, View viw, int intPosition, long intId) {

    try {

      if (viw == null) {

        return false;
      }

      if (viw == null) {

        return false;
      }

      if (!viw.getClass().equals(ItemConsulta.class)) {

        return false;
      }

      this.setItmSelec((ItemConsulta) viw);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
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

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }

    return false;
  }

  private void onOptionsItemSelectedPesquisar(MenuItem mni) {

    try {

      this.getPnlPesquisa().setVisibility(!mni.isChecked() ? View.VISIBLE : View.GONE);

      mni.setChecked(!mni.isChecked());

      if (this.getPnlPesquisa().getVisibility() == View.VISIBLE) {

        this.mostrarTeclado(this.getEdtPesquisa());
      }
    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu mnu) {

    super.onPrepareOptionsMenu(mnu);

    try {

      mnu.clear();

      this.onCreateOptionsMenuPesquisar(mnu);
      this.onCreateOptionsMenuAdicionar(mnu);
      this.onPrepareOptionsMenuTbl(mnu);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return true;
  }

  private void onPrepareOptionsMenuTbl(Menu mnu) {

    try {

      if (this.getTbl() == null) {

        return;
      }

      this.getTbl().montarMenu(mnu);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

    try {

      this.getAdpCadastro().getFilter().filter(s);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void recuperarUltimaPesquisa() {

    try {

      if (Utils.getBooStrVazia(this.getTbl().getStrPesquisa())) {

        this.getPnlPesquisa().setVisibility(View.GONE);
        return;
      }

      this.getEdtPesquisa().setText(this.getTbl().getStrPesquisa());
      this.getPnlPesquisa().setVisibility(View.VISIBLE);

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }
  }

  @Override
  protected void setEventos() {

    super.setEventos();

    try {

      this.getEdtPesquisa().addTextChangedListener(this);
      this.getPnlLista().setLongClickable(true);
      this.getPnlLista().setOnItemClickListener(this);
      this.getPnlLista().setOnItemLongClickListener(this);

      this.registerForContextMenu(this.getPnlLista());

      this.recuperarUltimaPesquisa();

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(116), ex);
    } finally {
    }
  }

  private void setItmSelec(ItemConsulta itmSelec) {

    _itmSelec = itmSelec;
  }
}