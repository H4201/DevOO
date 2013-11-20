package com.h4201.prototype.modele;

import java.util.Vector;

/*
 */
public class Chemin
{

	private double longueur;
	private double temps;
    private PointLivraison pointLivraisonOrigine;
    private PointLivraison pointLivraisonDestination;
    private Vector<Troncon> troncons;

    public Chemin(PointLivraison pointLivraisonOrigine,
    		PointLivraison pointLivraisonDestination, Vector<Troncon> troncons,
    		double longueur, double temps)
    {
    	this.longueur = longueur;
    	this.temps = temps;
    	this.pointLivraisonOrigine = pointLivraisonOrigine;
    	this.pointLivraisonDestination = pointLivraisonDestination;
    	this.troncons = troncons;
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