package com.digosofter.digodroid.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.arquivo.ArquivoDb;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.DataBase;

public class DataBaseAndroid extends DataBase {

  public static final String STR_FILE_PREFIXO = ".sqlite";

  private ArquivoDb _arq;
  private SQLiteDatabase _objDbEscrita;
  private SQLiteDatabase _objDbLeitura;
  private SQLiteOpenHelper _objSQLiteOpenHelper;

  public DataBaseAndroid() {

    try {

      this.setStrNome(AppAndroid.getI().getStrNome() + STR_FILE_PREFIXO);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(118), ex);
    }
    finally {
    }
  }

  /**
   * Salva um arquivo contendo o banco de dados compactado na memória externa.
   */
  public void backup() {

    String dir;

    try {

      dir = "_dir_completo/_app_nome";

      dir = dir.replace("_dir_completo", Environment.getExternalStorageDirectory().getAbsolutePath());
      dir = dir.replace("_app_nome", AppAndroid.getI().getStrNome());

      this.getArq().copiar(dir);

      AppAndroid.getI().mostrarNotificacao("Backup efetuado com sucesso.");
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  @Override
  public void execSql(String sql) {

    try {

      this.getObjDbEscrita().execSQL(sql);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(119), ex);
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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(119), ex);
    }
    finally {
    }

    return crsResultado;
  }

  @Override
  public String execSqlGetStr(String sql) {

    Cursor crs;
    String strResultado = Utils.STR_VAZIA;

    try {

      crs = this.execSqlComRetorno(sql);

      if (crs != null && crs.moveToFirst()) {

        strResultado = crs.getString(0);
      }
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return strResultado;
  }

  private ArquivoDb getArq() {

    try {

      if (_arq != null) {

        return _arq;
      }

      _arq = new ArquivoDb(this);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _arq;
  }

  private SQLiteDatabase getObjDbEscrita() {

    try {

      if (_objDbEscrita != null) {

        return _objDbEscrita;
      }

      _objDbEscrita = this.getObjSQLiteOpenHelper().getWritableDatabase();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _objDbEscrita;
  }

  private SQLiteDatabase getObjDbLeitura() {

    try {

      if (_objDbLeitura != null) {

        return _objDbLeitura;
      }

      _objDbLeitura = this.getObjSQLiteOpenHelper().getReadableDatabase();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _objDbLeitura;
  }

  private SQLiteOpenHelper getObjSQLiteOpenHelper() {

    try {

      if (_objSQLiteOpenHelper != null) {

        return _objSQLiteOpenHelper;
      }

      _objSQLiteOpenHelper = new SQLiteOpenHelper(AppAndroid.getI().getCnt(), this.getStrNome(), null, AppAndroid.getI().getIntVersao()) {

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
      };
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _objSQLiteOpenHelper;
  }
}