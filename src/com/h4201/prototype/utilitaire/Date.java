package com.h4201.prototype.utilitaire;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Classe des actions possibles sur une date.
 * Classe qui ne contient que des classes statiques. Elle est abstraite car non instanciable. 
 * @author H4201
 */
public abstract class Date
{
	/**
	 * Convertit un heure au format anglais en un Calendar.
	 * @param heureFormatAnglais de type hh:ii:ss
	 * @return un calendar instancie selon la date en parametre.
	 */
	public static Calendar getCalendarDepuisHeureAn(String heureFormatAnglais)
	{
		String[] eltsHeure = heureFormatAnglais.split(":");
		
		TimeZone tz = TimeZone.getTimeZone("GMT+1" ); // Paris
		GregorianCalendar gcalendar = new GregorianCalendar();
		gcalendar.setTimeZone(tz);
		
		gcalendar.set(Calendar.YEAR, 1);
		gcalendar.set(Calendar.MONTH, 1);
		gcalendar.set(Calendar.DAY_OF_MONTH, 1);
		
		gcalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(eltsHeure[0]).intValue());
		gcalendar.set(Calendar.MINUTE, Integer.valueOf(eltsHeure[1]).intValue());
		gcalendar.set(Calendar.SECOND, Integer.valueOf(eltsHeure[2]).intValue());
		
		return gcalendar;
	}
	
	/**
	 * Convertit des secondes en un Calendar.
	 * @param nombreDeSecondes secondes a convertir.
	 * @return un Calendar instancie.
	 */
	public static Calendar getCalendarDepuisSecondes(double nombreDeSecondes)
	{
		TimeZone tz = TimeZone.getTimeZone("GMT+1" ); // Paris
		GregorianCalendar gcalendar = new GregorianCalendar();
		gcalendar.setTimeZone(tz);
		
		gcalendar.set(Calendar.YEAR, 1);
		gcalendar.set(Calendar.MONTH, 1);
		gcalendar.set(Calendar.DAY_OF_MONTH, 1);
		

		int secondes = (int) nombreDeSecondes % 60 ;
		int minutes = (int) ((nombreDeSecondes / (60)) % 60);
		int heures   = (int) ((nombreDeSecondes / (60*60)) % 24);
		
		gcalendar.set(Calendar.HOUR_OF_DAY, heures);
		gcalendar.set(Calendar.MINUTE, minutes);
		gcalendar.set(Calendar.SECOND, secondes);
		
		return gcalendar;
	}
	
	/**
	 * Convertit un Calendar en une chaine de caracteres heure minute seconde.
	 * @param cal Calendar instancie.
	 * @return chaine de caracteres : heure minute seconde.
	 */
	public static String getHeureFrDepuisCalendar(Calendar cal)
	{
		String seconde = String.valueOf(cal.get(Calendar.SECOND));
		seconde = (seconde.length()==1) ? "0"+seconde : seconde;
		String minute = String.valueOf(cal.get(Calendar.MINUTE));
		minute = (minute.length()==1) ? "0"+minute : minute;
		String heure = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		heure = (heure.length()==1) ? "0"+heure : heure;
		
		return heure + "h" + minute + "m" + seconde + "s";
	}
	
	/**
	 * Convertit un Calendar en une chaine de caracteres heure minute.
	 * @param cal Calendar instancie.
	 * @return chaine de caracteres : heure minute.
	 */
	public static String getHeureFrSimplifieeDepuisCalendar(Calendar cal)
	{
		String minute = String.valueOf(cal.get(Calendar.MINUTE));
		minute = (minute.length()==1) ? "0"+minute : minute;
		String heure = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		heure = (heure.length()==1) ? "0"+heure : heure;
		
		return heure + "h" + minute;
	}
}
