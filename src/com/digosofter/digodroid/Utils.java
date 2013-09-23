package com.digosofter.digodroid;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.digosofter.digodroid.erro.Erro;

public abstract class Utils {
	// CONSTANTES

	public static enum EnmRandomTipo {
		ALPHA, ALPHANUMERICO, NUMERICO
	}

	public static final Locale objLocaleBrasil = new Locale("pt", "BR");

	public static final String STRING_VAZIA = "";

	public enum EnmDataFormato {
		DD_MM, DD_MM_YYYY, HH_MM_DD_MM_YYYY, HH_MM_SS_DD_MM_YYYY
	}

	// FIM CONSTANTES

	// ATRIBUTOS
	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// MÉTODOS

	public static String getStrAleatoria(int intTamanho, EnmRandomTipo enmRandomTipo) {

		StringBuffer buffer = new StringBuffer();
		String characters = "";

		switch (enmRandomTipo) {

		case ALPHA:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;

		case ALPHANUMERICO:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;

		case NUMERICO:
			characters = "1234567890";
			break;
		}

		int charactersLength = characters.length();

		for (int i = 0; i < intTamanho; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

	public static String getStrDataFormatada(Date objDate, EnmDataFormato enmDataFormato) {
		// VARIÁVEIS
		String strDataFormato = Utils.STRING_VAZIA;
		SimpleDateFormat objSimpleDateFormat = null;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			switch (enmDataFormato) {
			case DD_MM:
				strDataFormato = "dd/MM";
				break;
			case DD_MM_YYYY:
				strDataFormato = "dd/MM/yyyy";
				break;
			case HH_MM_DD_MM_YYYY:
				strDataFormato = "hh:mm dd/MM/yyyy";
				break;
			case HH_MM_SS_DD_MM_YYYY:
				strDataFormato = "hh:mm:ss dd/MM/yyyy";
				break;
			default:
				strDataFormato = "dd/MM/yyyy";
				break;
			}
			objSimpleDateFormat = new SimpleDateFormat(strDataFormato, objLocaleBrasil);

			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
		return objSimpleDateFormat.format(objDate);
	}

	public static String getStrPrimeiraMaiuscula(String str) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			str = str.substring(0, 1).toUpperCase() + str.substring(1);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
		return str;
	}

	public static String getStrSimplificada(String strComplexa) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			strComplexa = strComplexa.toLowerCase(Locale.ENGLISH);
			String[] arrChrAcentos = new String[] { "ç", "á", "é", "í", "ó", "ú", "ý", "à", "è", "ì", "ò", "ù", "ã",
					"õ", "ñ", "ä", "ë", "ï", "ö", "ü", "ÿ", "â", "ê", "î", "ô", "û" };
			String[] arrChrSemAcento = new String[] { "c", "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u", "a",
					"o", "n", "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u" };
			for (int intTemp = 0; intTemp < arrChrAcentos.length; intTemp++) {
				strComplexa = strComplexa.replace(arrChrAcentos[intTemp], arrChrSemAcento[intTemp]);
			}
			String[] arrChrCaracteresEspeciais = { "\\.", ",", "-", ":", "\\(", "\\)", "ª", "\\|", "\\\\", "°",
					"^\\s+", "\\s+$", "\\s+", ".", "(", ")" };
			for (int intTemp = 0; intTemp < arrChrCaracteresEspeciais.length; intTemp++) {
				strComplexa = strComplexa.replace(arrChrCaracteresEspeciais[intTemp], "");
			}
			strComplexa = strComplexa.replace(" ", "");

			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
		}
		return strComplexa;
	}

	public static String getStrValorMonetario(double monValor) {
		// VARIÁVEIS

		NumberFormat objNumberFormat = NumberFormat.getCurrencyInstance(objLocaleBrasil);

		// FIM VARIÁVEIS
		try {
			// AÇÕES
			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARIÁVEIS
			// FIM LIMPAR VARIÁVEIS
		}
		return objNumberFormat.format(monValor);
	}

	public static String removerUltimaLetra(String str) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			str = str.substring(0, str.length() - 1);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" + ex.getMessage());

		} finally {
		}
		return str;
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
