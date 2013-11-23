package com.h4201.prototype.modele;

import java.util.Vector;

/**
 * Un chemin est une succession orientee de un ou plusieurs troncons.
 * Un chemin est un parcours optimise en temps de troncons pour aller 
 * d'un noeud origine a un noeud destination.
 * @author Paul
 *
 */
public class Chemin
{

	private double longueur;
	private double temps;
    private PointLivraison pointLivraisonOrigine;
    private PointLivraison pointLivraisonDestination;
    private Vector<Troncon> troncons;
    private static int dernierIdChemin = 0;
    private int idChemin = 0;

    /**
     * Constructeur d'un chemin.
     * @param pointLivraisonOrigine Noeud de depart
     * @param pointLivraisonDestination Noeud d'arrivee
     * @param longueur longueur totale du chemin (= somme des troncons)
     * @param temps duree totale necessaire pour parcourir le chemin
     */
    public Chemin(PointLivraison pointLivraisonOrigine,
    		PointLivraison pointLivraisonDestination, Vector<Troncon> troncons,
    		double longueur, double temps)
    {
    	this.longueur = longueur;
    	this.temps = temps;
    	this.pointLivraisonOrigine = pointLivraisonOrigine;
    	this.pointLivraisonDestination = pointLivraisonDestination;
    	this.troncons = troncons;
    	this.idChemin = Chemin.dernierIdChemin++;
    }

	public double getLongueur() {
		return longueur;
	}

	public double getTemps() {
		return temps;
	}

	public PointLivraison getPointLivraisonOrigine() {
		return pointLivraisonOrigine;
	}

	public PointLivraison getPointLivraisonDestination() {
		return pointLivraisonDestination;
	}

	public Vector<Troncon> getTroncons() {
		return troncons;
	}
	
	public int getIdChemin() {
		return idChemin;
	}
	
	public void afficher()
	{
		System.out.println(this.toString());
		  
		System.out.println("Troncons : ");
		for(Troncon troncon : troncons)
		{
			troncon.afficher();
		}
	}

	@Override
	public String toString() {
		return "Chemin [longueur=" + longueur + ", temps=" + temps
				+ ", pointLivraisonOrigine=" + pointLivraisonOrigine
				+ ", pointLivraisonDestination=" + pointLivraisonDestination + "]";
	}
}
