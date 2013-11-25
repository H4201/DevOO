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
	
	public static String getHeureFrDepuisCalendar(Calendar cal)
	{
		return cal.get(Calendar.HOUR_OF_DAY) + "h" + cal.get(Calendar.MINUTE) + "m" + cal.get(Calendar.SECOND) + "s";
	}
}
