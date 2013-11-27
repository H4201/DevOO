package com.h4201.prototype.exception;

import com.h4201.prototype.utilitaire.Constante;

/**
 * Exception levee si utilisation d'un objet qui n'a pas encore ete intancie.
 * @author Paul
 *
 */
public class ExceptionNonInstancie extends Exception
{
	/**
	 * Serialize constante
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionNonInstancie()
	{
		super(Constante.EXCEPTION_NON_INSTANCIE);
	}
	
	public ExceptionNonInstancie(String s)
	{
		super(Constante.EXCEPTION_NON_INSTANCIE + " : " + s);
	}
}
