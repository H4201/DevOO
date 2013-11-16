package com.h4201.prototype.utilitaire;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Date
{
	public static Calendar getCalendarDepuisHeureAn(String heureFormatAnglais)
	{
		String[] eltsHeure = heureFormatAnglais.split(":");
		
		GregorianCalendar gcalendar = new GregorianCalendar();
		
		gcalendar.set(Calendar.HOUR, Integer.valueOf(eltsHeure[0]).intValue());
		gcalendar.set(Calendar.MINUTE, Integer.valueOf(eltsHeure[1]).intValue());
		gcalendar.set(Calendar.SECOND, Integer.valueOf(eltsHeure[2]).intValue());
		
		return gcalendar;
	}
}
