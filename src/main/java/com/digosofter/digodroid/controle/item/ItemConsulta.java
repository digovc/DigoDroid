package com.digosofter.digodroid.controle.item;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.View;

import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.activity.ActConsulta;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.controle.painel.PainelGeral;
import com.digosofter.digodroid.controle.painel.PainelRipple;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.database.Coluna;

public class ItemConsulta extends ItemMain implements View.OnClickListener, View.OnLongClickListener {

  private LabelGeral _lblRegistroId;
  private LabelGeral _lblRegistroNome;
  private PainelGeral _pnlCabecalho;
  private PainelGeral _pnlCampoContainer;
  private PainelGeral _pnlConteudo;
  private PainelRipple _pnlRipple;
  private TabelaAndroid<?> _tbl;

  public ItemConsulta(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public ItemConsulta(Context context, TabelaAndroid tbl, Cursor crs) {

    super(context);

    try {

      this.setTbl(tbl);
      this.carregarDados(crs, false);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
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
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
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

      for (Coluna cln : this.getTbl().getLstClnConsultaOrdenado()) {

        this.carregarDadosItem(crs, (ColunaAndroid) cln, booReciclar);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void carregarDadosItem(Cursor crs, ColunaAndroid cln, boolean booReciclar) {

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
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
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

  private ItemCampo getItmCampo(ColunaAndroid cln) {

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

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return null;
  }

  private LabelGeral getLblRegistroId() {

    try {

      if (_lblRegistroId != null) {

        return _lblRegistroId;
      }

      _lblRegistroId = new LabelGeral(this.getContext());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lblRegistroId;
  }

  private LabelGeral getLblRegistroNome() {

    try {

      if (_lblRegistroNome != null) {

        return _lblRegistroNome;
      }

      _lblRegistroNome = new LabelGeral(this.getContext());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lblRegistroNome;
  }

  private PainelGeral getPnlCabecalho() {

    try {

      if (_pnlCabecalho != null) {

        return _pnlCabecalho;
      }

      _pnlCabecalho = new PainelGeral(this.getContext());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _pnlCabecalho;
  }

  private PainelGeral getPnlCampoContainer() {

    try {

      if (_pnlCampoContainer != null) {

        return _pnlCampoContainer;
      }

      _pnlCampoContainer = new PainelGeral(this.getContext());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _pnlCampoContainer;
  }

  private PainelGeral getPnlConteudo() {

    try {

      if (_pnlConteudo != null) {

        return _pnlConteudo;
      }

      _pnlConteudo = new PainelGeral(this.getContext());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _pnlConteudo;
  }

  private PainelRipple getPnlRipple() {

    try {

      if (_pnlRipple != null) {

        return _pnlRipple;
      }

      _pnlRipple = new PainelRipple(this.getContext());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _pnlRipple;
  }

  /**
   * Retorna o nome do registro que este item representa.
   *
   * @return O nome do registro que este item representa.
   */
  public String getStrRegistroNome() {

    return this.getLblRegistroNome().getStrTexto();
  }

  private TabelaAndroid<?> getTbl() {

    return _tbl;
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.getPnlCabecalho().setOrientation(HORIZONTAL);

      this.getPnlConteudo().setIntPadding(TemaDefault.getI().getIntPadding());

      this.getLblRegistroNome().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(55, this.getContext())));
      this.getLblRegistroNome().setStrTexto("Nome que representa este registro");

      this.getLblRegistroId().setIntTexto(9999999);
      this.getLblRegistroId().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
      this.getLblRegistroId().setPadding(0, 0, UtilsAndroid.dpToPx(10, this.getContext()), 0);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void montarLayout() {

    super.montarLayout();

    try {

      this.addView(this.getPnlRipple());

      this.getPnlRipple().addView(this.getPnlConteudo());
      this.getPnlConteudo().addView(this.getPnlCabecalho());
      this.getPnlCabecalho().addView(this.getLblRegistroId());
      this.getPnlCabecalho().addView(this.getLblRegistroNome());
      this.getPnlConteudo().addView(this.getPnlCampoContainer());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onClick(final View v) {

    try {

      if (this.getIntRegistroId() < 1) {

        return;
      }

      if (this.getContext() == null) {

        return;
      }

      if (!ActConsulta.class.isAssignableFrom(this.getContext().getClass())) {

        return;
      }

      ((ActConsulta)this.getContext()).onItemClick(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public boolean onLongClick(final View v) {

    try {

      if (this.getIntRegistroId() < 1) {

        return false;
      }

      if (this.getContext() == null) {

        return false;
      }

      if (!ActConsulta.class.isAssignableFrom(this.getContext().getClass())) {

        return false;
      }

      ((ActConsulta) this.getContext()).onItemLongClick(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return true;
  }

  @Override
  public void reciclar(Cursor crs) {

    super.reciclar(crs);

    try {

      this.carregarDados(crs, true);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void reciclarItem(Cursor crs, ColunaAndroid cln) {

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

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

  }

  @Override
  public void setEventos() {

    super.setEventos();

    try {

      this.getPnlRipple().setOnClickListener(this);
      this.getPnlRipple().setOnLongClickListener(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setTbl(TabelaAndroid<?> tbl) {

    _tbl = tbl;
  }
}