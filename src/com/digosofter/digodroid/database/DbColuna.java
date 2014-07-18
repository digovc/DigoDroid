package com.digosofter.digodroid.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.net.io.Util;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Utils;
import com.digosofter.digodroid.erro.Erro;

public class DbColuna extends Objeto {

  public enum EnmTipo {
    BOOLEAN,
    DATE_TIME,
    INTEGER,
    MONETARY,
    NONE,
    NUMERIC,
    REAL,
    TEXT
  }

  private boolean _booChavePrimaria;
  private boolean _booClnNome;
  private boolean _booOrdemCadastro;
  private boolean _booOrdemDecrecente;
  private boolean _booVisivelCadastro;
  private DbColuna _clnReferencia;
  private EnmTipo _enmTipo;
  private int _intOrdem;
  private List<String> _lstStrOpcao;
  private String _strSqlTipo;
  private String _strValor;
  private String _strValorDefault;
  private String _strValorExibicao;
  private DbTabela _tbl;

  public DbColuna(String strNome, DbTabela tbl) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.setStrNome(strNome);
      this.setTbl(tbl);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(120), ex);

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

      new Erro(App.getI().getStrTextoPadrao(120), ex);

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

      new Erro(App.getI().getStrTextoPadrao(120), ex);

    } finally {
      // LIMPAR VARIÁVEIS
      // FIM LIMPAR VARIÁVEIS
    }
  }

  public boolean getBooChavePrimaria() {
    return _booChavePrimaria;
  }

  public boolean getBooClnNome() {
    return _booClnNome;
  }

  public boolean getBooOrdemCadastro() {
    return _booOrdemCadastro;
  }

  public boolean getBooOrdemDecrecente() {
    return _booOrdemDecrecente;
  }

  public boolean getBooValor() {
    // VARIÁVEIS

    boolean booResultado;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      booResultado = Boolean.parseBoolean(this.getStrValor());

      // FIM AÇÕES
    } catch (Exception ex) {

      return false;

    } finally {
    }

    return booResultado;
  }

  public boolean getBooVisivelCadastro() {
    return _booVisivelCadastro;
  }

  public char getChrValor() {
    // VARIÁVEIS

    char chrResultado = 0;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      chrResultado = this.getStrValor().charAt(0);

      // FIM AÇÕES
    } catch (Exception ex) {

      return 0;

    } finally {
    }

    return chrResultado;
  }

  public DbColuna getClnReferencia() {
    return _clnReferencia;
  }

  public double getDblValor() {
    // VARIÁVEIS

    double dlbResultado = 0;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      dlbResultado = Double.parseDouble(this.getStrValor());

      // FIM AÇÕES
    } catch (Exception ex) {

      return 0;

    } finally {
    }

    return dlbResultado;
  }

  public GregorianCalendar getDttValor() {
    // VARIÁVEIS

    int intAno = 0;
    int intMes = 0;
    int intDia = 0;
    int intHora = 0;
    int intMin = 0;
    int intSeg = 0;
    GregorianCalendar dttResultado = null;

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

      dttResultado = new GregorianCalendar(intAno, intMes - 1, intDia, intHora, intMin, intSeg);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return dttResultado;
  }

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

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _enmTipo;
  }

  public int getIntOrdem() {
    return _intOrdem;
  }

  public int getIntValor() {
    // VARIÁVEIS

    int intResultado = 0;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      intResultado = Integer.parseInt(this.getStrValor());

      // FIM AÇÕES
    } catch (Exception ex) {

      return 0;

    } finally {
    }

    return intResultado;
  }

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

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _lstStrOpcao;
  }

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

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _strSqlTipo;
  }

  public String getStrValor() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (Utils.getBooStrVazia(_strValor)) {
        _strValor = Utils.STRING_VAZIA;
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _strValor;
  }

  public String getStrValorDefault() {
    return _strValorDefault;
  }

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
          _strValorExibicao = Utils.getStrDataFormatada(this.getDttValor().getTime(),
              Utils.EnmDataFormato.DD_MM_YYYY);
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

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _strValorExibicao;
  }

  public String getStrValorMonetario() {
    // VARIÁVEIS

    String strResultado = null;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      strResultado = Utils.getStrValorMonetario(Double.parseDouble(_strValor));

      // FIM AÇÕES
    } catch (Exception ex) {

      return "R$ 0,00";

    } finally {
    }

    return strResultado;
  }

  public DbTabela getTbl() {
    return _tbl;
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

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
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

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
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

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setBooOrdemDecrecente(boolean booOrdemDecrecente) {
    _booOrdemDecrecente = booOrdemDecrecente;
  }

  public void setBooValor(Boolean booValor) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.setStrValor(String.valueOf(booValor));

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setBooVisivelCadastro(boolean booVisivelCadastro) {
    _booVisivelCadastro = booVisivelCadastro;
  }

  public void setChrValor(char chrValor) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.setStrValor(String.valueOf(chrValor));

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setClnReferencia(DbColuna clnReferencia) {
    _clnReferencia = clnReferencia;
  }

  public void setDblValor(double dblValor) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.setStrValor(String.valueOf(dblValor));

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setDttValor(Date dttValor) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (dttValor == null) {
        this.setStrValor(Utils.STRING_VAZIA);
        return;
      }

      this.setStrValor(Utils
          .getStrDataFormatada(dttValor, Utils.EnmDataFormato.YYYY_MM_DD_HH_MM_SS));

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setEnmTipo(EnmTipo enmTipo) {
    _enmTipo = enmTipo;
  }

  public void setIntOrdem(int intOrdem) {
    _intOrdem = intOrdem;
  }

  public void setIntValor(int intValor) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.setStrValor(String.valueOf(intValor));

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setLstStrOpcao(List<String> lstStrOpcao) {
    _lstStrOpcao = lstStrOpcao;
  }

  public void setStrValor(String strValor) {
    _strValor = strValor;
  }

  public void setStrValorDefault(String strValorDefault) {
    _strValorDefault = strValorDefault;
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

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  @Override
  public String toString() {
    return this.getStrValor();
  }

}
