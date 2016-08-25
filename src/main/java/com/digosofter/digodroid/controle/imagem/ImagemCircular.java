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

  public Bitmap getclip()
  {
    if (this.getDrawable() == null)
    {
      return null;
    }

    Bitmap bitmap = ((BitmapDrawable) this.getDrawable()).getBitmap();
    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(output);
    final int color = 0xff424242;
    final Paint paint = new Paint();
    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    paint.setAntiAlias(true);
    canvas.drawARGB(0, 0, 0, 0);
    // paint.setColor(color);
    canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(bitmap, rect, rect, paint);
    return output;
  }

  @Override
  public void onDraw(Canvas canvas)
  {
    Paint paint = new Paint();
    // paint.setColor(Color.CYAN);
    canvas.drawBitmap(getclip(), 30, 20, paint);
  }

}