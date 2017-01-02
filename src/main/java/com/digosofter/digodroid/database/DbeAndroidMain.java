package com.digosofter.digodroid.database;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.arquivo.ArquivoDb;
import com.digosofter.digojava.database.DbeMain;

public abstract class DbeAndroidMain extends DbeMain
{
  public static final String STR_FILE_PREFIXO = ".sqlite";

  private ArquivoDb _arq;
  private SQLiteDatabase _dbeEscrita;
  private SQLiteDatabase _dbeLeitura;
  private DialogInterface.OnClickListener _evtDlgApagar;
  private SQLiteOpenHelper _objSqLiteOpenHelper;

  public DbeAndroidMain(String strNome)
  {
    this.setStrNome(strNome + STR_FILE_PREFIXO);
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

    String strPergunta = "Todos os dados serão perdidos, inclusive aqueles que ainda não foram sincronizados.\n A aplicação será fechada.\n\n Tem certeza desta ação?";

    AppAndroid.getI().perguntar(act, strPergunta, this.getEvtDlgApagar());
  }

  private void apagar()
  {
    this.setDbeEscrita(null);
    this.setDbeLeitura(null);
    this.setObjSQLiteOpenHelper(null);

    AppAndroid.getI().getCnt().deleteDatabase(this.getStrNome());

    AppAndroid.getI().notificar("Banco de dados apagado.");

    AppAndroid.getI().fechar();
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
    this.getDbeEscrita().execSQL(sql);
  }

  public Cursor execSqlComRetorno(String sql)
  {
    return this.getDbeLeitura().rawQuery(sql, null);
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

  private SQLiteDatabase getDbeEscrita()
  {
    if (_dbeEscrita != null)
    {
      return _dbeEscrita;
    }

    _dbeEscrita = this.getObjSQLiteOpenHelper().getWritableDatabase();

    return _dbeEscrita;
  }

  private SQLiteDatabase getDbeLeitura()
  {
    if (_dbeLeitura != null)
    {
      return _dbeLeitura;
    }

    _dbeLeitura = this.getObjSQLiteOpenHelper().getReadableDatabase();

    return _dbeLeitura;
  }

  private DialogInterface.OnClickListener getEvtDlgApagar()
  {
    if (_evtDlgApagar != null)
    {
      return _evtDlgApagar;
    }

    _evtDlgApagar = new DialogInterface.OnClickListener()
    {
      @Override
      public void onClick(final DialogInterface dlg, final int intWhich)
      {
        switch (intWhich)
        {
          case DialogInterface.BUTTON_POSITIVE:
            DbeAndroidMain.this.apagar();
            return;

          case DialogInterface.BUTTON_NEGATIVE:
            AppAndroid.getI().notificar("Processo cancelado.");
            return;
        }
      }
    };

    return _evtDlgApagar;
  }

  private SQLiteOpenHelper getObjSQLiteOpenHelper()
  {
    if (_objSqLiteOpenHelper != null)
    {
      return _objSqLiteOpenHelper;
    }

    _objSqLiteOpenHelper = new SQLiteOpenHelper(AppAndroid.getI().getCnt(), this.getStrNome(), null, AppAndroid.getI().getIntVersao())
    {
      @Override
      public void onCreate(SQLiteDatabase objSQLiteDatabase)
      {
        DbeAndroidMain.this.onCreateSQLiteOpenHelper(objSQLiteDatabase);
      }

      @Override
      public void onUpgrade(SQLiteDatabase objSQLiteDatabase, int intOldVersion, int intNewVersion)
      {
        DbeAndroidMain.this.onUpdateSQLiteOpenHelper(objSQLiteDatabase, intOldVersion, intNewVersion);
      }
    };

    return _objSqLiteOpenHelper;
  }

  private void onCreateSQLiteOpenHelper(final SQLiteDatabase objSQLiteDatabase)
  {
  }

  private void onUpdateSQLiteOpenHelper(final SQLiteDatabase objSQLiteDatabase, final int intOldVersion, final int intNewVersion)
  {
  }

  private void setDbeEscrita(SQLiteDatabase dbeEscrita)
  {
    _dbeEscrita = dbeEscrita;
  }

  private void setDbeLeitura(SQLiteDatabase dbeLeitura)
  {
    _dbeLeitura = dbeLeitura;
  }

  private void setObjSQLiteOpenHelper(SQLiteOpenHelper objSQLiteOpenHelper)
  {
    _objSqLiteOpenHelper = objSQLiteOpenHelper;
  }
}