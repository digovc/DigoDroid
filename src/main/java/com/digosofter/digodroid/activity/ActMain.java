package com.digosofter.digodroid.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.drawermenu.DrawerMenu;
import com.digosofter.digodroid.controle.painel.PainelDrawerMenu;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;

public abstract class ActMain extends Activity {

  private boolean _booVisivel;

  public void abrirAct(Class<? extends ActMain> cls) {

    try {

      if (cls == null) {

        return;
      }

      this.startActivity(new Intent(this, cls));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);

    } finally {
    }
  }

  protected void addFragmento(int intViewGroupConteinerId, Fragment frg) {

    try {

      this.getFragmentManager().beginTransaction().add(intViewGroupConteinerId, frg).commit();

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(113), ex);
    } finally {
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

  protected void inicializar() {

    try {

      this.inicializarActionBar();
      this.inicializarContentView();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void inicializarActionBar() {

    try {

      this.getActionBar().setBackgroundDrawable(new ColorDrawable(TemaDefault.getI().getCorTema()));
      this.getActionBar().setDisplayHomeAsUpEnabled(true);
      this.getActionBar().setHomeButtonEnabled(true);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void inicializarContentView() {

    DrawerMenu objDrawerMenu;
    FrameLayout objFrameLayout;
    PainelDrawerMenu pnlDrawerMenu;

    try {

      if (this.getIntLayoutId() < 1) {

        return;
      }

      objDrawerMenu = (DrawerMenu) this.getLayoutInflater().inflate(R.layout.act_main, null);

      objFrameLayout = (FrameLayout) objDrawerMenu.findViewById(R.id.actMain_viwConteudo);
      pnlDrawerMenu = (PainelDrawerMenu) objDrawerMenu.findViewById(R.id.actMain_pnlDrawerMenu);

      this.getLayoutInflater().inflate(this.getIntLayoutId(), objFrameLayout, true);
      this.getLayoutInflater().inflate(this.getIntDrawerMenuLayoutId(), pnlDrawerMenu, true);

      this.setContentView(objDrawerMenu);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void inicializarLocal() {

    try {

      this.inicializar();
      this.montarLayout();
      this.setEventos();
      this.finalizar();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
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

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    try {

      this.inicializarLocal();

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }
  }

  @Override
  protected void onDestroy() {

    super.onDestroy();

    try {

      this.finalizar();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
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
    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return false;
  }

  @Override
  protected void onStart() {

    super.onStart();

    try {

      this.setBooVisivel(true);

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }
  }

  @Override
  protected void onStop() {

    super.onStop();

    try {

      this.setBooVisivel(false);

    } catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    } finally {
    }
  }

  private void setBooVisivel(boolean booVisivel) {

    _booVisivel = booVisivel;
  }

  protected void setEventos() {

  }
}