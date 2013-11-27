package com.h4201.prototype.utilitaire;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public abstract class Date
{
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
	
	public static String getHeureFrSimplifieeDepuisCalendar(Calendar cal)
	{
		String minute = String.valueOf(cal.get(Calendar.MINUTE));
		minute = (minute.length()==1) ? "0"+minute : minute;
		String heure = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		heure = (heure.length()==1) ? "0"+heure : heure;
		
		return heure + "h" + minute;
	}
}
