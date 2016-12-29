package com.digosofter.digodroid.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.botao.BotaoGeral;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.log.LogErro;
import com.digosofter.digojava.Utils;

public class ActErro extends ActMain implements View.OnClickListener
{
  public static final String STR_EXTRA_INT_STR_ERRO_DESCRICAO = "str_erro_descricao";
  public static final String STR_EXTRA_INT_STR_ERRO_TITULO = "str_erro_titulo";
  public static final String STR_EXTRA_OUT_BOO_ERRO_IGNORAR_TODOS = "boo_erro_ignorar_todos";
  private static final String STR_MENU_COMPARTILHAR = "Compartilhar";
  private BotaoGeral _btnIgnorarTodos;
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

    Aparelho.getI().compartilhar(this, this.getLblTitulo().getText().toString(), strConteudo);

    return true;
  }

  @Override
  protected void finalizar()
  {
    super.finalizar();

    LogErro.getI().removerActErro(this);
  }

  private BotaoGeral getBtnIgnorarTodos()
  {
    if (_btnIgnorarTodos != null)
    {
      return _btnIgnorarTodos;
    }

    _btnIgnorarTodos = this.getView(R.id.actErro_btnIgnorarTodos);

    return _btnIgnorarTodos;
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

  private void ignorarTodos()
  {
    LogErro.getI().ignorarTodos();
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.setTitle("Erro");

    LogErro.getI().addActErro(this);

    this.inicializarBtnIgnorarTodos();
  }

  private void inicializarBtnIgnorarTodos()
  {
    this.getBtnIgnorarTodos().setVisibility((LogErro.getI().getLstActErro().size() > 1) ? View.VISIBLE : View.GONE);
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
  protected void onActivityResult(final int intRequestCode, final int intResultCode, final Intent ittResult)
  {
    super.onActivityResult(intRequestCode, intResultCode, ittResult);

    if (ittResult == null)
    {
      return;
    }

    this.onActivityResultIgnorarTodos(ittResult);
  }

  private void onActivityResultIgnorarTodos(final Intent ittResult)
  {
    if (!ittResult.getBooleanExtra(STR_EXTRA_OUT_BOO_ERRO_IGNORAR_TODOS, false))
    {
      return;
    }

    this.setResult(0, new Intent().putExtra(STR_EXTRA_OUT_BOO_ERRO_IGNORAR_TODOS, true));

    this.finish();
  }

  @Override
  public void onClick(final View viw)
  {
    if (viw.equals(this.getBtnIgnorarTodos()))
    {
      this.ignorarTodos();
      return;
    }
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

  @Override
  protected void setEventos()
  {
    super.setEventos();

    this.getBtnIgnorarTodos().setOnClickListener(this);
  }
}