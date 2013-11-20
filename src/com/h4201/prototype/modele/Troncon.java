package com.h4201.prototype.modele;


/**
 * Arc entre 2 noeuds.
 * Representation d'une rue avec son nom, sa longueur 
 * et la vitesse moyenne des automobilistes.
 * @author Paul
 *
 */
public class Troncon
{
  private String nomRue;
  private double longueur;
  private double vitesse;

  private static int dernierIdTroncon = 0;
  private int idTroncon;

  private Noeud noeudOrigine;
  private Noeud noeudDestination;

  /**
   * Constructeur d'un troncon.
   * @param noeudOrigine Noeud de depart du troncon
   * @param noeudDestination Noeud d'arrivee du troncon
   * @param nomRue Le nom de la rue
   * @param longueur la longueur de la rue
   * @param vitesse la vitesse moyenne dans cette rue
   */
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
   * Calcul du temps necessaire pour parcourir le troncon
   * @return temps en secondes
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
	
	public void afficher()
	{
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "Troncon [idTroncon=" + idTroncon + ", longueur=" + longueur 
				+ ", vitesse=" + vitesse
				+ ", noeudOrigine=" + noeudOrigine
				+ ", noeudDestination=" + noeudDestination + "]";
	}
}