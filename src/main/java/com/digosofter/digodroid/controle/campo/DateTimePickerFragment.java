package com.digosofter.digodroid.controle.campo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.digosofter.digodroid.activity.ActMain;

import java.util.Calendar;

public class DateTimePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{
  private boolean _booData;
  private CampoDataHora _cmpDtt;

  private boolean getBooData()
  {
    return _booData;
  }

  private CampoDataHora getCmpDtt()
  {
    return _cmpDtt;
  }

  void mostrar()
  {
    if (this.getCmpDtt() == null)
    {
      return;
    }

    if (this.getCmpDtt().getCln() == null)
    {
      return;
    }

    this.setBooData(true);

    switch (this.getCmpDtt().getCln().getEnmTipo())
    {
      case TIME_WITH_TIME_ZONE:
      case TIME_WITHOUT_TIME_ZONE:
        break;
    }

    this.mostrarLocal();
  }

  private void mostrarLocal()
  {
    if (this.getCmpDtt() == null)
    {
      return;
    }

    this.show(((ActMain) this.getCmpDtt().getContext()).getFragmentManager(), this.getCmpDtt().getCln().getSqlNome());
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState)
  {
    if (this.getCmpDtt() == null)
    {
      return null;
    }

    if (this.getBooData())
    {
      return onCreateDialogData();
    }
    else
    {
      return onCreateDialogHora();
    }
  }

  private Dialog onCreateDialogData()
  {
    final Calendar objCalendar = Calendar.getInstance();

    int intAno = objCalendar.get(Calendar.YEAR);

    int intMes = objCalendar.get(Calendar.MONTH);

    int intDia = objCalendar.get(Calendar.DAY_OF_MONTH);

    return new DatePickerDialog(this.getActivity(), this, intAno, intMes, intDia);
  }

  private Dialog onCreateDialogHora()
  {
    final Calendar objCalendar = Calendar.getInstance();

    int intHora = objCalendar.get(Calendar.HOUR_OF_DAY);

    int intMinuto = objCalendar.get(Calendar.MINUTE);

    return new TimePickerDialog(getActivity(), this, intHora, intMinuto, DateFormat.is24HourFormat(this.getActivity()));
  }

  @Override
  public void onDateSet(final DatePicker viw, final int intAno, final int intMes, final int intDia)
  {
    if (this.getCmpDtt() == null)
    {
      return;
    }

    Calendar dttResultado = this.getCmpDtt().getDttValor();

    if (dttResultado == null)
    {
      dttResultado = Calendar.getInstance();
    }

    dttResultado.set(Calendar.YEAR, intAno);
    dttResultado.set(Calendar.MONTH, intMes);
    dttResultado.set(Calendar.DAY_OF_MONTH, intDia);

    this.getCmpDtt().setDttValor(dttResultado);

    if (this.getCmpDtt().getCln() == null)
    {
      return;
    }

    switch (this.getCmpDtt().getCln().getEnmTipo())
    {
      case DATE_TIME:
      case TIMESTAMP_WITH_TIME_ZONE:
      case TIMESTAMP_WITHOUT_TIME_ZONE:
        this.setBooData(false);
        this.mostrarLocal();
        return;
    }
  }

  @Override
  public void onTimeSet(final TimePicker viw, final int intHora, final int intMinuto)
  {
    if (this.getCmpDtt() == null)
    {
      return;
    }

    Calendar dttResultado = this.getCmpDtt().getDttValor();

    if (dttResultado == null)
    {
      dttResultado = Calendar.getInstance();
    }

    dttResultado.set(Calendar.HOUR_OF_DAY, intHora);
    dttResultado.set(Calendar.MINUTE, intMinuto);

    this.getCmpDtt().setDttValor(dttResultado);
  }

  private void setBooData(boolean booData)
  {
    _booData = booData;
  }

  void setCmpDtt(CampoDataHora cmpDtt)
  {
    _cmpDtt = cmpDtt;
  }
}