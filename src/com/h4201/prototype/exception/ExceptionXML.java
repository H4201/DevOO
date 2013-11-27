package com.h4201.prototype.exception;

import com.h4201.prototype.utilitaire.Constante;

/**
 * Exception levee si le XML ne respecte pas la DTD.
 * @author Paul
 *
 */
public class ExceptionXML extends Exception
{
	/**
	 * Serialize constante
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par defaut.
	 */
	public ExceptionXML()
	{
		super(Constante.EXCEPTION_XML);
	}
	
	/**
	 * Constructeur permettant de personnaliser le message d'erreur.
	 * @param s : personnalisation du message
	 */
	public ExceptionXML(String s)
	{
		super(Constante.EXCEPTION_XML + " : " + s);
	}
}
