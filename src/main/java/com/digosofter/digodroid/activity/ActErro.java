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
    try
    {
      this.setIntQtdErroVisivel(this.getIntQtdErroVisivel() + 1);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void actErro_btnIgnorarTodos_onClick(View viw)
  {
    try
    {
      this.setbooIgnorarTodos(true);
      this.finish();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.", ex);
    }
    finally
    {
    }
  }

  public void actErro_btnIgnorar_onClick(View viw)
  {
    try
    {
      this.finish();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.", ex);
    }
    finally
    {
    }
  }

  private void atualizarIntQtdErroVisivel()
  {
    try
    {
      if (_intQtdErroVisivel != 0)
      {
        return;
      }
      this.setbooIgnorarTodos(false);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private boolean getBooIgnorarTodos()
  {
    return _booIgnorarTodos;
  }

  public ErroAndroid getErr()
  {
    try
    {
      if (_err != null)
      {
        return _err;
      }
      _err = (ErroAndroid) this.getIntent().getSerializableExtra(ActErro.STR_EXTRA_IN_OBJ_ERRO);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.", ex);
    }
    finally
    {
    }
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
    try
    {
      if (_txtAppNome != null)
      {
        return _txtAppNome;
      }
      _txtAppNome = this.getView(R.id.actErro_txtAppNome, TextView.class);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.", ex);
    }
    finally
    {
    }
    return _txtAppNome;
  }

  private TextView getTxtErroMensagem()
  {
    try
    {
      if (_txtErroMensagem != null)
      {
        return _txtErroMensagem;
      }
      _txtErroMensagem = this.getView(R.id.actErro_txtErroMensagem, TextView.class);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.", ex);
    }
    finally
    {
    }
    return _txtErroMensagem;
  }

  private TextView getTxtErroTitulo()
  {
    try
    {
      if (_txtErroTitulo != null)
      {
        return _txtErroTitulo;
      }
      _txtErroTitulo = this.getView(R.id.actErro_txtErroTitulo, TextView.class);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.", ex);
    }
    finally
    {
    }
    return _txtErroTitulo;
  }

  @Override
  protected void montarLayout()
  {
    super.montarLayout();
    String strFormatada;
    try
    {
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.", ex);
    }
    finally
    {
    }
  }

  private void montarLayoutIgnorarTodos()
  {
    try
    {
      if (this.getIntQtdErroVisivel() < 2)
      {
        return;
      }
      this.getView(R.id.actErro_btnIgnorarTodos, Button.class).setVisibility(View.VISIBLE);
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
  protected void onDestroy()
  {
    super.onDestroy();
    try
    {
      this.setIntQtdErroVisivel(this.getIntQtdErroVisivel() - 1);
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
  protected void onStart()
  {
    super.onStart();
    try
    {
      if (!this.getBooIgnorarTodos())
      {
        return;
      }
      this.finish();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void setIntQtdErroVisivel(int intQtdErroVisivel)
  {
    try
    {
      _intQtdErroVisivel = intQtdErroVisivel;
      this.atualizarIntQtdErroVisivel();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void setbooIgnorarTodos(boolean booIgnorarTodos)
  {
    _booIgnorarTodos = booIgnorarTodos;
  }
}