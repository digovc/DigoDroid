package com.digosofter.digodroid.database;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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
import com.digosofter.digojava.Utils.EnmDataFormato;
import com.digosofter.digojava.Utils.EnmStrTipo;
import com.digosofter.digojava.database.DbColuna;
import com.digosofter.digojava.database.DbFiltro;
import com.digosofter.digojava.database.DbTabela;
import com.digosofter.digojava.database.Dominio;
import com.digosofter.digojava.database.TblOnChangeArg;

import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/**
 * Classe que faz a abstração de uma tabela do banco de dados SQLite no
 * aparelho. Esta classe faz todo o intermédio de dados entre a aplicação e a
 * persistência do banco SQLite do Android. Possuim métodos para salvamento,
 * recuperação, deleção, e etc.<br/>
 * Esta classe precisa ser implementada pelas classes que representarão as
 * tabelas da aplicação em específico, pois esta não pode ter instância, já que
 * é abstrada. É indicado que as classes que herdam esta façam uso da técnica
 * "singleton", garantinho uma instância apenas para interação com esta tabela.
 *
 * @author r-vieira
 * @param <T>
 *
 */
public abstract class DbTabelaAndroid<T extends Dominio> extends DbTabela<T> {

  public static final String STR_MENU_ADICIONAR = "Adicionar";
  private static final String STR_MENU_ALTERAR = "Alterar";
  public static final String STR_MENU_APAGAR = "Apagar";
  private static final String STR_MENU_DETALHAR = "Ver detalhes";
  public static final String STR_MENU_PESQUISAR = "Pesquisar";

  private boolean _booAbrirCadastroAuto;
  private boolean _booItmListaCache = true;
  private boolean _booLockLstItmConsulta;
  private boolean _booSinc = true;
  private Class<? extends ActMain> _clsActCadastro;
  private List<ItmConsulta> _lstItmConsulta;
  private List<ItmDetalheGrupo> _lstItmDetalheGrupo;
  private List<DbViewAndroid> _lstViwAndroid;
  private MenuItem _mniOrdemDecrescente;
  private DataBaseAndroid _objDb;

  /**
   * Constroe uma nova instância dessa tabela. Este processo cria também a
   * tabela e suas colunas no banco de dados SQLite caso ela não exista.
   *
   * @param strNome
   *          Nome da tabela no banco de dados.
   *
   * @param clsDominio
   *          Classe que representa o domínio desta tabela.
   *
   */
  protected DbTabelaAndroid(String strNome, Class<T> clsDominio) {

    super(strNome, clsDominio);

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

  /**
   * Abre uma nova tela (Activity) com a lista com a representação dos registros
   * retornados, levando em consideração os filtros carregados em
   * {@link #getLstFilConsulta()}. Esta lista de filtros será limpada logo que a
   * tela seja apresentada para o usuário, necessitando que os filtros sejam
   * carregados novamente na próxima vez que a tela seja necessária. A lista de
   * registros será guardada em cache dentro da instância desta tabela para
   * otimizar a montagem da tela em próximas chamadas da tela de consulta.
   *
   * @param act
   *          Activity "parent" (que vem antes na hierarquia de chamadas) da
   *          tela de consulta que será aberta.
   */
  public void abrirActConsulta(ActMain act) {

    this.abrirActConsulta(act, 0);
  }

  /**
   * Faz o mesmo que {@link #abrirActConsulta(ActMain)}, porém com o aditivo de
   * passar o id de um registro de uma tabela referenciada, apresentando apenas
   * os registros detalhe desta tabela.
   *
   * @param act
   *          Activity "parent" (que vem antes na hierarquia de chamadas) da
   *          tela de consulta que será aberta.
   * @param intRegistroRefId
   *          Id do registro na tabela de capa que filtrará os registros desta
   *          tabela de itens.
   */
  public void abrirActConsulta(ActMain act, int intRegistroRefId) {

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

      itt.putExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, intRegistroRefId);

      act.startActivityForResult(itt, 0);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(123), ex);
    }
    finally {
    }
  }

  /**
   * Faz o mesmo que {@link #abrirActConsulta(ActMain)}, porém, quando o usuário
   * sair da tela de consulta a lista não será guardada em cache. Ideal para
   * montar listas dinâmicas onde os itens são alterados constantemente.
   *
   * @param act
   *          Activity "parent" (que vem antes na hierarquia de chamadas) da
   *          tela de consulta que será aberta.
   */
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

  /**
   * Apresenta ao usuário uma Activity (tela) com os detalhes do registro
   * indicado pelo id passado como parâmetro.
   *
   * @param act
   *          Activity "parent" (que vem antes na hierarquia de chamadas) da
   *          tela de consulta que será aberta.
   * @param intRegistroId
   *          Id que indica o registro que será apresentado em detalhes.
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

      if (this.getLstViwAndroid().size() > 0) {

        AppAndroid.getI().setTblSelec(this.getLstViwAndroid().get(0));
      }
      else {

        AppAndroid.getI().setTblSelec(this);
      }

      ((DbTabelaAndroid<?>) AppAndroid.getI().getTblSelec()).recuperar(intRegistroId);

      if (AppAndroid.getI().getTblSelec().getClnChavePrimaria().getIntValor() < 1) {

        AppAndroid.getI().mostrarNotificacao("Registro não econtrado.");
        return;
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

  /**
   * Apaga o registro da tabela.
   *
   * @param intId
   *          Id do registro que será apagado da tabela.
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

  private T carregarDominio(Cursor crs) {

    T objDominio;

    try {

      if (crs == null) {

        return null;
      }

      objDominio = this.getClsDominio().newInstance();

      for (DbColuna cln : this.getLstCln()) {

        if (cln == null) {

          continue;
        }

        if (!(cln instanceof DbColunaAndroid)) {

          continue;
        }

        ((DbColunaAndroid) cln).carregarDominio(crs, objDominio);
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

  private boolean getBooItmListaCache() {

    return _booItmListaCache;
  }

  private boolean getBooLockLstItmConsulta() {

    return _booLockLstItmConsulta;
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

  public List<Integer> getLstInt(DbColuna cln, List<DbFiltro> lstFil) {

    List<Integer> lstIntResultado;
    List<String> lstStr;

    try {

      lstStr = this.getLstStr(cln, lstFil);

      lstIntResultado = new ArrayList<Integer>();

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

  public List<ItmConsulta> getLstItmConsulta() {

    Cursor crs;
    ItmConsulta itmConsulta;

    try {

      this.setBooLockLstItmConsulta(true);

      if (_lstItmConsulta != null && this.getBooItmListaCache()) {

        return _lstItmConsulta;
      }

      _lstItmConsulta = new ArrayList<ItmConsulta>();

      crs = this.pesquisarTelaConsulta();

      if (crs == null || !crs.moveToFirst()) {

        return _lstItmConsulta;
      }

      do {

        itmConsulta = new ItmConsulta(true);

        itmConsulta.setTbl(this);
        itmConsulta.carregarDados(crs);

        _lstItmConsulta.add(itmConsulta);
      }
      while (crs.moveToNext());

      crs.close();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {

      this.setBooLockLstItmConsulta(false);
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

  public List<String> getLstStr(DbColuna cln, List<DbFiltro> lstFil) {

    Cursor crs;
    List<String> lstStrResultado = null;

    try {

      crs = this.pesquisar(cln, lstFil);
      lstStrResultado = new ArrayList<String>();

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

      if (this.getLstCln() == null) {

        return "*";
      }

      if (this.getLstCln().isEmpty()) {

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

    String strResultado;
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

      for (DbColuna cln : this.getLstCln()) {

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

  private String getSqlSelectColunasNomes(List<DbColuna> lstCln) {

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

        strResultado += cln.getSqlSubSelectClnRef();
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
    String strResultado;

    try {

      if (lstObjDbFiltro == null) {

        return Utils.STR_VAZIA;
      }

      if (lstObjDbFiltro.isEmpty()) {

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

      return strResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
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

      this.salvar(false);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(130), ex);
    }
    finally {
    }
  }

  private void lerDominio(T objDominio) {

    try {

      this.limparColunas();

      if (objDominio == null) {

        return;
      }

      for (DbColuna cln : this.getLstCln()) {

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

  private void onAdicionarAtualizarDispatcher(boolean booAdicionar) {

    TblOnChangeArg arg;

    try {

      arg = new TblOnChangeArg();
      arg.setIntRegistroId(this.getClnChavePrimaria().getIntValor());

      this.viwOnAdicionarAtualizarDispatcher(arg, booAdicionar);

      if (booAdicionar) {

        this.onAdicionarRegDispatcher(arg);
        return;
      }

      this.onAtualizarRegDispatcher(arg);
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

  public Cursor pesquisar(DbColuna cln) {

    List<DbColuna> lstCln;

    try {

      lstCln = new ArrayList<DbColuna>();

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

  public Cursor pesquisar(DbColuna clnFiltro, boolean booFiltro) {

    return this.pesquisar(clnFiltro, (booFiltro ? 1 : 0));
  }

  public Cursor pesquisar(DbColuna clnFiltro, double dblFiltro) {

    return this.pesquisar(clnFiltro, String.valueOf(dblFiltro));
  }

  public Cursor pesquisar(DbColuna clnFiltro, int intFiltro) {

    return this.pesquisar(clnFiltro, (double) intFiltro);
  }

  public Cursor pesquisar(DbColuna cln, List<DbFiltro> lstFil) {

    List<DbColuna> lstCln;

    try {

      lstCln = new ArrayList<DbColuna>();

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

  public Cursor pesquisar(DbColuna clnFiltro, String strFiltro) {

    List<DbFiltro> lstObjDbFiltro;

    try {

      lstObjDbFiltro = new ArrayList<DbFiltro>();

      lstObjDbFiltro.add(new DbFiltro(clnFiltro, strFiltro));

      return this.pesquisar(lstObjDbFiltro);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(128), ex);
    }
    finally {
    }

    return null;
  }

  public Cursor pesquisar(List<DbColuna> lstCln, List<DbFiltro> lstFil) {

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

  public Cursor pesquisar(List<DbFiltro> lstFil) {

    return this.pesquisar(this.getLstCln(), lstFil);
  }

  private List<T> pesquisarDominio(Cursor crs) {

    List<T> lstResultado;
    T objDominio;

    try {

      if (crs == null) {

        return null;
      }

      lstResultado = new ArrayList<T>();

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

  public List<T> pesquisarDominio(DbColuna clnFiltro, boolean booFiltro) {

    return this.pesquisarDominio(clnFiltro, (booFiltro ? 1 : 0));
  }

  public List<T> pesquisarDominio(DbColuna clnFiltro, double dblFiltro) {

    return this.pesquisarDominio(clnFiltro, String.valueOf(dblFiltro));
  }

  public List<T> pesquisarDominio(DbColuna clnFiltro, GregorianCalendar dttFiltro) {

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

  public List<T> pesquisarDominio(DbColuna clnFiltro, int intFiltro) {

    return this.pesquisarDominio(clnFiltro, (double) intFiltro);
  }

  public List<T> pesquisarDominio(DbColuna clnFiltro, String strFiltro) {

    return this.pesquisarDominio(new DbFiltro(clnFiltro, strFiltro));
  }

  public List<T> pesquisarDominio(DbFiltro fil) {

    List<DbFiltro> lstFil;

    try {

      if (fil == null) {

        return null;
      }

      lstFil = new ArrayList<DbFiltro>();

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

  public List<T> pesquisarDominio(List<DbFiltro> lstFil) {

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

  public Cursor pesquisarTelaConsulta() {

    Cursor crsResultado;
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

      return crsResultado;
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(128), ex);
    }
    finally {
    }

    return null;
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

  public void processarMenuItem(ActMain act, MenuItem mnu, int intId) {

    try {

      if (act == null) {

        return;
      }

      if (mnu == null) {

        return;
      }

      switch (mnu.getTitle().toString()) {

        case DbTabelaAndroid.STR_MENU_ALTERAR:
          this.processarMenuItemAlterar(act, intId);
          return;

        case DbTabelaAndroid.STR_MENU_APAGAR:
          this.processarMenuItemApagar(act, intId);
          return;

        case DbTabelaAndroid.STR_MENU_DETALHAR:
          this.processarMenuItemDetalhar(act, intId);
          return;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void processarMenuItemAlterar(ActMain act, int intId) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (intId < 1) {

        return;
      }

      if (this.getClsActCadastro() == null) {

        return;
      }

      AppAndroid.getI().setTblSelec(this);

      itt = new Intent(act, this.getClsActCadastro());
      itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_ID, intId);

      act.startActivity(itt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void processarMenuItemApagar(ActMain act, int intId) {

    try {

      if (act == null) {

        return;
      }

      if (intId < 1) {

        return;
      }

      this.apagar(intId);

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

  private void processarMenuItemDetalhar(ActMain act, int intId) {

    Intent itt;

    try {

      if (act == null) {

        return;
      }

      if (intId < 1) {

        return;
      }

      AppAndroid.getI().setTblSelec(this);

      itt = new Intent(act, ActDetalhe.class);
      itt.putExtra(ActDetalhe.STR_EXTRA_IN_INT_REGISTRO_ID, intId);

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

  public void recuperar(DbColuna clnFiltro, boolean booFiltro) {

    this.recuperar(clnFiltro, (booFiltro ? 1 : 0));
  }

  public void recuperar(DbColuna clnFiltro, double dblFiltro) {

    this.recuperar(clnFiltro, String.valueOf(dblFiltro));
  }

  public void recuperar(DbColuna clnFiltro, GregorianCalendar dttFiltro) {

    try {

      if (dttFiltro == null) {

        return;
      }

      this.recuperar(clnFiltro, Utils.getStrDataFormatada(dttFiltro, EnmDataFormato.YYYY_MM_DD_HH_MM_SS));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void recuperar(DbColuna clnFiltro, int intFiltro) {

    this.recuperar(clnFiltro, (double) intFiltro);
  }

  public void recuperar(DbColuna clnFiltro, String strFiltro) {

    this.recuperar(new DbFiltro(clnFiltro, strFiltro));
  }

  public void recuperar(DbFiltro fil) {

    List<DbFiltro> lstFil;

    try {

      if (fil == null) {

        return;
      }

      lstFil = new ArrayList<DbFiltro>();
      lstFil.add(fil);

      this.recuperar(lstFil);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

  }

  public void recuperar(int intId) {

    this.recuperar(this.getClnChavePrimaria(), intId);
  }

  public void recuperar(List<DbFiltro> lstFil) {

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
  }

  public T recuperarDominio(DbColuna clnFiltro, boolean booFiltro) {

    return this.recuperarDominio(clnFiltro, (booFiltro ? 1 : 0));
  }

  public T recuperarDominio(DbColuna clnFiltro, double dblFiltro) {

    return this.recuperarDominio(clnFiltro, String.valueOf(dblFiltro));
  }

  public T recuperarDominio(DbColuna clnFiltro, GregorianCalendar dttFiltro) {

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

  public T recuperarDominio(DbColuna clnFiltro, int intFiltro) {

    return this.recuperarDominio(clnFiltro, (double) intFiltro);
  }

  public T recuperarDominio(DbColuna clnFiltro, String strFiltro) {

    return this.recuperarDominio(new DbFiltro(clnFiltro, strFiltro));
  }

  public T recuperarDominio(DbFiltro fil) {

    List<DbFiltro> lstFil;

    try {

      if (fil == null) {

        return null;
      }

      lstFil = new ArrayList<DbFiltro>();
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

  public T recuperarDominio(List<DbFiltro> lstFil) {

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

      this.limparListaConsulta();

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

  protected void setBooItmListaCache(boolean booItmListaCache) {

    _booItmListaCache = booItmListaCache;
  }

  private void setBooLockLstItmConsulta(boolean booLockLstItmConsulta) {

    _booLockLstItmConsulta = booLockLstItmConsulta;
  }

  public void setBooSinc(boolean booSinc) {

    _booSinc = booSinc;
  }

  protected void setClsActCadastro(Class<? extends ActMain> clsActFrm) {

    _clsActCadastro = clsActFrm;
  }

  public void setLstItmConsulta(List<ItmConsulta> lstItmConsulta) {

    try {

      while (this.getBooLockLstItmConsulta()) {

        Thread.sleep(100);
      }

      _lstItmConsulta = lstItmConsulta;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setMniOrdemDecrescente(MenuItem mniOrdemDecrescente) {

    _mniOrdemDecrescente = mniOrdemDecrescente;
  }

  public void setObjDb(DataBaseAndroid objDb) {

    _objDb = objDb;
  }

  protected boolean validarDados() {

    return true;
  }

  private void viwOnAdicionarAtualizarDispatcher(TblOnChangeArg arg, boolean booAdicionar) {

    try {

      if (arg == null) {

        return;
      }

      for (DbViewAndroid viw : this.getLstViwAndroid()) {

        if (viw == null) {

          continue;
        }

        if (booAdicionar) {

          viw.onAdicionarRegDispatcher(arg);
          continue;
        }

        viw.onAtualizarRegDispatcher(arg);
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

        viw.onApagarRegDispatcher(arg);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}