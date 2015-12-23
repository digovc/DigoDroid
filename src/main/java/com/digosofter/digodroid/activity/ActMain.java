package com.digosofter.digodroid.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.drawermenu.DrawerMenu;
import com.digosofter.digodroid.controle.painel.PainelMenuConteudo;
import com.digosofter.digodroid.erro.ErroAndroid;

public abstract class ActMain extends Activity {

  public static final String STR_EXTRA_OUT_BOO_FECHAR = "boo_fechar";
  private boolean _booVisivel;
  private DrawerMenu _viwDrawerMenu;

  public void abrirAct(Class<? extends ActMain> cls) {

    try {

      if (cls == null) {

        return;
      }

      this.startActivity(new Intent(this, cls));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);

    }
    finally {
    }
  }

  protected void addFragmento(int intViewGroupConteinerId, Fragment frg) {

    try {

      this.getFragmentManager().beginTransaction().add(intViewGroupConteinerId, frg).commit();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(113), ex);
    }
    finally {
    }
  }

  public void fecharMenu() {

    try {

      this.getViwDrawerMenu().closeDrawers();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void finalizar() {

  }

  public boolean getBooVisivel() {

    return _booVisivel;
  }

  private int getIntDrawerMenuLayoutId() {

    return AppAndroid.getI().getIntDrawerMenuLayoutId();
  }

  protected abstract int getIntLayoutId();

  protected <T extends View> T getView(int intViewId, Class<T> t) {

    return (T) this.findViewById(intViewId);
  }

  public DrawerMenu getViwDrawerMenu() {

    try {

      if (_viwDrawerMenu != null) {

        return _viwDrawerMenu;
      }

      _viwDrawerMenu = (DrawerMenu) this.findViewById(R.id.actMain_viwDrawerMenu);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _viwDrawerMenu;
  }

  protected void inicializar() {

    try {

      this.inicializarActionBar();
      this.inicializarContentView();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void inicializarActionBar() {

    try {

      this.getActionBar().setDisplayHomeAsUpEnabled(true);
      this.getActionBar().setHomeButtonEnabled(true);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void inicializarContentView() {

    DrawerMenu viwDrawerMenu;
    FrameLayout viwConteudo;
    PainelMenuConteudo pnlMenuConteudo;

    try {

      if (this.getIntLayoutId() < 1) {

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
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void inicializarLocal() {

    try {

      this.inicializar();
      this.montarLayout();
      this.setEventos();
      this.finalizar();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

  }

  protected void montarLayout() {

  }

  protected void mostrarTeclado(View viw) {

    try {

      if (viw == null) {

        return;
      }

      viw.requestFocus();

      ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0, 0);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void onActivityResult(final int intRequestCode, final int intResultCode, final Intent itt) {

    super.onActivityResult(intRequestCode, intResultCode, itt);

    try {

      this.onActivityResultFechar(itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void onActivityResultFechar(final Intent itt) {

    try {

      if (itt == null) {

        return;
      }

      if (!itt.getBooleanExtra(STR_EXTRA_OUT_BOO_FECHAR, false)) {

        return;
      }

      if (this.equals(AppAndroid.getI().getActPrincipal())) {

        return;
      }

      this.setResult(0, new Intent().putExtra(STR_EXTRA_OUT_BOO_FECHAR, true));
      this.finish();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    try {

      this.inicializarLocal();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  @Override
  protected void onDestroy() {

    super.onDestroy();

    try {

      this.finalizar();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem itm) {

    try {

      if (super.onOptionsItemSelected(itm)) {

        return true;
      }

      switch (itm.getItemId()) {

        case android.R.id.home:
          this.onBackPressed();
          return true;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  @Override
  protected void onStart() {

    super.onStart();

    try {

      this.setBooVisivel(true);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  @Override
  protected void onStop() {

    super.onStop();

    try {

      this.setBooVisivel(false);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private void setBooVisivel(boolean booVisivel) {

    _booVisivel = booVisivel;
  }

  protected void setEventos() {

  }
}