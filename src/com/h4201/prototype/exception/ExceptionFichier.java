package com.h4201.prototype.exception;

import com.h4201.prototype.utilitaire.Constante;

/**
 * Cette exception est levee s'il y a une erreur avec un fichier.
 * A la lecture, ecriture, chemin erronne...
 * @author Paul
 *
 */
public class ExceptionFichier extends Exception
{
	/**
	 * Serialize constante
	 */
	private static final long serialVersionUID = 1929329573059L;

	/**
	 * Constructeur par defaut.
	 */
	public ExceptionFichier()
	{
		super(Constante.EXCEPTION_FICHIER);
	}
	
	/**
	 * Constructeur permettant de personnaliser le message d'erreur.
	 * @param s : personnalisation du message
	 */
	public ExceptionFichier(String s)
	{
		super(Constante.EXCEPTION_FICHIER + " : " + s);
	}
}
