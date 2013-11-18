package com.h4201.prototype.utilitaire;

import java.awt.Color;

public abstract class Constante
{
	/**
	 * Etat d'une livraison
	 */
//	public static final int NON_LIVRE = 1; // Pt de livraison non livre
//	public static final int LIVRE = 2; // Pt de livraison livre
//	
//	public enum EtatLivraison {
//		NON_LIVRE, LIVRE
//	}
	
	/*
	 * constante pour le plan
	 */
	public static final int LONGUEUR = 240;
	public static final int LARGEUR = 240;
	public static final Color ARRIEREPLAN = Color.BLUE;
	
	/**
	 * Exceptions
	 */
	public static final String EXCEPTION_XML = "Le XML ne correspond pas à ce qui est attendu";
	public static final String EXCEPTION_FICHIER = "Le fichier n'existe pas ou est illisible";
	public static final String EXCEPTION_NON_INSTANCIE = "L'objet n'a pas été instancié";
	
}
