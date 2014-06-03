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
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;
import com.digosofter.digodroid.item.ItmConsulta;

public abstract class DbTabela extends Objeto {

  private boolean _booPermitirCadastroNovo = false;

  private boolean _booSincronizar = true;

  private DbColuna _clnChavePrimaria;

  private DbColuna _clnNome;

  private DbColuna _clnOrdemCadastro;

  private Class<?> _clsActFrm;

  private int _intQtdLinha;

  private List<DbColuna> _lstCln;

  private ArrayList<DbFiltro> _lstDbFiltroTelaCadastro;

  private List<ItmConsulta> _lstItmConsulta;

  private DataBase _objDataBase;

  private String _strPesquisaActConsulta;

  public DbTabela(String strNome) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      App.getI().getLstTbl().add(this);
      this.setStrNome(strNome);
      this.inicializarColunas();
      this.criarTabela();

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(122), ex.getMessage());

    } finally {
    }
  }

  public void abrirTelaCadastro(Activity actPai) {
    // VARIÁVEIS

    Intent objIntent;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      App.getI().setTblSelecionada(this);
      objIntent = new Intent(actPai, ActConsulta.class);
      actPai.startActivityForResult(objIntent, 0);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(123), ex.getMessage());

    } finally {
    }
  }

  public void buscarRegistro(DbColuna clnFiltro, int intValor) {
    this.buscarRegistro(clnFiltro, String.valueOf(intValor));
  }

  public void buscarRegistro(DbColuna clnFiltro, String strValorFiltro) {
    // VARIÁVEIS

    Cursor crs;

    int intColunaIndex = 0;

    String sql;
    String strColunasNomes = Utils.STRING_VAZIA;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

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

      crs = this.getObjDataBase().execSqlComRetorno(sql);

      if (crs != null && crs.moveToFirst()) {

        do {

          this.getLstCln().get(intColunaIndex).setStrValor(crs.getString(intColunaIndex));
          intColunaIndex++;

        } while (intColunaIndex < crs.getColumnCount());
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(124), ex.getMessage());

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

      // FIM AÇÕES

    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(124), ex.getMessage());

    } finally {
      // LIMPAR VARIÁVEIS
      // FIM LIMPAR VARIÁVEIS
    }
  }

  public void excluir(int intId) {
    this.excluir(String.valueOf(intId));
  }

  public void excluir(String strId) {
    // VARIÁVEIS

    String sql = Utils.STRING_VAZIA;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      sql = "DELETE FROM " + this.getStrNomeSimplificado() + " WHERE "
          + this.getClnChavePrimaria().getStrNomeSimplificado() + "= '" + strId + "';";

      this.getObjDataBase().execSqlSemRetorno(sql);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(126), ex.getMessage());

    } finally {
    }
  }

  public boolean getBooPermitirCadastroNovo() {
    return _booPermitirCadastroNovo;
  }

  public boolean getBooSincronizar() {
    return _booSincronizar;
  }

  public boolean getBooTabelaExiste() {
    // VARIÁVEIS

    boolean booTblExiste = false;
    Cursor crs;
    String sql;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='"
          + this.getStrNomeSimplificado() + "';";

      crs = this.getObjDataBase().execSqlComRetorno(sql);
      crs.moveToFirst();

      if (crs.getCount() > 0) {
        booTblExiste = true;
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(127), ex.getMessage());

    } finally {
      crs = null;
    }
    return booTblExiste;
  }

  public DbColuna getClnChavePrimaria() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_clnChavePrimaria == null) {
        _clnChavePrimaria = this.getLstCln().get(0);
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _clnChavePrimaria;
  }

  public DbColuna getClnNome() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_clnNome == null) {
        _clnNome = this.getClnChavePrimaria();
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _clnNome;
  }

  public DbColuna getClnOrdemCadastro() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_clnOrdemCadastro == null) {
        _clnOrdemCadastro = this.getClnChavePrimaria();
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _clnOrdemCadastro;
  }

  public Class<?> getClsActFrm() {
    return _clsActFrm;
  }

  public Cursor getCrsDados() {
    return this.getCrsDados(this.getLstCln(), null);
  }

  public Cursor getCrsDados(DbColuna cln) {
    // VARIÁVEIS

    List<DbColuna> lstCln = null;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      lstCln = new ArrayList<DbColuna>();
      lstCln.add(cln);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return this.getCrsDados(lstCln, null);
  }

  public Cursor getCrsDados(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {
    // VARIÁVEIS

    List<DbColuna> lstCln = null;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      lstCln = new ArrayList<DbColuna>();
      lstCln.add(cln);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return this.getCrsDados(lstCln, lstObjDbFiltro);
  }

  public Cursor getCrsDados(DbColuna clnFiltro, String strFiltro) {
    // VARIÁVEIS

    Cursor crsResultado = null;
    ArrayList<DbFiltro> lstObjDbFiltro;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      lstObjDbFiltro = new ArrayList<DbFiltro>();
      lstObjDbFiltro.add(new DbFiltro(clnFiltro, strFiltro));
      crsResultado = this.getCrsDados(lstObjDbFiltro);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(128), ex.getMessage());

    } finally {
    }
    return crsResultado;
  }

  public Cursor getCrsDados(List<DbColuna> lstCln, List<DbFiltro> lstObjDbFiltro) {
    // VARIÁVEIS

    boolean booPrimeiroTermo = true;

    Cursor crsResultado = null;

    String sql;
    String strClnNome = Utils.STRING_VAZIA;
    String strFiltro = Utils.STRING_VAZIA;
    String strClnOrdemNome;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (lstObjDbFiltro != null) {

        for (DbFiltro objDbFiltro : lstObjDbFiltro) {

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
      sql += strFiltro.isEmpty() ? Utils.STRING_VAZIA : " WHERE" + strFiltro;
      sql += strClnOrdemNome.isEmpty() ? Utils.STRING_VAZIA : " ORDER BY " + strClnOrdemNome;
      sql += ";";

      crsResultado = this.getObjDataBase().execSqlComRetorno(sql);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(128), ex.getMessage());

    } finally {
    }

    return crsResultado;
  }

  public Cursor getCrsDados(List<DbFiltro> lstObjDbFiltro) {
    return this.getCrsDados(this.getLstCln(), lstObjDbFiltro);
  }

  public Cursor getCrsDadosTelaCadastro() {
    return this.getCrsDadosTelaCadastro(this.getLstDbFiltroTelaCadastro());
  }

  public Cursor getCrsDadosTelaCadastro(List<DbFiltro> lstObjDbFiltro) {
    // VARIÁVEIS

    boolean booPrimeiroTermo = true;

    Cursor crsResultado = null;

    int intNumeroColuna = 0;

    String sql;
    String strClnNome;
    String strFiltro = Utils.STRING_VAZIA;
    String strClnOrdemNome;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (lstObjDbFiltro.size() > 0) {

        for (DbFiltro objDbFiltro : lstObjDbFiltro) {

          strFiltro += objDbFiltro.getStrFiltroFormatado(booPrimeiroTermo);
          booPrimeiroTermo = false;
        }

        lstObjDbFiltro.clear();
      }

      strClnNome = this.getStrNomeSimplificado();
      strClnNome += ".";
      strClnNome += this.getClnChavePrimaria().getStrNomeSimplificado();
      strClnNome += ",";

      if (this.getClnNome().getClnReferencia() != null) {

        strClnNome += "(SELECT ";
        strClnNome += this.getClnNome().getClnReferencia().getTbl().getStrNomeSimplificado();
        strClnNome += ".";
        strClnNome += this.getClnNome().getClnReferencia().getTbl().getClnNome()
            .getStrNomeSimplificado();
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
            strClnNome += cln.getClnReferencia().getTbl().getClnChavePrimaria()
                .getStrNomeSimplificado();
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

      if (!strClnOrdemNome.equals(Utils.STRING_VAZIA)
          && this.getClnOrdemCadastro().getBooOrdemDecrecente()) {
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

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(128), ex.getMessage());

    } finally {
    }

    return crsResultado;
  }

  public int getIntQtdLinha() {
    // VARIÁVEIS

    String sql;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      sql = "SELECT COUNT(" + this.getClnChavePrimaria().getStrNomeSimplificado() + ") FROM "
          + this.getStrNomeSimplificado() + ";";
      _intQtdLinha = this.getObjDataBase().execSqlGetInt(sql);

      // FIM AÇÕES
    } catch (Exception ex) {

      _intQtdLinha = 0;

    } finally {
    }

    return _intQtdLinha;
  }

  public List<DbColuna> getLstCln() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_lstCln == null) {
        _lstCln = new ArrayList<DbColuna>();
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _lstCln;
  }

  public ArrayList<DbFiltro> getLstDbFiltroTelaCadastro() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_lstDbFiltroTelaCadastro == null) {
        _lstDbFiltroTelaCadastro = new ArrayList<DbFiltro>();
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _lstDbFiltroTelaCadastro;
  }

  public List<Integer> getLstInt(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {
    // VARIÁVEIS

    List<Integer> lstIntResultado = null;
    List<String> lstStr;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      lstStr = this.getLstStr(cln, lstObjDbFiltro);

      if (lstStr != null && !lstStr.isEmpty()) {

        lstIntResultado = new ArrayList<Integer>();
        for (String str : lstStr) {

          lstIntResultado.add(Integer.valueOf(str));
        }
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return lstIntResultado;
  }

  public List<ItmConsulta> getLstItmConsulta() {
    // VARIÁVEIS

    Cursor crs;
    ItmConsulta itmConsulta;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_lstItmConsulta == null) {

        _lstItmConsulta = new ArrayList<ItmConsulta>();
        crs = this.getCrsDadosTelaCadastro();

        if (crs != null && crs.moveToFirst()) {

          do {

            itmConsulta = new ItmConsulta();
            itmConsulta.setStrItemId(crs.getString(crs.getColumnIndex(this.getClnChavePrimaria()
                .getStrNomeSimplificado())));

            if (this.getClnNome().getClnReferencia() != null) {
              itmConsulta.setStrNome(crs.getString(crs.getColumnIndex("_strNomeB")));
            } else {
              itmConsulta.setStrNome(crs.getString(crs.getColumnIndex(this.getClnNome()
                  .getStrNomeSimplificado())));
            }

            for (int intColunaIndex = 0; intColunaIndex <= 4; intColunaIndex++) {

              if (intColunaIndex < crs.getColumnCount()) {

                switch (intColunaIndex) {
                  case 2:
                    itmConsulta.setStrCampo001Nome(this.getStrClnNomePeloNomeSimplificado(crs
                        .getColumnName(intColunaIndex)));
                    itmConsulta.setStrCampo001Valor(crs.getString(intColunaIndex));
                    break;
                  case 3:
                    itmConsulta.setStrCampo002Nome(this.getStrClnNomePeloNomeSimplificado(crs
                        .getColumnName(intColunaIndex)));
                    itmConsulta.setStrCampo002Valor(crs.getString(intColunaIndex));
                    break;
                  case 4:
                    itmConsulta.setStrCampo003Nome(this.getStrClnNomePeloNomeSimplificado(crs
                        .getColumnName(intColunaIndex)));
                    itmConsulta.setStrCampo003Valor(crs.getString(intColunaIndex));
                    break;
                }
              }
            }

            _lstItmConsulta.add(itmConsulta);

          } while (crs.moveToNext());
        }

      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _lstItmConsulta;
  }

  public List<String> getLstStr(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {
    // VARIÁVEIS

    Cursor crs;
    List<String> lstStrResultado = null;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      crs = this.getCrsDados(cln, lstObjDbFiltro);

      if (crs != null && crs.moveToFirst()) {

        lstStrResultado = new ArrayList<String>();
        do {

          lstStrResultado.add(crs.getString(0));
        } while (crs.moveToNext());
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return lstStrResultado;
  }

  public DataBase getObjDataBase() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_objDataBase == null) {
        this._objDataBase = App.getI().getObjDataBasePrincipal();
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _objDataBase;
  }

  public String getStrClnNomePeloNomeSimplificado(String strNomeSimplificado) {
    // VARIÁVEIS

    String strColunaNome = Utils.STRING_VAZIA;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      for (DbColuna cln : this.getLstCln()) {

        if (cln.getStrNomeSimplificado().equals(strNomeSimplificado)) {
          strColunaNome = cln.getStrNomeExibicao();
          break;
        }
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(128), ex.getMessage());

    } finally {
    }
    return strColunaNome;
  }

  public String getStrPesquisaActConsulta() {
    return _strPesquisaActConsulta;
  }

  protected abstract void inicializarColunas();

  public void inserir() {
    // VARIÁVEIS

    String strId;
    String strColunasNomes = Utils.STRING_VAZIA;
    String strColunasValores = Utils.STRING_VAZIA;
    String sql;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.setLstItmConsulta(null);

      for (DbColuna cln : this.getLstCln()) {

        if (!Utils.getBooIsEmptyNull(cln.getStrValor())) {

          strColunasNomes += cln.getStrNomeSimplificado() + ",";
          strColunasValores += "'" + cln.getStrValor() + "',";

        } else if (!Utils.getBooIsEmptyNull(cln.getStrValorDefault())) {

          strColunasNomes += cln.getStrNomeSimplificado() + ",";
          strColunasValores += "'" + cln.getStrValorDefault() + "',";
        }
      }

      strColunasNomes = Utils.removerUltimaLetra(strColunasNomes);
      strColunasValores = Utils.removerUltimaLetra(strColunasValores);

      sql = "REPLACE INTO " + this.getStrNomeSimplificado() + " (" + strColunasNomes + ") VALUES ("
          + strColunasValores + ");";

      this.getObjDataBase().execSqlSemRetorno(sql);

      if (Utils.getBooIsEmptyNull(this.getClnChavePrimaria().getStrValor())) {

        sql = "SELECT last_insert_rowid();";
        strId = this.getObjDataBase().execSqlGetStr(sql);

      } else {
        strId = this.getClnChavePrimaria().getStrValor();
      }

      this.buscarRegistroPelaChavePrimaria(strId);

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(129), ex.getMessage());

    } finally {
    }
  }

  public void inserirAleatorio() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

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

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(130), ex.getMessage());

    } finally {
    }
  }

  public void limparCampos() {
    this.zerarCampos();
  }

  public void salvar() {
    this.inserir();
  }

  protected void setBooPermitirCadastroNovo(boolean booPermitirCadastroNovo) {
    _booPermitirCadastroNovo = booPermitirCadastroNovo;
  }

  public void setBooSincronizar(boolean booSincronizar) {
    _booSincronizar = booSincronizar;
  }

  public void setClnChavePrimaria(DbColuna clnChavePrimaria) {
    _clnChavePrimaria = clnChavePrimaria;
  }

  public void setClnNome(DbColuna clnNome) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_clnNome == null) {
        _clnNome = this.getClnChavePrimaria();
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    _clnNome = clnNome;
  }

  public void setClnOrdemCadastro(DbColuna clnOrdemCadastro) {
    _clnOrdemCadastro = clnOrdemCadastro;
  }

  protected void setClsActFrm(Class<?> clsActFrm) {
    _clsActFrm = clsActFrm;
  }

  public void setLstCln(List<DbColuna> lstCln) {
    _lstCln = lstCln;
  }

  private void setLstItmConsulta(List<ItmConsulta> lstItmConsulta) {
    _lstItmConsulta = lstItmConsulta;
  }

  public void setObjDataBase(DataBase objDataBase) {
    _objDataBase = objDataBase;
  }

  public void setStrPesquisaActConsulta(String strPesquisaActConsulta) {
    _strPesquisaActConsulta = strPesquisaActConsulta;
  }

  public void zerarCampos() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      for (DbColuna cln : this.getLstCln()) {
        cln.setStrValor(null);
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(130), ex.getMessage());

    } finally {
    }
  }

}
