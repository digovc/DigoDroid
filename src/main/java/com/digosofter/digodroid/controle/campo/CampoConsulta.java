package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.controle.botao.BotaoGeral;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.database.ViewAndroid;

public class CampoConsulta extends CampoMain implements View.OnClickListener
{
  private BotaoGeral _btn;

  public CampoConsulta(Context context)
  {
    super(context);
  }

  public CampoConsulta(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public CampoConsulta(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void atualizarStrValor()
  {
    super.atualizarStrValor();

    this.atualizarStrValorNome();
  }

  private void atualizarStrValorNome()
  {
    String strNome;

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
    if (this.getIntValor() < 1)
    {
      return;
    }
    strNome = ((TabelaAndroid) this.getCln().getClnRef().getTbl()).getViwPrincipal().recuperar(this.getIntValor()).getClnNome().getStrValor();
    this.getBtn().setText(strNome);
  }

  private BotaoGeral getBtn()
  {
    if (_btn != null)
    {
      return _btn;
    }
    _btn = new BotaoGeral(this.getContext());

    return _btn;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.getBtn().setFocusable(true);
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();

    this.addView(this.getBtn());
  }

  public void onActivityResult(final Intent itt)
  {
    int intRegistroId;
    TabelaAndroid tbl;
    ViewAndroid viw;

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
    tbl = AppAndroid.getI().getTbl(itt.getIntExtra(ActConsulta.STR_EXTRA_OUT_INT_TBL_OBJETO_ID, 0));
    viw = null;
    if (ViewAndroid.class.isAssignableFrom(tbl.getClass()))
    {
      viw = ((ViewAndroid) tbl);
      tbl = viw.getTbl();
    }
    if (!this.getCln().getClnRef().getTbl().equals(tbl) && !this.getCln().getClnRef().getTbl().equals(viw))
    {
      return;
    }
    intRegistroId = itt.getIntExtra(ActConsulta.STR_EXTRA_OUT_INT_REGISTRO_ID, 0);
    if (intRegistroId < 1)
    {
      return;
    }
    this.setIntValor(intRegistroId);
  }

  @Override
  public void onClick(final View v)
  {
    Intent itt;

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
    itt = new Intent();
    itt.putExtra(ActConsulta.STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL, true);
    ((TabelaAndroid) this.getCln().getClnRef().getTbl()).abrirActConsulta((ActMain) this.getContext(), itt);
  }

  @Override
  public void receberFoco()
  {
    this.getBtn().performClick();
  }

  @Override
  public void setEventos()
  {
    super.setEventos();

    this.setOnClickListener(this);
    this.getBtn().setOnClickListener(this);
  }
}