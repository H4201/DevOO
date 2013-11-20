package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Constante;

@SuppressWarnings("serial")
public class VuePointDeLivraison extends JPanel
{
	private PointLivraison pointLivraison;
	private Vector<Tournee> lesTournees = new Vector<Tournee>();
	private Map<TrancheHoraire, Color> CouleursTranchesHoraires = new HashMap<TrancheHoraire,Color>();

	
	public VuePointDeLivraison(PointLivraison pointLivraison)
	{
		this.pointLivraison = pointLivraison;
	}
	
	public Map<TrancheHoraire, Color> initMap()
	{
		Controleur controleur = Controleur.getInstance();
		return CouleursTranchesHoraires;
		
	}
	
	public Color getCouleurTrancheHoraire(TrancheHoraire trancheHoraire)
	{
		return CouleursTranchesHoraires.get(trancheHoraire);	
	}

	
	public void dessinerPointLivraison(Graphics g)
	{
		Color cTemp = g.getColor();
		int x = (int) pointLivraison.getNoeud().getX() * getWidth() / Constante.LARGEUR;
		int y = (int) pointLivraison.getNoeud().getY() * getWidth() / Constante.HAUTEUR;
		int rayon = (int) (Constante.RAYONNOEUD * getWidth() / Constante.LARGEUR);
		
		if(CouleursTranchesHoraires.containsKey(pointLivraison.getTrancheHoraire()))
		{
			g.setColor(CouleursTranchesHoraires.get(pointLivraison.getTrancheHoraire()));
			g.fillOval((int) x - rayon ,(int) y - rayon , 2*rayon , 2*rayon);
	        g.setColor(cTemp);
		}
		else
		{
			CouleursTranchesHoraires.put(pointLivraison.getTrancheHoraire(), Color.black);
			//cas à traiter
		}
		
		//TODO   
		// si entrepot d'une tournee dessiner l'entrepot avec sa taille
		// recuperer la couleur de la tranche horaire
		// dessiner le noeud et colorier le noeud en fonction de la couleur de la tranchehoraire
		//g.setColor();
	}


}
