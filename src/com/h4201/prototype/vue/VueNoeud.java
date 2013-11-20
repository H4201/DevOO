package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.utilitaire.Constante;

@SuppressWarnings("serial")
public class VueNoeud extends JPanel
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
	
	public void dessinerNoeud(Graphics g)
	{	
		Color cTemp = g.getColor();
		int x = (int) noeud.getX() * getWidth() / Constante.LARGEUR;
		int y = (int) noeud.getY() * getWidth() / Constante.HAUTEUR;
		int rayon = (int) (Constante.RAYONNOEUD * getWidth() / Constante.LARGEUR);
		g.setColor(Constante.COULEURNOEUD);
		g.fillOval((int) x - rayon ,(int) y - rayon , 2*rayon , 2*rayon);
        g.setColor(cTemp);
	}

}
