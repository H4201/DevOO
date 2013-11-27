package com.h4201.prototype.exception;

import com.h4201.prototype.utilitaire.Constante;

/**
 * Exception levee si une tranche horaire ne respecte pas les conditions definis ci-apres.
 * Une tranche horaire doit avoir une heure debut anterieure a l'heure de fin.
 * Les tranches horaire doivent etre definies dans l'ordre chronologique.
 * @author Paul
 *
 */
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
