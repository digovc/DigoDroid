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
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.adapter.AdapterConsulta;
import com.digosofter.digodroid.animacao.Animar;
import com.digosofter.digodroid.componente.botao.BotaoCircular;
import com.digosofter.digodroid.componente.item.ItemConsulta;
import com.digosofter.digodroid.componente.label.LabelGeral;
import com.digosofter.digodroid.componente.painel.PainelGeralRelativo;
import com.digosofter.digodroid.componente.textbox.TextBoxGeral;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.log.LogErro;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.OnChangeArg;
import com.digosofter.digojava.database.OnTblChangeListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**/
public class ActConsulta extends ActMain implements OnTblChangeListener, TextWatcher, OnClickListener
{
  public enum EnmResultado
  {
    NONE,
    REGISTRO_SELECIONADO,
    REGISTRO_SELECIONADO_MULTIPLO,
    VOLTAR,
  }

  /**
   * Indica se o cadastro será aberto automaticamente.
   */
  public static final String STR_EXTRA_IN_BOO_ABRIR_CADASTRO_AUTO = "boo_abrir_cadastro_auto";
  public static final String STR_EXTRA_IN_BOO_MULTIPLA_SELECAO = "boo_multipla_selecao";
  /**
   * Indica se os registro da lista serão selecionados quando o usuário clicar.
   */
  public static final String STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL = "boo_registro_selecionavel";
  public static final String STR_EXTRA_IN_INT_COLUNA_OBJETO_ID = "int_coluna_objeto_id";

  /**
   * Código do registro de referência.
   */
  public static final String STR_EXTRA_IN_INT_REGISTRO_REF_ID = "int_registro_ref_id";
  /**
   * Código do objeto da tabela que esta lista representa.
   */
  public static final String STR_EXTRA_IN_INT_TABELA_OBJETO_ID = "int_tabela_objeto_id";
  public static final String STR_EXTRA_IN_INT_TABELA_PAI_OBJETO_ID = "int_tabela_pai_objeto_id";
  public static final String STR_EXTRA_OUT_ARR_INT_REGISTRO_ID = "arr_int_registro_id";
  public static final String STR_EXTRA_OUT_INT_COLUNA_OBJETO_ID = "int_coluna_objeto_id_out";
  /**
   * Código do registro que indica o consulta_item que o usuário selecionou na lista desta tela.
   */
  public static final String STR_EXTRA_OUT_INT_REGISTRO_ID = "int_registro_id";
  public static final String STR_EXTRA_OUT_INT_REGISTRO_REF_ID = "int_registro_ref_id_out";
  /**
   * Código do objeto da tabela que esta lista representa.
   */
  public static final String STR_EXTRA_OUT_INT_TABELA_OBJETO_ID = "int_tabela_objeto_id";

  private static final String STR_MENU_PESQUISAR = "Pesquisar";
  private static final String STR_MENU_SELECIONAR = "Selecionar";

  private AdapterConsulta _adpCadastro;
  private boolean _booAbrindoActDetalhe;
  private BotaoCircular _btnPesquisaLimpar;
  private Calendar _dttItemClickUltimo;
  private int _intRegistroRefId = -1;
  private ItemConsulta _itmSelecionado;
  private LabelGeral _lblVazio;
  private List<Integer> _lstIntRegistroSelecionadoId;
  private ListView _pnlLista;
  private PainelGeralRelativo _pnlPesquisa;
  private TblAndroidMain<?> _tbl;
  private TblAndroidMain _tblPai;
  private TextBoxGeral _txtPesquisa;
  private TextView _txtTblDescricao;

  public ActConsulta()
  {
    this.setResult(EnmResultado.NONE.ordinal());
  }

  @Override
  public void afterTextChanged(Editable s)
  {
  }

  private void atualizarBtnPesquisarLimparVisibilidade(final CharSequence strFiltro)
  {
    if (strFiltro == null || strFiltro.length() == 0)
    {
      this.getBtnPesquisaLimpar().setVisibility(View.INVISIBLE);
      return;
    }

    this.getBtnPesquisaLimpar().setVisibility(View.VISIBLE);
  }

  public void atualizarLista()
  {
    this.getPnlLista().post(new Runnable()
    {
      @Override
      public void run()
      {

        ActConsulta.this.getAdpCadastro().atualizarLista();

        ActConsulta.this.montarLayoutVazio();
      }
    });
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after)
  {
  }

  @Override
  protected void finalizar()
  {
    super.finalizar();

    if (this.getTbl() != null)
    {
      this.getTbl().removerEvtOnTblChangeListener(this);
    }
  }

  public AdapterConsulta getAdpCadastro()
  {
    if (_adpCadastro != null)
    {
      return _adpCadastro;
    }

    if (this.getTbl() == null)
    {
      return null;
    }

    _adpCadastro = new AdapterConsulta(this);

    return _adpCadastro;
  }

  private boolean getBooAbrindoActDetalhe()
  {
    return _booAbrindoActDetalhe;
  }

  public boolean getBooMultiplaSelecao()
  {
    return this.getIntent().getBooleanExtra(STR_EXTRA_IN_BOO_MULTIPLA_SELECAO, false);
  }

  private boolean getBooRegistroSelecionavel()
  {
    return this.getIntent().getBooleanExtra(ActConsulta.STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL, false);
  }

  private BotaoCircular getBtnPesquisaLimpar()
  {
    if (_btnPesquisaLimpar != null)
    {
      return _btnPesquisaLimpar;
    }

    _btnPesquisaLimpar = this.getView(R.id.actConsulta_btnPesquisaLimpar);

    return _btnPesquisaLimpar;
  }

  private Calendar getDttItemClickUltimo()
  {
    return _dttItemClickUltimo;
  }

  @Override
  public int getIntLayoutId()
  {
    return R.layout.act_consulta;
  }

  public int getIntRegistroRefId()
  {
    if (_intRegistroRefId > -1)
    {
      return _intRegistroRefId;
    }

    _intRegistroRefId = this.getIntent().getIntExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, 0);

    return _intRegistroRefId;
  }

  private ItemConsulta getItmSelecionado()
  {
    return _itmSelecionado;
  }

  private LabelGeral getLblVazio()
  {
    if (_lblVazio != null)
    {
      return _lblVazio;
    }

    _lblVazio = this.getView(R.id.actConsulta_lblVazio);

    return _lblVazio;
  }

  private List<Integer> getLstIntRegistroSelecionadoId()
  {
    if (_lstIntRegistroSelecionadoId != null)
    {
      return _lstIntRegistroSelecionadoId;
    }

    _lstIntRegistroSelecionadoId = new ArrayList<>();

    return _lstIntRegistroSelecionadoId;
  }

  private ListView getPnlLista()
  {
    if (_pnlLista != null)
    {
      return _pnlLista;
    }

    _pnlLista = this.getView(R.id.actConsulta_pnlLista);

    _pnlLista.setCacheColorHint(Color.TRANSPARENT);

    return _pnlLista;
  }

  private PainelGeralRelativo getPnlPesquisa()
  {
    if (_pnlPesquisa != null)
    {
      return _pnlPesquisa;
    }

    _pnlPesquisa = this.getView(R.id.actConsulta_pnlPesquisa);

    return _pnlPesquisa;
  }

  public TblAndroidMain<?> getTbl()
  {
    if (_tbl != null)
    {
      return _tbl;
    }

    if (AppAndroid.getI() == null)
    {
      return null;
    }

    if (AppAndroid.getI().getDbe() == null)
    {
      return null;
    }

    _tbl = (TblAndroidMain<?>) AppAndroid.getI().getDbe().getTbl(this.getIntent().getIntExtra(STR_EXTRA_IN_INT_TABELA_OBJETO_ID, -1));

    if (_tbl == null)
    {
      return null;
    }

    _tbl.addEvtOnTblChangeListener(this);

    return _tbl;
  }

  public TblAndroidMain getTblPai()
  {
    if (_tblPai != null)
    {
      return _tblPai;
    }

    if (AppAndroid.getI() == null)
    {
      return null;
    }

    if (AppAndroid.getI().getDbe() == null)
    {
      return null;
    }

    _tblPai = (TblAndroidMain) AppAndroid.getI().getDbe().getTbl(this.getIntent().getIntExtra(STR_EXTRA_IN_INT_TABELA_PAI_OBJETO_ID, -1));

    if (_tblPai == null)
    {
      return null;
    }

    _tblPai = (TblAndroidMain) _tblPai.getTblPrincipal();

    return _tblPai;
  }

  private TextBoxGeral getTxtPesquisa()
  {
    if (_txtPesquisa != null)
    {
      return _txtPesquisa;
    }

    _txtPesquisa = this.getView(R.id.actConsulta_txtPesquisa);

    return _txtPesquisa;
  }

  private TextView getTxtTblDescricao()
  {
    if (_txtTblDescricao != null)
    {
      return _txtTblDescricao;
    }

    _txtTblDescricao = this.getView(R.id.actConsulta_pnlPesquisa);

    return _txtTblDescricao;
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.getActionBar().setDisplayUseLogoEnabled(false);
  }

  @Override
  protected void montarLayout()
  {
    super.montarLayout();

    this.montarLayoutTitulo();
    this.montarLayoutLista();
    this.montarLayoutVazio();
    this.montarLayoutAbrirCadastroAuto();
  }

  private void montarLayoutAbrirCadastroAuto()
  {
    if (this.getTbl() == null)
    {
      return;
    }

    if (this.getTbl().getClsActCadastro() == null)
    {
      return;
    }

    if (!this.getIntent().getBooleanExtra(STR_EXTRA_IN_BOO_ABRIR_CADASTRO_AUTO, false))
    {
      return;
    }

    this.getTbl().abrirCadastro(this, 0, this.getTblPai(), this.getIntRegistroRefId());
  }

  private void montarLayoutLista()
  {
    this.getPnlLista().setAdapter(this.getAdpCadastro());
  }

  private void montarLayoutTitulo()
  {
    if (this.getTbl() == null)
    {
      this.setTitle("<desconhecido>");
      return;
    }

    this.setTitle(this.getTbl().getStrNomeExibicao());

    if (Utils.getBooStrVazia(this.getTbl().getStrDescricao()))
    {
      return;
    }

    this.getTxtTblDescricao().setText(this.getTbl().getStrDescricao());
    this.getTxtTblDescricao().setVisibility(View.VISIBLE);
  }

  private void montarLayoutVazio()
  {
    this.runOnUiThread(new Runnable()
    {
      @Override
      public void run()
      {
        ActConsulta.this.montarLayoutVazioLocal();
      }
    });
  }

  protected void montarLayoutVazioLocal()
  {
    if (this.getAdpCadastro().getCount() > 0)
    {
      this.getPnlLista().setVisibility(View.VISIBLE);
      this.getLblVazio().setVisibility(View.GONE);
      return;
    }

    this.getPnlLista().setVisibility(View.GONE);
    this.getLblVazio().setVisibility(View.VISIBLE);
  }

  @Override
  public void onBackPressed()
  {
    if (this.getTbl() == null)
    {
      return;
    }

    Intent itt = new Intent();

    itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_TABELA_OBJETO_ID, this.getTbl().getIntObjetoId());
    itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_COLUNA_OBJETO_ID, this.getIntent().getIntExtra(STR_EXTRA_IN_INT_COLUNA_OBJETO_ID, -1));

    this.setResult(EnmResultado.VOLTAR.ordinal(), itt);

    super.onBackPressed();
  }

  @Override
  public void onClick(final View viw)
  {
    if (this.getBtnPesquisaLimpar().equals(viw))
    {
      this.getTxtPesquisa().limparTexto();
      return;
    }
  }

  @Override
  public boolean onContextItemSelected(MenuItem mni)
  {
    try
    {
      if (super.onContextItemSelected(mni))
      {
        return true;
      }

      if (this.getTbl() == null)
      {
        return false;
      }

      return this.getTbl().processarMenuItem(this, mni, this.getItmSelecionado().getIntRegistroId());
    }
    catch (Exception ex)
    {
      LogErro.getI().addLog(this, ex);
    }

    return false;
  }

  @Override
  public void onCreateContextMenu(ContextMenu mnu, View viw, ContextMenuInfo objContextMenuInfo)
  {
    super.onCreateContextMenu(mnu, viw, objContextMenuInfo);

    if (this.getTbl() == null)
    {
      return;
    }

    if (!ItemConsulta.class.isAssignableFrom(viw.getClass()))
    {
      return;
    }

    if (((ItemConsulta) viw).getIntRegistroId() < 1)
    {
      return;
    }

    this.setItmSelecionado((ItemConsulta) viw);

    mnu.setHeaderTitle(this.getItmSelecionado().getStrRegistroNome());

    this.getTbl().montarMenuItem(mnu, this.getItmSelecionado().getIntRegistroId(), true);
  }

  public void onItemClick(final ItemConsulta viwItem)
  {
    if ((this.getDttItemClickUltimo() != null) && (Calendar.getInstance().getTimeInMillis() - this.getDttItemClickUltimo().getTimeInMillis()) < 1000)
    {
      return;
    }

    this.setDttItemClickUltimo(Calendar.getInstance());

    if (viwItem == null)
    {
      return;
    }

    if (this.onItemClickTabela(viwItem))
    {
      return;
    }

    if (this.onItemClickMultiplaSelecao(viwItem))
    {
      return;
    }

    if (this.getBooRegistroSelecionavel())
    {
      this.onItemClickRegistroSelecionar(viwItem.getIntRegistroId());
      return;
    }

    if (this.getBooAbrindoActDetalhe())
    {
      return;
    }

    this.onItemClickDetalhar(viwItem.getIntRegistroId());
  }

  private void onItemClickDetalhar(int intRegistroId)
  {
    this.setBooAbrindoActDetalhe(true);

    this.getTbl().abrirDetalhe(this, intRegistroId);
  }

  private boolean onItemClickMultiplaSelecao(final ItemConsulta viwItem)
  {
    if (!this.getBooMultiplaSelecao())
    {
      return false;
    }

    viwItem.setBooSelecionado(!viwItem.getBooSelecionado());

    if (viwItem.getBooSelecionado())
    {
      this.getLstIntRegistroSelecionadoId().add(viwItem.getIntRegistroId());
    }
    else
    {
      this.getLstIntRegistroSelecionadoId().remove(this.getLstIntRegistroSelecionadoId().indexOf(viwItem.getIntRegistroId()));
    }

    return true;
  }

  private void onItemClickRegistroSelecionar(int intRegistroId)
  {
    if (intRegistroId < 1)
    {
      return;
    }

    if (this.getTbl() == null)
    {
      return;
    }

    Intent itt = new Intent();

    itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_REGISTRO_ID, intRegistroId);
    itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_TABELA_OBJETO_ID, this.getTbl().getIntObjetoId());
    itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_COLUNA_OBJETO_ID, this.getIntent().getIntExtra(STR_EXTRA_IN_INT_COLUNA_OBJETO_ID, -1));

    this.getTbl().setStrPesquisa(this.getTxtPesquisa().getText().toString());

    this.setResult(EnmResultado.REGISTRO_SELECIONADO.ordinal(), itt);

    this.finish();
  }

  private boolean onItemClickTabela(final ItemConsulta viwItem)
  {
    if (this.getTbl() == null)
    {
      return false;
    }

    return this.getTbl().processarItemClick(this, viwItem);
  }

  public void onItemLongClick(final ItemConsulta viwItem)
  {
    if (viwItem == null)
    {
      return;
    }

    if (viwItem.getIntRegistroId() < 1)
    {
      return;
    }

    this.registerForContextMenu(viwItem);

    this.openContextMenu(viwItem);

    this.unregisterForContextMenu(viwItem);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem mni)
  {
    if (super.onOptionsItemSelected(mni))
    {
      return true;
    }

    if (STR_MENU_PESQUISAR.equals(mni.getTitle()))
    {
      return this.onOptionsItemSelectedPesquisar(mni);
    }

    if (STR_MENU_SELECIONAR.equals(mni.getTitle()))
    {
      return this.onOptionsItemSelectedSelecionar(mni);
    }

    return this.getTbl().processarMenu(this, mni);
  }

  private boolean onOptionsItemSelectedPesquisar(MenuItem mni)
  {
    this.getPnlPesquisa().setVisibility(!mni.isChecked() ? View.VISIBLE : View.GONE);

    mni.setChecked(!mni.isChecked());

    if (this.getPnlPesquisa().getVisibility() != View.VISIBLE)
    {
      return true;
    }

    this.mostrarTeclado(this.getTxtPesquisa());

    return true;
  }

  private boolean onOptionsItemSelectedSelecionar(final MenuItem mni)
  {
    if (this.getTbl() == null)
    {
      return false;
    }

    if (!this.getBooMultiplaSelecao())
    {
      return false;
    }

    if (this.getLstIntRegistroSelecionadoId().isEmpty())
    {
      this.notificar("Pelo menos um registro deve ser selecionado.");
      return false;
    }

    if (this.getTbl().processarOptionItemSelectedSelecionar(this, this.getLstIntRegistroSelecionadoId()))
    {
      return true;
    }

    Intent itt = new Intent();

    itt.putExtra(ActConsulta.STR_EXTRA_OUT_ARR_INT_REGISTRO_ID, this.getLstIntRegistroSelecionadoId().toArray());
    itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_COLUNA_OBJETO_ID, this.getIntent().getIntExtra(STR_EXTRA_IN_INT_COLUNA_OBJETO_ID, -1));
    itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_REGISTRO_REF_ID, this.getIntRegistroRefId());
    itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_TABELA_OBJETO_ID, this.getTbl().getIntObjetoId());

    this.getTbl().setStrPesquisa(this.getTxtPesquisa().getText().toString());

    this.setResult(EnmResultado.REGISTRO_SELECIONADO_MULTIPLO.ordinal(), itt);

    this.finish();

    return true;
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu mnu)
  {
    super.onPrepareOptionsMenu(mnu);

    mnu.clear();

    this.onPrepareOptionsMenuSelecionar(mnu);
    this.onPrepareOptionsMenuPesquisar(mnu);
    this.onPrepareOptionsMenuAdicionar(mnu);
    this.onPrepareOptionsMenuTbl(mnu);

    return true;
  }

  private void onPrepareOptionsMenuAdicionar(Menu mnu)
  {
    if (mnu == null)
    {
      return;
    }

    if (this.getTbl() == null)
    {
      return;
    }

    if (this.getTbl().getClsActCadastro() == null)
    {
      return;
    }

    if (!this.getTbl().getBooPermitirAdicionar())
    {
      return;
    }

    MenuItem mni = mnu.add(TblAndroidMain.STR_MENU_ADICIONAR);

    mni.setIcon(R.drawable.adicionar);
    mni.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
  }

  private void onPrepareOptionsMenuPesquisar(Menu mnu)
  {
    if (this.getTbl() == null)
    {
      return;
    }

    if (this.getAdpCadastro().getCount() < 15)
    {
      return;
    }

    MenuItem mni = mnu.add(STR_MENU_PESQUISAR);

    mni.setCheckable(true);
    mni.setIcon(R.drawable.pesquisar);
    mni.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
  }

  private void onPrepareOptionsMenuSelecionar(final Menu mnu)
  {
    if (!this.getBooMultiplaSelecao())
    {
      return;
    }

    MenuItem mni = mnu.add(STR_MENU_SELECIONAR);

    mni.setCheckable(true);
    mni.setIcon(R.drawable.salvar);
    mni.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
  }

  private void onPrepareOptionsMenuTbl(Menu mnu)
  {
    if (this.getTbl() == null)
    {
      return;
    }

    this.getTbl().montarMenu(this, mnu);
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    this.setBooAbrindoActDetalhe(false);
  }

  @Override
  public void onTabelaAdicionar(OnChangeArg arg)
  {
    this.atualizarLista();
  }

  @Override
  public void onTabelaApagar(OnChangeArg arg)
  {
    if (arg.getIntRegistroId() < 1)
    {
      return;
    }

    this.atualizarLista();

    this.montarLayoutVazio();
  }

  @Override
  public void onTabelaAtualizar(OnChangeArg arg)
  {
    this.atualizarLista();
  }

  @Override
  public void onTextChanged(CharSequence strFiltro, int intStart, int intBefore, int intCount)
  {
    this.atualizarBtnPesquisarLimparVisibilidade(strFiltro);

    this.getAdpCadastro().getFilter().filter(strFiltro);
  }

  private void recuperarUltimaPesquisa()
  {
    if (Utils.getBooStrVazia(this.getTbl().getStrPesquisa()))
    {
      this.getPnlPesquisa().setVisibility(View.GONE);
      return;
    }

    this.getTxtPesquisa().setText(this.getTbl().getStrPesquisa());

    Animar.getI().aparecerSlideDown(this.getPnlPesquisa());
  }

  private void setAdpCadastro(final AdapterConsulta adpCadastro)
  {
    _adpCadastro = adpCadastro;
  }

  private void setBooAbrindoActDetalhe(boolean booAbrindoActDetalhe)
  {
    _booAbrindoActDetalhe = booAbrindoActDetalhe;
  }

  private void setDttItemClickUltimo(Calendar dttItemClickUltimo)
  {
    _dttItemClickUltimo = dttItemClickUltimo;
  }

  @Override
  protected void setEventos()
  {
    super.setEventos();

    this.getBtnPesquisaLimpar().setOnClickListener(this);
    this.getTxtPesquisa().addTextChangedListener(this);
    this.getPnlLista().setLongClickable(true);
    this.recuperarUltimaPesquisa();
  }

  private void setItmSelecionado(ItemConsulta itmSelecionado)
  {
    _itmSelecionado = itmSelecionado;
  }

  @Override
  public boolean validarAbertura()
  {
    if (!super.validarAbertura())
    {
      return false;
    }

    if (this.getTbl() == null)
    {
      LogErro.getI().addLog("A tabela não foi encontrada.");
      return false;
    }

    return true;
  }

}