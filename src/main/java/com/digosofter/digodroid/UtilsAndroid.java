package com.digosofter.digodroid;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

import java.util.Random;

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
    DisplayMetrics objDisplayMetrics;
    try
    {
      objDisplayMetrics = cnt.getResources().getDisplayMetrics();
      return Math.round(intDp * (objDisplayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);

    }
    finally
    {
    }
    return 0;
  }

  public static int getIntCorAleatoria()
  {
    Random objRandom;
    try
    {
      objRandom = new Random();
      return Color.argb(255, objRandom.nextInt(256), objRandom.nextInt(256), objRandom.nextInt(256));

    }
    catch (Exception ex)
    {
      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(110), ex);
    }
    finally
    {
    }
    return 0;
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
    DisplayMetrics objDisplayMetrics;
    try
    {
      objDisplayMetrics = cnt.getResources().getDisplayMetrics();
      return Math.round(intPx / (objDisplayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);

    }
    finally
    {
    }
    return 0;
  }
}