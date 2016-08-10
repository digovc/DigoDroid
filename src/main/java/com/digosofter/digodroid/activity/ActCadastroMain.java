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
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Coluna;

import java.util.ArrayList;
import java.util.List;

public abstract class ActCadastroMain extends ActMain
{
  /**
   * Código do registro que indica o item que o usuário selecionou na lista desta tela.
   */
  public static final String STR_EXTRA_IN_INT_REGISTRO_ID = "int_registro_id";

  /**
   * Código do registro de referência.
   */
  public static final String STR_EXTRA_IN_INT_REGISTRO_REF_ID = "int_registro_ref_id";

  /**
   * Código do objeto da tabela que esta lista representa.
   */
  public static final String STR_EXTRA_IN_INT_TBL_OBJETO_ID = "int_tbl_objeto_id";

  protected static final String STR_MENU_SALVAR = "Salvar";
  private static final String STR_MENU_SALVAR_NOVO = "Salvar e novo";
  private CampoMain _cmpFocoInicial;
  private int _intRegistroId;
  private int _intRegistroRefId;
  private List<CampoMain> _lstCmp;
  private TabelaAndroid<?> _tbl;

  protected boolean getBooMostrarMenuSalvarNovo()
  {
    return false;
  }

  protected CampoMain getCmpFocoInicial()
  {
    try
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
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _cmpFocoInicial;
  }

  protected int getIntRegistroId()
  {
    try
    {
      if (_intRegistroId != 0)
      {
        return _intRegistroId;
      }
      _intRegistroId = this.getIntent().getIntExtra(STR_EXTRA_IN_INT_REGISTRO_ID, -1);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _intRegistroId;
  }

  protected int getIntRegistroRefId()
  {
    try
    {
      if (_intRegistroRefId != 0)
      {
        return _intRegistroRefId;
      }
      _intRegistroRefId = this.getIntent().getIntExtra(STR_EXTRA_IN_INT_REGISTRO_REF_ID, -1);
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

  private List<CampoMain> getLstCmp()
  {
    try
    {
      if (_lstCmp != null)
      {
        return _lstCmp;
      }
      _lstCmp = this.inicializarLstCmp();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _lstCmp;
  }

  private TabelaAndroid<?> getTbl()
  {
    try
    {
      if (_tbl != null)
      {
        return _tbl;
      }
      _tbl = AppAndroid.getI().getTbl(this.getIntent().getIntExtra(STR_EXTRA_IN_INT_TBL_OBJETO_ID, 0));
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _tbl;
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();
    try
    {
      this.inicializarTbl();
      this.inicializarTitulo();
      this.inicializarCampos();
      this.inicializarFoco();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }

  }

  private void inicializarTbl()
  {
    if (this.getTbl() == null) {

      return;
    }

    if (this.getIntRegistroId() < 1) {

      this.getTbl().limparColunas();
      return;
    }

    this.getTbl().recuperar(this.getIntRegistroId());
  }

  private void inicializarCampos()
  {
    try
    {
      for (CampoMain cmp : this.getLstCmp())
      {
        this.inicializarCampos(cmp);
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

  private void inicializarCampos(final CampoMain cmp)
  {
    Coluna cln;
    try
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
      cln = this.getTbl().getCln(cmp.getStrClnNomeSql());
      if (cln == null)
      {
        return;
      }
      cmp.setCln((ColunaAndroid) cln);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarFoco()
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private List<CampoMain> inicializarLstCmp()
  {
    List<CampoMain> lstCmpResultado;
    try
    {
      lstCmpResultado = new ArrayList<>();
      this.inicializarLstCmp(lstCmpResultado, this.getViwRoot());
      return lstCmpResultado;
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return null;
  }

  private void inicializarLstCmp(final List<CampoMain> lstCmp, final View viw)
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarTitulo()
  {
    try
    {
      if (this.getTbl() == null)
      {
        this.setTitle("<desconhecido>");
        return;
      }
      this.setTitle(this.getTbl().getStrNomeExibicao());
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
  protected void onActivityResult(final int intRequestCode, final int intResultCode, final Intent ittResult)
  {
    super.onActivityResult(intRequestCode, intResultCode, ittResult);
    try
    {
      if (intResultCode != ActConsulta.EnmResultado.REGISTRO_SELECIONADO.ordinal())
      {
        return;
      }
      for (CampoMain cmp : this.getLstCmp())
      {
        this.onActivityResult(cmp, ittResult);
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

  private void onActivityResult(final CampoMain cmp, final Intent itt)
  {
    try
    {
      if (cmp == null)
      {
        return;
      }
      if (!CampoConsulta.class.isAssignableFrom(cmp.getClass()))
      {
        return;
      }
      if (itt == null)
      {
        return;
      }
      ((CampoConsulta) cmp).onActivityResult(itt);
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
  public boolean onCreateOptionsMenu(final Menu mnu)
  {
    super.onCreateOptionsMenu(mnu);
    try
    {
      this.onCreateOptionsMenuSalvarNovo(mnu);
      this.onCreateOptionsMenuSalvar(mnu);
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

  protected void onCreateOptionsMenuSalvar(final Menu mnu)
  {
    MenuItem mniSalvar;
    try
    {
      mniSalvar = mnu.add(STR_MENU_SALVAR);
      mniSalvar.setIcon(R.drawable.salvar);
      mniSalvar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void onCreateOptionsMenuSalvarNovo(final Menu mnu)
  {
    MenuItem mniSalvar;
    try
    {
      if (!this.getBooMostrarMenuSalvarNovo())
      {
        return;
      }
      mniSalvar = mnu.add(STR_MENU_SALVAR_NOVO);
      mniSalvar.setIcon(R.drawable.continuar);
      mniSalvar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
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
  public boolean onOptionsItemSelected(final MenuItem mni)
  {
    try
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
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return false;
  }

  /**
   * Salva o registro atual da tela.
   *
   * @param booNovo Indica se logo após o salvamento será aberto um novo cadastro.
   */
  protected void salvar(boolean booNovo)
  {
    Intent itt;
    try
    {
      if (this.getTbl() == null)
      {
        return;
      }
      if (!this.validarDados())
      {
        return;
      }
      this.getTbl().salvar(false);
      this.finish();
      if (!booNovo)
      {
        return;
      }
      this.getTbl().limparColunas();
      itt = new Intent(this, this.getClass());
      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, this.getIntRegistroRefId());
      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_TBL_OBJETO_ID, this.getTbl().getIntObjetoId());
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

  private boolean validarDados()
  {
    try
    {
      if (!this.getTbl().validarDados())
      {
        return false;
      }
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
}