package DigoDroid.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public DataBase(String strDbNome, Context context) {
		super(context, strDbNome, null, 1);
	}

	// FIM CONSTRUTORES

	// MÉTODOS

	public Cursor execSqlComRetorno(String sql) {
		try {
			// VARIÁVEIS
			// FIM VARIÁVEIS

			// AÇÕES

			return this.getObjDbLeitura().rawQuery(sql, null);

			// FIM AÇÕES
		} catch (Exception e) {
			return null;
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
