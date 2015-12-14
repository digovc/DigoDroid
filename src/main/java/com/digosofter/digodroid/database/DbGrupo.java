package com.digosofter.digodroid.database;

import com.digosofter.digojava.Objeto;

public class DbGrupo extends Objeto {

  private int _intImagemResId;

  private int getIntImagemResId() {

    return _intImagemResId;
  }

  /**
   * Indica o código da imagem que representa este grupo de dados.
   *
   * @param intImagemResId Código da imagem que representa este grupo de dados.
   */
  public void setIntImagemResId(int intImagemResId) {

    _intImagemResId = intImagemResId;
  }
}