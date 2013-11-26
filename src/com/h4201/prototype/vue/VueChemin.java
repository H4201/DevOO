package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.Troncon;
import com.h4201.prototype.utilitaire.Constante;


public class VueChemin
{

	private Chemin chemin;
	private Color couleur;
	private int numeroChemin;
	
	public VueChemin(int numeroChemin, Chemin chemin, Color couleur)
	{
		this.numeroChemin = numeroChemin;
		this.chemin = chemin;
		this.couleur = couleur;
	}
	
	public Color getCouleur()
	{
		return couleur;
	}

	public void dessinerChemin(Graphics g, int facteurConversionLarg, int facteurConversionHaut, Color couleur)
	{
			Color cTemp = g.getColor();
			Vector<Troncon> lesTronconsDuChemin = chemin.getTroncons();
						
			for(Troncon leTroncon : lesTronconsDuChemin)
			{
				// Determination des coordonnees
				int x1 = (int)leTroncon.getNoeudOrigine().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
				int y1 = (int)leTroncon.getNoeudOrigine().getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
				int x2 = (int)leTroncon.getNoeudDestination().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
				int y2 = (int)leTroncon.getNoeudDestination().getY() * facteurConversionHaut/ Constante.HAUTEURSUPERV;		
				
				double x3 = x1;
				double y3 = y1;
				double x4 = x2;
				double y4 = y2;
				int facteur = 1;
				// Decalage des chemins si besoin
				if(leTroncon.getNbCheminPassantParCeTroncon() > 1)
				{
					if(numeroChemin%2 == 0)
					{
						facteur = -1;
					}
					
					x3 = x1 + ((numeroChemin/2) * Constante.DECALAGE_CHEMIN) * facteur;
					y3 = y1 + ((numeroChemin/2) * Constante.DECALAGE_CHEMIN) * facteur;
					
					x4 = x3 + x2 - x1;
					y4 = y3 + y2 - y1;
				}
				
				g.setColor(couleur);
				g.drawLine((int) Math.round(x3), (int) Math.round(y3), 
						(int) Math.round(x4), (int) Math.round(y4));
				g.setColor(cTemp);
			}		
	}


	
}
