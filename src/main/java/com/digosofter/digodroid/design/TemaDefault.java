package com.digosofter.digodroid.design;

import android.graphics.Color;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;

public class TemaDefault extends Objeto {

  public enum EnmFonteTamanho {

    GRANDE, // 4
    MEDIO, // 3
    MINIM0, // 0
    NORMAL, // 2
    PEQUENO, // 1
  }

  private static TemaDefault i;

  public static TemaDefault getI() {

    try {

      if (i != null) {

        return i;
      }

      i = new TemaDefault();

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return i;
  }

  private final int INT_FONTE_TAMANHO_FATOR = 12;

  private int _intFontGrande;
  private int _intFontMedio;
  private int _intFontMinimo;
  private int _intFontNormal;
  private int _intFontPequeno;
  private int _intHeightNivel;
  private int _intMargin;
  private int _intPadding;

  public float enmFonteTamanhoToInt(EnmFonteTamanho enmFonteTamanho) {

    switch (enmFonteTamanho) {

      case GRANDE:
        return this.getIntFontGrande();

      case MEDIO:
        return this.getIntFontMedio();

      case MINIM0:
        return this.getIntFontMinimo();

      case PEQUENO:
        return this.getIntFontPequeno();

      default:
        return this.getIntFontNormal();
    }
  }

  /**
   * @return Tamanho da fonte (grande).
   */
  public int getIntFontGrande() {

    try {

      if (_intFontGrande > 0) {

        return _intFontGrande;
      }

      _intFontGrande = (INT_FONTE_TAMANHO_FATOR * 3);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _intFontGrande;
  }

  /**
   * @return Tamanho da fonte (média).
   */
  public int getIntFontMedio() {

    try {

      if (_intFontMedio > 0) {

        return _intFontMedio;
      }

      _intFontMedio = (int) (INT_FONTE_TAMANHO_FATOR * 2.5);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _intFontMedio;
  }

  /**
   * @return Tamanho da fonte (mínima).
   */
  public int getIntFontMinimo() {

    try {

      if (_intFontMinimo > 0) {

        return _intFontMinimo;
      }

      _intFontMinimo = INT_FONTE_TAMANHO_FATOR;

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _intFontMinimo;
  }

  /**
   * @return Tamanho da fonte (normal).
   */
  public int getIntFontNormal() {

    try {

      if (_intFontNormal > 0) {

        return _intFontNormal;
      }

      _intFontNormal = (int) (INT_FONTE_TAMANHO_FATOR * 1.75f);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _intFontNormal;
  }

  /**
   * @return Tamanho da fonte (pequena).
   */
  public int getIntFontPequeno() {

    try {

      if (_intFontPequeno > 0) {

        return _intFontPequeno;
      }

      _intFontPequeno = (int) (INT_FONTE_TAMANHO_FATOR * 1.3);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _intFontPequeno;
  }

  /**
   * @return Quantidade de "density pixels" de cada nível das telas.
   */
  public int getIntHeightNivel() {

    try {

      if (_intHeightNivel > 0) {

        return _intHeightNivel;
      }

      _intHeightNivel = 100;

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _intHeightNivel;
  }

  /**
   * @return Espaçamento externo padrão.
   */
  public int getIntMargin() {

    try {

      if (_intMargin > 0) {

        return _intMargin;
      }

      _intMargin = 5;

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _intMargin;
  }

  /**
   * @return Quantidade de "density pixels" do espaçamento interno padrão.
   */
  public int getIntPadding() {

    try {

      if (_intPadding > 0) {

        return _intPadding;
      }

      _intPadding = 10;

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _intPadding;
  }

  public EnmFonteTamanho intToEnmFonteTamanho(int intFonteTamanho) {

    switch (intFonteTamanho) {

      case 4:
        return EnmFonteTamanho.GRANDE;

      case 3:
        return EnmFonteTamanho.MEDIO;

      case 0:
        return EnmFonteTamanho.MINIM0;

      case 1:
        return EnmFonteTamanho.PEQUENO;

      default:
        return EnmFonteTamanho.NORMAL;
    }
  }
}