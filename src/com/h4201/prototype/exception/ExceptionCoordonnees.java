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

	/**
	 * Constructeur par defaut.
	 */
	public ExceptionCoordonnees()
	{
		super(Constante.EXCEPTION_COORDONNEES);
	}
	
	/**
	 * Constructeur permettant de personnaliser le message d'erreur.
	 * @param s : personnalisation du message
	 */
	public ExceptionCoordonnees(String s)
	{
		super(Constante.EXCEPTION_COORDONNEES + " : " + s);
	}
	
	/**
	 * Constructeur permettant d'indiquer le noeud qui pose probleme.
	 * @param idNoeud id du noeud qui pose probleme.
	 */
	public ExceptionCoordonnees(int idNoeud)
	{
		super(Constante.EXCEPTION_COORDONNEES + ", pour : " + idNoeud);
	}
}
