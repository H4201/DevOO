package com.h4201.prototype.exception;

import com.h4201.prototype.utilitaire.Constante;

/**
 * Cette exception est levee si des coordonnees sont incorrectes (hors limites).
 * @author Paul
 *
 */
public class ExceptionCoordonnees extends Exception
{
	/**
	 * Serialize constante
	 */
	private static final long serialVersionUID = -4493307342764988569L;

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
