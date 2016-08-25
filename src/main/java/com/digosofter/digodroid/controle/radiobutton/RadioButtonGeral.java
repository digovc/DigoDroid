package com.digosofter.digodroid.controle.radiobutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.digosofter.digodroid.controle.IControleMain;
import com.digosofter.digodroid.controle.campo.CampoMain;
import com.digosofter.digojava.Utils;

public class RadioButtonGeral extends RadioButton implements IControleMain
{
  private String _strTitulo;

  public RadioButtonGeral(Context context)
  {
    super(context);

    this.iniciar(null);
  }

  public RadioButtonGeral(Context context, AttributeSet attrs)
  {
    super(context, attrs);

    this.iniciar(attrs);
  }

  public RadioButtonGeral(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);

    this.iniciar(attrs);
  }

  private void atualizarStrTitulo()
  {
    this.setText((!Utils.getBooStrVazia(this.getStrTitulo())) ? this.getStrTitulo() : CampoMain.STR_TITULO_DESCONHECIDO);
  }

  @Override
  public void finalizar()
  {
  }

  private String getStrTitulo()
  {
    return _strTitulo;
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
  }

  @Override
  public void inicializar()
  {
    this.setStrTitulo(CampoMain.STR_TITULO_DESCONHECIDO);
    this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
  }

  @Override
  public void iniciar(AttributeSet ats)
  {
    this.inicializar();
    this.inicializar(ats);
    this.montarLayout();
    this.setEventos();
  }

  @Override
  public void montarLayout()
  {
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
  {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    this.finalizar();
  }

  @Override
  public void setEventos()
  {
  }

  /**
   * Indica o título deste controle.
   *
   * @param strTitulo Título deste controle.
   */
  public void setStrTitulo(String strTitulo)
  {
    _strTitulo = strTitulo;
    this.atualizarStrTitulo();
  }
}