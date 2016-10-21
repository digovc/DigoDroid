package com.digosofter.digodroid;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.digosofter.digojava.ConfigMain;

import java.lang.reflect.Field;

public abstract class ConfigAndroidMain extends ConfigMain
{
  private Editor _objEditor;
  private SharedPreferences _objSharedPreferences;

  protected ConfigAndroidMain()
  {
    this.iniciar();
  }

  /**
   * Carrega os valores do arquivo de configuração XML para esta instância.
   */
  public void carregarDados()
  {
    this.carregarDados(this.getClass());
  }

  private void carregarDados(Class cls)
  {
    if (cls == null)
    {
      return;
    }

    if (Object.class.equals(cls))
    {
      return;
    }

    this.carregarDados(cls.getClass().getSuperclass());

    for (Field objField : cls.getDeclaredFields())
    {
      this.carregarDados(objField);
    }
  }

  private void carregarDados(Field objField)
  {
    if (objField == null)
    {
      return;
    }

    objField.setAccessible(true);

    try
    {
      if (boolean.class.equals(objField.getType()))
      {
        objField.set(this, this.getBooConfig(objField.getName(), (boolean) objField.get(this)));
        return;
      }

      if (double.class.equals(objField.getType()))
      {
        objField.set(this, this.getDblConfig(objField.getName(), (double) objField.get(this)));
        return;
      }

      if (String.class.equals(objField.getType()))
      {
        objField.set(this, this.getStrConfig(objField.getName(), (String) objField.get(this)));
        return;
      }

      if (int.class.equals(objField.getType()))
      {
        objField.set(this, this.getIntConfig(objField.getName(), (int) objField.get(this)));
        return;
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    finally
    {
      objField.setAccessible(false);
    }
  }

  private boolean getBooConfig(String strConfig, boolean booDefault)
  {
    if (this.getObjSharedPreferences() == null)
    {
      return false;
    }

    return this.getObjSharedPreferences().getBoolean(strConfig, booDefault);
  }

  private double getDblConfig(String strConfig, double dblDefault)
  {
    if (this.getObjSharedPreferences() == null)
    {
      return 0;
    }

    return this.getObjSharedPreferences().getFloat(strConfig, (float) dblDefault);
  }

  private int getIntConfig(String strConfig, int intDefault)
  {
    return Integer.valueOf(this.getStrConfig(strConfig, String.valueOf(intDefault)));
  }

  private Editor getObjEditor()
  {
    if (_objEditor != null)
    {
      return _objEditor;
    }

    if (this.getObjSharedPreferences() == null)
    {
      return null;
    }

    _objEditor = this.getObjSharedPreferences().edit();

    return _objEditor;
  }

  private SharedPreferences getObjSharedPreferences()
  {
    if (_objSharedPreferences != null)
    {
      return _objSharedPreferences;
    }

    if (AppAndroid.getI().getCnt() == null)
    {
      return null;
    }

    _objSharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppAndroid.getI().getCnt());

    return _objSharedPreferences;
  }

  private String getStrConfig(String strConfig, String strDefault)
  {
    if (this.getObjSharedPreferences() == null)
    {
      return null;
    }

    return this.getObjSharedPreferences().getString(strConfig, strDefault);
  }

  protected void inicializar()
  {
    this.carregarDados();
  }

  private void iniciar()
  {
    this.inicializar();
  }

  /**
   * Persiste os dados desta intância no arquivo de configuração XML da aplicação.
   */
  public void salvar()
  {
    // super.sincronizar();

    this.salvar(this.getClass());
  }

  private void salvar(Class cls)
  {
    if (cls == null)
    {
      return;
    }

    this.salvar(cls.getSuperclass());

    for (Field objField : cls.getDeclaredFields())
    {
      this.salvar(objField);
    }
  }

  private void salvar(Field objField)
  {
    if (objField == null)
    {
      return;
    }

    objField.setAccessible(true);

    try
    {

      if (boolean.class.equals(objField.getType()))
      {
        this.setBooConfig(objField.getName(), (boolean) objField.get(this));
        return;
      }

      if (double.class.equals(objField.getType()))
      {
        this.setDblConfig(objField.getName(), (double) objField.get(this));
        return;
      }

      if (String.class.equals(objField.getType()))
      {
        this.setStrConfig(objField.getName(), (String) objField.get(this));
        return;
      }

      if (int.class.equals(objField.getType()))
      {
        this.setIntConfig(objField.getName(), (int) objField.get(this));
        return;
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    finally
    {
      objField.setAccessible(false);
    }
  }

  private void setBooConfig(String strConfig, boolean booValor)
  {
    if (this.getObjEditor() == null)
    {
      return;
    }

    this.getObjEditor().putBoolean(strConfig, booValor);
    this.getObjEditor().commit();
  }

  private void setDblConfig(String strConfig, double dblValor)
  {
    if (this.getObjEditor() == null)
    {
      return;
    }

    this.getObjEditor().putFloat(strConfig, (float) dblValor);
    this.getObjEditor().commit();
  }

  private void setIntConfig(String strConfig, int intValor)
  {
    if (this.getObjEditor() == null)
    {
      return;
    }

    this.getObjEditor().putString(strConfig, String.valueOf(intValor));
    this.getObjEditor().commit();
  }

  private void setStrConfig(String strConfig, String strValor)
  {
    if (this.getObjEditor() == null)
    {
      return;
    }

    this.getObjEditor().putString(strConfig, strValor);
    this.getObjEditor().commit();
  }
}