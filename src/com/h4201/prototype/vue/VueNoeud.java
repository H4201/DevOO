package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.Noeud;

public class VueNoeud
{
	private Noeud noeud;
	private double rayon;
	private Color couleur;
	
	public VueNoeud(Noeud Vuenoeud)
	{
		this.noeud = noeud;
	}
	
	public Noeud getNoeud()
	{
		return noeud;
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
	
	public void dessinerNoeud(Graphics g)
	{
		double x = noeud.getX();
		double y = noeud.getY();
		//g.fillRect(, , x, y);
	}
	
	

}
