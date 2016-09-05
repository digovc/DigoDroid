package com.digosofter.digodroid.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.drawermenu.DrawerMenu;
import com.digosofter.digodroid.controle.painel.PainelMenuConteudo;

import java.util.ArrayList;
import java.util.List;

public abstract class ActMain extends Activity
{
  public static final String STR_EXTRA_OUT_BOO_FECHAR = "boo_fechar";

  private boolean _booVisivel;
  private List<OnActivityDestruirListener> _lstEvtOnActivityDestruirListener;
  private List<OnActivityResultListener> _lstEvtOnActivityResultListener;
  private List<OnRequestPermissionResultListener> _lstEvtOnRequestPermissionResultListener;
  private DrawerMenu _viwDrawerMenu;
  private ViewGroup _viwRoot;

  public void abrirAct(Class<? extends ActMain> cls)
  {
    if (cls == null)
    {
      return;
    }

    this.startActivityForResult(new Intent(this, cls), 0);
  }

  protected void abrirMenuPrincipal()
  {
    if (this.getIntDrawerMenuLayoutId() < 1)
    {
      return;
    }

    if (this.getViwDrawerMenu() == null)
    {
      return;
    }

    this.getViwDrawerMenu().openDrawer(Gravity.LEFT);
  }

  public void addEvtOnActivityResultListener(OnActivityResultListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnActivityResultListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnActivityResultListener().add(evt);
  }

  public void addEvtOnDestruirListener(OnActivityDestruirListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnActivityDestruirListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnActivityDestruirListener().add(evt);
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

  protected void addFragmento(int intViewGroupConteinerId, Fragment frg)
  {
    this.getFragmentManager().beginTransaction().add(intViewGroupConteinerId, frg).commit();
  }

  private void dispararEvtOnActivityResultListener(final int intRequestCode, final int intResultCode, final Intent ittResult)
  {
    if (this.getLstEvtOnActivityResultListener().isEmpty())
    {
      return;
    }

    OnActivityResultArg arg = new OnActivityResultArg();

    arg.setIntRequestCode(intRequestCode);
    arg.setIntResultCode(intResultCode);
    arg.setIttResult(ittResult);

    for (OnActivityResultListener evt : this.getLstEvtOnActivityResultListener())
    {
      if (evt == null)
      {
        continue;
      }

      evt.onActivityResult(this, arg);
    }
  }

  private void dispararEvtOnDestruirListener()
  {
    if (this.getLstEvtOnActivityDestruirListener().isEmpty())
    {
      return;
    }

    for (OnActivityDestruirListener evt : this.getLstEvtOnActivityDestruirListener())
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

  public void fecharMenu()
  {
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

  public boolean getBooVisivel()
  {
    return _booVisivel;
  }

  private int getIntDrawerMenuLayoutId()
  {
    if (AppAndroid.getI() == null)
    {
      return -1;
    }

    return AppAndroid.getI().getIntDrawerMenuLayoutId();
  }

  protected abstract int getIntLayoutId();

  private List<OnActivityDestruirListener> getLstEvtOnActivityDestruirListener()
  {
    if (_lstEvtOnActivityDestruirListener != null)
    {
      return _lstEvtOnActivityDestruirListener;
    }

    _lstEvtOnActivityDestruirListener = new ArrayList<>();

    return _lstEvtOnActivityDestruirListener;
  }

  private List<OnActivityResultListener> getLstEvtOnActivityResultListener()
  {
    if (_lstEvtOnActivityResultListener != null)
    {
      return _lstEvtOnActivityResultListener;
    }

    _lstEvtOnActivityResultListener = new ArrayList<>();

    return _lstEvtOnActivityResultListener;
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

  protected <T extends View> T getView(int intViewId, Class<T> t)
  {
    return (T) this.findViewById(intViewId);
  }

  public DrawerMenu getViwDrawerMenu()
  {
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

  private void inicializarActionBar()
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

    if (this.getIntDrawerMenuLayoutId() < 1)
    {
      return;
    }

    this.setViwDrawerMenu((DrawerMenu) this.getLayoutInflater().inflate(R.layout.act_main, null));

    FrameLayout viwConteudo = (FrameLayout) this.getViwDrawerMenu().findViewById(R.id.actMain_viwConteudo);

    PainelMenuConteudo pnlMenuConteudo = (PainelMenuConteudo) this.getViwDrawerMenu().findViewById(R.id.actMain_pnlMenuConteudo);

    this.getLayoutInflater().inflate(this.getIntLayoutId(), viwConteudo, true);
    this.getLayoutInflater().inflate(this.getIntDrawerMenuLayoutId(), pnlMenuConteudo, true);

    AppAndroid.getI().dispararOnMenuCreateListener(this, this.getViwDrawerMenu());

    this.setContentView(this.getViwDrawerMenu());
  }

  private void iniciar()
  {
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

  @Override
  protected void onActivityResult(final int intRequestCode, final int intResultCode, final Intent ittResult)
  {
    super.onActivityResult(intRequestCode, intResultCode, ittResult);

    this.onActivityResultFechar(ittResult);

    this.dispararEvtOnActivityResultListener(intRequestCode, intResultCode, ittResult);
  }

  private void onActivityResultFechar(final Intent itt)
  {
    if (itt == null)
    {
      return;
    }

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

    this.dispararEvtOnDestruirListener();
    this.getLstEvtOnActivityDestruirListener().clear();
    this.getLstEvtOnActivityResultListener().clear();
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

  public void removerEvtOnActivityResultListener(OnActivityResultListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnActivityResultListener().remove(evt);
  }

  public void removerEvtOnDestruirListener(OnActivityDestruirListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnActivityDestruirListener().remove(evt);
  }

  public void removerEvtOnRequestPermissionListener(OnRequestPermissionResultListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnRequestPermissionResultListener().remove(evt);
  }

  private void setBooVisivel(boolean booVisivel)
  {
    _booVisivel = booVisivel;
  }

  protected void setEventos()
  {
  }

  private void setViwDrawerMenu(DrawerMenu viwDrawerMenu)
  {
    _viwDrawerMenu = viwDrawerMenu;
  }
}