package com.digosofter.digodroid.design;

import android.graphics.Color;

import com.digosofter.digojava.Objeto;
import com.digosofter.digojava.erro.Erro;

public class TemaDefault extends Objeto {

  private static TemaDefault i;
  private int _corFundo1 = -1;
  private int _corTema = -1;
  private int _intHeightNivel = -1;
  private int _intMargin = -1;
  private int _intPadding = -1;

  public static TemaDefault getI() {

    try {

      if (i != null) {

        return i;
      }

      i = new TemaDefault();
    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);

    } finally {
    }

    return i;
  }

  /**
   * @return Cor dos comonentes com relevo.
   */
  public int getCorFundo1() {

    try {

      if (_corFundo1 > -1) {

        return _corFundo1;
      }

      _corFundo1 = Color.parseColor("#e3e3e3");
    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }

    return _corFundo1;
  }

  /**
   * @return Cor do tema da aplicação.
   */
  public int getCorTema() {

    try {

      if (_corTema > -1) {

        return _corTema;
      }

      _corTema = Color.parseColor("#004d7e");
    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }

    return _corTema;
  }

  /**
   * @return Quantidade de "density pixels" de cada nível das telas.
   */
  public int getIntHeightNivel() {

    try {

      if (_intHeightNivel > -1) {

        return _intHeightNivel;
      }

      _intHeightNivel = 100;

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }

    return _intHeightNivel;
  }

  /**
   * @return Espaçamento externo padrão.
   */
  public int getIntMargin() {

    try {

      if (_intMargin > -1) {

        return _intMargin;
      }

      _intMargin = 10;

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }

    return _intMargin;
  }

  /**
   * @return Quantidade de "density pixels" do espaçamento interno padrão.
   */
  public int getIntPadding() {

    try {

      if (_intPadding > -1) {

        return _intPadding;
      }

      _intPadding = 10;

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);
    } finally {
    }

    return _intPadding;
  }
}