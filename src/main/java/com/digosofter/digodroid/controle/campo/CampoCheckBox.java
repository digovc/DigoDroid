package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.checkbox.CheckBoxGeral;

public class CampoCheckBox extends CampoMain
{
  private CheckBoxGeral _ckb;

  public CampoCheckBox(Context context)
  {
    super(context);
  }

  public CampoCheckBox(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public CampoCheckBox(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  private CheckBoxGeral getCkb()
  {
    if (_ckb != null)
    {
      return _ckb;
    }

    _ckb = new CheckBoxGeral(this.getContext());

    return _ckb;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.inicializarCkb();
  }

  private void inicializarCkb()
  {
    this.getCkb().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();

    this.addView(this.getCkb());
  }

  @Override
  public void receberFoco()
  {
    this.getCkb().requestFocus();
  }
}