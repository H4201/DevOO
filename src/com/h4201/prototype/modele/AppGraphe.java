package com.h4201.prototype.modele;

import java.util.Vector;

import com.h4201.prototype.utilitaire.Pair;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionTranchesHorairesNonOrdonees;

public class AppGraphe
{
	private static volatile AppGraphe instance = null;
	private Plan plan;
	private Tournee tournee;
	
	public AppGraphe()
	{
	}
	
	protected final static AppGraphe setInstance() throws ExceptionNonInstancie
	{
		synchronized(Tournee.class)
		{
			AppGraphe.instance = new AppGraphe();
		}
		
		return getInstance();
	}
	  
	public final static AppGraphe getInstance()
	{
	    return AppGraphe.instance;
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
	public void genererTournee()
		  throws ExceptionTranchesHorairesNonOrdonees, ExceptionNonInstancie{
		Tournee tournee = Tournee.getInstance();
		Vector<TrancheHoraire> tranchesHoraire = tournee.getTranchesHoraire();
		Chemin chemin = null;
		
		// Parcourir les tranches horaires de manière ordonnée
		for (int i=0; i<tranchesHoraire.size(); i++){
			
			// Vérifier que les tranches horaires du jour sont bien ordonées
			if (i>0 && tranchesHoraire.get(i-1).getHeureFin().before(tranchesHoraire.get(i).getHeureDebut())
				|| i<tranchesHoraire.size() && tranchesHoraire.get(i+1).getHeureDebut().after(tranchesHoraire.get(i).getHeureFin())){
				
				// Parcourir les points de livraison de cette tranche
				Vector<PointLivraison> pointsLivraison = tranchesHoraire.get(i).getPointsLivraisons();
				for (int j=0; j<pointsLivraison.size(); j++){
					
					// Créer un chemin vers tous les autres points de livraison de la même tranche
					for (int k=0; k<pointsLivraison.size(); k++){
						if (pointsLivraison.get(j) != pointsLivraison.get(k)) {
							chemin = creerChemin(pointsLivraison.get(j), pointsLivraison.get(k));
							tournee.ajouterChemin(chemin);
						}
					}
					
					// Créer un chemin vers tous les points de livraison de la tranche suivante (sauf dernière)
					if (i < tranchesHoraire.size()-1){
						Vector<PointLivraison> pointsLivraisonTrancheSuivante = tranchesHoraire.get(i+1).getPointsLivraisons();
						for (int k=0; k<pointsLivraisonTrancheSuivante.size(); k++){
							chemin = creerChemin(pointsLivraison.get(j), pointsLivraisonTrancheSuivante.get(k));
							tournee.ajouterChemin(chemin);
						}
					}
	
					
					// Pour la première tranche, créer un chemin depuis l'entrepôt 
					if (i==0) {
						chemin = creerChemin(tournee.getEntrepot(), pointsLivraison.get(j));
						tournee.ajouterChemin(chemin);
					}
					
					// Pour la dernière tranche, créer un chemin vers l'entrepôt 
					if (i==tranchesHoraire.size()-1) {
						chemin = creerChemin(pointsLivraison.get(j), tournee.getEntrepot());
						tournee.ajouterChemin(chemin);
					}
				}
			} else {
				throw new ExceptionTranchesHorairesNonOrdonees();
			}
		}	
	}
	  
	/**
	 * Crée l'objet chemin entre pointLivraisonDepart et pointLivraisonArrivee, en y incluant le chemin le plus court.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArrivee
	 * @param plan
	 * @return Le chemin créé.
	 */
	public Chemin creerChemin(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArrivee){ 
		//Calcule le plus court chemin entre pointLivraisonDepart et pointLivraisonArivee dans G
		Vector<Troncon> troncons = calculerPlusCourtChemin(pointLivraisonDepart, pointLivraisonArrivee);
		double longueur = 0;
		double temps = 0;
		
		// Créer le chemin
		Chemin chemin = new Chemin(pointLivraisonDepart, pointLivraisonArrivee, troncons, longueur, temps);
		
		return chemin;
	}

	/**
	 * Implémente l'algorithme de Dijkstra entre pointLivraisonDepart et pointLivraisonArivee.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArivee
	 * @return La liste des Tronçons constituant le plus court chemin entre les 2 points.
	 */
	public Vector<Troncon> calculerPlusCourtChemin(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArivee){
		 
		Vector<Troncon> tronconsSortants = new Vector<Troncon>();
		Vector<Pair<Noeud, Double>> noeudsAccessibles = new Vector<Pair<Noeud, Double>>();
		Vector<Pair<Noeud, Vector<Troncon>>> plusCourtChemins = new Vector<Pair<Noeud, Vector<Troncon>>>();
		
		Pair<Noeud, Double> pairNoeudActuel = new Pair(pointLivraisonDepart.getNoeud(), (double) 0);
		Noeud noeudFin = pointLivraisonArivee.getNoeud();
		noeudsAccessibles.add(pairNoeudActuel);
		plusCourtChemins.add(new Pair(pairNoeudActuel.getFirst(), new Vector<Troncon>()));
		// Critère d'arrêt
		while(pairNoeudActuel.getFirst() != noeudFin){
			pairNoeudActuel.getFirst().afficher(); /////////////////////////////////////
			tronconsSortants = pairNoeudActuel.getFirst().getTronconsSortants();
			double minTemps = Double.MAX_VALUE;
			
			// Parcourir les troncons sortants et ajouter/remplacer les noeuds acessibles
			for (int i=0; i<tronconsSortants.size(); i++){
				System.out.println("3 - ajouter/remplacer les noeuds acessible");
				tronconsSortants.get(i).afficher(); /////////////////////////////////////
				Pair<Noeud, Double> pairNoeudDestination = retournerPairDepuisNoeudAccessibles(noeudsAccessibles, tronconsSortants.get(i).getNoeudDestination());
				double tempsNoeud = Double.MAX_VALUE;
				if(pairNoeudDestination != null){
					tempsNoeud = pairNoeudDestination.getSecond();
				}
				double tempsTroncon = tronconsSortants.get(i).calculerTemps();
				// Cas ou le nouveau chemin est meilleur
				if (pairNoeudActuel.getSecond()+tempsTroncon < tempsNoeud){
					tempsNoeud = pairNoeudActuel.getSecond()+tempsTroncon;
					Vector<Troncon> meilleursTroncons = retournerPairDepuisPlusCourtChemin(plusCourtChemins, pairNoeudActuel.getFirst()).getSecond();
					meilleursTroncons.add(tronconsSortants.get(i));
					AjouterOuRemplacerPlusCourtChemin(plusCourtChemins, pairNoeudActuel.getFirst(), meilleursTroncons);
				}
				noeudsAccessibles = AjouterOuRemplacerNoeudsAccessibles(noeudsAccessibles, tronconsSortants.get(i).getNoeudDestination(), tempsNoeud);
			}

			// Supprimer les noeuds entièrement testés
			if(tronconsSortants.size() == 0){
				SupprimerNoeudsAccessibles(noeudsAccessibles, pairNoeudActuel.getFirst());
			}
			
			// Sélectionne le noeud de poids le plus faible TOCHANGE
			for(int j=0; j<noeudsAccessibles.size(); j++){
				System.out.println("5 - Sélectionne le noeud de poids le plus faible");
				if(noeudsAccessibles.get(j).getSecond() < minTemps){
					pairNoeudActuel = noeudsAccessibles.get(j);
					minTemps = noeudsAccessibles.get(j).getSecond();
				}
			}	
		}

		return retournerPairDepuisPlusCourtChemin(plusCourtChemins, noeudFin).getSecond();
	}
	
	/**
	 * Ajoute ou met à jour le noeud dans noeudsAccessibles.
	 * @param noeudsAccessibles
	 * @param noeud
	 * @param temps
	 * @return l'objet noeudsAccessibles modifié.
	 */
	public Vector<Pair<Noeud, Double>> AjouterOuRemplacerNoeudsAccessibles(Vector<Pair<Noeud, Double>> noeudsAccessibles, Noeud noeud, Double temps) {
		boolean trouve = false;
		System.out.println("7");
		for(int i=0; i<noeudsAccessibles.size(); i++){
			if (noeudsAccessibles.get(i).getFirst() == noeud){
				trouve = true;
				noeudsAccessibles.get(i).setSecond(temps);
				break;
			}
		}
		if(!trouve){
			System.out.println("8");
			noeudsAccessibles.add(new Pair(noeud, temps));
		}
		
		return noeudsAccessibles;
	}
	
	public Vector<Pair<Noeud, Double>> SupprimerNoeudsAccessibles(Vector<Pair<Noeud, Double>> noeudsAccessibles, Noeud noeud){
		for(int i=0; i<noeudsAccessibles.size(); i++){
			if (noeudsAccessibles.get(i).getFirst() == noeud){
				noeudsAccessibles.remove(i);
				break;
			}
		}
		
		return noeudsAccessibles;
	}
	
	/**
	 * Ajoute ou met à jour le noeud dans plusCourtChemin.
	 * @param noeudsAccessibles
	 * @param noeud
	 * @param temps
	 * @return l'objet plusCourtChemin modifié.
	 */
	public Vector<Pair<Noeud, Vector<Troncon>>> AjouterOuRemplacerPlusCourtChemin(Vector<Pair<Noeud, Vector<Troncon>>> plusCourtChemins, Noeud noeud, Vector<Troncon> troncons) {
		boolean trouve = false;
		for(int i=0; i<plusCourtChemins.size(); i++){
			if (plusCourtChemins.get(i).getFirst() == noeud){
				trouve = true;
				plusCourtChemins.get(i).setSecond(troncons);
				break;
			}
		}
		if(!trouve){
			plusCourtChemins.add(new Pair(noeud, troncons));
		}
		
		return plusCourtChemins;
	}
	
	/**
	 * Retourne la paire en prenant le noeud comme argument pour noeudsAccessibles.
	 * @param noeudsAccessibles
	 * @param noeud
	 * @return la paire en prenant le noeud comme argument.
	 */
	public Pair<Noeud, Double> retournerPairDepuisNoeudAccessibles(Vector<Pair<Noeud, Double>> noeudsAccessibles, Noeud noeud){
		Pair<Noeud, Double> pair = null;
		for(int i=0; i<noeudsAccessibles.size(); i++){
			if (noeudsAccessibles.get(i).getFirst() == noeud){
				pair = noeudsAccessibles.get(i);
				break;
			}
		}
		
		return pair;
	}
	
	/**
	 * Retourne la paire en prenant le noeud comme argument pour plusCourtChemins.
	 * @param plusCourtChemins
	 * @param noeud
	 * @return la paire en prenant le noeud comme argument.
	 */
	public Pair<Noeud, Vector<Troncon>> retournerPairDepuisPlusCourtChemin(Vector<Pair<Noeud, Vector<Troncon>>> plusCourtChemins, Noeud noeud){
		Pair<Noeud, Vector<Troncon>> pair = null;
		for(int i=0; i<plusCourtChemins.size(); i++){
			if (plusCourtChemins.get(i).getFirst() == noeud){
				pair = plusCourtChemins.get(i);
				break;
			}
		}
		
		return pair;
	}
}





