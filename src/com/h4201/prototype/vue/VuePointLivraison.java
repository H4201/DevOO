package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.utilitaire.Constante;


public class VuePointLivraison
{
	private PointLivraison pointLivraison;
	private Color couleur;

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
	
	public boolean estClique(double x, double y)
	{
		boolean flag = false;
		int rayonClicPixels = (int) Constante.RAYONCLIC * Constante.LARGEUR / Constante.LARGEURSUPERV;
		double distance = Math.sqrt(Math.pow(x - pointLivraison.getNoeud().getX(), 2) + Math.pow(y - pointLivraison.getNoeud().getY(),2));
		if(distance <= (double) rayonClicPixels)
		{
			flag = true;
		}
		return flag;		
	}
	
	public void dessinerPointLivraison(Graphics g, int facteurConversionLarg, int facteurConversionHaut, Color couleur)
	{
		Color cTemp = g.getColor();
		int x = (int) pointLivraison.getNoeud().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
		int y = (int) pointLivraison.getNoeud().getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
		int rayon = (int) (Constante.RAYONNOEUD * facteurConversionLarg / Constante.LARGEURSUPERV);	
		g.setColor(couleur);
		g.fillOval((int)( x - rayon) ,(int) (y - rayon) , 2 * rayon , 2 * rayon);
	    g.setColor(cTemp);

	}

}
