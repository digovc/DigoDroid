package DigoDroid;

import java.util.Locale;

public abstract class Utils {
	// CONSTANTES

	public static final String STRING_VAZIA = "";

	// FIM CONSTANTES

	// ATRIBUTOS
	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// M�TODOS

	public static String getStrSimplificada(String strComplexa) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			strComplexa = strComplexa.toLowerCase(Locale.ENGLISH);
			String[] arrChrAcentos = new String[] { "�", "�", "�", "�", "�",
					"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
					"�", "�", "�", "�", "�", "�", "�", "�", "�" };
			String[] arrChrSemAcento = new String[] { "c", "a", "e", "i", "o",
					"u", "y", "a", "e", "i", "o", "u", "a", "o", "n", "a", "e",
					"i", "o", "u", "y", "a", "e", "i", "o", "u" };
			for (int intTemp = 0; intTemp < arrChrAcentos.length; intTemp++) {
				strComplexa = strComplexa.replace(arrChrAcentos[intTemp],
						arrChrSemAcento[intTemp]);
			}
			String[] arrChrCaracteresEspeciais = { "\\.", ",", "-", ":", "\\(",
					"\\)", "�", "\\|", "\\\\", "�", "^\\s+", "\\s+$", "\\s+",
					".", "(", ")" };
			for (int intTemp = 0; intTemp < arrChrCaracteresEspeciais.length; intTemp++) {
				strComplexa = strComplexa.replace(
						arrChrCaracteresEspeciais[intTemp], "");
			}
			strComplexa = strComplexa.replace(" ", "");

			// FIM A��ES
		} catch (Exception e) {
		} finally {
		}
		return strComplexa;
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
