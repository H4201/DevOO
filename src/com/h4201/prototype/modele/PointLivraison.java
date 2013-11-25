package com.h4201.prototype.modele;

import com.h4201.prototype.utilitaire.Constante;

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
  private boolean respecteTrancheHoraireDemandee;
  private int type = Constante.TYPE_POINT_LIVRAISON;

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
	  this.respecteTrancheHoraireDemandee = true;
  }

  	public int getType()
	{
		return type;
	}

  	protected void ajouterCheminEntrant(Chemin chemin)
  	{
  		cheminEntrant = chemin;
  	}
  	
  	protected void ajouterCheminSortant(Chemin chemin)
  	{
  		cheminSortant = chemin;
  	}
  
  	public boolean getRecpecteTrancheHoraireDemandee()
  	{
  		return respecteTrancheHoraireDemandee;
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
	
	public void afficher()
	{
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "PointLivraison [respecteTrancheHoraireDemandee="
				+ respecteTrancheHoraireDemandee + ", idPointLivraison="
				+ idPointLivraison + ", client=" + client
				+ ", noeud=" + noeud + "]";
	}
}