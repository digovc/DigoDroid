package com.digosofter.digodroid.design;

import com.digosofter.digojava.Objeto;

public class TemaDefault extends Objeto
{
  public enum EnmFonteTamanho
  {
    GRANDE, // 4
    MEDIO, // 3
    MINIM0, // 0
    NORMAL, // 2
    PEQUENO, // 1
  }

  private static TemaDefault _i;

  public static TemaDefault getI()
  {
    if (_i != null)
    {
      return _i;
    }

    _i = new TemaDefault();

    return _i;
  }

  private final int INT_FONTE_TAMANHO_FATOR = 12;

  private int _intEspacamento;
  private int _intFontGrande;
  private int _intFontMedio;
  private int _intFontMinimo;
  private int _intFontNormal;
  private int _intFontPequeno;
  private int _intHeightNivel;
  private int _intMargin;

  public float enmFonteTamanhoToInt(EnmFonteTamanho enmFonteTamanho)
  {
    switch (enmFonteTamanho)
    {
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
   * @return Quantidade de "density pixels" do espaçamento interno padrão.
   */
  public int getIntEspacamento()
  {
    if (_intEspacamento > 0)
    {
      return _intEspacamento;
    }

    _intEspacamento = 10;

    return _intEspacamento;
  }

  /**
   * @return Tamanho da fonte (grande).
   */
  public int getIntFontGrande()
  {
    if (_intFontGrande > 0)
    {
      return _intFontGrande;
    }

    _intFontGrande = (INT_FONTE_TAMANHO_FATOR * 3);

    return _intFontGrande;
  }

  /**
   * @return Tamanho da fonte (média).
   */
  public int getIntFontMedio()
  {
    if (_intFontMedio > 0)
    {
      return _intFontMedio;
    }

    _intFontMedio = (int) (INT_FONTE_TAMANHO_FATOR * 2.5);

    return _intFontMedio;
  }

  /**
   * @return Tamanho da fonte (mínima).
   */
  public int getIntFontMinimo()
  {
    if (_intFontMinimo > 0)
    {
      return _intFontMinimo;
    }

    _intFontMinimo = INT_FONTE_TAMANHO_FATOR;

    return _intFontMinimo;
  }

  /**
   * @return Tamanho da fonte (normal).
   */
  public int getIntFontNormal()
  {
    if (_intFontNormal > 0)
    {
      return _intFontNormal;
    }

    _intFontNormal = (int) (INT_FONTE_TAMANHO_FATOR * 1.75f);

    return _intFontNormal;
  }

  /**
   * @return Tamanho da fonte (pequena).
   */
  public int getIntFontPequeno()
  {
    if (_intFontPequeno > 0)
    {
      return _intFontPequeno;
    }

    _intFontPequeno = (int) (INT_FONTE_TAMANHO_FATOR * 1.3);

    return _intFontPequeno;
  }

  /**
   * @return Quantidade de "density pixels" de cada nível das telas.
   */
  public int getIntHeightNivel()
  {
    if (_intHeightNivel > 0)
    {
      return _intHeightNivel;
    }

    _intHeightNivel = 100;

    return _intHeightNivel;
  }

  /**
   * @return Espaçamento externo padrão.
   */
  public int getIntMargin()
  {
    if (_intMargin > 0)
    {
      return _intMargin;
    }

    _intMargin = 10;

    return _intMargin;
  }

  public EnmFonteTamanho intToEnmFonteTamanho(int intFonteTamanho)
  {
    switch (intFonteTamanho)
    {
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