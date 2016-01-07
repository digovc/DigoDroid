package com.digosofter.digodroid.controle.item;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;

import com.digosofter.digodroid.controle.painel.PainelGeral;
import com.digosofter.digodroid.erro.ErroAndroid;

public abstract class ItemMain extends PainelGeral {

  public ItemMain(Context context) {

    super(context);
  }

  public ItemMain(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public ItemMain(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
      this.setOrientation(VERTICAL);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Este método recarrega os valores apresentados nos itens com o registro selecionado no cursor.
   *
   * @param crs Cursor com os dados para fazer a reciclagem do item.
   */
  public void reciclar(Cursor crs) {

  }
}