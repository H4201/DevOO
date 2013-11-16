package com.h4201.prototype.vue;

import java.awt.Color;

import com.h4201.prototype.modele.Chemin;

/* on affichera dans le plan les noeuds et les points de livraisons depuis les fichiers XML
 * puis la tournée constituée d'un ensemble de chemins ( entre deux points de livraisons)
 * et non les tronçons (entre deux noeuds quelconques)
 */

public class VueChemin
{
	private Chemin chemin;
	private boolean etat;
	
	public VueChemin()
	{
		
	}
	
	public VueChemin(Chemin chemin, Color couleur)
	{
		
	}
	
	public Chemin getChemin()
	{
		return chemin;
	}
	
	public boolean getEtat()
	{
		return etat;		
	}
	
	public void afficherChemin()
	{
		
	}
	
	public void desactiverChemin(boolean etat)
	{
		
	}
	
}
