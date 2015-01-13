package com.digosofter.digodroid.dialog;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;

public abstract class DialogMain extends DialogFragment {

  private Builder _bld;
  private LayoutInflater _lif;
  private View _viw;

  private Builder getBld() {

    try {

      if (_bld != null) {

        return _bld;
      }

      _bld = new Builder(this.getActivity());

      _bld.setView(this.getViw());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _bld;
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

  private LayoutInflater getLif() {

    try {

      if (_lif != null) {

        return _lif;
      }

      _lif = this.getActivity().getLayoutInflater();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lif;
  }

  protected LinearLayout getLinearLayout(int intId) {

    return (LinearLayout) this.getView(intId);
  }

  protected ListView getListView(int intId) {

    return (ListView) this.getView(intId);
  }

  public ProgressBar getProgressBar(int intId) {

    return (ProgressBar) this.getView(intId);
  }

  protected RadioButton getRadioButton(int intId) {

    return (RadioButton) this.getView(intId);
  }

  protected RadioGroup getRadioGroup(int intId) {

    return (RadioGroup) this.getView(intId);
  }

  protected TextView getTextView(int intId) {

    return (TextView) this.getView(intId);
  }

  protected VideoView getVideoView(int intId) {

    return (VideoView) this.getView(intId);
  }

  protected View getView(int intId) {

    View viwResultado = null;

    try {

      viwResultado = this.getViw().findViewById(intId);
    }
    catch (Exception ex) {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return viwResultado;
  }

  private View getViw() {

    try {

      if (_viw != null) {

        return _viw;
      }

      _viw = this.getLif().inflate(this.getIntLayoutId(), null);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _viw;
  }

  protected void montarLayout() {

  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {

    try {

      this.montarLayout();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return this.getBld().create();
  }
}
