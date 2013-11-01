package com.digosofter.digodroid.effects;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class ZoomOutPagerTransformer implements PageTransformer {
	// CONSTANTES

	private static float MIN_SCALE = 0.85f;
	private static float MIN_ALPHA = 0.5f;

	// FIM CONSTANTES

	// ATRIBUTOS
	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// M�TODOS

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		int pageHeight = view.getHeight();

		if (position < -1) { // [-Infinity,-1)
			// This page is way off-screen to the left.
			if (android.os.Build.VERSION.SDK_INT >= 11) {
				view.setAlpha(0);
			}
		} else if (position <= 1) { // [-1,1]
			// Modify the default slide transition to shrink the page as well
			float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
			float vertMargin = pageHeight * (1 - scaleFactor) / 2;
			float horzMargin = pageWidth * (1 - scaleFactor) / 2;
			if (position < 0) {
				if (android.os.Build.VERSION.SDK_INT >= 11) {
					view.setTranslationX(horzMargin - vertMargin / 2);
				}
			} else {
				if (android.os.Build.VERSION.SDK_INT >= 11) {
					view.setTranslationX(-horzMargin + vertMargin / 2);
				}
			}

			// Scale the page down (between MIN_SCALE and 1)
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);

			// Fade the page relative to its size.
			view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

		} else { // (1,+Infinity]
			// This page is way off-screen to the right.
			view.setAlpha(0);
		}
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}