package com.digosofter.digodroid.componente.imagem;

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

  /*
    public static Bitmap getBmpCircular(Bitmap bmp, int intRadius) {

      Bitmap bmpResultado;
      Bitmap bmpS;
      Canvas cnv;

      try {

        if (bmp.getWidth() != intRadius || bmp.getHeight() != intRadius) {

          bmpS = Bitmap.createScaledBitmap(bmp, intRadius, intRadius, false);

        } else {

          bmpS = bmp;
        }

        bmpResultado = Bitmap.createBitmap(bmpS.getWidth(), bmpS.getHeight(), Bitmap.Config.ARGB_8888);
        cnv = new Canvas(bmpResultado);

        final Paint objPaint = new Paint();
        final Rect rct = new Rect(0, 0, bmpS.getWidth(), bmpS.getHeight());

        objPaint.setAntiAlias(true);
        objPaint.setFilterBitmap(true);
        objPaint.setDither(true);
        cnv.drawARGB(0, 0, 0, 0);
        objPaint.setColor(Color.parseColor("#BAB399"));
        cnv.drawCircle(bmpS.getWidth() / 2 + 0.7f, bmpS.getHeight() / 2 + 0.7f, bmpS.getWidth() / 2 + 0.1f, objPaint);
        objPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        cnv.drawBitmap(bmpS, rct, rct, objPaint);

        return bmpResultado;

      } catch (Exception ex) {

        new ErroAndroid("Erro inesperado.\n", ex);
      } finally {
      }

      return null;
    }

    @Override
    protected void onDraw(Canvas cnv) {

      Bitmap bmp1;
      Bitmap bmp2;
      Drawable objDrawable;
      Bitmap bmpCirculo;

      try {

        objDrawable = this.getDrawable();

        if (objDrawable == null) {

          return;
        }

        if ((this.getWidth() == 0) || (this.getHeight() == 0)) {

          return;
        }

        bmp1 = ((BitmapDrawable) objDrawable).getBitmap();
        bmp2 = bmp1.copy(Bitmap.Config.ARGB_8888, true);

        bmpCirculo = this.getBmpCircular(bmp2, this.getWidth());

        cnv.drawBitmap(bmpCirculo, 0, 0, null);

      } catch (Exception ex) {

        new ErroAndroid("Erro inesperado.\n", ex);
      } finally {
      }
    }
    */
  @Override
  public void onDraw(Canvas canvas)
  {
    Paint paint = new Paint();
    // paint.setColor(Color.CYAN);
    canvas.drawBitmap(getclip(), 30, 20, paint);
  }

}