package com.digosofter.digodroid.database;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
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
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.Utils.EnmDataFormato;
import com.digosofter.digojava.Utils.EnmStrTipo;
import com.digosofter.digojava.database.Coluna;
import com.digosofter.digojava.database.Dominio;
import com.digosofter.digojava.database.Filtro;
import com.digosofter.digojava.database.OnChangeArg;
import com.digosofter.digojava.database.Tabela;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Classe que faz a abstração de uma tabela do banco de dados SQLite no aparelho. Esta classe faz todo o intermédio de
 * dados entre a aplicação e a persistência do banco SQLite do Android. Possuim métodos para salvamento, recuperação,
 * deleção, e etc.<br/> Esta classe precisa ser implementada pelas classes que representarão as tabelas da aplicação em
 * específico, pois esta não pode ter instância, já que é abstrada. É indicado que as classes que herdam esta façam uso
 * da técnica "singleton", garantinho uma instância apenas para interação com esta tabela.
 *
 * @param <T>
 * @author r-vieira
 */
public abstract class TabelaAndroid<T extends Dominio> extends Tabela<T> {

  public static final String STR_MENU_ADICIONAR = "Adicionar";
  public static final String STR_MENU_APAGAR = "Apagar";
  public static final String STR_MENU_PESQUISAR = "Pesquisar";
  private static final String STR_MENU_ALTERAR = "Alterar";
  private static final String STR_MENU_DETALHAR = "Ver detalhes";

  private boolean _booAbrirCadastroAuto;
  private boolean _booSinc = true;
  private Class<? extends ActMain> _clsActCadastro;
  private int _intRegistroRefId;
  private List<ViewAndroid> _lstViwAndroid;
  private MenuItem _mniOrdemDecrescente;
  private DataBaseAndroid _objDb;
  private TabelaAndroid<?> _viwPrincipal;

  /**
   * Constroe uma nova instância dessa tabela. Este processo cria também a tabela e suas colunas no banco de dados
   * SQLite caso ela não exista.
   *
   * @param strNome Nome da tabela no banco de dados.
   * @param clsDominio Classe que representa o domínio desta tabela.
   */
  protected TabelaAndroid(String strNome, Class<T> clsDominio) {

    super(strNome, clsDominio);

    try {

      this.criar();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(122), ex);
    }
    finally {
    }
  }

  /**
   * Abre a tela de cadastro para que um novo item seja inserido.
   *
   * @param act Activity "parent" que está chamando este novo activity de cadastro.
   * @param intId Código do registro, no caso de ser uma alteração num registro já salvo.
   * @param intRegistroRefId Código do registro de referência caso este cadastro seja de um item ou se esse tem alguma
   * ligação com outra tabela.
   */
  public void abrirActCadastro(final ActMain act, int intId, int intRegistroRefId) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (this.getClsActCadastro() == null) {

        return;
      }

      itt = new Intent(act, this.getClsActCadastro());

      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_ID, intId);
      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, intRegistroRefId);
      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_TBL_OBJETO_ID, this.getIntObjetoId());

      this.setIntRegistroRefId(itt.getIntExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, 0));

      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Apenas um atalho para {@link #abrirActConsulta(ActMain, Intent)}.
   */
  public void abrirActConsulta(ActMain act) {

    this.abrirActConsulta(act, null);
  }

  /**
   * Abre uma nova tela (Activity) com uma lista que representa os registros retornados, levando em consideração os
   * filtros carregados em {@link #getLstFilConsulta()}. Esta lista de filtros será limpada logo que a tela seja
   * apresentada para o usuário, necessitando que os filtros sejam carregados novamente na próxima vez que este método
   * for chamado.<br/>
   *
   * @param act Activity "parent" (que vem antes na hierarquia de chamadas) da tela de consulta que será aberta.
   * @param itt Intent com parâmetros de entrada da tela ActConsulta para configurar sua aparência e comportamento.<br/>
   * O valor "null" pode ser passado para que a tela tenha o seu comportamento padrão.
   */
  public void abrirActConsulta(ActMain act, Intent itt) {

    try {

      if (itt == null) {

        itt = new Intent();
      }

      itt.setClass(act, ActConsulta.class);

      itt.putExtra(ActConsulta.STR_EXTRA_IN_INT_TBL_OBJETO_ID, this.getViwPrincipal().getIntObjetoId());

      this.setIntRegistroRefId(itt.getIntExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, 0));

      act.startActivityForResult(itt, ActConsulta.EnmResultado.REGISTRO_SELECIONADO.ordinal());
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(123), ex);
    }
    finally {
    }
  }

  /**
   * Atalho para {@link #abrirActConsulta(ActMain, Intent)}. A diferença é que este abre uma tela de consulta que tenha
   * referência para uma outra tabela.
   *
   * @param act Activity "parent" (que vem antes na hierarquia de chamadas) da tela de consulta que será aberta.
   * @param intRegistroRefId Código do registro da tabela que faz referência a esta.
   */
  public void abrirActConsulta(ActMain act, int intRegistroRefId) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (intRegistroRefId < 1) {

        return;
      }

      this.setIntRegistroRefId(intRegistroRefId);

      itt = new Intent();

      itt.putExtra(ActConsulta.STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL, false);
      itt.putExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, intRegistroRefId);

      this.abrirActConsulta(act, itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Apresenta ao usuário uma Activity (tela) com os detalhes do registro indicado pelo id passado como parâmetro.
   *
   * @param act Activity "parent" (que vem antes na hierarquia de chamadas) da tela de consulta que será aberta.
   * @param intRegistroId Id que indica o registro que será apresentado em detalhes.
   */
  public void abrirActDetalhe(ActMain act, int intRegistroId) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (intRegistroId < 1) {

        return;
      }

      this.getViwPrincipal().recuperar(intRegistroId);

      if (this.getViwPrincipal().getClnChavePrimaria().getIntValor() < 1) {

        AppAndroid.getI().notificar("Registro não econtrado.");
        return;
      }

      itt = new Intent(act, ActDetalhe.class);

      itt.putExtra(ActDetalhe.STR_EXTRA_IN_INT_REGISTRO_ID, intRegistroId);
      itt.putExtra(ActDetalhe.STR_EXTRA_IN_INT_TBL_OBJETO_ID, this.getViwPrincipal().getIntObjetoId());

      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(123), ex);
    }
    finally {
    }
  }

  void addViw(ViewAndroid viwAndroid) {

    try {

      if (viwAndroid == null) {

        return;
      }

      if (this.getLstViwAndroid().contains(viwAndroid)) {

        return;
      }

      this.getLstViwAndroid().add(viwAndroid);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

  }

  /**
   * Apaga o registro da tabela.
   *
   * @param intId Id do registro que será apagado da tabela.
   */
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

  private void carregarDados(Cursor crs) {

    try {

      this.limparColunas();

      if (crs == null) {

        return;
      }

      if (!crs.moveToFirst()) {

        return;
      }

      for (Coluna cln : this.getLstCln()) {

        cln.setStrValor(crs.getString(crs.getColumnIndex(cln.getStrNomeSql())));
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private T carregarDominio(Cursor crs) {

    T objDominio;

    try {

      if (crs == null) {

        return null;
      }

      objDominio = this.getClsDominio().newInstance();

      for (Coluna cln : this.getLstCln()) {

        if (cln == null) {

          continue;
        }

        if (!(cln instanceof ColunaAndroid)) {

          continue;
        }

        ((ColunaAndroid) cln).carregarDominio(crs, objDominio);
      }

      return objDominio;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
    return null;
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

  public boolean getBooAbrirCadastroAuto() {

    return _booAbrirCadastroAuto;
  }

  protected boolean getBooExiste() {

    boolean booResultado;
    Cursor crs;
    String sql;

    try {

      sql = "select name from sqlite_master where type='table' and name='_tbl_nome';";
      sql = sql.replace("_tbl_nome", this.getStrNomeSql());

      crs = this.getObjDb().execSqlComRetorno(sql);

      booResultado = ((crs != null) && (crs.moveToFirst()) && (crs.getCount() > 0));

      crs.close();

      return booResultado;
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(127), ex);
    }
    finally {
    }

    return false;
  }

  public boolean getBooRegistroExiste(int intId) {

    String sql;

    try {

      if (intId < 1) {

        return false;
      }

      sql = "select 1 from _tbl_nome where _cln_pk_nome = '_registro_id';";
      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("_cln_pk_nome", this.getClnChavePrimaria().getStrNomeSql());
      sql = sql.replace("_registro_id", String.valueOf(intId));

      return this.getObjDb().execSqlGetBoo(sql);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  public boolean getBooSinc() {

    return _booSinc;
  }

  public Class<? extends ActMain> getClsActCadastro() {

    return _clsActCadastro;
  }

  protected int getIntRegistroRefId() {

    return _intRegistroRefId;
  }

  public List<Integer> getLstInt(Coluna cln, List<Filtro> lstFil) {

    List<Integer> lstIntResultado;
    List<String> lstStr;

    try {

      lstStr = this.getLstStr(cln, lstFil);

      lstIntResultado = new ArrayList<>();

      if (lstStr == null) {

        return lstIntResultado;
      }

      if (lstStr.isEmpty()) {

        return lstIntResultado;
      }

      for (String str : lstStr) {

        lstIntResultado.add(Integer.valueOf(str));
      }

      return lstIntResultado;
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return null;
  }

  public List<String> getLstStr(Coluna cln, List<Filtro> lstFil) {

    Cursor crs;
    List<String> lstStrResultado = null;

    try {

      crs = this.pesquisar(cln, lstFil);
      lstStrResultado = new ArrayList<>();

      if (crs == null) {

        return lstStrResultado;
      }

      if (!crs.moveToFirst()) {

        return lstStrResultado;
      }

      do {

        lstStrResultado.add(crs.getString(0));
      }
      while (crs.moveToNext());

      crs.close();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return lstStrResultado;
  }

  /**
   * Lista de view que representam os dados desta tabela.
   *
   * @return Lista de view que representam os dados desta tabela.
   */
  public List<ViewAndroid> getLstViwAndroid() {

    try {

      if (_lstViwAndroid != null) {

        return _lstViwAndroid;
      }

      _lstViwAndroid = new ArrayList<>();

      this.inicializarLstViwAndroid();
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

      if (this.getLstCln() == null) {

        return "*";
      }

      if (this.getLstCln().isEmpty()) {

        return "*";
      }

      strResultado = Utils.STR_VAZIA;

      for (Coluna cln : this.getLstCln()) {

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

      for (Coluna cln : this.getLstCln()) {

        if (cln == null) {

          continue;
        }

        strResultado += ((ColunaAndroid) cln).getSqlCreateTable();
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

    String strResultado;
    String str;

    try {

      strResultado = Utils.STR_VAZIA;

      for (Coluna cln : this.getLstCln()) {

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

      return strResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  private String getSqlColunasNomesValorUpdate() {

    String strResultado;
    String str;

    try {

      strResultado = Utils.STR_VAZIA;

      for (Coluna cln : this.getLstCln()) {

        if (cln == null) {

          continue;
        }

        if (Utils.getBooStrVazia(cln.getStrValor())) {

          continue;
        }

        if (cln.getBooChavePrimaria()) {

          continue;
        }

        if (cln.getClnRef() != null && cln.getIntValor() < 1) {

          continue;
        }

        str = "_cln_nome = '_cln_valor', ";

        str = str.replace("_cln_nome", cln.getStrNomeSql());
        str = str.replace("_cln_valor", cln.getStrValorSql());

        strResultado += str;
      }

      strResultado = Utils.removerUltimaLetra(strResultado, 2);

      return strResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  private String getSqlColunasValoresInsert() {

    String str;
    String strResultado;

    try {

      strResultado = Utils.STR_VAZIA;

      for (Coluna cln : this.getLstCln()) {

        if (cln == null) {

          continue;
        }

        if (Utils.getBooStrVazia(cln.getStrValor())) {

          continue;
        }

        if (cln.getBooChavePrimaria() && cln.getIntValor() < 1) {

          continue;
        }

        if (cln.getClnRef() != null && (cln.getIntValor() < 1)) {

          continue;
        }

        str = "'_cln_valor', ";
        str = str.replace("_cln_valor", cln.getStrValorSql());

        strResultado += str;
      }

      strResultado = Utils.removerUltimaLetra(strResultado, 2);

      return strResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  private String getSqlSelectColunaCursorAdapterId() {

    String strResultado;

    try {

      strResultado = " _tbl_nome._cln_pk_nome _id";

      strResultado = strResultado.replace("_tbl_nome", this.getStrNomeSql());
      strResultado = strResultado.replace("_cln_pk_nome", this.getClnChavePrimaria().getStrNomeSql());

      return strResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  private String getSqlSelectColunasNomes(List<Coluna> lstCln) {

    String strResultado = null;
    String str;

    try {

      if (lstCln == null) {

        return "*";
      }

      if (lstCln.isEmpty()) {

        return "*";
      }

      strResultado = Utils.STR_VAZIA;

      for (Coluna cln : lstCln) {

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

    List<Coluna> lstClnAdicionada;
    String strResultado;

    try {

      lstClnAdicionada = new ArrayList<>();
      strResultado = Utils.STR_VAZIA;

      for (Coluna cln : this.getLstClnConsulta()) {

        strResultado += this.getSqlSelectColunasNomesConsulta(lstClnAdicionada, cln);
      }

      strResultado += this.getSqlSelectColunaCursorAdapterId();

      strResultado = strResultado.replace(" null", Utils.STR_VAZIA);

      return strResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  @Nullable
  private String getSqlSelectColunasNomesConsulta(List<Coluna> lstClnAdicionada, Coluna cln) {

    try {

      if (cln == null) {

        return null;
      }

      if (lstClnAdicionada.contains(cln)) {

        return null;
      }

      lstClnAdicionada.add(cln);

      if (cln.getClnRef() == null) {

        return cln.getStrTblNomeClnNome();
      }

      return cln.getSqlSubSelectClnRef();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  private String getSqlWhere(List<Filtro> lstFil) {

    boolean booPrimeiroTermo;
    String str;
    String strResultado;

    try {

      if (lstFil == null) {

        return Utils.STR_VAZIA;
      }

      if (lstFil.isEmpty()) {

        return Utils.STR_VAZIA;
      }

      booPrimeiroTermo = true;
      strResultado = Utils.STR_VAZIA;

      for (Filtro fil : lstFil) {

        str = "_filtro ";

        str = str.replace("_filtro", fil.getSqlFiltro(booPrimeiroTermo));

        strResultado += str;

        booPrimeiroTermo = false;
      }

      strResultado = Utils.removerUltimaLetra(strResultado);

      return strResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  /**
   * Esta é a view que representa esta tabela. A primeira view da lista de views. Caso a lista não contenha nenhuma
   * view, retorna a instância desta tabela.
   *
   * @return A view principal desta tabela.
   */
  public TabelaAndroid<?> getViwPrincipal() {

    try {

      if (_viwPrincipal != null) {

        return _viwPrincipal;
      }

      if (this.getLstViwAndroid() == null) {

        _viwPrincipal = this;

        return _viwPrincipal;
      }

      if (this.getLstViwAndroid().isEmpty()) {

        _viwPrincipal = this;

        return _viwPrincipal;
      }

      _viwPrincipal = this.getLstViwAndroid().get(0);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _viwPrincipal;
  }

  /**
   * Este método tem a responsabilidade de inicializar a lista de views desta tabela.
   */
  protected void inicializarLstViwAndroid() {

  }

  private void lerDominio(T objDominio) {

    try {

      this.limparColunas();

      if (objDominio == null) {

        return;
      }

      for (Coluna cln : this.getLstCln()) {

        if (cln == null) {

          continue;
        }

        cln.lerDominio(objDominio);
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

      for (Coluna cln : this.getLstClnOrdenado()) {

        if (cln == null) {

          continue;
        }

        ((ColunaAndroid) cln).montarMenuCampo(smn);
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

      this.montarMenuItemAlterar(mnu, intRegistroId);
      this.montarMenuItemApagar(mnu, intRegistroId);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void montarMenuItemAlterar(Menu mnu, int intRegistroId) {

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

      mnu.add(TabelaAndroid.STR_MENU_ALTERAR);
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

      mnu.add(TabelaAndroid.STR_MENU_APAGAR);
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

      mnu.add(TabelaAndroid.STR_MENU_DETALHAR);
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

      ((ColunaAndroid) this.getClnChavePrimaria()).montarMenuOrdenar(smn);
      ((ColunaAndroid) this.getClnNome()).montarMenuOrdenar(smn);

      for (Coluna cln : this.getLstClnConsultaOrdenado()) {

        if (cln == null) {

          continue;
        }

        if (!cln.getBooVisivelConsulta()) {

          continue;
        }

        ((ColunaAndroid) cln).montarMenuOrdenar(smn);
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

  private void onAdicionarAtualizarDispatcher(boolean booAdicionar) {

    OnChangeArg arg;

    try {

      arg = new OnChangeArg();
      arg.setIntRegistroId(this.getClnChavePrimaria().getIntValor());

      this.viwOnAdicionarAtualizarDispatcher(arg, booAdicionar);

      if (booAdicionar) {

        this.dispararOnAdicionarReg(arg);
        return;
      }

      this.dispararOnAtualizarReg(arg);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public Cursor pesquisar() {

    return this.pesquisar(this.getLstCln(), null);
  }

  public Cursor pesquisar(Coluna cln) {

    List<Coluna> lstCln;

    try {

      lstCln = new ArrayList<>();

      lstCln.add(cln);

      return this.pesquisar(lstCln, null);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return null;
  }

  public Cursor pesquisar(Coluna clnFiltro, boolean booFiltro) {

    return this.pesquisar(clnFiltro, (booFiltro ? 1 : 0));
  }

  public Cursor pesquisar(Coluna clnFiltro, double dblFiltro) {

    return this.pesquisar(clnFiltro, String.valueOf(dblFiltro));
  }

  public Cursor pesquisar(Coluna clnFiltro, int intFiltro) {

    return this.pesquisar(clnFiltro, (double) intFiltro);
  }

  public Cursor pesquisar(Coluna cln, List<Filtro> lstFil) {

    List<Coluna> lstCln;

    try {

      lstCln = new ArrayList<>();

      lstCln.add(cln);

      return this.pesquisar(lstCln, lstFil);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return null;
  }

  public Cursor pesquisar(Coluna clnFiltro, String strFiltro) {

    List<Filtro> lstFil;

    try {

      lstFil = new ArrayList<>();

      lstFil.add(new Filtro(clnFiltro, strFiltro));

      return this.pesquisar(lstFil);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(128), ex);
    }
    finally {
    }

    return null;
  }

  public Cursor pesquisar(List<Coluna> lstCln, List<Filtro> lstFil) {

    String sql;

    try {

      sql = "select _clns_nome from _tbl_nome where _where order by _tbl_nome._order;";

      sql = sql.replace("_clns_nome", this.getSqlSelectColunasNomes(lstCln));
      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("where _where", lstFil != null && lstFil.size() > 0 ? "where _where" : Utils.STR_VAZIA);
      sql = sql.replace("_where", this.getSqlWhere(lstFil));
      sql = sql.replace("_order", this.getClnOrdem().getStrNomeSql());

      return this.getObjDb().execSqlComRetorno(sql);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(128), ex);
    }
    finally {
    }

    return null;
  }

  public Cursor pesquisar(List<Filtro> lstFil) {

    return this.pesquisar(this.getLstCln(), lstFil);
  }

  /**
   * Retorna os dados que serão mostrados na tela de consulta.
   *
   * @return Os dados que serão mostrados na tela de consulta.
   */
  public Cursor pesquisarConsulta() {

    Cursor crsResultado;
    String sql;

    try {

      sql = "select _clns_nome from _tbl_nome where _where order by _order _asc_desc;";

      sql = sql.replace("_clns_nome", this.getSqlSelectColunasNomesConsulta());
      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("where _where", (!this.getLstFilConsulta().isEmpty()) ? "where _where" : Utils.STR_VAZIA);
      sql = sql.replace("_where", this.getSqlWhere(this.getLstFilConsulta()));
      sql = sql.replace("_order", this.getClnOrdem().getStrNomeSql());
      sql = sql.replace("_asc_desc", !this.getClnOrdem().getBooOrdemDecrescente() ? "asc" : "desc");

      crsResultado = this.getObjDb().execSqlComRetorno(sql);

      this.getLstFilConsulta().clear();

      return crsResultado;
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(128), ex);
    }
    finally {
    }

    return null;
  }

  private List<T> pesquisarDominio(Cursor crs) {

    List<T> lstResultado;
    T objDominio;

    try {

      if (crs == null) {

        return null;
      }

      lstResultado = new ArrayList<>();

      do {

        objDominio = this.carregarDominio(crs);

        if (objDominio == null) {

          continue;
        }

        if (objDominio.getIntId() < 1) {

          continue;
        }

        lstResultado.add(objDominio);

      }
      while (crs.moveToNext());

      return lstResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  public List<T> pesquisarDominio(Coluna clnFiltro, boolean booFiltro) {

    return this.pesquisarDominio(clnFiltro, (booFiltro ? 1 : 0));
  }

  public List<T> pesquisarDominio(Coluna clnFiltro, double dblFiltro) {

    return this.pesquisarDominio(clnFiltro, String.valueOf(dblFiltro));
  }

  public List<T> pesquisarDominio(Coluna clnFiltro, GregorianCalendar dttFiltro) {

    try {

      if (dttFiltro == null) {

        return null;
      }

      return this.pesquisarDominio(clnFiltro, Utils.getStrDataFormatada(dttFiltro, EnmDataFormato.YYYY_MM_DD_HH_MM_SS));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  public List<T> pesquisarDominio(Coluna clnFiltro, int intFiltro) {

    return this.pesquisarDominio(clnFiltro, (double) intFiltro);
  }

  public List<T> pesquisarDominio(Coluna clnFiltro, String strFiltro) {

    return this.pesquisarDominio(new Filtro(clnFiltro, strFiltro));
  }

  public List<T> pesquisarDominio(Filtro fil) {

    List<Filtro> lstFil;

    try {

      if (fil == null) {

        return null;
      }

      lstFil = new ArrayList<>();

      lstFil.add(fil);

      return this.pesquisarDominio(lstFil);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  public List<T> pesquisarDominio(List<Filtro> lstFil) {

    Cursor crs;
    List<T> lstObjDominioResultado;

    try {

      if (lstFil == null) {

        return null;
      }

      crs = this.pesquisar(lstFil);

      if (crs == null) {

        return null;
      }

      if (!crs.moveToFirst()) {

        return null;
      }

      lstObjDominioResultado = this.pesquisarDominio(crs);

      crs.close();

      return lstObjDominioResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  public boolean processarMenu(ActMain act, MenuItem mni) {

    try {

      if (act == null) {

        return false;
      }

      if (mni == null) {

        return false;
      }

      if (this.processarMenuCampo(act, mni)) {

        ((ActConsulta) act).atualizarLista();
        return false;
      }

      if (this.processarMenuOrdenar(act, mni)) {

        ((ActConsulta) act).atualizarLista();
        return false;
      }

      if (mni.equals(this.getMniOrdemDecrescente())) {

        return this.processarMenuOrdenarDecrescente((ActConsulta) act);
      }

      switch (mni.getTitle().toString()) {

        case TabelaAndroid.STR_MENU_ADICIONAR:

          return this.processarMenuAdicionar(act);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  private boolean processarMenuAdicionar(ActMain act) {

    Intent itt;

    try {

      if (act == null) {

        return false;
      }

      if (this.getClsActCadastro() == null) {

        return false;
      }

      itt = new Intent(act, this.getClsActCadastro());

      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, this.getIntRegistroRefId());

      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return true;
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

      for (Coluna cln : this.getLstCln()) {

        if (cln == null) {

          continue;
        }

        if (!mni.equals(((ColunaAndroid) cln).getMniCampo())) {

          continue;
        }

        ((ColunaAndroid) cln).processarMenuCampo(mni);
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

  public boolean processarMenuItem(ActMain act, MenuItem mnu, int intId) {

    try {

      if (act == null) {

        return false;
      }

      if (mnu == null) {

        return false;
      }

      switch (mnu.getTitle().toString()) {

        case TabelaAndroid.STR_MENU_ALTERAR:
          return this.processarMenuItemAlterar(act, intId);

        case TabelaAndroid.STR_MENU_APAGAR:
          return this.processarMenuItemApagar(act, intId);

        case TabelaAndroid.STR_MENU_DETALHAR:
          return this.processarMenuItemDetalhar(act, intId);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  private boolean processarMenuItemAlterar(ActMain act, int intId) {

    Intent itt;

    try {

      if (act == null) {

        return false;
      }

      if (intId < 1) {

        return false;
      }

      if (this.getClsActCadastro() == null) {

        return false;
      }

      itt = new Intent(act, this.getClsActCadastro());

      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_ID, intId);
      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, this.getIntRegistroRefId());
      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_TBL_OBJETO_ID, this.getIntObjetoId());

      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return true;
  }

  private boolean processarMenuItemApagar(ActMain act, int intId) {

    try {

      if (act == null) {

        return false;
      }

      if (intId < 1) {

        return false;
      }

      this.apagar(intId);

      if (!(act instanceof ActDetalhe)) {

        return true;
      }

      act.finish();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return true;
  }

  private boolean processarMenuItemDetalhar(ActMain act, int intId) {

    try {

      if (act == null) {

        return false;
      }

      if (intId < 1) {

        return false;
      }

      this.abrirActDetalhe(act, intId);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return true;
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

      if (mni.equals(((ColunaAndroid) this.getClnChavePrimaria()).getMniOrdenar())) {

        ((ColunaAndroid) this.getClnChavePrimaria()).processarMenuOrdenar(mni);
        return true;
      }

      if (mni.equals(((ColunaAndroid) this.getClnNome()).getMniOrdenar())) {

        ((ColunaAndroid) this.getClnNome()).processarMenuOrdenar(mni);
        return true;
      }

      for (Coluna cln : this.getLstClnConsulta()) {

        if (cln == null) {

          continue;
        }

        if (!mni.equals(((ColunaAndroid) cln).getMniOrdenar())) {

          continue;
        }

        ((ColunaAndroid) cln).processarMenuOrdenar(mni);
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

  private boolean processarMenuOrdenarDecrescente(ActConsulta actConsulta) {

    try {

      if (actConsulta == null) {

        return false;
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

    return true;
  }

  public TabelaAndroid recuperar(Coluna clnFiltro, boolean booFiltro) {

    return this.recuperar(clnFiltro, (booFiltro ? 1 : 0));
  }

  public TabelaAndroid recuperar(Coluna clnFiltro, double dblFiltro) {

    return this.recuperar(clnFiltro, String.valueOf(dblFiltro));
  }

  public TabelaAndroid recuperar(Coluna clnFiltro, GregorianCalendar dttFiltro) {

    try {

      if (dttFiltro == null) {

        return this;
      }

      this.recuperar(clnFiltro, Utils.getStrDataFormatada(dttFiltro, EnmDataFormato.YYYY_MM_DD_HH_MM_SS));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return this;
  }

  public TabelaAndroid recuperar(Coluna clnFiltro, int intFiltro) {

    return this.recuperar(clnFiltro, (double) intFiltro);
  }

  public TabelaAndroid recuperar(Coluna clnFiltro, String strFiltro) {

    return this.recuperar(new Filtro(clnFiltro, strFiltro));
  }

  public TabelaAndroid recuperar(Filtro fil) {

    List<Filtro> lstFil;

    try {

      if (fil == null) {

        return this;
      }

      lstFil = new ArrayList<>();
      lstFil.add(fil);

      this.recuperar(lstFil);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return this;
  }

  public TabelaAndroid recuperar(int intId) {

    return this.recuperar(this.getClnChavePrimaria(), intId);
  }

  public TabelaAndroid recuperar(List<Filtro> lstFil) {

    Cursor crs;
    String sql;

    try {

      sql = "select _clns_nome from _tbl_nome where _where order by _tbl_nome._order;";

      sql = sql.replace("_clns_nome", this.getSqlColunasNomes());
      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("_where", this.getSqlWhere(lstFil));
      sql = sql.replace("_order", this.getClnChavePrimaria().getStrNomeSql());

      crs = this.getObjDb().execSqlComRetorno(sql);

      this.carregarDados(crs);

      crs.close();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return this;
  }

  public T recuperarDominio(Coluna clnFiltro, boolean booFiltro) {

    return this.recuperarDominio(clnFiltro, (booFiltro ? 1 : 0));
  }

  public T recuperarDominio(Coluna clnFiltro, double dblFiltro) {

    return this.recuperarDominio(clnFiltro, String.valueOf(dblFiltro));
  }

  public T recuperarDominio(Coluna clnFiltro, GregorianCalendar dttFiltro) {

    try {

      if (dttFiltro == null) {

        return null;
      }

      return this.recuperarDominio(clnFiltro, Utils.getStrDataFormatada(dttFiltro, EnmDataFormato.YYYY_MM_DD_HH_MM_SS));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  public T recuperarDominio(Coluna clnFiltro, int intFiltro) {

    return this.recuperarDominio(clnFiltro, (double) intFiltro);
  }

  public T recuperarDominio(Coluna clnFiltro, String strFiltro) {

    return this.recuperarDominio(new Filtro(clnFiltro, strFiltro));
  }

  public T recuperarDominio(Filtro fil) {

    List<Filtro> lstFil;

    try {

      if (fil == null) {

        return null;
      }

      lstFil = new ArrayList<>();
      lstFil.add(fil);

      return this.recuperarDominio(lstFil);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  public T recuperarDominio(int intId) {

    return this.recuperarDominio(this.getClnChavePrimaria(), intId);
  }

  public T recuperarDominio(List<Filtro> lstFil) {

    List<T> lstObjDominio;

    try {

      if (lstFil == null) {

        return null;
      }

      lstObjDominio = this.pesquisarDominio(lstFil);

      if (lstObjDominio == null) {

        return null;
      }

      if (lstObjDominio.size() < 1) {

        return null;
      }

      return lstObjDominio.get(0);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  public boolean salvar(boolean booValidar) {

    try {

      if (booValidar && !this.validarDados()) {

        return false;
      }

      if (!this.getBooRegistroExiste(this.getClnChavePrimaria().getIntValor())) {

        this.salvarInsert();
        return true;
      }

      this.salvarUpdate();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(129), ex);
    }
    finally {

      this.recuperar(this.getClnChavePrimaria().getIntValor());
    }

    return true;
  }

  public boolean salvar(T objDominio, boolean booValidar) {

    boolean booResultado;

    try {

      if (objDominio == null) {

        return false;
      }

      this.lerDominio(objDominio);

      booResultado = this.salvar(booValidar);

      objDominio.setIntId(this.getClnChavePrimaria().getIntValor());

      return booResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  public void salvarAleatorio() {

    try {

      for (Coluna cln : this.getLstCln()) {

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

      this.salvar(false);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(130), ex);
    }
    finally {
    }
  }

  private void salvarInsert() {

    int intId;
    String sql;

    try {

      sql = "insert into _tbl_nome (_cln_nome) values (_cln_valor);";

      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("_cln_nome", this.getSqlColunasNomesInsert());
      sql = sql.replace("_cln_valor", this.getSqlColunasValoresInsert());

      this.getObjDb().execSql(sql);

      sql = "select last_insert_rowid();";
      intId = this.getObjDb().execSqlGetInt(sql);

      this.getClnChavePrimaria().setIntValor(intId);

      this.onAdicionarAtualizarDispatcher(true);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void salvarUpdate() {

    String sql;

    try {

      sql = "update _tbl_nome set _cln_nome_valor where _cln_pk_nome = '_registro_id';";

      sql = sql.replace("_tbl_nome", this.getStrNomeSql());
      sql = sql.replace("_cln_nome_valor", this.getSqlColunasNomesValorUpdate());
      sql = sql.replace("_cln_pk_nome", this.getClnChavePrimaria().getStrNomeSql());
      sql = sql.replace("_registro_id", this.getClnChavePrimaria().getStrValorSql());

      this.getObjDb().execSql(sql);

      this.onAdicionarAtualizarDispatcher(false);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void setBooAbrirCadastroAuto(boolean booAbrirCadastroAuto) {

    _booAbrirCadastroAuto = booAbrirCadastroAuto;
  }

  public void setBooSinc(boolean booSinc) {

    _booSinc = booSinc;
  }

  protected void setClsActCadastro(Class<? extends ActMain> clsActFrm) {

    _clsActCadastro = clsActFrm;
  }

  private void setIntRegistroRefId(int intRegistroRefId) {

    _intRegistroRefId = intRegistroRefId;
  }

  private void setMniOrdemDecrescente(MenuItem mniOrdemDecrescente) {

    _mniOrdemDecrescente = mniOrdemDecrescente;
  }

  public void setObjDb(DataBaseAndroid objDb) {

    _objDb = objDb;
  }

  /**
   * Este métod é chamado quando o usário termina de preencher os dados nos campos da Activity de cadastro e clica na
   * opção salvar.
   *
   * @return True caso tudo esteja satisfatório para o salvamento do registro, ou false caso contrário.
   */
  public boolean validarDados() {

    return true;
  }

  private void viwOnAdicionarAtualizarDispatcher(OnChangeArg arg, boolean booAdicionar) {

    try {

      if (arg == null) {

        return;
      }

      for (ViewAndroid viw : this.getLstViwAndroid()) {

        if (viw == null) {

          continue;
        }

        if (booAdicionar) {

          viw.dispararOnAdicionarReg(arg);
          continue;
        }

        viw.dispararOnAtualizarReg(arg);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void viwOnApagarDispatcher(int intId) {

    OnChangeArg arg;

    try {

      if (intId < 1) {

        return;
      }

      arg = new OnChangeArg();
      arg.setIntRegistroId(intId);

      for (ViewAndroid viw : this.getLstViwAndroid()) {

        if (viw == null) {

          continue;
        }

        viw.dispararOnApagarReg(arg);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}