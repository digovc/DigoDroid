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

	private Class _clsActFrm;

	public Class getClsActFrm() {
		return _clsActFrm;
	}

	protected void setClsActFrm(Class clsActFrm) {
		_clsActFrm = clsActFrm;
	}

	private int _intQtdLinha;

	public int getIntQtdLinha() {
		// VARIÁVEIS

		Cursor crs;
		String sql;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			sql = "SELECT COUNT(" + this.getClnChavePrimaria().getStrNomeSimplificado() + ") FROM "
					+ this.getStrNomeSimplificado() + ";";
			crs = this.getObjDataBase().execSqlComRetorno(sql);

			if (crs != null) {
				if (crs.moveToFirst()) {
					_intQtdLinha = crs.getInt(0);
				}
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			_intQtdLinha = 0;

		} finally {
		}

		return _intQtdLinha;
	}

	private ArrayList<DbFiltro> _lstDbFiltroTelaCadastro = new ArrayList<DbFiltro>();

	public ArrayList<DbFiltro> getLstDbFiltroTelaCadastro() {
		return _lstDbFiltroTelaCadastro;
	}

	private void setLstDbFiltroTelaCadastro(ArrayList<DbFiltro> lstDbFiltroTelaCadastro) {
		_lstDbFiltroTelaCadastro = lstDbFiltroTelaCadastro;
	}

	private List<DbColuna> _lstObjDbColuna = new ArrayList<DbColuna>();;

	public List<DbColuna> getLstObjDbColuna() {
		return _lstObjDbColuna;
	}

	public List<DbColuna> getLstCln() {
		return this.getLstObjDbColuna();
	}

	public void setLstObjDbColuna(List<DbColuna> lstObjDbColuna) {
		_lstObjDbColuna = lstObjDbColuna;
	}

	private DbColuna _clnChavePrimaria;

	public DbColuna getClnChavePrimaria() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			for (DbColuna cln : this.getLstObjDbColuna()) {
				if (cln.getBooChavePrimaria()) {
					_clnChavePrimaria = cln;
					break;
				}
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnChavePrimaria;
	}

	private DbColuna _clnNome;

	public DbColuna getClnNome() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

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

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnNome;
	}

	private DbColuna _clnOrdemCadastro;

	public DbColuna getClnOrdemCadastro() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_clnOrdemCadastro == null) {

				for (DbColuna cln : this.getLstObjDbColuna()) {
					if (cln.getBooOrdemCadastro()) {
						_clnOrdemCadastro = cln;
						break;
					}
				}

				if (_clnOrdemCadastro == null) {
					_clnOrdemCadastro = this.getClnChavePrimaria();
				}
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _clnOrdemCadastro;
	}

	public void setClnOrdemCadastro(DbColuna clnOrdemCadastro) {
		_clnOrdemCadastro = clnOrdemCadastro;
	}

	private DataBase _objDataBase;

	public DataBase getObjDataBase() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_objDataBase == null) {
				this._objDataBase = App.getApp().getObjDataBasePrincipal();
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

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
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			App.getApp().getLstTbl().add(this);
			this.setStrNome(strNome);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(122), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS

	public void abrirTelaCadastro(Activity actPai) {
		// VARIÁVEIS

		Intent objIntent;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			App.getApp().setTblSelecionada(this);
			objIntent = new Intent(actPai, ActCadastro.class);
			actPai.startActivityForResult(objIntent, 0);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(123), ex.getMessage());

		} finally {
		}
	}

	public void buscarRegistro(DbColuna clnFiltro, String strValorFiltro) {
		// VARIÁVEIS

		Cursor objCrs;
		int intColunaIndex = 0;
		String sql;
		String strTabelaNome;
		String strColunaFiltro;
		String strColunasNomes = Utils.STRING_VAZIA;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			strTabelaNome = this.getStrNomeSimplificado();
			strColunaFiltro = clnFiltro.getStrNomeSimplificado();

			for (DbColuna cln : this.getLstObjDbColuna()) {
				cln.setStrValor(null);
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

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(124), ex.getMessage());

		} finally {
		}
	}

	public void buscarRegistroPelaChavePrimaria(int intId) {
		this.buscarRegistro(this.getClnChavePrimaria(), String.valueOf(intId));
	}

	public void buscarRegistroPelaChavePrimaria(String strId) {
		this.buscarRegistro(this.getClnChavePrimaria(), strId);
	}

	public void criarTabela() {
		// VARIÁVEIS

		String sql = Utils.STRING_VAZIA;

		// FIM VARIÁVEIS

		try {

			// AÇÕES

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

					if (cln.getStrValorDefault() != null) {
						sql += " DEFAULT '" + cln.getStrValorDefault() + "'";
					}

					sql += ",";
				}

				sql = Utils.removerUltimaLetra(sql);
				sql += ");";

				this.getObjDataBase().execSqlSemRetorno(sql);
			}

			// FIM AÇÕES

		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(124), ex.getMessage());

		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	public void excluir(int intId) {
		// VARIÁVEIS

		String sql = Utils.STRING_VAZIA;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			sql = "DELETE FROM " + this.getStrNomeSimplificado() + " WHERE "
					+ this.getClnChavePrimaria().getStrNomeSimplificado() + "= '" + intId + "';";

			this.getObjDataBase().execSqlSemRetorno(sql);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(126), ex.getMessage());

		} finally {
		}
	}

	public boolean getBooTabelaExiste() {
		// VARIÁVEIS

		boolean booTabelaExiste = false;
		Cursor objCursor;
		String sql;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + this.getStrNomeSimplificado() + "';";

			objCursor = this.getObjDataBase().execSqlComRetorno(sql);
			objCursor.moveToFirst();

			if (objCursor.getCount() > 0) {
				booTabelaExiste = true;
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(127), ex.getMessage());

		} finally {
			objCursor = null;
		}
		return booTabelaExiste;
	}

	public Cursor getCrsDados(List<DbFiltro> lstObjDbFitro) {
		// VARIÁVEIS

		Cursor crsResultado = null;
		String sql = Utils.STRING_VAZIA;
		String strClnNome = Utils.STRING_VAZIA;
		String strFiltro = Utils.STRING_VAZIA;
		String strClnOrdemNome = Utils.STRING_VAZIA;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (lstObjDbFitro != null) {

				strFiltro += " WHERE ";

				for (DbFiltro objDbFiltro : lstObjDbFitro) {
				
					strFiltro += objDbFiltro.getClnFiltro().getStrNomeSimplificado();
					strFiltro += objDbFiltro.getStrOperador() + "'";
					strFiltro += objDbFiltro.getStrFiltro();
					strFiltro += "' AND ";

				}

				strFiltro = Utils.removerUltimaLetra(strFiltro);
				strFiltro = Utils.removerUltimaLetra(strFiltro);
				strFiltro = Utils.removerUltimaLetra(strFiltro);
				strFiltro = Utils.removerUltimaLetra(strFiltro);

			}

			for (DbColuna cln : this.getLstObjDbColuna()) {
				strClnNome += "A." + cln.getStrNomeSimplificado() + ",";
			}

			strClnOrdemNome = this.getClnOrdemCadastro().getStrNomeSimplificado();
			strClnNome = Utils.removerUltimaLetra(strClnNome);

			sql += "SELECT " + strClnNome + " FROM " + this.getStrNomeSimplificado() + " A " + strFiltro + " ORDER BY "
					+ strClnOrdemNome + ";";
			
			crsResultado = this.getObjDataBase().execSqlComRetorno(sql);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(128), ex.getMessage());

		} finally {
		}
		return crsResultado;
	}

	public Cursor getCrsDados() {
		return this.getCrsDados(null);
	}

	public Cursor getCrsDados(DbColuna clnFiltro, String strFiltro) {
		// VARIÁVEIS

		Cursor crsResultado = null;
		DbFiltro objDbFiltro;
		ArrayList<DbFiltro> lstObjDbFiltro;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			objDbFiltro = new DbFiltro(clnFiltro, strFiltro);

			lstObjDbFiltro = new ArrayList<DbFiltro>();
			lstObjDbFiltro.add(objDbFiltro);

			crsResultado = this.getCrsDados(lstObjDbFiltro);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(128), ex.getMessage());

		} finally {
		}
		return crsResultado;
	}

	public Cursor getCrsDadosTelaCadastro(List<DbFiltro> lstObjDbFitro) {
		// VARIÁVEIS

		Cursor crsResultado = null;

		int intNumeroColuna = 0;

		String sql = Utils.STRING_VAZIA;
		String strClnNome = Utils.STRING_VAZIA;
		String strFiltro = Utils.STRING_VAZIA;
		String strClnOrdemNome = Utils.STRING_VAZIA;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (lstObjDbFitro.size() > 0) {
				strFiltro += " WHERE ";

				for (DbFiltro objDbFiltro : lstObjDbFitro) {

					strFiltro += objDbFiltro.getClnFiltro().getStrNomeSimplificado();
					strFiltro += objDbFiltro.getStrOperador() + "'";
					strFiltro += objDbFiltro.getStrFiltro();
					strFiltro += "' " + objDbFiltro.getStrCondicao() + " ";

				}

				strFiltro = Utils.removerUltimaLetra(strFiltro);
				strFiltro = Utils.removerUltimaLetra(strFiltro);
				strFiltro = Utils.removerUltimaLetra(strFiltro);
				strFiltro = Utils.removerUltimaLetra(strFiltro);

			}

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

				if (cln.getBooVisivelCadastro() && !cln.getBooClnNome()) {
					strClnNome += "A." + cln.getStrNomeSimplificado() + ",";
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
			sql += "SELECT " + strClnNome + " FROM " + this.getStrNomeSimplificado() + " A " + strFiltro + " ORDER BY "
					+ strClnOrdemNome + ";";

			crsResultado = this.getObjDataBase().execSqlComRetorno(sql);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(128), ex.getMessage());

		} finally {
		}
		return crsResultado;
	}

	public Cursor getCrsDadosTelaCadastro() {
		return this.getCrsDadosTelaCadastro(this.getLstDbFiltroTelaCadastro());
	}

	public String getStrClnNomePeloNomeSimplificado(String strNomeSimplificado) {
		// VARIÁVEIS

		String strColunaNome = Utils.STRING_VAZIA;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			for (DbColuna cln : this.getLstObjDbColuna()) {

				if (cln.getStrNomeSimplificado().equals(strNomeSimplificado)) {
					strColunaNome = cln.getStrNomeExibicao();
					break;
				}
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(128), ex.getMessage());

		} finally {
		}
		return strColunaNome;
	}

	public void inserir() {
		// VARIÁVEIS

		int intId;

		String strColunasNomes = Utils.STRING_VAZIA;
		String strColunasValores = Utils.STRING_VAZIA;
		String sql = Utils.STRING_VAZIA;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			for (DbColuna cln : this.getLstObjDbColuna()) {

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

			sql += "REPLACE INTO " + this.getStrNomeSimplificado() + " (" + strColunasNomes + ") VALUES ("
					+ strColunasValores + ");";

			this.getObjDataBase().execSqlSemRetorno(sql);

			sql = "SELECT last_insert_rowid();";

			intId = this.getObjDataBase().execSqlGetInt(sql);
			
			this.buscarRegistroPelaChavePrimaria(intId);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(129), ex.getMessage());

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
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

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

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(130), ex.getMessage());

		} finally {
		}
	}

	public void zerarCampos() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			for (DbColuna cln : this.getLstObjDbColuna()) {
				cln.setStrValor(null);
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(130), ex.getMessage());

		} finally {
		}
	}

	public void limparCampos() {
		this.zerarCampos();
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
