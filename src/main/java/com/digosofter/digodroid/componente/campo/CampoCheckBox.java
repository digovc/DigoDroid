package com.digosofter.digodroid.componente.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.componente.checkbox.CheckBoxGeral;

public class CampoCheckBox extends CampoMain
{
  private CheckBoxGeral _ckb;

  public CampoCheckBox(Context cnt)
  {
    super(cnt);
  }

  public CampoCheckBox(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public CampoCheckBox(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
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

  @Override
  protected void setBooSomenteLeitura(final boolean booSomenteLeitura)
  {
    super.setBooSomenteLeitura(booSomenteLeitura);

    this.getCkb().setEnabled(booSomenteLeitura);
  }
}