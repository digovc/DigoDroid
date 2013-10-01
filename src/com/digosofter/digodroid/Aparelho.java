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
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			String ts = Context.TELEPHONY_SERVICE;
			TelephonyManager mTelephonyMgr = (TelephonyManager) App.getApp().getActMain().getSystemService(ts);
			_strImei = mTelephonyMgr.getDeviceId();

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro ao recuperar o EMEI do aparelho.\n" + ex.getMessage());

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
