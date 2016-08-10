package com.digosofter.digodroid.componente.item;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.componente.label.LabelGeral;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public class ItemCampo extends ItemMain implements OnClickListener
{

  private ColunaAndroid _cln;
  private LabelGeral _lblRegistroNome;
  private LabelGeral _lblRegistroValor;

  public ItemCampo(Context context, ColunaAndroid cln)
  {
    super(context);
    try
    {
      this.setCln(cln);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void carregarDados(Cursor crs)
  {
    super.carregarDados(crs);
    try
    {
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void carregarDadosNome(final Cursor crs)
  {
    String strNome;
    try
    {
      if (!Utils.getBooStrVazia(this.getLblRegistroNome().getText().toString()))
      {
        return;
      }
      strNome = "_registro_nome: ";
      strNome = strNome.replace("_registro_nome", this.getCln().getStrNomeExibicao());
      this.getLblRegistroNome().setText(strNome);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void carregarDadosValor(Cursor crs)
  {
    String strValor;
    try
    {
      this.getLblRegistroValor().setText(null);
      strValor = crs.getString(crs.getColumnIndex(this.getCln().getSqlNome()));
      if (Utils.getBooStrVazia(strValor))
      {
        return;
      }
      this.getCln().setStrValor(strValor);
      this.getLblRegistroValor().setText(this.getCln().getStrValorExibicao());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public ColunaAndroid getCln()
  {
    return _cln;
  }

  protected LabelGeral getLblRegistroNome()
  {
    try
    {
      if (_lblRegistroNome != null)
      {
        return _lblRegistroNome;
      }
      _lblRegistroNome = new LabelGeral(this.getContext());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _lblRegistroNome;
  }

  protected LabelGeral getLblRegistroValor()
  {
    try
    {
      if (_lblRegistroValor != null)
      {
        return _lblRegistroValor;
      }
      _lblRegistroValor = new LabelGeral(this.getContext());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _lblRegistroValor;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();
    try
    {
      this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
      this.setOrientation(HORIZONTAL);
      this.inicializarLblRegistroNome();
      this.inicializarLblRegistroValor();
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
    try
    {
      this.getLblRegistroNome().setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.PEQUENO);
      this.getLblRegistroNome().setMaxLines(1);
      this.getLblRegistroNome().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarLblRegistroValor()
  {
    try
    {
      this.getLblRegistroValor().setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.PEQUENO);
      this.getLblRegistroValor().setMaxLines(1);
      this.getLblRegistroValor().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
      this.getLblRegistroValor().setText(null);
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
  public void montarLayout()
  {
    super.montarLayout();
    try
    {
      this.addView(this.getLblRegistroNome());
      this.addView(this.getLblRegistroValor());
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
  public void onClick(View v)
  {
    try
    {
      AppAndroid.getI().notificar(this.getLblRegistroValor().getText().toString());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void setCln(ColunaAndroid cln)
  {
    _cln = cln;
  }

  @Override
  public void setEventos()
  {
    super.setEventos();
    try
    {
      if (this.getCln() == null)
      {
        return;
      }
      this.getLblRegistroValor().setOnClickListener(this);
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