package com.h4201.prototype.modele;

import java.util.Vector;

import com.h4201.prototype.utilitaire.Pair;
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
		Chemin new chemin(pointLivraisonDepart, pointLivraisonArrivee, troncons, longueur, temps);
		
		return chemin;
	}

	/**
	 * Implémente l'algorithme de Dijkstra entre pointLivraisonDepart et pointLivraisonArivee.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArivee
	 * @return La liste des Tronçons constituant le plus court chemin entre les 2 points.
	 */
	private Vector<Troncon> calculerPlusCourtChemin(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArivee){
		 
		Vector<Pair<Vector<Troncon>, Double>> listeTroncons = new Vector<Pair<Vector<Troncon>, Double>>();
		Vector<Troncon> listeTronconsActuelle = new Vector<Troncon>();
		Vector<Troncon> tronconsSortants = new Vector<Troncon>();
		Vector<Troncon> tronconsParcourus = new Vector<Troncon>();
		Vector<Pair<Noeud, Double>> noeudsAccessibles = new Vector<Pair<Noeud, Double>>();
		
		listeTroncons.add(new Pair(new Vector<Troncon>(), 0));
		int indexListeTronconsActuelle = 0;
		Pair<Noeud, Double> noeudActuel = new Pair(pointLivraisonDepart.getNoeud(), 0);
		Noeud noeudFin = pointLivraisonArivee.getNoeud();
		
		while(noeudActuel.getFirst() != noeudFin){
			listeTronconsActuelle = listeTroncons.get(indexListeTronconsActuelle).getFirst();
			tronconsSortants = noeudActuel.getFirst().getTronconsSortants();
			double minTemps = Double.MAX_VALUE;
			
			// Supprimer les troncons parcourus
			for(int i=0; i<tronconsParcourus.size(); i++){
				tronconsSortants.remove(tronconsParcourus.get(i));
			}
			
			// Ajouter/remplacer les noeuds acessibles
			for (int i=0; i<tronconsSortants.size(); i++){
				double temps = tronconsSortants.get(i).calculerTemps();
				boolean aEteRemplace = false;
				for(int j=0; j<noeudsAccessibles.size(); j++){
					if(noeudsAccessibles.get(i).getFirst() == tronconsSortants.get(i).getNoeudDestination()){
						aEteRemplace = true;
						// Affecter le nouveau poids s'il est plus petit
						noeudsAccessibles.get(i).setSecond(Math.min(noeudsAccessibles.get(i).getSecond(), noeudActuel.getSecond()+temps));
						break;
					}
				}
				if(!aEteRemplace){
					noeudsAccessibles.add(new Pair(tronconsSortants.get(i).getNoeudDestination(), listeTroncons.get(indexListeTronconsActuelle).getSecond()+temps));
				}
				
				// Sélectionne le noeud de poids le plus faible
				for(int j=0; j<noeudsAccessibles.size(); j++){
					if(noeudsAccessibles.get(j).getSecond() < minTemps){
						noeudActuel = noeudsAccessibles.get(j);
						minTemps = noeudsAccessibles.get(j).getSecond();
					}
				}	
			}

			// Supprimer les noeuds entièrement testés
			if(tronconsSortants.size() == 0){
				for(int i=0; i<noeudsAccessibles.size(); i++){
					if (noeudsAccessibles.get(i).getFirst() == noeudActuel.getFirst()){
						noeudsAccessibles.remove(i);
					}else if(noeudsAccessibles.get(i).getSecond() < minTemps){
						noeudActuel = noeudsAccessibles.get(i);
						minTemps = noeudsAccessibles.get(i).getSecond();
					}
				}	
			}
					
		}

		return listeTronconsActuelle;
	}
}





