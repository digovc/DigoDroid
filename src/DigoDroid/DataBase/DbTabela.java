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
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			// if (!this.getBooTabelaExiste()) {
			// this.criaTabela();
			// }

			// FIM A��ES
		} catch (Exception e) {
		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS

	public void criaTabela() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
	}

	public boolean getBooTabelaExiste() {
		// VARI�VEIS

		boolean booTabelaExiste = false;
		Cursor objCursor;

		// FIM VARI�VEIS
		try {
			// A��ES

			objCursor = this.getObjDataBase().execSqlComRetorno(
					"SELECT name FROM sqlite_master WHERE type='table' AND name='"
							+ this.getStrNomeSimplificado() + "';");
			objCursor.moveToFirst();
			if (objCursor.getCount() > 0) {
				booTabelaExiste = true;
			}
			return booTabelaExiste;

			// FIM A��ES
		} catch (Exception e) {
			return false;
		} finally {
			objCursor = null;
		}
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
