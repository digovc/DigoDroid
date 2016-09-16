package com.digosofter.digodroid.database;

import android.database.Cursor;
import android.view.MenuItem;
import android.view.SubMenu;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digojava.OnValorAlteradoArg;
import com.digosofter.digojava.OnValorAlteradoListener;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Coluna;
import com.digosofter.digojava.dominio.DominioMain;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;

public class ColunaAndroid extends Coluna implements OnValorAlteradoListener
{
  private boolean _booDominioFieldCarregado;
  private boolean _booPesquisa;
  private Grupo _grp;
  private MenuItem _mniCampo;
  private MenuItem _mniOrdenar;
  private MenuItem _mniPesquisa;
  private String _sqlTipo;

  public ColunaAndroid(String strNome, TabelaAndroid<?> tbl, EnmTipo enmTipo)
  {
    this(strNome, tbl, enmTipo, null);
  }

  public ColunaAndroid(String strNome, TabelaAndroid<?> tbl, EnmTipo enmTipo, ColunaAndroid clnRef)
  {
    super(strNome, tbl, enmTipo, clnRef);
  }

  public <T extends DominioMain> void carregarDominio(Cursor crs, T objDominio)
  {
    if (crs == null)
    {
      return;
    }

    if (crs.getColumnIndex(this.getSqlNome()) < 0)
    {
      return;
    }

    if (objDominio == null)
    {
      return;
    }

    this.setBooDominioFieldCarregado(false);
    this.carregarDominio(crs, objDominio, objDominio.getClass());
  }

  private <T extends DominioMain> void carregarDominio(Cursor crs, T objDominio, Class<?> cls)
  {
    if (crs == null)
    {
      return;
    }

    if (objDominio == null)
    {
      return;
    }

    if (cls == null)
    {
      return;
    }

    this.carregarDominio(crs, objDominio, cls.getSuperclass());

    if (this.getBooDominioFieldCarregado())
    {
      return;
    }

    for (Field objField : cls.getDeclaredFields())
    {
      if (objField == null)
      {
        continue;
      }

      if (!Utils.simplificar(objField.getName().replace("_", Utils.STR_VAZIA)).equals(this.getStrDominioNome()))
      {
        continue;
      }

      if (this.carregarDominio(crs, objDominio, objField))
      {
        this.setBooDominioFieldCarregado(true);
        return;
      }
    }
  }

  private <T extends DominioMain> boolean carregarDominio(Cursor crs, T objDominio, Field objField)
  {
    if (crs == null)
    {
      return false;
    }

    if (objDominio == null)
    {
      return false;
    }

    if (objField == null)
    {
      return false;
    }

    objField.setAccessible(true);

    if (boolean.class.equals(objField.getType()))
    {
      this.carregarDominioBoo(crs, objDominio, objField);
      return true;
    }

    if (double.class.equals(objField.getType()))
    {
      this.carregarDominioDbl(crs, objDominio, objField);
      return true;
    }

    if (GregorianCalendar.class.equals(objField.getType()))
    {
      this.carregarDominioDtt(crs, objDominio, objField);
      return true;
    }

    if (int.class.equals(objField.getType()))
    {
      this.carregarDominioInt(crs, objDominio, objField);
      return true;
    }

    if (String.class.equals(objField.getType()))
    {
      this.carregarDominioStr(crs, objDominio, objField);
      return true;
    }

    return false;
  }

  private <T extends DominioMain> void carregarDominioBoo(Cursor crs, T objDominio, Field objField)
  {
    int intValor = crs.getInt(crs.getColumnIndex(this.getSqlNome()));

    try
    {
      objField.set(objDominio, (intValor == 1));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  private <T extends DominioMain> void carregarDominioDbl(Cursor crs, T objDominio, Field objField)
  {
    double dblValor = crs.getDouble(crs.getColumnIndex(this.getSqlNome()));

    try
    {
      objField.set(objDominio, dblValor);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  private <T extends DominioMain> void carregarDominioDtt(Cursor crs, T objDominio, Field objField)
  {
    String strValor = crs.getString(crs.getColumnIndex(this.getSqlNome()));

    try
    {
      objField.set(objDominio, Utils.strToDtt(strValor));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  private <T extends DominioMain> void carregarDominioInt(Cursor crs, T objDominio, Field objField)
  {
    int intValor = crs.getInt(crs.getColumnIndex(this.getSqlNome()));

    try
    {
      objField.set(objDominio, intValor);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  private <T extends DominioMain> void carregarDominioStr(Cursor crs, T objDominio, Field objField)
  {
    String strValor = crs.getString(crs.getColumnIndex(this.getSqlNome()));

    try
    {
      objField.set(objDominio, strValor);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void criar()
  {
    if (this.getTbl() == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(this.getTbl().getSqlNome()))
    {
      return;
    }

    if (Utils.getBooStrVazia(this.getSqlNome()))
    {
      return;
    }

    if (this.getBooExiste())
    {
      return;
    }

    String sql = "alter table _tbl_nome add column _cln_nome _cln_tipo _cln_valor_default _cln_ref;";

    sql = sql.replace("_tbl_nome", this.getTbl().getSqlNome());
    sql = sql.replace("_cln_nome", this.getSqlNome());
    sql = sql.replace("_cln_tipo", this.getSqlTipo());
    sql = sql.replace("_cln_valor_default", (!Utils.getBooStrVazia(this.getSqlValorDetault())) ? this.getSqlValorDetault() : Utils.STR_VAZIA);
    sql = sql.replace("_cln_ref", this.getSqlReference());

    this.getTbl().getDbe().execSql(sql);
  }

  private boolean getBooDominioFieldCarregado()
  {
    return _booDominioFieldCarregado;
  }

  private boolean getBooExiste()
  {
    String sql = "select * from _tbl_nome limit 0;";

    sql = sql.replace("_tbl_nome", this.getTbl().getSqlNome());

    Cursor crs = ((DataBaseAndroid) this.getTbl().getDbe()).execSqlComRetorno(sql);

    if (crs == null)
    {
      return false;
    }

    boolean booResultado = (crs.getColumnIndex(this.getSqlNome()) > -1);

    crs.close();

    return booResultado;
  }

  private boolean getBooPesquisa()
  {
    return _booPesquisa;
  }

  MenuItem getMniCampo()
  {
    return _mniCampo;
  }

  MenuItem getMniOrdenar()
  {
    return _mniOrdenar;
  }

  MenuItem getMniPesquisa()
  {
    return _mniPesquisa;
  }

  public Grupo getObjDbGrupo()
  {
    return _grp;
  }

  String getSqlCreateTable()
  {
    String strResultado = "_cln_nome _cln_tipo _cln_pk default _default, ";

    strResultado = strResultado.replace("_cln_nome", this.getSqlNome());
    strResultado = strResultado.replace("_cln_tipo", this.getSqlTipo());
    strResultado = strResultado.replace(" _cln_pk", (this.equals(this.getTbl().getClnIntId())) ? " primary key on conflict replace autoincrement" : Utils.STR_VAZIA);
    strResultado = strResultado.replace(" autoincrement", this.getEnmTipo() != EnmTipo.TEXT ? " autoincrement" : Utils.STR_VAZIA);
    strResultado = strResultado.replace(" default _default", !Utils.getBooStrVazia(this.getStrValorDefault()) ? " default _default" : Utils.STR_VAZIA);
    strResultado = strResultado.replace("_default", !Utils.getBooStrVazia(this.getStrValorDefault()) ? this.getStrValorDefault() : Utils.STR_VAZIA);

    return strResultado;
  }

  private String getSqlReference()
  {
    if (this.getClnRef() == null)
    {
      return Utils.STR_VAZIA;
    }

    String sql = "references _tbl_ref_nome(_cln_ref_nome) _on_update_cascade _on_delete_cascade";

    sql = sql.replace("_tbl_ref_nome", this.getClnRef().getTbl().getSqlNome());
    sql = sql.replace("_cln_ref_nome", this.getClnRef().getSqlNome());
    sql = sql.replace("_cln_ref_nome", this.getClnRef().getSqlNome());
    sql = sql.replace("_on_update_cascade", this.getBooOnUpdateCascade() ? "ON UPDATE CASCADE" : Utils.STR_VAZIA);
    sql = sql.replace("_on_delete_cascade", this.getBooOnDeleteCascade() ? "ON DELETE CASCADE" : Utils.STR_VAZIA);

    return sql;
  }

  String getSqlTipo()
  {
    if (_sqlTipo != null)
    {
      return _sqlTipo;
    }

    switch (this.getEnmTipo())
    {
      case BIGINT:
      case BOOLEAN:
      case INTEGER:
      case SMALLINT:
        return _sqlTipo = "integer";

      case DECIMAL:
      case DOUBLE:
      case FLOAT:
      case MONEY:
      case NUMERIC:
      case PERCENTUAL:
      case REAL:
        return _sqlTipo = "numeric";

      default:
        return _sqlTipo = "text";
    }
  }

  void montarMenuCampo(SubMenu smn)
  {
    if (smn == null)
    {
      return;
    }

    if (this.equals(this.getTbl().getClnIntId()))
    {
      return;
    }

    if (this.getBooNome())
    {
      return;
    }

    this.setMniCampo(smn.add(this.getStrNomeExibicao()));
    this.getMniCampo().setChecked(this.getBooVisivelConsulta());
    this.getMniCampo().setCheckable(true);
  }

  void montarMenuOrdenar(SubMenu smn)
  {
    if (smn == null)
    {
      return;
    }

    this.setMniOrdenar(smn.add(this.getStrNomeExibicao()));
    this.getMniOrdenar().setChecked(this.getBooOrdem());
    this.getMniOrdenar().setCheckable(true);
  }

  void montarMenuPesquisa(final SubMenu smn)
  {
    if (smn == null)
    {
      return;
    }

    if (!this.getBooVisivelConsulta() && !this.getBooNome() && !(this.equals(this.getTbl().getClnIntId())))
    {
      return;
    }

    this.setMniPesquisa(smn.add(this.getStrNomeExibicao()));
    this.getMniPesquisa().setChecked(this.equals(((TabelaAndroid) this.getTbl()).getClnPesquisa()));
    this.getMniPesquisa().setCheckable(true);
  }

  @Override
  public void onValorAlterado(final Object objSender, final OnValorAlteradoArg arg)
  {
    if (arg == null)
    {
      return;
    }

    this.setStrValor(arg.getStrValor());
  }

  void processarMenuCampo(MenuItem mni)
  {
    if (mni == null)
    {
      return;
    }

    if (!mni.equals(this.getMniCampo()))
    {
      return;
    }

    this.getMniCampo().setChecked(!this.getMniCampo().isChecked());
    this.setBooVisivelConsulta(this.getMniCampo().isChecked());
  }

  void processarMenuOrdenar(MenuItem mni)
  {
    if (mni == null)
    {
      return;
    }

    if (!mni.equals(this.getMniOrdenar()))
    {
      return;
    }

    ((ColunaAndroid) this.getTbl().getClnOrdem()).getMniOrdenar().setChecked(false);

    this.setBooOrdem(true);
    this.getMniOrdenar().setChecked(true);

    ((TabelaAndroid<?>) this.getTbl()).getMniOrdemDecrescente().setChecked(this.getBooOrdemDecrescente());
  }

  void processarMenuPesquisa(final MenuItem mni)
  {
    if (mni == null)
    {
      return;
    }

    if (!mni.equals(this.getMniPesquisa()))
    {
      return;
    }

    ((ColunaAndroid) this.getTbl().getClnOrdem()).getMniPesquisa().setChecked(false);

    this.setBooPesquisa(true);
    this.getMniPesquisa().setChecked(true);
  }

  private void setBooDominioFieldCarregado(boolean booDominioFieldCarregado)
  {
    _booDominioFieldCarregado = booDominioFieldCarregado;
  }

  /**
   * Indica se esta coluna é a coluna de pesquisa nas telas de consultas.
   *
   * @param booPesquisa Indica se esta coluna é a coluna de pesquisa nas telas de consultas.
   */
  public void setBooPesquisa(boolean booPesquisa)
  {
    _booPesquisa = booPesquisa;

    if (!_booPesquisa)
    {
      ((TabelaAndroid) this.getTbl()).setClnPesquisa(null);
      return;
    }

    if (!this.equals(((TabelaAndroid) this.getTbl()).getClnPesquisa()))
    {
      ((TabelaAndroid) this.getTbl()).getClnPesquisa()._booPesquisa = false;
    }

    ((TabelaAndroid) this.getTbl()).setClnPesquisa(this);
  }

  private void setMniCampo(MenuItem mniCampo)
  {
    _mniCampo = mniCampo;
  }

  private void setMniOrdenar(MenuItem mniOrdenar)
  {
    _mniOrdenar = mniOrdenar;
  }

  private void setMniPesquisa(MenuItem mniPesquisa)
  {
    _mniPesquisa = mniPesquisa;
  }

  void setObjDbGrupo(Grupo grp)
  {
    _grp = grp;
  }

  /**
   * Valida os dados desta coluna.
   *
   * @return True caso o valor da coluna esteja válido para salvamento.
   */
  public boolean validarDados()
  {
    if (this.getBooVazia() && this.getBooObrigatorio())
    {
      AppAndroid.getI().notificar("Campo \"_cln_nome\" não pode estar vazio.".replace("_cln_nome", this.getStrNomeExibicao()));
      return false;
    }

    return true;
  }
}