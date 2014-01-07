package com.digosofter.digodroid.database;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public class DbFiltro extends Objeto {
	// CONSTANTES

	public enum EnmOperador {
		IGUAL, MAIOR, MENOR, MAIOR_IGUAL, MENOR_IGUAL
	}

	// FIM CONSTANTES

	// ATRIBUTOS

	private boolean _booSubSelect;

	private boolean getBooSubSelect() {
		return _booSubSelect;
	}

	private void setBooSubSelect(boolean booSubSelect) {
		_booSubSelect = booSubSelect;
	}

	private EnmOperador _enmOperador = EnmOperador.IGUAL;

	private EnmOperador getEnmOperador() {
		return _enmOperador;
	}

	public void setEnmOperador(EnmOperador enmOperador) {
		_enmOperador = enmOperador;
	}

	private DbColuna _clnFiltro;

	private DbColuna getClnFiltro() {
		return _clnFiltro;
	}

	private void setClnFiltro(DbColuna clnFiltro) {
		_clnFiltro = clnFiltro;
	}

	private String _strCondicao = "AND";

	private String getStrCondicao() {
		return _strCondicao;
	}

	public void setStrCondicao(String strCondicao) {
		_strCondicao = strCondicao;
	}

	private String _strFiltro;

	private String getStrFiltro() {
		return _strFiltro;
	}

	private void setStrFiltro(String strFiltro) {
		_strFiltro = strFiltro;
	}

	private String _strOperador;

	private String getStrOperador() {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			switch (this.getEnmOperador()) {
			case IGUAL:
				_strOperador = "=";
				break;
			case MAIOR:
				_strOperador = ">";
				break;
			case MAIOR_IGUAL:
				_strOperador = ">=";
				break;
			case MENOR:
				_strOperador = "<";
				break;
			case MENOR_IGUAL:
				_strOperador = "<=";
				break;
			default:
				_strOperador = "=";
				break;
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return _strOperador;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public DbFiltro(String strSubSelect) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setBooSubSelect(true);
			this.setStrFiltro(strSubSelect);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(121), ex.getMessage());

		} finally {
		}
	}

	public DbFiltro(DbColuna clnFiltro, int intFiltro) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setClnFiltro(clnFiltro);
			this.setStrFiltro(String.valueOf(intFiltro));

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(121), ex.getMessage());

		} finally {
		}
	}

	public DbFiltro(DbColuna clnFiltro, String strFiltro) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.setClnFiltro(clnFiltro);
			this.setStrFiltro(strFiltro);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(121), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS

	/**
	 * Retorna string com o filtro formatado para uso em sql's.
	 *  
	 */
	public String getStrFiltroFormatado(boolean booPrimeiroTermo) {
		// VARIÁVEIS

		StringBuilder stbResultado = null;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			stbResultado = new StringBuilder();

			if (!booPrimeiroTermo) {

				stbResultado.append(this.getStrCondicao());
				stbResultado.append(" ");					
			}
			
			if (!this.getBooSubSelect()) {

				stbResultado.append(this.getClnFiltro().getTbl().getStrNomeSimplificado());
				stbResultado.append(".");
				stbResultado.append(this.getClnFiltro().getStrNomeSimplificado());
				stbResultado.append(this.getStrOperador());
				stbResultado.append("'");
				stbResultado.append(this.getStrFiltro());
				stbResultado.append("' ");

			} else {
				
//				stbResultado.append("(");
				stbResultado.append(this.getStrFiltro());
//				stbResultado.append(") ");
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return stbResultado.toString();
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
