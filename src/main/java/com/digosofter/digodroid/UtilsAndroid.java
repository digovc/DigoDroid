package com.digosofter.digodroid;

import android.content.Context;
import android.graphics.Color;
import android.util.Base64;
import android.util.DisplayMetrics;

import com.digosofter.digojava.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public abstract class UtilsAndroid extends Utils
{
  public static String descomprimir(String zipText) throws IOException
  {
    byte[] compressed = Base64.decode(zipText, Base64.DEFAULT);

    if (compressed.length > 4)
    {
      GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressed, 4, compressed.length - 4));

      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      for (int value = 0; value != -1; )
      {
        value = gzipInputStream.read();
        if (value != -1)
        {
          baos.write(value);
        }
      }

      gzipInputStream.close();
      baos.close();
      String sReturn = new String(baos.toByteArray(), "UTF-8");

      return sReturn;
    }

    return STR_VAZIA;
  }

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