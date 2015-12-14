package com.digosofter.digodroid.animacao;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.Objeto;

import java.util.concurrent.Callable;

public class Animar extends Objeto {

  private static final long INT_DURACAO_RAPIDO = 250;
  private static Animar i;

  public static Animar getI() {

    try {

      if (i != null) {

        return i;
      }

      i = new Animar();

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);

    } finally {
    }

    return i;
  }

  /**
   * Usa o alpha do componente, fazendo um efeito de esmaecimento para fazer o controle aparecer.
   *
   * @param viwTarget Controle que será animado.
   */
  public void aparecerFadeIn(View viwTarget) {

    try {

      if (viwTarget == null) {

        return;
      }

      viwTarget.setAlpha(0);
      viwTarget.setVisibility(View.VISIBLE);
      viwTarget.animate().alpha(1);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  /**
   * Usa o tamanho vertical do controle para fazê-lo aparecer, partindo do zero até o tamanho vertical normal.
   *
   * @param viwTarget Controle que será animado.
   */
  public void aparecerSlideDown(View viwTarget) {

    ScaleAnimation anm;

    try {

      if (viwTarget == null) {

        return;
      }

      anm = new ScaleAnimation(1, 1, 0, 1);

      anm.setDuration(INT_DURACAO_RAPIDO);
      anm.setFillAfter(true);

      viwTarget.setVisibility(View.VISIBLE);
      viwTarget.startAnimation(anm);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  /**
   * Usa o alpha do componente, fazendo um efeito de esmaecimento para fazer o controle desaparecer.
   *
   * @param viwTarget Controle que será animado.
   */
  public void desaparecerFadeOut(View viwTarget) {

    try {

      if (viwTarget == null) {

        return;
      }

      viwTarget.setAlpha(1);
      viwTarget.animate().alpha(0);
      viwTarget.setVisibility(View.INVISIBLE);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  /**
   * Move o controle para fora da tela, partindo da sua posição atual até a parte superior da tela.
   *
   * @param viwTarget            Controle que será animado.
   * @param objAnimationListener Listener que vai cuidar dos callbacks da animação.
   */
  public void desaparecerMoverCima(View viwTarget, Animation.AnimationListener objAnimationListener) {

    int intTop;
    TranslateAnimation anm;

    try {

      if (viwTarget == null) {

        return;
      }

      intTop = -(((ViewGroup.MarginLayoutParams) viwTarget.getLayoutParams()).topMargin + viwTarget.getHeight());

      anm = new TranslateAnimation(0, 0, 0, intTop);

      anm.setDuration(INT_DURACAO_RAPIDO);

      if (objAnimationListener != null) {

        anm.setAnimationListener(objAnimationListener);
      }

      viwTarget.startAnimation(anm);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  /**
   * Move o controle para fora da tela, partindo da sua posição atual até a parte superior da tela.
   *
   * @param viwTarget Controle que será animado.
   */
  public void desaparecerMoverCima(View viwTarget) {

    try {

      this.desaparecerMoverCima(viwTarget, null);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }

  /**
   * Usa o tamanho vertical do controle para fazê-lo desaparecer, partindo do zero até o tamanho vertical normal.
   *
   * @param viwTarget Controle que será animado.
   */
  public void desaparecerSlideUp(View viwTarget) {

    ScaleAnimation anm;

    try {

      if (viwTarget == null) {

        return;
      }

      anm = new ScaleAnimation(1, 1, 1, 0);

      anm.setDuration(INT_DURACAO_RAPIDO);
      anm.setFillAfter(true);

      viwTarget.startAnimation(anm);

    } catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    } finally {
    }
  }
}