package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.database.ViewAndroid;

public class CampoConsulta extends CampoBotaoMain
{
  public CampoConsulta(Context cnt)
  {
    super(cnt);
  }

  public CampoConsulta(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public CampoConsulta(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  public void abrirConsulta()
  {
    if (this.getBooSomenteLeitura())
    {
      return;
    }

    if (this.getCln() == null)
    {
      return;
    }

    if (this.getCln().getClnRef() == null)
    {
      return;
    }

    Intent itt = new Intent();

    itt.putExtra(ActConsulta.STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL, true);

    ((TblAndroidMain) this.getCln().getClnRef().getTbl()).getViwPrincipal().abrirConsulta((ActMain) this.getContext(), itt);
  }

  public void onActivityResult(final Intent itt)
  {
    if (itt == null)
    {
      return;
    }

    if (this.getCln() == null)
    {
      return;
    }

    if (this.getCln().getClnRef() == null)
    {
      return;
    }

    if (this.getCln().getClnRef().getTbl() == null)
    {
      return;
    }

    if (AppAndroid.getI() == null)
    {
      return;
    }

    if (AppAndroid.getI().getDbe() == null)
    {
      return;
    }

    TblAndroidMain tbl = (TblAndroidMain) AppAndroid.getI().getDbe().getTbl(itt.getIntExtra(ActConsulta.STR_EXTRA_OUT_INT_TBL_OBJETO_ID, 0));

    ViewAndroid viw = null;

    if (ViewAndroid.class.isAssignableFrom(tbl.getClass()))
    {
      viw = ((ViewAndroid) tbl);
      tbl = viw.getTbl();
    }

    if (!this.getCln().getClnRef().getTbl().equals(tbl) && !this.getCln().getClnRef().getTbl().equals(viw))
    {
      return;
    }

    int intRegistroId = itt.getIntExtra(ActConsulta.STR_EXTRA_OUT_INT_REGISTRO_ID, 0);

    if (intRegistroId < 1)
    {
      return;
    }

    this.setIntValor(intRegistroId);
  }

  @Override
  protected void processarBtnClick()
  {
    this.abrirConsulta();
  }

  @Override
  public void setIntValor(final int intValor)
  {
    super.setIntValor(intValor);

    this.setIntValorNome(intValor);
  }

  private void setIntValorNome(final int intId)
  {
    this.getBtn().setText(null);

    if (this.getCln() == null)
    {
      return;
    }

    if (this.getCln().getClnRef() == null)
    {
      return;
    }

    if (this.getCln().getClnRef().getTbl() == null)
    {
      return;
    }

    if (intId < 1)
    {
      return;
    }

    try
    {
      String strNome = ((TblAndroidMain) this.getCln().getClnRef().getTbl()).getViwPrincipal().recuperar(intId).getClnNome().getStrValor();

      this.getBtn().setText(strNome);
    }
    finally
    {
      ((TblAndroidMain) this.getCln().getClnRef().getTbl()).getViwPrincipal().liberarThread();
    }
  }
}