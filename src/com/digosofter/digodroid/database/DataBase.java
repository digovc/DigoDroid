package com.digosofter.digodroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.digosofter.digodroid.erro.Erro;

public class DataBase extends SQLiteOpenHelper {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private SQLiteDatabase _objDbEscrita;

	public SQLiteDatabase getObjDbEscrita() {
		if (_objDbEscrita == null) {
			_objDbEscrita = this.getWritableDatabase();
		}

		return _objDbEscrita;
	}

	private SQLiteDatabase _objDataBaseLeitura;

	public SQLiteDatabase getObjDbLeitura() {
		if (_objDataBaseLeitura == null) {
			_objDataBaseLeitura = this.getReadableDatabase();
		}
		return _objDataBaseLeitura;
	}

	private String _strNome;

	public String getStrNome() {
		return _strNome;
	}

	private void setStrNome(String strNome) {
		_strNome = strNome;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public DataBase(String strDbNome, Context context) {
		// VARIÁVEIS

		super(context, strDbNome, null, 1);

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setStrNome(strDbNome);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro ao criar o banco de dados.\n", ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS

	public Cursor execSqlComRetorno(String sql) {
		// VARIÁVEIS

		Cursor objCursorTemp = null;

		// FIM VARIÁVEIS
		try {

			// AÇÕES

			objCursorTemp = this.getObjDbLeitura().rawQuery(sql, null);

			// FIM AÇÕES
		} catch (Exception ex) {
			new Erro("Erro ao executar SQL.\n", ex.getMessage());
		} finally {
		}
		return objCursorTemp;
	}

	public void execSqlSemRetorno(String sql) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {

			// AÇÕES

			// this.getObjDbEscrita().rawQuery(sql, null);
			this.getObjDbEscrita().execSQL(sql);

			// FIM AÇÕES
		} catch (Exception e) {
			new Erro("Erro ao executar SQL.\n", e.getMessage());
		} finally {
		}
	}

	// FIM MÉTODOS

	// EVENTOS

	@Override
	public void onCreate(SQLiteDatabase db) {
		// db.execSQL("CREATE TABLE teste(intId int, strTexto text);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	// FIM EVENTOS
}
