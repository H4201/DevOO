package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.Entrepot;
import com.h4201.prototype.utilitaire.Constante;

/**
 * Affichage de l'entrepot
 * @author Eva
 *
 */
public class VueEntrepot
{
	private Entrepot entrepot;
	
	/**
	 * Constructeur de l entrepot.
	 * @param entrepot
	 */
	public VueEntrepot(Entrepot entrepot)
	{
		this.entrepot = entrepot;		
	}
	
	public Entrepot getEntrepot()
	{
		return entrepot;
	}
	
	/**
	 * Cette methode permet de dessiner l entrepot (le depot).
	 * l entrepot est un point de livraison particulier, sa taille est 
	 * legerement superieure a celle des autres noeuds et points de livraison
	 * @param g graphique ou il faudra dessiner
	 * @param facteurConversionLarg largeur du panel du plan
	 * @param facteurConversionHaut hauteur du panel du plan
	 * @param couleur couleur predefinie pour l entrepot
	 */
	public void dessinerEntrepot(Graphics g, int facteurConversionLarg, int facteurConversionHaut, Color couleur)
	{
		Color cTemp = g.getColor();
		int x = (int) entrepot.getNoeud().getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
		int y = (int) entrepot.getNoeud().getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
		int rayon = (int) (Constante.RAYONENTREPOT * facteurConversionLarg / Constante.LARGEURSUPERV);	
		g.setColor(Constante.COULEURENTREPOT);
		g.fillOval((int) (x - rayon) ,(int) (y - rayon) , 2 * rayon , 2 * rayon);
	    g.setColor(cTemp);

	}
}
