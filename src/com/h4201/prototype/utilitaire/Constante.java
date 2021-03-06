package com.h4201.prototype.utilitaire;

import java.awt.Color;

/**
 * Classe de constantes de l'application.
 * Classe qui ne contient que des classes statiques. Elle est abstraite car non instanciable. 
 * @author H4201
 */
public abstract class Constante
{	
	/**
	 * Exceptions.
	 */
	public static final String EXCEPTION_XML = "Le XML ne correspond pas a ce qui est attendu";
	public static final String EXCEPTION_FICHIER = "Le fichier n'existe pas ou est illisible";
	public static final String EXCEPTION_NON_INSTANCIE = "L'objet n'a pas ete instancie";
	public static final String EXCEPTION_COORDONNEES = "Les coordonnees du noeud sont incorrectes";
	public static final String EXCEPTION_NOEUD_INCONNU = "Le noeud n'existe pas";
	public static final String EXCEPTION_TRANCHE_HORAIRE = "La tranche horaire est invalide";
	
	
	/**
	 * Conversions.
	 */
	public static final double CONVERSION_METRES_EN_PIXELS = 0.1;
	public static final double CONVERSION_PIXELS_EN_METRES = 10.0;
	
	
	/**
	 * Constantes pour le plan.
	 */
	public static final int HAUTEUR = 500;
	public static final int LARGEUR = 800;
	public static final int POSVUEX = 350;
	public static final int POSVUEY = 100;
	public static final int RAYONNOEUD = 7;
	public static final int RAYONENTREPOT = 12;
	public static final Color COULEURNOEUD = Color.LIGHT_GRAY;
	public static final Color COULEURTRONCON = Color.LIGHT_GRAY;
	public static final Color COULEURENTREPOT = Color.CYAN;
	public static final Color ARRIEREPLAN = Color.WHITE;
	public static final int RAYONCLIC = 10;
	public static final double DECALAGE_CHEMIN = 0.5;
	public static final Color COULEURPOINTLIVNONRESPECTE = Color.BLACK;

	/**
	 * Tableau couleurs des tranches horaires.
	 */
	public static final Color tabCouleur[] = { Color.RED, Color.GREEN, Color.BLUE,Color.MAGENTA,Color.PINK, Color.ORANGE,
		Color.CYAN, Color.YELLOW };
	
	/**
	 * Constantes pour la fenetre de supervision.
	 */
	public static final int LARGEURSUPERV = 1200;
	public static final int HAUTEURSUPERV = 800;
	public static final int HBOUTON = 30;
	public static final int LBOUTON = 200;
	public static final int LIGNEBOUTON1 = 0;
	public static final int LIGNEBOUTON2 = 50;
	public static final int LIGNEBOUTON3 = 650;
	
	
	/**
	 * Controleur : 3 modes dans l'interaction de supervision. 
	 */
	public static final int MODE_NORMAL = 0;
	public static final int MODE_AJOUT = 1;
	public static final int MODE_SUPPRESSION = 2;
	
	
	/**
	 * Tournee.
	 */
	public static final int DUREE_LIVRAISON_ESTIMEE = 600; // en secondes
	
	
	/**
	 * Feuille de route.
	 */
	public static final String FICHIER_NOM_FEUILLE_DE_ROUTE = "feuille_de_route.txt";
}
