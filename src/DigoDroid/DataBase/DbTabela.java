package DigoDroid.DataBase;

import java.util.ArrayList;
import java.util.List;

import DigoDroid.Objeto;
import android.database.Cursor;

public class DbTabela extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private List<DbColuna> _lstObjDbColuna = new ArrayList<DbColuna>();;

	public List<DbColuna> getLstObjDbColuna() {
		return _lstObjDbColuna;
	}

	public void setLstObjDbColuna(List<DbColuna> lstObjDbColuna) {
		_lstObjDbColuna = lstObjDbColuna;
	}

	private DataBase _objDataBase;

	public DataBase getObjDataBase() {
		return _objDataBase;
	}

	public void setObjDataBase(DataBase objDataBase) {
		_objDataBase = objDataBase;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public DbTabela() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			// if (!this.getBooTabelaExiste()) {
			// this.criaTabela();
			// }

			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS

	public void criaTabela() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	public boolean getBooTabelaExiste() {
		// VARIÁVEIS

		boolean booTabelaExiste = false;
		Cursor objCursor;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			objCursor = this.getObjDataBase().execSqlComRetorno(
					"SELECT name FROM sqlite_master WHERE type='table' AND name='"
							+ this.getStrNomeSimplificado() + "';");
			objCursor.moveToFirst();
			if (objCursor.getCount() > 0) {
				booTabelaExiste = true;
			}
			return booTabelaExiste;

			// FIM AÇÕES
		} catch (Exception e) {
			return false;
		} finally {
			objCursor = null;
		}
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
