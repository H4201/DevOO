package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.utilitaire.Constante;


public class VueNoeud
{
	private Noeud noeud;
	
	public VueNoeud(Noeud noeud)
	{
		this.noeud = noeud;
	}
	
	public Noeud getNoeud()
	{
		return noeud;
	}

	public Boolean estClique(double x, double y)
	{
		boolean flag = false;
		if(x == noeud.getX() &&  y == noeud.getY())
		{
			flag = true;
		}
		return flag;
	}
	

	public void dessinerNoeud(Graphics g, int facteurConversion)
	{	
		Color cTemp = g.getColor();
		/*
		int x = (int) noeud.getX() * facteurConversion / Constante.LARGEUR;
		int y = (int) noeud.getY() * facteurConversion / Constante.HAUTEUR;
		int rayon = (int) (Constante.RAYONNOEUD * facteurConversion / Constante.LARGEUR);
		*/
		int x = (int) (noeud.getX() * Constante.CONVERSION_METRES_EN_PIXELS);
		int y = (int) (noeud.getY() * Constante.CONVERSION_METRES_EN_PIXELS);
		int rayon = (int) (Constante.RAYONNOEUD * Constante.CONVERSION_METRES_EN_PIXELS);
		g.setColor(Constante.COULEURNOEUD);
		g.fillOval((int) x - rayon ,(int) y - rayon , 2*rayon , 2*rayon);
        g.setColor(cTemp);
	}

}
