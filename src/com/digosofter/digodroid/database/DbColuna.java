package com.digosofter.digodroid.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;

public class DbColuna extends Objeto {
	// CONSTANTES

	public enum EnmTipo {
		INTEGER, TEXT, NONE, REAL, NUMERIC
	}

	// FIM CONSTANTES

	// ATRIBUTOS

	private boolean _booChavePrimaria = false;

	public boolean getBooChavePrimaria() {
		return _booChavePrimaria;
	}

	public void setBooChavePrimaria(boolean booChavePrimaria) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getTbl().getLstObjDbColuna()) {
				cln._booChavePrimaria = false;
			}

			_booChavePrimaria = booChavePrimaria;

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private boolean _booClnNome = false;

	public boolean getBooClnNome() {
		return _booClnNome;
	}

	public void setBooClnNome(boolean booClnNome) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getTbl().getLstObjDbColuna()) {
				cln._booClnNome = false;
			}

			_booClnNome = booClnNome;

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private boolean _booOrdemCadastro = false;

	public boolean getBooOrdemCadastro() {
		return _booOrdemCadastro;
	}

	public void setBooOrdemCadastro(boolean booOrdemCadastro) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			for (DbColuna cln : this.getTbl().getLstObjDbColuna()) {
				cln._booOrdemCadastro = false;
			}

			_booOrdemCadastro = booOrdemCadastro;

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private boolean _booOrdemDecrecente;

	public boolean getBooOrdemDecrecente() {
		return _booOrdemDecrecente;
	}

	public void setBooOrdemDecrecente(boolean booOrdemDecrecente) {
		_booOrdemDecrecente = booOrdemDecrecente;
	}

	private boolean _booVisivelCadastro = true;

	public boolean getBooVisivelCadastro() {
		return _booVisivelCadastro;
	}

	public void setBooVisivelCadastro(boolean booVisivelCadastro) {
		_booVisivelCadastro = booVisivelCadastro;
	}

	private DbColuna _clnReferencia;

	public DbColuna getClnReferencia() {
		return _clnReferencia;
	}

	public void setClnReferencia(DbColuna clnReferencia) {
		_clnReferencia = clnReferencia;
	}

	private EnmTipo _enmTipo = EnmTipo.TEXT;

	public EnmTipo getEnmTipo() {
		return _enmTipo;
	}

	private String _strSqlTipo;

	public String getStrSqlTipo() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			switch (this.getEnmTipo()) {
			case INTEGER:
				_strSqlTipo = "INTEGER";
				break;
			case NONE:
				_strSqlTipo = "NONE";
				break;
			case NUMERIC:
				_strSqlTipo = "NUMERIC";
				break;
			case REAL:
				_strSqlTipo = "REAL";
				break;
			case TEXT:
				_strSqlTipo = "TEXT";
				break;
			default:
				_strSqlTipo = "TEXT";
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _strSqlTipo;
	}

	public void setEnmTipo(EnmTipo enmTipo) {
		_enmTipo = enmTipo;
	}

	private List<String> _lstStrOpcao = new ArrayList<String>();

	public List<String> getLstStrOpcao() {
		return _lstStrOpcao;
	}

	public void setLstStrOpcao(List<String> lstStrOpcao) {
		_lstStrOpcao = lstStrOpcao;
	}

	private String _strValor;

	public String getStrValor() {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (_strValor == null) {
				_strValor = Utils.STRING_VAZIA;
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _strValor;
	}

	public boolean getBooValor() {
		return Boolean.parseBoolean(this.getStrValor());
	}

	public String getStrValorMonetario() {
		return Utils.getStrValorMonetario(Double.parseDouble(_strValor));
	}

	public double getDblValor() {
		// VARI�VEIS

		double dlbValorResultado = 0;

		// FIM VARI�VEIS
		try {
			// A��ES

			dlbValorResultado = Double.parseDouble(this.getStrValor());

			// FIM A��ES
		} catch (Exception ex) {

			return 0;

		} finally {
		}

		return dlbValorResultado;
	}

	public GregorianCalendar getDttValor() {
		// VARI�VEIS

		int intAno = 0;
		int intMes = 0;
		int intDia = 0;
		int intHora = 0;
		int intMin = 0;
		int intSeg = 0;
		GregorianCalendar objGregorianCalendarResultado = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			intAno = Integer.parseInt(this.getStrValor().substring(0, 4));
			intMes = Integer.parseInt(this.getStrValor().substring(5, 7));
			intDia = Integer.parseInt(this.getStrValor().substring(8, 10));
			intHora = Integer.parseInt(this.getStrValor().substring(11, 13));
			intMin = Integer.parseInt(this.getStrValor().substring(14, 16));
			intSeg = Integer.parseInt(this.getStrValor().substring(17, 19));

			objGregorianCalendarResultado = new GregorianCalendar(intAno, intMes - 1, intDia, intHora, intMin, intSeg);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return objGregorianCalendarResultado;
	}

	public int getIntValor() {
		// VARI�VEIS

		int intValorResultado = 0;

		// FIM VARI�VEIS
		try {
			// A��ES

			intValorResultado = Integer.parseInt(this.getStrValor());

			// FIM A��ES
		} catch (Exception ex) {

			return 0;

		} finally {
		}

		return intValorResultado;
	}

	@Override
	public String toString() {
		return this.getStrValor();
	}

	public void setStrValor(String strValor) {
		_strValor = strValor;
	}

	public void setBooValor(Boolean booValor) {
		this.setStrValor(String.valueOf(booValor));
	}

	public void setDttValor(Date dttValor) {
		this.setStrValor(Utils.getStrDataFormatada(dttValor, Utils.EnmDataFormato.YYYY_MM_DD_HH_MM_SS));
	}

	public void setDblValor(double dblValor) {
		this.setStrValor(String.valueOf(dblValor));
	}

	public void setIntValor(int intValor) {
		this.setStrValor(String.valueOf(intValor));
	}

	public void setMonValor(double monValor) {
		this.setStrValor(String.valueOf(monValor));
	}

	private String _strValorDefault;

	public String getStrValorDefault() {
		return _strValorDefault;
	}

	public void setStrValorDefault(String strValorDefault) {
		_strValorDefault = strValorDefault;
	}

	private DbTabela _tbl;

	public DbTabela getTbl() {
		return _tbl;
	}

	public void setTbl(DbTabela tbl) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			_tbl = tbl;
			_tbl.getLstObjDbColuna().add(this);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public DbColuna(String strNome, DbTabela objDbTabela) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setStrNome(strNome);
			this.setTbl(objDbTabela);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(120), ex.getMessage());

		} finally {
		}
	}

	public DbColuna(String strNome, DbTabela objDbTabela, EnmTipo objEnmTipo) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setStrNome(strNome);
			this.setTbl(objDbTabela);
			this.setEnmTipo(objEnmTipo);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(120), ex.getMessage());

		} finally {
		}
	}

	public DbColuna(String strNome, DbTabela objDbTabela, EnmTipo objEnmTipo, DbColuna clnReferencia) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.setStrNome(strNome);
			this.setTbl(objDbTabela);
			this.setEnmTipo(objEnmTipo);
			this.setClnReferencia(clnReferencia);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(120), ex.getMessage());

		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS
	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
