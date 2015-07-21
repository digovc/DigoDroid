package com.digosofter.digodroid.database;

import java.lang.reflect.Field;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.App;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.DbColuna;
import com.digosofter.digojava.database.Dominio;

import android.database.Cursor;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class DbColunaAndroid extends DbColuna {

  private MenuItem _mniCampo;
  private MenuItem _mniOrdenar;
  private String _sqlTipo;
  private View _viw;

  public DbColunaAndroid(String strNome, DbTabelaAndroid<?> tbl, EnmTipo enmTipo) {

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

      for (Field objField : cls.getDeclaredFields()) {

        if (objField == null) {

          continue;
        }

        if (!Utils.simplificar(objField.getName().replace("_", Utils.STR_VAZIA)).equals(this.getStrDominioNome())) {

          continue;
        }

        if (this.carregarDominio(crs, objDominio, objField)) {

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

      if ("boolean".equals(objField.getType().getSimpleName().toLowerCase(Utils.LOCAL_BRASIL))) {

        this.carregarDominioBoo(crs, objDominio, objField);
        return true;
      }

      if ("double".equals(objField.getType().getSimpleName().toLowerCase(Utils.LOCAL_BRASIL))) {

        this.carregarDominioDbl(crs, objDominio, objField);
        return true;
      }

      if ("gregoriancalendar".equals(objField.getType().getSimpleName().toLowerCase(Utils.LOCAL_BRASIL))) {

        this.carregarDominioDtt(crs, objDominio, objField);
        return true;
      }

      if ("int".equals(objField.getType().getSimpleName().toLowerCase(Utils.LOCAL_BRASIL))) {

        this.carregarDominioInt(crs, objDominio, objField);
        return true;
      }

      if ("string".equals(objField.getType().getSimpleName().toLowerCase(Utils.LOCAL_BRASIL))) {

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

  MenuItem getMniCampo() {

    return _mniCampo;
  }

  MenuItem getMniOrdenar() {

    return _mniOrdenar;
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
        case MONEY:
        case NUMERIC:
        case PERCENTUAL:
          _sqlTipo = "numeric";
          break;

        case DOUBLE:
        case FLOAT:
        case REAL:
          _sqlTipo = "real";
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

  public View getViw() {

    return _viw;
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

      ((DbColunaAndroid) this.getTbl().getClnOrdem()).getMniOrdenar().setChecked(false);

      this.setBooOrdem(true);
      this.getMniOrdenar().setChecked(true);

      ((DbTabelaAndroid<?>) this.getTbl()).getMniOrdemDecrescente().setChecked(this.getBooOrdemDecrescente());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setMniCampo(MenuItem mniCampo) {

    _mniCampo = mniCampo;
  }

  private void setMniOrdenar(MenuItem mniOrdenar) {

    _mniOrdenar = mniOrdenar;
  }

  public void setViw(View viw) {

    _viw = viw;
  }
}