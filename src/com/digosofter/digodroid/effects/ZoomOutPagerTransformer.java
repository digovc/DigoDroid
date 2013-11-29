package com.digosofter.digodroid.effects;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import com.digosofter.digodroid.App;
import com.digosofter.digodroid.erro.Erro;

public class ZoomOutPagerTransformer implements PageTransformer {
	// CONSTANTES

	private static float MIN_SCALE = 0.85f;
	private static float MIN_ALPHA = 0.5f;

	// FIM CONSTANTES

	// ATRIBUTOS
	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// MÉTODOS

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void transformPage(View objView, float dblPosition) {
		// VARIÁVEIS

		int intPageWidth = objView.getWidth();
		int intPageHeight = objView.getHeight();

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (dblPosition < -1) {

				if (android.os.Build.VERSION.SDK_INT >= 11) {
					objView.setAlpha(0);
				}
			} else if (dblPosition <= 1) {

				float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(dblPosition));
				float vertMargin = intPageHeight * (1 - scaleFactor) / 2;
				float horzMargin = intPageWidth * (1 - scaleFactor) / 2;
				
				if (dblPosition < 0) {
				
					if (android.os.Build.VERSION.SDK_INT >= 11) {
						objView.setTranslationX(horzMargin - vertMargin / 2);
					}
				} else {

					if (android.os.Build.VERSION.SDK_INT >= 11) {
						objView.setTranslationX(-horzMargin + vertMargin / 2);
					}
				}

				objView.setScaleX(scaleFactor);
				objView.setScaleY(scaleFactor);
				objView.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

			} else {
				objView.setAlpha(0);
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}