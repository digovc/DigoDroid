package com.digosofter.digodroid.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.activity.ActCadastroMain;
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.activity.ActDetalhe;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digodroid.item.ItmConsulta;
import com.digosofter.digodroid.item.ItmDetalheGrupo;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.Utils.EnmStrTipo;
import com.digosofter.digojava.database.DbColuna;
import com.digosofter.digojava.database.DbFiltro;
import com.digosofter.digojava.database.DbTabela;
import com.digosofter.digojava.database.TblOnChangeArg;

public abstract class DbTabelaAndroid extends DbTabela {

  public static final String STR_MENU_ADICIONAR = "Adicionar";
  private static final String STR_MENU_ALTERAR = "Alterar";
  public static final String STR_MENU_APAGAR = "Apagar";
  private static final String STR_MENU_DETALHAR = "Ver detalhes";
  public static final String STR_MENU_PESQUISAR = "Pesquisar";

  private boolean _booItmListaCache = true;
  private boolean _booSinc = true;
  private Class<? extends ActMain> _clsActCadastro;
  private List<ItmConsulta> _lstItmConsulta;
  private List<ItmDetalheGrupo> _lstItmDetalheGrupo;
  private List<DbViewAndroid> _lstViwAndroid;
  private MenuItem _mniOrdemDecrescente;
  private DataBaseAndroid _objDb;

  protected DbTabelaAndroid(String strNome) {

    super(strNome);

    try {

      this.criar();
      this.inicializarViews();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(122), ex);
    }
    finally {
    }
  }

  public void abrirActConsulta(ActMain act) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (this.getLstViwAndroid().size() > 0) {

        AppAndroid.getI().setTblSelec(this.getLstViwAndroid().get(0));
      }
      else {

        AppAndroid.getI().setTblSelec(this);
      }

      itt = new Intent(act, ActConsulta.class);
      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(123), ex);
    }
    finally {
    }
  }

  public void abrirActConsultaLimparCacheAoSair(ActMain act) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (this.getLstViwAndroid().size() > 0) {

        AppAndroid.getI().setTblSelec(this.getLstViwAndroid().get(0));
      }
      else {

        AppAndroid.getI().setTblSelec(this);
      }

      itt = new Intent(act, ActConsulta.class);
      itt.putExtra(ActConsulta.STR_EXTRA_IN_BOO_LIMPAR_LISTA_AO_SAIR, true);

      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(123), ex);
    }
    finally {
    }
  }

  public void abrirActDetalhe(ActMain act, int intRegistroId) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (intRegistroId < 1) {

        return;
      }

      if (this.getLstViwAndroid().size() > 0) {

        AppAndroid.getI().setTblSelec(this.getLstViwAndroid().get(0));
      }
      else {

        AppAndroid.getI().setTblSelec(this);
      }

      itt = new Intent(act, ActDetalhe.class);
      itt.putExtra(ActDetalhe.STR_EXTRA_IN_INT_REGISTRO_ID, intRegistroId);

      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(123), ex);
    }
    finally {
    }
  }

  @Override
  public void apagar(int intId) {

    String sql;

    try {

      if (intId < 1) {

        return;
      }

      sql = "delete from _tbl_nome where _tbl_nome._cln_nome='_registro_id';";

      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("_cln_nome", this.getClnChavePrimaria().getStrNomeSql());
      sql = sql.replace("_registro_id", String.valueOf(intId));

      this.getObjDb().execSql(sql);

      this.viwOnApagarDispatcher(intId);

      super.apagar(intId);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(126), ex);
    }
    finally {
    }
  }

  public void buscar(DbColuna clnFiltro, int intFiltro) {

    this.buscar(clnFiltro, String.valueOf(intFiltro));
  }

  public void buscar(DbColuna clnFiltro, String strFiltro) {

    List<DbFiltro> lstObjDbFiltro;

    try {

      lstObjDbFiltro = new ArrayList<DbFiltro>();
      lstObjDbFiltro.add(new DbFiltro(clnFiltro, strFiltro));

      this.buscar(lstObjDbFiltro);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void buscar(int intId) {

    this.buscar(this.getClnChavePrimaria(), intId);
  }

  public void buscar(List<DbFiltro> lstObjDbFiltro) {

    Cursor crs;
    String sql;

    try {

      sql = "select _clns_nome from _tbl_nome where _where order by _tbl_nome._order;";

      sql = sql.replace("_clns_nome", this.getSqlColunasNomes());
      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("_where", this.getSqlWhere(lstObjDbFiltro));
      sql = sql.replace("_order", this.getClnChavePrimaria().getStrNomeSql());

      crs = this.getObjDb().execSqlComRetorno(sql);

      this.carregarDados(crs);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void buscar(String strId) {

    this.buscar(this.getClnChavePrimaria(), strId);
  }

  private void carregarDados(Cursor crs) {

    try {

      this.limparColunas();

      if (crs == null || !crs.moveToFirst()) {

        return;
      }

      for (DbColuna cln : this.getLstCln()) {

        cln.setStrValor(crs.getString(crs.getColumnIndex(cln.getStrNomeSql())));
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void criar() {

    String sql;

    try {

      if (this.getBooExiste()) {

        return;
      }

      sql = "create table if not exists _tbl_nome (_clns);";

      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("_clns", this.getSqlColunasNomesCreateTable());

      this.getObjDb().execSql(sql);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(124), ex);
    }
    finally {
    }
  }

  protected boolean getBooExiste() {

    boolean booResultado = false;
    Cursor crs;
    String sql;

    try {

      sql = "select name from sqlite_master where type='table' and name='_tbl_nome';";
      sql = sql.replace("_tbl_nome", this.getStrNomeSql());

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

  private boolean getBooItmListaCache() {

    return _booItmListaCache;
  }

  public boolean getBooSinc() {

    return _booSinc;
  }

  public Class<? extends ActMain> getClsActCadastro() {

    return _clsActCadastro;
  }

  public Cursor getCrs() {

    return this.getCrs(this.getLstCln(), null);
  }

  public Cursor getCrs(DbColuna cln) {

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

    return this.getCrs(lstCln, null);
  }

  public Cursor getCrs(DbColuna clnFiltro, double dblFiltro) {

    return this.getCrs(clnFiltro, String.valueOf(dblFiltro));
  }

  public Cursor getCrs(DbColuna clnFiltro, int intFiltro) {

    return this.getCrs(clnFiltro, Double.valueOf(intFiltro));
  }

  public Cursor getCrs(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {

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

    return this.getCrs(lstCln, lstObjDbFiltro);
  }

  public Cursor getCrs(DbColuna clnFiltro, String strFiltro) {

    List<DbFiltro> lstObjDbFiltro;
    Cursor crsResultado = null;

    try {

      lstObjDbFiltro = new ArrayList<DbFiltro>();

      lstObjDbFiltro.add(new DbFiltro(clnFiltro, strFiltro));

      crsResultado = this.getCrs(lstObjDbFiltro);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(128), ex);
    }
    finally {
    }

    return crsResultado;
  }

  public Cursor getCrs(List<DbColuna> lstCln, List<DbFiltro> lstObjDbFiltro) {

    Cursor crsResultado = null;
    String sql;

    try {

      sql = "select _clns_nome from _tbl_nome where _where order by _tbl_nome._order;";

      sql = sql.replace("_clns_nome", this.getSqlSelectColunasNomes(lstCln));
      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("where _where", lstObjDbFiltro != null && lstObjDbFiltro.size() > 0 ? "where _where" : Utils.STR_VAZIA);
      sql = sql.replace("_where", this.getSqlWhere(lstObjDbFiltro));
      sql = sql.replace("_order", this.getClnOrdem().getStrNomeSql());

      crsResultado = this.getObjDb().execSqlComRetorno(sql);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(128), ex);
    }
    finally {
    }

    return crsResultado;
  }

  public Cursor getCrs(List<DbFiltro> lstObjDbFiltro) {

    return this.getCrs(this.getLstCln(), lstObjDbFiltro);
  }

  public Cursor getCrsTelaConsulta() {

    Cursor crsResultado = null;
    String sql;

    try {

      sql = "select _clns_nome from _tbl_nome where _where order by _order _asc_desc;";

      sql = sql.replace("_clns_nome", this.getSqlSelectColunasNomesConsulta());
      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("where _where", this.getLstFilConsulta() != null && this.getLstFilConsulta().size() > 0 ? "where _where" : Utils.STR_VAZIA);
      sql = sql.replace("_where", this.getSqlWhere(this.getLstFilConsulta()));
      sql = sql.replace("_order", this.getClnOrdem().getStrNomeSql());
      sql = sql.replace("_asc_desc", !this.getClnOrdem().getBooOrdemDecrescente() ? "asc" : "desc");

      crsResultado = this.getObjDb().execSqlComRetorno(sql);

      this.getLstFilConsulta().clear();
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

      if (_lstItmConsulta != null && this.getBooItmListaCache()) {

        return _lstItmConsulta;
      }

      _lstItmConsulta = new ArrayList<ItmConsulta>();

      crs = this.getCrsTelaConsulta();

      if (crs == null || !crs.moveToFirst()) {

        return _lstItmConsulta;
      }

      do {

        itmConsulta = new ItmConsulta();

        itmConsulta.setTbl(this);
        itmConsulta.carregarDados(crs, true);

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

  public List<ItmDetalheGrupo> getLstItmDetalheGrupo() {

    try {

      if (_lstItmDetalheGrupo != null) {

        return _lstItmDetalheGrupo;
      }

      _lstItmDetalheGrupo = new ArrayList<ItmDetalheGrupo>();

      this.inicializar(_lstItmDetalheGrupo);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lstItmDetalheGrupo;
  }

  public List<String> getLstStr(DbColuna cln, List<DbFiltro> lstObjDbFiltro) {

    Cursor crs;
    List<String> lstStrResultado = null;

    try {

      crs = this.getCrs(cln, lstObjDbFiltro);
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

  protected List<DbViewAndroid> getLstViwAndroid() {

    try {

      if (_lstViwAndroid != null) {

        return _lstViwAndroid;
      }

      _lstViwAndroid = new ArrayList<DbViewAndroid>();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lstViwAndroid;
  }

  MenuItem getMniOrdemDecrescente() {

    return _mniOrdemDecrescente;
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

        str = str.replace("_tbl_nome", cln.getTbl().getStrNomeSql());
        str = str.replace("_cln_nome", cln.getStrNomeSql());

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

    try {

      strResultado = Utils.STR_VAZIA;

      for (DbColuna cln : this.getLstCln()) {

        if (cln == null) {

          continue;
        }

        strResultado += ((DbColunaAndroid) cln).getSqlCreateTable();
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

        if (cln == null) {

          continue;
        }

        if (Utils.getBooStrVazia(cln.getStrValor())) {

          continue;
        }

        if (cln.getBooChavePrimaria() && cln.getIntValor() < 1) {

          continue;
        }

        if (cln.getClnRef() != null && "0".equals(cln.getStrValor())) {

          continue;
        }

        str = "_cln_nome, ";
        str = str.replace("_cln_nome", cln.getStrNomeSql());

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

        if (cln == null) {

          continue;
        }

        if (Utils.getBooStrVazia(cln.getStrValor())) {

          continue;
        }

        if (cln.getBooChavePrimaria() && cln.getIntValor() < 1) {

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
        str = str.replace("_tbl_nome", cln.getTbl().getStrNomeSql());
        str = str.replace("_cln_nome", cln.getStrNomeSql());

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

    List<DbColuna> lstClnAdicionada;
    String strResultado = null;

    try {

      lstClnAdicionada = new ArrayList<>();
      strResultado = Utils.STR_VAZIA;

      for (DbColuna cln : this.getLstClnConsulta()) {

        if (cln == null) {

          continue;
        }

        if (lstClnAdicionada.contains(cln)) {

          continue;
        }

        if (cln.getClnRef() == null) {

          strResultado += cln.getStrTblNomeClnNome();
          lstClnAdicionada.add(cln);
          continue;
        }

        strResultado += cln.getSqlSubSelectColunaRef();
        lstClnAdicionada.add(cln);
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

  protected void inicializar(List<ItmDetalheGrupo> lstItmDetalheGrupo) {

  }

  public void inicializarViews() {

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
      this.limparListaConsultaViw();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }

  private void limparListaConsultaViw() {

    try {

      for (DbViewAndroid viw : this.getLstViwAndroid()) {

        if (viw == null) {

          continue;
        }

        viw.setLstItmConsulta(null);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void montarMenu(Menu mnu) {

    try {

      if (mnu == null) {

        return;
      }

      this.montarMenuCampo(mnu);
      this.montarMenuOrdenar(mnu);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarMenuCampo(Menu mnu) {

    SubMenu smn;

    try {

      if (mnu == null) {

        return;
      }

      smn = mnu.addSubMenu("Campos");
      smn.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
      smn.setIcon(R.drawable.ic_campo);

      this.montarMenuCampoColuna(smn);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarMenuCampoColuna(SubMenu smn) {

    try {

      if (smn == null) {

        return;
      }

      for (DbColuna cln : this.getLstClnOrdenado()) {

        if (cln == null) {

          continue;
        }

        ((DbColunaAndroid) cln).montarMenuCampo(smn);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void montarMenuItem(Menu mnu, int intRegistroId, boolean booDetalhar) {

    try {

      if (mnu == null) {

        return;
      }

      if (intRegistroId < 1) {

        return;
      }

      if (booDetalhar) {

        this.montarMenuItemDetalhar(mnu);
      }

      this.montarMenuItemAlterar(mnu);
      this.montarMenuItemApagar(mnu, intRegistroId);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarMenuItemAlterar(Menu mnu) {

    try {

      if (mnu == null) {

        return;
      }

      if (this.getClsActCadastro() == null) {

        return;
      }

      if (!this.getBooMenuAlterar()) {

        return;
      }

      mnu.add(DbTabelaAndroid.STR_MENU_ALTERAR);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void montarMenuItemApagar(Menu mnu, int intRegistroId) {

    try {

      if (mnu == null) {

        return;
      }

      if (intRegistroId < 1) {

        return;
      }

      if (!this.getBooMenuApagar()) {

        return;
      }

      mnu.add(DbTabelaAndroid.STR_MENU_APAGAR);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarMenuItemDetalhar(Menu mnu) {

    try {

      if (mnu == null) {

        return;
      }

      mnu.add(DbTabelaAndroid.STR_MENU_DETALHAR);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarMenuOrdenar(Menu mnu) {

    SubMenu smn;

    try {

      if (mnu == null) {

        return;
      }

      smn = mnu.addSubMenu("Ordenar");
      smn.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
      smn.setIcon(R.drawable.ic_ordenar);

      this.montarMenuOrdenarColuna(smn);
      this.montarMenuOrdenarDecrescente(smn);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarMenuOrdenarColuna(SubMenu smn) {

    try {

      if (smn == null) {

        return;
      }

      ((DbColunaAndroid) this.getClnChavePrimaria()).montarMenuOrdenar(smn);
      ((DbColunaAndroid) this.getClnNome()).montarMenuOrdenar(smn);

      for (DbColuna cln : this.getLstClnConsultaOrdenado()) {

        if (cln == null) {

          continue;
        }

        if (!cln.getBooVisivelConsulta()) {

          continue;
        }

        ((DbColunaAndroid) cln).montarMenuOrdenar(smn);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarMenuOrdenarDecrescente(SubMenu smn) {

    try {

      if (smn == null) {

        return;
      }

      this.setMniOrdemDecrescente(smn.add("Ordem decrescente"));
      this.getMniOrdemDecrescente().setChecked(this.getClnOrdem().getBooOrdemDecrescente());
      this.getMniOrdemDecrescente().setCheckable(true);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void onAdicionarAtualizarDispatcher() {

    TblOnChangeArg arg;

    try {

      arg = new TblOnChangeArg();
      arg.setIntRegistroId(this.getClnChavePrimaria().getIntValor());

      this.viwOnAdicionarAtualizarDispatcher(arg);

      if (arg.getIntRegistroId() < 1) {

        this.OnAdicionarRegDispatcher(arg);
        return;
      }

      this.OnAtualizarRegDispatcher(arg);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void processarMenu(ActMain act, MenuItem mni) {

    try {

      if (act == null) {

        return;
      }

      if (mni == null) {

        return;
      }

      if (this.processarMenuCampo(act, mni)) {

        ((ActConsulta) act).atualizarLista();
        return;
      }

      if (this.processarMenuOrdenar(act, mni)) {

        ((ActConsulta) act).atualizarLista();
        return;
      }

      if (mni.equals(this.getMniOrdemDecrescente())) {

        this.processarMenuOrdenarDecrescente((ActConsulta) act);
        return;
      }

      switch (mni.getTitle().toString()) {

        case DbTabelaAndroid.STR_MENU_ADICIONAR:
          this.processarMenuAdicionar(act);
          return;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void processarMenuAdicionar(ActMain act) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (this.getClsActCadastro() == null) {

        return;
      }

      itt = new Intent(act, this.getClsActCadastro());

      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private boolean processarMenuCampo(ActMain act, MenuItem mni) {

    try {

      if (act == null) {

        return false;
      }

      if (!(act instanceof ActConsulta)) {

        return false;
      }

      if (mni == null) {

        return false;
      }

      for (DbColuna cln : this.getLstCln()) {

        if (cln == null) {

          continue;
        }

        if (!mni.equals(((DbColunaAndroid) cln).getMniCampo())) {

          continue;
        }

        ((DbColunaAndroid) cln).processarMenuCampo(mni);
        act.invalidateOptionsMenu();
        return true;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  public void processarMenuItem(ActMain act, MenuItem mnu, int intRegistroId) {

    try {

      if (act == null) {

        return;
      }

      if (mnu == null) {

        return;
      }

      switch (mnu.getTitle().toString()) {

        case DbTabelaAndroid.STR_MENU_ALTERAR:
          this.processarMenuItemAlterar(act, intRegistroId);
          return;

        case DbTabelaAndroid.STR_MENU_APAGAR:
          this.processarMenuItemApagar(act, intRegistroId);
          return;

        case DbTabelaAndroid.STR_MENU_DETALHAR:
          this.processarMenuItemDetalhar(act, intRegistroId);
          return;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void processarMenuItemAlterar(ActMain act, int intRegistroId) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (intRegistroId < 1) {

        return;
      }

      if (this.getClsActCadastro() == null) {

        return;
      }

      AppAndroid.getI().setTblSelec(this);

      itt = new Intent(act, this.getClsActCadastro());
      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_ID, intRegistroId);

      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void processarMenuItemApagar(ActMain act, int intRegistroId) {

    try {

      if (act == null) {

        return;
      }

      if (intRegistroId < 1) {

        return;
      }

      this.apagar(intRegistroId);

      if (!(act instanceof ActDetalhe)) {

        return;
      }

      act.finish();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void processarMenuItemDetalhar(ActMain act, int intRegistroId) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (intRegistroId < 1) {

        return;
      }

      AppAndroid.getI().setTblSelec(this);

      itt = new Intent(act, ActDetalhe.class);
      itt.putExtra(ActDetalhe.STR_EXTRA_IN_INT_REGISTRO_ID, intRegistroId);

      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private boolean processarMenuOrdenar(ActMain act, MenuItem mni) {

    try {

      if (act == null) {

        return false;
      }

      if (!(act instanceof ActConsulta)) {

        return false;
      }

      if (mni == null) {

        return false;
      }

      if (mni.isChecked()) {

        return false;
      }

      if (mni.equals(((DbColunaAndroid) this.getClnChavePrimaria()).getMniOrdenar())) {

        ((DbColunaAndroid) this.getClnChavePrimaria()).processarMenuOrdenar(mni);
        return true;
      }

      if (mni.equals(((DbColunaAndroid) this.getClnNome()).getMniOrdenar())) {

        ((DbColunaAndroid) this.getClnNome()).processarMenuOrdenar(mni);
        return true;
      }

      for (DbColuna cln : this.getLstClnConsulta()) {

        if (cln == null) {

          continue;
        }

        if (!mni.equals(((DbColunaAndroid) cln).getMniOrdenar())) {

          continue;
        }

        ((DbColunaAndroid) cln).processarMenuOrdenar(mni);
        return true;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  private void processarMenuOrdenarDecrescente(ActConsulta actConsulta) {

    try {

      if (actConsulta == null) {

        return;
      }

      this.getMniOrdemDecrescente().setChecked(!this.getMniOrdemDecrescente().isChecked());
      this.getClnOrdem().setBooOrdemDecrescente(this.getMniOrdemDecrescente().isChecked());

      actConsulta.atualizarLista();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void salvar() {

    int intId;
    String sql;

    try {

      this.limparListaConsulta();

      sql = "replace into _tbl_nome (_clns_nome) values (_values);";

      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("_clns_nome", this.getSqlColunasNomesInsert());
      sql = sql.replace("_values", this.getSqlColunasValoresInsert());

      this.getObjDb().execSql(sql);
      this.onAdicionarAtualizarDispatcher();

      if (this.getClnChavePrimaria().getIntValor() < 1) {

        sql = "select last_insert_rowid();";
        intId = this.getObjDb().execSqlGetInt(sql);
      }
      else {

        intId = this.getClnChavePrimaria().getIntValor();
      }

      this.buscar(intId);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(129), ex);
    }
    finally {
    }
  }

  protected void setBooItmListaCache(boolean booItmListaCache) {

    _booItmListaCache = booItmListaCache;
  }

  public void setBooSinc(boolean booSinc) {

    _booSinc = booSinc;
  }

  protected void setClsActCadastro(Class<? extends ActMain> clsActFrm) {

    _clsActCadastro = clsActFrm;
  }

  public void setLstItmConsulta(List<ItmConsulta> lstItmConsulta) {

    _lstItmConsulta = lstItmConsulta;
  }

  private void setMniOrdemDecrescente(MenuItem mniOrdemDecrescente) {

    _mniOrdemDecrescente = mniOrdemDecrescente;
  }

  public void setObjDb(DataBaseAndroid objDb) {

    _objDb = objDb;
  }

  private void viwOnAdicionarAtualizarDispatcher(TblOnChangeArg arg) {

    try {

      if (arg == null) {

        return;
      }

      for (DbViewAndroid viw : this.getLstViwAndroid()) {

        if (viw == null) {

          continue;
        }

        if (arg.getIntRegistroId() < 1) {

          viw.OnAdicionarRegDispatcher(arg);
          continue;
        }

        viw.OnAtualizarRegDispatcher(arg);
      }

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void viwOnApagarDispatcher(int intId) {

    TblOnChangeArg arg;

    try {

      if (intId < 1) {

        return;
      }

      arg = new TblOnChangeArg();
      arg.setIntRegistroId(intId);

      for (DbViewAndroid viw : this.getLstViwAndroid()) {

        if (viw == null) {

          continue;
        }

        viw.OnApagarRegDispatcher(arg);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}