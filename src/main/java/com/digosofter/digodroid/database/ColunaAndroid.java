package com.digosofter.digodroid.database;

import android.database.Cursor;
import android.view.MenuItem;
import android.view.SubMenu;

import com.digosofter.digodroid.OnValorAlterado;
import com.digosofter.digodroid.OnValorAlteradoArg;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.App;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Coluna;
import com.digosofter.digojava.database.Dominio;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;

public class ColunaAndroid extends Coluna implements OnValorAlterado {

  private boolean _booDominioFieldCarregado;
  private Grupo _grp;
  private MenuItem _mniCampo;
  private MenuItem _mniOrdenar;
  private String _sqlTipo;

  public ColunaAndroid(String strNome, TabelaAndroid<?> tbl, EnmTipo enmTipo) {

    super(strNome, tbl, enmTipo);
  }

  public <T extends Dominio> void carregarDominio(Cursor crs, T objDominio) {

    try {

      if (crs == null) {

        return;
      }

      if (crs.getColumnIndex(this.getStrNomeSql()) < 0) {

        return;
      }

      if (objDominio == null) {

        return;
      }

      this.setBooDominioFieldCarregado(false);
      this.carregarDominio(crs, objDominio, objDominio.getClass());

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private <T extends Dominio> void carregarDominio(Cursor crs, T objDominio, Class<?> cls) {

    try {

      if (crs == null) {

        return;
      }

      if (objDominio == null) {

        return;
      }

      if (cls == null) {

        return;
      }

      this.carregarDominio(crs, objDominio, cls.getSuperclass());

      if (this.getBooDominioFieldCarregado()) {

        return;
      }

      for (Field objField : cls.getDeclaredFields()) {

        if (objField == null) {

          continue;
        }

        if (!Utils.simplificar(objField.getName().replace("_", Utils.STR_VAZIA)).equals(this.getStrDominioNome())) {

          continue;
        }

        if (this.carregarDominio(crs, objDominio, objField)) {

          this.setBooDominioFieldCarregado(true);
          return;
        }
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private <T extends Dominio> boolean carregarDominio(Cursor crs, T objDominio, Field objField) {

    try {

      if (crs == null) {

        return false;
      }

      if (objDominio == null) {

        return false;
      }

      if (objField == null) {

        return false;
      }

      objField.setAccessible(true);

      if (boolean.class.equals(objField.getType())) {

        this.carregarDominioBoo(crs, objDominio, objField);
        return true;
      }

      if (double.class.equals(objField.getType())) {

        this.carregarDominioDbl(crs, objDominio, objField);
        return true;
      }

      if (GregorianCalendar.class.equals(objField.getType())) {

        this.carregarDominioDtt(crs, objDominio, objField);
        return true;
      }

      if (int.class.equals(objField.getType())) {

        this.carregarDominioInt(crs, objDominio, objField);
        return true;
      }

      if (String.class.equals(objField.getType())) {

        this.carregarDominioStr(crs, objDominio, objField);
        return true;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {

      objField.setAccessible(false);
    }

    return false;
  }

  private <T extends Dominio> void carregarDominioBoo(Cursor crs, T objDominio, Field objField) {

    int intValor;

    try {

      intValor = crs.getInt(crs.getColumnIndex(this.getStrNomeSql()));

      objField.set(objDominio, ((intValor == 1) ? true : false));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private <T extends Dominio> void carregarDominioDbl(Cursor crs, T objDominio, Field objField) {

    double dblValor;

    try {

      dblValor = crs.getDouble(crs.getColumnIndex(this.getStrNomeSql()));

      objField.set(objDominio, dblValor);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private <T extends Dominio> void carregarDominioDtt(Cursor crs, T objDominio, Field objField) {

    String strValor;

    try {

      strValor = crs.getString(crs.getColumnIndex(this.getStrNomeSql()));

      objField.set(objDominio, Utils.strToDtt(strValor));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private <T extends Dominio> void carregarDominioInt(Cursor crs, T objDominio, Field objField) {

    int intValor;

    try {

      intValor = crs.getInt(crs.getColumnIndex(this.getStrNomeSql()));

      objField.set(objDominio, intValor);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private <T extends Dominio> void carregarDominioStr(Cursor crs, T objDominio, Field objField) {

    String strValor;

    try {

      strValor = crs.getString(crs.getColumnIndex(this.getStrNomeSql()));

      objField.set(objDominio, strValor);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private boolean getBooDominioFieldCarregado() {

    return _booDominioFieldCarregado;
  }

  MenuItem getMniCampo() {

    return _mniCampo;
  }

  MenuItem getMniOrdenar() {

    return _mniOrdenar;
  }

  public Grupo getObjDbGrupo() {

    return _grp;
  }

  String getSqlCreateTable() {

    String strResultado;

    try {

      strResultado = "_cln_nome _cln_tipo _cln_pk default _default, ";

      strResultado = strResultado.replace("_cln_nome", this.getStrNomeSql());
      strResultado = strResultado.replace("_cln_tipo", this.getSqlTipo());
      strResultado = strResultado.replace(" _cln_pk", this.getBooChavePrimaria() ? " primary key on conflict replace autoincrement" : Utils.STR_VAZIA);
      strResultado = strResultado.replace(" autoincrement", this.getEnmTipo() != EnmTipo.TEXT ? " autoincrement" : Utils.STR_VAZIA);
      strResultado = strResultado.replace(" default _default", !Utils.getBooStrVazia(this.getStrValorDefault()) ? " default _default" : Utils.STR_VAZIA);
      strResultado = strResultado.replace("_default", !Utils.getBooStrVazia(this.getStrValorDefault()) ? this.getStrValorDefault() : Utils.STR_VAZIA);

      return strResultado;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  private String getSqlTipo() {

    try {

      if (!Utils.getBooStrVazia(_sqlTipo)) {

        return _sqlTipo;
      }

      switch (this.getEnmTipo()) {

        case BIGINT:
        case BOOLEAN:
        case INTEGER:
        case SMALLINT:
          _sqlTipo = "integer";
          break;

        case DECIMAL:
        case DOUBLE:
        case FLOAT:
        case MONEY:
        case NUMERIC:
        case PERCENTUAL:
        case REAL:
          _sqlTipo = "numeric";
          break;

        default:
          _sqlTipo = "text";
      }
    }
    catch (Exception ex) {

      new ErroAndroid(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _sqlTipo;
  }

  void montarMenuCampo(SubMenu smn) {

    try {

      if (smn == null) {

        return;
      }

      if (this.getBooChavePrimaria()) {

        return;
      }

      if (this.getBooNome()) {

        return;
      }

      this.setMniCampo(smn.add(this.getStrNomeExibicao()));
      this.getMniCampo().setChecked(this.getBooVisivelConsulta());
      this.getMniCampo().setCheckable(true);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  void montarMenuOrdenar(SubMenu smn) {

    try {

      if (smn == null) {

        return;
      }

      this.setMniOrdenar(smn.add(this.getStrNomeExibicao()));
      this.getMniOrdenar().setChecked(this.getBooOrdem());
      this.getMniOrdenar().setCheckable(true);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onValorAlterado(final Object objSender, final OnValorAlteradoArg arg) {

    try {

      if (arg == null) {

        return;
      }

      this.setStrValor(arg.getStrValor());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  void processarMenuCampo(MenuItem mni) {

    try {

      if (mni == null) {

        return;
      }

      if (!mni.equals(this.getMniCampo())) {

        return;
      }

      this.getMniCampo().setChecked(!this.getMniCampo().isChecked());
      this.setBooVisivelConsulta(this.getMniCampo().isChecked());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  void processarMenuOrdenar(MenuItem mni) {

    try {

      if (mni == null) {

        return;
      }

      if (!mni.equals(this.getMniOrdenar())) {

        return;
      }

      ((ColunaAndroid) this.getTbl().getClnOrdem()).getMniOrdenar().setChecked(false);

      this.setBooOrdem(true);
      this.getMniOrdenar().setChecked(true);

      ((TabelaAndroid<?>) this.getTbl()).getMniOrdemDecrescente().setChecked(this.getBooOrdemDecrescente());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setBooDominioFieldCarregado(boolean booDominioFieldCarregado) {

    _booDominioFieldCarregado = booDominioFieldCarregado;
  }

  private void setMniCampo(MenuItem mniCampo) {

    _mniCampo = mniCampo;
  }

  private void setMniOrdenar(MenuItem mniOrdenar) {

    _mniOrdenar = mniOrdenar;
  }

  /**
   * Este é o grupo que conterá esta coluna na tela de detalhes dos registros.
   *
   * @param grp Grupo que conterá esta coluna na tela de detalhes dos registros.
   */
  public void setObjDbGrupo(Grupo grp) {

    _grp = grp;
  }
}