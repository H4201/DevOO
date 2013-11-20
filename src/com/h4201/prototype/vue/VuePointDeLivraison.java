package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.utilitaire.Constante;

@SuppressWarnings("serial")
public class VuePointDeLivraison extends JPanel
{
	private PointLivraison pointLivraison;
	private Color couleur;
	/*
	private Vector<Tournee> lesTournees = new Vector<Tournee>();
	private Map<TrancheHoraire, Color> CouleursTranchesHoraires = new HashMap<TrancheHoraire,Color>();
	*/
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
	
	public void dessinerPointLivraison(Graphics g)
	{
		Color cTemp = g.getColor();
		int x = (int) pointLivraison.getNoeud().getX() * getWidth() / Constante.LARGEUR;
		int y = (int) pointLivraison.getNoeud().getY() * getWidth() / Constante.HAUTEUR;
		int rayon = (int) (Constante.RAYONNOEUD * getWidth() / Constante.LARGEUR);
	
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
