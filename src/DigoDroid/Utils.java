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

	// MÉTODOS

	public static String getStrSimplificada(String strComplexa) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			strComplexa = strComplexa.toLowerCase(Locale.ENGLISH);
			String[] arrChrAcentos = new String[] { "ç", "á", "é", "í", "ó",
					"ú", "ý", "à", "è", "ì", "ò", "ù", "ã", "õ", "ñ", "ä", "ë",
					"ï", "ö", "ü", "ÿ", "â", "ê", "î", "ô", "û" };
			String[] arrChrSemAcento = new String[] { "c", "a", "e", "i", "o",
					"u", "y", "a", "e", "i", "o", "u", "a", "o", "n", "a", "e",
					"i", "o", "u", "y", "a", "e", "i", "o", "u" };
			for (int intTemp = 0; intTemp < arrChrAcentos.length; intTemp++) {
				strComplexa = strComplexa.replace(arrChrAcentos[intTemp],
						arrChrSemAcento[intTemp]);
			}
			String[] arrChrCaracteresEspeciais = { "\\.", ",", "-", ":", "\\(",
					"\\)", "ª", "\\|", "\\\\", "°", "^\\s+", "\\s+$", "\\s+",
					".", "(", ")" };
			for (int intTemp = 0; intTemp < arrChrCaracteresEspeciais.length; intTemp++) {
				strComplexa = strComplexa.replace(
						arrChrCaracteresEspeciais[intTemp], "");
			}
			strComplexa = strComplexa.replace(" ", "");

			// FIM AÇÕES
		} catch (Exception e) {
		} finally {
		}
		return strComplexa;
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS
}
