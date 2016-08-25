package com.digosofter.digodroid.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public class ActErro extends ActMain
{
  public static final String STR_EXTRA_IN_OBJ_ERRO = "obj_erro";
  private static boolean _booIgnorarTodos;
  private static int _intQtdErroVisivel;
  private ErroAndroid _err;
  private TextView _txtAppNome;
  private TextView _txtErroMensagem;
  private TextView _txtErroTitulo;

  public ActErro()
  {
    this.setIntQtdErroVisivel(this.getIntQtdErroVisivel() + 1);
  }

  public void actErro_btnIgnorarTodos_onClick(View viw)
  {
    this.setbooIgnorarTodos(true);
    this.finish();
  }

  public void actErro_btnIgnorar_onClick(View viw)
  {
    this.finish();
  }

  private void atualizarIntQtdErroVisivel()
  {
    if (_intQtdErroVisivel != 0)
    {
      return;
    }
    this.setbooIgnorarTodos(false);
  }

  private boolean getBooIgnorarTodos()
  {
    return _booIgnorarTodos;
  }

  public ErroAndroid getErr()
  {
    if (_err != null)
    {
      return _err;
    }
    _err = (ErroAndroid) this.getIntent().getSerializableExtra(ActErro.STR_EXTRA_IN_OBJ_ERRO);

    return _err;
  }

  @Override
  protected int getIntLayoutId()
  {
    return R.layout.act_erro;
  }

  private int getIntQtdErroVisivel()
  {
    return _intQtdErroVisivel;
  }

  private TextView getTxtAppNome()
  {
    if (_txtAppNome != null)
    {
      return _txtAppNome;
    }
    _txtAppNome = this.getView(R.id.actErro_txtAppNome, TextView.class);

    return _txtAppNome;
  }

  private TextView getTxtErroMensagem()
  {
    if (_txtErroMensagem != null)
    {
      return _txtErroMensagem;
    }
    _txtErroMensagem = this.getView(R.id.actErro_txtErroMensagem, TextView.class);

    return _txtErroMensagem;
  }

  private TextView getTxtErroTitulo()
  {
    if (_txtErroTitulo != null)
    {
      return _txtErroTitulo;
    }
    _txtErroTitulo = this.getView(R.id.actErro_txtErroTitulo, TextView.class);

    return _txtErroTitulo;
  }

  @Override
  protected void montarLayout()
  {
    super.montarLayout();
    String strFormatada;

    if (this.getTxtAppNome() == null)
    {
      return;
    }
    this.montarLayoutIgnorarTodos();
    this.getTxtAppNome().setText((AppAndroid.getI() != null) ? AppAndroid.getI().getStrNome() : "App Android");
    this.getTxtErroTitulo().setText(this.getErr().getStrNome());
    if (Utils.getBooStrVazia(this.getErr().getStrMsgDetalhe()))
    {
      this.getTxtErroMensagem().setText(this.getErr().getStrMsg());
      return;
    }
    strFormatada = "_msg\n\nDetalhes: _detalhe";
    strFormatada = strFormatada.replace("_msg", this.getErr().getStrMsg());
    strFormatada = strFormatada.replace("_detalhe", this.getErr().getStrMsgDetalhe());
    this.getTxtErroMensagem().setText(strFormatada);
  }

  private void montarLayoutIgnorarTodos()
  {
    if (this.getIntQtdErroVisivel() < 2)
    {
      return;
    }
    this.getView(R.id.actErro_btnIgnorarTodos, Button.class).setVisibility(View.VISIBLE);
  }

  @Override
  protected void onDestroy()
  {
    super.onDestroy();

    this.setIntQtdErroVisivel(this.getIntQtdErroVisivel() - 1);
  }

  @Override
  protected void onStart()
  {
    super.onStart();

    if (!this.getBooIgnorarTodos())
    {
      return;
    }
    this.finish();
  }

  private void setIntQtdErroVisivel(int intQtdErroVisivel)
  {
    _intQtdErroVisivel = intQtdErroVisivel;
    this.atualizarIntQtdErroVisivel();
  }

  private void setbooIgnorarTodos(boolean booIgnorarTodos)
  {
    _booIgnorarTodos = booIgnorarTodos;
  }
}