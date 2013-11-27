package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.utilitaire.Constante;


public class VueNoeud
{
	private Noeud noeud;
	
	/**
	 * Constructeur de la vue pour le noeud.
	 * @param noeud
	 */
	public VueNoeud(Noeud noeud)
	{
		this.noeud = noeud;
	}
	
	public Noeud getNoeud()
	{
		return noeud;
	}
	
	/**
	 * cette methode permet de savoir si un noeud est clique.
	 * On verifie que le point clique appartient a un cercle de centre
	 * le noeud passe en parametre et de rayon predefini (car on ne peut pas tomber sur
	 * le noeud exactement clique).
	 * @param x1 coordonees du clic suivant l'axe x
	 * @param y1 coordonees du clic suivant l'axe y
	 * @param x2 coordonees du noeud a tester suivant l'axe x
	 * @param y2 coordonees du noeud suivant l'axe y
	 * @return vrai si le noeud passe en parametre est clique
	 */
	public Boolean estClique(double x1, double y1, double x2, double y2)
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
	 * Cette methode permet de dessiner un noeud.
	 * Les coordonnees du noeud sont adaptes aux dimensions du cadre du plan,
	 * on dessine un cercle plein et on associe une couleur predefinie au noeud.
	 * @param g graphique ou il faudra dessiner
	 * @param facteurConversionLarg largeur du cadre du plan
	 * @param facteurConversionHaut hauteur du cadre du plan
	 */
	public void dessinerNoeud(Graphics g, int facteurConversionLarg, int facteurConversionHaut)
	{	
		Color cTemp = g.getColor();
		
		int x = (int) noeud.getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
		int y = (int) noeud.getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
		int rayon = (int) (Constante.RAYONNOEUD * facteurConversionLarg / Constante.LARGEURSUPERV);
		g.setColor(Constante.COULEURNOEUD);
		g.fillOval((int)( x - rayon) ,(int)( y - rayon) , 2 * rayon , 2 * rayon);
        g.setColor(cTemp);
	}

}
