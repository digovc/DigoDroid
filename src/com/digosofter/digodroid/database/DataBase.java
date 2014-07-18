package com.digosofter.digodroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;

public class DataBase extends SQLiteOpenHelper {

  public static final String STR_FILE_PREFIXO = ".sqlite";

  private SQLiteDatabase _objDataBaseLeitura;
  private SQLiteDatabase _objDbEscrita;
  private String _strNome;

  public DataBase(String strDbNome, Context context) {

    super(context, strDbNome + STR_FILE_PREFIXO, null, 1);

    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setStrNome(strDbNome);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(118), ex);

    } finally {
    }
  }

  public Cursor execSqlComRetorno(String sql) {
    // VARI�VEIS

    Cursor crsResultado = null;

    // FIM VARI�VEIS
    try {

      // A��ES

      crsResultado = this.getObjDbLeitura().rawQuery(sql, null);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(119), ex);

    } finally {
    }

    return crsResultado;
  }

  public double execSqlGetDbl(String sql) {
    // VARI�VEIS

    String strResultado;
    double dblResultado = 0;

    // FIM VARI�VEIS
    try {
      // A��ES

      strResultado = this.execSqlGetStr(sql);

      if (Utils.getBooStrVazia(strResultado)) {
        strResultado = "0";
      }

      dblResultado = Double.valueOf(strResultado);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return dblResultado;
  }

  public int execSqlGetInt(String sql) {
    return (int) this.execSqlGetDbl(sql);
  }

  public String execSqlGetStr(String sql) {
    // VARI�VEIS

    Cursor crs;
    String strResultado = Utils.STRING_VAZIA;

    // FIM VARI�VEIS
    try {
      // A��ES

      crs = this.execSqlComRetorno(sql);

      if (crs != null && crs.moveToFirst()) {
        strResultado = crs.getString(0);
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return strResultado;
  }

  public void execSqlSemRetorno(String sql) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {

      // A��ES

      this.getObjDbEscrita().execSQL(sql);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(119), ex);

    } finally {
    }
  }

  public SQLiteDatabase getObjDbEscrita() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_objDbEscrita == null) {
        _objDbEscrita = this.getWritableDatabase();
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _objDbEscrita;
  }

  public SQLiteDatabase getObjDbLeitura() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_objDataBaseLeitura == null) {
        _objDataBaseLeitura = this.getReadableDatabase();
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _objDataBaseLeitura;
  }

  public String getStrNome() {
    return _strNome;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES
      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES
      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  private void setStrNome(String strNome) {
    _strNome = strNome;
  }
}
