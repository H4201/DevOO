package com.h4201.prototype.modele;

import com.h4201.prototype.utilitaire.Constante.EtatLivraison;

public class PointLivraison
{
  private EtatLivraison etatLivraison;
  private int idPointLivraison;
  private Chemin cheminEntrant;
  private Chemin cheminSortant;
  private Noeud noeud;
  private TrancheHoraire trancheHoraire;

  public PointLivraison(Noeud noeud, TrancheHoraire trancheHoraire)
  {
	  this.noeud = noeud;
	  this.trancheHoraire = trancheHoraire; 
  }

	public EtatLivraison getEtatLivraison() {
		return etatLivraison;
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
	
	public Noeud getNoeud() {
		return noeud;
	}
	
	public TrancheHoraire getTrancheHoraire() {
		return trancheHoraire;
	}
}