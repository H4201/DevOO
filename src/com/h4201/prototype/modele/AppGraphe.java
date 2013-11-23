package com.h4201.prototype.modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import com.h4201.prototype.utilitaire.Pair;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionTranchesHorairesNonOrdonees;

import fr.insa.lyon.if4.tsp.Graph;
import fr.insa.lyon.if4.tsp.TSP;
import fr.insa.lyon.if4.tsp.SolutionState;

public class AppGraphe implements Graph {

	private static volatile AppGraphe instance = null;
	
	private int nbVertices;
	private int maxArcCost;
	private int minArcCost;
	private int[][] cost; 
	private ArrayList<ArrayList<Integer>> succ; 
	
	private AppGraphe()
	{
		nbVertices = 0;
		maxArcCost = 0;
		minArcCost = Integer.MAX_VALUE;
	}
	
	public final static AppGraphe getInstance() {
		if (AppGraphe.instance == null){
			synchronized(AppGraphe.class){
				if (AppGraphe.instance == null){
					AppGraphe.instance = new AppGraphe();
				}
			}
		}
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
			if (i>0 && (tranchesHoraire.get(i-1).getHeureFin().before(tranchesHoraire.get(i).getHeureDebut()) || tranchesHoraire.get(i-1).getHeureFin().equals(tranchesHoraire.get(i).getHeureDebut()))
				|| i<tranchesHoraire.size() && (tranchesHoraire.get(i+1).getHeureDebut().after(tranchesHoraire.get(i).getHeureFin()) || tranchesHoraire.get(i+1).getHeureDebut().equals(tranchesHoraire.get(i).getHeureFin())) ){
				
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
		
		// Calculer nbVertices
		Vector<Chemin> chemins = tournee.getChemins();
		Vector<Pair<PointLivraison, Integer>> match = new Vector<Pair<PointLivraison, Integer>>(); // Permet de faire le lien entre les chemins et leur place dans le tableau de coûts
		for(int i = 0; i<chemins.size(); i++){
			int sizeMatch = match.size();
			AjouterOuRemplacerMatch(match, chemins.get(i).getPointLivraisonOrigine(), nbVertices-1);
			if (sizeMatch != match.size()){
				nbVertices++;
			}
			sizeMatch = match.size();
			AjouterOuRemplacerMatch(match, chemins.get(i).getPointLivraisonDestination(), nbVertices-1);
			if (sizeMatch != match.size()){
				nbVertices++;
			}
		}
		
		// Remplir matrice des couts
		cost = new int[nbVertices][nbVertices];
		for(int i=0; i<nbVertices; i++){
			Arrays.fill(cost[i], maxArcCost+1);
		}
		for(int i = 0; i<chemins.size(); i++){
			Integer positionXdansMatch = retournerPairDepuisMatch(match, chemins.get(i).getPointLivraisonOrigine()).getSecond();
			Integer positionYdansMatch = retournerPairDepuisMatch(match, chemins.get(i).getPointLivraisonDestination()).getSecond();
			cost[positionXdansMatch][positionYdansMatch] = (int) chemins.get(i).getTemps();
		}
		
		// Remplir le vecteur succ
		//ArrayList<ArrayList<Integer>>
		PointLivraison[] PointLivraisonOrdonnes = new PointLivraison[nbVertices];
		for(int i=0; i<nbVertices; i++){
			PointLivraisonOrdonnes[match.get(i).getSecond()] = match.get(i).getFirst();
		}
		for(int i=0; i<nbVertices; i++){
			//for(){
				
			//}
		}
		
					
		// Passer le graphe à la classe TSP
		TSP tsp = new TSP(this);
		SolutionState solutionState = tsp.solve(10000, 1000000);
		System.out.println("Solution state : " + solutionState);
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
		int longueur = 0;
		int temps = 0;
		
		for (int i=0; i<troncons.size(); i++){
			longueur += troncons.get(i).getLongueur();
			temps += troncons.get(i).getLongueur()/troncons.get(i).getVitesse();
		}
		
		// Créer le chemin
		Chemin chemin = new Chemin(pointLivraisonDepart, pointLivraisonArrivee, troncons, longueur, temps);
		
		if(temps > maxArcCost){
			maxArcCost = temps;
		}
		if(temps < minArcCost){
			minArcCost = temps;
		}
		
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
		Vector<Pair<Noeud, Integer>> noeudsAccessibles = new Vector<Pair<Noeud, Integer>>();
		Vector<Noeud> noeudsParcourus = new Vector<Noeud>();
		Vector<Pair<Noeud, Vector<Troncon>>> plusCourtChemins = new Vector<Pair<Noeud, Vector<Troncon>>>();
		
		Pair<Noeud, Integer> pairNoeudActuel = new Pair<Noeud, Integer>(pointLivraisonDepart.getNoeud(), (int) 0);
		Noeud noeudFin = pointLivraisonArivee.getNoeud();
		noeudsAccessibles.add(pairNoeudActuel);
		plusCourtChemins.add(new Pair<Noeud, Vector<Troncon>>(pairNoeudActuel.getFirst(), new Vector<Troncon>()));
		// Critère d'arrêt
		while(pairNoeudActuel.getFirst() != noeudFin){
			//System.out.println("BOUCLE NOEUD");
			//pairNoeudActuel.getFirst().afficher(); /////////////////////////////////////
			tronconsSortants = pairNoeudActuel.getFirst().getTronconsSortants();
			int minTemps = Integer.MAX_VALUE;
			
			// Parcourir les troncons sortants et ajouter/remplacer les noeuds acessibles
			for (int i=0; i<tronconsSortants.size(); i++){
				if(!noeudsParcourus.contains(tronconsSortants.get(i).getNoeudDestination())){ // Pas de retour en arrière
					//System.out.println("3 - ajouter/remplacer les noeuds acessible");
					Pair<Noeud, Integer> pairNoeudDestination = retournerPairDepuisNoeudAccessibles(noeudsAccessibles, tronconsSortants.get(i).getNoeudDestination());
					int tempsNoeud = Integer.MAX_VALUE;
					if(pairNoeudDestination != null){
						tempsNoeud = pairNoeudDestination.getSecond();
					}
					int tempsTroncon = (int) tronconsSortants.get(i).calculerTemps();
					// Cas ou le nouveau chemin est meilleur
					if (pairNoeudActuel.getSecond()+tempsTroncon < tempsNoeud){
						tempsNoeud = pairNoeudActuel.getSecond()+tempsTroncon;
						
						Vector<Troncon> meilleursTroncons = (Vector<Troncon>) retournerPairDepuisPlusCourtChemin(plusCourtChemins, pairNoeudActuel.getFirst()).getSecond().clone();
						
						if (meilleursTroncons.size() > 0 && tronconsSortants.get(i).getNoeudOrigine() == meilleursTroncons.lastElement().getNoeudOrigine()) {
							meilleursTroncons.remove(meilleursTroncons.size()-1);
						}	
						
						meilleursTroncons.add(tronconsSortants.get(i));
						
						//System.out.println("Meilleurs troncons ");
						//if (meilleursTroncons.size() >1){
						//	meilleursTroncons.get(meilleursTroncons.size()-2).afficher(); /////////////////////////////////////
						//}
						//if (meilleursTroncons.size() >0){
						//	meilleursTroncons.get(meilleursTroncons.size()-1).afficher(); /////////////////////////////////////
						//}
						AjouterOuRemplacerPlusCourtChemin(plusCourtChemins, tronconsSortants.get(i).getNoeudDestination(), meilleursTroncons);
					}

					noeudsAccessibles = AjouterOuRemplacerNoeudsAccessibles(noeudsAccessibles, tronconsSortants.get(i).getNoeudDestination(), tempsNoeud);
				}
			}

			// Retirer le noeud de la liste à parcourir
			noeudsParcourus.add(pairNoeudActuel.getFirst());
			noeudsAccessibles.remove(pairNoeudActuel);
			
			// Sélectionne le noeud de poids le plus faible
			for(int i=0; i<noeudsAccessibles.size(); i++){
				if(noeudsAccessibles.get(i).getSecond() < minTemps){
					pairNoeudActuel = noeudsAccessibles.get(i);
					minTemps = noeudsAccessibles.get(i).getSecond();
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
	public Vector<Pair<Noeud, Integer>> AjouterOuRemplacerNoeudsAccessibles(Vector<Pair<Noeud, Integer>> noeudsAccessibles, Noeud noeud, Integer temps) {
		boolean trouve = false;
		for(int i=0; i<noeudsAccessibles.size(); i++){
			if (noeudsAccessibles.get(i).getFirst().getIdNoeud() == noeud.getIdNoeud()){
				trouve = true;
				noeudsAccessibles.get(i).setSecond(temps);
				break;
			}
		}
		if(!trouve){
			noeudsAccessibles.add(new Pair<Noeud, Integer>(noeud, temps));
		}
		
		return noeudsAccessibles;
	}
	
	public Vector<Pair<Noeud, Integer>> SupprimerNoeudsAccessibles(Vector<Pair<Noeud, Integer>> noeudsAccessibles, Noeud noeud){
		for(int i=0; i<noeudsAccessibles.size(); i++){
			if (noeudsAccessibles.get(i).getFirst().getIdNoeud() == noeud.getIdNoeud()){
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
			if (plusCourtChemins.get(i).getFirst().getIdNoeud() == noeud.getIdNoeud()){
				trouve = true;
				plusCourtChemins.get(i).setSecond(troncons);
				break;
			}
		}
		if(!trouve){
			plusCourtChemins.add(new Pair<Noeud, Vector<Troncon>>(noeud, troncons));
		}
		
		return plusCourtChemins;
	}
	
	/**
	 * Ajoute ou met à jour le noeud dans Match.
	 * @param match
	 * @param pointLivraison
	 * @param position
	 * @return l'objet match modifié
	 */
	public Vector<Pair<PointLivraison, Integer>> AjouterOuRemplacerMatch(Vector<Pair<PointLivraison, Integer>> match, PointLivraison pointLivraison, Integer position) {
		boolean trouve = false;
		for(int i=0; i<match.size(); i++){
			if (match.get(i).getFirst().getIdPointLivraison() == pointLivraison.getIdPointLivraison()){
				trouve = true;
				match.get(i).setSecond(position);
				break;
			}
		}
		if(!trouve){
			match.add(new Pair<PointLivraison, Integer>(pointLivraison, position));
		}
		
		return match;
	}
	
	/**
	 * Retourne la paire en prenant le noeud comme argument pour noeudsAccessibles.
	 * @param noeudsAccessibles
	 * @param noeud
	 * @return la paire en prenant le noeud comme argument.
	 */
	public Pair<Noeud, Integer> retournerPairDepuisNoeudAccessibles(Vector<Pair<Noeud, Integer>> noeudsAccessibles, Noeud noeud){
		Pair<Noeud, Integer> pair = null;
		for(int i=0; i<noeudsAccessibles.size(); i++){
			if (noeudsAccessibles.get(i).getFirst().getIdNoeud() == noeud.getIdNoeud()){
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
			if (plusCourtChemins.get(i).getFirst().getIdNoeud() == noeud.getIdNoeud()){
				pair = plusCourtChemins.get(i);
				break;
			}
		}
		
		return pair;
	}
	
	/**
	 * Retourne la paire en prenant le pointdelivraison comme argument pour match.
	 * @param plusCourtChemins
	 * @param noeud
	 * @return la paire en prenant le noeud comme argument.
	 */
	public Pair<PointLivraison, Integer> retournerPairDepuisMatch(Vector<Pair<PointLivraison, Integer>> match, PointLivraison pointLivraison){
		System.out.println("RetournerPaire depuis match : ");
		pointLivraison.afficher();
		Pair<PointLivraison, Integer> pair = null;
		for(int i=0; i<match.size(); i++){
			if (match.get(i).getFirst().getIdPointLivraison() == pointLivraison.getIdPointLivraison()){
				pair = match.get(i);
				break;
			}
		}
		
		return pair;
	}
	
	public int getMaxArcCost() {
		return maxArcCost;
	}

	public int getMinArcCost() {
		return minArcCost;
	}

	public int getNbVertices() {
		return nbVertices;
	}

	public int[][] getCost(){
		return cost;
	}

	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException{
		if ((i<0) || (i>=nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		
		int nb = getNbSucc(i);
		int counter = 0;
		int[] succTab = new int[nb];
		for(int j=0; j<nb; j++){
			if (cost[i][j] <= maxArcCost){
				succTab[counter] = j;
				counter++;
			}
		}
		
		return succTab;
	}


	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		if ((i<0) || (i>=nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		
		int nb = 0;
		for(int j=0; j<nbVertices; j++){
			if (cost[i][j] <= maxArcCost){
				nb++;
			}
		}
		return nb;
	}
	
	
}





