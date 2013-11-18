package com.h4201.prototype.modele;

import java.util.Vector;

import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionTranchesHorairesNonOrdonees;

public class AppGraphe
{
	private Plan plan;
	private Tournee tournee;
	
	public AppGraphe(Plan plan, Tournee tournee)
	{
		
	}
	
	/**
	 * Génère et crée la tournée optimisée de la journée. Il faut au préalable que le modèle soit entièrement généré
	 * (tranchesHoraires etc.). Ne vérifie pas si les tranches horaires sont respectées 
	 * @param entrepot
	 * @param tranchesHoraire
	 * @return La tournée créée.
	 * @throws ExceptionTranchesHorairesNonOrdonees Si les tranches horaires ne sont pas dans l'ordre, et sans
	 * chevauchement, la tournée n'est pas calculée.
	 * @throws ExceptionNonInstancie Si le plan n'est pas correctement instancié
	 */
	public Tournee GenererTournee(Entrepot entrepot, TrancheHoraire[] tranchesHoraire)
		  throws ExceptionTranchesHorairesNonOrdonees, ExceptionNonInstancie{	
		Plan plan = Plan.getInstance();
		
		// Créer un nouveau Graphe
		Tournee tournee = null;
		
		// Parcourir les tranches horaires de manière ordonnée
		for (int i=0; i<tranchesHoraire.length; i++){
			
			// Vérifier que les tranches horaires du jour sont bien ordonées
			if (i>0 && tranchesHoraire[i-1].getHeureFin().before(tranchesHoraire[i].getHeureDebut())
				|| i<tranchesHoraire.length && tranchesHoraire[i+1].getHeureDebut().after(tranchesHoraire[i].getHeureFin())){
				
				// Parcourir les points de livraison de cette tranche
				Vector<PointLivraison> pointsLivraison = tranchesHoraire[i].getPointsLivraisons();
				for (int j=0; j<pointsLivraison.size(); j++){
					
					// Créer un chemin vers tous les autres points de livraison de la même tranche
					for (int k=0; k<pointsLivraison.size(); k++){
						if (pointsLivraison.get(j) != pointsLivraison.get(k)) {
							creerChemin(pointsLivraison.get(j), pointsLivraison.get(k));
						}
					}
					
					// Créer un chemin vers tous les points de livraison de la tranche suivante (sauf dernière)
					if (i < tranchesHoraire.length-1){
						Vector<PointLivraison> pointsLivraisonTrancheSuivante = tranchesHoraire[i+1].getPointsLivraisons();
						for (int k=0; k<pointsLivraisonTrancheSuivante.size(); k++){
							creerChemin(pointsLivraison.get(j), pointsLivraisonTrancheSuivante.get(k));
						}
					}
	
					
					// Pour la première tranche, créer un chemin depuis l'entrepôt 
					if (i==0) {
						creerChemin(entrepot, pointsLivraison.get(j));
					}
					
					// Pour la dernière tranche, créer un chemin vers l'entrepôt 
					if (i==tranchesHoraire.length-1) {
						creerChemin(pointsLivraison.get(j), entrepot);
					}
				}
			} else {
				throw new ExceptionTranchesHorairesNonOrdonees();
			}
		}	
		
		return tournee;
	}
	  
	/**
	 * Crée l'objet chemin entre pointLivraisonDepart et pointLivraisonArrivee, en y incluant le chemin le plus court.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArrivee
	 * @param plan
	 * @return Le chemin créé.
	 */
	private Chemin creerChemin(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArrivee){ 
		//Calcule le plus court chemin entre pointLivraisonDepart et pointLivraisonArivee dans G
		Vector<Troncon> troncons = calculerPlusCourtChemin(pointLivraisonDepart, pointLivraisonArrivee);
		double longueur = 0;
		double temps = 0;
		
		// Créer le chemin
		//Chemin new chemin(pointLivraisonDepart, pointLivraisonArrivee, troncons, longueur, temps);
		Chemin chemin = null;
		return chemin;
	}

	/**
	 * Implémente l'algorithme de Dijkstra entre pointLivraisonDepart et pointLivraisonArivee.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArivee
	 * @return La liste des Tronçons constituant le plus court chemin entre les 2 points.
	 */
	private Vector<Troncon> calculerPlusCourtChemin(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArivee){
		  
		Vector<Vector<Troncon>> troncons = new Vector<Vector<Troncon>>();
		Vector<Double> tempsTroncons = new Vector<Double>();
		Vector<Troncon> tronconsSortants = new Vector<Troncon>();
		
		int indexTronconActuel = 0;
		Noeud noeudActuel = pointLivraisonDepart.getNoeud();
		Noeud noeudFin = pointLivraisonArivee.getNoeud();

		tronconsSortants = noeudActuel.getTronconsSortants();
		for (int i=0; i<tronconsSortants.size(); i++){
			double temps = tronconsSortants.get(i).calculerTemps();
			
		}
		Vector<Troncon> plusCourtChemin = null;
		return plusCourtChemin;
	}
}





