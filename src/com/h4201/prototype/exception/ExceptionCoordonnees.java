package com.h4201.prototype.exception;

import com.h4201.prototype.utilitaire.Constante;

public class ExceptionCoordonnees extends Exception
{
	/**
	 * Serialize constante
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionCoordonnees()
	{
		super(Constante.EXCEPTION_COORDONNEES);
	}
	
	public ExceptionCoordonnees(String s)
	{
		super(Constante.EXCEPTION_COORDONNEES + " : " + s);
	}
	
	public ExceptionCoordonnees(int idNoeud)
	{
		super(Constante.EXCEPTION_COORDONNEES + ", pour : " + idNoeud);
	}
}
