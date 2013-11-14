package exception;

import utilitaire.Constante;

public class ExceptionXML extends Exception
{
	/**
	 * Serialize constante
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionXML()
	{
		super(Constante.EXCEPTION_XML);
	}
	
	public ExceptionXML(String s)
	{
		super(s);
	}
}
