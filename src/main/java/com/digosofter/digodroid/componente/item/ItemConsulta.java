package com.digosofter.digodroid.componente.item;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.View;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.componente.label.LabelGeral;
import com.digosofter.digodroid.componente.painel.PainelGeral;
import com.digosofter.digodroid.componente.painel.PainelRipple;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Coluna;

public class ItemConsulta extends ItemMain implements View.OnClickListener, View.OnLongClickListener
{

  private int _intRegistroId;
  private LabelGeral _lblRegistroTitulo;
  private PainelGeral _pnlCampos;
  private PainelGeral _pnlConteudo;
  private PainelRipple _pnlRipple;
  private String _strRegistroNome;
  private TabelaAndroid<?> _tbl;

  public ItemConsulta(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public ItemConsulta(Context cnt, TabelaAndroid tbl)
  {
    super(cnt);
    try
    {
      this.setTbl(tbl);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public ItemConsulta(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void carregarDados(Cursor crs)
  {
    super.carregarDados(crs);
    String strTitulo;
    try
    {
      if (crs == null)
      {
        return;
      }
      if (this.getTbl() == null)
      {
        return;
      }
      this.setIntRegistroId(crs.getInt(crs.getColumnIndex(this.getTbl().getClnChavePrimaria().getSqlNome())));
      this.setStrRegistroNome(crs.getString(crs.getColumnIndex(this.getTbl().getClnNome().getSqlNome())));
      strTitulo = "_registro_id - _registro_nome";
      strTitulo = strTitulo.replace("_registro_id", String.valueOf(this.getIntRegistroId()));
      strTitulo = strTitulo.replace("_registro_nome", (!Utils.getBooStrVazia(this.getStrRegistroNome()) ? this.getStrRegistroNome() : String.valueOf(this.getIntRegistroId())));
      this.getLblRegistroTitulo().setText(strTitulo);
      this.carregarDadosItem(crs);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void carregarDadosItem(Cursor crs)
  {
    try
    {
      if (crs == null)
      {
        return;
      }
      if (this.getTbl() == null)
      {
        return;
      }
      for (Coluna cln : this.getTbl().getLstClnConsultaOrdenado())
      {
        this.carregarDadosItem(crs, (ColunaAndroid) cln);
      }
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void carregarDadosItem(Cursor crs, ColunaAndroid cln)
  {
    ItemCampo itmCampo;
    try
    {
      if (cln == null)
      {
        return;
      }
      if (cln.getBooChavePrimaria())
      {
        return;
      }
      if (cln.getBooNome())
      {
        return;
      }
      if (!cln.getBooVisivelConsulta())
      {
        return;
      }
      itmCampo = this.getItmCampo(cln);
      if (itmCampo == null)
      {
        return;
      }
      itmCampo.carregarDados(crs);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  /**
   * Retorna o código do registro que este item representa.
   *
   * @return O código do registro que este item representa
   */
  public int getIntRegistroId()
  {
    return _intRegistroId;
  }

  private ItemCampo getItmCampo(ColunaAndroid cln)
  {
    ItemCampo itmCampoResultado;
    try
    {
      for (int i = 0; i < this.getPnlCampos().getChildCount(); i++)
      {
        if (this.getPnlCampos().getChildAt(i) == null)
        {
          continue;
        }
        if (!this.getPnlCampos().getChildAt(i).getClass().equals(ItemCampo.class))
        {
          continue;
        }
        if (((ItemCampo) this.getPnlCampos().getChildAt(i)).getCln() != cln)
        {
          continue;
        }
        return (ItemCampo) this.getPnlCampos().getChildAt(i);
      }
      itmCampoResultado = new ItemCampo(this.getContext(), cln);
      this.getPnlCampos().addView(itmCampoResultado);
      return itmCampoResultado;
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return null;
  }

  private LabelGeral getLblRegistroTitulo()
  {
    try
    {
      if (_lblRegistroTitulo != null)
      {
        return _lblRegistroTitulo;
      }
      _lblRegistroTitulo = new LabelGeral(this.getContext());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _lblRegistroTitulo;
  }

  private PainelGeral getPnlCampos()
  {
    try
    {
      if (_pnlCampos != null)
      {
        return _pnlCampos;
      }
      _pnlCampos = new PainelGeral(this.getContext());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _pnlCampos;
  }

  private PainelGeral getPnlConteudo()
  {
    try
    {
      if (_pnlConteudo != null)
      {
        return _pnlConteudo;
      }
      _pnlConteudo = new PainelGeral(this.getContext());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _pnlConteudo;
  }

  private PainelRipple getPnlRipple()
  {
    try
    {
      if (_pnlRipple != null)
      {
        return _pnlRipple;
      }
      _pnlRipple = new PainelRipple(this.getContext());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _pnlRipple;
  }

  /**
   * Retorna o nome do registro que este item representa.
   *
   * @return O nome do registro que este item representa.
   */
  public String getStrRegistroNome()
  {
    return _strRegistroNome;
  }

  private TabelaAndroid<?> getTbl()
  {
    return _tbl;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();
    try
    {
      this.setBackground(this.getResources().getDrawable(R.drawable.bkg_borda));
      this.setOrientation(VERTICAL);
      this.inicializarLblRegistroTitulo();
      this.inicializarPnlCampos();
      this.inicializarPnlConteudo();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarLblRegistroTitulo()
  {
    try
    {
      this.getLblRegistroTitulo().setBackgroundColor(this.getContext().getResources().getColor(R.color.cor_borda));
      this.getLblRegistroTitulo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
      this.getLblRegistroTitulo().setPadding(UtilsAndroid.dpToPx(10, this.getContext()), 0, UtilsAndroid.dpToPx(10, this.getContext()), 0);
      this.getLblRegistroTitulo().setText("999 - Nome que representa este registro");
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarPnlCampos()
  {
    int intPadding;
    try
    {
      intPadding = UtilsAndroid.dpToPx(10, this.getContext());
      this.getPnlCampos().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
      this.getPnlCampos().setOrientation(VERTICAL);
      this.getPnlCampos().setPadding(intPadding, intPadding, intPadding, intPadding);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarPnlConteudo()
  {
    try
    {
      this.getPnlConteudo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
      this.getPnlConteudo().setOrientation(VERTICAL);
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
      this.addView(this.getPnlRipple());
      this.getPnlRipple().addView(this.getPnlConteudo());
      this.getPnlConteudo().addView(this.getLblRegistroTitulo());
      this.getPnlConteudo().addView(this.getPnlCampos());
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
  public void onClick(final View v)
  {
    try
    {
      if (this.getIntRegistroId() < 1)
      {
        return;
      }
      if (this.getContext() == null)
      {
        return;
      }
      if (!ActConsulta.class.isAssignableFrom(this.getContext().getClass()))
      {
        return;
      }
      ((ActConsulta) this.getContext()).onItemClick(this);
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
  public boolean onLongClick(final View v)
  {
    try
    {
      if (this.getIntRegistroId() < 1)
      {
        return false;
      }
      if (this.getContext() == null)
      {
        return false;
      }
      if (!ActConsulta.class.isAssignableFrom(this.getContext().getClass()))
      {
        return false;
      }
      ((ActConsulta) this.getContext()).onItemLongClick(this);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return true;
  }

  @Override
  public void setEventos()
  {
    super.setEventos();
    try
    {
      this.getPnlRipple().setOnClickListener(this);
      this.getPnlRipple().setOnLongClickListener(this);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void setIntRegistroId(int intRegistroId)
  {
    _intRegistroId = intRegistroId;
  }

  private void setStrRegistroNome(String strRegistroNome)
  {
    _strRegistroNome = strRegistroNome;
  }

  private void setTbl(TabelaAndroid<?> tbl)
  {
    _tbl = tbl;
  }
}