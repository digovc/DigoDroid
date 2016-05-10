package com.digosofter.digodroid.erro;

import android.content.Intent;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActErro;
import com.digosofter.digojava.erro.Erro;

import java.io.Serializable;

public class ErroAndroid extends Erro implements Serializable
{

  private static final long serialVersionUID = 1L;

  public ErroAndroid(String strMsg, Exception ex)
  {
    super(strMsg, ex);
    Intent itt;
    try
    {
      itt = new Intent(AppAndroid.getI().getCnt(), ActErro.class);
      itt.putExtra(ActErro.STR_EXTRA_IN_OBJ_ERRO, this);
      AppAndroid.getI().getActPrincipal().startActivity(itt);

    }
    catch (Exception e)
    {
    }
    finally
    {
    }
  }
}