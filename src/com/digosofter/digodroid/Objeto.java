package com.digosofter.digodroid;

import com.digosofter.digodroid.erro.Erro;

public abstract class Objeto {

  private static int INT_INDEX;
  private int _intIndexObjeto = Objeto.INT_INDEX;
  private String _strDescricao;
  private String _strNome;
  private String _strNomeExibicao;
  private String _strNomeSimplificado;

  public Objeto() {

    try {
      Objeto.INT_INDEX++;
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(108), ex);
    }
    finally {
    }
  }

  public int getIntIndexObjeto() {

    return _intIndexObjeto;
  }

  public String getStrDescricao() {

    return _strDescricao;
  }

  public String getStrNome() {

    return _strNome;
  }

  public String getStrNomeExibicao() {

    try {
      if (_strNomeExibicao == null) {
        _strNomeExibicao = _strNome;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strNomeExibicao;
  }

  public String getStrNomeSimplificado() {

    try {
      _strNomeSimplificado = Utils.getStrSimplificada(_strNome);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return _strNomeSimplificado;
  }

  public void setStrDescricao(String strDescricao) {

    _strDescricao = strDescricao;
  }

  public void setStrNome(String strNome) {

    _strNome = strNome;
  }

  public void setStrNomeExibicao(String strNomeExibicao) {

    _strNomeExibicao = strNomeExibicao;
  }
}
