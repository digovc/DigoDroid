package com.digosofter.digodroid.effect;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.erro.Erro;

public class ZoomOutPagerTransformer implements PageTransformer {

  private static float MIN_ALPHA = 0.5f;
  private static float MIN_SCALE = 0.85f;

  @Override
  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  public void transformPage(View objView, float dblPosition) {

    int intPageWidth;
    int intPageHeight;

    try {

      if (android.os.Build.VERSION.SDK_INT >= 11) {

        intPageWidth = objView.getWidth();
        intPageHeight = objView.getHeight();

        if (dblPosition < -1) {
          objView.setAlpha(0);
        }
        else if (dblPosition <= 1) {

          float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(dblPosition));
          float vertMargin = intPageHeight * (1 - scaleFactor) / 2;
          float horzMargin = intPageWidth * (1 - scaleFactor) / 2;

          if (dblPosition < 0) {
            objView.setTranslationX(horzMargin - vertMargin / 2);
          }
          else {
            objView.setTranslationX(-horzMargin + vertMargin / 2);
          }

          objView.setScaleX(scaleFactor);
          objView.setScaleY(scaleFactor);
          objView.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        }
        else {
          objView.setAlpha(0);
        }
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }
  }

}