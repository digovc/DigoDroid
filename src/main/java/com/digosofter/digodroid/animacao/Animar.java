package com.digosofter.digodroid.animacao;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.digosofter.digojava.Objeto;

public class Animar extends Objeto
{
  private static final long INT_DURACAO_RAPIDO = 450;
  private static Animar i;

  public static Animar getI()
  {
    if (i != null)
    {
      return i;
    }
    i = new Animar();

    return i;
  }

  /**
   * Usa o alpha do componente, fazendo um efeito de esmaecimento para fazer o controle aparecer.
   *
   * @param viwTarget Controle que será animado.
   */
  public void aparecerFadeIn(View viwTarget)
  {
    if (viwTarget == null)
    {
      return;
    }
    viwTarget.setAlpha(0);
    viwTarget.setVisibility(View.VISIBLE);
    viwTarget.animate().alpha(1);
  }

  /**
   * Usa o tamanho vertical do controle para fazê-lo aparecer, partindo do zero até o tamanho vertical normal.
   *
   * @param viwTarget Controle que será animado.
   */
  public void aparecerSlideDown(View viwTarget)
  {
    ScaleAnimation anm;

    if (viwTarget == null)
    {
      return;
    }
    anm = new ScaleAnimation(1, 1, 0, 1);
    anm.setDuration(INT_DURACAO_RAPIDO);
    viwTarget.clearAnimation();
    viwTarget.setVisibility(View.VISIBLE);
    viwTarget.startAnimation(anm);
  }

  /**
   * Usa o alpha do componente, fazendo um efeito de esmaecimento para fazer o controle desaparecer.
   *
   * @param viwTarget Controle que será animado.
   */
  public void desaparecerFadeOut(View viwTarget)
  {
    if (viwTarget == null)
    {
      return;
    }
    viwTarget.clearAnimation();
    viwTarget.setAlpha(1);
    viwTarget.animate().alpha(0);
  }

  /**
   * Move o controle para fora da tela, partindo da sua posição atual até a parte inferior da tela.
   *
   * @param viwTarget Controle que será animado.
   */
  public void desaparecerMoverBaixo(final View viwTarget)
  {
    this.desaparecerMoverBaixo(viwTarget, null);
  }

  /**
   * Move o controle para fora da tela, partindo da sua posição atual até a parte inferior da tela.
   *
   * @param viwTarget Controle que será animado.
   * @param objAnimationListener Listener que vai cuidar dos callbacks da animação.
   */
  public void desaparecerMoverBaixo(final View viwTarget, final Animation.AnimationListener objAnimationListener)
  {
    int intBottom;
    TranslateAnimation anm;

    if (viwTarget == null)
    {
      return;
    }
    intBottom = (((ViewGroup.MarginLayoutParams) viwTarget.getLayoutParams()).bottomMargin + viwTarget.getHeight());
    anm = new TranslateAnimation(0, 0, 0, intBottom);
    anm.setDuration(INT_DURACAO_RAPIDO);
    if (objAnimationListener != null)
    {
      anm.setAnimationListener(objAnimationListener);
    }
    viwTarget.clearAnimation();
    viwTarget.startAnimation(anm);
  }

  /**
   * Move o controle para fora da tela, partindo da sua posição atual até a parte superior da tela.
   *
   * @param viwTarget Controle que será animado.
   * @param objAnimationListener Listener que vai cuidar dos callbacks da animação.
   */
  public void desaparecerMoverCima(View viwTarget, Animation.AnimationListener objAnimationListener)
  {
    int intTop;
    TranslateAnimation anm;

    if (viwTarget == null)
    {
      return;
    }
    intTop = -(((ViewGroup.MarginLayoutParams) viwTarget.getLayoutParams()).topMargin + viwTarget.getHeight());
    anm = new TranslateAnimation(0, 0, 0, intTop);
    anm.setDuration(INT_DURACAO_RAPIDO);
    if (objAnimationListener != null)
    {
      anm.setAnimationListener(objAnimationListener);
    }
    viwTarget.clearAnimation();
    viwTarget.startAnimation(anm);
  }

  /**
   * Move o controle para fora da tela, partindo da sua posição atual até a parte superior da tela.
   *
   * @param viwTarget Controle que será animado.
   */
  public void desaparecerMoverCima(View viwTarget)
  {
    this.desaparecerMoverCima(viwTarget, null);
  }

  /**
   * Usa o tamanho vertical do controle para fazê-lo desaparecer, partindo do zero até o tamanho vertical normal.
   *
   * @param viwTarget Controle que será animado.
   */
  public void desaparecerSlideUp(final View viwTarget)
  {
    ScaleAnimation anm;

    if (viwTarget == null)
    {
      return;
    }
    anm = new ScaleAnimation(1, 1, 1, 0);
    anm.setDuration(INT_DURACAO_RAPIDO);
    anm.setFillAfter(true);
    anm.setAnimationListener(new Animation.AnimationListener()
    {
      @Override
      public void onAnimationEnd(final Animation animation)
      {
        viwTarget.setVisibility(View.GONE);
      }

      @Override
      public void onAnimationRepeat(final Animation animation)
      {
      }

      @Override
      public void onAnimationStart(final Animation animation)
      {
      }
    });
    viwTarget.clearAnimation();
    viwTarget.startAnimation(anm);
  }
}