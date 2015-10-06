package com.digosofter.digodroid;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;

public abstract class ConfigMain extends Objeto {

  private Editor _objEditor;
  private SharedPreferences _objSharedPreferences;

  /**
   * Retorna o valor da configuração.
   */
  public boolean getBooConfig(String strConfig, boolean booDefault) {

    return this.getObjSharedPreferences().getBoolean(strConfig, booDefault);
  }

  /**
   * Retorna o valor da configuração.
   */
  public float getDblConfig(String strConfig, double dblDefault) {

    return this.getObjSharedPreferences().getFloat(strConfig, (float) dblDefault);
  }

  /**
   * Retorna o valor da configuração.
   */
  public int getIntConfig(String strConfig, int intDefault) {

    return Integer.valueOf(this.getStrConfig(strConfig, String.valueOf(intDefault)));
  }

  private Editor getObjEditor() {

    try {

      if (_objEditor != null) {

        return _objEditor;
      }

      _objEditor = this.getObjSharedPreferences().edit();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
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

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _objSharedPreferences;
  }

  /**
   * Retorna o valor da configuração.
   */
  public String getStrConfig(String strConfig, String strDefault) {

    return this.getObjSharedPreferences().getString(strConfig, strDefault);
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

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Guarda o valor da configuração.
   */
  public void setDblConfig(String strConfig, double dblValor) {

    try {

      this.getObjEditor().putFloat(strConfig, (float) dblValor);
      this.getObjEditor().commit();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Guarda o valor da configuração.
   */
  public void setIntConfig(String strConfig, int intValor) {

    try {

      this.getObjEditor().putString(strConfig, String.valueOf(intValor));
      this.getObjEditor().commit();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
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

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}