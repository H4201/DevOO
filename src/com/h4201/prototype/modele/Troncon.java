package com.h4201.prototype.modele;

public class Troncon {

  private double longueur;

  private double vitesse;

  private int idTroncon;

  private Noeud noeudOrigine;
  private Noeud noeudDestination;

  public Troncon(Noeud noeudOrigine, Noeud noeudDestination, double longueur, double vitesse)
  {
	  this.noeudOrigine = noeudOrigine;
	  this.noeudDestination = noeudDestination;
	  this.longueur = longueur;
	  this.vitesse = vitesse;
  }
  
  /**
   * Calcul du temps nécessaire pour parcourir le troncon
   * @return Temps en secondes
   */
  public double calculerTemps()
  {
	  return (longueur/vitesse);
  }

	public double getLongueur() {
		return longueur;
	}
	
	public double getVitesse() {
		return vitesse;
	}
	
	public int getIdTroncon() {
		return idTroncon;
	}
	
	public Noeud getNoeudOrigine() {
		return noeudOrigine;
	}
	
	public Noeud getNoeudDestination() {
		return noeudDestination;
	}
}