package com.digosofter.digodroid;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;

import com.digosofter.digojava.Utils;

public abstract class UtilsAndroid extends Utils
{
  /**
   * Converte um valor em "density pixels" para "pixels".
   *
   * @param intDp Quantidade que se espera converter para "pixels".
   * @param cnt Contexto do controle.
   * @return Quantidade em "pixels".
   */
  public static int dpToPx(int intDp, Context cnt)
  {
    if (intDp < 1)
    {
      return 0;
    }

    if (cnt == null)
    {
      return 0;
    }

    DisplayMetrics objDisplayMetrics = cnt.getResources().getDisplayMetrics();

    return Math.round(intDp * (objDisplayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
  }

  public static int getIntCorAleatoria()
  {
    return Color.argb(255, Utils.getIntNumeroAleatorio(256), Utils.getIntNumeroAleatorio(256), Utils.getIntNumeroAleatorio(256));
  }

  /**
   * Converte um valor em "pixels" para "density pixels".
   *
   * @param intPx Quantidade que se espera converter para "density pixels".
   * @param cnt Contexto do controle.
   * @return Quantidade em "density pixels".
   */
  public static int pxToDp(int intPx, Context cnt)
  {
    if (intPx < 1)
    {
      return 0;
    }

    if (cnt == null)
    {
      return 0;
    }

    DisplayMetrics objDisplayMetrics = cnt.getResources().getDisplayMetrics();

    return Math.round(intPx / (objDisplayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
  }
}