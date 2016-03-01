package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.activity.OnActivityDestruirListener;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.controle.painel.PainelLinha;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.OnValorAlteradoArg;
import com.digosofter.digojava.OnValorAlteradoListener;
import com.digosofter.digojava.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class CampoMain extends PainelLinha implements OnActivityDestruirListener, OnValorAlteradoListener {

  public static final String STR_TITULO_DESCONHECIDO = "<desconhecido>";

  private boolean _booSomenteLeitura;
  private boolean _booValor;
  private ColunaAndroid _cln;
  private double _dblValor;
  private int _intValor;
  private LabelGeral _lblTitulo;
  private List<OnValorAlteradoListener> _lstEvtOnValorAlteradoListener;
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

  public void addEvtOnValorAlteradoListener(OnValorAlteradoListener evt) {

    try {

      if (evt == null) {

        return;
      }

      if (this.getLstEvtOnValorAlteradoListener().contains(evt)) {

        return;
      }

      this.getLstEvtOnValorAlteradoListener().add(evt);
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
      this.addEvtOnValorAlteradoListener(this.getCln());
      this.getCln().addEvtOnValorAlteradoListener(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void atualizarStrValor() {

    try {

      this.dispararEvtOnValorAlteradoListener();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void dispararEvtOnValorAlteradoListener() {

    OnValorAlteradoArg arg;

    try {

      if (this.getLstEvtOnValorAlteradoListener().isEmpty()) {

        return;
      }

      if ((this.getStrValor() != null) ? (this.getStrValor().equals(this.getStrValorAnterior())) : (this.getStrValorAnterior() == null)) {

        return;
      }

      arg = new OnValorAlteradoArg();

      arg.setStrValor(this.getStrValor());
      arg.setStrValorAnterior(this.getStrValorAnterior());

      for (OnValorAlteradoListener evt : this.getLstEvtOnValorAlteradoListener()) {

        if (evt == null) {

          continue;
        }

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

  private List<OnValorAlteradoListener> getLstEvtOnValorAlteradoListener() {

    try {

      if (_lstEvtOnValorAlteradoListener != null) {

        return _lstEvtOnValorAlteradoListener;
      }

      _lstEvtOnValorAlteradoListener = new ArrayList<>();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lstEvtOnValorAlteradoListener;
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

    this.getLstEvtOnValorAlteradoListener().clear();
  }

  @Override
  public void onValorAlterado(final Object objSender, final OnValorAlteradoArg arg) {

    try {

      if (arg == null) {

        return;
      }

      if ((arg.getStrValor() != null) ? (arg.getStrValor().equals(arg.getStrValorAnterior())) : arg.getStrValorAnterior() == null) {

        return;
      }

      if (objSender.equals(this.getCln())) {

        this.setStrValor(this.getCln().getStrValor());
        return;
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Faz com que este campo receba o foco da aplicação.
   */
  public abstract void receberFoco();

  public void removerEvtOnValorAlteradoListener(OnValorAlteradoListener evt) {

    try {

      if (evt == null) {

        return;
      }

      this.getLstEvtOnValorAlteradoListener().remove(evt);
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