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
	 * G�n�re et cr�e la tourn�e optimis�e de la journ�e. Il faut au pr�alable que le mod�le soit enti�rement g�n�r�
	 * (tranchesHoraires etc.). Ne v�rifie pas si les tranches horaires sont respect�es 
	 * @param entrepot
	 * @param tranchesHoraire
	 * @return La tourn�e cr��e.
	 * @throws ExceptionTranchesHorairesNonOrdonees Si les tranches horaires ne sont pas dans l'ordre, et sans
	 * chevauchement, la tourn�e n'est pas calcul�e.
	 * @throws ExceptionNonInstancie Si le plan n'est pas correctement instanci�
	 */
	public Tournee GenererTournee(Entrepot entrepot, TrancheHoraire[] tranchesHoraire)
		  throws ExceptionTranchesHorairesNonOrdonees, ExceptionNonInstancie{	
		Plan plan = Plan.getInstance();
		
		// Cr�er un nouveau Graphe
		Tournee tournee = null;
		
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
							creerChemin(pointsLivraison.get(j), pointsLivraison.get(k));
						}
					}
					
					// Cr�er un chemin vers tous les points de livraison de la tranche suivante (sauf derni�re)
					if (i < tranchesHoraire.length-1){
						Vector<PointLivraison> pointsLivraisonTrancheSuivante = tranchesHoraire[i+1].getPointsLivraisons();
						for (int k=0; k<pointsLivraisonTrancheSuivante.size(); k++){
							creerChemin(pointsLivraison.get(j), pointsLivraisonTrancheSuivante.get(k));
						}
					}
	
					
					// Pour la premi�re tranche, cr�er un chemin depuis l'entrep�t 
					if (i==0) {
						creerChemin(entrepot, pointsLivraison.get(j));
					}
					
					// Pour la derni�re tranche, cr�er un chemin vers l'entrep�t 
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
	 * Cr�e l'objet chemin entre pointLivraisonDepart et pointLivraisonArrivee, en y incluant le chemin le plus court.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArrivee
	 * @param plan
	 * @return Le chemin cr��.
	 */
	private Chemin creerChemin(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArrivee){ 
		//Calcule le plus court chemin entre pointLivraisonDepart et pointLivraisonArivee dans G
		Vector<Troncon> troncons = calculerPlusCourtChemin(pointLivraisonDepart, pointLivraisonArrivee);
		double longueur = 0;
		double temps = 0;
		
		// Cr�er le chemin
		//Chemin new chemin(pointLivraisonDepart, pointLivraisonArrivee, troncons, longueur, temps);
		Chemin chemin = null;
		return chemin;
	}

	/**
	 * Impl�mente l'algorithme de Dijkstra entre pointLivraisonDepart et pointLivraisonArivee.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArivee
	 * @return La liste des Tron�ons constituant le plus court chemin entre les 2 points.
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





