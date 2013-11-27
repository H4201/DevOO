package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.utilitaire.Constante;


public class VuePointLivraison
{
	private PointLivraison pointLivraison;
	private Color couleur;

	/**
	 * Constructeur d une vue pour point de livraison.
	 * @param pointLivraison
	 * @param couleur du point de livraison
	 */
	public VuePointLivraison(PointLivraison pointLivraison, Color couleur)
	{
		this.pointLivraison = pointLivraison;
		this.couleur = couleur;
	}

	public PointLivraison getPointLivraison()
	{
		return pointLivraison;
	}

	public Color getCouleur()
	{
		return couleur;
	}
	
	/**
	 * cette methode permet de savoir si un point de livraison est clique.
	 * On verifie que le point clique appartient a un cercle de centre
	 * le point de livraison passe en parametre et de rayon predefini (car on ne peut pas tomber sur
	 * le point de livraison exactement clique).
	 * @param x1 coordonees du clic suivant l'axe x
	 * @param y1 coordonees du clic suivant l'axe y
	 * @param x2 coordonees du noeud a tester suivant l'axe x
	 * @param y2 coordonees du noeud suivant l'axe y
	 * @return vrai si le point de livraison passe en parametre est clique
	 */
	public boolean estClique(double x1, double y1, double x2, double y2)
	{
		boolean flag = false;
		double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		if(distance <= (double) Constante.RAYONCLIC)
		{
			flag = true;
		}
		return flag;		
	}
	
	/**
	 * Cette methode permet de dessiner un point de livraison.
	 * on recupere les coordonnees du noeud correspondant au point de livraison,
	 * Les coordonnees sont adaptes aux dimensions du cadre du plan,
	 * on dessine un cercle plein 
	 * puis on lui associe une couleur correspondant a sa tranche horaire de livraison.
	 * @param g graphique ou il faudra dessiner
	 * @param facteurConversionLarg largeur du cadre du plan
	 * @param facteurConversionHaut hauteur du cadre du plan
	 * @param couleur du point de livraison
	 */
	
	public void dessinerPointLivraison(Graphics g, int facteurConversionLarg, int facteurConversionHaut, Color couleur)
	{
		Color cTemp = g.getColor();
		int x = (int) pointLivraison.getNoeud().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
		int y = (int) pointLivraison.getNoeud().getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
		int rayon = (int) (Constante.RAYONNOEUD * facteurConversionLarg / Constante.LARGEURSUPERV);	

		if(pointLivraison.getRespecteTrancheHoraireDemandee())
		{
			g.setColor(couleur);
		}
		else
		{
			g.setColor(Constante.COULEURPOINTLIVNONRESPECTE);
		}
		
		g.fillOval((int)( x - rayon) ,(int) (y - rayon) , 2 * rayon , 2 * rayon);
	    g.setColor(cTemp);
	}

}
