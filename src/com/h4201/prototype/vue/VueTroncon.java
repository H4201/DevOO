package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.Troncon;
import com.h4201.prototype.utilitaire.Constante;


public class VueTroncon
{
	private Troncon troncon;
	private boolean etat;
	
	public VueTroncon(Troncon troncon)
	{
		this.troncon = troncon;
	}
	
	public Troncon getTroncon()
	{
		return troncon;
	}
	
	public boolean getEtat()
	{
		return etat;		
	}
	
	public void dessinerTroncon(Graphics g, int facteurConversion)
	{
		Color cTemp = g.getColor();
		int x1 = (int)  troncon.getNoeudDestination().getX() * facteurConversion / Constante.LARGEUR;
		int y1 = (int)  troncon.getNoeudDestination().getY() * facteurConversion / Constante.HAUTEUR;
		int x2 = (int)  troncon.getNoeudOrigine().getX() * facteurConversion / Constante.LARGEUR;
		int y2 = (int)  troncon.getNoeudOrigine().getY() * facteurConversion / Constante.HAUTEUR;
		g.setColor(Constante.COULEURTRONCON);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(cTemp);
	
	}
	
	public void desactiverTroncon(boolean etat)
	{
		
	}
	
}
