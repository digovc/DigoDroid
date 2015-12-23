package com.digosofter.digodroid.controle.item;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public class ItemCampo extends ItemMain implements OnClickListener {

  private ColunaAndroid _cln;
  private LabelGeral _lblRegistroNome;
  private LabelGeral _lblRegistroValor;

  public ItemCampo(Context context, ColunaAndroid cln, Cursor crs) {

    super(context);

    try {

      this.setCln(cln);
      this.carregarDados(crs);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void carregarDados(Cursor crs) {

    try {

      if (crs == null) {

        return;
      }

      this.getLblRegistroNome().setStrTexto(this.getCln().getStrNomeExibicao() + ": ");

      this.carregarDadosValor(crs);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void carregarDadosValor(Cursor crs) {

    String strValor;

    try {

      strValor = crs.getString(crs.getColumnIndex(this.getCln().getStrNomeSql()));

      if (Utils.getBooStrVazia(strValor)) {

        return;
      }

      this.getCln().setStrValor(strValor);
      this.getLblRegistroValor().setStrTexto(this.getCln().getStrValorExibicao());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public ColunaAndroid getCln() {

    return _cln;
  }

  protected LabelGeral getLblRegistroNome() {

    try {

      if (_lblRegistroNome != null) {

        return _lblRegistroNome;
      }

      _lblRegistroNome = new LabelGeral(this.getContext());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lblRegistroNome;
  }

  protected LabelGeral getLblRegistroValor() {

    try {

      if (_lblRegistroValor != null) {

        return _lblRegistroValor;
      }

      _lblRegistroValor = new LabelGeral(this.getContext());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lblRegistroValor;
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.setOrientation(HORIZONTAL);

      this.getLblRegistroNome().setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.PEQUENO);
      this.getLblRegistroNome().setIntLinhaQuantidade(1);
      this.getLblRegistroNome().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

      this.getLblRegistroValor().setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.PEQUENO);
      this.getLblRegistroValor().setIntLinhaQuantidade(1);
      this.getLblRegistroValor().setStrTexto("-");
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void montarLayout() {

    super.montarLayout();

    try {

      this.addView(this.getLblRegistroNome());
      this.addView(this.getLblRegistroValor());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onClick(View v) {

    try {

      AppAndroid.getI().notificar(this.getLblRegistroValor().getStrTexto());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void reciclar(Cursor crs) {

    super.reciclar(crs);

    try {

      this.carregarDadosValor(crs);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setCln(ColunaAndroid cln) {

    _cln = cln;
  }

  @Override
  public void setEventos() {

    super.setEventos();

    try {

      if (this.getCln() == null) {

        return;
      }

      this.getLblRegistroValor().setOnClickListener(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}