package com.h4201.prototype.modele;

import java.util.Vector;


public class Tournee
{

  private static int idTournee;
  private Entrepot entrepot;
  private Vector<Chemin> chemins;
  private Vector<TrancheHoraire> trancheshoraire;

  public Tournee(Entrepot entrepot, TrancheHoraire trancheshoraire)
  {
	  Tournee.idTournee++;
  }
  
//  public void ajouterPointDeLivraison(PointLivraison pointLivraison)
//  {
//	  
//  }
//
//  public void supprimerPointDeLivraison(Noeud noeud)
//  {
//	  
//  }

  public void ajouterChemin(Chemin chemin)
  {
	  this.chemins.addElement(chemin);
  }

	public static int getIdTournee() {
		return idTournee;
	}
	
	public Entrepot getEntrepot() {
		return entrepot;
	}
	
	public Vector<Chemin> getChemins() {
		return chemins;
	}
	
	public Vector<TrancheHoraire> getTrancheshoraire() {
		return trancheshoraire;
	}
}