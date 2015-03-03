package com.digosofter.digodroid.item;

import android.database.Cursor;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;
import com.digosofter.digojava.Utils;

// TODO: Tornar dinâmico o número de campo do item dependendo da
// quantidade de colunas setadas para aparecer na consulta.
public class ItmConsulta extends Objeto {

  private String _strCampo1;
  private String _strCampo1Nome;
  private String _strCampo1Valor;
  private String _strCampo2;
  private String _strCampo2Nome;
  private String _strCampo2Valor;
  private String _strCampo3;
  private String _strCampo3Nome;
  private String _strCampo3Valor;
  private String _strItemId;

  public void carregarDados(DbTabelaAndroid tbl, Cursor crs) {

    try {

      tbl.getClnNome().setStrValor(crs.getString(crs.getColumnIndex(tbl.getClnNome().getStrNomeSimplificado())));
      tbl.getLstClnConsulta().get(2).setStrValor(crs.getString(crs.getColumnIndex(tbl.getLstClnConsulta().get(2).getStrNomeSimplificado())));
      tbl.getLstClnConsulta().get(3).setStrValor(crs.getString(crs.getColumnIndex(tbl.getLstClnConsulta().get(3).getStrNomeSimplificado())));
      tbl.getLstClnConsulta().get(4).setStrValor(crs.getString(crs.getColumnIndex(tbl.getLstClnConsulta().get(4).getStrNomeSimplificado())));

      this.setStrItemId(crs.getString(crs.getColumnIndex(tbl.getClnChavePrimaria().getStrNomeSimplificado())));
      this.setStrNome(tbl.getClnNome().getStrValorExibicao());
      this.setStrCampo1Nome(tbl.getLstClnConsulta().get(2).getStrNomeExibicao());
      this.setStrCampo1Valor(tbl.getLstClnConsulta().get(2).getStrValorExibicao());
      this.setStrCampo2Nome(tbl.getLstClnConsulta().get(3).getStrNomeExibicao());
      this.setStrCampo2Valor(tbl.getLstClnConsulta().get(3).getStrValorExibicao());
      this.setStrCampo3Nome(tbl.getLstClnConsulta().get(4).getStrNomeExibicao());
      this.setStrCampo3Valor(tbl.getLstClnConsulta().get(4).getStrValorExibicao());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private String formatarValor(String strValor) {

    try {

      if (Utils.getBooStrVazia(strValor)) {

        return Utils.STR_VAZIA;
      }

      strValor = strValor.replace("true", "Sim");
      strValor = strValor.replace("false", "Não");
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return strValor;
  }

  public boolean getBooContemTermo(String strTermo) {

    try {

      if (Utils.getBooStrVazia(strTermo)) {

        return true;
      }

      if (this.getStrNome().contains(strTermo)) {

        return true;
      }

      if (this.getStrCampo1Valor().contains(strTermo)) {

        return true;
      }

      if (this.getStrCampo2Valor().contains(strTermo)) {

        return true;
      }

      if (this.getStrCampo3Valor().contains(strTermo)) {

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

  public String getStrCampo001() {

    try {

      if (!Utils.getBooStrVazia(_strCampo1)) {

        return _strCampo1;
      }

      _strCampo1 = this.getStrCampo1Nome() + ": " + this.getStrCampo1Valor();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _strCampo1;
  }

  public String getStrCampo1Nome() {

    return _strCampo1Nome;
  }

  public String getStrCampo1Valor() {

    try {

      if (!Utils.getBooStrVazia(_strCampo1Valor)) {

        return _strCampo1Valor;
      }

      _strCampo1Valor = this.formatarValor(_strCampo1Valor);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _strCampo1Valor;
  }

  public String getstrCampo2() {

    try {

      if (!Utils.getBooStrVazia(_strCampo2)) {

        return _strCampo2;
      }

      _strCampo2 = this.getStrCampo2Nome() + ": " + this.getStrCampo2Valor();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _strCampo2;
  }

  public String getStrCampo2Nome() {

    return _strCampo2Nome;
  }

  public String getStrCampo2Valor() {

    try {

      if (!Utils.getBooStrVazia(_strCampo2Valor)) {

        return _strCampo2Valor;
      }

      _strCampo2Valor = this.formatarValor(_strCampo2Valor);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _strCampo2Valor;
  }

  public String getstrCampo3() {

    try {

      if (!Utils.getBooStrVazia(_strCampo3)) {

        return _strCampo3;
      }

      _strCampo3 = this.getStrCampo3Nome() + ": " + this.getStrCampo3Valor();
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _strCampo3;
  }

  public String getStrCampo3Nome() {

    return _strCampo3Nome;
  }

  public String getStrCampo3Valor() {

    try {

      if (!Utils.getBooStrVazia(_strCampo3Valor)) {

        return _strCampo3Valor;
      }

      _strCampo3Valor = this.formatarValor(_strCampo3Valor);
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }

    return _strCampo3Valor;
  }

  public String getStrItemId() {

    return _strItemId;
  }

  private void setStrCampo1Nome(String strCampo1Nome) {

    _strCampo1Nome = strCampo1Nome;
  }

  private void setStrCampo1Valor(String strCampo1Valor) {

    _strCampo1Valor = strCampo1Valor;
  }

  private void setStrCampo2Nome(String strCampo2Nome) {

    _strCampo2Nome = strCampo2Nome;
  }

  private void setStrCampo2Valor(String strCampo2Valor) {

    _strCampo2Valor = strCampo2Valor;
  }

  private void setStrCampo3Nome(String strCampo3Nome) {

    _strCampo3Nome = strCampo3Nome;
  }

  private void setStrCampo3Valor(String strCampo3Valor) {

    _strCampo3Valor = strCampo3Valor;
  }

  private void setStrItemId(String strItemId) {

    _strItemId = strItemId;
  }
}