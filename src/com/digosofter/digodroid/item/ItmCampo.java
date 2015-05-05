package com.digosofter.digodroid.item;

import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.DbColuna;

public class ItmCampo extends ItmMain implements OnClickListener {

  private DbColuna _cln;
  private String _strValor;
  private TextView _txtNome;
  private TextView _txtValor;

  public ItmCampo(boolean booTelaConsulta) {

    try {

      this.setBooTelaConsulta(booTelaConsulta);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void carregarDados(Cursor crs) {

    try {

      if (crs == null) {

        return;
      }

      this.setStrNome(this.getCln().getStrNomeExibicao() + ": ");
      this.setStrValor(crs.getString(crs.getColumnIndex(this.getCln().getStrNomeSql())));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public boolean getBooContemTermo(String strTermo) {

    try {

      if (Utils.getBooStrVazia(strTermo)) {

        return false;
      }

      if (Utils.getBooStrVazia(this.getStrValor())) {

        return false;
      }

      if (this.getStrValor().contains(strTermo)) {

        return true;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return false;
  }

  public DbColuna getCln() {

    return _cln;
  }

  @Override
  protected int getIntLayoutId() {

    return R.layout.itm_campo;
  }

  public String getStrValor() {

    return _strValor;
  }

  private TextView getTxtNome() {

    try {

      if (_txtNome != null) {

        return _txtNome;
      }

      _txtNome = (TextView) this.getViw().findViewById(R.id.itmCampo_txtNome);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txtNome;
  }

  private TextView getTxtValor() {

    try {

      if (_txtValor != null) {

        return _txtValor;
      }

      _txtValor = (TextView) this.getViw().findViewById(R.id.itmCampo_txtValor);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _txtValor;
  }

  @Override
  protected void limparLayout() {

    super.limparLayout();

    try {

      _txtNome = null;
      _txtValor = null;
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

      this.getTxtNome().setText(this.getStrNome());
      this.getTxtValor().setText(this.getStrValor());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onClick(View v) {

    String str;

    try {

      str = ((TextView) v.findViewById(R.id.itmCampo_txtValor)).getText().toString();

      if (Utils.getBooStrVazia(str)) {

        return;
      }

      AppAndroid.getI().mostrarNotificacao(str);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void setCln(DbColuna cln) {

    _cln = cln;
  }

  @Override
  protected void setEventos() {

    super.setEventos();

    try {

      if (this.getBooTelaConsulta()) {

        return;
      }

      this.getTxtValor().setOnClickListener(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setStrValor(String strValor) {

    try {

      _strValor = strValor;

      if (Utils.getBooStrVazia(_strValor)) {

        return;
      }

      if (this.getCln() == null) {

        return;
      }

      this.getCln().setStrValor(_strValor);

      _strValor = this.getCln().getStrValorExibicao();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}