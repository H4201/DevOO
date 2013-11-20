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

	public boolean estClique(double x, double y)
	{
		return false;		
	}
	

	public Graphics dessinerNoeud(Graphics g, int facteurConversion)
	{	
		Color cTemp = g.getColor();
		int x = (int) noeud.getX() * facteurConversion / Constante.LARGEUR;
		int y = (int) noeud.getY() * facteurConversion / Constante.HAUTEUR;
		int rayon = (int) (Constante.RAYONNOEUD * facteurConversion / Constante.LARGEUR);
		g.setColor(Constante.COULEURNOEUD);
		g.fillOval((int) x - rayon ,(int) y - rayon , 2*rayon , 2*rayon);
        g.setColor(cTemp);
        
        return g;
	}

}
