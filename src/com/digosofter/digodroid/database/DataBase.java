package com.digosofter.digodroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.arquivo.ArquivoDb;
import com.digosofter.digodroid.erro.Erro;

public class DataBase extends SQLiteOpenHelper {

  public static final String STR_FILE_PREFIXO = ".sqlite";

  private ArquivoDb _arq;
  private SQLiteDatabase _objDataBaseLeitura;
  private SQLiteDatabase _objDbEscrita;
  private String _strNome;

  public DataBase(String strDbNome, Context context) {

    super(context, strDbNome + STR_FILE_PREFIXO, null, 1);

    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.setStrNome(strDbNome + STR_FILE_PREFIXO);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(118), ex);

    } finally {
    }
  }

  /**
   * Salva um arquivo contendo o banco de dados compactado na memória externa.
   */
  public void backup() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.getArq().copiar(Environment.getExternalStorageDirectory().getPath());

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public Cursor execSqlComRetorno(String sql) {
    // VARIÁVEIS

    Cursor crsResultado = null;

    // FIM VARIÁVEIS
    try {

      // AÇÕES

      crsResultado = this.getObjDbLeitura().rawQuery(sql, null);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(119), ex);

    } finally {
    }

    return crsResultado;
  }

  public double execSqlGetDbl(String sql) {
    // VARIÁVEIS

    String strResultado;
    double dblResultado = 0;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      strResultado = this.execSqlGetStr(sql);

      if (Utils.getBooStrVazia(strResultado)) {
        strResultado = "0";
      }

      dblResultado = Double.valueOf(strResultado);

      // FIM AÇÕES
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
    // VARIÁVEIS

    Cursor crs;
    String strResultado = Utils.STRING_VAZIA;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      crs = this.execSqlComRetorno(sql);

      if (crs != null && crs.moveToFirst()) {
        strResultado = crs.getString(0);
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return strResultado;
  }

  public void execSqlSemRetorno(String sql) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {

      // AÇÕES

      this.getObjDbEscrita().execSQL(sql);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(119), ex);

    } finally {
    }
  }

  private ArquivoDb getArq() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_arq != null) {
        return _arq;
      }

      _arq = new ArquivoDb(this);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _arq;
  }

  public SQLiteDatabase getObjDbEscrita() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_objDbEscrita == null) {
        _objDbEscrita = this.getWritableDatabase();
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _objDbEscrita;
  }

  public SQLiteDatabase getObjDbLeitura() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_objDataBaseLeitura == null) {
        _objDataBaseLeitura = this.getReadableDatabase();
      }

      // FIM AÇÕES
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
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES
      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES
      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  private void setStrNome(String strNome) {
    _strNome = strNome;
  }
}
