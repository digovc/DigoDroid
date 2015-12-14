package com.digosofter.digodroid.controle.item;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.controle.painel.PainelGeral;
import com.digosofter.digodroid.database.DbColunaAndroid;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.database.DbColuna;

public class ItemConsulta extends ItemMain {

  private LabelGeral _lblRegistroId;
  private LabelGeral _lblRegistroNome;
  private PainelGeral _pnlCampoContainer;
  private PainelGeral _pnlConteudo;
  private DbTabelaAndroid<?> _tbl;

  public ItemConsulta(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public ItemConsulta(Context context, DbTabelaAndroid tbl, Cursor crs) {

    super(context);

    try {

      this.setTbl(tbl);
      this.carregarDados(crs, false);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  public ItemConsulta(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  private void carregarDados(Cursor crs, boolean booReciclar) {

    try {

      if (crs == null) {

        return;
      }

      if (this.getTbl() == null) {

        return;
      }

      this.getLblRegistroNome().setStrTexto(crs.getString(crs.getColumnIndex(this.getTbl().getClnNome().getStrNomeSql())));
      this.getLblRegistroId().setIntTexto(crs.getInt(crs.getColumnIndex(this.getTbl().getClnChavePrimaria().getStrNomeSql())));

      this.carregarDadosItem(crs, booReciclar);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void carregarDadosItem(Cursor crs, boolean booReciclar) {

    try {

      if (crs == null) {

        return;
      }

      if (this.getTbl() == null) {

        return;
      }

      for (DbColuna cln : this.getTbl().getLstClnConsultaOrdenado()) {

        this.carregarDadosItem(crs, (DbColunaAndroid) cln, booReciclar);
      }
    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void carregarDadosItem(Cursor crs, DbColunaAndroid cln, boolean booReciclar) {

    try {

      if (cln == null) {

        return;
      }

      if (cln.getBooChavePrimaria()) {

        return;
      }

      if (cln.getBooNome()) {

        return;
      }

      if (!cln.getBooVisivelConsulta()) {

        return;
      }

      if (booReciclar) {

        this.reciclarItem(crs, cln);
        return;
      }

      this.getPnlCampoContainer().addView(new ItemCampo(this.getContext(), cln, crs));

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  /**
   * Retorna o código do registro que este item representa.
   *
   * @return O código do registro que este item representa
   */
  public int getIntRegistroId() {

    return this.getLblRegistroId().getIntTexto();
  }

  private ItemCampo getItmCampo(DbColunaAndroid cln) {

    try {

      for (int i = 0; i < this.getPnlCampoContainer().getChildCount(); i++) {

        if (this.getPnlCampoContainer().getChildAt(i) == null) {

          continue;
        }

        if (!this.getPnlCampoContainer().getChildAt(i).getClass().equals(ItemCampo.class)) {

          continue;
        }

        if (((ItemCampo) this.getPnlCampoContainer().getChildAt(i)).getCln() != cln) {

          continue;
        }

        return (ItemCampo) this.getPnlCampoContainer().getChildAt(i);
      }

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return null;
  }

  private LabelGeral getLblRegistroId() {

    try {

      if (_lblRegistroId != null) {

        return _lblRegistroId;
      }

      _lblRegistroId = new LabelGeral(this.getContext());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _lblRegistroId;
  }

  private LabelGeral getLblRegistroNome() {

    try {

      if (_lblRegistroNome != null) {

        return _lblRegistroNome;
      }

      _lblRegistroNome = new LabelGeral(this.getContext());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _lblRegistroNome;
  }

  private PainelGeral getPnlCampoContainer() {

    try {

      if (_pnlCampoContainer != null) {

        return _pnlCampoContainer;
      }

      _pnlCampoContainer = new PainelGeral(this.getContext());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _pnlCampoContainer;
  }

  private PainelGeral getPnlConteudo() {

    try {

      if (_pnlConteudo != null) {

        return _pnlConteudo;
      }

      _pnlConteudo = new PainelGeral(this.getContext());
    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

    return _pnlConteudo;
  }

  /**
   * Retorna o nome do registro que este item representa.
   *
   * @return O nome do registro que este item representa.
   */
  public String getStrRegistroNome() {

    return this.getLblRegistroNome().getStrTexto();
  }

  private DbTabelaAndroid<?> getTbl() {

    return _tbl;
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.getPnlConteudo().setIntPadding(TemaDefault.getI().getIntPadding());

      this.getLblRegistroNome().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(50, this.getContext())));
      this.getLblRegistroNome().setStrTexto("Nome que representa este registro");

      this.getLblRegistroId().setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.PEQUENO);
      this.getLblRegistroId().setIntTexto(9999999);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void montarLayout() {

    super.montarLayout();

    try {

      this.addView(this.getPnlConteudo());

      this.getPnlConteudo().addView(this.getLblRegistroId());
      this.getPnlConteudo().addView(this.getLblRegistroNome());
      this.getPnlConteudo().addView(this.getPnlCampoContainer());

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  @Override
  public void reciclar(Cursor crs) {

    super.reciclar(crs);

    try {

      this.carregarDados(crs, true);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  private void reciclarItem(Cursor crs, DbColunaAndroid cln) {

    ItemCampo itmCampo;

    try {

      if (crs == null) {

        return;
      }

      if (cln == null) {

        return;
      }

      itmCampo = this.getItmCampo(cln);

      if (itmCampo == null) {

        return;
      }

      itmCampo.reciclar(crs);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }

  }

  private void setTbl(DbTabelaAndroid<?> tbl) {

    _tbl = tbl;
  }
}