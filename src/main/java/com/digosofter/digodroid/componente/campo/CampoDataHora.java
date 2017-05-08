package com.digosofter.digodroid.componente.campo;

import android.content.Context;
import android.util.AttributeSet;

import com.digosofter.digojava.Utils;

import java.util.Calendar;

public class CampoDataHora extends CampoBotaoMain
{
  private DateTimePickerFragment _frgDateTimePicker;

  public CampoDataHora(Context cnt)
  {
    super(cnt);
  }

  public CampoDataHora(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public CampoDataHora(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  private void abrirDataHora()
  {
    if (this.getCln() == null)
    {
      return;
    }

    this.getFrgDateTimePicker().mostrar();
  }

  private DateTimePickerFragment getFrgDateTimePicker()
  {
    if (_frgDateTimePicker != null)
    {
      return _frgDateTimePicker;
    }

    _frgDateTimePicker = new DateTimePickerFragment();

    _frgDateTimePicker.setCmpDtt(this);

    return _frgDateTimePicker;
  }

  @Override
  protected void processarBtnClick()
  {
    this.abrirDataHora();
  }

  @Override
  public void setDttValor(final Calendar dttValor)
  {
    super.setDttValor(dttValor);

    if (this.getCln() == null)
    {
      return;
    }

    this.getBtn().setText(null);

    if (dttValor == null)
    {
      return;
    }

    switch (this.getCln().getEnmTipo())
    {
      case DATE_TIME:
      case TIMESTAMP_WITH_TIME_ZONE:
      case TIMESTAMP_WITHOUT_TIME_ZONE:
        this.getBtn().setText(Utils.getStrDataFormatada(dttValor, Utils.EnmDataFormato.DD_MM_YYYY_HH_MM));
        return;

      case TIME_WITH_TIME_ZONE:
      case TIME_WITHOUT_TIME_ZONE:
        this.getBtn().setText(Utils.getStrDataFormatada(dttValor, Utils.EnmDataFormato.HH_MM));
        return;

      default:
        this.getBtn().setText(Utils.getStrDataFormatada(dttValor, Utils.EnmDataFormato.DD_MM_YYYY));
        return;
    }
  }
}