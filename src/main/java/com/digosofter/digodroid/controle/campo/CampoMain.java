package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.digosofter.digodroid.OnValorAlterado;
import com.digosofter.digodroid.OnValorAlteradoArg;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.activity.OnActivityDestruirListener;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.controle.painel.PainelLinha;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class CampoMain extends PainelLinha implements OnActivityDestruirListener {

  public static final String STR_TITULO_DESCONHECIDO = "<desconhecido>";

  private boolean _booSomenteLeitura;
  private boolean _booValor;
  private ColunaAndroid _cln;
  private double _dblValor;
  private int _intValor;
  private LabelGeral _lblTitulo;
  private List<OnValorAlterado> _lstEvtOnValorAlterado;
  private String _strClnNomeSql;
  private String _strTitulo;
  private String _strValor;
  private String _strValorAnterior;

  public CampoMain(Context context) {

    super(context);
  }

  public CampoMain(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public CampoMain(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  public void addEvtOnValorAlterado(OnValorAlterado evt) {

    try {

      if (evt == null) {

        return;
      }

      if (this.getLstEvtOnValorAlterado().contains(evt)) {

        return;
      }

      this.getLstEvtOnValorAlterado().add(evt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void atualizarCln() {

    try {

      if (this.getCln() == null) {

        return;
      }

      this.setStrTitulo(this.getCln().getStrNomeExibicao());
      this.addEvtOnValorAlterado(this.getCln());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void atualizarStrValor() {

    try {

      if (!Utils.getBooStrVazia(this.getStrValor()) && this.getStrValor().equals(this.getStrValorAnterior())) {

        return;
      }

      if (!Utils.getBooStrVazia(this.getStrValorAnterior()) && this.getStrValorAnterior().equals(this.getStrValor())) {

        return;
      }

      this.dispararOnValorAlterado();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void dispararOnValorAlterado() {

    OnValorAlteradoArg arg;

    try {

      if (this.getLstEvtOnValorAlterado().isEmpty()) {

        return;
      }

      arg = new OnValorAlteradoArg();

      arg.setStrValor(this.getStrValor());
      arg.setStrValorAnterior(this.getStrValorAnterior());

      for (OnValorAlterado evt : this.getLstEvtOnValorAlterado()) {

        evt.onValorAlterado(this, arg);
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected boolean getBooSomenteLeitura() {

    return _booSomenteLeitura;
  }

  public boolean getBooValor() {

    try {

      _booValor = Boolean.valueOf(this.getStrValor());
    }
    catch (Exception ex) {

      _booValor = false;
    }
    finally {
    }

    return _booValor;
  }

  protected ColunaAndroid getCln() {

    return _cln;
  }

  public double getDblValor() {

    try {

      _dblValor = Double.valueOf(this.getStrValor());
    }
    catch (Exception ex) {

      _dblValor = 0;
    }
    finally {
    }

    return _dblValor;
  }

  public int getIntValor() {

    return _intValor = (int) this.getDblValor();
  }

  private LabelGeral getLblTitulo() {

    try {

      if (_lblTitulo != null) {

        return _lblTitulo;
      }

      _lblTitulo = new LabelGeral(this.getContext());

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lblTitulo;
  }

  private List<OnValorAlterado> getLstEvtOnValorAlterado() {

    try {

      if (_lstEvtOnValorAlterado != null) {

        return _lstEvtOnValorAlterado;
      }

      _lstEvtOnValorAlterado = new ArrayList<>();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lstEvtOnValorAlterado;
  }

  public String getStrClnNomeSql() {

    return _strClnNomeSql;
  }

  private String getStrTitulo() {

    return _strTitulo;
  }

  public String getStrValor() {

    return _strValor;
  }

  private String getStrValorAnterior() {

    return _strValorAnterior;
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.setOrientation(VERTICAL);

      this.getLblTitulo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(30, this.getContext())));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void inicializar(AttributeSet ats) {

    super.inicializar(ats);

    TypedArray objTypedArray;

    try {

      if (ats == null) {

        return;
      }

      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.CampoMain);
      this.setStrClnNomeSql(objTypedArray.getString(R.styleable.CampoMain_clnStrNomeSql));

      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.View);
      this.setStrTitulo(objTypedArray.getString(R.styleable.View_strTitulo));
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

      this.addView(this.getLblTitulo());

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onActivityDestruir(final Object objSender) {

    this.getLstEvtOnValorAlterado().clear();
  }

  /**
   * Faz com que este campo receba o foco da aplicação.
   */
  public abstract void receberFoco();

  public void removerEvtOnValorAlterado(OnValorAlterado evt) {

    try {

      if (evt == null) {

        return;
      }

      this.getLstEvtOnValorAlterado().remove(evt);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void setBooSomenteLeitura(boolean booSomenteLeitura) {

    _booSomenteLeitura = booSomenteLeitura;
  }

  public void setBooValor(boolean booValor) {

    try {

      _booValor = booValor;

      this.setStrValor(String.valueOf(_booValor));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void setCln(ColunaAndroid cln) {

    try {

      _cln = cln;

      this.atualizarCln();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void setDblValor(double dblValor) {

    try {

      _dblValor = dblValor;

      this.setStrValor(String.valueOf(_dblValor));
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

      ((ActMain) this.getContext()).addEvtOnDestruirListener(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void setIntValor(int intValor) {

    try {

      _intValor = intValor;

      this.setDblValor(_intValor);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setStrClnNomeSql(String strClnNomeSql) {

    try {

      _strClnNomeSql = strClnNomeSql;

      this.setStrTitulo(_strClnNomeSql);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Altera o texto que será apresentado ao usuário e dá nome a este campo.
   *
   * @param strTitulo Texto que será apresentado ao usuário e dá nome a este campo.
   */
  public void setStrTitulo(String strTitulo) {

    try {

      if (Utils.getBooStrVazia(strTitulo)) {

        return;
      }

      _strTitulo = strTitulo;

      this.getLblTitulo().setText(_strTitulo);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void setStrValor(String strValor) {

    try {

      this.setStrValorAnterior(_strValor);

      _strValor = strValor;

      this.atualizarStrValor();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setStrValorAnterior(String strValorAnterior) {

    _strValorAnterior = strValorAnterior;
  }
}