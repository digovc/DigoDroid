package com.digosofter.digodroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.erro.Erro;

public class DataBase extends SQLiteOpenHelper {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private SQLiteDatabase _objDbEscrita;

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

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _objDbEscrita;
	}

	private SQLiteDatabase _objDataBaseLeitura;

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

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
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

		super(context, strDbNome, null, 1);

		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setStrNome(strDbNome);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(118), ex.getMessage());

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

			new Erro(App.getApp().getStrTextoPadrao(119), ex.getMessage());

		} finally {
		}

		return objCursorTemp;
	}

	public int execSqlGetInt(String sql) {
		// VARIÁVEIS

		int intResultado = 0;
		Cursor objCursor;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			objCursor = this.execSqlComRetorno(sql);

			if (objCursor != null) {
				if (objCursor.moveToFirst()) {
					intResultado = objCursor.getInt(0);
				}
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return intResultado;
	}

	public void execSqlSemRetorno(String sql) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {

			// AÇÕES

			this.getObjDbEscrita().execSQL(sql);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(119), ex.getMessage());

		} finally {
		}
	}

	// FIM MÉTODOS

	// EVENTOS

	@Override
	public void onCreate(SQLiteDatabase db) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

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

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}
	// FIM EVENTOS
}
