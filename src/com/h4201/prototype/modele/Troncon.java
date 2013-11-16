package com.h4201.prototype.modele;

public class Troncon
{
  private String nomRue;
  private double longueur;
  private double vitesse;

  private static int dernierIdTroncon = 0;
  private int idTroncon;

  private Noeud noeudOrigine;
  private Noeud noeudDestination;

  public Troncon(Noeud noeudOrigine, Noeud noeudDestination, String nomRue, 
		  double longueur, double vitesse)
  {
	  this.noeudOrigine = noeudOrigine;
	  this.noeudDestination = noeudDestination;
	  this.nomRue = nomRue;
	  this.longueur = longueur;
	  this.vitesse = vitesse;
	  idTroncon = Troncon.dernierIdTroncon++;
  }
  
  /**
   * Calcul du temps nécessaire pour parcourir le troncon
   * @return Temps en secondes
   */
  public double calculerTemps()
  {
	  return (longueur/vitesse);
  }

    public String getNomRue() {
    	return nomRue;
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

	@Override
	public String toString() {
		return "Troncon [idTroncon=" + idTroncon + ", longueur=" + longueur 
				+ ", vitesse=" + vitesse
				+ ", noeudOrigine=" + noeudOrigine
				+ ", noeudDestination=" + noeudDestination + "]";
	}
}