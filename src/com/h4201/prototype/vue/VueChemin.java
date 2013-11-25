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
	
	public VueChemin(Chemin chemin, Color couleur)
	{
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
				int x1 = (int)  leTroncon.getNoeudOrigine().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
				int y1 = (int)  leTroncon.getNoeudOrigine().getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
				int x2 = (int)  leTroncon.getNoeudDestination().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
				int y2 = (int)  leTroncon.getNoeudDestination().getY() * facteurConversionHaut/ Constante.HAUTEURSUPERV;		
				g.setColor(couleur);
				g.drawLine(x1, y1, x2, y2);
				g.setColor(cTemp);
			}		
	}


	
}
