package com.digosofter.digodroid.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.arquivo.ArquivoDb;
import com.digosofter.digojava.database.DataBase;

public class DataBaseAndroid extends DataBase
{
  public static final String STR_FILE_PREFIXO = ".sqlite";

  private ArquivoDb _arq;
  private SQLiteDatabase _objDbEscrita;
  private SQLiteDatabase _objDbLeitura;
  private SQLiteOpenHelper _objSQLiteOpenHelper;

  public DataBaseAndroid()
  {
    this.setStrNome(AppAndroid.getI().getStrNome() + STR_FILE_PREFIXO);
  }

  /**
   * Apaga o arquivo de banco de dados do aparelho.
   *
   * @param act
   */
  public void apagar(ActMain act)
  {
    if (act == null)
    {
      return;
    }

    this.setObjDbEscrita(null);
    this.setObjDbLeitura(null);
    this.setObjSQLiteOpenHelper(null);

    act.deleteDatabase(this.getStrNome());

    AppAndroid.getI().criarTabela();
    AppAndroid.getI().criarView();
    AppAndroid.getI().notificar("Banco de dados apagado.");
  }

  /**
   * Salva um arquivo contendo o banco de dados compactado na memória externa.
   *
   * @param act Activity da aplicação que está solicitando o backup.
   */
  public void backup(final ActMain act)
  {
    this.getArq().copiar(AppAndroid.getI().getDir());

    AppAndroid.getI().notificar("Backup efetuado com sucesso.");
  }

  @Override
  public void execSql(String sql)
  {
    this.getObjDbEscrita().execSQL(sql);
  }

  public Cursor execSqlComRetorno(String sql)
  {
    return this.getObjDbLeitura().rawQuery(sql, null);
  }

  @Override
  public String execSqlGetStr(String sql)
  {
    Cursor crs = this.execSqlComRetorno(sql);

    if (crs == null || !crs.moveToFirst())
    {
      return null;
    }

    String strResultado = crs.getString(0);

    crs.close();

    return strResultado;
  }

  private ArquivoDb getArq()
  {
    if (_arq != null)
    {
      return _arq;
    }

    _arq = new ArquivoDb(this);

    return _arq;
  }

  private SQLiteDatabase getObjDbEscrita()
  {
    if (_objDbEscrita != null)
    {
      return _objDbEscrita;
    }

    _objDbEscrita = this.getObjSQLiteOpenHelper().getWritableDatabase();

    return _objDbEscrita;
  }

  private SQLiteDatabase getObjDbLeitura()
  {
    if (_objDbLeitura != null)
    {
      return _objDbLeitura;
    }

    _objDbLeitura = this.getObjSQLiteOpenHelper().getReadableDatabase();

    return _objDbLeitura;
  }

  private SQLiteOpenHelper getObjSQLiteOpenHelper()
  {
    if (_objSQLiteOpenHelper != null)
    {
      return _objSQLiteOpenHelper;
    }

    _objSQLiteOpenHelper = new SQLiteOpenHelper(AppAndroid.getI().getCnt(), this.getStrNome(), null, AppAndroid.getI().getIntVersao())
    {
      @Override
      public void onCreate(SQLiteDatabase objSQLiteDatabase)
      {
        DataBaseAndroid.this.onCreateSQLiteOpenHelper(objSQLiteDatabase);
      }

      @Override
      public void onUpgrade(SQLiteDatabase objSQLiteDatabase, int intOldVersion, int intNewVersion)
      {
        DataBaseAndroid.this.onUpdateSQLiteOpenHelper(objSQLiteDatabase, intOldVersion, intNewVersion);
      }
    };

    return _objSQLiteOpenHelper;
  }

  private void onCreateSQLiteOpenHelper(final SQLiteDatabase objSQLiteDatabase)
  {
  }

  private void onUpdateSQLiteOpenHelper(final SQLiteDatabase objSQLiteDatabase, final int intOldVersion, final int intNewVersion)
  {
  }

  private void setObjDbEscrita(SQLiteDatabase objDbEscrita)
  {
    _objDbEscrita = objDbEscrita;
  }

  private void setObjDbLeitura(SQLiteDatabase objDbLeitura)
  {
    _objDbLeitura = objDbLeitura;
  }

  private void setObjSQLiteOpenHelper(SQLiteOpenHelper objSQLiteOpenHelper)
  {
    _objSQLiteOpenHelper = objSQLiteOpenHelper;
  }
}