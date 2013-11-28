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
  
  /**
   * Modifier l'heure d'arrivee estimee au point de livraison.
   * @param nouvelle heureArriveeEstimee 
   */
  protected void setHeureArriveeEstimee(Calendar heureArriveeEstimee)
  {
	  this.heureArriveeEstimee = (Calendar) heureArriveeEstimee.clone();
  }
  
  /**
   * Recuperer l'heure d'arrivee estimee au point de livraison.
   * @return l'heure au format Calendar.
   */
  public Calendar getHeureArriveeEstimee()
  {
	  return heureArriveeEstimee;
  }
  
  /**
   * Repond a la question : est-ce que la livraison aura lieu sur la 
   * tranche horaire demandee par le client ?.
   * @return true la livraison aura lieu dans la tranche horaire demandee par 
   * le client, false sinon.
   */
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
	
  	/**
  	 * Recuperer l'id unique du point de livraison.
  	 * @return l'id.
  	 */
	public int getIdPointLivraison() {
		return idPointLivraison;
	}
	
	/**
	 * Recuperer les informations du client.
	 * @return le numero du client.
	 */
	public String getClient() {
		return client;
	}
	
	/**
	 * Recuperer le noeud sur lequel est place le point de livraison.
	 * @return le noeud lie au point de livraison.
	 */
	public Noeud getNoeud() {
		return noeud;
	}
	
	/**
	 * Recuperer la tranche horaire demandee par le client.
	 * @return la tranche horaire demandee.
	 */
	public TrancheHoraire getTrancheHoraire() {
		return trancheHoraire;
	}
	
	/**
	 * Redefinition de l'egalite entre 2 points de livraison.
	 */
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
	
	/**
	 * Permet d'afficher les informations d'un point de livraison.
	 */
	public void afficher()
	{
		System.out.println(this.toString());
	}

	/**
	 * Recuperer les informations du point de livraison dans une string.
	 */
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