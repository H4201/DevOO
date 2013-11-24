package com.h4201.prototype.exception;

import com.h4201.prototype.utilitaire.Constante;

public class ExceptionTrancheHoraire extends Exception
{
	/**
	 * Serialize constante
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionTrancheHoraire()
	{
		super(Constante.EXCEPTION_TRANCHE_HORAIRE);
	}
	
	public ExceptionTrancheHoraire(String s)
	{
		super(Constante.EXCEPTION_TRANCHE_HORAIRE + " : " + s);
	}
}
