package com.digosofter.digodroid.controle.drawermenu;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.controle.painel.PainelGeral;

public abstract class DrawerMenuMain extends DrawerLayout
{
  private ActMain _act;
  private PainelGeral _pnlMenu;
  private FrameLayout _viwFmlConteudo;

  public DrawerMenuMain(ActMain act)
  {
    super(act);

    this.iniciar(null);
  }

  public DrawerMenuMain(ActMain act, AttributeSet atr)
  {
    super(act, atr);

    this.iniciar(atr);
  }

  public DrawerMenuMain(ActMain act, AttributeSet atr, int intDefStyle)
  {
    super(act, atr, intDefStyle);

    this.iniciar(atr);
  }

  public void abrirMenu()
  {
    this.openDrawer(this.getPnlMenu());
  }

  private void fecharActivity()
  {
    if (this.getContext().equals(AppAndroid.getI().getActPrincipal()))
    {
      return;
    }

    ((Activity) this.getContext()).setResult(0, new Intent().putExtra(ActMain.STR_EXTRA_OUT_BOO_FECHAR, true));
    ((Activity) this.getContext()).finish();
  }

  protected void finalizar()
  {
  }

  protected ActMain getAct()
  {
    if (_act != null)
    {
      return _act;
    }

    _act = (ActMain) this.getContext();

    return _act;
  }

  protected abstract int getIntLayoutId();

  private PainelGeral getPnlMenu()
  {
    if (_pnlMenu != null)
    {
      return _pnlMenu;
    }

    _pnlMenu = new PainelGeral(this.getContext());

    return _pnlMenu;
  }

  private FrameLayout getViwFmlConteudo()
  {
    if (_viwFmlConteudo != null)
    {
      return _viwFmlConteudo;
    }

    _viwFmlConteudo = new FrameLayout(this.getContext());

    return _viwFmlConteudo;
  }

  /**
   * Chamado de dentro do construtor para fazer qualquer inicialização que seja necessária neste controle. Neste momento do ciclo os parâmetros de
   * layout do controle ainda não foram carregados, portanto a propriedade {@link ViewGroup.LayoutParams} não está inicializada.
   */
  protected void inicializar()
  {
    this.inicializarPnlMenu();
    this.inicializarViwFmlConteudo();
  }

  /**
   * Chamado de dentro do construtor para fazer qualquer inicialização que seja necessária neste controle.
   *
   * @param ats Conjunto de atributos que foram declarados no XML de layout que contém este controle.
   */
  protected void inicializar(AttributeSet ats)
  {
  }

  private void inicializarPnlMenu()
  {
    DrawerLayout.LayoutParams objLayoutParams = new DrawerLayout.LayoutParams(UtilsAndroid.dpToPx(300, this.getContext()), ViewGroup.LayoutParams.MATCH_PARENT);

    objLayoutParams.gravity = Gravity.START;

    this.getPnlMenu().setLayoutParams(objLayoutParams);
    this.getPnlMenu().setClickable(true);
  }

  private void inicializarViwFmlConteudo()
  {
    this.getViwFmlConteudo().setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
  }

  private void iniciar(AttributeSet ats)
  {
    this.inicializar(ats);
    this.inicializar();
    this.montarLayout();
    this.setEventos();
    this.finalizar();
  }

  protected void montarLayout()
  {
    this.addView(this.getViwFmlConteudo());
    this.addView(this.getPnlMenu());

    this.getAct().getLayoutInflater().inflate(this.getAct().getIntLayoutId(), this.getViwFmlConteudo(), true);
    this.getAct().getLayoutInflater().inflate(this.getIntLayoutId(), this.getPnlMenu(), true);
  }

  protected void setEventos()
  {
  }
}