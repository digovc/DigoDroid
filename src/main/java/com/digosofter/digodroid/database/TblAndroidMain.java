package com.digosofter.digodroid.database;

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
import com.digosofter.digodroid.database.dominio.DominioAndroidMain;
import com.digosofter.digodroid.database.tabela.TblReservaCodigo;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.Utils.EnmDataFormato;
import com.digosofter.digojava.database.Coluna;
import com.digosofter.digojava.database.Filtro;
import com.digosofter.digojava.database.OnChangeArg;
import com.digosofter.digojava.database.TabelaMain;

import java.util.ArrayList;
import java.util.Calendar;
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
public abstract class TblAndroidMain<T extends DominioAndroidMain> extends TabelaMain<T>
{
  public static final String STR_MENU_ADICIONAR = "Adicionar";
  public static final String STR_MENU_APAGAR = "Apagar";
  private static final String STR_MENU_ALTERAR = "Alterar";
  private static final String STR_MENU_DETALHAR = "Ver detalhes";
  private static final String STR_MENU_PESQUISAR_POR = "Pesquisar por";

  private boolean _booMostrarSalvarNovo;
  private ColunaAndroid _clnBooAtivo;
  private ColunaAndroid _clnDttAlteracao;
  private ColunaAndroid _clnDttCadastro;
  private ColunaAndroid _clnIntId;
  private ColunaAndroid _clnIntUsuarioAlteracaoId;
  private ColunaAndroid _clnIntUsuarioCadastroId;
  private ColunaAndroid _clnPesquisa;
  private ColunaAndroid _clnStrObservacao;
  private Class<? extends ActMain> _clsActCadastro;
  private DbeAndroidMain _dbe;
  private List<ViewAndroid> _lstViwAndroid;
  private MenuItem _mniOrdemDecrescente;
  private String _sqlCursorAdapterId;
  private TblAndroidMain<?> _viwPrincipal;

  /**
   * Constroe uma nova instância dessa tabela. Este processo cria também a tabela e suas colunas no banco de dados SQLite caso ela não exista.
   *
   * @param strNome Nome da tabela no banco de dados.
   */
  protected TblAndroidMain(String strNome, DbeAndroidMain dbeAndroid)
  {
    super(strNome, dbeAndroid);
  }

  /**
   * Atalho para {@link #abrirCadastro(ActMain, int, TblAndroidMain, int)}
   *
   * @param act Activity "parent" da tela de cadastro que será aberta.
   */
  public void abrirCadastro(final ActMain act)
  {
    this.abrirCadastro(act, 0, null, 0);
  }

  /**
   * Atalho para {@link #abrirCadastro(ActMain, int, TblAndroidMain, int)}
   *
   * @param act Activity "parent" da tela de cadastro que será aberta.
   * @param intId Código do registro para alteração.
   */
  public void abrirCadastro(final ActMain act, int intId)
  {
    this.abrirCadastro(act, intId, null, 0);
  }

  /**
   * Abre a tela de cadastro para que um novo consulta_item seja inserido.
   *
   * @param act Activity "parent" que está chamando este novo activity de cadastro.
   * @param intRegistroId Código do registro, no caso de ser uma alteração num registro já salvo.
   * @param intRegistroRefId Código do registro de referência caso este cadastro seja de um consulta_item ou se esse tem alguma ligação com outra
   * tabela.
   */
  public void abrirCadastro(final ActMain act, int intRegistroId, TblAndroidMain tblPai, int intRegistroRefId)
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

    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_BOO_MOSTRAR_SALVAR_NOVO, (this.getBooMostrarSalvarNovo() || (intRegistroRefId > 0)));
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_ID, intRegistroId);
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_REGISTRO_REF_ID, intRegistroRefId);
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_TBL_OBJETO_ID, this.getIntObjetoId());
    itt.putExtra(ActCadastroMain.STR_EXTRA_IN_INT_TBL_PAI_OBJETO_ID, (tblPai != null) ? tblPai.getIntObjetoId() : -1);

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
  public void abrirConsulta(ActMain act, TblAndroidMain tblPai, int intRegistroRefId)
  {
    if (act == null)
    {
      return;
    }

    if (intRegistroRefId < 1)
    {
      return;
    }

    Intent itt = new Intent();

    itt.putExtra(ActConsulta.STR_EXTRA_IN_BOO_REGISTRO_SELECIONAVEL, false);
    itt.putExtra(ActConsulta.STR_EXTRA_IN_INT_REGISTRO_REF_ID, intRegistroRefId);
    itt.putExtra(ActConsulta.STR_EXTRA_IN_INT_TBL_PAI_OBJETO_ID, (tblPai != null) ? tblPai.getIntObjetoId() : -1);

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

    this.setViwPrincipal(null);

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

    String sql = "delete from _tbl_nome where _tbl_nome._cln_nome = '_record_id';";

    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("_cln_nome", this.getClnIntId().getSqlNome());
    sql = sql.replace("_record_id", String.valueOf(intRegistroId));

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
   * @param actConsulta
   * @param lstFilConsulta Ponteiro para o atributo {@link @getLstFilConsulta} desta tabela.
   */
  protected void carregarLstFilConsulta(final ActConsulta actConsulta, List<Filtro> lstFilConsulta)
  {
    this.carregarLstFilConsultaClnPesquisa(lstFilConsulta);
    this.carregarLstFilConsultaClnRef(actConsulta, lstFilConsulta);
  }

  private void carregarLstFilConsultaClnPesquisa(final List<Filtro> lstFilConsulta)
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

  private void carregarLstFilConsultaClnRef(final ActConsulta actConsulta, final List<Filtro> lstFilConsulta)
  {
    if (actConsulta == null)
    {
      return;
    }

    if (actConsulta.getTblPai() == null)
    {
      return;
    }

    if (actConsulta.getIntRegistroRefId() < 1)
    {
      return;
    }

    for (Coluna cln : this.getLstCln())
    {
      if (this.carregarLstFilConsultaClnRef(actConsulta, lstFilConsulta, cln))
      {
        return;
      }
    }

    // Adiciona filtro "impossível" caso não encontre uma coluna de referência com a tabela pai.
    lstFilConsulta.add(new Filtro(this.getClnIntId(), -1));
  }

  private boolean carregarLstFilConsultaClnRef(final ActConsulta actConsulta, final List<Filtro> lstFilConsulta, final Coluna cln)
  {
    if (cln == null)
    {
      return false;
    }

    if (cln.getClnRef() == null)
    {
      return false;
    }

    if (cln.getClnRef().getTbl() == null)
    {
      return false;
    }

    if (!cln.getClnRef().getTbl().getTblPrincipal().equals(actConsulta.getTblPai()))
    {
      return false;
    }

    lstFilConsulta.add(new Filtro(cln, actConsulta.getIntRegistroRefId()));

    return true;
  }

  private void carregarValorDefault()
  {
    if (this.getLstCln() == null)
    {
      return;
    }

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      cln.carregarValorDefault();
    }
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
    sql = sql.replace("_cln_pk_tipo", this.getClnIntId().getSqlTipo());

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

  public int duplicar(final int intId)
  {
    if (intId < 1)
    {
      return 0;
    }

    this.recuperar(intId);

    this.getClnIntId().limpar();

    return this.salvar().getClnIntId().getIntValor();
  }

  public boolean getBooCodigoDisponivel()
  {
    return TblReservaCodigo.getI().getBooCodigoDisponivel(this);
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

  private boolean getBooMostrarSalvarNovo()
  {
    return _booMostrarSalvarNovo;
  }

  public boolean getBooRegistroExiste(int intRegistroId)
  {
    if (intRegistroId < 1)
    {
      return false;
    }

    String sql = "select 1 from _tbl_nome where _cln_pk_nome = '_record_id';";

    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("_cln_pk_nome", this.getClnIntId().getSqlNome());
    sql = sql.replace("_record_id", String.valueOf(intRegistroId));

    return this.getDbe().execSqlGetBoo(sql);
  }

  public ColunaAndroid getClnBooAtivo()
  {
    if (_clnBooAtivo != null)
    {
      return _clnBooAtivo;
    }

    _clnBooAtivo = new ColunaAndroid("boo_ativo", this, Coluna.EnmTipo.BOOLEAN);

    return _clnBooAtivo;
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

    _clnDttCadastro = new ColunaAndroid("dtt_cadastro", this, Coluna.EnmTipo.DATE_TIME);

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

  public ColunaAndroid getClnIntUsuarioAlteracaoId()
  {
    if (_clnIntUsuarioAlteracaoId != null)
    {
      return _clnIntUsuarioAlteracaoId;
    }

    _clnIntUsuarioAlteracaoId = new ColunaAndroid("int_usuario_alteracao_id", this, Coluna.EnmTipo.BIGINT);

    return _clnIntUsuarioAlteracaoId;
  }

  public ColunaAndroid getClnIntUsuarioCadastroId()
  {
    if (_clnIntUsuarioCadastroId != null)
    {
      return _clnIntUsuarioCadastroId;
    }

    _clnIntUsuarioCadastroId = new ColunaAndroid("int_usuario_cadastro_id", this, Coluna.EnmTipo.BIGINT);

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

  public ColunaAndroid getClnStrObservacao()
  {
    if (_clnStrObservacao != null)
    {
      return _clnStrObservacao;
    }

    _clnStrObservacao = new ColunaAndroid("str_observacao", this, Coluna.EnmTipo.TEXT);

    return _clnStrObservacao;
  }

  public Class<? extends ActMain> getClsActCadastro()
  {
    return _clsActCadastro;
  }

  @Override
  public DbeAndroidMain getDbe()
  {
    if (_dbe != null)
    {
      return _dbe;
    }

    if (super.getDbe() == null)
    {
      return null;
    }

    _dbe = (DbeAndroidMain) super.getDbe();

    return _dbe;
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

    this.inicializarLstViwAndroid(_lstViwAndroid);

    return _lstViwAndroid;
  }

  MenuItem getMniOrdemDecrescente()
  {
    return _mniOrdemDecrescente;
  }

  private String getSqlClnNomeInsert()
  {
    StringBuilder stbResultado = new StringBuilder();

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (Utils.getBooStrVazia(cln.getSqlNomeInsert()))
      {
        continue;
      }

      stbResultado.append(cln.getSqlNomeInsert());
    }

    return Utils.removerUltimaLetra(stbResultado.toString(), 2);
  }

  private String getSqlClnSelect()
  {
    List<Coluna> lstClnAdicionada = new ArrayList<>();

    StringBuilder stbResultado = new StringBuilder();

    for (Coluna cln : this.getLstClnConsulta())
    {
      if (cln == null)
      {
        continue;
      }

      this.getSqlClnSelect(lstClnAdicionada, cln, stbResultado);
    }

    stbResultado.append(this.getSqlCursorAdapterId());

    return stbResultado.toString().replace(" null", Utils.STR_VAZIA);
  }

  private void getSqlClnSelect(List<Coluna> lstClnAdicionada, Coluna cln, final StringBuilder stb)
  {
    if (cln == null)
    {
      return;
    }

    if (lstClnAdicionada.contains(cln))
    {
      return;
    }

    lstClnAdicionada.add(cln);

    if (cln.getClnRef() == null)
    {
      stb.append(cln.getSqlSelect());
    }

    stb.append(cln.getSqlSubSelect());
  }

  private String getSqlClnSelect(List<Coluna> lstCln)
  {
    if (lstCln == null)
    {
      return "*";
    }

    if (lstCln.isEmpty())
    {
      return "*";
    }

    StringBuilder stbResultado = new StringBuilder();

    for (Coluna cln : lstCln)
    {
      if (cln == null)
      {
        continue;
      }

      stbResultado.append(cln.getSqlSelect());
    }

    return Utils.removerUltimaLetra(stbResultado.toString(), 2);
  }

  private String getSqlClnUpdate()
  {
    StringBuilder stbResultado = new StringBuilder();

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (Utils.getBooStrVazia(cln.getSqlUpdate()))
      {
        continue;
      }

      stbResultado.append(cln.getSqlUpdate());
    }

    return Utils.removerUltimaLetra(stbResultado.toString(), 2);
  }

  private String getSqlClnValorInsert()
  {
    StringBuilder stbResultado = new StringBuilder();

    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (Utils.getBooStrVazia(cln.getSqlValorInsert()))
      {
        continue;
      }

      stbResultado.append(cln.getSqlValorInsert());
    }

    return Utils.removerUltimaLetra(stbResultado.toString(), 2);
  }

  private String getSqlCursorAdapterId()
  {
    if (_sqlCursorAdapterId != null)
    {
      return _sqlCursorAdapterId;
    }

    return _sqlCursorAdapterId = String.format(" %s.%s _id", this.getSqlNome(), this.getClnIntId().getSqlNome());
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
  public TblAndroidMain<?> getViwPrincipal()
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

    if (this instanceof ViewAndroid)
    {
      return;
    }

    this.getClnBooAtivo().setBooValorDefault(true);
    this.getClnStrObservacao().setStrNomeExibicao("Observação");
  }

  /**
   * Este método tem a responsabilidade de inicializar a lista de views desta tabela.
   *
   * @param lstViw
   */
  protected void inicializarLstViwAndroid(final List<ViewAndroid> lstViw)
  {
  }

  public void lerDominio(T objDominio)
  {
    this.limparDados();

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

  public void montarMenuItem(Menu mnu, int intRegistroId, boolean booActConsulta)
  {
    if (mnu == null)
    {
      return;
    }

    if (intRegistroId < 1)
    {
      return;
    }

    this.montarMenuItemDetalhar(mnu, booActConsulta);
    this.montarMenuItemAlterar(mnu, intRegistroId);
    this.montarMenuItemApagar(mnu, intRegistroId);
  }

  protected void montarMenuItemAlterar(Menu mnu, int intRegistroId)
  {
    if (this.getClsActCadastro() == null)
    {
      return;
    }

    if (!this.getBooPermitirAlterar())
    {
      return;
    }

    mnu.add(TblAndroidMain.STR_MENU_ALTERAR);
  }

  protected void montarMenuItemApagar(Menu mnu, int intRegistroId)
  {
    if (!this.getBooPermitirApagar())
    {
      return;
    }

    mnu.add(TblAndroidMain.STR_MENU_APAGAR);
  }

  private void montarMenuItemDetalhar(Menu mnu, final boolean booActConsulta)
  {
    if (!booActConsulta)
    {
      return;
    }

    mnu.add(TblAndroidMain.STR_MENU_DETALHAR);
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

    this.getClnIntId().montarMenuOrdenar(smn);

    ((ColunaAndroid) this.getClnNome()).montarMenuOrdenar(smn);

    for (Coluna cln : this.getLstClnConsulta())
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

    this.getMniOrdemDecrescente().setCheckable(true);

    for (Coluna cln : this.getLstClnOrdem())
    {
      if (Coluna.EnmOrdem.NONE.equals(cln.getEnmOrdem()))
      {
        continue;
      }

      this.getMniOrdemDecrescente().setChecked(Coluna.EnmOrdem.DECRESCENTE.equals(cln.getEnmOrdem()));
      return;
    }
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

  public Cursor pesquisar(final int intId)
  {
    return this.pesquisar(this.getClnIntId(), intId);
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
    String sql = "select _clns_nome from _tbl_nome where _where order by _order_by;";

    sql = sql.replace("_clns_nome", this.getSqlClnSelect(lstCln));
    sql = sql.replace("_tbl_nome", this.getSqlNome());

    sql = sql.replace("where _where", (lstFil != null && lstFil.size() > 0) ? "where _where" : Utils.STR_VAZIA);

    sql = sql.replace("_where", this.getSqlWhere(lstFil));

    sql = sql.replace("order by _order_by", (!Utils.getBooStrVazia(this.getSqlOrderBy()) ? "order by _order_by" : Utils.STR_VAZIA));

    sql = sql.replace("_order_by", (!Utils.getBooStrVazia(this.getSqlOrderBy()) ? Utils.removerUltimaLetra(this.getSqlOrderBy(), 2) : Utils.STR_VAZIA));

    return this.getDbe().execSqlComRetorno(sql);
  }

  public Cursor pesquisar(List<Filtro> lstFil)
  {
    return this.pesquisar(this.getLstCln(), lstFil);
  }

  /**
   * Retorna os dados que serão mostrados na tela de consulta.
   *
   * @param actConsulta
   * @return Os dados que serão mostrados na tela de consulta.
   */
  public Cursor pesquisarConsulta(final ActConsulta actConsulta)
  {
    if (actConsulta == null)
    {
      return null;
    }

    this.carregarLstFilConsulta(actConsulta, this.getLstFilConsulta());

    String sql = "select _clns_nome from _tbl_nome where _where order by _order_by;";

    sql = sql.replace("_clns_nome", this.getSqlClnSelect());

    sql = sql.replace("_tbl_nome", this.getSqlNome());

    sql = sql.replace("where _where", (!this.getLstFilConsulta().isEmpty()) ? "where _where" : Utils.STR_VAZIA);

    sql = sql.replace("_where", this.getSqlWhere(this.getLstFilConsulta()));

    sql = sql.replace("order by _order_by", (!Utils.getBooStrVazia(this.getSqlOrderBy()) ? "order by _order_by" : Utils.STR_VAZIA));

    sql = sql.replace("_order_by", (!Utils.getBooStrVazia(this.getSqlOrderBy()) ? Utils.removerUltimaLetra(this.getSqlOrderBy(), 2) : Utils.STR_VAZIA));

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
      case TblAndroidMain.STR_MENU_ADICIONAR:
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

    if (!(act instanceof ActConsulta))
    {
      return false;
    }

    ActConsulta actConsulta = (ActConsulta) act;

    this.abrirCadastro(act, 0, actConsulta.getTblPai(), actConsulta.getIntRegistroRefId());
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
      if (this.processarMenuCampo(act, mni, cln))
      {
        return true;
      }
    }

    return false;
  }

  private boolean processarMenuCampo(final ActMain act, final MenuItem mni, final Coluna cln)
  {
    if (cln == null)
    {
      return false;
    }

    if (!mni.equals(((ColunaAndroid) cln).getMniCampo()))
    {
      return false;
    }

    ((ColunaAndroid) cln).processarMenuCampo(mni);

    act.invalidateOptionsMenu();

    return true;
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
      case TblAndroidMain.STR_MENU_ALTERAR:
        return this.processarMenuItemAlterar(act, intRegistroId);

      case TblAndroidMain.STR_MENU_APAGAR:
        return this.processarMenuItemApagar(act, intRegistroId);

      case TblAndroidMain.STR_MENU_DETALHAR:
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

    if (!(act instanceof ActConsulta))
    {
      return false;
    }

    ActConsulta actConsulta = (ActConsulta) act;

    this.abrirCadastro(act, intRegistroId, actConsulta.getTblPai(), actConsulta.getIntRegistroRefId());
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

    for (Coluna cln : this.getLstCln())
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

    for (Coluna cln : this.getLstCln())
    {
      if (Coluna.EnmOrdem.NONE.equals(cln.getEnmOrdem()))
      {
        continue;
      }

      cln.setEnmOrdem(this.getMniOrdemDecrescente().isChecked() ? Coluna.EnmOrdem.DECRESCENTE : Coluna.EnmOrdem.CRESCENTE);
      break;
    }

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

  public TblAndroidMain recuperar(Coluna clnFiltro, boolean booFiltro)
  {
    return this.recuperar(clnFiltro, (booFiltro ? 1 : 0));
  }

  public TblAndroidMain recuperar(Coluna clnFiltro, double dblFiltro)
  {
    return this.recuperar(clnFiltro, String.valueOf(dblFiltro));
  }

  public TblAndroidMain recuperar(Coluna clnFiltro, GregorianCalendar dttFiltro)
  {
    if (dttFiltro == null)
    {
      return this;
    }

    this.recuperar(clnFiltro, Utils.getStrDataFormatada(dttFiltro, EnmDataFormato.YYYY_MM_DD_HH_MM_SS));

    return this;
  }

  public TblAndroidMain recuperar(Coluna clnFiltro, int intFiltro)
  {
    return this.recuperar(clnFiltro, (double) intFiltro);
  }

  public TblAndroidMain recuperar(Coluna clnFiltro, String strFiltro)
  {
    return this.recuperar(new Filtro(clnFiltro, strFiltro));
  }

  public TblAndroidMain recuperar(Filtro fil)
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

  public TblAndroidMain recuperar(int intId)
  {
    return this.recuperar(this.getClnIntId(), intId);
  }

  public TblAndroidMain recuperar(List<Filtro> lstFil)
  {
    this.limparDados();

    if (this.getDbe() == null)
    {
      return this;
    }

    String sql = "select * from _tbl_nome where _where order by _tbl_nome._order;";

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

  public TblAndroidMain salvar()
  {
    this.carregarValorDefault();

    if (this.getClnDttCadastro().getBooVazia())
    {
      this.getClnDttCadastro().setDttValor(Calendar.getInstance());
    }

    if (this.getClnDttAlteracao().getBooVazia())
    {
      this.getClnDttAlteracao().setDttValor(Calendar.getInstance());
    }

    if (!this.getBooRegistroExiste(this.getClnIntId().getIntValor()))
    {
      this.salvarInsert();
      return this;
    }

    this.salvarUpdate();
    return this;
  }

  public T salvar(final T objDominio)
  {
    if (objDominio == null)
    {
      return null;
    }

    this.lerDominio(objDominio);

    this.salvar();

    objDominio.setIntId(this.getClnIntId().getIntValor());

    return objDominio;
  }

  private void salvarInsert()
  {
    String sql = "insert into _tbl_nome (_cln_nome) values (_cln_valor);";

    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("_cln_nome", this.getSqlClnNomeInsert());
    sql = sql.replace("_cln_valor", this.getSqlClnValorInsert());

    this.getDbe().execSql(sql);

    sql = "select last_insert_rowid();";

    int intId = this.getDbe().execSqlGetInt(sql);

    this.getClnIntId().setIntValor(intId);

    this.dispararEvtOnAdicionarAtualizarListener(true);
  }

  private void salvarUpdate()
  {
    String sql = "update _tbl_nome set _cln_nome_valor where _cln_pk_nome = '_record_id';";

    sql = sql.replace("_tbl_nome", this.getSqlNome());
    sql = sql.replace("_cln_nome_valor", this.getSqlClnUpdate());
    sql = sql.replace("_cln_pk_nome", this.getClnIntId().getSqlNome());
    sql = sql.replace("_record_id", this.getClnIntId().getSqlValor());

    this.getDbe().execSql(sql);

    this.dispararEvtOnAdicionarAtualizarListener(false);
  }

  protected void setBooMostrarSalvarNovo(boolean booMostrarSalvarNovo)
  {
    _booMostrarSalvarNovo = booMostrarSalvarNovo;
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

  private void setMniOrdemDecrescente(MenuItem mniOrdemDecrescente)
  {
    _mniOrdemDecrescente = mniOrdemDecrescente;
  }

  public void setObjDb(DbeAndroidMain objDb)
  {
    _dbe = objDb;
  }

  private void setViwPrincipal(final ViewAndroid viwPrincipal)
  {
    _viwPrincipal = viwPrincipal;
  }

  /**
   * Este métod é chamado quando o usário termina de preencher os dados nos campos da Activity de cadastro e clica na opção sincronizar.
   *
   * @param act
   * @return True caso tudo esteja satisfatório para o salvamento do registro, ou false caso contrário.
   */
  public boolean validarDados(final ActCadastroMain act)
  {
    for (Coluna cln : this.getLstCln())
    {
      if (cln == null)
      {
        continue;
      }

      if (!((ColunaAndroid) cln).validarDados(act))
      {
        return false;
      }
    }

    return true;
  }
}