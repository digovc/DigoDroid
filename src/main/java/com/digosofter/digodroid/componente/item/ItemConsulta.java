package com.digosofter.digodroid.controle.item;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.controle.linha.LinhaGeral;
import com.digosofter.digodroid.controle.painel.PainelGeral;
import com.digosofter.digodroid.controle.painel.PainelRipple;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TblAndroidMain;
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
  private TblAndroidMain<?> _tbl;
  private LinhaGeral _viwLinha;

  public ItemConsulta(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public ItemConsulta(Context cnt, TblAndroidMain tbl)
  {
    super(cnt);

    this.setTbl(tbl);
  }

  public ItemConsulta(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  public ItemConsulta(Context cnt, AttributeSet atr, int intDefStyleAttr, TblAndroidMain tbl)
  {
    super(cnt, atr, intDefStyleAttr);

    this.setTbl(tbl);
  }

  private void atualizarVisibilidadeItem()
  {
    for (int i = 0; i < this.getPnlCampos().getChildCount(); i++)
    {
      if (!this.getPnlCampos().getChildAt(i).getClass().equals(ItemCampo.class))
      {
        continue;
      }

      if (((ItemCampo) this.getPnlCampos().getChildAt(i)).getCln().getBooVisivelConsulta())
      {
        continue;
      }

      this.getPnlCampos().removeViewAt(i);
    }
  }

  @Override
  public void carregarDados(Cursor crs)
  {
    super.carregarDados(crs);

    if (crs == null)
    {
      return;
    }

    if (this.getTbl() == null)
    {
      return;
    }

    this.setIntRegistroId(crs.getInt(crs.getColumnIndex(this.getTbl().getClnIntId().getSqlNome())));
    this.setStrRegistroNome(crs.getString(crs.getColumnIndex(this.getTbl().getClnNome().getSqlNome())));

    String strTitulo = "_registro_id - _registro_nome";

    strTitulo = strTitulo.replace("_registro_id", String.valueOf(this.getIntRegistroId()));
    strTitulo = strTitulo.replace("_registro_nome", (!Utils.getBooStrVazia(this.getStrRegistroNome()) ? this.getStrRegistroNome() : String.valueOf(this.getIntRegistroId())));

    this.getLblRegistroTitulo().setText(strTitulo);

    this.carregarDadosItem(crs);
    this.atualizarVisibilidadeItem();
  }

  private void carregarDadosItem(Cursor crs)
  {
    if (crs == null)
    {
      return;
    }

    if (this.getTbl() == null)
    {
      return;
    }

    for (Coluna cln : this.getTbl().getLstClnConsulta())
    {
      this.carregarDadosItem(crs, (ColunaAndroid) cln);
    }
  }

  private void carregarDadosItem(Cursor crs, ColunaAndroid cln)
  {
    if (cln == null)
    {
      return;
    }

    if (cln.equals(this.getTbl().getClnIntId()))
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

    ItemCampo itmCampo = this.getItmCampo(cln);

    if (itmCampo == null)
    {
      return;
    }

    itmCampo.carregarDados(crs);
  }

  /**
   * Retorna o código do registro que este consulta_item representa.
   *
   * @return O código do registro que este consulta_item representa
   */
  public int getIntRegistroId()
  {
    return _intRegistroId;
  }

  private ItemCampo getItmCampo(ColunaAndroid cln)
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

    ItemCampo itmCampoResultado = new ItemCampo(this.getContext(), cln);

    this.getPnlCampos().addView(itmCampoResultado);

    return itmCampoResultado;
  }

  private LabelGeral getLblRegistroTitulo()
  {
    if (_lblRegistroTitulo != null)
    {
      return _lblRegistroTitulo;
    }

    _lblRegistroTitulo = new LabelGeral(this.getContext());

    return _lblRegistroTitulo;
  }

  private PainelGeral getPnlCampos()
  {
    if (_pnlCampos != null)
    {
      return _pnlCampos;
    }

    _pnlCampos = new PainelGeral(this.getContext());

    return _pnlCampos;
  }

  private PainelGeral getPnlConteudo()
  {
    if (_pnlConteudo != null)
    {
      return _pnlConteudo;
    }

    _pnlConteudo = new PainelGeral(this.getContext());

    return _pnlConteudo;
  }

  private PainelRipple getPnlRipple()
  {
    if (_pnlRipple != null)
    {
      return _pnlRipple;
    }

    _pnlRipple = new PainelRipple(this.getContext());

    return _pnlRipple;
  }

  /**
   * Retorna o nome do registro que este consulta_item representa.
   *
   * @return O nome do registro que este consulta_item representa.
   */
  public String getStrRegistroNome()
  {
    return _strRegistroNome;
  }

  private TblAndroidMain<?> getTbl()
  {
    return _tbl;
  }

  private LinhaGeral getViwLinha()
  {
    if (_viwLinha != null)
    {
      return _viwLinha;
    }

    _viwLinha = new LinhaGeral(this.getContext());

    return _viwLinha;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.setOrientation(VERTICAL);

    this.setBackgroundColor(Color.WHITE);

    this.getViwLinha().setEnmDisposicao(LinhaGeral.EnmDisposicao.HORIZONTAL);

    this.inicializarLblRegistroTitulo();
    this.inicializarPnlCampos();
    this.inicializarPnlConteudo();
  }

  private void inicializarLblRegistroTitulo()
  {
    this.getLblRegistroTitulo().setGravity(Gravity.CENTER_VERTICAL);
    this.getLblRegistroTitulo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    this.getLblRegistroTitulo().setMinHeight(UtilsAndroid.dpToPx(55, this.getContext()));
    this.getLblRegistroTitulo().setPadding(UtilsAndroid.dpToPx(10, this.getContext()), 0, UtilsAndroid.dpToPx(10, this.getContext()), 0);
    this.getLblRegistroTitulo().setText("999 - Nome que representa este registro");
    this.getLblRegistroTitulo().setTypeface(null, Typeface.BOLD);
  }

  private void inicializarPnlCampos()
  {
    int intPadding = UtilsAndroid.dpToPx(10, this.getContext());

    this.getPnlCampos().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    this.getPnlCampos().setOrientation(VERTICAL);
    this.getPnlCampos().setPadding(intPadding, intPadding, intPadding, intPadding);
  }

  private void inicializarPnlConteudo()
  {
    this.getPnlConteudo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    this.getPnlConteudo().setOrientation(VERTICAL);
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();

    this.addView(this.getPnlRipple());

    this.getPnlRipple().addView(this.getPnlConteudo());

    this.getPnlConteudo().addView(this.getLblRegistroTitulo());
    this.getPnlConteudo().addView(this.getViwLinha());
    this.getPnlConteudo().addView(this.getPnlCampos());
  }

  @Override
  public void onClick(final View viw)
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

  @Override
  public boolean onLongClick(final View viw)
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

    return true;
  }

  @Override
  public void setEventos()
  {
    super.setEventos();

    this.getPnlRipple().setOnClickListener(this);
    this.getPnlRipple().setOnLongClickListener(this);
  }

  private void setIntRegistroId(int intRegistroId)
  {
    _intRegistroId = intRegistroId;
  }

  private void setStrRegistroNome(String strRegistroNome)
  {
    _strRegistroNome = strRegistroNome;
  }

  private void setTbl(TblAndroidMain<?> tbl)
  {
    _tbl = tbl;
  }
}