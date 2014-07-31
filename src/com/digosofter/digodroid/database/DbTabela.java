package com.digosofter.digodroid.database;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.Objeto;
import com.digosofter.digodroid.Util;
import com.digosofter.digodroid.Util.EnmStrTipo;
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.database.DbColuna.EnmTipo;
import com.digosofter.digodroid.erro.Erro;
import com.digosofter.digodroid.item.ItmConsulta;

public abstract class DbTabela extends Objeto {

  private boolean _booPermitirCadastroNovo;
  private boolean _booSinc = true;
  private DbColuna _clnChavePrimaria;
  private DbColuna _clnNome;
  private DbColuna _clnOrdemCadastro;
  private Class<? extends ActMain> _clsActCadastro;
  private int _intQtdLinha;
  private List<DbColuna> _lstCln;
  private List<DbColuna> _lstClnCadastro;
  private ArrayList<DbFiltro> _lstDbFiltroTelaCadastro;
  private List<ItmConsulta> _lstItmConsulta;
  private DataBase _objDb;
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

  public void abrirActCadastro(Activity actPai) {

    Intent objIntent;
    try {
      App.getI().setTblSelec(this);
      objIntent = new Intent(actPai, ActConsulta.class);
      actPai.startActivityForResult(objIntent, 0);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(123), ex);
    }
    finally {
    }
  }

  public void buscarReg(DbColuna clnFiltro, int intFiltro) {

    this.buscarReg(clnFiltro, String.valueOf(intFiltro));
  }

  public void buscarReg(DbColuna clnFiltro, String strFiltro) {

    List<DbFiltro> lstObjDbFiltro;
    try {
      lstObjDbFiltro = new ArrayList<DbFiltro>();
      lstObjDbFiltro.add(new DbFiltro(clnFiltro, strFiltro));
      this.buscarReg(lstObjDbFiltro);
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void buscarReg(int intId) {

    this.buscarReg(this.getClnChavePrimaria(), intId);
  }

  public void buscarReg(List<DbFiltro> lstObjDbFiltro) {

    String sql;
    Cursor crs;
    try {
      sql = "select _clns_nome from _tbl_nome where _where order by _tbl_nome._order;";
      sql = sql.replace("_clns_nome", this.getSqlColunasNomes());
      sql = sql.replace("_tbl_nome", this.getStrNomeSimplificado());
      sql = sql.replace("_where", this.getSqlWhere(lstObjDbFiltro));
      sql = sql.replace("_order", this.getClnChavePrimaria().getStrNomeSimplificado());
      crs = this.getObjDb().execSqlComRetorno(sql);
      this.carregarDados(crs);
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void buscarReg(String strId) {

    this.buscarReg(this.getClnChavePrimaria(), strId);
  }

  private void carregarDados(Cursor crs) {

    try {
      if (crs == null || !crs.moveToFirst()) {
        return;
      }
      this.limparColunas();
      for (DbColuna cln : this.getLstCln()) {
        cln.setStrValor(crs.getString(crs.getColumnIndex(cln.getStrNomeSimplificado())));
      }
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void criarTabela() {

    String sql;
    try {
      if (this.getBooTblExiste()) {
        return;
      }
      sql = "create table if not exists _tbl_nome(_clns);";
      sql = sql.replace("_tbl_nome", this.getStrNomeSimplificado());
      sql = sql.replace("_clns", this.getSqlColunasNomesCreateTable());
      this.getObjDb().execSqlSemRetorno(sql);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(124), ex);
    }
    finally {
    }
  }

  public void excluir(int intId) {

    this.excluir(String.valueOf(intId));
  }

  public void excluir(String strId) {

    String sql;
    try {
      sql = "delete from _tbl_nome where _tbl_nome._cln_nome='_id';";
      sql = sql.replace("_tbl_nome", this.getStrNomeSimplificado());
      sql = sql.replace("_cln_nome", this.getClnChavePrimaria().getStrNomeExibicao());
      sql = sql.replace("_id", strId);
      this.getObjDb().execSqlSemRetorno(sql);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(126), ex);
    }
    finally {
    }
  }

  public boolean getBooPermitirCadastroNovo() {

    return _booPermitirCadastroNovo;
  }

  public boolean getBooSinc() {

    return _booSinc;
  }

  private boolean getBooTblExiste() {

    boolean booResultado = false;
    Cursor crs;
    String sql;
    try {
      sql = "select name from sqlite_master where type='table' and name='_tbl_nome';";
      sql = sql.replace("_tbl_nome", this.getStrNomeSimplificado());
      crs = this.getObjDb().execSqlComRetorno(sql);
      booResultado = crs != null && crs.moveToFirst() && crs.getCount() > 0;
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
      if (_clnChavePrimaria != null) {
        return _clnChavePrimaria;
      }
      _clnChavePrimaria = this.getLstCln().get(0);
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
      if (_clnNome != null) {
        return _clnNome;
      }
      _clnNome = this.getClnChavePrimaria();
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
      if (_clnOrdemCadastro != null) {
        return _clnOrdemCadastro;
      }
      _clnOrdemCadastro = this.getClnChavePrimaria();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _clnOrdemCadastro;
  }

  public Class<? extends ActMain> getClsActCadastro() {

    return _clsActCadastro;
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

    Cursor crsResultado = null;
    String sql;
    try {
      sql = "select _clns_nome from _tbl_nome where _where order by _tbl_nome._order;";
      sql = sql.replace("_clns_nome", this.getSqlSelectColunasNomes(lstCln));
      sql = sql.replace("_tbl_nome", this.getStrNomeSimplificado());
      sql = sql.replace("where _where",
          lstObjDbFiltro != null && lstObjDbFiltro.size() > 0 ? "where _where" : Util.STR_VAZIA);
      sql = sql.replace("_where", this.getSqlWhere(lstObjDbFiltro));
      sql = sql.replace("_order", this.getClnOrdemCadastro().getStrNomeSimplificado());
      crsResultado = this.getObjDb().execSqlComRetorno(sql);
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

    Cursor crsResultado = null;
    String sql;
    try {
      sql = "select _clns_nome from _tbl_nome where _where order by _tbl_nome._order;";
      sql = sql.replace("_clns_nome", this.getSqlSelectColunasNomesCadastro());
      sql = sql.replace("_tbl_nome", this.getStrNomeSimplificado());
      sql = sql.replace("where _where", this.getLstDbFiltroTelaCadastro() != null
          && this.getLstDbFiltroTelaCadastro().size() > 0 ? "where _where" : Util.STR_VAZIA);
      sql = sql.replace("_where", this.getSqlWhere(this.getLstDbFiltroTelaCadastro()));
      sql = sql.replace("_order", this.getClnOrdemCadastro().getStrNomeSimplificado());
      crsResultado = this.getObjDb().execSqlComRetorno(sql);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(128), ex);
    }
    finally {
    }
    return crsResultado;
  }

  private String getSqlSelectColunasNomesCadastro() {

    String strResultado = null;
    String str;
    try {
      strResultado = Util.STR_VAZIA;
      for (DbColuna cln : this.getLstClnCadastro()) {
        if (cln.getClnRef() == null) {
          str = "_tbl_nome._cln_nome,";
          str = str.replace("_tbl_nome", cln.getTbl().getStrNomeSimplificado());
          str = str.replace("_cln_nome", cln.getStrNomeSimplificado());
          strResultado += str;
          continue;
        }
        str = "(select _tbl_ref_nome._cln_ref_nome from _tbl_ref_nome where _tbl_ref_nome._cln_ref_pk=_tbl_nome._cln_nome) _cln_nome,";
        str = str.replace("_tbl_ref_nome", cln.getClnRef().getTbl().getStrNomeSimplificado());
        str = str.replace("_cln_ref_nome", cln.getClnRef().getTbl().getClnNome()
            .getStrNomeSimplificado());
        str = str.replace("_cln_ref_pk", cln.getClnRef().getTbl().getClnChavePrimaria()
            .getStrNomeSimplificado());
        str = str.replace("_tbl_nome", cln.getTbl().getStrNomeSimplificado());
        str = str.replace("_cln_nome", cln.getStrNomeSimplificado());
      }
      strResultado = Util.removerUltimaLetra(strResultado);
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return strResultado;
  }

  public int getIntQtdLinha() {

    String sql;
    try {
      sql = "select count(1) from _tbl_nome;";
      sql = sql.replace("_tbl_nome", this.getStrNomeSimplificado());
      _intQtdLinha = this.getObjDb().execSqlGetInt(sql);
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
      if (_lstCln != null) {
        return _lstCln;
      }
      _lstCln = new ArrayList<DbColuna>();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _lstCln;
  }

  public List<DbColuna> getLstClnCadastro() {

    try {
      if (_lstClnCadastro != null) {
        return _lstClnCadastro;
      }
      _lstClnCadastro = new ArrayList<DbColuna>();
      for (DbColuna cln : this.getLstCln()) {
        if (!cln.getBooVisivelCadastro() && !cln.getBooChavePrimaria()) {
          continue;
        }
        _lstClnCadastro.add(cln);
      }
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return _lstClnCadastro;
  }

  public ArrayList<DbFiltro> getLstDbFiltroTelaCadastro() {

    try {
      if (_lstDbFiltroTelaCadastro != null) {
        return _lstDbFiltroTelaCadastro;
      }
      _lstDbFiltroTelaCadastro = new ArrayList<DbFiltro>();
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
      lstIntResultado = new ArrayList<Integer>();
      if (lstStr == null || lstStr.isEmpty()) {
        return lstIntResultado;
      }
      for (String str : lstStr) {
        lstIntResultado.add(Integer.valueOf(str));
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
      if (_lstItmConsulta != null) {
        return _lstItmConsulta;
      }
      _lstItmConsulta = new ArrayList<ItmConsulta>();
      crs = this.getCrsDadosTelaCadastro();
      if (crs == null || !crs.moveToFirst()) {
        return _lstItmConsulta;
      }
      do {
        itmConsulta = new ItmConsulta();
        itmConsulta.setStrItemId(crs.getString(crs.getColumnIndex(this.getClnChavePrimaria()
            .getStrNomeSimplificado())));
        itmConsulta.setStrNome(crs.getString(crs.getColumnIndex(this.getClnNome()
            .getStrNomeSimplificado())));
        itmConsulta.setStrCampo1Nome(this.getLstClnCadastro().get(0).getStrNomeExibicao());
        itmConsulta.setStrCampo1Valor(crs.getString(crs.getColumnIndex(this.getLstClnCadastro()
            .get(0).getStrNomeSimplificado())));
        itmConsulta.setStrCampo2Nome(this.getLstClnCadastro().get(1).getStrNomeExibicao());
        itmConsulta.setStrCampo2Valor(crs.getString(crs.getColumnIndex(this.getLstClnCadastro()
            .get(1).getStrNomeSimplificado())));
        itmConsulta.setStrCampo3Nome(this.getLstClnCadastro().get(2).getStrNomeExibicao());
        itmConsulta.setStrCampo3Valor(crs.getString(crs.getColumnIndex(this.getLstClnCadastro()
            .get(2).getStrNomeSimplificado())));
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
      lstStrResultado = new ArrayList<String>();
      if (crs == null || !crs.moveToFirst()) {
        return lstStrResultado;
      }
      do {
        lstStrResultado.add(crs.getString(0));
      }
      while (crs.moveToNext());
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return lstStrResultado;
  }

  public DataBase getObjDb() {

    try {
      if (_objDb != null) {
        return _objDb;
      }
      _objDb = App.getI().getObjDbPrincipal();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _objDb;
  }

  private String getSqlColunasNomes() {

    String strResultado = null;
    String str;
    try {
      strResultado = Util.STR_VAZIA;
      if (this.getLstCln() == null || this.getLstCln().isEmpty()) {
        return "*";
      }
      for (DbColuna cln : this.getLstCln()) {
        str = "_tbl_nome._cln_nome,";
        str = str.replace("_tbl_nome", cln.getTbl().getStrNomeSimplificado());
        str = str.replace("_cln_nome", cln.getStrNomeSimplificado());
        strResultado += str;
      }
      strResultado = Util.removerUltimaLetra(strResultado);
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return strResultado;
  }

  private String getSqlColunasNomesCreateTable() {

    String strResultado = null;
    String str;
    try {
      strResultado = Util.STR_VAZIA;
      for (DbColuna cln : this.getLstCln()) {
        str = "_cln_nome _cln_tipo _pk default _default,";
        str = str.replace("_cln_nome", cln.getStrNomeSimplificado());
        str = str.replace("_cln_tipo", cln.getSqlTipo());
        str = str.replace("_pk", cln.getBooChavePrimaria() ? "primary key autoincrement"
            : Util.STR_VAZIA);
        str = str.replace("autoincrement", cln.getEnmTipo() != EnmTipo.TEXT ? "autoincrement"
            : Util.STR_VAZIA);
        str = str.replace(" default _default",
            !Util.getBooStrVazia(cln.getStrValorDefault()) ? " default _default" : Util.STR_VAZIA);
        str = str.replace("_default", cln.getStrValorDefault());
        strResultado += str;
      }
      strResultado = Util.removerUltimaLetra(strResultado);
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return strResultado;
  }

  private String getSqlSelectColunasNomes(List<DbColuna> lstCln) {

    String strResultado = null;
    String str;
    try {
      strResultado = Util.STR_VAZIA;
      if (lstCln == null || lstCln.isEmpty()) {
        return "*";
      }
      for (DbColuna cln : lstCln) {
        str = "_tbl_nome._cln_nome,";
        str = str.replace("_tbl_nome", cln.getTbl().getStrNomeSimplificado());
        str = str.replace("_cln_nome", cln.getStrNomeSimplificado());
        strResultado += str;
      }
      strResultado = Util.removerUltimaLetra(strResultado);
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return strResultado;
  }

  private String getSqlWhere(List<DbFiltro> lstObjDbFiltro) {

    String strResultado = null;
    String str;
    boolean booPrimeiroTermo;
    try {
      booPrimeiroTermo = true;
      strResultado = Util.STR_VAZIA;
      for (DbFiltro fil : lstObjDbFiltro) {
        str = "_filtro ";
        str = str.replace("_filtro", fil.getSqlFiltro(booPrimeiroTermo));
        strResultado += str;
        booPrimeiroTermo = false;
      }
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return strResultado;
  }

  public String getStrClnNome(String strNomeSimplificado) {

    String strColunaNome = Util.STR_VAZIA;
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

  public void salvar() {

    String strId;
    String sql;
    try {
      this.limparListaConsulta();
      sql = "replace into _tbl_nome (_clns_nome) values (_values);";
      sql = sql.replace("_tbl_nome", this.getStrNomeSimplificado());
      sql = sql.replace("_clns_nome", this.getSqlColunasNomesInsert());
      sql = sql.replace("_values", this.getSqlColunasValoresInsert());
      this.getObjDb().execSqlSemRetorno(sql);
      if (Util.getBooStrVazia(this.getClnChavePrimaria().getStrValor())) {
        sql = "SELECT last_insert_rowid();";
        strId = this.getObjDb().execSqlGetStr(sql);
      }
      else {
        strId = this.getClnChavePrimaria().getStrValor();
      }
      this.buscarReg(strId);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(129), ex);
    }
    finally {
    }
  }

  private String getSqlColunasValoresInsert() {

    String strResultado = null;
    String str;
    try {
      for (DbColuna cln : this.getLstCln()) {
        if (Util.getBooStrVazia(cln.getStrValor()) && Util.getBooStrVazia(cln.getStrValorDefault())) {
          continue;
        }
        str = "'_cln_valor',";
        str = str.replace("_cln_valor", Util.getBooStrVazia(cln.getStrValor()) ? cln.getStrValor()
            : cln.getStrValorDefault());
        strResultado += str;
      }
      strResultado = Util.removerUltimaLetra(strResultado);
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return strResultado;
  }

  private String getSqlColunasNomesInsert() {

    String strResultado = null;
    String str;
    try {
      for (DbColuna cln : this.getLstCln()) {
        if (Util.getBooStrVazia(cln.getStrValor()) && Util.getBooStrVazia(cln.getStrValorDefault())) {
          continue;
        }
        str = "_cln_nome,";
        str = str.replace("_cln_nome", cln.getStrNomeSimplificado());
        strResultado += str;
      }
      strResultado = Util.removerUltimaLetra(strResultado);
    }
    catch (Exception ex) {
      new Erro("Erro inesperado.\n", ex);
    }
    finally {
    }
    return strResultado;
  }

  public void inserirAleatorio() {

    try {
      for (DbColuna cln : this.getLstCln()) {
        switch (cln.getEnmTipo()) {
          case INTEGER:
            cln.setStrValor(Util.getStrAleatoria(5, EnmStrTipo.NUMERICO));
            break;
          case REAL:
            cln.setStrValor(Util.getStrAleatoria(5, EnmStrTipo.NUMERICO));
            break;
          case NUMERIC:
            cln.setStrValor(Util.getStrAleatoria(5, EnmStrTipo.NUMERICO));
            break;
          default:
            cln.setStrValor(Util.getStrAleatoria(5, EnmStrTipo.ALPHA));
            break;
        }
      }
      this.salvar();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(130), ex);
    }
    finally {
    }
  }

  public void limparColunas() {

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

  protected void setBooPermitirCadastroNovo(boolean booPermitirCadastroNovo) {

    _booPermitirCadastroNovo = booPermitirCadastroNovo;
  }

  public void setBooSinc(boolean booSinc) {

    _booSinc = booSinc;
  }

  public void setClnChavePrimaria(DbColuna clnChavePrimaria) {

    _clnChavePrimaria = clnChavePrimaria;
  }

  public void setClnNome(DbColuna clnNome) {

    _clnNome = clnNome;
  }

  public void setClnOrdemCadastro(DbColuna clnOrdemCadastro) {

    _clnOrdemCadastro = clnOrdemCadastro;
  }

  protected void setClsActCadastro(Class<? extends ActMain> clsActFrm) {

    _clsActCadastro = clsActFrm;
  }

  public void setLstCln(List<DbColuna> lstCln) {

    _lstCln = lstCln;
  }

  private void setLstItmConsulta(List<ItmConsulta> lstItmConsulta) {

    _lstItmConsulta = lstItmConsulta;
  }

  public void setObjDb(DataBase objDb) {

    _objDb = objDb;
  }

  public void setStrPesquisaActConsulta(String strPesquisaActConsulta) {

    _strPesquisaActConsulta = strPesquisaActConsulta;
  }
}
