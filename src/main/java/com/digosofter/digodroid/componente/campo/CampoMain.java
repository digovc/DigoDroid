package com.digosofter.digodroid.componente.campo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.activity.OnDestroyListener;
import com.digosofter.digodroid.componente.label.LabelGeral;
import com.digosofter.digodroid.componente.painel.PainelLinha;
import com.digosofter.digodroid.database.ColunaAndroid;
import com.digosofter.digojava.OnValorAlteradoArg;
import com.digosofter.digojava.OnValorAlteradoListener;
import com.digosofter.digojava.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class CampoMain extends PainelLinha implements OnDestroyListener
{
  public static final String STR_TITULO_DESCONHECIDO = "<desconhecido>";

  private boolean _booSomenteLeitura;
  private boolean _booValor;
  private ColunaAndroid _cln;
  private double _dblValor;
  private Calendar _dttValor;
  private int _intValor;
  private LabelGeral _lblTitulo;
  private List<OnValorAlteradoListener> _lstEvtOnValorAlteradoListener;
  private String _strClnNomeSql;
  private String _strTitulo;
  private String _strValor;
  private String _strValorAnterior;

  public CampoMain(Context cnt)
  {
    super(cnt);
  }

  public CampoMain(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public CampoMain(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  public void addEvtOnValorAlteradoListener(OnValorAlteradoListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnValorAlteradoListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnValorAlteradoListener().add(evt);
  }

  public void carregarValorCln()
  {
    if (this.getCln() == null)
    {
      return;
    }

    this.getCln().setStrValor(this.getStrValor());
  }

  private void dispararEvtOnValorAlteradoListener()
  {
    if (this.getLstEvtOnValorAlteradoListener().isEmpty())
    {
      return;
    }
    if ((this.getStrValor() != null) ? (this.getStrValor().equals(this.getStrValorAnterior())) : (this.getStrValorAnterior() == null))
    {
      return;
    }

    OnValorAlteradoArg arg = new OnValorAlteradoArg();

    arg.setStrValor(this.getStrValor());
    arg.setStrValorAnterior(this.getStrValorAnterior());

    for (OnValorAlteradoListener evt : this.getLstEvtOnValorAlteradoListener())
    {
      if (evt == null)
      {
        continue;
      }

      evt.onValorAlterado(this, arg);
    }
  }

  protected boolean getBooSomenteLeitura()
  {
    return _booSomenteLeitura;
  }

  public boolean getBooValor()
  {
    _booValor = Utils.getBoo(this.getStrValor());

    return _booValor;
  }

  public ColunaAndroid getCln()
  {
    return _cln;
  }

  public double getDblValor()
  {
    if (Utils.getBooStrVazia(this.getStrValor()))
    {
      return 0;
    }

    _dblValor = Double.valueOf(this.getStrValor());

    return _dblValor;
  }

  public Calendar getDttValor()
  {
    if (Utils.getBooStrVazia(this.getStrValor()))
    {
      return null;
    }

    _dttValor = Utils.strToDtt(this.getStrValor());

    return _dttValor;
  }

  public int getIntValor()
  {
    return _intValor = (int) this.getDblValor();
  }

  protected LabelGeral getLblTitulo()
  {
    if (_lblTitulo != null)
    {
      return _lblTitulo;
    }

    _lblTitulo = new LabelGeral(this.getContext());

    return _lblTitulo;
  }

  private List<OnValorAlteradoListener> getLstEvtOnValorAlteradoListener()
  {
    if (_lstEvtOnValorAlteradoListener != null)
    {
      return _lstEvtOnValorAlteradoListener;
    }

    _lstEvtOnValorAlteradoListener = new ArrayList<>();

    return _lstEvtOnValorAlteradoListener;
  }

  public String getStrClnNomeSql()
  {
    return _strClnNomeSql;
  }

  private String getStrTitulo()
  {
    return _strTitulo;
  }

  public String getStrValor()
  {
    return _strValor;
  }

  private String getStrValorAnterior()
  {
    return _strValorAnterior;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.setOrientation(VERTICAL);
    this.getLblTitulo().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(30, this.getContext())));
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
    super.inicializar(ats);

    if (ats == null)
    {
      return;
    }

    TypedArray objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.CampoMain);

    this.setBooSomenteLeitura(objTypedArray.getBoolean(R.styleable.CampoMain_booSomenteLeitura, false));
    this.setStrClnNomeSql(objTypedArray.getString(R.styleable.CampoMain_clnSqlNome));

    objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.View);

    this.setStrTitulo(objTypedArray.getString(R.styleable.View_strTitulo));
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();

    this.addView(this.getLblTitulo());
  }

  @Override
  public void onActivityDestruir(final Object objSender)
  {
    this.getLstEvtOnValorAlteradoListener().clear();
  }

  /**
   * Faz com que este campo receba o foco da aplicação.
   */
  public abstract void receberFoco();

  public void removerEvtOnValorAlteradoListener(OnValorAlteradoListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnValorAlteradoListener().remove(evt);
  }

  protected void setBooSomenteLeitura(boolean booSomenteLeitura)
  {
    _booSomenteLeitura = booSomenteLeitura;
  }

  public void setBooValor(boolean booValor)
  {
    _booValor = booValor;

    this.setStrValor(String.valueOf(_booValor));
  }

  public void setCln(ColunaAndroid cln)
  {
    if (_cln == cln)
    {
      return;
    }

    _cln = cln;

    this.setStrTitulo(_cln.getStrNomeExibicao());

    _cln.setCmp(this);
  }

  public void setDblValor(double dblValor)
  {
    _dblValor = dblValor;

    this.setStrValor(String.valueOf(_dblValor));
  }

  public void setDttValor(Calendar dttValor)
  {
    if (_dttValor == dttValor)
    {
      return;
    }

    _dttValor = dttValor;

    this.setStrValor(Utils.getStrDataFormatada(dttValor, Utils.EnmDataFormato.DD_MM_YYYY_HH_MM_SS));
  }

  @Override
  public void setEventos()
  {
    super.setEventos();

    this.setEventosActMain();
  }

  private void setEventosActMain()
  {
    if (!(this.getContext() instanceof ActMain))
    {
      return;
    }

    ((ActMain) this.getContext()).addEvtOnDestroyListener(this);
  }

  public void setIntValor(int intValor)
  {
    _intValor = intValor;

    this.setDblValor(_intValor);
  }

  private void setStrClnNomeSql(String strClnNomeSql)
  {
    _strClnNomeSql = strClnNomeSql;

    this.setStrTitulo(_strClnNomeSql);
  }

  /**
   * Altera o texto que será apresentado ao usuário e dá nome a este campo.
   *
   * @param strTitulo Texto que será apresentado ao usuário e dá nome a este campo.
   */
  public void setStrTitulo(String strTitulo)
  {
    _strTitulo = strTitulo;

    this.getLblTitulo().setText(_strTitulo);
  }

  public void setStrValor(String strValor)
  {
    if (_strValor == strValor)
    {
      return;
    }

    this.setStrValorAnterior(_strValor);

    _strValor = strValor;

    this.dispararEvtOnValorAlteradoListener();
  }

  private void setStrValorAnterior(String strValorAnterior)
  {
    _strValorAnterior = strValorAnterior;
  }
}