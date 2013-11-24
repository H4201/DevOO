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
		int rayonClicPixels = (int) Constante.RAYONCLIC * Constante.LARGEUR / Constante.LARGEURSUPERV;
		double distance = Math.sqrt(Math.pow(x-noeud.getX(),2) + Math.pow(y-noeud.getY(),2));
		if(distance <= (double) rayonClicPixels)
		{
			flag = true;
		}
		return flag;
	}
	

	public void dessinerNoeud(Graphics g, int facteurConversionLarg, int facteurConversionHaut)
	{	
		Color cTemp = g.getColor();
		
		int x = (int) noeud.getX() * facteurConversionLarg / Constante.LARGEURSUPERV;
		int y = (int) noeud.getY() * facteurConversionHaut / Constante.HAUTEURSUPERV;
		int rayon = (int) (Constante.RAYONNOEUD * facteurConversionLarg / Constante.LARGEURSUPERV);
		g.setColor(Constante.COULEURNOEUD);
		g.fillOval((int)( x - rayon) ,(int)( y - rayon) , 2 * rayon , 2 * rayon);
        g.setColor(cTemp);
	}

}
