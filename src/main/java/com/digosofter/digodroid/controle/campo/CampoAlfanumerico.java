package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.textbox.TextBoxGeral;
import com.digosofter.digojava.OnValorAlteradoArg;
import com.digosofter.digojava.OnValorAlteradoListener;

public class CampoAlfanumerico extends CampoMain implements OnValorAlteradoListener
{
  private TextBoxGeral _txt;

  public CampoAlfanumerico(Context context)
  {
    super(context);
  }

  public CampoAlfanumerico(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public CampoAlfanumerico(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  protected TextBoxGeral getTxt()
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
    if (arg == null)
    {
      return;
    }
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
  public void setEventos()
  {
    super.setEventos();

    this.getTxt().addEvtOnValorAlteradoListener(this);
  }
}