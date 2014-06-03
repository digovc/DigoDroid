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

  public enum EnmTipo {
    BOOLEAN, DATE_TIME, INTEGER, MONETARY, NONE, NUMERIC, REAL, TEXT
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
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setStrNome(strNome);
      this.setTbl(tbl);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(120), ex.getMessage());

    } finally {
    }
  }

  public DbColuna(String strNome, DbTabela tbl, EnmTipo enmTipo) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setStrNome(strNome);
      this.setTbl(tbl);
      this.setEnmTipo(enmTipo);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(120), ex.getMessage());

    } finally {
    }
  }

  public DbColuna(String strNome, DbTabela tbl, EnmTipo enmTipo, DbColuna clnReferencia) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setStrNome(strNome);
      this.setTbl(tbl);
      this.setEnmTipo(enmTipo);
      this.setClnReferencia(clnReferencia);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(120), ex.getMessage());

    } finally {
      // LIMPAR VARI�VEIS
      // FIM LIMPAR VARI�VEIS
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
    return Boolean.parseBoolean(this.getStrValor());
  }

  public boolean getBooVisivelCadastro() {
    return _booVisivelCadastro;
  }

  public DbColuna getClnReferencia() {
    return _clnReferencia;
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

      objGregorianCalendarResultado = new GregorianCalendar(intAno, intMes - 1, intDia, intHora,
          intMin, intSeg);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return objGregorianCalendarResultado;
  }

  public EnmTipo getEnmTipo() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_enmTipo == null) {
        _enmTipo = EnmTipo.TEXT;
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _enmTipo;
  }

  public int getIntOrdem() {
    return _intOrdem;
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

  public List<String> getLstStrOpcao() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_lstStrOpcao == null) {
        _lstStrOpcao = new ArrayList<String>();
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _lstStrOpcao;
  }

  public String getStrSqlTipo() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

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

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _strSqlTipo;
  }

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

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _strValor;
  }

  public String getStrValorDefault() {
    return _strValorDefault;
  }

  public String getStrValorExibicao() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      switch (this.getEnmTipo()) {
        case BOOLEAN:
          _strValorExibicao = this.getBooValor() ? "Sim" : "N�o";
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

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _strValorExibicao;
  }

  public String getStrValorMonetario() {
    return Utils.getStrValorMonetario(Double.parseDouble(_strValor));
  }

  public DbTabela getTbl() {
    return _tbl;
  }

  public void setBooChavePrimaria(boolean booChavePrimaria) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (booChavePrimaria) {

        for (DbColuna cln : this.getTbl().getLstCln()) {
          cln._booChavePrimaria = false;
        }

        this.getTbl().setClnChavePrimaria(this);
      }

      _booChavePrimaria = booChavePrimaria;

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }
  }

  public void setBooClnNome(boolean booClnNome) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (booClnNome) {

        for (DbColuna cln : this.getTbl().getLstCln()) {
          cln._booClnNome = false;
        }

        this.getTbl().setClnNome(this);
      }

      _booClnNome = booClnNome;

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }
  }

  public void setBooOrdemCadastro(boolean booOrdemCadastro) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (booOrdemCadastro) {

        for (DbColuna cln : this.getTbl().getLstCln()) {
          cln._booOrdemCadastro = false;
        }

        this.getTbl().setClnOrdemCadastro(this);
      }

      _booOrdemCadastro = booOrdemCadastro;

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }
  }

  public void setBooOrdemDecrecente(boolean booOrdemDecrecente) {
    _booOrdemDecrecente = booOrdemDecrecente;
  }

  public void setBooValor(Boolean booValor) {
    this.setStrValor(String.valueOf(booValor));
  }

  public void setBooVisivelCadastro(boolean booVisivelCadastro) {
    _booVisivelCadastro = booVisivelCadastro;
  }

  public void setClnReferencia(DbColuna clnReferencia) {
    _clnReferencia = clnReferencia;
  }

  public void setDblValor(double dblValor) {
    this.setStrValor(String.valueOf(dblValor));
  }

  public void setDttValor(Date dttValor) {
    this.setStrValor(Utils.getStrDataFormatada(dttValor, Utils.EnmDataFormato.YYYY_MM_DD_HH_MM_SS));
  }

  public void setEnmTipo(EnmTipo enmTipo) {
    _enmTipo = enmTipo;
  }

  public void setIntOrdem(int intOrdem) {
    _intOrdem = intOrdem;
  }

  public void setIntValor(int intValor) {
    this.setStrValor(String.valueOf(intValor));
  }

  public void setLstStrOpcao(List<String> lstStrOpcao) {
    _lstStrOpcao = lstStrOpcao;
  }

  public void setMonValor(double monValor) {
    this.setStrValor(String.valueOf(monValor));
  }

  public void setStrValor(String strValor) {
    _strValor = strValor;
  }

  public void setStrValorDefault(String strValorDefault) {
    _strValorDefault = strValorDefault;
  }

  public void setTbl(DbTabela tbl) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      _tbl = tbl;
      _tbl.getLstCln().add(this);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }
  }

  @Override
  public String toString() {
    return this.getStrValor();
  }

}
