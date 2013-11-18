package com.h4201.prototype.exception;

import com.h4201.prototype.utilitaire.Constante;

public class ExceptionTranchesHorairesNonOrdonees extends Exception
{
	/**
	 * Serialize constante
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionTranchesHorairesNonOrdonees()
	{
		super(Constante.EXCEPTION_XML);
	}
	
	public ExceptionTranchesHorairesNonOrdonees(String s)
	{
		super(Constante.EXCEPTION_XML + " : " + s);
	}
}
