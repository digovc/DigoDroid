package com.digosofter.digodroid.controle.item;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digojava.Utils;

public class ItemCampo extends ItemMain implements OnClickListener
{
  private ColunaAndroid _cln;
  private LabelGeral _lblRegistroNome;
  private LabelGeral _lblRegistroValor;

  public ItemCampo(Context cnt, ColunaAndroid cln)
  {
    super(cnt);

    this.setCln(cln);
  }

  public void carregarDados(Cursor crs)
  {
    super.carregarDados(crs);

    if (!this.getCln().getBooVisivelConsulta())
    {
      this.setVisibility(GONE);
      return;
    }

    if (crs == null)
    {
      return;
    }

    this.carregarDadosNome(crs);
    this.carregarDadosValor(crs);
  }

  private void carregarDadosNome(final Cursor crs)
  {
    if (!Utils.getBooStrVazia(this.getLblRegistroNome().getText().toString()))
    {
      return;
    }

    String strNome = "_registro_nome: ";

    strNome = strNome.replace("_registro_nome", this.getCln().getStrNomeExibicao());

    this.getLblRegistroNome().setText(strNome);
  }

  private void carregarDadosValor(Cursor crs)
  {
    this.getLblRegistroValor().setText(null);

    String strValor = crs.getString(crs.getColumnIndex(this.getCln().getSqlNome()));

    if (Utils.getBooStrVazia(strValor))
    {
      return;
    }

    this.getCln().setStrValor(strValor);

    this.getLblRegistroValor().setText(this.getCln().getStrValorExibicao());
  }

  public ColunaAndroid getCln()
  {
    return _cln;
  }

  protected LabelGeral getLblRegistroNome()
  {
    if (_lblRegistroNome != null)
    {
      return _lblRegistroNome;
    }

    _lblRegistroNome = new LabelGeral(this.getContext());

    return _lblRegistroNome;
  }

  protected LabelGeral getLblRegistroValor()
  {
    if (_lblRegistroValor != null)
    {
      return _lblRegistroValor;
    }

    _lblRegistroValor = new LabelGeral(this.getContext());

    return _lblRegistroValor;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    this.setOrientation(HORIZONTAL);

    this.inicializarLblRegistroNome();
    this.inicializarLblRegistroValor();
  }

  private void inicializarLblRegistroNome()
  {
    this.getLblRegistroNome().setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.PEQUENO);
    this.getLblRegistroNome().setMaxLines(1);
    this.getLblRegistroNome().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
  }

  private void inicializarLblRegistroValor()
  {
    this.getLblRegistroValor().setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.PEQUENO);
    this.getLblRegistroValor().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    this.getLblRegistroValor().setMaxLines(1);
    this.getLblRegistroValor().setText(null);
//    this.getLblRegistroValor().setTypeface(null, Typeface.BOLD);
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();

    this.addView(this.getLblRegistroNome());
    this.addView(this.getLblRegistroValor());
  }

  @Override
  public void onClick(View v)
  {
    AppAndroid.getI().notificar(this.getLblRegistroValor().getText().toString());
  }

  private void setCln(ColunaAndroid cln)
  {
    _cln = cln;
  }

  @Override
  public void setEventos()
  {
    super.setEventos();

    if (this.getCln() == null)
    {
      return;
    }

    this.getLblRegistroValor().setOnClickListener(this);
  }
}