package com.digosofter.digodroid.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.componente.botao.BotaoGeral;
import com.digosofter.digodroid.componente.label.LabelGeral;
import com.digosofter.digodroid.log.LogErro;
import com.digosofter.digojava.Utils;

public class ActErro extends ActMain implements View.OnClickListener
{
  public static final String STR_EXTRA_INT_STR_ERRO_DESCRICAO = "str_erro_descricao";
  public static final String STR_EXTRA_INT_STR_ERRO_TITULO = "str_erro_titulo";
  public static final String STR_EXTRA_OUT_BOO_ERRO_IGNORAR_TODOS = "boo_erro_ignorar_todos";
  private static final String STR_MENU_COMPARTILHAR = "Compartilhar";

  private BotaoGeral _btnIgnorarTodos;
  private LabelGeral _lblTitulo;
  private String _strDescricao;

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

    String strConteudo = String.format("%s\n\n%s", this.getLblTitulo().getText().toString(), this.getStrDescricao().toString());

    Aparelho.getI().compartilhar(this, String.format("Erro no %s", AppAndroid.getI().getStrNome()), strConteudo);

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

  private LabelGeral getLblTitulo()
  {
    if (_lblTitulo != null)
    {
      return _lblTitulo;
    }

    _lblTitulo = this.getView(R.id.actErro_lblTitulo);

    return _lblTitulo;
  }

  private String getStrDescricao()
  {
    return _strDescricao;
  }

  private void ignorarTodos()
  {
    LogErro.getI().ignorarTodos();
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    ((NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();

    this.setTitle("Erro");

    this.getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f44336")));

    LogErro.getI().addActErro(this);

    this.inicializarBtnIgnorarTodos();
  }

  private void inicializarBtnIgnorarTodos()
  {
    // TODO: O processo de "ignorar todos" precisa ser corrigido.
    //    this.getBtnIgnorarTodos().setVisibility((!LogErro.getI().getLstActErro().isEmpty()) ? View.VISIBLE : View.GONE);
    this.getBtnIgnorarTodos().setVisibility(View.GONE);
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

    this.setStrDescricao(strDescricao);
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

    mnu.add(STR_MENU_COMPARTILHAR);

    return true;
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

  private void setStrDescricao(String strDescricao)
  {
    _strDescricao = strDescricao;
  }
}