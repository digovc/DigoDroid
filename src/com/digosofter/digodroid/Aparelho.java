package com.digosofter.digodroid;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.digosofter.digodroid.erro.Erro;

public abstract class Aparelho extends Objeto {
	// CONSTANTES
	// FIM CONSTANTES

	// ATRIBUTOS

	private static String _strImei;

	public static String getStrImei() {
		// VARI�VEIS

		String strTelephonyService;
		TelephonyManager objTelephonyManager;

		// FIM VARI�VEIS
		try {
			// A��ES

			strTelephonyService = Context.TELEPHONY_SERVICE;
			objTelephonyManager = (TelephonyManager) App.getApp().getActMain().getSystemService(strTelephonyService);
			_strImei = objTelephonyManager.getDeviceId();

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrMensagemUsuarioPadrao(100), ex.getMessage());

		} finally {
		}

		return _strImei;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// M�TODOS
	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
