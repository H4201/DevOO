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

    /**
     * Recuperer la somme des longueurs des troncons du chemin.
     * @return la longueur du chemin.
     */
	public double getLongueur() {
		return longueur;
	}

	/**
	 * Recuperer le temps cumule des troncons du chemin.
	 * @return temps total pour parcourir le chemin.
	 */
	public double getTemps() {
		return temps;
	}

	/**
	 * Recuperer le point de depart du chemin.
	 * @return le point de livraison origine.
	 */
	public PointLivraison getPointLivraisonOrigine() {
		return pointLivraisonOrigine;
	}

	/**
	 * Recuperer le point d'arrivee du chemin.
	 * @return le point de livraison destination.
	 */
	public PointLivraison getPointLivraisonDestination() {
		return pointLivraisonDestination;
	}

	/**
	 * Recuperer la liste des troncons qui composent le chemin.
	 * @return liste des troncons.
	 */
	public Vector<Troncon> getTroncons() {
		return troncons;
	}
	
	/**
	 * Recuperer l'id unique du chemin.
	 * @return l'id du chemin.
	 */
	public int getIdChemin() {
		return idChemin;
	}
	
	/**
	 * Permet d'afficher les informations du chemin.
	 */
	public void afficher()
	{
		System.out.println(this.toString());
		  
		System.out.println("Troncons : ");
		for(Troncon troncon : troncons)
		{
			troncon.afficher();
		}
	}

	/**
	 * Permet de recuperer dans une string les informations du chemin.
	 */
	@Override
	public String toString() {
		return "Chemin [longueur=" + longueur + ", temps=" + temps
				+ ", pointLivraisonOrigine=" + pointLivraisonOrigine
				+ ", pointLivraisonDestination=" + pointLivraisonDestination + "]";
	}
}
