package com.digosofter.digodroid.componente.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digodroid.componente.checkbox.CheckBoxGeral;
import com.digosofter.digodroid.erro.ErroAndroid;

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
    try
    {
      if (_ckb != null)
      {
        return _ckb;
      }
      _ckb = new CheckBoxGeral(this.getContext());

    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _ckb;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();
    try
    {
      this.inicializarCkb();

    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarCkb()
  {
    try
    {
      this.getCkb().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();
    try
    {
      this.addView(this.getCkb());

    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void receberFoco()
  {
    this.getCkb().requestFocus();
  }
}