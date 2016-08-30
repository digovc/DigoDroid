package com.digosofter.digodroid.controle.painel;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.animacao.Animar;
import com.digosofter.digodroid.controle.imagem.ImagemGeral;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digojava.Utils;

public class PainelGrupo extends PainelGeral implements View.OnClickListener
{
  private boolean _booAberto = true;
  private ImagemGeral _imgSeta;
  private LabelGeral _lblTitulo;
  private PainelGeralRelativo _pnlCabecalho;
  private PainelGeral _pnlConteudo;
  private String _strTitulo = "<desconhecido>";

  public PainelGrupo(Context cnt)
  {
    super(cnt);
  }

  public PainelGrupo(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public PainelGrupo(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  private void abrirFecharDados(boolean booAbrir)
  {
    if (booAbrir)
    {
      Animar.getI().aparecerSlideDown(this.getPnlConteudo());
    }
    else
    {
      Animar.getI().desaparecerSlideUp(this.getPnlConteudo());
    }

    this.getImgSeta().animate().rotationX(booAbrir ? 0 : 180);

    this.setBooAberto(booAbrir);
  }

  private void atualizarStrTitulo()
  {
    this.getLblTitulo().setText((!Utils.getBooStrVazia(_strTitulo)) ? _strTitulo : "<desconhecido>");
  }

  @Override
  public void finalizar()
  {
    super.finalizar();

    while (this.getChildCount() > 2)
    {
      View viw = this.getChildAt(2);

      this.removeView(viw);
      this.getPnlConteudo().addView(viw);
    }
  }

  private boolean getBooAberto()
  {
    return _booAberto;
  }

  private ImagemGeral getImgSeta()
  {
    if (_imgSeta != null)
    {
      return _imgSeta;
    }

    _imgSeta = new ImagemGeral(this.getContext());

    return _imgSeta;
  }

  private LabelGeral getLblTitulo()
  {
    if (_lblTitulo != null)
    {
      return _lblTitulo;
    }

    _lblTitulo = new LabelGeral(this.getContext());

    return _lblTitulo;
  }

  private PainelGeralRelativo getPnlCabecalho()
  {
    if (_pnlCabecalho != null)
    {
      return _pnlCabecalho;
    }

    _pnlCabecalho = new PainelGeralRelativo(this.getContext());

    return _pnlCabecalho;
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

  public String getStrTitulo()
  {
    return _strTitulo;
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  @Override
  public void inicializar()
  {
    super.inicializar();

    this.setBackground(this.getResources().getDrawable(R.drawable.bkg_borda));
    this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    this.setOrientation(VERTICAL);
    this.inicializarImgSeta();
    this.inicializarLblTitulo();
    this.inicializarPnlCabecalho();
    this.inicializarPnlConteudo();
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
    super.inicializar(ats);

    if (ats == null)
    {
      return;
    }

    TypedArray objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.View);

    this.setStrTitulo(objTypedArray.getString(R.styleable.View_strTitulo));
  }

  private void inicializarImgSeta()
  {
    int intTamanho = UtilsAndroid.dpToPx(25, this.getContext());

    RelativeLayout.LayoutParams objLayoutParams = new RelativeLayout.LayoutParams(intTamanho, LayoutParams.MATCH_PARENT);

    objLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    objLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

    this.getImgSeta().setImageResource(R.drawable.abrir_fechar_grupo);
    this.getImgSeta().setLayoutParams(objLayoutParams);
    this.getImgSeta().setRight(0);
  }

  private void inicializarLblTitulo()
  {
    this.getLblTitulo().setGravity(Gravity.CENTER_VERTICAL);
    this.getLblTitulo().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
  }

  private void inicializarPnlCabecalho()
  {
    int intPadding = UtilsAndroid.dpToPx(TemaDefault.getI().getIntEspacamento(), this.getContext());

    this.getPnlCabecalho().setBackgroundColor(this.getContext().getResources().getColor(R.color.cor_borda));
    this.getPnlCabecalho().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(40, this.getContext())));
    this.getPnlCabecalho().setPadding(intPadding, 0, intPadding, 0);
  }

  private void inicializarPnlConteudo()
  {
    this.getPnlConteudo().setOrientation(VERTICAL);
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();

    super.addView(this.getPnlCabecalho());
    super.addView(this.getPnlConteudo());

    this.getPnlCabecalho().addView(this.getLblTitulo());
    this.getPnlCabecalho().addView(this.getImgSeta());
  }

  @Override
  public void onClick(final View v)
  {
    this.abrirFecharDados(!this.getBooAberto());
  }

  private void setBooAberto(boolean booAberto)
  {
    _booAberto = booAberto;
  }

  @Override
  public void setEventos()
  {
    super.setEventos();

    this.getPnlCabecalho().setOnClickListener(this);
  }

  public void setStrTitulo(String strTitulo)
  {
    _strTitulo = strTitulo;

    this.atualizarStrTitulo();
  }
}