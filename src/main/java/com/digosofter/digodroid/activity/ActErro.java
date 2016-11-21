package com.digosofter.digodroid.activity;

import android.view.Menu;
import android.view.MenuItem;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digojava.Utils;

public class ActErro extends ActMain
{
  public static final String STR_EXTRA_INT_STR_ERRO_DESCRICAO = "str_erro_descricao";
  public static final String STR_EXTRA_INT_STR_ERRO_TITULO = "str_erro_titulo";

  private static final String STR_MENU_COMPARTILHAR = "Compartilhar";
  private LabelGeral _lblDescricao;
  private LabelGeral _lblTitulo;

  private boolean compartilhar()
  {
    if (AppAndroid.getI() == null)
    {
      return false;
    }

    if (Utils.getBooStrVazia(this.getLblTitulo().getText().toString()))
    {
      return false;
    }
    if (Utils.getBooStrVazia(this.getLblDescricao().getText().toString()))
    {
      return false;
    }

    String strConteudo = String.format("%s\n\n%s", this.getLblTitulo().getText().toString(), this.getLblDescricao().getText().toString());

    AppAndroid.getI().compartilhar(this, this.getLblTitulo().getText().toString(), strConteudo);

    return true;
  }

  @Override
  public int getIntLayoutId()
  {
    return R.layout.act_erro;
  }

  private LabelGeral getLblDescricao()
  {
    if (_lblDescricao != null)
    {
      return _lblDescricao;
    }

    _lblDescricao = this.getView(R.id.actErro_lblDescricao);

    return _lblDescricao;
  }

  private LabelGeral getLblTitulo()
  {
    if (_lblTitulo != null)
    {
      return _lblTitulo;
    }

    _lblTitulo = this.getView(R.id.actErro_lblTitulo);

    return _lblTitulo;
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.setTitle("Erro");
  }

  @Override
  protected void montarLayout()
  {
    super.montarLayout();

    this.montarLayoutLblDescricao();
    this.montarLayoutLblTitulo();
  }

  private void montarLayoutLblDescricao()
  {
    String strDescricao = this.getIntent().getStringExtra(STR_EXTRA_INT_STR_ERRO_DESCRICAO);

    if (Utils.getBooStrVazia(strDescricao))
    {
      return;
    }

    this.getLblDescricao().setText(strDescricao);
  }

  private void montarLayoutLblTitulo()
  {
    String strTitulo = this.getIntent().getStringExtra(STR_EXTRA_INT_STR_ERRO_TITULO);

    if (Utils.getBooStrVazia(strTitulo))
    {
      return;
    }

    this.getLblTitulo().setText(strTitulo);
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu mnu)
  {
    if (!super.onCreateOptionsMenu(mnu))
    {
      return false;
    }

    this.onCreateOptionsMenuCompartilhar(mnu);

    return true;
  }

  private void onCreateOptionsMenuCompartilhar(final Menu mnu)
  {
    MenuItem mniCompartilhar = mnu.add(STR_MENU_COMPARTILHAR);
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem mni)
  {
    if (super.onOptionsItemSelected(mni))
    {
      return true;
    }

    switch (mni.getTitle().toString())
    {
      case STR_MENU_COMPARTILHAR:
        return this.compartilhar();
    }

    return false;
  }
}