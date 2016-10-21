package com.digosofter.digodroid.activity;

import android.content.Intent;

import com.digosofter.digojava.EventArg;

public class OnActivityResultArg extends EventArg
{
  private int _intRequestCode;
  private int _intResultCode;
  private Intent _ittResult;

  public int getIntRequestCode()
  {
    return _intRequestCode;
  }

  public int getIntResultCode()
  {
    return _intResultCode;
  }

  public Intent getIttResult()
  {
    return _ittResult;
  }

  public void setIntRequestCode(int intRequestCode)
  {
    _intRequestCode = intRequestCode;
  }

  public void setIntResultCode(int intResultCode)
  {
    _intResultCode = intResultCode;
  }

  public void setIttResult(Intent ittResult)
  {
    _ittResult = ittResult;
  }
}