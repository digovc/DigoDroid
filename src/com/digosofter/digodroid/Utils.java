package com.digosofter.digodroid;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.digosofter.digodroid.erro.Erro;

public abstract class Utils {
	// CONSTANTES

	public enum EnmDataFormato {
		DD_MM, DD_MM_YY, DD_MM_YYYY, DD_MM_YYYY_HH_MM, DD_MM_YYYY_HH_MM_SS, HH_MM_DD_MM_YYYY, HH_MM_SS_DD_MM_YYYY, YYYY_MM_DD_HH_MM_SS
	}

	public static enum EnmRandomTipo {
		ALPHA, ALPHANUMERICO, NUMERICO
	}

	public static final Locale LOCAL_BRASIL = new Locale("pt", "BR");

	public static final String STRING_VAZIA = "";

	// FIM CONSTANTES

	// ATRIBUTOS
	// FIM ATRIBUTOS

	// CONSTRUTORES
	// FIM CONSTRUTORES

	// M�TODOS

	public static double arredondar(double dblValor, int intQtdCasas, int ceilOrFloor) {
		// VARI�VEIS

		double dblValorArredondado = dblValor;

		// FIM VARI�VEIS
		try {
			// A��ES

			dblValorArredondado *= (Math.pow(10, intQtdCasas));
			if (ceilOrFloor == 0) {
				dblValorArredondado = Math.ceil(dblValorArredondado);
			} else {
				dblValorArredondado = Math.floor(dblValorArredondado);
			}
			dblValorArredondado /= (Math.pow(10, intQtdCasas));

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(109), ex.getMessage());

		} finally {
		}
		return dblValorArredondado;
	}

	public static String enmDataFormatoToString(EnmDataFormato enmDataFormato) {
		// VARI�VEIS

		String strDataFormatoResultado = Utils.STRING_VAZIA;

		// FIM VARI�VEIS
		try {
			// A��ES

			switch (enmDataFormato) {
			case DD_MM:
				strDataFormatoResultado = "dd-MM";
				break;
			case DD_MM_YY:
				strDataFormatoResultado = "dd-MM-yy";
				break;
			case DD_MM_YYYY:
				strDataFormatoResultado = "dd-MM-yyyy";
				break;
			case DD_MM_YYYY_HH_MM:
				strDataFormatoResultado = "dd-MM-yyyy HH:mm";
				break;
			case DD_MM_YYYY_HH_MM_SS:
				strDataFormatoResultado = "dd-MM-yyyy HH:mm:ss";
				break;
			case HH_MM_DD_MM_YYYY:
				strDataFormatoResultado = "HH:mm dd-MM-yyyy";
				break;
			case HH_MM_SS_DD_MM_YYYY:
				strDataFormatoResultado = "HH:mm:ss dd-MM-yyyy";
				break;
			case YYYY_MM_DD_HH_MM_SS:
				strDataFormatoResultado = "yyyy-MM-dd HH:mm:ss";
				break;
			default:
				strDataFormatoResultado = "dd-MM-yyyy";
				break;
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
		return strDataFormatoResultado;
	}

	public static boolean getBooIsEmptyNull(String str) {
		// VARI�VEIS

		boolean booBooIsEmptyNullResultado = true;

		// FIM VARI�VEIS
		try {
			// A��ES

			if (str != null && !str.isEmpty()) {
				booBooIsEmptyNullResultado = false;
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return booBooIsEmptyNullResultado;
	}

	public static Date getDttAgora() {
		// VARI�VEIS

		Date dttResultado = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			dttResultado = new GregorianCalendar().getTime();

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(110), ex.getMessage());

		} finally {
		}
		return dttResultado;
	}

	public static int getIntCorAleatoria() {
		// VARI�VEIS

		int intColorResultado = 0;
		Random objRandom = new Random();

		// FIM VARI�VEIS
		try {
			// A��ES

			intColorResultado = Color.argb(255, objRandom.nextInt(256), objRandom.nextInt(256), objRandom.nextInt(256));

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(110), ex.getMessage());

		} finally {
		}
		return intColorResultado;
	}

	public static String getStrAleatoria(int intTamanho, EnmRandomTipo enmRandomTipo) {
		// VARI�VEIS

		int intCharactersLength;

		StringBuffer strBuffer = new StringBuffer();
		String strCharacters = "";

		// FIM VARI�VEIS
		try {
			// A��ES

			switch (enmRandomTipo) {

			case ALPHA:
				strCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
				break;
			case ALPHANUMERICO:
				strCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
				break;
			case NUMERICO:
				strCharacters = "1234567890";
				break;
			}

			intCharactersLength = strCharacters.length();

			for (int i = 0; i < intTamanho; i++) {
				double index = Math.random() * intCharactersLength;
				strBuffer.append(strCharacters.charAt((int) index));
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(111), ex.getMessage());

		} finally {
		}

		return strBuffer.toString();
	}

	public static String getStrDataFormatada(Date objDate, EnmDataFormato enmDataFormato) {
		// VARI�VEIS

		String strDataFormato = Utils.STRING_VAZIA;
		SimpleDateFormat objSimpleDateFormat = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			strDataFormato = Utils.enmDataFormatoToString(enmDataFormato);
			objSimpleDateFormat = new SimpleDateFormat(strDataFormato, LOCAL_BRASIL);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}

		return objSimpleDateFormat.format(objDate);
	}

	public static String getStrMd5(String str) {
		// VARI�VEIS

		MessageDigest objMessageDigest = null;
		String strMd5Resultado = Utils.STRING_VAZIA;

		// FIM VARI�VEIS
		try {
			// A��ES

			objMessageDigest = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, objMessageDigest.digest(str.getBytes()));
			strMd5Resultado = hash.toString(16).toUpperCase(Utils.LOCAL_BRASIL);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(112), ex.getMessage());

		} finally {
		}
		return strMd5Resultado;
	}

	public static String getStrPrimeiraMaiuscula(String str) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			str = str.substring(0, 1).toUpperCase(LOCAL_BRASIL) + str.substring(1);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
		return str;
	}

	public static String getStrSimplificada(String strComplexa) {
		// VARI�VEIS

		String[] arrChrAcentos;
		String[] arrChrCaracteresEspeciais;
		String[] arrChrSemAcento;

		// FIM VARI�VEIS
		try {
			// A��ES

			strComplexa = strComplexa.toLowerCase(Locale.ENGLISH);
			arrChrAcentos = new String[] { "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
					"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�" };
			arrChrSemAcento = new String[] { "c", "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u", "a", "o", "n",
					"a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u" };
			for (int intTemp = 0; intTemp < arrChrAcentos.length; intTemp++) {
				strComplexa = strComplexa.replace(arrChrAcentos[intTemp], arrChrSemAcento[intTemp]);
			}
			arrChrCaracteresEspeciais = new String[] { "\\.", ",", "-", ":", "\\(", "\\)", "�", "\\|", "\\\\", "�",
					"^\\s+", "\\s+$", "\\s+", ".", "(", ")" };
			for (int intTemp = 0; intTemp < arrChrCaracteresEspeciais.length; intTemp++) {
				strComplexa = strComplexa.replace(arrChrCaracteresEspeciais[intTemp], "");
			}
			strComplexa = strComplexa.replace(" ", "");

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
			arrChrAcentos = null;
			arrChrCaracteresEspeciais = null;
			arrChrSemAcento = null;
		}
		return strComplexa;
	}

	public static String getStrToken(List<String> lstStrTermo, int intTamanho) {
		// VARI�VEIS

		String strTermoMd5 = Utils.STRING_VAZIA;
		String strTokenResultado = Utils.STRING_VAZIA;

		// FIM VARI�VEIS
		try {
			// A��ES

			for (String strTermo : lstStrTermo) {
				strTermoMd5 = Utils.getStrMd5(strTermo);
				strTokenResultado = Utils.getStrMd5(strTokenResultado + strTermoMd5);
			}

			strTokenResultado = strTokenResultado.substring(0, intTamanho);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
		return strTokenResultado;
	}

	public static String getStrToken(List<String> lstStrTermo) {
		return Utils.getStrToken(lstStrTermo, 5);
	}

	public static String getStrValorMonetario(double monValor) {
		// VARI�VEIS

		NumberFormat objNumberFormat = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			objNumberFormat = NumberFormat.getCurrencyInstance(LOCAL_BRASIL);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
		
		return objNumberFormat.format(monValor);
	}

	public static String gregorianCalendarToString(GregorianCalendar objGregorianCalendar) {
		// VARI�VEIS

		StringBuilder stbDteResultado = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			stbDteResultado = new StringBuilder();
			stbDteResultado.append(String.format("%d/%02d/%02d",
					objGregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH),
					objGregorianCalendar.get(GregorianCalendar.MONTH) + 1,
					objGregorianCalendar.get(GregorianCalendar.YEAR)));

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return stbDteResultado.toString();
	}

	public static TextWatcher inserirMascara(final String strMascara, final EditText ediTxt) {
		return new TextWatcher() {
			boolean isUpdating;
			String old = "";

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String str = Utils.removerMascara(s.toString());
				String mascara = "";
				if (isUpdating) {
					old = str;
					isUpdating = false;
					return;
				}
				int i = 0;
				for (char m : strMascara.toCharArray()) {
					if (m != '#' && str.length() > old.length()) {
						mascara += m;
						continue;
					}
					try {
						mascara += str.charAt(i);
					} catch (Exception e) {
						break;
					}
					i++;
				}
				isUpdating = true;
				ediTxt.setText(mascara);
				ediTxt.setSelection(mascara.length());
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void afterTextChanged(Editable s) {
			}
		};
	}

	public static String removerMascara(String s) {
		return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "")
				.replaceAll("[)]", "");
	}

	public static String removerUltimaLetra(String str) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			str = str.substring(0, str.length() - 1);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
		return str;
	}

	public static Date strToDte(String strDte, EnmDataFormato enmDataFormato) {
		// VARI�VEIS

		Date dteResultado = null;
		SimpleDateFormat objSimpleDateFormat = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			objSimpleDateFormat = new SimpleDateFormat(Utils.enmDataFormatoToString(enmDataFormato), LOCAL_BRASIL);
			dteResultado = objSimpleDateFormat.parse(strDte);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
		return dteResultado;
	}

	public static GregorianCalendar strToGregorianCalendar(String strDte) {
		// VARI�VEIS

		GregorianCalendar dteResultado = null;

		// FIM VARI�VEIS
		try {
			// A��ES

			dteResultado = new GregorianCalendar();
			dteResultado.setTime(Utils.strToDte(strDte, EnmDataFormato.DD_MM_YY));
			dteResultado.add(Calendar.MONTH, 1);

			if (dteResultado.get(Calendar.MONTH) == 12) {
				dteResultado.add(Calendar.YEAR, 1);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return dteResultado;
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
