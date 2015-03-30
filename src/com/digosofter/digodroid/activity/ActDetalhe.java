package com.digosofter.digodroid.activity;

import android.database.Cursor;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digodroid.item.ItmCampo;
import com.digosofter.digodroid.item.ItmConsulta;

public class ActDetalhe extends ActMain {

  public static final String EXTRA_IN_INT_REGISTRO_ID = "registro_id";

  private int _intRegistroId;
  private ItmConsulta _itmConsulta;
  private LinearLayout _pnlCampos;
  private DbTabelaAndroid _tbl;
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

  @Override
  protected void montarLayout() {

    super.montarLayout();

    try {

      this.montarLayoutTitulo();
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

      for (ItmCampo itmCampo : this.getItmConsulta().getLstItmCampo()) {

        if (itmCampo == null) {

          continue;
        }

        this.montarLayoutItem(itmCampo);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarLayoutItem(ItmCampo itmCampo) {

    try {

      if (itmCampo == null) {

        return;
      }

      itmCampo.montarLayout();

      this.getPnlCampos().addView(itmCampo.getViw());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void montarLayoutTitulo() {

    try {

      this.getTxtNome().setText(this.getItmConsulta().getStrNome());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}