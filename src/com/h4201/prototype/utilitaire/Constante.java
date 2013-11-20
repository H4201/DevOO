package com.h4201.prototype.utilitaire;

import java.awt.Color;

/**
 * 
 * @author Paul
 *
 */
public abstract class Constante
{	
	/*
	 * Exception
	 */
	
	/*
	 * constante pour le plan
	 */
	public static final int HAUTEUR = 240;
	public static final int LARGEUR = 240;
	public static final int RAYONNOEUD = 1;
	public static final int RAYONENTREPOT = 5;
	public static final Color COULEURNOEUD = Color.GRAY;
	public static final Color COULEURTRONCON = Color.GRAY;
	public static final Color ARRIEREPLAN = Color.BLUE;
	
	/*
	 * tableau couleurs des tranches horaires
	 */
	public static final Color tabCouleur[] = { Color.WHITE, Color.RED, Color.YELLOW, Color.BLUE,Color.GREEN,Color.GRAY,Color.PINK, Color.ORANGE, Color.BLACK,Color.DARK_GRAY };

	public static final String EXCEPTION_XML = "Le XML ne correspond pas à ce qui est attendu";
	public static final String EXCEPTION_FICHIER = "Le fichier n'existe pas ou est illisible";
	public static final String EXCEPTION_NON_INSTANCIE = "L'objet n'a pas été instancié";
	
	
	/*
	 * Conversion
	 */
	public static final double CONVERSION_METRES_EN_PIXELS = 0.1;
}
