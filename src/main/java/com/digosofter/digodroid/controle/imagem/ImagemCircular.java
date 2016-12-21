package com.digosofter.digodroid.controle.imagem;

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

  public Bitmap getCroppedBitmap(Bitmap bmp, int radius)
  {
    Bitmap sbmp;

    if (bmp.getWidth() != radius || bmp.getHeight() != radius)
    {
      float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
      float factor = smallest / radius;
      sbmp = Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() / factor), (int) (bmp.getHeight() / factor), false);
    }
    else
    {
      sbmp = bmp;
    }

    Bitmap output = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(output);

    final String color = "#BAB399";
    final Paint paint = new Paint();
    final Rect rect = new Rect(0, 0, radius, radius);

    paint.setAntiAlias(true);
    paint.setFilterBitmap(true);
    paint.setDither(true);
    canvas.drawARGB(0, 0, 0, 0);
    paint.setColor(Color.parseColor(color));
    canvas.drawCircle(radius / 2 + 0.7f, radius / 2 + 0.7f, radius / 2 + 0.1f, paint);
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(sbmp, rect, rect, paint);

    return output;
  }

  @Override
  public void onDraw(Canvas cnv)
  {
    Drawable drawable = getDrawable();

    if (drawable == null)
    {
      return;
    }

    if (getWidth() == 0 || getHeight() == 0)
    {
      return;
    }
    Bitmap b = ((BitmapDrawable) drawable).getBitmap();
    Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

    int w = getWidth();
    @SuppressWarnings("unused") int h = getHeight();

    Bitmap roundBitmap = getCroppedBitmap(bitmap, w);
    cnv.drawBitmap(roundBitmap, 0, 0, null);
  }
}