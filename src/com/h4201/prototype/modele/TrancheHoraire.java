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
	private static int dernierIdTrancheHoraire;
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

  protected void ajouterPointLivraison(PointLivraison pointLivraison)
  {
	  this.pointsLivraison.add(pointLivraison);
  }

    public int getIdTrancheHoraire()
    {
    	return idTrancheHoraire;
    }
    
	public Calendar getHeureDebut() {
		return heureDebut;
	}
	
	public Calendar getHeureFin() {
		return heureFin;
	}
	
	public Vector<PointLivraison> getPointsLivraisons() {
		return pointsLivraison;
	}
	
	public void afficher()
	{
		System.out.println("\n" + this.toString());
		System.out.println("Points de livraison : ");
		for(PointLivraison pointLivraison : pointsLivraison)
		{
			pointLivraison.afficher();
		}
	}

	@Override
	public String toString() {
		return "TrancheHoraire [idTrancheHoraire=" + idTrancheHoraire
				+ ", heureDebut=" + Date.getHeureFrDepuisCalendar(heureDebut) 
				+ ", heureFin=" + Date.getHeureFrDepuisCalendar(heureFin) + "]";
	}
}