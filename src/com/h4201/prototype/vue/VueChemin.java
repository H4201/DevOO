package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.utilitaire.Constante;

@SuppressWarnings("serial")
public class VueChemin extends JPanel
{

	private Chemin chemin;
	Vector<VuePointDeLivraison> lesPointLivraisons = new Vector<VuePointDeLivraison>();
	public VueChemin(Chemin chemin)
	{
		this.chemin = chemin;
	}
	
	public Chemin getChemin()
	{
		return chemin;
	}
	
	public void dessinerChemin(Graphics g)
	{
		Color cTemp = g.getColor();
		int x1 = (int)  chemin.getPointLivraisonOrigine().getNoeud().getX() * getWidth() / Constante.LARGEUR;
		int y1 = (int)  chemin.getPointLivraisonOrigine().getNoeud().getY() * getWidth() / Constante.HAUTEUR;
		int x2 = (int)  chemin.getPointLivraisonDestination().getNoeud().getX() * getWidth() / Constante.LARGEUR;
		int y2 = (int)  chemin.getPointLivraisonDestination().getNoeud().getY() * getWidth() / Constante.HAUTEUR;
		
		// g.setColor(CouleurTH); set la couleur de la tranchehoraire correspondante
		g.drawLine(x1, y1, x2, y2);
		g.setColor(cTemp);	
	}
	
	public void desactiverChemin(boolean etat)
	{
		
	}


	
}
