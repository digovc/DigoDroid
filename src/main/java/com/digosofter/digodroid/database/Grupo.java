package com.digosofter.digodroid.database;

import com.digosofter.digojava.Objeto;

import java.util.ArrayList;
import java.util.List;

public class Grupo extends Objeto
{
  private int _intImagemResId;
  private List<ColunaAndroid> _lstCln;

  public Grupo(String strNome)
  {
    this.setStrNome(strNome);
  }

  public void addCln(ColunaAndroid cln)
  {
    if (cln == null)
    {
      return;
    }

    if (this.getLstCln().contains(cln))
    {
      return;
    }

    this.getLstCln().add(cln);

    cln.setObjDbGrupo(this);
  }

  private int getIntImagemResId()
  {
    return _intImagemResId;
  }

  private List<ColunaAndroid> getLstCln()
  {
    if (_lstCln != null)
    {
      return _lstCln;
    }

    _lstCln = new ArrayList();

    return _lstCln;
  }

  /**
   * Indica o código da imagem que representa este grupo de dados.
   *
   * @param intImagemResId Código da imagem que representa este grupo de dados.
   */
  public void setIntImagemResId(int intImagemResId)
  {
    _intImagemResId = intImagemResId;
  }
}