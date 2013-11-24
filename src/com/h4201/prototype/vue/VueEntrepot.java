package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.Entrepot;
import com.h4201.prototype.utilitaire.Constante;

public class VueEntrepot
{
	private Entrepot entrepot;

	public VueEntrepot(Entrepot entrepot)
	{
		this.entrepot = entrepot;		
	}
	
	public Entrepot getEntrepot()
	{
		return entrepot;
	}
/*
	public Boolean estClique(double x1, double y1, double x2, double y2)
	{
		boolean flag = false;
		int rayonClicPixels = (int) Constante.RAYONCLIC * Constante.LARGEUR / Constante.LARGEURSUPERV;
		double distance = Math.sqrt(Math.pow(x1 - y1, 2) + Math.pow(x2 - y2, 2));
		if(distance <= (double) rayonClicPixels)
		{
			flag = true;
		}
		return flag;
	}
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
