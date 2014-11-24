package com.digosofter.digodroid;

import java.util.Random;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.digosofter.digodroid.erro.AndroidErro;
import com.digosofter.digojava.Utils;

public abstract class UtilsAndroid extends Utils {

  public static int getIntCorAleatoria() {

    int intResultado = 0;
    Random objR;
    try {
      objR = new Random();
      intResultado = Color.argb(255, objR.nextInt(256), objR.nextInt(256), objR.nextInt(256));
    }
    catch (Exception ex) {
      new AndroidErro(AppAndroid.getI().getStrTextoPadrao(110), ex);
    }
    finally {
    }
    return intResultado;
  }

  public static TextWatcher inserirMascara(final String strMascara, final EditText ediTxt) {

    return new TextWatcher() {

      boolean isUpdating;
      String old = "";

      @Override
      public void afterTextChanged(Editable s) {

      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

        String str = UtilsAndroid.removerMascara(s.toString());
        String mascara = "";
        if (isUpdating) {
          old = str;
          isUpdating = false;
          return;
        }
        int i = 0;
        for (char m : strMascara.toCharArray()) {
          if (m != '#' && str.length() > old.length()) {
            mascara += m;
            continue;
          }
          try {
            mascara += str.charAt(i);
          }
          catch (Exception e) {
            break;
          }
          i++;
        }
        isUpdating = true;
        ediTxt.setText(mascara);
        ediTxt.setSelection(mascara.length());
      }
    };
  }
}
