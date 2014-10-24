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

  private boolean _booItmConsultaUsarCache = true;

  private boolean _booPermitirCadastroNovo;

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

    try {

      App.getI().getLstTbl().add(this);
      this.setStrNome(strNome);
      this.inicializarColunas(-1);
      this.criarTabela();

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(122), ex);

    }
    finally {
    }
  }

  public void abrirTelaCadastro(Activity actPai) {

    Intent objIntent;

    try {

      App.getI().setTblSelecionada(this);
      objIntent = new Intent(actPai, ActConsulta.class);
      actPai.startActivityForResult(objIntent, 0);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(123), ex);

    }
    finally {
    }
  }

  public void buscarRegistro(DbColuna clnFiltro, int intValor) {

    this.buscarRegistro(clnFiltro, String.valueOf(intValor));
  }

  public void buscarRegistro(DbColuna clnFiltro, String strValorFiltro) {

    Cursor crs;

    int intColunaIndex = 0;

    String sql;
    String strColunasNomes = Utils.STRING_VAZIA;

    try {

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

        }
        while (intColunaIndex < crs.getColumnCount());
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(124), ex);

    }
    finally {
    }
  }

  public void buscarRegistroPelaChavePrimaria(int intId) {

    this.buscarRegistro(this.getClnChavePrimaria(), String.valueOf(intId));
  }

  public void buscarRegistroPelaChavePrimaria(String strId) {

    this.buscarRegistro(this.getClnChavePrimaria(), strId);
  }

  public void criarTabela() {

    String sql = Utils.STRING_VAZIA;

    try {

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
          }
          else {
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

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(124), ex);

    }
    finally {
      // LIMPAR VARIÁVEIS
      // FIM LIMPAR VARIÁVEIS
    }
  }

  public void excluir(int intId) {

    this.excluir(String.valueOf(intId));
  }

  public void excluir(String strId) {

    String sql = Utils.STRING_VAZIA;

    try {

      sql = "DELETE FROM " + this.getStrNomeSimplificado() + " WHERE " + this.getClnChavePrimaria().getStrNomeSimplificado() + "= '" + strId + "';";

      this.getObjDataBase().execSqlSemRetorno(sql);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(126), ex);

    }
    finally {
    }
  }

  private boolean getBooItmConsultaUsarCache() {

    return _booItmConsultaUsarCache;
  }

  public boolean getBooPermitirCadastroNovo() {

    return _booPermitirCadastroNovo;
  }

  public boolean getBooSincronizar() {

    return _booSincronizar;
  }

  private boolean getBooTabelaExiste() {

    boolean booResultado = false;
    Cursor crs;
    String sql;

    try {

      sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='_tbl_nome_simplificado';";
      sql = sql.replace("_tbl_nome_simplificado", this.getStrNomeSimplificado());

      crs = this.getObjDataBase().execSqlComRetorno(sql);
      crs.moveToFirst();

      if (crs != null && crs.moveToFirst() && crs.getCount() > 0) {
        booResultado = true;
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(127), ex);

    }
    finally {
      crs = null;
    }

    return booResultado;
  }

  public DbColuna getClnChavePrimaria() {

    try {

      if (_clnChavePrimaria == null) {
        _clnChavePrimaria = this.getLstCln().get(0);
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _clnChavePrimaria;
  }

  public DbColuna getClnNome() {

    try {

      if (_clnNome == null) {
        _clnNome = this.getClnChavePrimaria();
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _clnNome;
  }

  public DbColuna getClnOrdemCadastro() {

    try {

      if (_clnOrdemCadastro == null) {
        _clnOrdemCadastro = this.getClnChavePrimaria();
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
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

    List<DbColuna> lstCln = null;

    try {

      lstCln = new ArrayList<DbColuna>();
      lstCln.add(cln);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return this.getCrsDados(lstCln, null);
  }

  public Cursor getCrsDados(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {

    List<DbColuna> lstCln = null;

    try {

      lstCln = new ArrayList<DbColuna>();
      lstCln.add(cln);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return this.getCrsDados(lstCln, lstObjDbFiltro);
  }

  public Cursor getCrsDados(DbColuna clnFiltro, String strFiltro) {

    Cursor crsResultado = null;
    ArrayList<DbFiltro> lstObjDbFiltro;

    try {

      lstObjDbFiltro = new ArrayList<DbFiltro>();
      lstObjDbFiltro.add(new DbFiltro(clnFiltro, strFiltro));
      crsResultado = this.getCrsDados(lstObjDbFiltro);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(128), ex);

    }
    finally {
    }
    return crsResultado;
  }

  public Cursor getCrsDados(List<DbColuna> lstCln, List<DbFiltro> lstObjDbFiltro) {

    boolean booPrimeiroTermo = true;

    Cursor crsResultado = null;

    String sql;
    String strClnNome = Utils.STRING_VAZIA;
    String strFiltro = Utils.STRING_VAZIA;
    String strClnOrdemNome;

    try {

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

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(128), ex);

    }
    finally {
    }

    return crsResultado;
  }

  public Cursor getCrsDados(List<DbFiltro> lstObjDbFiltro) {

    return this.getCrsDados(this.getLstCln(), lstObjDbFiltro);
  }

  public Cursor getCrsDadosTelaCadastro() {

    boolean booPrimeiroTermo = true;

    Cursor crsResultado = null;

    int intNumeroColuna = 0;

    String sql;
    String strClnNome;
    String strFiltro = Utils.STRING_VAZIA;
    String strClnOrdemNome;

    try {

      if (this.getLstDbFiltroTelaCadastro().size() > 0) {

        for (DbFiltro objDbFiltro : this.getLstDbFiltroTelaCadastro()) {

          strFiltro += objDbFiltro.getStrFiltroFormatado(booPrimeiroTermo);
          booPrimeiroTermo = false;
        }

        this.getLstDbFiltroTelaCadastro().clear();
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

      }
      else {

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

          }
          else {

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
      }
      else {
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

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(128), ex);

    }
    finally {
    }

    return crsResultado;
  }

  public int getIntQtdLinha() {

    String sql;

    try {

      sql = "SELECT COUNT(" + this.getClnChavePrimaria().getStrNomeSimplificado() + ") FROM " + this.getStrNomeSimplificado() + ";";
      _intQtdLinha = this.getObjDataBase().execSqlGetInt(sql);

    }
    catch (Exception ex) {

      _intQtdLinha = 0;

    }
    finally {
    }

    return _intQtdLinha;
  }

  public List<DbColuna> getLstCln() {

    try {

      if (_lstCln == null) {
        _lstCln = new ArrayList<DbColuna>();
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _lstCln;
  }

  public ArrayList<DbFiltro> getLstDbFiltroTelaCadastro() {

    try {

      if (_lstDbFiltroTelaCadastro == null) {
        _lstDbFiltroTelaCadastro = new ArrayList<DbFiltro>();
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _lstDbFiltroTelaCadastro;
  }

  public List<Integer> getLstInt(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {

    List<Integer> lstIntResultado = null;
    List<String> lstStr;

    try {

      lstStr = this.getLstStr(cln, lstObjDbFiltro);

      if (lstStr != null && !lstStr.isEmpty()) {

        lstIntResultado = new ArrayList<Integer>();
        for (String str : lstStr) {

          lstIntResultado.add(Integer.valueOf(str));
        }
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return lstIntResultado;
  }

  public List<ItmConsulta> getLstItmConsulta() {

    Cursor crs;
    ItmConsulta itmConsulta;

    try {

      if (_lstItmConsulta != null && this.getBooItmConsultaUsarCache()) {
        return _lstItmConsulta;
      }

      _lstItmConsulta = new ArrayList<ItmConsulta>();
      crs = this.getCrsDadosTelaCadastro();

      if (crs == null || !crs.moveToFirst()) {
        return _lstItmConsulta;
      }

      do {

        itmConsulta = new ItmConsulta();
        itmConsulta.setStrItemId(crs.getString(crs.getColumnIndex(this.getClnChavePrimaria().getStrNomeSimplificado())));

        if (this.getClnNome().getClnReferencia() != null) {
          itmConsulta.setStrNome(crs.getString(crs.getColumnIndex("_strNomeB")));
        }
        else {
          itmConsulta.setStrNome(crs.getString(crs.getColumnIndex(this.getClnNome().getStrNomeSimplificado())));
        }

        for (int intColunaIndex = 0; intColunaIndex <= 4; intColunaIndex++) {

          if (intColunaIndex < crs.getColumnCount()) {

            switch (intColunaIndex) {
              case 2:
                itmConsulta.setStrCampo001Nome(this.getStrClnNomePeloNomeSimplificado(crs.getColumnName(intColunaIndex)));
                itmConsulta.setStrCampo001Valor(crs.getString(intColunaIndex));
                break;
              case 3:
                itmConsulta.setStrCampo002Nome(this.getStrClnNomePeloNomeSimplificado(crs.getColumnName(intColunaIndex)));
                itmConsulta.setStrCampo002Valor(crs.getString(intColunaIndex));
                break;
              case 4:
                itmConsulta.setStrCampo003Nome(this.getStrClnNomePeloNomeSimplificado(crs.getColumnName(intColunaIndex)));
                itmConsulta.setStrCampo003Valor(crs.getString(intColunaIndex));
                break;
            }
          }
        }

        _lstItmConsulta.add(itmConsulta);

      }
      while (crs.moveToNext());

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _lstItmConsulta;
  }

  public List<String> getLstStr(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {

    Cursor crs;
    List<String> lstStrResultado = null;

    try {

      crs = this.getCrsDados(cln, lstObjDbFiltro);

      if (crs != null && crs.moveToFirst()) {

        lstStrResultado = new ArrayList<String>();
        do {

          lstStrResultado.add(crs.getString(0));
        }
        while (crs.moveToNext());
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return lstStrResultado;
  }

  public DataBase getObjDataBase() {

    try {

      if (_objDataBase == null) {
        this._objDataBase = App.getI().getObjDataBasePrincipal();
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return _objDataBase;
  }

  public String getStrClnNomePeloNomeSimplificado(String strNomeSimplificado) {

    String strColunaNome = Utils.STRING_VAZIA;

    try {

      for (DbColuna cln : this.getLstCln()) {

        if (cln.getStrNomeSimplificado().equals(strNomeSimplificado)) {
          strColunaNome = cln.getStrNomeExibicao();
          break;
        }
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(128), ex);

    }
    finally {
    }
    return strColunaNome;
  }

  public String getStrPesquisaActConsulta() {

    return _strPesquisaActConsulta;
  }

  protected int inicializarColunas(int intOrdem) {

    try {

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(128), ex);

    }
    finally {
    }

    return intOrdem;
  }

  public void inserir() {

    String strId;
    String strColunasNomes = Utils.STRING_VAZIA;
    String strColunasValores = Utils.STRING_VAZIA;
    String sql;

    try {

      this.limparListaConsulta();

      for (DbColuna cln : this.getLstCln()) {

        if (!Utils.getBooStrVazia(cln.getStrValor())) {

          strColunasNomes += cln.getStrNomeSimplificado() + ",";
          strColunasValores += "'" + cln.getStrValor() + "',";

        }
        else if (!Utils.getBooStrVazia(cln.getStrValorDefault())) {

          strColunasNomes += cln.getStrNomeSimplificado() + ",";
          strColunasValores += "'" + cln.getStrValorDefault() + "',";
        }
      }

      strColunasNomes = Utils.removerUltimaLetra(strColunasNomes);
      strColunasValores = Utils.removerUltimaLetra(strColunasValores);

      sql = "REPLACE INTO " + this.getStrNomeSimplificado() + " (" + strColunasNomes + ") VALUES (" + strColunasValores + ");";

      this.getObjDataBase().execSqlSemRetorno(sql);

      if (Utils.getBooStrVazia(this.getClnChavePrimaria().getStrValor())) {

        sql = "SELECT last_insert_rowid();";
        strId = this.getObjDataBase().execSqlGetStr(sql);

      }
      else {
        strId = this.getClnChavePrimaria().getStrValor();
      }

      this.buscarRegistroPelaChavePrimaria(strId);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(129), ex);

    }
    finally {
    }
  }

  public void inserirAleatorio() {

    try {

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

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(130), ex);

    }
    finally {
    }
  }

  public void limparCampos() {

    this.zerarCampos();
  }

  public void limparListaConsulta() {

    try {

      this.setLstItmConsulta(null);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }
  }

  public void salvar() {

    this.inserir();
  }

  protected void setBooItmConsultaUsarCache(boolean booItmConsultaUsarCache) {

    _booItmConsultaUsarCache = booItmConsultaUsarCache;
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

    try {

      if (_clnNome == null) {
        _clnNome = this.getClnChavePrimaria();
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
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

    try {

      for (DbColuna cln : this.getLstCln()) {
        cln.setStrValor(null);
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(130), ex);

    }
    finally {
    }
  }

}
