package com.h4201.prototype.vue;

import java.awt.Color;

import com.h4201.prototype.modele.Troncon;

/* on affichera dans le plan les noeuds et les points de livraisons depuis les fichiers XML
 * puis la tournée constituée d'un ensemble de chemins ( entre deux points de livraisons)
 * et non les tronçons (entre deux noeuds quelconques)
 */

public class VueTroncon
{
	private Troncon troncon;
	private boolean etat;
	private Color couleur;
	
	public VueTroncon()
	{
		
	}
	
	public VueTroncon(Troncon troncon , Color couleur)
	{
		
	}
	
	public Troncon getTroncon()
	{
		return troncon;
	}
	
	public Color getCouleur()
	{
		return couleur;
	}
	
	public boolean getEtat()
	{
		return etat;		
	}
	
	public void afficherChemin()
	{
		
	}
	
	public void desactiverTroncon(boolean etat)
	{
		
	}
	
}
