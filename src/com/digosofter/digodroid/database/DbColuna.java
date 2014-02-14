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
		BOOLEAN, DATE_TIME, INTEGER, TEXT, NONE, REAL, MONETARY, NUMERIC
	}

	// FIM CONSTANTES

	// ATRIBUTOS

	private boolean _booChavePrimaria = false;

	public boolean getBooChavePrimaria() {
		return _booChavePrimaria;
	}

	public void setBooChavePrimaria(boolean booChavePrimaria) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (booChavePrimaria) {

				for (DbColuna cln : this.getTbl().getLstCln()) {
					cln._booChavePrimaria = false;
				}

				this.getTbl().setClnChavePrimaria(this);
			}

			_booChavePrimaria = booChavePrimaria;

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private boolean _booClnNome = false;

	public boolean getBooClnNome() {
		return _booClnNome;
	}

	public void setBooClnNome(boolean booClnNome) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (booClnNome) {

				for (DbColuna cln : this.getTbl().getLstCln()) {
					cln._booClnNome = false;
				}

				this.getTbl().setClnNome(this);
			}

			_booClnNome = booClnNome;

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private boolean _booOrdemCadastro = false;

	public boolean getBooOrdemCadastro() {
		return _booOrdemCadastro;
	}

	public void setBooOrdemCadastro(boolean booOrdemCadastro) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (booOrdemCadastro) {

				for (DbColuna cln : this.getTbl().getLstCln()) {
					cln._booOrdemCadastro = false;
				}

				this.getTbl().setClnOrdemCadastro(this);
			}

			_booOrdemCadastro = booOrdemCadastro;

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

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

	private boolean _booVisivelCadastro = false;

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

	private EnmTipo _enmTipo;

	public EnmTipo getEnmTipo() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_enmTipo == null) {
				_enmTipo = EnmTipo.TEXT;
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _enmTipo;
	}

	private int _intOrdem;

	public int getIntOrdem() {
		return _intOrdem;
	}

	public void setIntOrdem(int intOrdem) {
		_intOrdem = intOrdem;
	}

	private String _strSqlTipo;

	public String getStrSqlTipo() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			switch (this.getEnmTipo()) {
			case BOOLEAN:
				_strSqlTipo = "TEXT";
				break;
			case DATE_TIME:
				_strSqlTipo = "TEXT";
				break;
			case INTEGER:
				_strSqlTipo = "INTEGER";
				break;
			case MONETARY:
				_strSqlTipo = "NUMERIC";
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

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _strSqlTipo;
	}

	public void setEnmTipo(EnmTipo enmTipo) {
		_enmTipo = enmTipo;
	}

	private List<String> _lstStrOpcao;

	public List<String> getLstStrOpcao() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_lstStrOpcao == null) {
				_lstStrOpcao = new ArrayList<String>();
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _lstStrOpcao;
	}

	public void setLstStrOpcao(List<String> lstStrOpcao) {
		_lstStrOpcao = lstStrOpcao;
	}

	private String _strValor;

	public String getStrValor() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (_strValor == null) {
				_strValor = Utils.STRING_VAZIA;
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

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
		// VARIÁVEIS

		double dlbValorResultado = 0;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			dlbValorResultado = Double.parseDouble(this.getStrValor());

			// FIM AÇÕES
		} catch (Exception ex) {

			return 0;

		} finally {
		}

		return dlbValorResultado;
	}

	public GregorianCalendar getDttValor() {
		// VARIÁVEIS

		int intAno = 0;
		int intMes = 0;
		int intDia = 0;
		int intHora = 0;
		int intMin = 0;
		int intSeg = 0;
		GregorianCalendar objGregorianCalendarResultado = null;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			intAno = Integer.parseInt(this.getStrValor().substring(0, 4));
			intMes = Integer.parseInt(this.getStrValor().substring(5, 7));
			intDia = Integer.parseInt(this.getStrValor().substring(8, 10));

			try {
				intHora = Integer.parseInt(this.getStrValor().substring(11, 13));
			} catch (Exception e) {
			}

			try {
				intMin = Integer.parseInt(this.getStrValor().substring(14, 16));
			} catch (Exception e) {
			}

			try {
				intSeg = Integer.parseInt(this.getStrValor().substring(17, 19));
			} catch (Exception e) {
			}

			objGregorianCalendarResultado = new GregorianCalendar(intAno, intMes - 1, intDia, intHora, intMin, intSeg);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return objGregorianCalendarResultado;
	}

	public int getIntValor() {
		// VARIÁVEIS

		int intValorResultado = 0;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			intValorResultado = Integer.parseInt(this.getStrValor());

			// FIM AÇÕES
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

	private String _strValorExibicao;

	public String getStrValorExibicao() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			switch (this.getEnmTipo()) {
			case BOOLEAN:
				_strValorExibicao = this.getBooValor() ? "Sim" : "Não";
				break;
			case DATE_TIME:
				_strValorExibicao = Utils.getStrDataFormatada(this.getDttValor().getTime(), Utils.EnmDataFormato.DD_MM_YYYY);
				break;
			case INTEGER:
				_strValorExibicao = this.getStrValor();
				break;
			case MONETARY:
				_strValorExibicao = Utils.getStrValorMonetario(this.getDblValor());
				break;
			case NONE:
				_strValorExibicao = this.getStrValor();
				break;
			case NUMERIC:
				_strValorExibicao = this.getStrValor();
				break;
			case REAL:
				_strValorExibicao = this.getStrValor();
				break;
			case TEXT:
				_strValorExibicao = this.getStrValor();
				break;
			default:
				_strValorExibicao = this.getStrValor();
				break;
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _strValorExibicao;
	}

	private DbTabela _tbl;

	public DbTabela getTbl() {
		return _tbl;
	}

	public void setTbl(DbTabela tbl) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			_tbl = tbl;
			_tbl.getLstCln().add(this);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public DbColuna(String strNome, DbTabela tbl) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setStrNome(strNome);
			this.setTbl(tbl);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(120), ex.getMessage());

		} finally {
		}
	}

	public DbColuna(String strNome, DbTabela tbl, EnmTipo enmTipo) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setStrNome(strNome);
			this.setTbl(tbl);
			this.setEnmTipo(enmTipo);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(120), ex.getMessage());

		} finally {
		}
	}

	public DbColuna(String strNome, DbTabela tbl, EnmTipo enmTipo, DbColuna clnReferencia) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setStrNome(strNome);
			this.setTbl(tbl);
			this.setEnmTipo(enmTipo);
			this.setClnReferencia(clnReferencia);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(120), ex.getMessage());

		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
