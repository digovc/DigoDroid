package com.digosofter.digodroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Util;
import com.digosofter.digodroid.arquivo.ArquivoDb;
import com.digosofter.digodroid.erro.Erro;

public class DataBase extends SQLiteOpenHelper {

  public static final String STR_FILE_PREFIXO = ".sqlite";
  private ArquivoDb _arq;
  private SQLiteDatabase _objDbLeitura;
  private SQLiteDatabase _objDbEscrita;
  private String _strNome;

  public DataBase(String strDbNome, Context cnt) {

    super(cnt, strDbNome + STR_FILE_PREFIXO, null, 1);
    try {
      this.setStrNome(strDbNome + STR_FILE_PREFIXO);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(118), ex);
    }
    finally {
    }
  }

  /**
   * Salva um arquivo contendo o banco de dados compactado na memória externa.
   */
  public void backup() {

    try {
      this.getArq().copiar(Environment.getExternalStorageDirectory().getPath());
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  public Cursor execSqlComRetorno(String sql) {

    Cursor crsResultado = null;
    try {
      crsResultado = this.getObjDbLeitura().rawQuery(sql, null);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(119), ex);
    }
    finally {
    }
    return crsResultado;
  }

  public double execSqlGetDbl(String sql) {

    String str;
    double dblResultado = 0;
    try {
      str = this.execSqlGetStr(sql);
      if (Util.getBooStrVazia(str)) {
        str = "0";
      }
      dblResultado = Double.valueOf(str);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return dblResultado;
  }

  public int execSqlGetInt(String sql) {

    return (int) this.execSqlGetDbl(sql);
  }

  public String execSqlGetStr(String sql) {

    Cursor crs;
    String strResultado = Util.STR_VAZIA;
    try {
      crs = this.execSqlComRetorno(sql);
      if (crs != null && crs.moveToFirst()) {
        strResultado = crs.getString(0);
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return strResultado;
  }

  public void execSqlSemRetorno(String sql) {

    try {
      this.getObjDbEscrita().execSQL(sql);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(119), ex);
    }
    finally {
    }
  }

  private ArquivoDb getArq() {

    try {
      if (_arq != null) {
        return _arq;
      }
      _arq = new ArquivoDb(this);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _arq;
  }

  public SQLiteDatabase getObjDbEscrita() {

    try {
      if (_objDbEscrita != null) {
        return _objDbEscrita;
      }
      _objDbEscrita = this.getWritableDatabase();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _objDbEscrita;
  }

  public SQLiteDatabase getObjDbLeitura() {

    try {
      if (_objDbLeitura != null) {
        return _objDbLeitura = this.getReadableDatabase();
      }
      _objDbLeitura = this.getReadableDatabase();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _objDbLeitura;
  }

  public String getStrNome() {

    return _strNome;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }

  private void setStrNome(String strNome) {

    _strNome = strNome;
  }
}
