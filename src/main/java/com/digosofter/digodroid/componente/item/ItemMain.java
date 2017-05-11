package com.digosofter.digodroid.componente.item;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;

import com.digosofter.digodroid.componente.painel.PainelGeral;

public abstract class ItemMain extends PainelGeral
{
  public ItemMain(Context cnt)
  {
    super(cnt);
  }

  public ItemMain(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public ItemMain(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  /**
   * Este método recarrega os valores apresentados nos itens com o registro selecionado no cursor.
   *
   * @param crs Cursor com os dados para fazer a reciclagem do consulta_item.
   */
  public void carregarDados(Cursor crs)
  {
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.setOrientation(VERTICAL);
  }
}