package com.h4201.prototype.modele;

import java.util.Calendar;
import java.util.Vector;

import com.h4201.prototype.utilitaire.Date;

/**
 * Une tranche horaire est un intervalle entre deux heures.
 * @author Paul
 *
 */
public class TrancheHoraire
{
	private static int dernierIdTrancheHoraire = 0;
	private int idTrancheHoraire;
	private Calendar heureDebut;
	private Calendar heureFin;
	private Vector<PointLivraison> pointsLivraison;

	/**
	 * Constructeur de tranche horaire.
	 * @param heureDebut Heure de debut du creneau horaire.
	 * @param heureFin Heure de fin du creneau horaire.
	 */
  public TrancheHoraire(Calendar heureDebut, Calendar heureFin)
  {
	  this.heureDebut = heureDebut;
	  this.heureFin = heureFin;
	  this.idTrancheHoraire = TrancheHoraire.dernierIdTrancheHoraire++;
	  this.pointsLivraison = new Vector<PointLivraison>();
  }

  /**
   * Ajouter un point de livraison a la tranche horaire.
   * @param pointLivraison a ajouter.
   */
  protected void ajouterPointLivraison(PointLivraison pointLivraison)
  {
	  this.pointsLivraison.add(pointLivraison);
  }

  /** 
   * Recuperer l'id unique de la tranche horaire.
   * @return int id de la tranche horaire
   */
    public int getIdTrancheHoraire()
    {
    	return idTrancheHoraire;
    }
    
    /**
     * Recuperer l'heure de debut de la tranche horaire.
     * @return l'heure au format Calendar.
     */
	public Calendar getHeureDebut() {
		return heureDebut;
	}
	
	/**
	 * Recuperer l'heure de fin de la tranche horaire.
	 * @return l'heure au format Calendar.
	 */
	public Calendar getHeureFin() {
		return heureFin;
	}
	
	/**
	 * Recuperer la liste des points de livraison de la tranche horaire.
	 * @return la liste des point de livraison.
	 */
	public Vector<PointLivraison> getPointsLivraisons() {
		return pointsLivraison;
	}
	
	/**
	 * Afficher les informations de la tranche horaire.
	 */
	public void afficher()
	{
		System.out.println("\n" + this.toString());
		System.out.println("Points de livraison : ");
		for(PointLivraison pointLivraison : pointsLivraison)
		{
			pointLivraison.afficher();
		}
	}
	
	/**
	 * Redefinition de l'egalite entre 2 tranches horaire.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this)
			return true;
		
		if(obj instanceof TrancheHoraire)
		{
			if(this.getIdTrancheHoraire() == ((TrancheHoraire) obj).getIdTrancheHoraire())
				return true;
		}
		
		return false;
	}

	/**
	 * Recuperer les informations de la tranche horaire dans une string.
	 */
	@Override
	public String toString() {
		return Date.getHeureFrSimplifieeDepuisCalendar(this.getHeureDebut())
				 + " - " + Date.getHeureFrSimplifieeDepuisCalendar(this.getHeureFin());
	}
}