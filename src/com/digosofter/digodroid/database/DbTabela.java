package com.digosofter.digodroid.database;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.Utils.EnmRandomTipo;
import com.digosofter.digodroid.activitys.ActCadastro;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;

public class DbTabela extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private boolean _booSincronizar = true;

	public boolean getBooSincronizar() {
		return _booSincronizar;
	}

	public void setBooSincronizar(boolean booSincronizar) {
		_booSincronizar = booSincronizar;
	}

	private List<DbColuna> _lstObjDbColuna = new ArrayList<DbColuna>();;

	public List<DbColuna> getLstObjDbColuna() {
		return _lstObjDbColuna;
	}

	public void setLstObjDbColuna(List<DbColuna> lstObjDbColuna) {
		_lstObjDbColuna = lstObjDbColuna;
	}

	private DbColuna _clnChavePrimaria;

	public DbColuna getClnChavePrimaria() {

		if (_clnChavePrimaria == null) {
			for (DbColuna cln : this.getLstObjDbColuna()) {
				if (cln.getBooChavePrimaria()) {
					_clnChavePrimaria = cln;
				}
			}
		}
		return _clnChavePrimaria;
	}

	private DbColuna _clnNome;

	public DbColuna getClnNome() {
		if (_clnNome == null) {
			for (DbColuna cln : this.getLstObjDbColuna()) {
				if (cln.getBooClnNome()) {
					_clnNome = cln;
					break;
				}
			}
		}
		if (_clnNome == null) {
			_clnNome = this.getLstObjDbColuna().get(0);
		}
		return _clnNome;
	}

	private DbColuna _clnOrdemCadastro;

	public DbColuna getClnOrdemCadastro() {
		_clnOrdemCadastro = null;
		for (DbColuna cln : this.getLstObjDbColuna()) {
			if (cln.getBooOrdemCadastro()) {
				_clnOrdemCadastro = cln;
				break;
			}
		}
		if (_clnOrdemCadastro == null) {
			_clnOrdemCadastro = this.getClnChavePrimaria();
		}
		return _clnOrdemCadastro;
	}

	public void setClnOrdemCadastro(DbColuna clnOrdemCadastro) {
		_clnOrdemCadastro = clnOrdemCadastro;
	}

	private DataBase _objDataBase;

	public DataBase getObjDataBase() {
		if (_objDataBase == null) {
			this._objDataBase = App.getApp().getObjDataBasePrincipal();
		}
		return _objDataBase;
	}

	public void setObjDataBase(DataBase objDataBase) {
		_objDataBase = objDataBase;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public DbTabela(String strNome) {
		// VARI�VEIS

		App.getApp().getlstTbl().add(this);
		this.setStrNome(strNome);

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

	public void abrirTelaCadastro(Activity actPai) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			Intent objIntent = new Intent(actPai, ActCadastro.class);
			// objIntent.putExtra("TblCliente", this);
			App.getApp().setTblSelecionada(this);
			actPai.startActivityForResult(objIntent, 0);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
	}

	public void buscarRegistro(DbColuna clnFiltro, String strValorFiltro) {
		// VARI�VEIS

		Cursor objCrs = null;
		int intColunaIndex = 0;
		String sql = Utils.STRING_VAZIA;
		String strTabelaNome = this.getStrNomeSimplificado();
		String strColunaFiltro = clnFiltro.getStrNomeSimplificado();
		String strColunasNomes = Utils.STRING_VAZIA;

		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getLstObjDbColuna()) {
				strColunasNomes += "A." + cln.getStrNomeSimplificado() + ",";
			}
			strColunasNomes = Utils.removerUltimaLetra(strColunasNomes);
			sql = "SELECT " + strColunasNomes + " FROM " + strTabelaNome + " AS A WHERE A." + strColunaFiltro + "='"
					+ strValorFiltro + "';";
			objCrs = this.getObjDataBase().execSqlComRetorno(sql);
			if (objCrs != null) {
				if (objCrs.moveToFirst()) {
					do {
						this.getLstObjDbColuna().get(intColunaIndex).setStrValor(objCrs.getString(intColunaIndex));
						intColunaIndex++;
					} while (intColunaIndex < objCrs.getColumnCount());
				}
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
	}

	public void buscarRegistroPelaChavePrimaria(int intId) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.buscarRegistro(this.getClnChavePrimaria(), String.valueOf(intId));

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
	}

	public void criarTabela() {

		// VARI�VEIS

		String sql = Utils.STRING_VAZIA;

		// FIM VARI�VEIS

		try {

			// A��ES

			if (!this.getBooTabelaExiste()) {
				sql += "CREATE TABLE IF NOT EXISTS ";
				sql += this.getStrNomeSimplificado();
				sql += "(";

				for (DbColuna cln : this.getLstObjDbColuna()) {
					sql += cln.getStrNomeSimplificado();
					sql += " ";
					sql += cln.getStrSqlTipo();
					if (cln.getEnmTipo() == EnmTipo.TEXT) {
						sql += cln.getBooChavePrimaria() ? " PRIMARY KEY" : Utils.STRING_VAZIA;
					} else {
						sql += cln.getBooChavePrimaria() ? " PRIMARY KEY AUTOINCREMENT" : Utils.STRING_VAZIA;
					}
					sql += ",";
				}
				sql = Utils.removerUltimaLetra(sql);
				sql += ");";
				this.getObjDataBase().execSqlSemRetorno(sql);
			}

			// FIM A��ES

		} catch (Exception ex) {
			new Erro("Erro ao criar tabela.\n" + ex.getMessage());

		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
	}

	public boolean getBooTabelaExiste() {
		// VARI�VEIS

		boolean booTabelaExiste = false;
		Cursor objCursorTemp;
		String sql;

		// FIM VARI�VEIS
		try {
			// A��ES

			sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + this.getStrNomeSimplificado() + "';";
			objCursorTemp = this.getObjDataBase().execSqlComRetorno(sql);
			objCursorTemp.moveToFirst();
			if (objCursorTemp.getCount() > 0) {
				booTabelaExiste = true;
			}

			// FIM A��ES
		} catch (Exception e) {
		} finally {
			objCursorTemp = null;
		}
		return booTabelaExiste;
	}

	public Cursor getCursorDadosTelaCadastro() {
		// VARI�VEIS

		Cursor crsResultado = null;
		int intNumeroColuna = 0;
		String sql = Utils.STRING_VAZIA;
		String strClnNome = Utils.STRING_VAZIA;
		String strClnOrdemNome = this.getClnOrdemCadastro().getStrNomeSimplificado();

		// FIM VARI�VEIS
		try {
			// A��ES
			strClnNome += "A." + this.getClnChavePrimaria().getStrNomeSimplificado() + ",";
			if (this.getClnNome().getClnReferencia() != null) {

				String strTblReferenciaNome = this.getClnNome().getClnReferencia().getTbl().getStrNomeSimplificado();
				String strTblReferenciaClnNome = this.getClnNome().getClnReferencia().getTbl().getClnNome()
						.getStrNomeSimplificado();
				String strClnReferenciaNome = this.getClnNome().getClnReferencia().getStrNomeSimplificado();

				strClnNome += "(SELECT B." + strTblReferenciaClnNome + " FROM " + strTblReferenciaNome + " B WHERE B."
						+ strClnReferenciaNome + " = A." + this.getClnNome().getStrNomeSimplificado() + ") _strNomeB,";
			} else {
				strClnNome += this.getClnNome().getStrNomeSimplificado() + ",";
			}
			for (DbColuna cln : this.getLstObjDbColuna()) {
				if (cln.getBooVisivelCadastro() && !cln.getBooChavePrimaria() && !cln.getBooClnNome()) {
					strClnNome += "A." + cln.getStrNomeSimplificado() + ",";
					intNumeroColuna++;
				}
				if (intNumeroColuna == 3) {
					break;
				}
			}
			strClnNome = Utils.removerUltimaLetra(strClnNome);
			sql += "SELECT " + strClnNome + " FROM " + this.getStrNomeSimplificado() + " A ORDER BY " + strClnOrdemNome
					+ ";";
			crsResultado = this.getObjDataBase().execSqlComRetorno(sql);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
		return crsResultado;
	}

	public String getStrClnNomeTelaCadastro(int intClnNumero) {
		// VARI�VEIS

		int intIndex = 0;
		String strColunaNome = Utils.STRING_VAZIA;

		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getLstObjDbColuna()) {
				if (cln.getBooVisivelCadastro()) {
					if (intClnNumero == intIndex) {
						strColunaNome = cln.getStrNomeExibicao();
						break;
					}
					intIndex++;
				}
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
		return strColunaNome;
	}

	public void inserir() {
		// VARI�VEIS

		String strColunasNomes = Utils.STRING_VAZIA;
		String strColunasValores = Utils.STRING_VAZIA;
		String sql = Utils.STRING_VAZIA;

		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getLstObjDbColuna()) {

				strColunasNomes += cln.getStrNomeSimplificado() + ",";
				if (cln.getStrValor() != null) {
					strColunasValores += "'" + cln.getStrValor() + "',";
				} else {
					strColunasValores += "null,";
				}
			}
			strColunasNomes = Utils.removerUltimaLetra(strColunasNomes);
			strColunasValores = Utils.removerUltimaLetra(strColunasValores);
			sql += "REPLACE INTO " + this.getStrNomeSimplificado() + " (" + strColunasNomes + ") VALUES ("
					+ strColunasValores + ");";
			this.getObjDataBase().execSqlSemRetorno(sql);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
	}

	public void inserirAleatorio() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getLstObjDbColuna()) {
				switch (cln.getEnmTipo()) {
				case INTEGER:
					cln.setStrValor(Utils.getStrAleatoria(5, EnmRandomTipo.NUMERICO));
					break;
				case REAL:
					cln.setStrValor(Utils.getStrAleatoria(5, EnmRandomTipo.NUMERICO));
					break;
				case NUMERIC:
					cln.setStrValor(Utils.getStrAleatoria(5, EnmRandomTipo.NUMERICO));
					break;
				default:
					cln.setStrValor(Utils.getStrAleatoria(5, EnmRandomTipo.ALPHA));
					break;
				}
			}
			this.inserir();

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
	}
	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
