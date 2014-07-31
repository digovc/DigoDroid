package com.digosofter.digodroid;

import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
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

public abstract class Util {

  public enum EnmDataFormato {
    DD_MM,
    DD_MM_YY,
    DD_MM_YYYY,
    DD_MM_YYYY_HH_MM,
    DD_MM_YYYY_HH_MM_SS,
    HH_MM_DD_MM_YYYY,
    HH_MM_SS_DD_MM_YYYY,
    YYYY_MM_DD_HH_MM_SS
  }

  public static enum EnmStrTipo {
    ALPHA,
    ALPHANUMERICO,
    NUMERICO
  }

  public static final Locale LOCAL_BRASIL = new Locale("pt", "BR");
  public static final String STR_VAZIA = "";

  public static double arredondar(double dblValor, int intQtdCasas, int ceilOrFloor) {

    double dblResultado = dblValor;
    try {
      dblResultado *= Math.pow(10, intQtdCasas);
      if (ceilOrFloor == 0) {
        dblResultado = Math.ceil(dblResultado);
      }
      else {
        dblResultado = Math.floor(dblResultado);
      }
      dblResultado /= Math.pow(10, intQtdCasas);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(109), ex);
    }
    finally {
    }
    return dblResultado;
  }

  private static String enmDataFormatoToString(EnmDataFormato enmDataFormato) {

    String strResultado = null;
    try {
      switch (enmDataFormato) {
        case DD_MM:
          strResultado = "dd/MM";
          break;
        case DD_MM_YY:
          strResultado = "dd/MM/yy";
          break;
        case DD_MM_YYYY:
          strResultado = "dd/MM/yyyy";
          break;
        case DD_MM_YYYY_HH_MM:
          strResultado = "dd/MM/yyyy HH:mm";
          break;
        case DD_MM_YYYY_HH_MM_SS:
          strResultado = "dd/MM/yyyy HH:mm:ss";
          break;
        case HH_MM_DD_MM_YYYY:
          strResultado = "HH:mm dd/MM/yyyy";
          break;
        case HH_MM_SS_DD_MM_YYYY:
          strResultado = "HH:mm:ss dd/MM/yyyy";
          break;
        case YYYY_MM_DD_HH_MM_SS:
          strResultado = "yyyy/MM/dd HH:mm:ss";
          break;
        default:
          strResultado = "dd/MM/yyyy";
          break;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return strResultado;
  }

  public static boolean getBooStrVazia(String str) {

    boolean booResultado = true;
    try {
      if (str != null && !str.isEmpty()) {
        booResultado = false;
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return booResultado;
  }

  /**
   * Verifica se a string � uma URL v�lida.
   */
  public static boolean getBooUrlValida(String url) {

    boolean booResultado = true;
    try {
      new URL(url);
    }
    catch (Exception ex) {
      booResultado = false;
    }
    finally {
    }
    return booResultado;
  }

  public static Date getDttAgora() {

    Date dttResultado = null;
    try {
      dttResultado = new Date();
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(110), ex);
    }
    finally {
    }
    return dttResultado;
  }

  public static int getIntCorAleatoria() {

    int intResultado = 0;
    Random objR;
    try {
      objR = new Random();
      intResultado = Color.argb(255, objR.nextInt(256), objR.nextInt(256), objR.nextInt(256));
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(110), ex);
    }
    finally {
    }
    return intResultado;
  }

  public static String getStrAleatoria(int intTamanho, EnmStrTipo enmStrTipo) {

    int intCharactersLength;
    StringBuffer stbResultado = new StringBuffer();
    String strCharacters = "";
    try {
      switch (enmStrTipo) {
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
        stbResultado.append(strCharacters.charAt((int) index));
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(111), ex);
    }
    finally {
    }
    return stbResultado.toString();
  }

  public static String getStrDataFormatada(Date objDate, EnmDataFormato enmDataFormato) {

    String strDataFormato = Util.STR_VAZIA;
    SimpleDateFormat objSimpleDateFormat = null;
    try {
      strDataFormato = Util.enmDataFormatoToString(enmDataFormato);
      objSimpleDateFormat = new SimpleDateFormat(strDataFormato, LOCAL_BRASIL);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
      // LIMPAR VARI�VEIS
      // FIM LIMPAR VARI�VEIS
    }
    return objSimpleDateFormat.format(objDate);
  }

  public static String getMd5(String str) {

    BigInteger objBigInteger;
    MessageDigest objMessageDigest;
    String md5Resultado = null;
    try {
      objMessageDigest = MessageDigest.getInstance("MD5");
      objBigInteger = new BigInteger(1, objMessageDigest.digest(str.getBytes()));
      md5Resultado = String.format("%0" + (objMessageDigest.digest(str.getBytes()).length << 1)
          + "X", objBigInteger);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(112), ex);
    }
    finally {
    }
    return md5Resultado;
  }

  public static String getStrPrimeiraMaiuscula(String str) {

    try {
      str = str.substring(0, 1).toUpperCase(LOCAL_BRASIL) + str.substring(1);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return str;
  }

  public static String getStrSimplificada(String strComplexa) {

    String[] arrChrAcentos;
    String[] arrChrCaracteresEspeciais;
    String[] arrChrSemAcento;
    try {
      strComplexa = strComplexa.toLowerCase(Locale.ENGLISH);
      arrChrAcentos = new String[] { "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
          "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�" };
      arrChrSemAcento = new String[] { "c", "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u",
          "a", "o", "n", "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u" };
      for (int intTemp = 0; intTemp < arrChrAcentos.length; intTemp++) {
        strComplexa = strComplexa.replace(arrChrAcentos[intTemp], arrChrSemAcento[intTemp]);
      }
      arrChrCaracteresEspeciais = new String[] { "/", "\\.", ",", "-", ":", "\\(", "\\)", "�",
          "\\|", "\\\\", "�", "^\\s+", "\\s+$", "\\s+", ".", "(", ")" };
      for (String arrChrCaracteresEspeciai : arrChrCaracteresEspeciais) {
        strComplexa = strComplexa.replace(arrChrCaracteresEspeciai, "");
      }
      strComplexa = strComplexa.replace(" ", "");
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
      arrChrAcentos = null;
      arrChrCaracteresEspeciais = null;
      arrChrSemAcento = null;
    }
    return strComplexa;
  }

  public static String getStrToken(List<String> lstStrTermo) {

    return Util.getStrToken(lstStrTermo, 5);
  }

  public static String getStrToken(List<String> lstStrTermo, int intTamanho) {

    String strTermoMd5;
    String strResultado = Util.STR_VAZIA;
    try {
      for (String strTermo : lstStrTermo) {
        strTermoMd5 = Util.getMd5(strTermo);
        strResultado = Util.getMd5(strResultado + strTermoMd5);
      }
      strResultado = strResultado.substring(0, intTamanho);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return strResultado;
  }

  public static String getStrValorMonetario(double monValor) {

    NumberFormat objNumberFormat = null;
    try {
      objNumberFormat = NumberFormat.getCurrencyInstance(LOCAL_BRASIL);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
      // LIMPAR VARI�VEIS
      // FIM LIMPAR VARI�VEIS
    }
    return objNumberFormat.format(monValor);
  }

  public static String gregorianCalendarToString(GregorianCalendar objGregorianCalendar) {

    StringBuilder stbResultado = null;
    try {
      stbResultado = new StringBuilder();
      stbResultado.append(String.format("%d/%02d/%02d",
          objGregorianCalendar.get(Calendar.DAY_OF_MONTH),
          objGregorianCalendar.get(Calendar.MONTH) + 1, objGregorianCalendar.get(Calendar.YEAR)));
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return stbResultado.toString();
  }

  public static TextWatcher inserirMascara(final String strMascara, final EditText ediTxt) {

    return new TextWatcher() {

      boolean isUpdating;
      String old = "";

      @Override
      public void afterTextChanged(Editable s) {

      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

        String str = Util.removerMascara(s.toString());
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
          }
          catch (Exception e) {
            break;
          }
          i++;
        }
        isUpdating = true;
        ediTxt.setText(mascara);
        ediTxt.setSelection(mascara.length());
      }
    };
  }

  /**
   * "Pinga" um host e retorna true caso haja resposta deste.
   */
  public static boolean ping(String url) {

    boolean booResultado = false;
    HttpURLConnection objHttpURLConnection;
    try {
      objHttpURLConnection = (HttpURLConnection) new URL(url).openConnection();
      objHttpURLConnection.setRequestMethod("HEAD");
      booResultado = objHttpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
    catch (Exception ex) {
      booResultado = false;
    }
    finally {
    }
    return booResultado;
  }

  public static String removerMascara(String s) {

    return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "")
        .replaceAll("[(]", "").replaceAll("[)]", "");
  }

  public static String removerUltimaLetra(String str) {

    try {
      str = str.substring(0, str.length() - 1);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return str;
  }

  public static Date strToDte(String strDte, EnmDataFormato enmDataFormato) {

    Date dteResultado = null;
    SimpleDateFormat sdf;
    try {
      sdf = new SimpleDateFormat(Util.enmDataFormatoToString(enmDataFormato), LOCAL_BRASIL);
      dteResultado = sdf.parse(strDte);
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return dteResultado;
  }

  public static GregorianCalendar strToGregorianCalendar(String strDte) {

    GregorianCalendar dteResultado = null;
    try {
      dteResultado = new GregorianCalendar();
      dteResultado.setTime(Util.strToDte(strDte, EnmDataFormato.DD_MM_YYYY));
      dteResultado.add(Calendar.MONTH, 1);
      if (dteResultado.get(Calendar.MONTH) == 12) {
        dteResultado.add(Calendar.YEAR, 1);
      }
    }
    catch (Exception ex) {
      new Erro(App.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
    return dteResultado;
  }
}
