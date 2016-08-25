package com.digosofter.digodroid.controle.imagem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.digosofter.digodroid.controle.IControleMain;

public class ImagemGeral extends ImageView implements IControleMain
{
  public ImagemGeral(Context context)
  {
    super(context);

    this.iniciar(null);
  }

  public ImagemGeral(Context context, AttributeSet attrs)
  {
    super(context, attrs);

    this.iniciar(attrs);
  }

  public ImagemGeral(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);

    this.iniciar(attrs);
  }

  @Override
  public void finalizar()
  {
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
  }

  @Override
  public void inicializar()
  {
    this.setScaleType(ScaleType.FIT_XY);
  }

  @Override
  public void iniciar(AttributeSet ats)
  {
  }

  @Override
  public void montarLayout()
  {
  }

  @Override
  public void setEventos()
  {
  }
}