package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.utilitaire.Constante;


public class VueChemin
{

	private Chemin chemin;
	private Color couleur;
	
	public VueChemin(Chemin chemin, Color couleur)
	{
		this.chemin = chemin;
		this.couleur = couleur;
	}
	
	public Color getCouleur()
	{
		return couleur;
	}

	public void dessinerChemin(Graphics g, int facteurConversion, Color couleur)
	{
			Color cTemp = g.getColor();
			int x1 = (int)  chemin.getPointLivraisonOrigine().getNoeud().getX() * facteurConversion / Constante.LARGEUR;
			int y1 = (int)  chemin.getPointLivraisonOrigine().getNoeud().getY() * facteurConversion / Constante.HAUTEUR;
			int x2 = (int)  chemin.getPointLivraisonDestination().getNoeud().getX() * facteurConversion / Constante.LARGEUR;
			int y2 = (int)  chemin.getPointLivraisonDestination().getNoeud().getY() * facteurConversion/ Constante.HAUTEUR;
			
			g.setColor(couleur);
			g.drawLine(x1, y1, x2, y2);
			g.setColor(cTemp);	
		
	}


	
}
