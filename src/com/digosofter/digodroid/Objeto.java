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
      // VARI�VEIS

      Objeto.INT_INDEX++;

      // FIM VARI�VEIS

      // A��ES

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(108), ex.getMessage());

    } finally {
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
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_strNomeExibicao == null) {
        _strNomeExibicao = _strNome;
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
    }

    return _strNomeExibicao;
  }

  public String getStrNomeSimplificado() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      _strNomeSimplificado = Utils.getStrSimplificada(_strNome);

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

    } finally {
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
