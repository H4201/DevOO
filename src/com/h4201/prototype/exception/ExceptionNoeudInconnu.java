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

	/**
	 * Constructeur par defaut.
	 */
	public ExceptionNoeudInconnu()
	{
		super(Constante.EXCEPTION_NOEUD_INCONNU);
	}
	
	/**
	 * Constructeur permettant de personnaliser le message d'erreur.
	 * @param s : personnalisation du message
	 */
	public ExceptionNoeudInconnu(String s)
	{
		super(Constante.EXCEPTION_NOEUD_INCONNU + " : " + s);
	}
	
	/**
	 * Constructeur permettant d'indiquer quel noeud pose probleme.
	 * @param idNoeud id du noeud qui pose probleme.
	 */
	public ExceptionNoeudInconnu(int idNoeud)
	{
		super(Constante.EXCEPTION_NOEUD_INCONNU + ", idNoeud = " + idNoeud);
	}
}
