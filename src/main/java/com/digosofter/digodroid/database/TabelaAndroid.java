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
import com.digosofter.digodroid.dominio.DominioAndroidMain;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.Utils.EnmDataFormato;
import com.digosofter.digojava.Utils.EnmStringTipo;
import com.digosofter.digojava.database.Coluna;
import com.digosofter.digojava.database.Filtro;
import com.digosofter.digojava.database.OnChangeArg;
import com.digosofter.digojava.database.Tabela;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Classe que faz a abstração de uma tabela do banco de dados SQLite no aparelho. Esta classe faz todo o intermédio de dados entre a aplicação e a
 * persistência do banco SQLite do Android. Possuim métodos para salvamento, recuperação, deleção, e etc.<br/> Esta classe precisa ser implementada
 * pelas classes que representarão as tabelas da aplicação em específico, pois esta não pode ter instância, já que é abstrada. É indicado que as
 * classes que herdam esta façam uso da técnica "singleton", garantinho uma instância apenas para interação com esta tabela.
 *
 * @param <T>
 * @author r-vieira
 */
public abstract class TabelaAndroid<T extends DominioAndroidMain> extends Tabela<T>
{
  public static final String STR_MENU_ADICIONAR = "Adicionar";
  public static final String STR_MENU_APAGAR = "Apagar";
  private static final String STR_MENU_ALTERAR = "Alterar";
  private static final String STR_MENU_DETALHAR = "Ver detalhes";
  private static final String STR_MENU_PESQUISAR_POR = "Pesquisar por";

  private boolean _booSincronizada = true;
  private ColunaAndroid _clnDttAlteracao;
  private ColunaAndroid _clnDttCadastro;
  private ColunaAndroid _clnIntId;
  private ColunaAndroid _clnIntUsuarioCadastroId;
  private ColunaAndroid _clnPesquisa;
  private Class<? extends ActMain> _clsActCadastro;
  private DataBaseAndroid _dbe;
  private int _intRegistroRefId;
  private List<ViewAndroid> _lstViwAndroid;
  private MenuItem _mniOrdemDecrescente;
  private TabelaAndroid<?> _viwPrincipal;

  /**
   * Constroe uma nova instância dessa tabela. Este processo cria também a tabela e suas colunas no banco de dados SQLite caso ela não exista.
   *
   * @param strNome Nome da tabela no banco de dados.
   * @param clsDominio Classe que representa o domínio desta tabela.
   */
  protected TabelaAndroid(String strNome, Class<T> clsDominio, DataBaseAndroid dbeAndroid)
  {
    super(strNome, clsDominio, dbeAndroid);
  }

  /**
   * Atalho para {@link #abrirCadastro(ActMain, int, int)}
   *
   * @param act Activity "parent" da tela de cadastro que será aberta.
   */
  public void abrirCadastro(final ActMain act)
  {
    this.abrirCadastro(act, 0, 0);
  }

  /**
   * Atalho para {@link #abrirCadastro(ActMain, int, int)}
   *
   * @param act Activity "parent" da tela de cadastro que será aberta.
   * @param intId Código do registro para alteração.
   */
  public void abrirCadastro(final ActMain act, int intId)
  {
    this.abrirCadastro(act, intId, 0);
  }

  /**
   * Abre a tela de cadastro para que um novo item seja inserido.
   *
   * @param act Activity "parent" que está chamando este novo activity de cadastro.
   * @param intRegistroId Código do registro, no caso de ser uma alteração num registro já salvo.
   * @param intRegistroRefId Código do registro de referência caso este cadastro seja de um item ou se esse tem alguma ligação com outra tabela.
   */
  public void abrirCadastro(final ActMain act, int intRegistroId, int intRegistroRefId)
  {
    if (act == null)
    {
      return;
    }

    if (this.getClsActCadastro() == null)
    {
      return;
    }

    Intent itt = new Intent(act, this.getClsActCadastro());

    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_ID, intRegistroId);
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, intRegistroRefId);
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_TBL_OBJETO_ID, this.getIntObjetoId());

    this.setIntRegistroRefId(intRegistroRefId);

    act.startActivity(itt);
  }

  /**
   * Atalho para {@link #abrirConsulta(ActMain, Intent)}.
   */
  public void abrirConsulta(ActMain act)
  {
    this.abrirConsulta(act, null);
  }

  /**
   * Abre uma nova tela (Activity) com uma lista que representa os registros retornados, levando em consideração os filtros carregados em {@link
   * #getLstFilConsulta()}. Esta lista de filtros será limpada logo que a tela seja apresentada para o usuário, necessitando que os filtros sejam
   * carregados novamente na próxima vez que este método for chamado.<br/>
   *
   * @param act Activity "parent" (que vem antes na hierarquia de chamadas) da tela de consulta que será aberta.
   * @param itt Intent com parâmetros de entrada da tela ActConsulta para configurar sua aparência e comportamento.<br/> O valor "null" pode ser
   * passado para que a tela tenha o seu comportamento padrão.
   */
  public void abrirConsulta(ActMain act, Intent itt)
  {
    if (act == null)
    {
      return;
    }

    if (itt == null)
    {
      itt = new Intent();
    }

    itt.setClass(act, ActConsulta.class);

    itt.putExtra(ActConsulta.STR_EXTRA_IN_INT_TBL_OBJETO_ID, this.getViwPrincipal().getIntObjetoId());

    act.startActivityForResult(itt, ActConsulta.EnmResultado.REGISTRO_SELECIONADO.ordinal());
  }

  /**
   * Atalho para {@link #abrirConsulta(ActMain, Intent)}. A diferença é que este abre uma tela de consulta que tenha referência para uma outra
   * tabela.
   *
   * @param act Activity "parent" (que vem antes na hierarquia de chamadas) da tela de consulta que será aberta.
   * @param intRegistroRefId Código do registro da tabela que faz referência a esta.
   */
  public void abrirConsulta(ActMain act, int intRegistroRefId)
  {
    if (act == null)
    {
      return;
    }

    if (intRegistroRefId < 1)
    {
      return;
    }

    this.setIntRegistroRefId(intRegistroRefId);
    this.getViwPrincipal().setIntRegistroRefId(intRegistroRefId);

    Intent itt = new Intent();

    itt.putExtra(ActConsulta.STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL, false);
    itt.putExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, intRegistroRefId);

    this.abrirConsulta(act, itt);
  }

  /**
   * Apresenta ao usuário uma Activity (tela) com os detalhes do registro indicado pelo id passado como parâmetro.
   *
   * @param act Activity "parent" (que vem antes na hierarquia de chamadas) da tela de consulta que será aberta.
   * @param intRegistroId Id que indica o registro que será apresentado em detalhes.
   */
  public void abrirDetalhe(ActMain act, int intRegistroId)
  {
    if (act == null)
    {
      return;
    }

    if (intRegistroId < 1)
    {
      return;
    }

    this.getViwPrincipal().recuperar(intRegistroId);

    if (this.getViwPrincipal().getClnIntId().getIntValor() < 1)
    {
      AppAndroid.getI().notificar("Registro não econtrado.");
      return;
    }

    Intent itt = new Intent(act, ActDetalhe.class);

    itt.putExtra(ActDetalhe.STR_EXTRA_IN_INT_REGISTRO_ID, intRegistroId);
    itt.putExtra(ActDetalhe.STR_EXTRA_IN_INT_TBL_OBJETO_ID, this.getViwPrincipal().getIntObjetoId());

    act.startActivity(itt);
  }

  void addViw(ViewAndroid viwAndroid)
  {
    if (viwAndroid == null)
    {
      return;
    }

    if (this.getLstViwAndroid().contains(viwAndroid))
    {
      return;
    }

    this.getLstViwAndroid().add(viwAndroid);
  }

  /**
   * Apaga o registro da tabela.
   *
   * @param intRegistroId Id do registro que será apagado da tabela.
   */
  @Override
  public void apagar(int intRegistroId)
  {
    if (intRegistroId < 1)
    {
      return;
    }

    String sql = "delete from _tbl_nome where _tbl_nome._cln_nome='_registro_id';";

    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("_cln_nome", this.getClnIntId().getSqlNome());
    sql = sql.replace("_registro_id", String.valueOf(intRegistroId));

    this.getDbe().execSql(sql);

    this.dispararEvtOnApagarDispatcherView(intRegistroId);

    super.apagar(intRegistroId);
  }

  /**
   * Apaga os registros da tabela que atendem aos filtros passados por parâmetro.
   *
   * @param lstFil Lista de filtros que será utilizado para buscar os registros que serão apagados do banco de dados.
   */
  public void apagar(final List<Filtro> lstFil)
  {
    if (lstFil == null)
    {
      return;
    }

    String sql = "delete from _tbl_nome where _where;";

    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("where _where", lstFil != null && lstFil.size() > 0 ? "where _where" : Utils.STR_VAZIA);
    sql = sql.replace("_where", this.getSqlWhere(lstFil));

    this.getDbe().execSql(sql);

    this.dispararEvtOnApagarDispatcherView(-1);

    super.apagar(-1);
  }

  private void carregarDados(Cursor crs)
  {
    this.limparColunas();

    if (crs == null)
    {
      return;
    }

    if (!crs.moveToFirst())
    {
      return;
    }

    for (Coluna cln : this.getLstCln())
    {
      cln.setStrValor(crs.getString(crs.getColumnIndex(cln.getSqlNome())));
    }
  }

  private T carregarDominio(Cursor crs)
  {
    if (crs == null)
    {
      return null;
    }

    T objDominio = null;

    try
    {
      objDominio = this.getClsDominio().newInstance();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }

    if (objDominio == null)
    {
      return null;
    }

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (!(cln instanceof ColunaAndroid))
      {
        continue;
      }

      ((ColunaAndroid) cln).carregarDominio(crs, objDominio);
    }

    return objDominio;
  }

  /**
   * Este método pode ser sobescrito para carregar os filtros da propriedade {#link #getLstFilConsulta} que será utilizada para pesquisar os registros
   * para serem apresentados na tela de consulta.
   *
   * @param lstFilConsulta Ponteiro para o atributo {@link @getLstFilConsulta} desta tabela.
   */
  protected void carregarLstFilConsulta(List<Filtro> lstFilConsulta)
  {
    if (Utils.getBooStrVazia(this.getStrPesquisa()))
    {
      return;
    }

    if (this.getClnPesquisa() == null)
    {
      return;
    }

    Filtro filPesquisa = new Filtro(this.getClnPesquisa(), this.getStrPesquisa());

    if (this.getClnPesquisa() == this.getClnIntId())
    {
      filPesquisa.setEnmOperador(Filtro.EnmOperador.IGUAL);
    }
    else
    {
      filPesquisa.setEnmOperador(Filtro.EnmOperador.CONTEM);
    }

    lstFilConsulta.add(filPesquisa);
  }

  protected void criar()
  {
    if (this.getDbe() == null)
    {
      return;
    }

    if (this.getBooExiste())
    {
      return;
    }

    String sql = "create table if not exists _tbl_nome (_cln_pk_nome _cln_pk_tipo primary key);";

    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("_cln_pk_nome", this.getClnIntId().getSqlNome());
    sql = sql.replace("_cln_pk_tipo", ((ColunaAndroid) this.getClnIntId()).getSqlTipo());

    this.getDbe().execSql(sql);
  }

  /**
   * Cria as views associadas a esta tabela no banco de dados.
   */
  public void criarView()
  {
    if (this.getLstViwAndroid() == null)
    {
      return;
    }

    for (ViewAndroid viw : this.getLstViwAndroid())
    {
      this.criarView(viw);
    }
  }

  private void criarView(final ViewAndroid viw)
  {
    if (viw == null)
    {
      return;
    }

    viw.criar();
  }

  private void dispararEvtOnAdicionarAtualizarListener(boolean booAdicionar)
  {
    OnChangeArg arg = new OnChangeArg();

    arg.setIntRegistroId(this.getClnIntId().getIntValor());

    this.dispararEvtOnAdicionarAtualizarListenerView(arg, booAdicionar);

    if (booAdicionar)
    {
      this.dispararEvtOnAdicionarReg(arg);
      return;
    }

    this.dispararEvtOnAtualizarReg(arg);
  }

  private void dispararEvtOnAdicionarAtualizarListenerView(OnChangeArg arg, boolean booAdicionar)
  {
    if (arg == null)
    {
      return;
    }

    for (ViewAndroid viw : this.getLstViwAndroid())
    {
      if (viw == null)
      {
        continue;
      }

      if (booAdicionar)
      {
        viw.dispararEvtOnAdicionarReg(arg);
        continue;
      }

      viw.dispararEvtOnAtualizarReg(arg);
    }
  }

  private void dispararEvtOnApagarDispatcherView(int intRegistroId)
  {
    if (intRegistroId < 1)
    {
      return;
    }

    OnChangeArg arg = new OnChangeArg();

    arg.setIntRegistroId(intRegistroId);

    for (ViewAndroid viw : this.getLstViwAndroid())
    {
      if (viw == null)
      {
        continue;
      }

      viw.dispararEvtOnApagarReg(arg);
    }
  }

  protected boolean getBooExiste()
  {
    if (this.getDbe() == null)
    {
      return false;
    }

    String sql = "select max(1) from sqlite_master where type='table' and name='_tbl_nome';";

    sql = sql.replace("_tbl_nome", this.getSqlNome());

    return this.getDbe().execSqlGetBoo(sql);
  }

  public boolean getBooRegistroExiste(int intRegistroId)
  {
    if (intRegistroId < 1)
    {
      return false;
    }

    String sql = "select 1 from _tbl_nome where _cln_pk_nome = '_registro_id';";

    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("_cln_pk_nome", this.getClnIntId().getSqlNome());
    sql = sql.replace("_registro_id", String.valueOf(intRegistroId));

    return this.getDbe().execSqlGetBoo(sql);
  }

  public boolean getBooSincronizada()
  {
    return _booSincronizada;
  }

  public ColunaAndroid getClnDttAlteracao()
  {
    if (_clnDttAlteracao != null)
    {
      return _clnDttAlteracao;
    }

    _clnDttAlteracao = new ColunaAndroid("dtt_alteracao", this, Coluna.EnmTipo.DATE_TIME);

    return _clnDttAlteracao;
  }

  public ColunaAndroid getClnDttCadastro()
  {
    if (_clnDttCadastro != null)
    {
      return _clnDttCadastro;
    }

    _clnDttCadastro = new ColunaAndroid("dtt_alteracao", this, Coluna.EnmTipo.DATE_TIME);

    return _clnDttCadastro;
  }

  public ColunaAndroid getClnIntId()
  {
    if (_clnIntId != null)
    {
      return _clnIntId;
    }

    _clnIntId = new ColunaAndroid("int_id", this, Coluna.EnmTipo.BIGINT);

    return _clnIntId;
  }

  @Override
  protected Coluna getClnIntUsuarioAlteracaoId()
  {
    return null;
  }

  public ColunaAndroid getClnIntUsuarioCadastroId()
  {
    if (_clnIntUsuarioCadastroId != null)
    {
      return _clnIntUsuarioCadastroId;
    }

    _clnIntUsuarioCadastroId = new ColunaAndroid("int_usuario_alteracao_id", this, Coluna.EnmTipo.BIGINT);

    return _clnIntUsuarioCadastroId;
  }

  /**
   * Coluna que está marcada como pesquisa.
   *
   * @return Coluna que está marcada como pesquisa.
   */
  public ColunaAndroid getClnPesquisa()
  {
    if (_clnPesquisa != null)
    {
      return _clnPesquisa;
    }

    _clnPesquisa = (ColunaAndroid) this.getClnNome();

    return _clnPesquisa;
  }

  public Class<? extends ActMain> getClsActCadastro()
  {
    return _clsActCadastro;
  }

  @Override
  public DataBaseAndroid getDbe()
  {
    if (_dbe != null)
    {
      return _dbe;
    }

    if (super.getDbe() == null)
    {
      return null;
    }

    _dbe = (DataBaseAndroid) super.getDbe();

    return _dbe;
  }

  protected int getIntRegistroRefId()
  {
    return _intRegistroRefId;
  }

  public List<Integer> getLstInt(Coluna cln, List<Filtro> lstFil)
  {
    List<String> lstStr = this.getLstStr(cln, lstFil);
    List<Integer> lstIntResultado = new ArrayList<>();

    if (lstStr == null)
    {
      return lstIntResultado;
    }

    if (lstStr.isEmpty())
    {
      return lstIntResultado;
    }

    for (String str : lstStr)
    {
      lstIntResultado.add(Integer.valueOf(str));
    }

    return lstIntResultado;
  }

  public List<String> getLstStr(Coluna cln, List<Filtro> lstFil)
  {
    Cursor crs = this.pesquisar(cln, lstFil);
    List<String> lstStrResultado = new ArrayList<>();

    if (crs == null)
    {
      return lstStrResultado;
    }

    if (!crs.moveToFirst())
    {
      return lstStrResultado;
    }

    do
    {
      lstStrResultado.add(crs.getString(0));
    }
    while (crs.moveToNext());

    crs.close();

    return lstStrResultado;
  }

  /**
   * Lista de view que representam os dados desta tabela.
   *
   * @return Lista de view que representam os dados desta tabela.
   */
  public List<ViewAndroid> getLstViwAndroid()
  {
    if (_lstViwAndroid != null)
    {
      return _lstViwAndroid;
    }

    _lstViwAndroid = new ArrayList<>();

    this.inicializarLstViwAndroid();

    return _lstViwAndroid;
  }

  MenuItem getMniOrdemDecrescente()
  {
    return _mniOrdemDecrescente;
  }

  private String getSqlColunasNomes()
  {
    if (this.getLstCln().isEmpty())
    {
      return "*";
    }

    String strResultado = Utils.STR_VAZIA;

    for (Coluna cln : this.getLstCln())
    {
      strResultado += cln.getStrTblNomeClnNome();
    }

    return Utils.removerUltimaLetra(strResultado, 2);
  }

  private String getSqlColunasNomesInsert()
  {
    String strResultado = Utils.STR_VAZIA;

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (Utils.getBooStrVazia(cln.getStrValor()))
      {
        continue;
      }

      if ((cln == this.getClnIntId()) && (cln.getIntValor() < 1))
      {
        continue;
      }

      if ((cln.getClnRef() != null) && (cln.getIntValor() < 1))
      {
        continue;
      }

      strResultado += cln.getSqlNome().concat(", ");
    }

    return Utils.removerUltimaLetra(strResultado, 2);
  }

  private String getSqlColunasNomesValorUpdate()
  {
    String strResultado = Utils.STR_VAZIA;

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (Utils.getBooStrVazia(cln.getStrValor()))
      {
        continue;
      }

      if (cln == this.getClnIntId())
      {
        continue;
      }

      if ((cln.getClnRef() != null) && (cln.getIntValor() < 1))
      {
        continue;
      }

      String strNomeValor = "_cln_nome = '_cln_valor', ";

      strNomeValor = strNomeValor.replace("_cln_nome", cln.getSqlNome());
      strNomeValor = strNomeValor.replace("_cln_valor", cln.getStrValorSql());

      strResultado += strNomeValor;
    }

    strResultado = Utils.removerUltimaLetra(strResultado, 2);

    return strResultado;
  }

  private String getSqlColunasValoresInsert()
  {
    String strResultado = Utils.STR_VAZIA;

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (Utils.getBooStrVazia(cln.getStrValor()))
      {
        continue;
      }

      if ((cln == this.getClnIntId()) && cln.getIntValor() < 1)
      {
        continue;
      }

      if (cln.getClnRef() != null && (cln.getIntValor() < 1))
      {
        continue;
      }

      String str = "'_cln_valor', ";

      str = str.replace("_cln_valor", cln.getStrValorSql());

      strResultado += str;
    }

    return Utils.removerUltimaLetra(strResultado, 2);
  }

  private String getSqlSelectColunaCursorAdapterId()
  {
    String strResultado = " _tbl_nome._cln_pk_nome _id";

    strResultado = strResultado.replace("_tbl_nome", this.getSqlNome());
    strResultado = strResultado.replace("_cln_pk_nome", this.getClnIntId().getSqlNome());

    return strResultado;
  }

  private String getSqlSelectColunasNomes(List<Coluna> lstCln)
  {
    if (lstCln == null)
    {
      return "*";
    }
    if (lstCln.isEmpty())
    {
      return "*";
    }

    String strResultado = Utils.STR_VAZIA;

    for (Coluna cln : lstCln)
    {
      if (cln == null)
      {
        continue;
      }

      strResultado += cln.getStrTblNomeClnNome();
    }

    return Utils.removerUltimaLetra(strResultado, 2);
  }

  private String getSqlSelectColunasNomesConsulta()
  {
    List<Coluna> lstClnAdicionada = new ArrayList<>();

    String strResultado = Utils.STR_VAZIA;

    for (Coluna cln : this.getLstClnConsulta())
    {
      if (cln == null)
      {
        continue;
      }

      strResultado += this.getSqlSelectColunasNomesConsulta(lstClnAdicionada, cln);
    }

    strResultado += this.getSqlSelectColunaCursorAdapterId();

    strResultado = strResultado.replace(" null", Utils.STR_VAZIA);

    return strResultado;
  }

  @Nullable
  private String getSqlSelectColunasNomesConsulta(List<Coluna> lstClnAdicionada, Coluna cln)
  {
    if (cln == null)
    {
      return null;
    }

    if (lstClnAdicionada.contains(cln))
    {
      return null;
    }

    lstClnAdicionada.add(cln);

    if (cln.getClnRef() == null)
    {
      return cln.getStrTblNomeClnNome();
    }

    return cln.getSqlSubSelectClnRef();
  }

  private String getSqlWhere(List<Filtro> lstFil)
  {
    if (lstFil == null)
    {
      return Utils.STR_VAZIA;
    }

    if (lstFil.isEmpty())
    {
      return Utils.STR_VAZIA;
    }

    boolean booPrimeiroTermo = true;
    String strResultado = Utils.STR_VAZIA;

    for (Filtro fil : lstFil)
    {
      if (fil == null)
      {
        continue;
      }

      strResultado += fil.getSqlFiltro(booPrimeiroTermo).concat(" ");

      booPrimeiroTermo = false;
    }

    return Utils.removerUltimaLetra(strResultado);
  }

  /**
   * Esta é a view que representa esta tabela. A primeira view da lista de views. Caso a lista não contenha nenhuma view, retorna a instância desta
   * tabela.
   *
   * @return A view principal desta tabela.
   */
  public TabelaAndroid<?> getViwPrincipal()
  {
    if (_viwPrincipal != null)
    {
      return _viwPrincipal;
    }

    if (this.getLstViwAndroid() == null)
    {
      _viwPrincipal = this;
      return _viwPrincipal;
    }

    if (this.getLstViwAndroid().isEmpty())
    {
      _viwPrincipal = this;
      return _viwPrincipal;
    }

    _viwPrincipal = this.getLstViwAndroid().get(0);

    return _viwPrincipal;
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.getClnDttAlteracao().setStrValorDefault("current_timestamp");
    this.getClnDttCadastro().setStrValorDefault("current_timestamp");
  }

  /**
   * Este método tem a responsabilidade de inicializar a lista de views desta tabela.
   */
  protected void inicializarLstViwAndroid()
  {
  }

  private void lerDominio(T objDominio)
  {
    this.limparColunas();

    if (objDominio == null)
    {
      return;
    }

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      cln.lerDominio(objDominio);
    }
  }

  public void montarMenu(Menu mnu)
  {
    if (mnu == null)
    {
      return;
    }

    this.montarMenuPesquisa(mnu);
    this.montarMenuCampo(mnu);
    this.montarMenuOrdenar(mnu);
  }

  private void montarMenuCampo(Menu mnu)
  {
    if (mnu == null)
    {
      return;
    }

    SubMenu smn = mnu.addSubMenu("Campos");

    smn.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    smn.setIcon(R.drawable.visualizar);

    this.montarMenuCampoColuna(smn);
  }

  private void montarMenuCampoColuna(SubMenu smn)
  {
    if (smn == null)
    {
      return;
    }

    for (Coluna cln : this.getLstClnOrdenado())
    {
      if (cln == null)
      {
        continue;
      }

      ((ColunaAndroid) cln).montarMenuCampo(smn);
    }
  }

  public void montarMenuItem(Menu mnu, int intRegistroId, boolean booDetalhar)
  {
    if (mnu == null)
    {
      return;
    }

    if (intRegistroId < 1)
    {
      return;
    }

    if (booDetalhar)
    {
      this.montarMenuItemDetalhar(mnu);
    }

    this.montarMenuItemAlterar(mnu, intRegistroId);
    this.montarMenuItemApagar(mnu, intRegistroId);
  }

  protected void montarMenuItemAlterar(Menu mnu, int intRegistroId)
  {
    if (mnu == null)
    {
      return;
    }

    if (this.getClsActCadastro() == null)
    {
      return;
    }

    if (!this.getBooPermitirAlterar())
    {
      return;
    }

    mnu.add(TabelaAndroid.STR_MENU_ALTERAR);
  }

  protected void montarMenuItemApagar(Menu mnu, int intRegistroId)
  {
    if (mnu == null)
    {
      return;
    }

    if (intRegistroId < 1)
    {
      return;
    }

    if (!this.getBooPermitirApagar())
    {
      return;
    }

    mnu.add(TabelaAndroid.STR_MENU_APAGAR);
  }

  private void montarMenuItemDetalhar(Menu mnu)
  {
    if (mnu == null)
    {
      return;
    }

    mnu.add(TabelaAndroid.STR_MENU_DETALHAR);
  }

  private void montarMenuOrdenar(Menu mnu)
  {
    if (mnu == null)
    {
      return;
    }

    SubMenu smn = mnu.addSubMenu("Ordenar");

    smn.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    smn.setIcon(R.drawable.ordenar);

    this.montarMenuOrdenarColuna(smn);
    this.montarMenuOrdenarDecrescente(smn);
  }

  private void montarMenuOrdenarColuna(SubMenu smn)
  {
    if (smn == null)
    {
      return;
    }

    ((ColunaAndroid) this.getClnIntId()).montarMenuOrdenar(smn);
    ((ColunaAndroid) this.getClnNome()).montarMenuOrdenar(smn);

    for (Coluna cln : this.getLstClnConsultaOrdenado())
    {
      if (cln == null)
      {
        continue;
      }

      if (!cln.getBooVisivelConsulta())
      {
        continue;
      }

      ((ColunaAndroid) cln).montarMenuOrdenar(smn);
    }
  }

  private void montarMenuOrdenarDecrescente(SubMenu smn)
  {
    if (smn == null)
    {
      return;
    }

    this.setMniOrdemDecrescente(smn.add("Ordem decrescente"));
    this.getMniOrdemDecrescente().setChecked(this.getClnOrdem().getBooOrdemDecrescente());
    this.getMniOrdemDecrescente().setCheckable(true);
  }

  private void montarMenuPesquisa(final Menu mnu)
  {
    if (mnu == null)
    {
      return;
    }

    SubMenu smn = mnu.addSubMenu(STR_MENU_PESQUISAR_POR);

    smn.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    smn.setIcon(R.drawable.opcoes_pesquisa);

    this.montarMenuPesquisa(smn);
  }

  private void montarMenuPesquisa(final SubMenu smn)
  {
    if (smn == null)
    {
      return;
    }

    for (Coluna cln : this.getLstClnOrdenado())
    {
      if (cln == null)
      {
        continue;
      }

      ((ColunaAndroid) cln).montarMenuPesquisa(smn);
    }
  }

  public Cursor pesquisar()
  {
    return this.pesquisar(this.getLstCln(), null);
  }

  public Cursor pesquisar(Coluna cln)
  {
    List<Coluna> lstCln = new ArrayList<>();

    lstCln.add(cln);

    return this.pesquisar(lstCln, null);
  }

  public Cursor pesquisar(Coluna clnFiltro, boolean booFiltro)
  {
    return this.pesquisar(clnFiltro, (booFiltro ? 1 : 0));
  }

  public Cursor pesquisar(Coluna clnFiltro, double dblFiltro)
  {
    return this.pesquisar(clnFiltro, String.valueOf(dblFiltro));
  }

  public Cursor pesquisar(Coluna clnFiltro, int intFiltro)
  {
    return this.pesquisar(clnFiltro, (double) intFiltro);
  }

  public Cursor pesquisar(Coluna cln, List<Filtro> lstFil)
  {
    List<Coluna> lstCln = new ArrayList<>();

    lstCln.add(cln);

    return this.pesquisar(lstCln, lstFil);
  }

  public Cursor pesquisar(Coluna clnFiltro, String strFiltro)
  {
    List<Filtro> lstFil = new ArrayList<>();

    lstFil.add(new Filtro(clnFiltro, strFiltro));

    return this.pesquisar(lstFil);
  }

  public Cursor pesquisar(List<Coluna> lstCln, List<Filtro> lstFil)
  {
    String sql = "select _clns_nome from _tbl_nome where _where order by _tbl_nome._order;";

    sql = sql.replace("_clns_nome", this.getSqlSelectColunasNomes(lstCln));
    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("where _where", lstFil != null && lstFil.size() > 0 ? "where _where" : Utils.STR_VAZIA);
    sql = sql.replace("_where", this.getSqlWhere(lstFil));
    sql = sql.replace("_order", this.getClnOrdem().getSqlNome());

    return this.getDbe().execSqlComRetorno(sql);
  }

  public Cursor pesquisar(List<Filtro> lstFil)
  {
    return this.pesquisar(this.getLstCln(), lstFil);
  }

  /**
   * Retorna os dados que serão mostrados na tela de consulta.
   *
   * @return Os dados que serão mostrados na tela de consulta.
   */
  public Cursor pesquisarConsulta()
  {
    this.carregarLstFilConsulta(this.getLstFilConsulta());

    String sql = "select _clns_nome from _tbl_nome where _where order by _order _asc_desc;";

    sql = sql.replace("_clns_nome", this.getSqlSelectColunasNomesConsulta());
    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("where _where", (!this.getLstFilConsulta().isEmpty()) ? "where _where" : Utils.STR_VAZIA);
    sql = sql.replace("_where", this.getSqlWhere(this.getLstFilConsulta()));
    sql = sql.replace("_order", this.getClnOrdem().getSqlNome());
    sql = sql.replace("_asc_desc", !this.getClnOrdem().getBooOrdemDecrescente() ? "asc" : "desc");

    Cursor crsResultado = this.getDbe().execSqlComRetorno(sql);

    this.getLstFilConsulta().clear();

    return crsResultado;
  }

  private List<T> pesquisarDominio(Cursor crs)
  {
    if (crs == null)
    {
      return null;
    }

    List<T> lstResultado = new ArrayList<>();

    do
    {
      T objDominio = this.carregarDominio(crs);

      if (objDominio == null)
      {
        continue;
      }
      if (objDominio.getIntId() < 1)
      {
        continue;
      }

      lstResultado.add(objDominio);

    }
    while (crs.moveToNext());

    return lstResultado;
  }

  public List<T> pesquisarDominio(Coluna clnFiltro, boolean booFiltro)
  {
    return this.pesquisarDominio(clnFiltro, (booFiltro ? 1 : 0));
  }

  public List<T> pesquisarDominio(Coluna clnFiltro, double dblFiltro)
  {
    return this.pesquisarDominio(clnFiltro, String.valueOf(dblFiltro));
  }

  public List<T> pesquisarDominio(Coluna clnFiltro, GregorianCalendar dttFiltro)
  {
    if (dttFiltro == null)
    {
      return null;
    }

    return this.pesquisarDominio(clnFiltro, Utils.getStrDataFormatada(dttFiltro, EnmDataFormato.YYYY_MM_DD_HH_MM_SS));
  }

  public List<T> pesquisarDominio(Coluna clnFiltro, int intFiltro)
  {
    return this.pesquisarDominio(clnFiltro, (double) intFiltro);
  }

  public List<T> pesquisarDominio(Coluna clnFiltro, String strFiltro)
  {
    return this.pesquisarDominio(new Filtro(clnFiltro, strFiltro));
  }

  public List<T> pesquisarDominio(Filtro fil)
  {
    if (fil == null)
    {
      return null;
    }

    List<Filtro> lstFil = new ArrayList<>();

    lstFil.add(fil);

    return this.pesquisarDominio(lstFil);
  }

  public List<T> pesquisarDominio(List<Filtro> lstFil)
  {
    if (lstFil == null)
    {
      return null;
    }

    Cursor crs = this.pesquisar(lstFil);

    if (crs == null)
    {
      return null;
    }

    if (!crs.moveToFirst())
    {
      return null;
    }

    List<T> lstObjDominioResultado = this.pesquisarDominio(crs);

    crs.close();

    return lstObjDominioResultado;
  }

  public boolean processarMenu(ActMain act, MenuItem mni)
  {
    if (act == null)
    {
      return false;
    }

    if (mni == null)
    {
      return false;
    }

    if (this.processarMenuPesquisa(act, mni))
    {
      return false;
    }

    if (this.processarMenuCampo(act, mni))
    {
      ((ActConsulta) act).atualizarLista();
      return false;
    }

    if (this.processarMenuOrdenar(act, mni))
    {
      ((ActConsulta) act).atualizarLista();
      return false;
    }

    if (mni.equals(this.getMniOrdemDecrescente()))
    {
      return this.processarMenuOrdenarDecrescente((ActConsulta) act);
    }

    switch (mni.getTitle().toString())
    {
      case TabelaAndroid.STR_MENU_ADICIONAR:
        return this.processarMenuAdicionar(act);
    }

    return false;
  }

  private boolean processarMenuAdicionar(ActMain act)
  {
    if (act == null)
    {
      return false;
    }

    if (this.getClsActCadastro() == null)
    {
      return false;
    }

    Intent itt = new Intent(act, this.getClsActCadastro());

    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, this.getIntRegistroRefId());

    act.startActivity(itt);

    return true;
  }

  private boolean processarMenuCampo(ActMain act, MenuItem mni)
  {
    if (act == null)
    {
      return false;
    }

    if (!(act instanceof ActConsulta))
    {
      return false;
    }

    if (mni == null)
    {
      return false;
    }

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (!mni.equals(((ColunaAndroid) cln).getMniCampo()))
      {
        continue;
      }

      ((ColunaAndroid) cln).processarMenuCampo(mni);

      act.invalidateOptionsMenu();

      return true;
    }

    return false;
  }

  public boolean processarMenuItem(ActMain act, MenuItem mni, int intRegistroId)
  {
    if (act == null)
    {
      return false;
    }

    if (mni == null)
    {
      return false;
    }

    if (intRegistroId < 1)
    {
      return false;
    }

    switch (mni.getTitle().toString())
    {
      case TabelaAndroid.STR_MENU_ALTERAR:
        return this.processarMenuItemAlterar(act, intRegistroId);

      case TabelaAndroid.STR_MENU_APAGAR:
        return this.processarMenuItemApagar(act, intRegistroId);

      case TabelaAndroid.STR_MENU_DETALHAR:
        return this.processarMenuItemDetalhar(act, intRegistroId);
    }

    return false;
  }

  private boolean processarMenuItemAlterar(ActMain act, int intRegistroId)
  {
    if (act == null)
    {
      return false;
    }

    if (intRegistroId < 1)
    {
      return false;
    }

    if (this.getClsActCadastro() == null)
    {
      return false;
    }

    Intent itt = new Intent(act, this.getClsActCadastro());

    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_ID, intRegistroId);
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, this.getIntRegistroRefId());
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_TBL_OBJETO_ID, this.getIntObjetoId());

    act.startActivity(itt);

    return true;
  }

  private boolean processarMenuItemApagar(ActMain act, int intId)
  {
    if (act == null)
    {
      return false;
    }

    if (intId < 1)
    {
      return false;
    }

    this.apagar(intId);

    if (!(act instanceof ActDetalhe))
    {
      return true;
    }

    act.finish();

    return true;
  }

  private boolean processarMenuItemDetalhar(ActMain act, int intId)
  {
    if (act == null)
    {
      return false;
    }

    if (intId < 1)
    {
      return false;
    }

    this.abrirDetalhe(act, intId);

    return true;
  }

  private boolean processarMenuOrdenar(ActMain act, MenuItem mni)
  {
    if (act == null)
    {
      return false;
    }

    if (!(act instanceof ActConsulta))
    {
      return false;
    }

    if (mni == null)
    {
      return false;
    }

    if (mni.isChecked())
    {
      return false;
    }

    if (mni.equals(((ColunaAndroid) this.getClnIntId()).getMniOrdenar()))
    {
      ((ColunaAndroid) this.getClnIntId()).processarMenuOrdenar(mni);
      return true;
    }

    if (mni.equals(((ColunaAndroid) this.getClnNome()).getMniOrdenar()))
    {
      ((ColunaAndroid) this.getClnNome()).processarMenuOrdenar(mni);
      return true;
    }

    for (Coluna cln : this.getLstClnConsulta())
    {
      if (cln == null)
      {
        continue;
      }

      if (!mni.equals(((ColunaAndroid) cln).getMniOrdenar()))
      {
        continue;
      }

      ((ColunaAndroid) cln).processarMenuOrdenar(mni);

      return true;
    }

    return false;
  }

  private boolean processarMenuOrdenarDecrescente(ActConsulta actConsulta)
  {
    if (actConsulta == null)
    {
      return false;
    }

    this.getMniOrdemDecrescente().setChecked(!this.getMniOrdemDecrescente().isChecked());
    this.getClnOrdem().setBooOrdemDecrescente(this.getMniOrdemDecrescente().isChecked());

    actConsulta.atualizarLista();

    return true;
  }

  private boolean processarMenuPesquisa(ActMain act, MenuItem mni)
  {
    if (act == null)
    {
      return false;
    }

    if (!(act instanceof ActConsulta))
    {
      return false;
    }

    if (mni == null)
    {
      return false;
    }

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (!mni.equals(((ColunaAndroid) cln).getMniPesquisa()))
      {
        continue;
      }

      ((ColunaAndroid) cln).processarMenuPesquisa(mni);

      act.invalidateOptionsMenu();

      return true;
    }

    return false;
  }

  public TabelaAndroid recuperar(Coluna clnFiltro, boolean booFiltro)
  {
    return this.recuperar(clnFiltro, (booFiltro ? 1 : 0));
  }

  public TabelaAndroid recuperar(Coluna clnFiltro, double dblFiltro)
  {
    return this.recuperar(clnFiltro, String.valueOf(dblFiltro));
  }

  public TabelaAndroid recuperar(Coluna clnFiltro, GregorianCalendar dttFiltro)
  {
    if (dttFiltro == null)
    {
      return this;
    }

    this.recuperar(clnFiltro, Utils.getStrDataFormatada(dttFiltro, EnmDataFormato.YYYY_MM_DD_HH_MM_SS));

    return this;
  }

  public TabelaAndroid recuperar(Coluna clnFiltro, int intFiltro)
  {
    return this.recuperar(clnFiltro, (double) intFiltro);
  }

  public TabelaAndroid recuperar(Coluna clnFiltro, String strFiltro)
  {
    return this.recuperar(new Filtro(clnFiltro, strFiltro));
  }

  public TabelaAndroid recuperar(Filtro fil)
  {
    if (fil == null)
    {
      return this;
    }

    List<Filtro> lstFil = new ArrayList<>();

    lstFil.add(fil);

    this.recuperar(lstFil);

    return this;
  }

  public TabelaAndroid recuperar(int intId)
  {
    return this.recuperar(this.getClnIntId(), intId);
  }

  public TabelaAndroid recuperar(List<Filtro> lstFil)
  {
    String sql = "select _clns_nome from _tbl_nome where _where order by _tbl_nome._order;";

    sql = sql.replace("_clns_nome", this.getSqlColunasNomes());
    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("_where", this.getSqlWhere(lstFil));
    sql = sql.replace("_order", this.getClnIntId().getSqlNome());

    Cursor crs = this.getDbe().execSqlComRetorno(sql);

    this.carregarDados(crs);

    crs.close();

    return this;
  }

  public T recuperarDominio(Coluna clnFiltro, boolean booFiltro)
  {
    return this.recuperarDominio(clnFiltro, (booFiltro ? 1 : 0));
  }

  public T recuperarDominio(Coluna clnFiltro, double dblFiltro)
  {
    return this.recuperarDominio(clnFiltro, String.valueOf(dblFiltro));
  }

  public T recuperarDominio(Coluna clnFiltro, GregorianCalendar dttFiltro)
  {
    if (dttFiltro == null)
    {
      return null;
    }

    return this.recuperarDominio(clnFiltro, Utils.getStrDataFormatada(dttFiltro, EnmDataFormato.YYYY_MM_DD_HH_MM_SS));
  }

  public T recuperarDominio(Coluna clnFiltro, int intFiltro)
  {
    return this.recuperarDominio(clnFiltro, (double) intFiltro);
  }

  public T recuperarDominio(Coluna clnFiltro, String strFiltro)
  {
    return this.recuperarDominio(new Filtro(clnFiltro, strFiltro));
  }

  public T recuperarDominio(Filtro fil)
  {
    if (fil == null)
    {
      return null;
    }

    List<Filtro> lstFil = new ArrayList<>();

    lstFil.add(fil);

    return this.recuperarDominio(lstFil);
  }

  public T recuperarDominio(int intId)
  {
    return this.recuperarDominio(this.getClnIntId(), intId);
  }

  public T recuperarDominio(List<Filtro> lstFil)
  {
    if (lstFil == null)
    {
      return null;
    }

    List<T> lstObjDominio = this.pesquisarDominio(lstFil);

    if (lstObjDominio == null)
    {
      return null;
    }

    if (lstObjDominio.size() < 1)
    {
      return null;
    }

    return lstObjDominio.get(0);
  }

  public boolean salvar(boolean booValidar)
  {
    if (booValidar && !this.validarDados())
    {
      return false;
    }

    if (!this.getBooRegistroExiste(this.getClnIntId().getIntValor()))
    {
      this.salvarInsert();
      return true;
    }

    this.salvarUpdate();

    return true;
  }

  public boolean salvar(T objDominio, boolean booValidar)
  {
    if (objDominio == null)
    {
      return false;
    }

    this.lerDominio(objDominio);

    boolean booResultado = this.salvar(booValidar);

    objDominio.setIntId(this.getClnIntId().getIntValor());

    return booResultado;
  }

  public void salvarAleatorio()
  {
    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      switch (cln.getEnmTipo())
      {
        case INTEGER:
          cln.setStrValor(Utils.getStrAleatoria(5, EnmStringTipo.NUMERICO));
          break;

        case REAL:
          cln.setStrValor(Utils.getStrAleatoria(5, Utils.EnmStringTipo.NUMERICO));
          break;

        case NUMERIC:
          cln.setStrValor(Utils.getStrAleatoria(5, Utils.EnmStringTipo.NUMERICO));
          break;

        default:
          cln.setStrValor(Utils.getStrAleatoria(5, EnmStringTipo.ALPHA));
          break;
      }
    }

    this.salvar(false);
  }

  private void salvarInsert()
  {
    String sql = "insert into _tbl_nome (_cln_nome) values (_cln_valor);";

    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("_cln_nome", this.getSqlColunasNomesInsert());
    sql = sql.replace("_cln_valor", this.getSqlColunasValoresInsert());

    this.getDbe().execSql(sql);

    sql = "select last_insert_rowid();";

    int intId = this.getDbe().execSqlGetInt(sql);

    this.getClnIntId().setIntValor(intId);

    this.dispararEvtOnAdicionarAtualizarListener(true);
  }

  private void salvarUpdate()
  {
    String sql = "update _tbl_nome set _cln_nome_valor where _cln_pk_nome = '_registro_id';";

    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("_cln_nome_valor", this.getSqlColunasNomesValorUpdate());
    sql = sql.replace("_cln_pk_nome", this.getClnIntId().getSqlNome());
    sql = sql.replace("_registro_id", this.getClnIntId().getStrValorSql());

    this.getDbe().execSql(sql);

    this.dispararEvtOnAdicionarAtualizarListener(false);
  }

  public void setBooSincronizada(boolean booSincronizada)
  {
    _booSincronizada = booSincronizada;
  }

  /**
   * Indica a coluna que será utilizada como pesquisa.
   *
   * @param clnPesquisa Coluna que será utilizada como pesquisa.
   */
  void setClnPesquisa(ColunaAndroid clnPesquisa)
  {
    _clnPesquisa = clnPesquisa;
  }

  protected void setClsActCadastro(Class<? extends ActMain> clsActFrm)
  {
    _clsActCadastro = clsActFrm;
  }

  public void setIntRegistroRefId(int intRegistroRefId)
  {
    _intRegistroRefId = intRegistroRefId;
  }

  private void setMniOrdemDecrescente(MenuItem mniOrdemDecrescente)
  {
    _mniOrdemDecrescente = mniOrdemDecrescente;
  }

  public void setObjDb(DataBaseAndroid objDb)
  {
    _dbe = objDb;
  }

  /**
   * Este métod é chamado quando o usário termina de preencher os dados nos campos da Activity de cadastro e clica na opção salvar.
   *
   * @return True caso tudo esteja satisfatório para o salvamento do registro, ou false caso contrário.
   */
  public boolean validarDados()
  {
    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (!((ColunaAndroid) cln).validarDados())
      {
        return false;
      }
    }

    return true;
  }
}