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
import android.widget.FrameLayout;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.drawermenu.DrawerMenu;
import com.digosofter.digodroid.controle.painel.PainelMenuConteudo;
import com.digosofter.digodroid.erro.ErroAndroid;

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
    try
    {
      if (cls == null)
      {
        return;
      }
      this.startActivityForResult(new Intent(this, cls), 0);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);

    }
    finally
    {
    }
  }

  public void addEvtOnActivityResultListener(OnActivityResultListener evt)
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void addEvtOnDestruirListener(OnActivityDestruirListener evt)
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void addEvtOnRequestPermissionListener(OnRequestPermissionResultListener evt)
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  protected void addFragmento(int intViewGroupConteinerId, Fragment frg)
  {
    try
    {
      this.getFragmentManager().beginTransaction().add(intViewGroupConteinerId, frg).commit();
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(113), ex);
    }
    finally
    {
    }
  }

  private void dispararEvtOnActivityResultListener(final int intRequestCode, final int intResultCode, final Intent ittResult)
  {
    OnActivityResultArg arg;
    try
    {
      if (this.getLstEvtOnActivityResultListener().isEmpty())
      {
        return;
      }
      arg = new OnActivityResultArg();
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void dispararEvtOnDestruirListener()
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void dispararEvtOnRequestPermissionListener(final int intRequestCode, final String[] arrStrPermissions, final int[] intArrGrantResults)
  {
    OnRequestPermissionResultArg arg;
    try
    {
      if (this.getLstEvtOnRequestPermissionResultListener().isEmpty())
      {
        return;
      }
      arg = new OnRequestPermissionResultArg();
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void fecharMenu()
  {
    try
    {
      this.getViwDrawerMenu().closeDrawers();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  protected void finalizar()
  {
  }

  @Override
  public void finish()
  {
    super.finish();
    try
    {
      this.finalizar();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public boolean getBooVisivel()
  {
    return _booVisivel;
  }

  private int getIntDrawerMenuLayoutId()
  {
    try
    {
      if (AppAndroid.getI() == null)
      {
        return -1;
      }
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return AppAndroid.getI().getIntDrawerMenuLayoutId();
  }

  protected abstract int getIntLayoutId();

  private List<OnActivityDestruirListener> getLstEvtOnActivityDestruirListener()
  {
    try
    {
      if (_lstEvtOnActivityDestruirListener != null)
      {
        return _lstEvtOnActivityDestruirListener;
      }
      _lstEvtOnActivityDestruirListener = new ArrayList<>();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _lstEvtOnActivityDestruirListener;
  }

  private List<OnActivityResultListener> getLstEvtOnActivityResultListener()
  {
    try
    {
      if (_lstEvtOnActivityResultListener != null)
      {
        return _lstEvtOnActivityResultListener;
      }
      _lstEvtOnActivityResultListener = new ArrayList<>();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _lstEvtOnActivityResultListener;
  }

  private List<OnRequestPermissionResultListener> getLstEvtOnRequestPermissionResultListener()
  {
    try
    {
      if (_lstEvtOnRequestPermissionResultListener != null)
      {
        return _lstEvtOnRequestPermissionResultListener;
      }
      _lstEvtOnRequestPermissionResultListener = new ArrayList<>();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _lstEvtOnRequestPermissionResultListener;
  }

  protected <T extends View> T getView(int intViewId, Class<T> t)
  {
    return (T) this.findViewById(intViewId);
  }

  public DrawerMenu getViwDrawerMenu()
  {
    try
    {
      if (_viwDrawerMenu != null)
      {
        return _viwDrawerMenu;
      }
      _viwDrawerMenu = (DrawerMenu) this.findViewById(R.id.actMain_viwDrawerMenu);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _viwDrawerMenu;
  }

  protected ViewGroup getViwRoot()
  {
    try
    {
      if (_viwRoot != null)
      {
        return _viwRoot;
      }
      _viwRoot = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _viwRoot;
  }

  protected void inicializar()
  {
    try
    {
      this.inicializarActionBar();
      this.inicializarContentView();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarActionBar()
  {
    try
    {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
      {
        this.getActionBar().setHomeAsUpIndicator(R.drawable.voltar);
      }
      this.getActionBar().setDisplayHomeAsUpEnabled(true);
      this.getActionBar().setHomeButtonEnabled(true);
      this.getActionBar().setIcon(null);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarContentView()
  {
    DrawerMenu viwDrawerMenu;
    FrameLayout viwConteudo;
    PainelMenuConteudo pnlMenuConteudo;
    try
    {
      if (this.getIntLayoutId() < 1)
      {
        return;
      }
      if (this.getIntDrawerMenuLayoutId() < 1)
      {
        return;
      }
      viwDrawerMenu = (DrawerMenu) this.getLayoutInflater().inflate(R.layout.act_main, null);
      viwConteudo = (FrameLayout) viwDrawerMenu.findViewById(R.id.actMain_viwConteudo);
      pnlMenuConteudo = (PainelMenuConteudo) viwDrawerMenu.findViewById(R.id.actMain_pnlMenuConteudo);
      this.getLayoutInflater().inflate(this.getIntLayoutId(), viwConteudo, true);
      this.getLayoutInflater().inflate(this.getIntDrawerMenuLayoutId(), pnlMenuConteudo, true);
      AppAndroid.getI().dispararOnMenuCreateListener(this, viwDrawerMenu);
      this.setContentView(viwDrawerMenu);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void iniciar()
  {
    try
    {
      this.inicializar();
      this.montarLayout();
      this.setEventos();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }

  }

  protected void montarLayout()
  {
  }

  protected void mostrarTeclado(final View viw)
  {
    try
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
          //          ((InputMethodManager) ActMain.this.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0, 0);
          ((InputMethodManager) ActMain.this.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(viw, 0);
        }
      }, 100);
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
      this.onActivityResultFechar(ittResult);
      this.dispararEvtOnActivityResultListener(intRequestCode, intResultCode, ittResult);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void onActivityResultFechar(final Intent itt)
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    try
    {
      this.iniciar();
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
  }

  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    try
    {
      this.dispararEvtOnDestruirListener();
      this.getLstEvtOnActivityDestruirListener().clear();
      this.getLstEvtOnActivityResultListener().clear();
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
  public boolean onOptionsItemSelected(MenuItem mni)
  {
    try
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

  @Override
  public void onRequestPermissionsResult(final int intRequestCode, final String[] arrStrPermissions, final int[] intArrGrantResults)
  {
    super.onRequestPermissionsResult(intRequestCode, arrStrPermissions, intArrGrantResults);
    try
    {
      this.dispararEvtOnRequestPermissionListener(intRequestCode, arrStrPermissions, intArrGrantResults);
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
  protected void onStart()
  {
    super.onStart();
    try
    {
      this.setBooVisivel(true);
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
  }

  @Override
  protected void onStop()
  {
    super.onStop();
    try
    {
      this.setBooVisivel(false);
    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally
    {
    }
  }

  public void removerEvtOnActivityResultListener(OnActivityResultListener evt)
  {
    try
    {
      if (evt == null)
      {
        return;
      }
      this.getLstEvtOnActivityResultListener().remove(evt);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void removerEvtOnDestruirListener(OnActivityDestruirListener evt)
  {
    try
    {
      if (evt == null)
      {
        return;
      }
      this.getLstEvtOnActivityDestruirListener().remove(evt);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void removerEvtOnRequestPermissionListener(OnRequestPermissionResultListener evt)
  {
    try
    {
      if (evt == null)
      {
        return;
      }
      this.getLstEvtOnRequestPermissionResultListener().remove(evt);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void setBooVisivel(boolean booVisivel)
  {
    _booVisivel = booVisivel;
  }

  protected void setEventos()
  {
  }
}