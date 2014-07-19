package com.digosofter.digodroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.erro.Erro;

public abstract class ActMain extends ActionBarActivity {

  private boolean _booVisivel;

  public boolean getBooVisivel() {
    return _booVisivel;
  }

  private void setBooVisivel(boolean booVisivel) {
    _booVisivel = booVisivel;
  }

  @Override
  protected void onStart() {

    super.onStart();

    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setBooVisivel(true);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  @Override
  protected void onStop() {

    super.onStop();

    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setBooVisivel(false);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  protected void addFragmento(int intIdPnlContainer, Fragment objFragmento) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.getSupportFragmentManager().beginTransaction().add(intIdPnlContainer, objFragmento)
          .commit();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(113), ex);

    } finally {
    }
  }

  public Button getButton(int intId) {
    return (Button) this.getView(intId);
  }

  public CheckBox getCheckBox(int intId) {
    return (CheckBox) this.getView(intId);
  }

  public EditText getEditText(int intId) {
    return (EditText) this.getView(intId);
  }

  public ImageView getImageView(int intId) {
    return (ImageView) this.getView(intId);
  }

  protected abstract int getIntLayoutId();

  public LinearLayout getLinearLayout(int intId) {
    return (LinearLayout) this.getView(intId);
  }

  public RadioButton getRadioButton(int intId) {
    return (RadioButton) this.getView(intId);
  }

  public RadioGroup getRadioGroup(int intId) {
    return (RadioGroup) this.getView(intId);
  }

  public TextView getTextView(int intId) {
    return (TextView) this.getView(intId);
  }

  public VideoView getVideoView(int intId) {
    return (VideoView) this.getView(intId);
  }

  protected View getView(int intId) {
    // VARI�VEIS

    View viewResultado = null;

    // FIM VARI�VEIS
    try {
      // A��ES

      viewResultado = this.findViewById(intId);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return viewResultado;
  }

  protected void montarLayout() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setContentView(this.getIntLayoutId());

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.montarLayout();
      this.setEventos();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  protected void setEventos() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES
      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }
}
