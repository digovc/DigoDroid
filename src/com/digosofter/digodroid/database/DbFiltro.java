package com.digosofter.digodroid.database;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.erro.Erro;

public class DbFiltro extends Objeto {

  public enum EnmOperador {
    DIFERENTE, IGUAL, MAIOR, MAIOR_IGUAL, MENOR, MENOR_IGUAL
  }

  private boolean _booSubSelect;

  private DbColuna _clnFiltro;

  private EnmOperador _enmOperador = EnmOperador.IGUAL;

  private String _strCondicao = "AND";

  private String _strFiltro;

  private String _strOperador;

  public DbFiltro(DbColuna clnFiltro, EnmOperador enmOperador, int intFiltro) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setClnFiltro(clnFiltro);
      this.setStrFiltro(String.valueOf(intFiltro));
      this.setEnmOperador(enmOperador);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public DbFiltro(DbColuna clnFiltro, EnmOperador enmOperador, String strFiltro) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setClnFiltro(clnFiltro);
      this.setStrFiltro(strFiltro);
      this.setEnmOperador(enmOperador);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public DbFiltro(DbColuna clnFiltro, int intFiltro) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setClnFiltro(clnFiltro);
      this.setStrFiltro(String.valueOf(intFiltro));

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(121), ex);

    } finally {
    }
  }

  public DbFiltro(DbColuna clnFiltro, String strFiltro) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setClnFiltro(clnFiltro);
      this.setStrFiltro(strFiltro);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(121), ex);

    } finally {
    }
  }

  public DbFiltro(String strSubSelect) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setBooSubSelect(true);
      this.setStrFiltro(strSubSelect);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(121), ex);

    } finally {
    }
  }

  private boolean getBooSubSelect() {
    return _booSubSelect;
  }

  private DbColuna getClnFiltro() {
    return _clnFiltro;
  }

  private EnmOperador getEnmOperador() {
    return _enmOperador;
  }

  private String getStrCondicao() {
    return _strCondicao;
  }

  private String getStrFiltro() {
    return _strFiltro;
  }

  /**
   * Retorna string com o filtro formatado para uso em sql's.
   * 
   */
  public String getStrFiltroFormatado(boolean booPrimeiroTermo) {
    // VARI�VEIS

    StringBuilder stbResultado = null;

    // FIM VARI�VEIS
    try {
      // A��ES

      stbResultado = new StringBuilder();
      stbResultado.append(" ");

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
        stbResultado.append("'");

      } else {

        // stbResultado.append("(");
        stbResultado.append(this.getStrFiltro());
        // stbResultado.append(") ");
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return stbResultado.toString();
  }

  private String getStrOperador() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      switch (this.getEnmOperador()) {
        case DIFERENTE:
          _strOperador = "<>";
          break;
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

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _strOperador;
  }

  private void setBooSubSelect(boolean booSubSelect) {
    _booSubSelect = booSubSelect;
  }

  private void setClnFiltro(DbColuna clnFiltro) {
    _clnFiltro = clnFiltro;
  }

  public void setEnmOperador(EnmOperador enmOperador) {
    _enmOperador = enmOperador;
  }

  public void setStrCondicao(String strCondicao) {
    _strCondicao = strCondicao;
  }

  private void setStrFiltro(String strFiltro) {
    _strFiltro = strFiltro;
  }

}
