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
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.OnChangeArg;
import com.digosofter.digojava.database.OnTblChangeListener;

/**/
public class ActConsulta extends ActMain implements OnTblChangeListener, TextWatcher, OnClickListener
{

  public enum EnmResultado
  {
    NONE,
    REGISTRO_SELECIONADO,
    VOLTAR,
  }

  /**
   * Indica se o cadastro será aberto automaticamente.
   */
  public static final String STR_EXTRA_IN_BOO_ABRIR_CADASTRO_AUTO = "boo_abrir_cadastro_auto";

  /**
   * Indica se os registro da lista serão selecionados quando o usuário clicar.
   */
  public static final String STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL = "boo_registro_selecionavel";
  /**
   * Código do registro de referência.
   */
  public static final String STR_EXTRA_IN_INT_REGISTRO_REF_ID = "int_registro_ref_id";
  /**
   * Código do objeto da tabela que esta lista representa.
   */
  public static final String STR_EXTRA_IN_INT_TBL_OBJETO_ID = "int_tbl_objeto_id";
  /**
   * Código do registro que indica o item que o usuário selecionou na lista desta tela.
   */
  public static final String STR_EXTRA_OUT_INT_REGISTRO_ID = "int_registro_id";

  /**
   * Código do objeto da tabela que esta lista representa.
   */
  public static final String STR_EXTRA_OUT_INT_TBL_OBJETO_ID = "int_tbl_objeto_id";

  private static final String STR_MENU_PESQUISAR = "Pesquisar";

  private AdapterConsulta _adpCadastro;
  private boolean _booAbrindoActDetalhe;
  private boolean _booRegistroSelecionavel;
  private BotaoCircular _btnPesquisaLimpar;
  private int _intRegistroRefId = -1;
  private ItemConsulta _itmSelecionado;
  private LabelGeral _lblVazio;
  private ListView _pnlLista;
  private PainelGeralRelativo _pnlPesquisa;
  private TabelaAndroid<?> _tbl;
  private TextBoxGeral _txtPesquisa;
  private TextView _txtTblDescricao;

  public ActConsulta()
  {
    try
    {
      this.setResult(EnmResultado.NONE.ordinal());
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
  }

  @Override
  public void afterTextChanged(Editable s)
  {
  }

  private void atualizarBtnPesquisarLimparVisibilidade(final CharSequence strFiltro)
  {
    try
    {
      if (strFiltro == null || strFiltro.length() == 0)
      {
        this.getBtnPesquisaLimpar().setVisibility(View.INVISIBLE);
      }
      else
      {
        this.getBtnPesquisaLimpar().setVisibility(View.VISIBLE);
      }
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void atualizarLista()
  {
    try
    {
      this.getPnlLista().post(new Runnable()
      {
        @Override
        public void run()
        {
          ActConsulta.this.getAdpCadastro().atualizarLista();
        }
      });
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }

  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after)
  {
  }

  @Override
  protected void finalizar()
  {
    super.finalizar();
    try
    {
      this.getTbl().removerEvtOnTblChangeListener(this);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private AdapterConsulta getAdpCadastro()
  {
    try
    {
      if (_adpCadastro != null)
      {
        return _adpCadastro;
      }
      if (this.getTbl() == null)
      {
        return null;
      }
      _adpCadastro = new AdapterConsulta(this, this.getTbl().pesquisarConsulta());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _adpCadastro;
  }

  private boolean getBooAbrindoActDetalhe()
  {
    return _booAbrindoActDetalhe;
  }

  private boolean getBooRegistroSelecionavel()
  {
    try
    {
      _booRegistroSelecionavel = this.getIntent().getBooleanExtra(ActConsulta.STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL, false);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _booRegistroSelecionavel;
  }

  private BotaoCircular getBtnPesquisaLimpar()
  {
    try
    {
      if (_btnPesquisaLimpar != null)
      {
        return _btnPesquisaLimpar;
      }
      _btnPesquisaLimpar = this.getView(R.id.actConsulta_btnPesquisaLimpar, BotaoCircular.class);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _btnPesquisaLimpar;
  }

  @Override
  protected int getIntLayoutId()
  {
    return R.layout.act_consulta;
  }

  private int getIntRegistroRefId()
  {
    try
    {
      if (_intRegistroRefId > -1)
      {
        return _intRegistroRefId;
      }
      _intRegistroRefId = this.getIntent().getIntExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, 0);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _intRegistroRefId;
  }

  private ItemConsulta getItmSelecionado()
  {
    return _itmSelecionado;
  }

  private LabelGeral getLblVazio()
  {
    try
    {
      if (_lblVazio != null)
      {
        return _lblVazio;
      }
      _lblVazio = this.getView(R.id.actConsulta_lblVazio, LabelGeral.class);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _lblVazio;
  }

  private ListView getPnlLista()
  {
    try
    {
      if (_pnlLista != null)
      {
        return _pnlLista;
      }
      _pnlLista = this.getView(R.id.actConsulta_pnlLista, ListView.class);
      _pnlLista.setCacheColorHint(Color.TRANSPARENT);
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
    return _pnlLista;
  }

  private PainelGeralRelativo getPnlPesquisa()
  {
    try
    {
      if (_pnlPesquisa != null)
      {
        return _pnlPesquisa;
      }
      _pnlPesquisa = this.getView(R.id.actConsulta_pnlPesquisa, PainelGeralRelativo.class);
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
    return _pnlPesquisa;
  }

  public TabelaAndroid<?> getTbl()
  {
    int intTblObjetoId;
    try
    {
      if (_tbl != null)
      {
        return _tbl;
      }
      intTblObjetoId = this.getIntent().getIntExtra(STR_EXTRA_IN_INT_TBL_OBJETO_ID, -1);
      if (intTblObjetoId < 0)
      {
        return null;
      }
      _tbl = AppAndroid.getI().getTbl(intTblObjetoId);
      if (_tbl == null)
      {
        return null;
      }
      _tbl.addEvtOnTblChangeListener(this);
      _tbl.setIntRegistroRefId(this.getIntRegistroRefId());
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
    return _tbl;
  }

  private TextBoxGeral getTxtPesquisa()
  {
    try
    {
      if (_txtPesquisa != null)
      {
        return _txtPesquisa;
      }
      _txtPesquisa = this.getView(R.id.actConsulta_txtPesquisa, TextBoxGeral.class);
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
    return _txtPesquisa;
  }

  private TextView getTxtTblDescricao()
  {
    try
    {
      if (_txtTblDescricao != null)
      {
        return _txtTblDescricao;
      }
      _txtTblDescricao = this.getView(R.id.actConsulta_pnlPesquisa, TextView.class);
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
    return _txtTblDescricao;
  }

  @Override
  protected void montarLayout()
  {
    super.montarLayout();
    try
    {
      this.montarLayoutTitulo();
      this.montarLayoutLista();
      this.montarLayoutVazio();
      this.montarLayoutAbrirCadastroAuto();
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(114), ex);
    }
    finally
    {
    }
  }

  private void montarLayoutAbrirCadastroAuto()
  {
    Intent itt;
    try
    {
      if (this.getTbl() == null)
      {
        return;
      }
      if (!this.getTbl().getBooMenuAdicionar())
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
      itt = new Intent(this, this.getTbl().getClsActCadastro());
      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, this.getIntent().getIntExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, this.getIntRegistroRefId()));
      this.startActivity(itt);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void montarLayoutLista()
  {
    try
    {
      this.getPnlLista().setAdapter(this.getAdpCadastro());
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
  }

  private void montarLayoutTitulo()
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
  }

  private void montarLayoutVazio()
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  protected void montarLayoutVazioLocal()
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void onClick(final View v)
  {
    try
    {
      if (this.getBtnPesquisaLimpar().equals(v))
      {
        this.getTxtPesquisa().limparTexto();
      }
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public boolean onContextItemSelected(MenuItem mni)
  {
    try
    {
      if (mni == null)
      {
        return super.onContextItemSelected(mni);
      }
      if (this.getTbl() == null)
      {
        return super.onContextItemSelected(mni);
      }
      if (this.getItmSelecionado() == null)
      {
        return super.onContextItemSelected(mni);
      }
      this.getTbl().processarMenuItem(this, mni, this.getItmSelecionado().getIntRegistroId());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
      this.setItmSelecionado(null);
    }
    return true;
  }

  @Override
  public void onCreateContextMenu(ContextMenu mnu, View viw, ContextMenuInfo objContextMenuInfo)
  {
    super.onCreateContextMenu(mnu, viw, objContextMenuInfo);
    try
    {
      if (this.getTbl() == null)
      {
        return;
      }
      if (viw == null)
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void onItemClick(final ItemConsulta viwItem)
  {
    try
    {
      if (viwItem == null)
      {
        return;
      }
      if (viwItem.getIntRegistroId() < 1)
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void onItemClickDetalhar(int intRegistroId)
  {
    try
    {
      this.setBooAbrindoActDetalhe(true);
      this.getTbl().abrirActDetalhe(this, intRegistroId);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void onItemClickRegistroSelecionar(int intRegistroId)
  {
    Intent itt;
    try
    {
      if (intRegistroId < 1)
      {
        return;
      }
      if (this.getTbl() == null)
      {
        return;
      }
      itt = new Intent();
      itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_REGISTRO_ID, intRegistroId);
      itt.putExtra(ActConsulta.STR_EXTRA_OUT_INT_TBL_OBJETO_ID, this.getTbl().getIntObjetoId());
      this.getTbl().setStrPesquisa(this.getTxtPesquisa().getText().toString());
      this.setResult(EnmResultado.REGISTRO_SELECIONADO.ordinal(), itt);
      this.finish();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void onItemLongClick(final ItemConsulta viwItem)
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem mni)
  {
    try
    {
      if (super.onOptionsItemSelected(mni))
      {
        return true;
      }
      if (STR_MENU_PESQUISAR.equals(mni.getTitle()))
      {
        return this.onOptionsItemSelectedPesquisar(mni);
      }
      return this.getTbl().processarMenu(this, mni);
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
    return false;
  }

  private boolean onOptionsItemSelectedPesquisar(MenuItem mni)
  {
    try
    {
      this.getPnlPesquisa().setVisibility(!mni.isChecked() ? View.VISIBLE : View.GONE);
      mni.setChecked(!mni.isChecked());
      if (this.getPnlPesquisa().getVisibility() != View.VISIBLE)
      {
        return true;
      }
      this.mostrarTeclado(this.getTxtPesquisa());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return true;
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu mnu)
  {
    super.onPrepareOptionsMenu(mnu);
    try
    {
      mnu.clear();
      this.onPrepareOptionsMenuPesquisar(mnu);
      this.onPrepareOptionsMenuAdicionar(mnu);
      this.onPrepareOptionsMenuTbl(mnu);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return true;
  }

  private void onPrepareOptionsMenuAdicionar(Menu mnu)
  {
    MenuItem mni;
    try
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
      if (!this.getTbl().getBooMenuAdicionar())
      {
        return;
      }
      mni = mnu.add(TabelaAndroid.STR_MENU_ADICIONAR);
      mni.setIcon(R.drawable.adicionar);
      mni.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void onPrepareOptionsMenuPesquisar(Menu mnu)
  {
    MenuItem mni;
    try
    {
      if (mnu == null)
      {
        return;
      }
      mni = mnu.add(STR_MENU_PESQUISAR);
      mni.setCheckable(true);
      mni.setIcon(R.drawable.pesquisar);
      mni.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void onPrepareOptionsMenuTbl(Menu mnu)
  {
    try
    {
      if (this.getTbl() == null)
      {
        return;
      }
      this.getTbl().montarMenu(mnu);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  protected void onResume()
  {
    super.onResume();
    try
    {
      this.setBooAbrindoActDetalhe(false);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void onTblAdicionar(OnChangeArg arg)
  {
    try
    {
      this.atualizarLista();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void onTblApagar(OnChangeArg arg)
  {
    try
    {
      if (arg.getIntRegistroId() < 1)
      {
        return;
      }
      this.atualizarLista();
      this.montarLayoutVazio();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void onTblAtualizar(OnChangeArg arg)
  {
    try
    {
      this.atualizarLista();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void onTextChanged(CharSequence strFiltro, int intStart, int intBefore, int intCount)
  {
    try
    {
      this.atualizarBtnPesquisarLimparVisibilidade(strFiltro);
      this.getAdpCadastro().getFilter().filter(strFiltro);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void recuperarUltimaPesquisa()
  {
    try
    {
      if (Utils.getBooStrVazia(this.getTbl().getStrPesquisa()))
      {
        this.getPnlPesquisa().setVisibility(View.GONE);
        return;
      }
      this.getTxtPesquisa().setText(this.getTbl().getStrPesquisa());
      Animar.getI().aparecerSlideDown(this.getPnlPesquisa());
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
  }

  private void setBooAbrindoActDetalhe(boolean booAbrindoActDetalhe)
  {
    _booAbrindoActDetalhe = booAbrindoActDetalhe;
  }

  @Override
  protected void setEventos()
  {
    super.setEventos();
    try
    {
      this.getBtnPesquisaLimpar().setOnClickListener(this);
      this.getTxtPesquisa().addTextChangedListener(this);
      this.getPnlLista().setLongClickable(true);
      this.recuperarUltimaPesquisa();
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(116), ex);
    }
    finally
    {
    }
  }

  private void setItmSelecionado(ItemConsulta itmSelecionado)
  {
    _itmSelecionado = itmSelecionado;
  }
}