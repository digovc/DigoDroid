package com.digosofter.digodroid.componente.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.componente.textbox.TextBoxGeral;
import com.digosofter.digojava.OnValorAlteradoArg;
import com.digosofter.digojava.OnValorAlteradoListener;

public class CampoAlfanumerico extends CampoMain implements OnValorAlteradoListener
{
  private TextBoxGeral _txt;

  public CampoAlfanumerico(Context cnt)
  {
    super(cnt);
  }

  public CampoAlfanumerico(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public CampoAlfanumerico(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  public TextBoxGeral getTxt()
  {
    if (_txt != null)
    {
      return _txt;
    }

    _txt = new TextBoxGeral(this.getContext());

    return _txt;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.inicializarTxt();
  }

  private void inicializarTxt()
  {
    this.getTxt().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();

    this.addView(this.getTxt());
  }

  @Override
  public void onValorAlterado(final Object objSender, final OnValorAlteradoArg arg)
  {
    if (objSender.equals(this.getTxt()))
    {
      this.setStrValor(arg.getStrValor());
    }
  }

  @Override
  public void receberFoco()
  {
    this.getTxt().receberFoco();
  }

  @Override
  protected void setBooSomenteLeitura(final boolean booSomenteLeitura)
  {
    super.setBooSomenteLeitura(booSomenteLeitura);

    this.getTxt().setBooSomenteLeitura(this.getBooSomenteLeitura());
  }

  @Override
  public void setEventos()
  {
    super.setEventos();

    this.getTxt().addEvtOnValorAlteradoListener(this);
  }

  @Override
  public void setStrValor(final String strValor)
  {
    super.setStrValor(strValor);

    this.getTxt().setStrValor(strValor);
  }
}