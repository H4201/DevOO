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
  private int nbCheminPassantParCeTroncon;

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
	  this.nbCheminPassantParCeTroncon = 0;
	  idTroncon = Troncon.dernierIdTroncon++;
  }
  
  /**
   * Permet d'indiquer que le troncon est utilise par un nouveau chemin
   * @return le nombre de chemins passant par ce troncon
   */
  public int unCheminSupplementairePasseParCeTroncon()
  {
	  return (this.nbCheminPassantParCeTroncon += 1);
  }
  
  public int getNbCheminPassantParCeTroncon()
  {
	  return this.nbCheminPassantParCeTroncon;
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
    
    /**
     * 
     * @return Longueur en metres
     */
	public double getLongueur() {
		return longueur;
	}
	
	/**
	 * 
	 * @return vitesse en m/s
	 */
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