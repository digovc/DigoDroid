package com.digosofter.digodroid.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.componente.campo.CampoConsulta;
import com.digosofter.digodroid.componente.campo.CampoMain;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.log.LogErro;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.ColunaMain;

import java.util.ArrayList;
import java.util.List;

public abstract class ActCadastroMain extends ActMain
{
  /**
   * Código do registro que indica o consulta_item que o usuário selecionou na lista desta tela.
   */
  public static final String STR_EXTRA_IN_INT_REGISTRO_ID = "int_registro_id";

  /**
   * Código do registro de referência.
   */
  public static final String STR_EXTRA_IN_INT_REGISTRO_REF_ID = "int_registro_ref_id";

  /**
   * Código do objeto da tabela.
   */
  public static final String STR_EXTRA_IN_INT_TBL_OBJETO_ID = "int_tbl_objeto_id";

  /**
   * Código do objeto da tabela pai.
   */
  public static final String STR_EXTRA_IN_INT_TBL_PAI_OBJETO_ID = "int_tbl_pai_objeto_id";
  /**
   * Código do último registro salvo.
   */
  protected static final String STR_EXTRA_IN_INT_REGISTRO_ANTERIOR_ID = "int_registro_anterior_id";
  protected static final String STR_MENU_SALVAR = "Salvar";
  private static final String STR_MENU_SALVAR_NOVO = "Salvar e novo";

  private CampoMain _cmpFocoInicial;
  private int _intRegistroAnteriorId;
  private int _intRegistroId;
  private int _intRegistroRefId;
  private List<CampoMain> _lstCmp;
  private TblAndroidMain _tbl;
  private TblAndroidMain _tblPai;

  private void abrirNovo()
  {
    if (this.getTbl() == null)
    {
      return;
    }

    Intent itt = new Intent(this, this.getClass());

    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, this.getIntRegistroRefId());
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_TBL_OBJETO_ID, (this.getTbl() != null) ? this.getTbl().getIntObjetoId() : -1);
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_TBL_PAI_OBJETO_ID, (this.getTblPai() != null) ? this.getTblPai().getIntObjetoId() : -1);
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_ANTERIOR_ID, this.getTbl().getClnIntId().getIntValor());

    try
    {
      this.getTbl().limparDados();

      this.startActivity(itt);
    }
    finally
    {
      this.getTbl().liberarThread();
    }
  }

  protected void carregarDados()
  {
    if (this.getTbl() == null)
    {
      return;
    }

    try
    {
      this.getTbl().limparDados();

      this.getTbl().getClnIntUsuarioAlteracaoId().setIntValor(this.getIntUsuarioId());

      if (this.getIntRegistroId() < 1)
      {
        this.getTbl().getClnIntUsuarioCadastroId().setIntValor(this.getIntUsuarioId());
      }

      for (ColunaMain cln : this.getTbl().getLstCln())
      {
        this.carregarDados((ColunaAndroid) cln);
      }
    }
    finally
    {
      this.getTbl().liberarThread();
    }
  }

  private void carregarDados(final ColunaAndroid cln)
  {
    if (cln == null)
    {
      return;
    }

    if (this.carregarDadosRef(cln))
    {
      return;
    }

    if (cln.getCmp() == null)
    {
      return;
    }

    cln.getCmp().carregarValorCln();
  }

  private boolean carregarDadosRef(final ColunaAndroid cln)
  {
    if (this.getIntRegistroRefId() < 1)
    {
      return false;
    }

    if (this.getTblPai() == null)
    {
      return false;
    }

    if (cln.getClnRef() == null)
    {
      return false;
    }

    if (cln.getClnRef().getTbl() == null)
    {
      return false;
    }

    if (!cln.getClnRef().getTbl().equals(this.getTblPai()))
    {
      return false;
    }

    cln.setIntValor(this.getIntRegistroRefId());

    return true;
  }

  protected boolean getBooMostrarMenuSalvarNovo()
  {
    return false;
  }

  protected CampoMain getCmpFocoInicial()
  {
    if (_cmpFocoInicial != null)
    {
      return _cmpFocoInicial;
    }

    if (this.getLstCmp() == null)
    {
      return null;
    }

    if (this.getLstCmp().isEmpty())
    {
      return null;
    }

    _cmpFocoInicial = this.getLstCmp().get(0);

    return _cmpFocoInicial;
  }

  protected int getIntRegistroAnteriorId()
  {
    if (_intRegistroAnteriorId != 0)
    {
      return _intRegistroAnteriorId;
    }

    _intRegistroAnteriorId = this.getIntent().getIntExtra(STR_EXTRA_IN_INT_REGISTRO_ANTERIOR_ID, 0);

    return _intRegistroAnteriorId;
  }

  protected int getIntRegistroId()
  {
    if (_intRegistroId != 0)
    {
      return _intRegistroId;
    }

    _intRegistroId = this.getIntent().getIntExtra(STR_EXTRA_IN_INT_REGISTRO_ID, -1);

    return _intRegistroId;
  }

  protected int getIntRegistroRefId()
  {
    if (_intRegistroRefId != 0)
    {
      return _intRegistroRefId;
    }

    _intRegistroRefId = this.getIntent().getIntExtra(STR_EXTRA_IN_INT_REGISTRO_REF_ID, -1);

    return _intRegistroRefId;
  }

  protected abstract int getIntUsuarioId();

  private List<CampoMain> getLstCmp()
  {
    if (_lstCmp != null)
    {
      return _lstCmp;
    }

    _lstCmp = new ArrayList<>();

    this.inicializarLstCmp(_lstCmp, this.getViwRoot());

    return _lstCmp;
  }

  private TblAndroidMain<?> getTbl()
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

    _tbl = (TblAndroidMain) AppAndroid.getI().getDbe().getTbl(this.getIntent().getIntExtra(STR_EXTRA_IN_INT_TBL_OBJETO_ID, 0));

    if (_tbl == null)
    {
      return null;
    }

    _tbl = (TblAndroidMain) _tbl.getTblPrincipal();

    return _tbl;
  }

  private TblAndroidMain getTblPai()
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

    _tblPai = (TblAndroidMain) AppAndroid.getI().getDbe().getTbl(this.getIntent().getIntExtra(STR_EXTRA_IN_INT_TBL_PAI_OBJETO_ID, -1));

    if (_tblPai == null)
    {
      return null;
    }

    _tblPai = (TblAndroidMain) _tblPai.getTblPrincipal();

    return _tblPai;
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.inicializarTbl();
    this.inicializarTitulo();
    this.inicializarLstCmp();
    this.inicializarFoco();
  }

  protected void inicializarFoco()
  {
    if (this.getIntRegistroId() > 0)
    {
      return;
    }

    if (this.getCmpFocoInicial() == null)
    {
      return;
    }

    this.getCmpFocoInicial().receberFoco();
  }

  private void inicializarLstCmp()
  {
    for (CampoMain cmp : this.getLstCmp())
    {
      this.inicializarLstCmp(cmp);
    }
  }

  private void inicializarLstCmp(final CampoMain cmp)
  {
    if (cmp == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(cmp.getStrClnNomeSql()))
    {
      return;
    }

    if (this.getTbl() == null)
    {
      return;
    }

    ColunaMain cln = this.getTbl().getCln(cmp.getStrClnNomeSql());

    if (cln == null)
    {
      return;
    }

    cmp.setCln((ColunaAndroid) cln);
  }

  private void inicializarLstCmp(final List<CampoMain> lstCmp, final View viw)
  {
    if (viw == null)
    {
      return;
    }

    if (CampoMain.class.isAssignableFrom(viw.getClass()))
    {
      lstCmp.add((CampoMain) viw);
      return;
    }

    if (!ViewGroup.class.isAssignableFrom(viw.getClass()))
    {
      return;
    }

    for (int i = 0; i < ((ViewGroup) viw).getChildCount(); i++)
    {
      this.inicializarLstCmp(lstCmp, ((ViewGroup) viw).getChildAt(i));
    }
  }

  private void inicializarTbl()
  {
    if (this.getTbl() == null)
    {
      return;
    }

    try
    {
      this.getTbl().limparDados();

      if (this.getIntRegistroId() < 1)
      {
        return;
      }

      this.getTbl().recuperar(this.getIntRegistroId());
    }
    finally
    {
      this.getTbl().liberarThread();
    }
  }

  private void inicializarTitulo()
  {
    if (this.getTbl() == null)
    {
      this.setTitle("<desconhecido>");
      return;
    }

    this.setTitle(this.getTbl().getStrNomeExibicao());
  }

  @Override
  protected void onActivityResult(final int intRequestCode, final int intResultCode, final Intent ittResult)
  {
    super.onActivityResult(intRequestCode, intResultCode, ittResult);

    if (ittResult == null)
    {
      return;
    }

    if (intResultCode != ActConsulta.EnmResultado.REGISTRO_SELECIONADO.ordinal())
    {
      return;
    }

    for (CampoMain cmp : this.getLstCmp())
    {
      this.onActivityResult(cmp, ittResult);
    }
  }

  protected void onActivityResult(final CampoMain cmp, final Intent itt)
  {
    if (cmp == null)
    {
      return;
    }

    if (!CampoConsulta.class.isAssignableFrom(cmp.getClass()))
    {
      return;
    }

    if (cmp.getCln().getIntObjetoId() != itt.getIntExtra(ActConsulta.STR_EXTRA_OUT_INT_COLUNA_OBJETO_ID, -1))
    {
      return;
    }

    ((CampoConsulta) cmp).onActivityResult(itt);

    this.selecionarProximo(cmp);
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu mnu)
  {
    super.onCreateOptionsMenu(mnu);

    this.onCreateOptionsMenuSalvarNovo(mnu);
    this.onCreateOptionsMenuSalvar(mnu);

    return true;
  }

  protected void onCreateOptionsMenuSalvar(final Menu mnu)
  {
    MenuItem mniSalvar = mnu.add(STR_MENU_SALVAR);

    mniSalvar.setIcon(R.drawable.salvar);
    mniSalvar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
  }

  private void onCreateOptionsMenuSalvarNovo(final Menu mnu)
  {
    if (!this.getBooMostrarMenuSalvarNovo())
    {
      return;
    }

    MenuItem mniSalvarNovo = mnu.add(STR_MENU_SALVAR_NOVO);

    mniSalvarNovo.setIcon(R.drawable.continuar);
    mniSalvarNovo.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem mni)
  {
    if (super.onOptionsItemSelected(mni))
    {
      return true;
    }

    switch (mni.getTitle().toString())
    {
      case STR_MENU_SALVAR:
        this.salvar(false);
        return true;

      case STR_MENU_SALVAR_NOVO:
        this.salvar(true);
        return true;
    }

    return false;
  }

  protected boolean salvar(final boolean booAbrirNovo)
  {
    if (this.getTbl() == null)
    {
      return false;
    }

    this.carregarDados();

    if (!this.validarDados())
    {
      return false;
    }

    try
    {
      this.getTbl().salvar();
      this.finish();

      return true;
    }
    finally
    {
      this.getTbl().liberarThread();

      if (booAbrirNovo)
      {
        this.abrirNovo();
      }

    }
  }

  private void selecionarProximo(final CampoMain cmp)
  {
    if (cmp == null)
    {
      return;
    }

    int i = this.getViwRoot().indexOfChild(cmp);

    if (i < 0)
    {
      return;
    }

    View viw = this.getViwRoot().getChildAt(i + 1);

    if (viw == null)
    {
      return;
    }

    if (!CampoMain.class.isAssignableFrom(viw.getClass()))
    {
      return;
    }

    ((CampoMain) viw).receberFoco();
  }

  @Override
  public boolean validarAbertura()
  {
    if (!super.validarAbertura())
    {
      return false;
    }

    if (!this.validarCodigoDisponivel())
    {
      return false;
    }

    return true;
  }

  private boolean validarCodigoDisponivel()
  {
    if (this.getTbl() == null)
    {
      return false;
    }

    boolean booResultado = this.getTbl().getBooCodigoDisponivel();

    if (!booResultado)
    {
      LogErro.getI().addLog(this, new Exception(String.format("Este aparelho não possui reserva de código para adicionar novos registros na tabela \"%s\". Favor sincronizar os dados para prosseguir.", this.getTbl().getSqlNome())));
    }

    return booResultado;
  }

  private boolean validarDados()
  {
    if (this.getTbl() == null)
    {
      return false;
    }

    return this.getTbl().validarDados(this);
  }
}