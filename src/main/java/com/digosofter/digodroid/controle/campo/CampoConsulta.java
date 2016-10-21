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

    ((TabelaAndroid) this.getCln().getClnRef().getTbl()).getViwPrincipal().abrirConsulta((ActMain) this.getContext(), itt);
  }

  @Override
  protected void atualizarStrValor(final String strValor)
  {
    super.atualizarStrValor(strValor);

    this.atualizarStrValorNome();
  }

  private void atualizarStrValorNome()
  {
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

    String strNome = ((TabelaAndroid) this.getCln().getClnRef().getTbl()).getViwPrincipal().recuperar(this.getIntValor()).getClnNome().getStrValor();

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
    this.getBtn().setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();

    this.addView(this.getBtn());
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

    TabelaAndroid tbl = (TabelaAndroid) AppAndroid.getI().getDbe().getTbl(itt.getIntExtra(ActConsulta.STR_EXTRA_OUT_INT_TBL_OBJETO_ID, 0));

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
  public void onClick(final View viw)
  {
    if (this.getBtn().equals(viw))
    {
      this.abrirConsulta();
      return;
    }
  }

  @Override
  public void receberFoco()
  {
    if (this.getIntValor() > 0)
    {
      this.getBtn().requestFocus();
    }
    else
    {
      this.getBtn().performClick();
    }
  }

  @Override
  protected void setBooSomenteLeitura(final boolean booSomenteLeitura)
  {
    super.setBooSomenteLeitura(booSomenteLeitura);

    this.getBtn().setEnabled(!booSomenteLeitura);
  }

  @Override
  public void setEventos()
  {
    super.setEventos();

    this.setOnClickListener(this);
    this.getBtn().setOnClickListener(this);
  }
}