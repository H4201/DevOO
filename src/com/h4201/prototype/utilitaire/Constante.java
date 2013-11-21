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
	public static final String EXCEPTION_XML = "Le XML ne correspond pas a ce qui est attendu";
	public static final String EXCEPTION_FICHIER = "Le fichier n'existe pas ou est illisible";
	public static final String EXCEPTION_NON_INSTANCIE = "L'objet n'a pas ete instancie";
	
	
	/*
	 * Conversion
	 */
	public static final double CONVERSION_METRES_EN_PIXELS = 0.1;
	
	
	/*
	 * constante pour le plan
	 */
	public static final int HAUTEUR = 450;
	public static final int LARGEUR = 600;
	public static final int POSVUEX = 350;
	public static final int POSVUEY = 100;
	public static final int RAYONNOEUD = 1;
	public static final int RAYONENTREPOT = 5;
	public static final Color COULEURNOEUD = Color.BLACK;
	public static final Color COULEURTRONCON = Color.BLACK;
	public static final Color COULEURENTREPOT = Color.BLACK;
	public static final Color ARRIEREPLAN = Color.WHITE;
	public static final int HBOUTON = 30;
	public static final int LBOUTON = 300;
	public static final int LIGNEBOUTON1 = 0;
	public static final int LIGNEBOUTON2 = 25;
	public static final int LIGNEBOUTON3 = 650;
	
	/*
	 * tableau couleurs des tranches horaires
	 */
	public static final Color tabCouleur[] = { Color.WHITE, Color.RED, Color.YELLOW, Color.BLUE,Color.GREEN,Color.GRAY,Color.PINK, Color.ORANGE, Color.BLACK,Color.DARK_GRAY };

}
