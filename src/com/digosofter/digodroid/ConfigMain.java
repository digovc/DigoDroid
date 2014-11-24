package com.digosofter.digodroid;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.digosofter.digodroid.erro.AndroidErro;
import com.digosofter.digojava.Objeto;

public abstract class ConfigMain extends Objeto {

  private Editor _objEditor;
  private SharedPreferences _objSharedPreferences;

  /**
   * Retorna o valor da configuração.
   */
  public boolean getBooConfig(String strConfig, boolean booValorDefault) {

    boolean booResultado = false;

    try {

      booResultado = this.getObjSharedPreferences().getBoolean(strConfig, booValorDefault);
    }
    catch (Exception ex) {
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }

    return booResultado;
  }

  /**
   * Retorna o valor da configuração.
   */
  public float getFltConfig(String strConfig, float fltValorDefault) {

    float fltResultado = 0;

    try {

      fltResultado = this.getObjSharedPreferences().getFloat(strConfig, fltValorDefault);
    }
    catch (Exception ex) {
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }

    return fltResultado;
  }

  /**
   * Retorna o valor da configuração.
   */
  public int getIntConfig(String strConfig, int intValorDefault) {

    int intResultado = 0;

    try {

      intResultado = Integer.valueOf(this.getStrConfig(strConfig, String.valueOf(intValorDefault)));
    }
    catch (Exception ex) {
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }

    return intResultado;
  }

  private Editor getObjEditor() {

    try {

      if (_objEditor != null) {
        return _objEditor;
      }

      _objEditor = this.getObjSharedPreferences().edit();
    }
    catch (Exception ex) {
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _objEditor;
  }

  private SharedPreferences getObjSharedPreferences() {

    try {

      if (_objSharedPreferences != null) {
        return _objSharedPreferences;
      }

      _objSharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppAndroid.getI().getCnt());
    }
    catch (Exception ex) {
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _objSharedPreferences;
  }

  /**
   * Retorna o valor da configuração.
   */
  public String getStrConfig(String strConfig, String strValorDefault) {

    String strResultado = null;

    try {

      strResultado = this.getObjSharedPreferences().getString(strConfig, strValorDefault);
    }
    catch (Exception ex) {
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }

    return strResultado;
  }

  /**
   * Guarda o valor da configuração.
   */
  public void setBooConfig(String strConfig, boolean booValor) {

    try {

      this.getObjEditor().putBoolean(strConfig, booValor);
      this.getObjEditor().commit();
    }
    catch (Exception ex) {
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Guarda o valor da configuração.
   */
  public void setFltConfig(String strConfig, float fltValor) {

    try {

      this.getObjEditor().putFloat(strConfig, fltValor);
      this.getObjEditor().commit();
    }
    catch (Exception ex) {
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Guarda o valor da configuração.
   */
  public void setIntConfig(String strConfig, int intValor) {

    try {

      this.getObjEditor().putInt(strConfig, intValor);
      this.getObjEditor().commit();
    }
    catch (Exception ex) {
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Guarda o valor da configuração.
   */
  public void setStrConfig(String strConfig, String strValor) {

    try {

      this.getObjEditor().putString(strConfig, strValor);
      this.getObjEditor().commit();
    }
    catch (Exception ex) {
      new AndroidErro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}
