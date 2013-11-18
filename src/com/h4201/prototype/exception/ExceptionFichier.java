package com.h4201.prototype.exception;

import com.h4201.prototype.utilitaire.Constante;

public class ExceptionFichier extends Exception
{
	/**
	 * Serialize constante
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionFichier()
	{
		super(Constante.EXCEPTION_FICHIER);
	}
	
	public ExceptionFichier(String s)
	{
		super(Constante.EXCEPTION_FICHIER + " : " + s);
	}
}
