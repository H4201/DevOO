package com.h4201.prototype.modele;

import java.util.Vector;

import com.h4201.prototype.exception.ExceptionTranchesHorairesNonOrdonees;

public class AppGraphe
{
	private Plan plan;
	private Tournee tournee;
	
	public AppGraphe(Plan plan, Tournee tournee)
	{
		
	}
	
	public Tournee GenererTournee(Plan plan, Entrepot entrepot, TrancheHoraire[] tranchesHoraire)
		  throws ExceptionTranchesHorairesNonOrdonees{	
		// Cr�er un nouveau Graphe
		AppGraphe graphe = null;
		
		// Parcourir les tranches horaires de mani�re ordonn�e
		for (int i=0; i<tranchesHoraire.length; i++){
			
			// V�rifier que les tranches horaires du jour sont bien ordon�es
			if (i>0 && tranchesHoraire[i-1].getHeureFin().before(tranchesHoraire[i].getHeureDebut())
				|| i<tranchesHoraire.length && tranchesHoraire[i+1].getHeureDebut().after(tranchesHoraire[i].getHeureFin())){
				
				// Parcourir les points de livraison de cette tranche
				Vector<PointLivraison> pointsLivraison = tranchesHoraire[i].getPointsLivraisons();
				for (int j=0; j<pointsLivraison.size(); j++){
					
					// Cr�er un chemin vers tous les autres points de livraison de la m�me tranche
					for (int k=0; k<pointsLivraison.size(); k++){
						if (pointsLivraison.get(j) != pointsLivraison.get(k)) {
							creerChemin(pointsLivraison.get(j), pointsLivraison.get(k), plan, graphe);
						}
					}
					
					// Cr�er un chemin vers tous les points de livraison de la tranche suivante (sauf derni�re)
					if (i < tranchesHoraire.length-1){
						Vector<PointLivraison> pointsLivraisonTrancheSuivante = tranchesHoraire[i+1].getPointsLivraisons();
						for (int k=0; k<pointsLivraisonTrancheSuivante.size(); k++){
							creerChemin(pointsLivraison.get(j), pointsLivraisonTrancheSuivante.get(k), plan, graphe);
						}
					}
	
					
					// Pour la premi�re tranche, cr�er un chemin depuis l'entrep�t 
					if (i==0) {
						creerChemin(entrepot, pointsLivraison.get(j), plan, graphe);
					}
					
					// Pour la derni�re tranche, cr�er un chemin vers l'entrep�t 
					if (i==tranchesHoraire.length-1) {
						creerChemin(pointsLivraison.get(j), entrepot, plan, graphe);
					}
				}
			} else {
				throw new ExceptionTranchesHorairesNonOrdonees();
			}
		}	
		
		return this;
	}
	  
	  private Chemin creerChemin(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArrivee, Plan plan, AppGraphe graphe){ 
		//Calcule le plus court chemin entre pointLivraisonDepart et pointLivraisonArivee dans G
		  Vector<Troncon> troncons = calculerPlusCourtChemin(pointLivraisonDepart, pointLivraisonArrivee, plan);

		for (int i=0; i<troncons.size(); i++){
			// des trucs
		}
		
		// Cr�er le chemin
		Chemin chemin = null;
		
		return chemin;
	  }

	  private Vector<Troncon> calculerPlusCourtChemin(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArivee, Plan plan){
		  
		  
		  
		  Vector<Troncon> plusCourtChemin = null;
		  
		  
		  return plusCourtChemin;
	  }
}