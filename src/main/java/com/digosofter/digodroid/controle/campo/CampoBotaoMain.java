package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.digosofter.digodroid.controle.botao.BotaoGeral;

public abstract class CampoBotaoMain extends CampoMain implements View.OnClickListener
{
  private BotaoGeral _btn;

  public CampoBotaoMain(final Context cnt)
  {
    super(cnt);
  }

  public CampoBotaoMain(final Context cnt, final AttributeSet atr)
  {
    super(cnt, atr);
  }

  public CampoBotaoMain(final Context cnt, final AttributeSet atr, final int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  protected BotaoGeral getBtn()
  {
    if (_btn != null)
    {
      return _btn;
    }

    _btn = new BotaoGeral(this.getContext());

    return _btn;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.inicializarBtn();
  }

  private void inicializarBtn()
  {
    this.getBtn().setFocusable(true);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
    {
      this.getBtn().setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
    }
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();

    this.addView(this.getBtn());
  }

  @Override
  public void onClick(final View viw)
  {
    if (this.getBtn().equals(viw))
    {
      this.processarBtnClick();
      return;
    }
  }

  protected abstract void processarBtnClick();

  @Override
  public void receberFoco()
  {
    if (this.getIntValor() > 0)
    {
      this.getBtn().requestFocus();
    }
    else
    {
      this.getBtn().performClick();
    }
  }

  @Override
  protected void setBooSomenteLeitura(final boolean booSomenteLeitura)
  {
    super.setBooSomenteLeitura(booSomenteLeitura);

    this.getBtn().setEnabled(!booSomenteLeitura);
  }

  @Override
  public void setEventos()
  {
    super.setEventos();

    this.setOnClickListener(this);
    this.getBtn().setOnClickListener(this);
  }

}