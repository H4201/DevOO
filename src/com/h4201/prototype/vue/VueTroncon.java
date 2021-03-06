package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.Troncon;
import com.h4201.prototype.utilitaire.Constante;

/**
 * Affiche un troncon.
 * @author Eva
 *
 */
public class VueTroncon
{
	private Troncon troncon;
	
	/**
	 * Constructeur de la vue pour le troncon.
	 * @param troncon
	 */
	public VueTroncon(Troncon troncon)
	{
		this.troncon = troncon;
	}
	
	public Troncon getTroncon()
	{
		return troncon;
	}
	
	/**
	 * Cette methode permet de dessiner un troncon.
	 * Elle dessine une droite entre deux noeuds et 
	 * elle associe une couleur predefinie au troncon.
	 * @param g graphique ou il faudra dessiner
	 * @param facteurConversionLarg largeur du cadre du plan
	 * @param facteurConversionHaut hauteur du cadre du plan
	 */
	public void dessinerTroncon(Graphics g, int facteurConversionLarg, int facteurConversionHaut)
	{
		Color cTemp = g.getColor();
		
		int x1 = (int)  troncon.getNoeudDestination().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
		int y1 = (int)  troncon.getNoeudDestination().getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
		int x2 = (int)  troncon.getNoeudOrigine().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
		int y2 = (int)  troncon.getNoeudOrigine().getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
		
		
		g.setColor(Constante.COULEURTRONCON);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(cTemp);
	}	
}
