package com.digosofter.digodroid;

import java.util.Random;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public abstract class UtilsAndroid extends Utils {

  public static TextWatcher addMascara(final EditText ediTxt, final String strMascara) {

    return new TextWatcher() {

      @Override
      public void afterTextChanged(Editable s) {

      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

        // TODO: Refazer.
      }
    };
  }

  public static int getIntCorAleatoria() {

    int intResultado = 0;
    Random objR;
    try {
      objR = new Random();
      intResultado = Color.argb(255, objR.nextInt(256), objR.nextInt(256), objR.nextInt(256));
    }
    catch (Exception ex) {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(110), ex);
    }
    finally {
    }
    return intResultado;
  }
}