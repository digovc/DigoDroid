package com.digosofter.digodroid.activity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.componente.item.ItemDetalhe;
import com.digosofter.digodroid.componente.label.LabelGeral;
import com.digosofter.digodroid.componente.painel.PainelGrupo;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.Grupo;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.log.LogErro;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.ColunaMain;

public class ActDetalhe extends ActMain
{
  public static final String STR_EXTRA_IN_INT_REGISTRO_ID = "int_registro_id";
  public static final String STR_EXTRA_IN_INT_TBL_OBJETO_ID = "int_tbl_objeto_id";

  private Grupo _grpGeral;
  private int _intRegistroId;
  private LabelGeral _lblIntRegistroId;
  private LabelGeral _lblStrRegistroNome;
  private LinearLayout _pnlCampos;
  private TblAndroidMain<?> _tbl;

  private Grupo getGrpGeral()
  {
    if (_grpGeral != null)
    {
      return _grpGeral;
    }

    _grpGeral = new Grupo("geral");

    return _grpGeral;
  }

  @Override
  public int getIntLayoutId()
  {
    return R.layout.act_detalhe;
  }

  private int getIntRegistroId()
  {
    if (_intRegistroId > 0)
    {
      return _intRegistroId;
    }

    _intRegistroId = this.getIntent().getIntExtra(ActDetalhe.STR_EXTRA_IN_INT_REGISTRO_ID, 0);

    return _intRegistroId;
  }

  private LabelGeral getLblIntRegistroId()
  {
    if (_lblIntRegistroId != null)
    {
      return _lblIntRegistroId;
    }

    _lblIntRegistroId = this.getView(R.id.actDetalhe_lblIntRegistroId);

    return _lblIntRegistroId;
  }

  private LabelGeral getLblStrRegistroNome()
  {
    if (_lblStrRegistroNome != null)
    {
      return _lblStrRegistroNome;
    }

    _lblStrRegistroNome = this.getView(R.id.actDetalhe_lblStrRegistroNome);

    return _lblStrRegistroNome;
  }

  private LinearLayout getPnlCampos()
  {
    if (_pnlCampos != null)
    {
      return _pnlCampos;
    }

    _pnlCampos = this.getView(R.id.actDetalhe_pnlCampos);

    return _pnlCampos;
  }

  private PainelGrupo getPnlGrupo(Grupo grp)
  {
    if (grp == null)
    {
      return null;
    }

    for (int i = 0; i < this.getPnlCampos().getChildCount(); i++)
    {
      if (this.getPnlCampos().getChildAt(i) == null)
      {
        continue;
      }

      if (!this.getPnlCampos().getChildAt(i).getClass().equals(PainelGrupo.class))
      {
        continue;
      }

      if (Utils.getBooStrVazia(((PainelGrupo) this.getPnlCampos().getChildAt(i)).getStrTitulo()))
      {
        continue;
      }

      if (!((PainelGrupo) this.getPnlCampos().getChildAt(i)).getStrTitulo().equals(grp.getStrNomeExibicao()))
      {
        continue;
      }

      return (PainelGrupo) this.getPnlCampos().getChildAt(i);
    }

    PainelGrupo pnlGrupoNovo = new PainelGrupo(this);

    pnlGrupoNovo.setBooPermitirFechar(false);
    pnlGrupoNovo.setStrTitulo(grp.getStrNomeExibicao());

    this.getPnlCampos().addView(pnlGrupoNovo);

    return pnlGrupoNovo;
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

    _tbl = (TblAndroidMain<?>) AppAndroid.getI().getDbe().getTbl(this.getIntent().getIntExtra(STR_EXTRA_IN_INT_TBL_OBJETO_ID, -1));

    return _tbl;
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    if (!this.inicializarValidar())
    {
      AppAndroid.getI().notificar("Registro inválido.");
      this.finish();
      return;
    }

    this.setTitle(this.getTbl().getStrNomeExibicao());

    try
    {
      this.getTbl().recuperar(this.getIntRegistroId());

      this.getLblIntRegistroId().setText(String.valueOf(this.getIntRegistroId()));
      this.getLblStrRegistroNome().setText(this.getTbl().getClnNome().getStrValorExibicao());
    }
    finally
    {
      this.getTbl().liberarThread();
    }
  }

  private boolean inicializarValidar()
  {
    if (this.getTbl() == null)
    {
      return false;
    }

    if (this.getIntRegistroId() < 1)
    {
      return false;
    }

    return true;
  }

  @Override
  protected void montarLayout()
  {
    super.montarLayout();

    this.montarLayoutPnlGrupoGeral();
    this.montarLayoutItem();
  }

  private void montarLayoutItem()
  {
    if (this.getTbl() == null)
    {
      return;
    }

    for (ColunaMain cln : this.getTbl().getLstCln())
    {
      this.montarLayoutItem((ColunaAndroid) cln);
    }
  }

  private void montarLayoutItem(ColunaAndroid cln)
  {
    if (cln == null)
    {
      return;
    }

    if (cln.equals(this.getTbl().getClnIntId()))
    {
      return;
    }

    if (cln.equals(cln.getTbl().getClnNome()))
    {
      return;
    }

    if (!cln.getBooVisivelDetalhe())
    {
      return;
    }

    if (cln.getObjDbGrupo() == null)
    {
      cln.setObjDbGrupo(this.getGrpGeral());
    }

    ItemDetalhe itmDetalhe = new ItemDetalhe(this, cln);

    itmDetalhe.carregarDados();

    this.montarLayoutItem(itmDetalhe);
  }

  private void montarLayoutItem(ItemDetalhe itmDetalhe)
  {
    if (itmDetalhe == null)
    {
      return;
    }

    PainelGrupo pnlGrupo = this.getPnlGrupo(itmDetalhe.getCln().getObjDbGrupo());

    if (pnlGrupo == null)
    {
      return;
    }

    pnlGrupo.addView(itmDetalhe);
  }

  private void montarLayoutPnlGrupoGeral()
  {
    PainelGrupo pnlGrupoGeral = new PainelGrupo(this);

    pnlGrupoGeral.setStrTitulo("Geral");

    this.getPnlCampos().addView(pnlGrupoGeral);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu mnu)
  {
    if (!super.onCreateOptionsMenu(mnu))
    {
      return false;
    }

    if (this.getTbl() == null)
    {
      return false;
    }

    this.getTbl().montarMenuItem(mnu, this.getIntRegistroId(), false);

    return true;
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

      if (this.getTbl() == null)
      {
        return false;
      }

      return this.getTbl().processarMenuItem(this, mni, this.getIntRegistroId());
    }
    catch (Exception ex)
    {
      LogErro.getI().addLog(this, ex);
    }

    return false;
  }
}