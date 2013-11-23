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
	
	/*
	public Noeud getNoeud(double x, double y)
	{
		if(x == noeud.getX() &&  y == noeud.getY())
		{
			return noeud;
		}
		else
		{
			return null;
		}	
	}
*/
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
		int x = (int) noeud.getX() * facteurConversion / Constante.LARGEUR;
		int y = (int) noeud.getY() * facteurConversion / Constante.HAUTEUR;
		int rayon = (int) (Constante.RAYONNOEUD * facteurConversion / Constante.LARGEUR);
		g.setColor(Constante.COULEURNOEUD);
		g.fillOval((int) x - rayon ,(int) y - rayon , 2*rayon , 2*rayon);
        g.setColor(cTemp);
	}

}
