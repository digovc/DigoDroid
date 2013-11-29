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
		// VARIÁVEIS

		String strTelephonyService;
		TelephonyManager objTelephonyManager;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			strTelephonyService = Context.TELEPHONY_SERVICE;
			objTelephonyManager = (TelephonyManager) App.getApp().getActMain().getSystemService(strTelephonyService);
			_strImei = objTelephonyManager.getDeviceId();

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getApp().getStrMensagemUsuarioPadrao(100), ex.getMessage());

		} finally {
		}

		return _strImei;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// MÉTODOS
	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
