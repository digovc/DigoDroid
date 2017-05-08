package com.digosofter.digodroid.componente.imagem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class ImagemCircular extends ImagemGeral
{
  public ImagemCircular(Context cnt)
  {
    super(cnt);
  }

  public ImagemCircular(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public ImagemCircular(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  public Bitmap getBmpCircular(Bitmap bmp, int intRadius)
  {
    Bitmap bmp2;

    if (bmp.getWidth() != intRadius || bmp.getHeight() != intRadius)
    {
      float fltSmallest = Math.min(bmp.getWidth(), bmp.getHeight());

      float fltFactor = (fltSmallest / intRadius);

      bmp2 = Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() / fltFactor), (int) (bmp.getHeight() / fltFactor), false);
    }
    else
    {
      bmp2 = bmp;
    }

    Bitmap bmpResultado = Bitmap.createBitmap(intRadius, intRadius, Bitmap.Config.ARGB_8888);

    Canvas cnv = new Canvas(bmpResultado);

    final String cor = "#bab399";
    final Paint pnt = new Paint();
    final Rect rct = new Rect(0, 0, intRadius, intRadius);

    pnt.setAntiAlias(true);
    pnt.setFilterBitmap(true);
    pnt.setDither(true);
    cnv.drawARGB(0, 0, 0, 0);
    pnt.setColor(Color.parseColor(cor));
    cnv.drawCircle(intRadius / 2 + 0.7f, intRadius / 2 + 0.7f, intRadius / 2 + 0.1f, pnt);
    pnt.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

    cnv.drawBitmap(bmp2, rct, rct, pnt);

    return bmpResultado;
  }

  @Override
  public void onDraw(Canvas cnv)
  {
    Drawable objDrawable = this.getDrawable();

    if (objDrawable == null)
    {
      return;
    }

    if ((this.getWidth() == 0) || (this.getHeight() == 0))
    {
      return;
    }

    Bitmap bmp = ((BitmapDrawable) objDrawable).getBitmap();

    Bitmap bmpCopia = bmp.copy(Bitmap.Config.ARGB_8888, true);

    Bitmap bmpCircular = this.getBmpCircular(bmpCopia, this.getWidth());

    cnv.drawBitmap(bmpCircular, 0, 0, null);
  }
}