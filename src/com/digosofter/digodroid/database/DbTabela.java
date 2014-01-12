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

public abstract class DbTabela extends Objeto {
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

	private boolean _booPermitirCadastroNovo = false;

	public boolean getBooPermitirCadastroNovo() {
		return _booPermitirCadastroNovo;
	}

	protected void setBooPermitirCadastroNovo(boolean booPermitirCadastroNovo) {
		_booPermitirCadastroNovo = booPermitirCadastroNovo;
	}

	private Class<?> _clsActFrm;

	public Class<?> getClsActFrm() {
		return _clsActFrm;
	}

	protected void setClsActFrm(Class<?> clsActFrm) {
		_clsActFrm = clsActFrm;
	}

	private int _intQtdLinha;

	public int getIntQtdLinha() {
		// VARI�VEIS

		Cursor crs;
		String sql;

		// FIM VARI�VEIS
		try {
			// A��ES

			sql = "SELECT COUNT(" + this.getClnChavePrimaria().getStrNomeSimplificado() + ") FROM " + this.getStrNomeSimplificado() + ";";
			crs = this.getObjDataBase().execSqlComRetorno(sql);

			if (crs != null) {
				if (crs.moveToFirst()) {
					_intQtdLinha = crs.getInt(0);
				}
			}

			// FIM A��ES
		} catch (Exception ex) {

			_intQtdLinha = 0;

		} finally {
		}

		return _intQtdLinha;
	}

	private ArrayList<DbFiltro> _lstDbFiltroTelaCadastro;

	public ArrayList<DbFiltro> getLstDbFiltroTelaCadastro() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_lstDbFiltroTelaCadastro == null) {
				_lstDbFiltroTelaCadastro = new ArrayList<DbFiltro>();
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _lstDbFiltroTelaCadastro;
	}

	private List<DbColuna> _lstCln;

	public List<DbColuna> getLstCln() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_lstCln == null) {
				_lstCln = new ArrayList<DbColuna>();
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _lstCln;
	}

	public void setLstCln(List<DbColuna> lstCln) {
		_lstCln = lstCln;
	}

	private DbColuna _clnChavePrimaria;

	public DbColuna getClnChavePrimaria() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getLstCln()) {
				if (cln.getBooChavePrimaria()) {
					_clnChavePrimaria = cln;
					break;
				}
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnChavePrimaria;
	}

	private DbColuna _clnNome;

	public DbColuna getClnNome() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_clnNome == null) {
				for (DbColuna cln : this.getLstCln()) {
					if (cln.getBooClnNome()) {
						_clnNome = cln;
						break;
					}
				}
			}

			if (_clnNome == null) {
				_clnNome = this.getLstCln().get(0);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnNome;
	}

	private DbColuna _clnOrdemCadastro;

	public DbColuna getClnOrdemCadastro() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_clnOrdemCadastro == null) {

				for (DbColuna cln : this.getLstCln()) {
					if (cln.getBooOrdemCadastro()) {
						_clnOrdemCadastro = cln;
						break;
					}
				}

				if (_clnOrdemCadastro == null) {
					_clnOrdemCadastro = this.getClnChavePrimaria();
				}
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnOrdemCadastro;
	}

	public void setClnOrdemCadastro(DbColuna clnOrdemCadastro) {
		_clnOrdemCadastro = clnOrdemCadastro;
	}

	private DataBase _objDataBase;

	public DataBase getObjDataBase() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_objDataBase == null) {
				this._objDataBase = App.getI().getObjDataBasePrincipal();
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
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
		// FIM VARI�VEIS
		try {
			// A��ES

			App.getI().getLstTbl().add(this);
			this.setStrNome(strNome);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(122), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS

	public void abrirTelaCadastro(Activity actPai) {
		// VARI�VEIS

		Intent objIntent;

		// FIM VARI�VEIS
		try {
			// A��ES

			App.getI().setTblSelecionada(this);
			objIntent = new Intent(actPai, ActCadastro.class);
			actPai.startActivityForResult(objIntent, 0);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(123), ex.getMessage());

		} finally {
		}
	}

	public void buscarRegistro(DbColuna clnFiltro, String strValorFiltro) {
		// VARI�VEIS

		Cursor objCrs;

		int intColunaIndex = 0;

		String sql;
		String strColunasNomes = Utils.STRING_VAZIA;

		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getLstCln()) {

				cln.setStrValor(null);
				strColunasNomes += cln.getTbl().getStrNomeSimplificado();
				strColunasNomes += ".";
				strColunasNomes += cln.getStrNomeSimplificado();
				strColunasNomes += ",";
			}

			strColunasNomes = Utils.removerUltimaLetra(strColunasNomes);

			sql = "SELECT ";
			sql += strColunasNomes;
			sql += " FROM ";
			sql += this.getStrNomeSimplificado();
			sql += " WHERE ";
			sql += this.getStrNomeSimplificado();
			sql += ".";
			sql += clnFiltro.getStrNomeSimplificado();
			sql += "='";
			sql += strValorFiltro;
			sql += "';";

			objCrs = this.getObjDataBase().execSqlComRetorno(sql);

			if (objCrs != null && objCrs.moveToFirst()) {

				do {

					this.getLstCln().get(intColunaIndex).setStrValor(objCrs.getString(intColunaIndex));
					intColunaIndex++;

				} while (intColunaIndex < objCrs.getColumnCount());
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(124), ex.getMessage());

		} finally {
		}
	}

	public void buscarRegistro(DbColuna clnFiltro, int intValor) {
		this.buscarRegistro(clnFiltro, String.valueOf(intValor));
	}

	public void buscarRegistroPelaChavePrimaria(int intId) {
		this.buscarRegistro(this.getClnChavePrimaria(), String.valueOf(intId));
	}

	public void buscarRegistroPelaChavePrimaria(String strId) {
		this.buscarRegistro(this.getClnChavePrimaria(), strId);
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

				for (DbColuna cln : this.getLstCln()) {
					sql += cln.getStrNomeSimplificado();
					sql += " ";
					sql += cln.getStrSqlTipo();

					if (cln.getEnmTipo() == EnmTipo.TEXT) {
						sql += cln.getBooChavePrimaria() ? " PRIMARY KEY" : Utils.STRING_VAZIA;
					} else {
						sql += cln.getBooChavePrimaria() ? " PRIMARY KEY AUTOINCREMENT" : Utils.STRING_VAZIA;
					}

					if (cln.getStrValorDefault() != null) {
						sql += " DEFAULT '" + cln.getStrValorDefault() + "'";
					}

					sql += ",";
				}

				sql = Utils.removerUltimaLetra(sql);
				sql += ");";

				this.getObjDataBase().execSqlSemRetorno(sql);
			}

			// FIM A��ES

		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(124), ex.getMessage());

		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
	}

	public void excluir(int intId) {
		// VARI�VEIS

		String sql = Utils.STRING_VAZIA;

		// FIM VARI�VEIS
		try {
			// A��ES

			sql = "DELETE FROM " + this.getStrNomeSimplificado() + " WHERE " + this.getClnChavePrimaria().getStrNomeSimplificado() + "= '" + intId + "';";

			this.getObjDataBase().execSqlSemRetorno(sql);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(126), ex.getMessage());

		} finally {
		}
	}

	public boolean getBooTabelaExiste() {
		// VARI�VEIS

		boolean booTabelaExiste = false;
		Cursor objCursor;
		String sql;

		// FIM VARI�VEIS
		try {
			// A��ES

			sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + this.getStrNomeSimplificado() + "';";

			objCursor = this.getObjDataBase().execSqlComRetorno(sql);
			objCursor.moveToFirst();

			if (objCursor.getCount() > 0) {
				booTabelaExiste = true;
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(127), ex.getMessage());

		} finally {
			objCursor = null;
		}
		return booTabelaExiste;
	}

	public Cursor getCrsDados(List<DbColuna> lstCln, List<DbFiltro> lstObjDbFitro) {
		// VARI�VEIS

		boolean booPrimeiroTermo = true;

		Cursor crsResultado = null;

		String sql;
		String strClnNome = Utils.STRING_VAZIA;
		String strFiltro = Utils.STRING_VAZIA;
		String strClnOrdemNome;

		// FIM VARI�VEIS
		try {
			// A��ES

			if (lstObjDbFitro != null) {

				for (DbFiltro objDbFiltro : lstObjDbFitro) {

					strFiltro += objDbFiltro.getStrFiltroFormatado(booPrimeiroTermo);
					booPrimeiroTermo = false;
				}
			}

			for (DbColuna cln : lstCln) {

				strClnNome += cln.getTbl().getStrNomeSimplificado();
				strClnNome += ".";
				strClnNome += cln.getStrNomeSimplificado();
				strClnNome += ",";
			}

			strClnNome = Utils.removerUltimaLetra(strClnNome);

			strClnOrdemNome = this.getClnOrdemCadastro().getStrNomeSimplificado();

			sql = "SELECT ";
			sql += strClnNome;
			sql += " FROM ";
			sql += this.getStrNomeSimplificado();
			sql += strFiltro.isEmpty() ? Utils.STRING_VAZIA : " WHERE " + strFiltro;
			sql += strClnOrdemNome.isEmpty() ? Utils.STRING_VAZIA : " ORDER BY " + strClnOrdemNome;
			sql += ";";

			crsResultado = this.getObjDataBase().execSqlComRetorno(sql);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(128), ex.getMessage());

		} finally {
		}

		return crsResultado;
	}

	public Cursor getCrsDados(List<DbFiltro> lstObjDbFitro) {
		return this.getCrsDados(this.getLstCln(), lstObjDbFitro);
	}

	public Cursor getCrsDados(DbColuna cln) {
		// VARI�VEIS

		List<DbColuna> lstCln = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			lstCln = new ArrayList<DbColuna>();
			lstCln.add(cln);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return this.getCrsDados(lstCln, null);
	}

	public Cursor getCrsDados(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {
		// VARI�VEIS

		List<DbColuna> lstCln = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			lstCln = new ArrayList<DbColuna>();
			lstCln.add(cln);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return this.getCrsDados(lstCln, lstObjDbFiltro);
	}

	public Cursor getCrsDados() {
		return this.getCrsDados(this.getLstCln(), null);
	}

	public Cursor getCrsDados(DbColuna clnFiltro, String strFiltro) {
		// VARI�VEIS

		Cursor crsResultado = null;
		DbFiltro objDbFiltro;
		ArrayList<DbFiltro> lstObjDbFiltro;

		// FIM VARI�VEIS
		try {
			// A��ES

			objDbFiltro = new DbFiltro(clnFiltro, strFiltro);

			lstObjDbFiltro = new ArrayList<DbFiltro>();
			lstObjDbFiltro.add(objDbFiltro);

			crsResultado = this.getCrsDados(lstObjDbFiltro);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(128), ex.getMessage());

		} finally {
		}
		return crsResultado;
	}

	public Cursor getCrsDadosTelaCadastro(List<DbFiltro> lstObjDbFitro) {
		// VARI�VEIS

		boolean booPrimeiroTermo = true;

		Cursor crsResultado = null;

		int intNumeroColuna = 0;

		String sql;
		String strClnNome;
		String strFiltro = Utils.STRING_VAZIA;
		String strClnOrdemNome;

		// FIM VARI�VEIS
		try {
			// A��ES

			if (lstObjDbFitro.size() > 0) {

				for (DbFiltro objDbFiltro : lstObjDbFitro) {

					strFiltro += objDbFiltro.getStrFiltroFormatado(booPrimeiroTermo);
					booPrimeiroTermo = false;
				}

				lstObjDbFitro.clear();
			}

			strClnNome = this.getStrNomeSimplificado();
			strClnNome += ".";
			strClnNome += this.getClnChavePrimaria().getStrNomeSimplificado();
			strClnNome += ",";

			if (this.getClnNome().getClnReferencia() != null) {

				strClnNome += "(SELECT ";
				strClnNome += this.getClnNome().getClnReferencia().getTbl().getStrNomeSimplificado();
				strClnNome += ".";
				strClnNome += this.getClnNome().getClnReferencia().getTbl().getClnNome().getStrNomeSimplificado();
				strClnNome += " FROM ";
				strClnNome += this.getClnNome().getClnReferencia().getTbl().getStrNomeSimplificado();
				strClnNome += " WHERE ";
				strClnNome += this.getClnNome().getClnReferencia().getTbl().getStrNomeSimplificado();
				strClnNome += ".";
				strClnNome += this.getClnNome().getClnReferencia().getStrNomeSimplificado();
				strClnNome += " = ";
				strClnNome += this.getStrNomeSimplificado();
				strClnNome += ".";
				strClnNome += this.getClnNome().getStrNomeSimplificado();
				strClnNome += ") _strNomeB,";

			} else {
				
				strClnNome += this.getStrNomeSimplificado();
				strClnNome += ".";
				strClnNome += this.getClnNome().getStrNomeSimplificado();
				strClnNome += ",";
			}

			for (DbColuna cln : this.getLstCln()) {

				if (cln.getBooVisivelCadastro() && !cln.getBooClnNome()) {

					if (cln.getClnReferencia() != null) {

						strClnNome += "(SELECT ";
						strClnNome += cln.getClnReferencia().getTbl().getStrNomeSimplificado();
						strClnNome += ".";
						strClnNome += cln.getClnReferencia().getTbl().getClnNome().getStrNomeSimplificado();
						strClnNome += " FROM ";
						strClnNome += cln.getClnReferencia().getTbl().getStrNomeSimplificado();
						strClnNome += " WHERE ";
						strClnNome += cln.getClnReferencia().getTbl().getStrNomeSimplificado();
						strClnNome += ".";
						strClnNome += cln.getClnReferencia().getTbl().getClnChavePrimaria().getStrNomeSimplificado();
						strClnNome += " = ";
						strClnNome += this.getStrNomeSimplificado();
						strClnNome += ".";
						strClnNome += cln.getStrNomeSimplificado();
						strClnNome += ")";
						strClnNome += cln.getStrNomeSimplificado();
						strClnNome += ",";

					} else {
						
						strClnNome += this.getStrNomeSimplificado();
						strClnNome += ".";
						strClnNome += cln.getStrNomeSimplificado();
						strClnNome += ",";
					}

					intNumeroColuna++;
				}

				if (intNumeroColuna == 3) {
					break;
				}
			}

			if (this.getClnOrdemCadastro().getClnReferencia() == null) {
				strClnOrdemNome = this.getClnOrdemCadastro().getStrNomeSimplificado();
			} else {
				strClnOrdemNome = "_strNomeB";
			}

			if (!strClnOrdemNome.equals(Utils.STRING_VAZIA) && this.getClnOrdemCadastro().getBooOrdemDecrecente()) {
				strClnOrdemNome += " DESC";
			}

			strClnNome = Utils.removerUltimaLetra(strClnNome);

			sql = "SELECT ";
			sql += strClnNome;
			sql += " FROM ";
			sql += this.getStrNomeSimplificado();
			sql += strFiltro.isEmpty() ? Utils.STRING_VAZIA : " WHERE " + strFiltro;
			sql += strClnOrdemNome.isEmpty() ? Utils.STRING_VAZIA : " ORDER BY " + strClnOrdemNome;
			sql += ";";

			crsResultado = this.getObjDataBase().execSqlComRetorno(sql);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(128), ex.getMessage());

		} finally {
		}

		return crsResultado;
	}

	public Cursor getCrsDadosTelaCadastro() {
		return this.getCrsDadosTelaCadastro(this.getLstDbFiltroTelaCadastro());
	}

	public String getStrClnNomePeloNomeSimplificado(String strNomeSimplificado) {
		// VARI�VEIS

		String strColunaNome = Utils.STRING_VAZIA;

		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getLstCln()) {

				if (cln.getStrNomeSimplificado().equals(strNomeSimplificado)) {
					strColunaNome = cln.getStrNomeExibicao();
					break;
				}
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(128), ex.getMessage());

		} finally {
		}
		return strColunaNome;
	}

	public List<String> getLstStr(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {
		// VARI�VEIS

		Cursor crs;
		List<String> lstStrResultado = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			crs = this.getCrsDados(cln, lstObjDbFiltro);

			if (crs != null && crs.moveToFirst()) {

				lstStrResultado = new ArrayList<String>();
				do {

					lstStrResultado.add(crs.getString(0));
				} while (crs.moveToNext());
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return lstStrResultado;
	}

	public List<Integer> getLstInt(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {
		// VARI�VEIS

		List<Integer> lstIntResultado = null;
		List<String> lstStr;

		// FIM VARI�VEIS
		try {
			// A��ES

			lstStr = this.getLstStr(cln, lstObjDbFiltro);

			if (lstStr != null && !lstStr.isEmpty()) {

				lstIntResultado = new ArrayList<Integer>();
				for (String str : lstStr) {

					lstIntResultado.add(Integer.valueOf(str));
				}
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return lstIntResultado;
	}

	public void inserir() {
		// VARI�VEIS

		int intId;

		String strColunasNomes = Utils.STRING_VAZIA;
		String strColunasValores = Utils.STRING_VAZIA;
		String sql = Utils.STRING_VAZIA;

		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getLstCln()) {

				if (cln.getStrValor() != null && !cln.getStrValor().equals(Utils.STRING_VAZIA)) {
					strColunasNomes += cln.getStrNomeSimplificado() + ",";
					strColunasValores += "'" + cln.getStrValor() + "',";
				} else if (cln.getStrValorDefault() != null && !cln.getStrValorDefault().equals(Utils.STRING_VAZIA)) {
					strColunasNomes += cln.getStrNomeSimplificado() + ",";
					strColunasValores += "'" + cln.getStrValorDefault() + "',";
				}
			}

			strColunasNomes = Utils.removerUltimaLetra(strColunasNomes);
			strColunasValores = Utils.removerUltimaLetra(strColunasValores);

			sql += "REPLACE INTO " + this.getStrNomeSimplificado() + " (" + strColunasNomes + ") VALUES (" + strColunasValores + ");";

			this.getObjDataBase().execSqlSemRetorno(sql);

			sql = "SELECT last_insert_rowid();";

			intId = this.getObjDataBase().execSqlGetInt(sql);

			this.buscarRegistroPelaChavePrimaria(intId);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(129), ex.getMessage());

		} finally {
			strColunasNomes = null;
			strColunasValores = null;
			sql = null;
		}
	}

	public void salvar() {
		this.inserir();
	}

	public void inserirAleatorio() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getLstCln()) {
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

			new Erro(App.getI().getStrTextoPadrao(130), ex.getMessage());

		} finally {
		}
	}

	public void zerarCampos() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getLstCln()) {
				cln.setStrValor(null);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(130), ex.getMessage());

		} finally {
		}
	}

	public void limparCampos() {
		this.zerarCampos();
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
