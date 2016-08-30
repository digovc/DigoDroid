package com.digosofter.digodroid.controle.drawermenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActMain;

public final class DrawerMenu extends DrawerLayout implements DrawerLayout.DrawerListener
{
  private MenuItem _mniClicado;

  public DrawerMenu(Context cnt)
  {
    super(cnt);

    this.iniciar(null);
  }

  public DrawerMenu(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);

    this.iniciar(atr);
  }

  public DrawerMenu(Context cnt, AttributeSet atr, int intDefStyle)
  {
    super(cnt, atr, intDefStyle);

    this.iniciar(atr);
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

  private MenuItem getMniClicado()
  {
    return _mniClicado;
  }

  /**
   * Chamado de dentro do construtor para fazer qualquer inicialização que seja necessária neste controle. Neste momento do ciclo os parâmetros de
   * layout do controle ainda não foram carregados, portanto a propriedade {@link android.view.ViewGroup.LayoutParams} não está inicializada.
   */
  protected void inicializar()
  {
  }

  /**
   * Chamado de dentro do construtor para fazer qualquer inicialização que seja necessária neste controle.
   *
   * @param ats Conjunto de atributos que foram declarados no XML de layout que contém este controle.
   */
  protected void inicializar(AttributeSet ats)
  {
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
  }

  @Override
  public void onDrawerClosed(final View viwDrawer)
  {
    try
    {
      if (this.getMniClicado() == null)
      {
        return;
      }

      AppAndroid.getI().dispararOnMenuItemClickListener(this.getMniClicado());

      this.fecharActivity();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    finally
    {
      this.setMniClicado(null);
    }
  }

  @Override
  public void onDrawerOpened(final View drawerView)
  {
  }

  @Override
  public void onDrawerSlide(final View drawerView, final float slideOffset)
  {
  }

  @Override
  public void onDrawerStateChanged(final int newState)
  {
  }

  /**
   * Responsável por configurar os eventos deste controle e de seus filhos.
   */
  protected void setEventos()
  {
    this.setDrawerListener(this);
  }

  void setMniClicado(MenuItem mniClicado)
  {
    _mniClicado = mniClicado;
  }
}