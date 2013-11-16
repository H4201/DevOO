package com.h4201.prototype.utilitaire;

public final class Constante
{
	/**
	 * Etat d'une livraison
	 */
	public static final int NON_LIVRE = 1; // Pt de livraison non livre
	public static final int LIVRE = 2; // Pt de livraison livre
	
	public enum EtatLivraison {
		NON_LIVRE, LIVRE
	}
	
	/**
	 * Exceptions
	 */
	public static final String EXCEPTION_XML = "Le XML ne correspond pas ˆ ce qui est attendu";
}
