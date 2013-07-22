package DigoDroid.Mailing;

import DigoDroid.Objeto;
import android.telephony.SmsManager;

public class Sms extends Objeto {
/* CONSTANTES */

/* ATRIBUTOS */

/* MODIFICADORES */

/* CONSTRUTORES */

/* MÉTODOS */
	public void enviarSms(String phoneNumber, String message)
	   {
	       SmsManager sms = SmsManager.getDefault();
	       sms.sendTextMessage(phoneNumber, null, message, null, null);
	    }
}
