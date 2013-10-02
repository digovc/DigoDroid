package com.digosofter.digodroid;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import android.graphics.Color;

import com.digosofter.digodroid.erro.Erro;

public abstract class Utils {
	// CONSTANTES

	public static enum EnmRandomTipo {
		ALPHA, ALPHANUMERICO, NUMERICO
	}

	public static final Locale LOCAL_BRASIL = new Locale("pt", "BR");

	public static final String STRING_VAZIA = "";

	public enum EnmDataFormato {
		DD_MM, DD_MM_YYYY, DD_MM_YYYY_HH_MM, DD_MM_YYYY_HH_MM_SS, HH_MM_DD_MM_YYYY, HH_MM_SS_DD_MM_YYYY, YYYY_MM_DD_HH_MM_SS
	}

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

			new Erro("Erro ao arredondar valor num�rico.\n" , ex.getMessage());

		} finally {
		}
		return dblValorArredondado;
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

			new Erro("Erro ao recuperar data atual.\n" , ex.getMessage());

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

			new Erro("Erro ao gerar cor aleat�ria.\n" , ex.getMessage());

		} finally {
		}
		return intColorResultado;
	}
	
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
			case DD_MM_YYYY_HH_MM:
				strDataFormato = "dd/MM/yyyy HH:mm";
				break;
			case DD_MM_YYYY_HH_MM_SS:
				strDataFormato = "dd/MM/yyyy HH:mm:ss";
				break;
			case HH_MM_DD_MM_YYYY:
				strDataFormato = "HH:mm dd/MM/yyyy";
				break;
			case HH_MM_SS_DD_MM_YYYY:
				strDataFormato = "HH:mm:ss dd/MM/yyyy";
				break;
			case YYYY_MM_DD_HH_MM_SS:
				strDataFormato = "yyyy/MM/dd HH:mm:ss";
				break;
			default:
				strDataFormato = "dd/MM/yyyy";
				break;
			}
			objSimpleDateFormat = new SimpleDateFormat(strDataFormato, LOCAL_BRASIL);

			// FIM A��ES
		} catch (Exception e) {
		} finally {
			// LIMPAR VARI�VEIS
			// FIM LIMPAR VARI�VEIS
		}
		return objSimpleDateFormat.format(objDate);
	}

	public static String getStrPrimeiraMaiuscula(String str) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			str = str.substring(0, 1).toUpperCase() + str.substring(1);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" , ex.getMessage());

		} finally {
		}
		return str;
	}

	public static String getStrSimplificada(String strComplexa) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			strComplexa = strComplexa.toLowerCase(Locale.ENGLISH);
			String[] arrChrAcentos = new String[] { "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
					"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�" };
			String[] arrChrSemAcento = new String[] { "c", "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u", "a",
					"o", "n", "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u" };
			for (int intTemp = 0; intTemp < arrChrAcentos.length; intTemp++) {
				strComplexa = strComplexa.replace(arrChrAcentos[intTemp], arrChrSemAcento[intTemp]);
			}
			String[] arrChrCaracteresEspeciais = { "\\.", ",", "-", ":", "\\(", "\\)", "�", "\\|", "\\\\", "�",
					"^\\s+", "\\s+$", "\\s+", ".", "(", ")" };
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

		NumberFormat objNumberFormat = NumberFormat.getCurrencyInstance(LOCAL_BRASIL);

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

	public static String removerUltimaLetra(String str) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			str = str.substring(0, str.length() - 1);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro("Erro inesperado.\n" , ex.getMessage());

		} finally {
		}
		return str;
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS
}
