package com.digosofter.digodroid.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.drawermenu.DrawerMenuMain;

import java.util.ArrayList;
import java.util.List;

public abstract class ActMain extends Activity
{
  public static final String STR_EXTRA_OUT_BOO_FECHAR = "boo_fechar";
  public static final String STR_EXTRA_OUT_BOO_FECHAR_TUDO = "boo_fechar_tudo";

  private boolean _booVisivel;
  private List<OnDestroyListener> _lstEvtOnDestroyListener;
  private List<OnRequestPermissionResultListener> _lstEvtOnRequestPermissionResultListener;
  private List<OnResultListener> _lstEvtOnResultListener;
  private DrawerMenuMain _viwDrawerMenu;
  private ViewGroup _viwRoot;

  public void abrirAct(Class<? extends ActMain> cls)
  {
    if (cls == null)
    {
      return;
    }

    this.startActivityForResult(new Intent(this, cls), 0);
  }

  protected void abrirMenu()
  {
    if (this.getViwDrawerMenu() == null)
    {
      return;
    }

    this.getViwDrawerMenu().abrirMenu();
  }

  public void addEvtOnDestroyListener(OnDestroyListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnDestroyListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnDestroyListener().add(evt);
  }

  public void addEvtOnRequestPermissionListener(OnRequestPermissionResultListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnRequestPermissionResultListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnRequestPermissionResultListener().add(evt);
  }

  public void addEvtOnResultListener(OnResultListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnResultListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnResultListener().add(evt);
  }

  protected void addFragmento(int intViewGroupConteinerId, Fragment frg)
  {
    this.getFragmentManager().beginTransaction().add(intViewGroupConteinerId, frg).commit();
  }

  private void dispararEvtOnDestroyListener()
  {
    if (this.getLstEvtOnDestroyListener().isEmpty())
    {
      return;
    }

    for (OnDestroyListener evt : this.getLstEvtOnDestroyListener())
    {
      if (evt == null)
      {
        continue;
      }

      evt.onActivityDestruir(this);
    }
  }

  private void dispararEvtOnRequestPermissionListener(final int intRequestCode, final String[] arrStrPermissions, final int[] intArrGrantResults)
  {
    if (this.getLstEvtOnRequestPermissionResultListener().isEmpty())
    {
      return;
    }

    OnRequestPermissionResultArg arg = new OnRequestPermissionResultArg();

    arg.setIntRequestCode(intRequestCode);
    arg.setArrStrPermissions(arrStrPermissions);
    arg.setIntArrGrantResults(intArrGrantResults);

    for (OnRequestPermissionResultListener evt : this.getLstEvtOnRequestPermissionResultListener())
    {
      if (evt == null)
      {
        continue;
      }

      evt.onRequestPermissionResult(this, arg);
    }
  }

  private void dispararEvtOnResultListener(final int intRequestCode, final int intResultCode, final Intent ittResult)
  {
    if (this.getLstEvtOnResultListener().isEmpty())
    {
      return;
    }

    OnActivityResultArg arg = new OnActivityResultArg();

    arg.setIntRequestCode(intRequestCode);
    arg.setIntResultCode(intResultCode);
    arg.setIttResult(ittResult);

    for (OnResultListener evt : this.getLstEvtOnResultListener())
    {
      if (evt == null)
      {
        continue;
      }

      evt.onActivityResult(this, arg);
    }
  }

  public void fecharMenu()
  {
    if (this.getViwDrawerMenu() == null)
    {
      return;
    }

    this.getViwDrawerMenu().closeDrawers();
  }

  protected void finalizar()
  {
  }

  @Override
  public void finish()
  {
    super.finish();

    this.finalizar();
  }

  protected boolean getBooMostrarMenu()
  {
    return false;
  }

  public boolean getBooVisivel()
  {
    return _booVisivel;
  }

  public abstract int getIntLayoutId();

  private List<OnDestroyListener> getLstEvtOnDestroyListener()
  {
    if (_lstEvtOnDestroyListener != null)
    {
      return _lstEvtOnDestroyListener;
    }

    _lstEvtOnDestroyListener = new ArrayList<>();

    return _lstEvtOnDestroyListener;
  }

  private List<OnRequestPermissionResultListener> getLstEvtOnRequestPermissionResultListener()
  {
    if (_lstEvtOnRequestPermissionResultListener != null)
    {
      return _lstEvtOnRequestPermissionResultListener;
    }

    _lstEvtOnRequestPermissionResultListener = new ArrayList<>();

    return _lstEvtOnRequestPermissionResultListener;
  }

  private List<OnResultListener> getLstEvtOnResultListener()
  {
    if (_lstEvtOnResultListener != null)
    {
      return _lstEvtOnResultListener;
    }

    _lstEvtOnResultListener = new ArrayList<>();

    return _lstEvtOnResultListener;
  }

  protected <T extends View> T getView(int intViewId)
  {
    return (T) this.findViewById(intViewId);
  }

  private DrawerMenuMain getViwDrawerMenu()
  {
    if (_viwDrawerMenu != null)
    {
      return _viwDrawerMenu;
    }

    if (AppAndroid.getI() == null)
    {
      return null;
    }

    if (AppAndroid.getI().getClsViwDrawerMenu() == null)
    {
      return null;
    }

    try
    {
      _viwDrawerMenu = (DrawerMenuMain) AppAndroid.getI().getClsViwDrawerMenu().getConstructor(ActMain.class).newInstance(this);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }

    return _viwDrawerMenu;
  }

  protected ViewGroup getViwRoot()
  {
    if (_viwRoot != null)
    {
      return _viwRoot;
    }

    _viwRoot = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

    return _viwRoot;
  }

  protected void inicializar()
  {
    this.inicializarActionBar();
    this.inicializarContentView();
  }

  protected void inicializarActionBar()
  {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
    {
      this.getActionBar().setHomeAsUpIndicator(R.drawable.voltar);
    }

    this.getActionBar().setDisplayHomeAsUpEnabled(true);
    this.getActionBar().setHomeButtonEnabled(true);
    this.getActionBar().setIcon(null);
  }

  private void inicializarContentView()
  {
    if (this.getIntLayoutId() < 1)
    {
      return;
    }

    if (!this.getBooMostrarMenu())
    {
      this.setContentView(this.getIntLayoutId());
      return;
    }

    if (this.getViwDrawerMenu() == null)
    {
      this.setContentView(this.getIntLayoutId());
      return;
    }

    this.setContentView(this.getViwDrawerMenu());

    this.getViwDrawerMenu().inicializar(this);
  }

  private void iniciar()
  {
    if (!this.validarAbertura())
    {
      this.finish();
      return;
    }

    this.inicializar();
    this.montarLayout();
    this.setEventos();
  }

  protected void montarLayout()
  {
  }

  protected void mostrarTeclado(final View viw)
  {
    if (viw == null)
    {
      return;
    }

    viw.postDelayed(new Runnable()
    {
      @Override
      public void run()
      {
        viw.requestFocus();
        ((InputMethodManager) ActMain.this.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(viw, 0);
      }
    }, 100);
  }

  public void notificar(final String strNotificacao)
  {
    if (AppAndroid.getI() == null)
    {
      return;
    }

    AppAndroid.getI().notificar(strNotificacao);
  }

  @Override
  protected void onActivityResult(final int intRequestCode, final int intResultCode, final Intent ittResult)
  {
    super.onActivityResult(intRequestCode, intResultCode, ittResult);

    if (ittResult == null)
    {
      return;
    }

    this.onActivityResultFechar(ittResult);
    this.onActivityResultFecharTudo(ittResult);

    this.dispararEvtOnResultListener(intRequestCode, intResultCode, ittResult);
  }

  private void onActivityResultFechar(final Intent itt)
  {
    if (!itt.getBooleanExtra(STR_EXTRA_OUT_BOO_FECHAR, false))
    {
      return;
    }

    if (this.equals(AppAndroid.getI().getActPrincipal()))
    {
      return;
    }

    this.setResult(0, new Intent().putExtra(STR_EXTRA_OUT_BOO_FECHAR, true));

    this.finish();
  }

  private void onActivityResultFecharTudo(final Intent itt)
  {
    if (!itt.getBooleanExtra(STR_EXTRA_OUT_BOO_FECHAR_TUDO, false))
    {
      return;
    }

    this.setResult(0, new Intent().putExtra(STR_EXTRA_OUT_BOO_FECHAR_TUDO, true));

    this.finish();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    this.iniciar();
  }

  @Override
  protected void onDestroy()
  {
    super.onDestroy();

    this.dispararEvtOnDestroyListener();
    this.getLstEvtOnDestroyListener().clear();
    this.getLstEvtOnResultListener().clear();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem mni)
  {
    if (super.onOptionsItemSelected(mni))
    {
      return true;
    }

    switch (mni.getItemId())
    {
      case android.R.id.home:
        this.onBackPressed();
        return true;
    }

    return false;
  }

  @Override
  public void onRequestPermissionsResult(final int intRequestCode, final String[] arrStrPermissions, final int[] intArrGrantResults)
  {
    super.onRequestPermissionsResult(intRequestCode, arrStrPermissions, intArrGrantResults);

    this.dispararEvtOnRequestPermissionListener(intRequestCode, arrStrPermissions, intArrGrantResults);
  }

  @Override
  protected void onStart()
  {
    super.onStart();

    this.setBooVisivel(true);
  }

  @Override
  protected void onStop()
  {
    super.onStop();

    this.setBooVisivel(false);
  }

  public void removerEvtOnDestroyListener(OnDestroyListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnDestroyListener().remove(evt);
  }

  public void removerEvtOnRequestPermissionListener(OnRequestPermissionResultListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnRequestPermissionResultListener().remove(evt);
  }

  public void removerEvtOnResultListener(OnResultListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnResultListener().remove(evt);
  }

  private void setBooVisivel(boolean booVisivel)
  {
    _booVisivel = booVisivel;
  }

  protected void setEventos()
  {
  }

  public boolean validarAbertura()
  {
    return true;
  }
}