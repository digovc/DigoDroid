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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.erro.Erro;

public abstract class ActMain extends ActionBarActivity {

  private boolean _booVisivel;

  protected void addFragmento(int intPnlId, Fragment frg) {

    try {
      this.getSupportFragmentManager().beginTransaction().add(intPnlId, frg).commit();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(113), ex);
    }
    finally {
    }
  }

  public boolean getBooVisivel() {

    return _booVisivel;
  }

  public Button getButton(int intId) {

    return (Button) this.getView(intId);
  }

  public ListView getListView(int intId) {

    return (ListView) this.getView(intId);
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

    View viwResultado = null;
    try {
      viwResultado = this.findViewById(intId);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return viwResultado;
  }

  protected void montarLayout() {

    try {
      this.setContentView(this.getIntLayoutId());
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    try {
      this.montarLayout();
      this.setEventos();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  @Override
  protected void onStart() {

    super.onStart();
    try {
      this.setBooVisivel(true);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
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
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private void setBooVisivel(boolean booVisivel) {

    _booVisivel = booVisivel;
  }

  protected void setEventos() {

    try {
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }
}
