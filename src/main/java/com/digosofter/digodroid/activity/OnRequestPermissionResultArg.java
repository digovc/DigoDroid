package com.digosofter.digodroid.activity;

import com.digosofter.digojava.EventArg;

public class OnRequestPermissionResultArg extends EventArg
{

  private String[] _arrStrPermissions;
  private int[] _intArrGrantResults;
  private int _intRequestCode;

  public String[] getArrStrPermissions()
  {
    return _arrStrPermissions;
  }

  public int[] getIntArrGrantResults()
  {
    return _intArrGrantResults;
  }

  public int getIntRequestCode()
  {
    return _intRequestCode;
  }

  public void setArrStrPermissions(String[] arrStrPermissions)
  {
    _arrStrPermissions = arrStrPermissions;
  }

  public void setIntArrGrantResults(int[] intArrGrantResults)
  {
    _intArrGrantResults = intArrGrantResults;
  }

  public void setIntRequestCode(int intRequestCode)
  {
    _intRequestCode = intRequestCode;
  }
}