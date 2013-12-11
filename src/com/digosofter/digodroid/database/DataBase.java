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
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_objDbEscrita == null) {
				_objDbEscrita = this.getWritableDatabase();
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _objDbEscrita;
	}

	private SQLiteDatabase _objDataBaseLeitura;

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

		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setStrNome(strDbNome);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(118), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS

	public Cursor execSqlComRetorno(String sql) {
		// VARI�VEIS

		Cursor objCursorTemp = null;

		// FIM VARI�VEIS
		try {

			// A��ES

			objCursorTemp = this.getObjDbLeitura().rawQuery(sql, null);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(119), ex.getMessage());

		} finally {
		}

		return objCursorTemp;
	}

	public int execSqlGetInt(String sql) {
		// VARI�VEIS

		int intResultado = 0;
		Cursor objCursor;

		// FIM VARI�VEIS
		try {
			// A��ES

			objCursor = this.execSqlComRetorno(sql);

			if (objCursor != null) {
				if (objCursor.moveToFirst()) {
					intResultado = objCursor.getInt(0);
				}
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return intResultado;
	}

	public void execSqlSemRetorno(String sql) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {

			// A��ES

			this.getObjDbEscrita().execSQL(sql);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(119), ex.getMessage());

		} finally {
		}
	}

	// FIM M�TODOS

	// EVENTOS

	@Override
	public void onCreate(SQLiteDatabase db) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

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

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}
	// FIM EVENTOS
}
