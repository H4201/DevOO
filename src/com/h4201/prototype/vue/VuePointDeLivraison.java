package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.utilitaire.Constante;


public class VuePointDeLivraison
{
	private PointLivraison pointLivraison;
	private Color couleur;

	public VuePointDeLivraison(PointLivraison pointLivraison, Color couleur)
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
		if(x == pointLivraison.getNoeud().getX() && y == pointLivraison.getNoeud().getY())
		{
			flag = true;
		}
		return flag;		
	}
	
	public void dessinerPointLivraison(Graphics g, int facteurConversion, Color couleur)
	{
		Color cTemp = g.getColor();
		int x = (int) pointLivraison.getNoeud().getX() * facteurConversion / Constante.LARGEUR;
		int y = (int) pointLivraison.getNoeud().getY() * facteurConversion / Constante.HAUTEUR;
		int rayon = (int) (Constante.RAYONNOEUD * facteurConversion / Constante.LARGEUR);	
		g.setColor(couleur);
		g.fillOval((int) x - rayon ,(int) y - rayon , 2*rayon , 2*rayon);
	    g.setColor(cTemp);

	}

}
