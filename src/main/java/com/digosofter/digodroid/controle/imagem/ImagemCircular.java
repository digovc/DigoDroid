package com.digosofter.digodroid.controle.imagem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

public class ImagemCircular extends ImagemGeral
{
  public ImagemCircular(Context context)
  {
    super(context);
  }

  public ImagemCircular(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public ImagemCircular(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  public Bitmap getBmpClip()
  {
    if (this.getDrawable() == null)
    {
      return null;
    }

    Bitmap bmp = ((BitmapDrawable) this.getDrawable()).getBitmap();
    Bitmap bmpOutup = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);

    Canvas cnv = new Canvas(bmpOutup);

    final int intColor = 0xff424242;
    final Paint objPaint = new Paint();
    final Rect rct = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());

    objPaint.setAntiAlias(true);
    cnv.drawARGB(0, 0, 0, 0);
    cnv.drawCircle(bmp.getWidth() / 2, bmp.getHeight() / 2, bmp.getWidth() / 2, objPaint);

    objPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    cnv.drawBitmap(bmp, rct, rct, objPaint);

    return bmpOutup;
  }

  @Override
  public void onDraw(Canvas cnv)
  {
    Paint objPaint = new Paint();

    cnv.drawBitmap(this.getBmpClip(), 30, 20, objPaint);
  }

}