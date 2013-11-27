package com.h4201.prototype.modele;

import java.util.Calendar;

import com.h4201.prototype.utilitaire.Date;

/**
 * PointLivraison est un noeud du plan faisant partie d'une tournee et donc livre.
 * @author Paul
 *
 */
public class PointLivraison
{
  private static int dernierIdPointLivraison = 0;
  private int idPointLivraison;
  private Chemin cheminEntrant;
  private Chemin cheminSortant;
  private String client;
  private Noeud noeud;
  private TrancheHoraire trancheHoraire;
  private Calendar heureArriveeEstimee;

  /**
   * Constructeur du point de livraison.
   * @param client le numero du client
   * @param noeud le noeud du plan correspondant a ce point de livraison
   * @param trancheHoraire tranche horaire
   */
  public PointLivraison(String client, Noeud noeud, TrancheHoraire trancheHoraire)
  {
	  this.idPointLivraison = PointLivraison.dernierIdPointLivraison++;
	  this.client = client;
	  this.noeud = noeud;
	  this.trancheHoraire = trancheHoraire;
  }
  
  protected void setHeureArriveeEstimee(Calendar heureArriveeEstimee)
  {
	  this.heureArriveeEstimee = (Calendar) heureArriveeEstimee.clone();
  }
  
  public Calendar getHeureArriveeEstimee()
  {
	  return heureArriveeEstimee;
  }

  	protected void ajouterCheminEntrant(Chemin chemin)
  	{
  		cheminEntrant = chemin;
  	}
  	
  	protected void ajouterCheminSortant(Chemin chemin)
  	{
  		cheminSortant = chemin;
  	}
  
  	public boolean getRespecteTrancheHoraireDemandee()
  	{
  		if(getHeureArriveeEstimee() == null)
  			return false;
  		else
  			return ((getHeureArriveeEstimee().after(trancheHoraire.getHeureDebut()) 
  				&& getHeureArriveeEstimee().before(trancheHoraire.getHeureFin()))
  				|| getHeureArriveeEstimee().equals(trancheHoraire.getHeureDebut())
  				|| getHeureArriveeEstimee().equals(trancheHoraire.getHeureFin()));
  	}
	
	public int getIdPointLivraison() {
		return idPointLivraison;
	}
	
	public Chemin getCheminEntrant() {
		return cheminEntrant;
	}
	
	public Chemin getCheminSortant() {
		return cheminSortant;
	}
	
	public String getClient() {
		return client;
	}
	
	public Noeud getNoeud() {
		return noeud;
	}
	
	public TrancheHoraire getTrancheHoraire() {
		return trancheHoraire;
	}
	
	protected void setTrancheHoraire(TrancheHoraire trancheHoraire)
	{
		this.trancheHoraire = trancheHoraire;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this)
			return true;
		
		if(obj instanceof PointLivraison)
		{
			if(this.getIdPointLivraison() == ((PointLivraison) obj).getIdPointLivraison())
				return true;
		}
		
		return false;
	}
	
	public void afficher()
	{
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		String str = "L" + this.getIdPointLivraison();
		if(this.getHeureArriveeEstimee() != null)
		{
			str += " - " + Date.getHeureFrSimplifieeDepuisCalendar(
					this.getHeureArriveeEstimee());
		}		
		
		return str;
	}
}