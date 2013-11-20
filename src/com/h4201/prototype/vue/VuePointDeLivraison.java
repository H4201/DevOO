package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.utilitaire.Constante;


public class VuePointDeLivraison
{
	private PointLivraison pointLivraison;
	private Color couleur;

	public VuePointDeLivraison(PointLivraison pointLivraison, Color couleur)
	{
		this.pointLivraison = pointLivraison;
		this.couleur = couleur;
	}
	/*
	public Map<TrancheHoraire, Color> initMap()
	{
		return CouleursTranchesHoraires;	
	}
	*/
	public Color getCouleur()
	{
		return couleur;
	}
/*
	public Color getCouleurTrancheHoraire(TrancheHoraire trancheHoraire)
	{
		return CouleursTranchesHoraires.get(trancheHoraire);	
	}
*/
	public boolean estClique(double x, double y)
	{
		return false;		
	}
	
	public void dessinerPointLivraison(Graphics g, int facteurConversion)
	{
		Color cTemp = g.getColor();
		int x = (int) pointLivraison.getNoeud().getX() * facteurConversion / Constante.LARGEUR;
		int y = (int) pointLivraison.getNoeud().getY() * facteurConversion / Constante.HAUTEUR;
		int rayon = (int) (Constante.RAYONNOEUD * facteurConversion / Constante.LARGEUR);
	
		//g.setColor();
		g.fillOval((int) x - rayon ,(int) y - rayon , 2*rayon , 2*rayon);
	    g.setColor(cTemp);

		
		//TODO   
		// si entrepot d'une tournee dessiner l'entrepot avec sa taille
		// recuperer la couleur de la tranche horaire
		// dessiner le noeud et colorier le noeud en fonction de la couleur de la tranchehoraire
		//g.setColor();
	}



}
