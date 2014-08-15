package com.digosofter.digodroid.database;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;

public class DbFiltro extends Objeto {

  public enum EnmOperador {
    DIFERENTE,
    IGUAL,
    IS_NOT_NULL,
    IS_NULL,
    MAIOR,
    MAIOR_IGUAL,
    MENOR,
    MENOR_IGUAL
  }

  private boolean _booSubSelect;
  private DbColuna _clnFiltro;
  private EnmOperador _enmOperador = EnmOperador.IGUAL;
  private String _strCondicao;
  private String _strFiltro;
  private String _strOperador;
  private String _sqlFiltro;

  public DbFiltro(DbColuna clnFiltro, EnmOperador enmOperador, int intFiltro) {

    try {
      this.setClnFiltro(clnFiltro);
      this.setStrFiltro(String.valueOf(intFiltro));
      this.setEnmOperador(enmOperador);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  public DbFiltro(DbColuna clnFiltro, EnmOperador enmOperador, String strFiltro) {

    try {
      this.setClnFiltro(clnFiltro);
      this.setStrFiltro(strFiltro);
      this.setEnmOperador(enmOperador);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  public DbFiltro(DbColuna clnFiltro, int intFiltro) {

    try {
      this.setClnFiltro(clnFiltro);
      this.setStrFiltro(String.valueOf(intFiltro));
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(121), ex);
    }
    finally {
    }
  }

  public DbFiltro(DbColuna clnFiltro, String strFiltro) {

    try {
      this.setClnFiltro(clnFiltro);
      this.setStrFiltro(strFiltro);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(121), ex);
    }
    finally {
    }
  }

  public DbFiltro(String strSubSelect) {

    try {
      this.setBooSubSelect(true);
      this.setStrFiltro(strSubSelect);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(121), ex);
    }
    finally {
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

    try {
      if (Utils.getBooStrVazia(_strCondicao)) {
        _strCondicao = "and";
      }
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return _strCondicao;
  }

  private String getStrFiltro() {

    return _strFiltro;
  }

  /**
   * Retorna string com o filtro formatado para uso em sql's.
   */
  public String getSqlFiltro(boolean booPrimeiroTermo) {

    try {

      if (!Utils.getBooStrVazia(_sqlFiltro)) {
        return _sqlFiltro;
      }

      if (this.getBooSubSelect()) {

        _sqlFiltro = "_condicao (_sub_select)";
        _sqlFiltro = _sqlFiltro.replace("_condicao", !booPrimeiroTermo ? this.getStrCondicao()
            : Utils.STR_VAZIA);
        _sqlFiltro = _sqlFiltro.replace("_sub_select", this.getStrFiltro());

        return _sqlFiltro;
      }

      _sqlFiltro = "_condicao _tbl_nome._cln_nome _operador '_filtro'";
      _sqlFiltro = _sqlFiltro.replace("_condicao", !booPrimeiroTermo ? this.getStrCondicao()
          : Utils.STR_VAZIA);
      _sqlFiltro = _sqlFiltro.replace("_tbl_nome", this.getClnFiltro().getTbl()
          .getStrNomeSimplificado());
      _sqlFiltro = _sqlFiltro.replace("_cln_nome", this.getClnFiltro().getStrNomeSimplificado());
      _sqlFiltro = _sqlFiltro.replace("_operador", this.getStrOperador());
      _sqlFiltro = _sqlFiltro.replace("_filtro", this.getStrFiltro());
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _sqlFiltro;
  }

  private String getStrOperador() {

    try {
      if (!Utils.getBooStrVazia(_strOperador)) {
        return _strOperador;
      }
      switch (this.getEnmOperador()) {
        case DIFERENTE:
          _strOperador = "<>";
          break;
        case IGUAL:
          _strOperador = "=";
          break;
        case IS_NOT_NULL:
          _strOperador = "is not null";
          break;
        case IS_NULL:
          _strOperador = "is null";
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
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
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
