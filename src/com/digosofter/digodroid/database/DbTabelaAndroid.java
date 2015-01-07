package com.digosofter.digodroid.database;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digodroid.item.ItmConsulta;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.Utils.EnmStrTipo;
import com.digosofter.digojava.database.DbColuna;
import com.digosofter.digojava.database.DbColuna.EnmTipo;
import com.digosofter.digojava.database.DbFiltro;
import com.digosofter.digojava.database.DbTabela;

public abstract class DbTabelaAndroid extends DbTabela {

  private boolean _booSinc = true;
  private Class<? extends ActMain> _clsActCadastro;
  private List<ItmConsulta> _lstItmConsulta;
  private DataBaseAndroid _objDb;

  public DbTabelaAndroid(String strNome) {

    super(strNome);

    try {

      this.criarTabela();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(122), ex);
    }
    finally {
    }
  }

  public void abrirActCadastro(Activity actPai) {

    Intent objIntent;

    try {

      AppAndroid.getI().setTblSelec(this);

      objIntent = new Intent(actPai, ActConsulta.class);
      actPai.startActivityForResult(objIntent, 0);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(123), ex);
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

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void buscarReg(int intId) {

    this.buscarReg(this.getClnChavePrimaria(), intId);
  }

  public void buscarReg(List<DbFiltro> lstObjDbFiltro) {

    Cursor crs;
    String sql;

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

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void buscarReg(String strId) {

    this.buscarReg(this.getClnChavePrimaria(), strId);
  }

  private void carregarDados(Cursor crs) {

    try {

      this.limparColunas();

      if (crs == null || !crs.moveToFirst()) {

        return;
      }

      for (DbColuna cln : this.getLstCln()) {

        cln.setStrValor(crs.getString(crs.getColumnIndex(cln.getStrNomeSimplificado())));
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void criarTabela() {

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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(124), ex);
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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(126), ex);
    }
    finally {
    }
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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(127), ex);
    }
    finally {

      crs = null;
    }

    return booResultado;
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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return this.getCrsDados(lstCln, lstObjDbFiltro);
  }

  public Cursor getCrsDados(DbColuna clnFiltro, String strFiltro) {

    ArrayList<DbFiltro> lstObjDbFiltro;
    Cursor crsResultado = null;

    try {

      lstObjDbFiltro = new ArrayList<DbFiltro>();

      lstObjDbFiltro.add(new DbFiltro(clnFiltro, strFiltro));

      crsResultado = this.getCrsDados(lstObjDbFiltro);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(128), ex);
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
      sql = sql.replace("where _where", lstObjDbFiltro != null && lstObjDbFiltro.size() > 0 ? "where _where" : Utils.STR_VAZIA);
      sql = sql.replace("_where", this.getSqlWhere(lstObjDbFiltro));
      sql = sql.replace("_order", this.getClnOrdemCadastro().getStrNomeSimplificado());

      crsResultado = this.getObjDb().execSqlComRetorno(sql);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(128), ex);
    }
    finally {
    }

    return crsResultado;
  }

  public Cursor getCrsDados(List<DbFiltro> lstObjDbFiltro) {

    return this.getCrsDados(this.getLstCln(), lstObjDbFiltro);
  }

  public Cursor getCrsDadosTelaConsulta() {

    Cursor crsResultado = null;
    String sql;

    try {

      sql = "select _clns_nome from _tbl_nome where _where order by _tbl_nome._order;";

      sql = sql.replace("_clns_nome", this.getSqlSelectColunasNomesConsulta());
      sql = sql.replace("_tbl_nome", this.getStrNomeSimplificado());
      sql = sql.replace("where _where", this.getLstDbFiltroTelaCadastro() != null && this.getLstDbFiltroTelaCadastro().size() > 0 ? "where _where" : Utils.STR_VAZIA);
      sql = sql.replace("_where", this.getSqlWhere(this.getLstDbFiltroTelaCadastro()));
      sql = sql.replace("_order", this.getClnOrdemCadastro().getStrNomeSimplificado());

      crsResultado = this.getObjDb().execSqlComRetorno(sql);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(128), ex);
    }
    finally {
    }

    return crsResultado;
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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
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

      crs = this.getCrsDadosTelaConsulta();

      if (crs == null || !crs.moveToFirst()) {

        return _lstItmConsulta;
      }

      do {

        itmConsulta = new ItmConsulta();

        itmConsulta.setStrItemId(crs.getString(crs.getColumnIndex(this.getClnChavePrimaria().getStrNomeSimplificado())));
        itmConsulta.setStrNome(crs.getString(crs.getColumnIndex(this.getClnNome().getStrNomeSimplificado())));
        itmConsulta.setStrCampo1Nome(this.getLstClnConsulta().get(2).getStrNomeExibicao());
        itmConsulta.setStrCampo1Valor(crs.getString(crs.getColumnIndex(this.getLstClnConsulta().get(2).getStrNomeSimplificado())));
        itmConsulta.setStrCampo2Nome(this.getLstClnConsulta().get(3).getStrNomeExibicao());
        itmConsulta.setStrCampo2Valor(crs.getString(crs.getColumnIndex(this.getLstClnConsulta().get(3).getStrNomeSimplificado())));
        itmConsulta.setStrCampo3Nome(this.getLstClnConsulta().get(4).getStrNomeExibicao());
        itmConsulta.setStrCampo3Valor(crs.getString(crs.getColumnIndex(this.getLstClnConsulta().get(4).getStrNomeSimplificado())));

        _lstItmConsulta.add(itmConsulta);
      }
      while (crs.moveToNext());
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
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

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return lstStrResultado;
  }

  @Override
  public DataBaseAndroid getObjDb() {

    try {

      if (_objDb != null) {

        return _objDb;
      }

      _objDb = AppAndroid.getI().getObjDbPrincipal();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _objDb;
  }

  private String getSqlColunasNomes() {

    String strResultado = null;
    String str;

    try {

      if (this.getLstCln() == null || this.getLstCln().isEmpty()) {

        return "*";
      }

      strResultado = Utils.STR_VAZIA;

      for (DbColuna cln : this.getLstCln()) {

        str = "_tbl_nome._cln_nome, ";

        str = str.replace("_tbl_nome", cln.getTbl().getStrNomeSimplificado());
        str = str.replace("_cln_nome", cln.getStrNomeSimplificado());

        strResultado += str;
      }

      strResultado = Utils.removerUltimaLetra(strResultado, 2);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return strResultado;
  }

  private String getSqlColunasNomesCreateTable() {

    String strResultado = null;
    String str;

    try {

      strResultado = Utils.STR_VAZIA;

      for (DbColuna cln : this.getLstCln()) {

        str = "_cln_nome _cln_tipo _pk default _default, ";

        str = str.replace("_cln_nome", cln.getStrNomeSimplificado());
        str = str.replace("_cln_tipo", cln.getSqlTipo());
        str = str.replace("_pk", cln.getBooChavePrimaria() ? "primary key autoincrement" : Utils.STR_VAZIA);
        str = str.replace("autoincrement", cln.getEnmTipo() != EnmTipo.TEXT ? "autoincrement" : Utils.STR_VAZIA);
        str = str.replace(" default _default", !Utils.getBooStrVazia(cln.getStrValorDefault()) ? " default _default" : Utils.STR_VAZIA);
        str = str.replace("_default", cln.getStrValorDefault());

        strResultado += str;
      }

      strResultado = Utils.removerUltimaLetra(strResultado, 2);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
    return strResultado;
  }

  private String getSqlColunasNomesInsert() {

    String strResultado = null;
    String str;

    try {

      strResultado = Utils.STR_VAZIA;

      for (DbColuna cln : this.getLstCln()) {

        if (Utils.getBooStrVazia(cln.getStrValor())) {

          continue;
        }

        if (cln.getClnRef() != null && "0".equals(cln.getStrValor())) {

          continue;
        }

        str = "_cln_nome, ";
        str = str.replace("_cln_nome", cln.getStrNomeSimplificado());

        strResultado += str;
      }

      strResultado = Utils.removerUltimaLetra(strResultado, 2);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return strResultado;
  }

  private String getSqlColunasValoresInsert() {

    String strResultado = null;
    String str;

    try {

      strResultado = Utils.STR_VAZIA;

      for (DbColuna cln : this.getLstCln()) {

        if (Utils.getBooStrVazia(cln.getStrValor())) {

          continue;
        }

        if (cln.getClnRef() != null && "0".equals(cln.getStrValor())) {

          continue;
        }

        str = "'_cln_valor', ";
        str = str.replace("_cln_valor", cln.getStrValorSql());

        strResultado += str;
      }

      strResultado = Utils.removerUltimaLetra(strResultado, 2);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return strResultado;
  }

  private String getSqlSelectColunasNomes(List<DbColuna> lstCln) {

    String strResultado = null;
    String str;

    try {

      if (lstCln == null || lstCln.isEmpty()) {

        return "*";
      }

      strResultado = Utils.STR_VAZIA;

      for (DbColuna cln : lstCln) {

        str = "_tbl_nome._cln_nome, ";
        str = str.replace("_tbl_nome", cln.getTbl().getStrNomeSimplificado());
        str = str.replace("_cln_nome", cln.getStrNomeSimplificado());

        strResultado += str;
      }

      strResultado = Utils.removerUltimaLetra(strResultado, 2);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return strResultado;
  }

  private String getSqlSelectColunasNomesConsulta() {

    String strResultado = null;
    String str;

    try {

      strResultado = Utils.STR_VAZIA;

      for (DbColuna cln : this.getLstClnConsulta()) {

        if (cln.getClnRef() == null) {

          str = "_tbl_nome._cln_nome, ";

          str = str.replace("_tbl_nome", cln.getTbl().getStrNomeSimplificado());
          str = str.replace("_cln_nome", cln.getStrNomeSimplificado());

          strResultado += str;

          continue;
        }

        str = "(select _tbl_ref_nome._cln_ref_nome from _tbl_ref_nome where _tbl_ref_nome._cln_ref_pk = _tbl_nome._cln_nome) _cln_nome, ";

        str = str.replace("_tbl_ref_nome", cln.getClnRef().getTbl().getStrNomeSimplificado());
        str = str.replace("_cln_ref_nome", cln.getClnRef().getTbl().getClnNome().getStrNomeSimplificado());
        str = str.replace("_cln_ref_pk", cln.getClnRef().getTbl().getClnChavePrimaria().getStrNomeSimplificado());
        str = str.replace("_tbl_nome", cln.getTbl().getStrNomeSimplificado());
        str = str.replace("_cln_nome", cln.getStrNomeSimplificado());

        strResultado += str;
      }

      strResultado = Utils.removerUltimaLetra(strResultado, 2);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return strResultado;
  }

  private String getSqlWhere(List<DbFiltro> lstObjDbFiltro) {

    boolean booPrimeiroTermo;
    String str;
    String strResultado = null;

    try {

      if (lstObjDbFiltro == null || lstObjDbFiltro.isEmpty()) {

        return Utils.STR_VAZIA;
      }

      booPrimeiroTermo = true;
      strResultado = Utils.STR_VAZIA;

      for (DbFiltro fil : lstObjDbFiltro) {

        str = "_filtro ";

        str = str.replace("_filtro", fil.getSqlFiltro(booPrimeiroTermo));

        strResultado += str;

        booPrimeiroTermo = false;
      }

      strResultado = Utils.removerUltimaLetra(strResultado);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
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
            cln.setStrValor(Utils.getStrAleatoria(5, EnmStrTipo.NUMERICO));
            break;
          case REAL:
            cln.setStrValor(Utils.getStrAleatoria(5, EnmStrTipo.NUMERICO));
            break;
          case NUMERIC:
            cln.setStrValor(Utils.getStrAleatoria(5, EnmStrTipo.NUMERICO));
            break;
          default:
            cln.setStrValor(Utils.getStrAleatoria(5, EnmStrTipo.ALPHA));
            break;
        }
      }

      this.salvar();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(130), ex);
    }
    finally {
    }
  }

  public void limparListaConsulta() {

    try {

      this.setLstItmConsulta(null);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
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

      if (Utils.getBooStrVazia(this.getClnChavePrimaria().getStrValor())) {

        sql = "SELECT last_insert_rowid();";
        strId = this.getObjDb().execSqlGetStr(sql);
      }
      else {

        strId = this.getClnChavePrimaria().getStrValor();
      }

      this.buscarReg(strId);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(129), ex);
    }
    finally {
    }
  }

  public void setBooSinc(boolean booSinc) {

    _booSinc = booSinc;
  }

  protected void setClsActCadastro(Class<? extends ActMain> clsActFrm) {

    _clsActCadastro = clsActFrm;
  }

  private void setLstItmConsulta(List<ItmConsulta> lstItmConsulta) {

    _lstItmConsulta = lstItmConsulta;
  }

  public void setObjDb(DataBaseAndroid objDb) {

    _objDb = objDb;
  }
}
