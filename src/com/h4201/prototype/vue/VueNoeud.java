package com.h4201.prototype.vue;

import java.awt.Color;

public class VueNoeud
{
	private double x;
	private double y;
	private double rayon;
	private Color couleur;
	
	public VueNoeud()
	{
		
	}
	 
	public VueNoeud(double x, double y, double r, Color couleur)
	{
		this.x = x;
        this.y =y;
        rayon = r;
        this.couleur=couleur;     
	}
	 
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getRayon()
	{
		return rayon;
	}
	public Color getCouleur()
	{
		return couleur;
	}
	
	public boolean estClique(double x, double y)
	{
		return false;		
	}
	
	public void afficherNoeud()
	{
		
	}
}
