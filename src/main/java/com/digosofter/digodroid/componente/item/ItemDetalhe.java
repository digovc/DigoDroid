package com.digosofter.digodroid.componente.item;

import android.content.Context;
import android.view.ViewGroup;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public class ItemDetalhe extends ItemCampo
{

  public ItemDetalhe(Context cnt, ColunaAndroid cln)
  {
    super(cnt, cln);
  }

  public void carregarDados()
  {
    String strValorFormatado;
    try
    {
      if (this.getCln() == null)
      {
        return;
      }
      strValorFormatado = (!Utils.getBooStrVazia(this.getCln().getStrValorExibicao())) ? this.getCln().getStrValorExibicao() : null;
      this.getLblRegistroValor().setText(strValorFormatado);
      this.getLblRegistroNome().setText(this.getCln().getStrNomeExibicao() + ": ");
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void inicializar()
  {
    super.inicializar();
    int intPadding;
    int intPaddingMeio;
    try
    {
      intPadding = UtilsAndroid.dpToPx(TemaDefault.getI().getIntEspacamento(), this.getContext());
      intPaddingMeio = UtilsAndroid.dpToPx((TemaDefault.getI().getIntEspacamento() / 2), this.getContext());
      this.setPadding(intPadding, intPaddingMeio, intPadding, intPaddingMeio);
      this.inicializarLblRegistroNome();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarLblRegistroNome()
  {
    int intMargin;
    MarginLayoutParams ltp;
    try
    {
      intMargin = UtilsAndroid.dpToPx(TemaDefault.getI().getIntEspacamento(), this.getContext());
      ltp = new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      ltp.setMargins(intMargin, 0, intMargin, 0);
      this.getLblRegistroNome().setLayoutParams(ltp);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }
}