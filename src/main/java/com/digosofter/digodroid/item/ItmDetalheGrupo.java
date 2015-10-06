package com.digosofter.digodroid.item;

import java.util.ArrayList;
import java.util.List;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.erro.ErroAndroid;

public class ItmDetalheGrupo extends ItmMain {

  private List<ItmCampo> _lstItmCampo;
  private LinearLayout _pnlCampos;
  private TextView _txtGrupoNome;

  @Override
  protected int getIntLayoutId() {

    return R.layout.itm_detalhe_grupo;
  }

  public List<ItmCampo> getLstItmCampo() {

    try {

      if (_lstItmCampo != null) {

        return _lstItmCampo;
      }

      _lstItmCampo = new ArrayList<ItmCampo>();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lstItmCampo;
  }

  private LinearLayout getPnlCampos() {

    try {

      if (_pnlCampos != null) {

        return _pnlCampos;
      }

      _pnlCampos = (LinearLayout) this.getViw().findViewById(R.id.itmDetalheGrupo_pnlCampos);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _pnlCampos;
  }

  private TextView getTxtGrupoNome() {

    try {

      if (_txtGrupoNome != null) {

        return _txtGrupoNome;
      }

      _txtGrupoNome = (TextView) this.getViw().findViewById(R.id.itmDetalheGrupo_txtGrupoNome);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txtGrupoNome;
  }

  @Override
  public void montarLayout() {

    super.montarLayout();

    try {

      this.getTxtGrupoNome().setText(this.getStrNome());

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

      for (ItmCampo itmCampo : this.getLstItmCampo()) {

        if (itmCampo == null) {

          continue;
        }

        if (itmCampo.getCln() == null) {

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

      if (itmCampo.getCln() == null) {

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
}