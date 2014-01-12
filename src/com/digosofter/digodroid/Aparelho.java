package com.digosofter.digodroid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
			objTelephonyManager = (TelephonyManager) App.getI().getActMain().getSystemService(strTelephonyService);
			_strImei = objTelephonyManager.getDeviceId();

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrMensagemUsuarioPadrao(100), ex.getMessage());

		} finally {
		}

		return _strImei;
	}

	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// M�TODOS

	public static void abrirMapa(String strEnderecoCompleto) {
		// VARI�VEIS

		Intent objIntent;
		String strMap = "http://maps.google.co.in/maps?q=";

		// FIM VARI�VEIS
		try {
			// A��ES

			strMap += strEnderecoCompleto;

			objIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strMap));
			objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			App.getI().getContext().getApplicationContext().getApplicationContext().startActivity(objIntent);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	/**
	 * Abre o aplicativo padr�o para envio de email.
	 * 
	 */
	public static void enviarEmail(String strEmail) {
		// VARI�VEIS

		Uri uri;
		Intent objIntent;

		// FIM VARI�VEIS
		try {
			// A��ES

			if (Utils.getBooIsEmptyNull(strEmail)) {
				return;
			}

			uri = Uri.parse("mailto:" + strEmail);
			objIntent = new Intent(Intent.ACTION_SENDTO, uri);
			objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			objIntent.putExtra(Intent.EXTRA_SUBJECT, "Customer comments/questions");
			App.getI().getContext().startActivity(objIntent);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	public static boolean getBooConectado() {
		// VARI�VEIS

		boolean booConectado = false;
		ConnectivityManager objConnectivityManager;
		NetworkInfo objNetworkInfo;

		// FIM VARI�VEIS
		try {
			// A��ES

			objConnectivityManager = (ConnectivityManager) App.getI().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
			objNetworkInfo = objConnectivityManager.getActiveNetworkInfo();

			if (objNetworkInfo == null) {
				booConectado = false;
			} else {
				booConectado = true;
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return booConectado;
	}

	public static void ligarNumero(String strNumero) {
		// VARI�VEIS

		Intent objIntent;

		// FIM VARI�VEIS
		try {
			// A��ES

			objIntent = new Intent(Intent.ACTION_CALL);
			objIntent.setData(Uri.parse("tel:" + strNumero));
			objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			App.getI().getContext().getApplicationContext().getApplicationContext().startActivity(objIntent);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
