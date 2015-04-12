package com.digosofter.digodroid.activity;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digodroid.item.ItmCampo;
import com.digosofter.digodroid.item.ItmConsulta;
import com.digosofter.digodroid.item.ItmDetalheGrupo;
import com.digosofter.digojava.Utils;

public class ActDetalhe extends ActMain {

  public static final String EXTRA_IN_INT_REGISTRO_ID = "registro_id";

  public static final CharSequence STR_DETALHE_ACAO_ALTERAR = "Alterar";

  private int _intRegistroId;
  private ItmConsulta _itmConsulta;
  private List<ItmDetalheGrupo> _lstItmDetalheGrupo;
  private LinearLayout _pnlCampos;
  private DbTabelaAndroid _tbl;
  private TextView _txtId;
  private TextView _txtNome;

  @Override
  protected int getIntLayoutId() {

    return R.layout.act_detalhe;
  }

  private int getIntRegistroId() {

    try {

      if (_intRegistroId > 0) {

        return _intRegistroId;
      }

      _intRegistroId = this.getIntent().getExtras().getInt(ActDetalhe.EXTRA_IN_INT_REGISTRO_ID);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _intRegistroId;
  }

  private ItmConsulta getItmConsulta() {

    Cursor crs;

    try {

      if (_itmConsulta != null) {

        return _itmConsulta;
      }

      crs = this.getTbl().getCrsDados(this.getTbl().getClnChavePrimaria(), this.getIntRegistroId());

      if (crs == null || !crs.moveToFirst()) {

        return null;
      }

      _itmConsulta = new ItmConsulta();

      _itmConsulta.setTbl(this.getTbl());
      _itmConsulta.carregarDados(crs, false);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _itmConsulta;
  }

  private ItmDetalheGrupo getItmDetalheGrupo(ItmCampo itmCampo) {

    ItmDetalheGrupo itmResultado = null;

    try {

      if (itmCampo == null) {

        return null;
      }

      if (itmCampo.getCln() == null) {

        return null;
      }

      if (Utils.getBooStrVazia(itmCampo.getCln().getStrGrupoNome())) {

        return this.getLstItmDetalheGrupo().get(0);
      }

      for (ItmDetalheGrupo itm : this.getLstItmDetalheGrupo()) {

        if (itm == null) {

          continue;
        }

        if (!itm.getStrNome().equals(itmCampo.getCln().getStrGrupoNome())) {

          continue;
        }

        itmResultado = itm;
        break;
      }

      if (itmResultado != null) {

        return itmResultado;
      }

      itmResultado = new ItmDetalheGrupo();
      itmResultado.setStrNome(itmCampo.getCln().getStrGrupoNome());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return itmResultado;
  }

  private List<ItmDetalheGrupo> getLstItmDetalheGrupo() {

    ItmDetalheGrupo itm;

    try {

      if (_lstItmDetalheGrupo != null) {

        return _lstItmDetalheGrupo;
      }

      itm = new ItmDetalheGrupo();

      itm.setStrNome("Geral");

      _lstItmDetalheGrupo = new ArrayList<ItmDetalheGrupo>();
      _lstItmDetalheGrupo.add(itm);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lstItmDetalheGrupo;
  }

  private LinearLayout getPnlCampos() {

    try {

      if (_pnlCampos != null) {

        return _pnlCampos;
      }

      _pnlCampos = this.getLinearLayout(R.id.actDetalhe_pnlCampos);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _pnlCampos;
  }

  private DbTabelaAndroid getTbl() {

    try {

      if (_tbl != null) {

        return _tbl;
      }

      _tbl = (DbTabelaAndroid) AppAndroid.getI().getTblSelec();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _tbl;
  }

  private TextView getTxtId() {

    try {

      if (_txtId != null) {

        return _txtId;
      }

      _txtId = this.getTextView(R.id.actDetalhe_txtId);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txtId;
  }

  private TextView getTxtNome() {

    try {

      if (_txtNome != null) {

        return _txtNome;
      }

      _txtNome = this.getTextView(R.id.actDetalhe_txtNome);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txtNome;
  }

  private void iniciarlizarLstItmDetalheGrupo() {

    ItmDetalheGrupo itmDetalheGrupo;

    try {

      for (ItmCampo itmCampo : this.getItmConsulta().getLstItmCampo()) {

        if (itmCampo == null) {

          continue;
        }

        itmDetalheGrupo = this.getItmDetalheGrupo(itmCampo);

        if (itmDetalheGrupo.getLstItmCampo().contains(itmCampo)) {

          continue;
        }

        itmDetalheGrupo.getLstItmCampo().add(itmCampo);

        if (this.getLstItmDetalheGrupo().contains(itmDetalheGrupo)) {

          continue;
        }

        this.getLstItmDetalheGrupo().add(itmDetalheGrupo);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();

    try {

      this.setTitle(this.getTbl().getStrNomeExibicao());
      this.getTxtId().setText(String.valueOf(this.getItmConsulta().getIntRegistroId()));
      this.getTxtNome().setText(this.getItmConsulta().getStrNome());
      this.montarLayoutItem();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarLayoutItem() {

    try {

      this.iniciarlizarLstItmDetalheGrupo();

      for (ItmDetalheGrupo itmDetalheGrupo : this.getLstItmDetalheGrupo()) {

        if (itmDetalheGrupo == null) {

          return;
        }

        this.montarLayoutItem(itmDetalheGrupo);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarLayoutItem(ItmDetalheGrupo itmDetalheGrupo) {

    try {

      if (itmDetalheGrupo == null) {

        return;
      }

      if (itmDetalheGrupo.getLstItmCampo().size() == 0) {

        return;
      }

      itmDetalheGrupo.montarLayout();

      this.getPnlCampos().addView(itmDetalheGrupo.getViw());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarMenu(Menu mnu) {

    try {

      if (mnu == null) {

        return;
      }

      this.montarMenuAlterar(mnu);
      this.montarMenuTblAcao(mnu);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarMenuAlterar(Menu mnu) {

    try {

      if (mnu == null) {

        return;
      }

      if (this.getTbl() == null) {

        return;
      }

      if (this.getTbl().getClsActCadastro() == null) {

        return;
      }

      mnu.add(ActDetalhe.STR_DETALHE_ACAO_ALTERAR);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarMenuTblAcao(Menu mnu) {

    try {

      if (this.getTbl() == null) {

        return;
      }

      if (this.getTbl().getLstStrAcao() == null) {

        return;
      }

      if (this.getTbl().getLstStrAcao().isEmpty()) {

        return;
      }

      for (String strAcao : this.getTbl().getLstStrAcao()) {

        if (Utils.getBooStrVazia(strAcao)) {

          continue;
        }

        mnu.add(strAcao);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem itm) {

    try {

      if (itm == null) {

        return super.onOptionsItemSelected(itm);
      }

      if (this.getTbl() == null) {

        return super.onOptionsItemSelected(itm);
      }

      this.getTbl().processarAcao(this, itm.getTitle().toString(), this.getIntRegistroId());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return super.onOptionsItemSelected(itm);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu mnu) {

    try {

      if (mnu == null) {

        return super.onCreateOptionsMenu(mnu);
      }

      this.montarMenu(mnu);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return super.onCreateOptionsMenu(mnu);
  }
}