package com.digosofter.digodroid.controle.painel;

import android.content.Context;
import android.content.res.TypedArray;
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
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Utils;

public class PainelGrupo extends PainelGeral implements View.OnClickListener
{

  private boolean _booAberto = true;
  private ImagemGeral _imgSeta;
  private LabelGeral _lblTitulo;
  private PainelGeralRelativo _pnlCabecalho;
  private PainelGeral _pnlConteudo;
  private String _strTitulo = "<desconhecido>";

  public PainelGrupo(Context context)
  {
    super(context);
  }

  public PainelGrupo(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public PainelGrupo(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  private void abrirFecharDados(boolean booAbrir)
  {
    try
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
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void atualizarStrTitulo()
  {
    try
    {
      this.getLblTitulo().setText((!Utils.getBooStrVazia(_strTitulo)) ? _strTitulo : "<desconhecido>");
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void finalizar()
  {
    super.finalizar();
    View viw;
    try
    {
      while (this.getChildCount() > 2)
      {
        viw = this.getChildAt(2);
        this.removeView(viw);
        this.getPnlConteudo().addView(viw);
      }
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private boolean getBooAberto()
  {
    return _booAberto;
  }

  private ImagemGeral getImgSeta()
  {
    try
    {
      if (_imgSeta != null)
      {
        return _imgSeta;
      }
      _imgSeta = new ImagemGeral(this.getContext());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _imgSeta;
  }

  private LabelGeral getLblTitulo()
  {
    try
    {
      if (_lblTitulo != null)
      {
        return _lblTitulo;
      }
      _lblTitulo = new LabelGeral(this.getContext());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _lblTitulo;
  }

  private PainelGeralRelativo getPnlCabecalho()
  {
    try
    {
      if (_pnlCabecalho != null)
      {
        return _pnlCabecalho;
      }
      _pnlCabecalho = new PainelGeralRelativo(this.getContext());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _pnlCabecalho;
  }

  private PainelGeral getPnlConteudo()
  {
    try
    {
      if (_pnlConteudo != null)
      {
        return _pnlConteudo;
      }
      _pnlConteudo = new PainelGeral(this.getContext());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
    return _pnlConteudo;
  }

  public String getStrTitulo()
  {
    return _strTitulo;
  }

  @Override
  public void inicializar()
  {
    super.inicializar();
    try
    {
      this.setBackground(this.getResources().getDrawable(R.drawable.bkg_borda));
      this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
      this.setOrientation(VERTICAL);
      this.inicializarImgSeta();
      this.inicializarLblTitulo();
      this.inicializarPnlCabecalho();
      this.inicializarPnlConteudo();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
    super.inicializar(ats);
    TypedArray objTypedArray;
    try
    {
      if (ats == null)
      {
        return;
      }
      objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.View);
      this.setStrTitulo(objTypedArray.getString(R.styleable.View_strTitulo));
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarImgSeta()
  {
    int intTamanho;
    RelativeLayout.LayoutParams objLayoutParams;
    try
    {
      intTamanho = UtilsAndroid.dpToPx(25, this.getContext());
      objLayoutParams = new RelativeLayout.LayoutParams(intTamanho, LayoutParams.MATCH_PARENT);
      objLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
      objLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
      this.getImgSeta().setImageResource(R.drawable.abrir_fechar_grupo);
      this.getImgSeta().setLayoutParams(objLayoutParams);
      this.getImgSeta().setRight(0);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarLblTitulo()
  {
    try
    {
      this.getLblTitulo().setGravity(Gravity.CENTER_VERTICAL);
      this.getLblTitulo().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarPnlCabecalho()
  {
    int intPadding;
    try
    {
      intPadding = UtilsAndroid.dpToPx(TemaDefault.getI().getIntEspacamento(), this.getContext());
      this.getPnlCabecalho().setBackgroundColor(this.getContext().getResources().getColor(R.color.cor_borda));
      this.getPnlCabecalho().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UtilsAndroid.dpToPx(40, this.getContext())));
      this.getPnlCabecalho().setPadding(intPadding, 0, intPadding, 0);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void inicializarPnlConteudo()
  {
    try
    {
      this.getPnlConteudo().setOrientation(VERTICAL);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();
    try
    {
      super.addView(this.getPnlCabecalho());
      super.addView(this.getPnlConteudo());
      this.getPnlCabecalho().addView(this.getLblTitulo());
      this.getPnlCabecalho().addView(this.getImgSeta());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  @Override
  public void onClick(final View v)
  {
    try
    {
      this.abrirFecharDados(!this.getBooAberto());
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  private void setBooAberto(boolean booAberto)
  {
    _booAberto = booAberto;
  }

  @Override
  public void setEventos()
  {
    super.setEventos();
    try
    {
      this.getPnlCabecalho().setOnClickListener(this);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }

  public void setStrTitulo(String strTitulo)
  {
    try
    {
      _strTitulo = strTitulo;
      this.atualizarStrTitulo();
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally
    {
    }
  }
}