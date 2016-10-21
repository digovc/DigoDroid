package com.digosofter.digodroid.controle.imagem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.digosofter.digodroid.controle.IControleMain;

public class ImagemGeral extends ImageView implements IControleMain
{
  public ImagemGeral(Context cnt)
  {
    super(cnt);

    this.iniciar(null);
  }

  public ImagemGeral(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);

    this.iniciar(atr);
  }

  public ImagemGeral(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);

    this.iniciar(atr);
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