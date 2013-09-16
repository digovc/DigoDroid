package DigoDroid;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class Utils {
	// CONSTANTES
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

	// M�TODOS

	public static String getStrDataFormatada(Date objDate, EnmDataFormato enmDataFormato) {
		// VARI�VEIS
		String strDataFormato = Utils.STRING_VAZIA;
		SimpleDateFormat objSimpleDateFormat = null;

		// FIM VARI�VEIS
		try {
			// A��ES

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

			// FIM A��ES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
		return objSimpleDateFormat.format(objDate);
	}

	public static String getStrSimplificada(String strComplexa) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			strComplexa = strComplexa.toLowerCase(Locale.ENGLISH);
			String[] arrChrAcentos = new String[] { "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�" };
			String[] arrChrSemAcento = new String[] { "c", "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u", "a", "o", "n", "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u" };
			for (int intTemp = 0; intTemp < arrChrAcentos.length; intTemp++) {
				strComplexa = strComplexa.replace(arrChrAcentos[intTemp], arrChrSemAcento[intTemp]);
			}
			String[] arrChrCaracteresEspeciais = { "\\.", ",", "-", ":", "\\(", "\\)", "�", "\\|", "\\\\", "�", "^\\s+", "\\s+$", "\\s+", ".", "(", ")" };
			for (int intTemp = 0; intTemp < arrChrCaracteresEspeciais.length; intTemp++) {
				strComplexa = strComplexa.replace(arrChrCaracteresEspeciais[intTemp], "");
			}
			strComplexa = strComplexa.replace(" ", "");

			// FIM A��ES
		} catch (Exception e) {
		} finally {
		}
		return strComplexa;
	}

	public static String getStrValorMonetario(double monValor) {
		// VARI�VEIS

		NumberFormat objNumberFormat = NumberFormat.getCurrencyInstance(objLocaleBrasil);

		// FIM VARI�VEIS
		try {
			// A��ES
			// FIM A��ES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
		return objNumberFormat.format(monValor);
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
