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

	public boolean estClique(double x, double y)
	{
		boolean flag = false;
		if(x == entrepot.getNoeud().getX() && y == entrepot.getNoeud().getY())
		{
			flag = true;
		}
		return flag;		
	}

	public void dessinerEntrepot(Graphics g, int facteurConversion, Color couleur)
	{
		Color cTemp = g.getColor();
		int x = (int) entrepot.getNoeud().getX() * facteurConversion / Constante.LARGEUR;
		int y = (int) entrepot.getNoeud().getY() * facteurConversion / Constante.HAUTEUR;
		int rayon = (int) (Constante.RAYONENTREPOT * facteurConversion / Constante.LARGEUR);	
		g.setColor(Constante.COULEURENTREPOT);
		g.fillOval((int) x - rayon ,(int) y - rayon , 2*rayon , 2*rayon);
	    g.setColor(cTemp);

	}
}
