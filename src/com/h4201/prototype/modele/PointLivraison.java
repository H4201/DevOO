package com.h4201.prototype.modele;


public class PointLivraison
{
  private static int dernierIdPointLivraison;
  private int idPointLivraison;
  private Chemin cheminEntrant;
  private Chemin cheminSortant;
  private String client;
  private Noeud noeud;
  private TrancheHoraire trancheHoraire;
  private boolean respecteTrancheHoraireDemandee;

  public PointLivraison(String client, Noeud noeud, TrancheHoraire trancheHoraire)
  {
	  this.idPointLivraison = PointLivraison.dernierIdPointLivraison++;
	  this.client = client;
	  this.noeud = noeud;
	  this.trancheHoraire = trancheHoraire; 
	  this.respecteTrancheHoraireDemandee = true;
  }

  	protected void ajouterCheminEntrant(Chemin chemin)
  	{
  		cheminEntrant = chemin;
  	}
  	
  	protected void ajouterCheminSortant(Chemin chemin)
  	{
  		cheminSortant = chemin;
  	}
  
    public void neRespectePlusTrancheHoraireDemandee()
    {
    	respecteTrancheHoraireDemandee = false;
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