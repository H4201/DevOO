package com.h4201.prototype.exception;

import com.h4201.prototype.utilitaire.Constante;

/**
 * Cette exception est levee si le noeud demande n'existe pas.
 * @author Paul
 *
 */
public class ExceptionNoeudInconnu extends Exception
{
	/**
	 * Serialize constante
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionNoeudInconnu()
	{
		super(Constante.EXCEPTION_NOEUD_INCONNU);
	}
	
	public ExceptionNoeudInconnu(String s)
	{
		super(Constante.EXCEPTION_NOEUD_INCONNU + " : " + s);
	}
	
	public ExceptionNoeudInconnu(int idNoeud)
	{
		super(Constante.EXCEPTION_NOEUD_INCONNU + ", idNoeud = " + idNoeud);
	}
}
