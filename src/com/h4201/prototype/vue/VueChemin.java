package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.Chemin;

public class VueChemin
{

	private Chemin chemin;
	private Color couleur;
	
	public VueChemin(Chemin chemin)
	{
		this.chemin = chemin;
	}
	
	public VueChemin(Chemin chemin , Color couleur)
	{
		
	}

	public Chemin getChemin()
	{
		return chemin;
	}
	
	public Color getCouleur()
	{
		return couleur;
	}
	
	public void dessinerChemin(Graphics g)
	{
		
	}
	
	public void desactiverChemin(boolean etat)
	{
		
	}


	
}
