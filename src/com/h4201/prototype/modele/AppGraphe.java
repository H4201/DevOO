package com.h4201.prototype.modele;

import java.util.ArrayList;
import java.util.Vector;

import com.h4201.prototype.utilitaire.Pair;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionTranchesHorairesNonOrdonees;

import fr.insa.lyon.if4.tsp.Graph;
import fr.insa.lyon.if4.tsp.TSP;

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
	 * G�n�re et cr�e la tourn�e optimis�e de la journ�e. Il faut au pr�alable que le mod�le soit enti�rement g�n�r�
	 * (tranchesHoraires etc.). Ne v�rifie pas si les tranches horaires sont respect�es 
	 * @param entrepot
	 * @param tranchesHoraire
	 * @return La tourn�e cr��e.
	 * @throws ExceptionTranchesHorairesNonOrdonees Si les tranches horaires ne sont pas dans l'ordre, et sans
	 * chevauchement, la tourn�e n'est pas calcul�e.
	 * @throws ExceptionNonInstancie Si le plan n'est pas correctement instanci�
	 */
	public void genererTournee()
		  throws ExceptionTranchesHorairesNonOrdonees, ExceptionNonInstancie{
		Tournee tournee = Tournee.getInstance();
		Vector<TrancheHoraire> tranchesHoraire = tournee.getTranchesHoraire();
		Chemin chemin = null;
		
		// Parcourir les tranches horaires de mani�re ordonn�e
		for (int i=0; i<tranchesHoraire.size(); i++){
			
			// V�rifier que les tranches horaires du jour sont bien ordon�es
			if (i>0 && tranchesHoraire.get(i-1).getHeureFin().before(tranchesHoraire.get(i).getHeureDebut())
				|| i<tranchesHoraire.size() && tranchesHoraire.get(i+1).getHeureDebut().after(tranchesHoraire.get(i).getHeureFin())){
				
				// Parcourir les points de livraison de cette tranche
				Vector<PointLivraison> pointsLivraison = tranchesHoraire.get(i).getPointsLivraisons();
				for (int j=0; j<pointsLivraison.size(); j++){
					
					// Cr�er un chemin vers tous les autres points de livraison de la m�me tranche
					for (int k=0; k<pointsLivraison.size(); k++){
						if (pointsLivraison.get(j) != pointsLivraison.get(k)) {
							chemin = creerChemin(pointsLivraison.get(j), pointsLivraison.get(k));
							tournee.ajouterChemin(chemin);
						}
					}
					
					// Cr�er un chemin vers tous les points de livraison de la tranche suivante (sauf derni�re)
					if (i < tranchesHoraire.size()-1){
						Vector<PointLivraison> pointsLivraisonTrancheSuivante = tranchesHoraire.get(i+1).getPointsLivraisons();
						for (int k=0; k<pointsLivraisonTrancheSuivante.size(); k++){
							chemin = creerChemin(pointsLivraison.get(j), pointsLivraisonTrancheSuivante.get(k));
							tournee.ajouterChemin(chemin);
						}
					}
	
					
					// Pour la premi�re tranche, cr�er un chemin depuis l'entrep�t 
					if (i==0) {
						chemin = creerChemin(tournee.getEntrepot(), pointsLivraison.get(j));
						tournee.ajouterChemin(chemin);
					}
					
					// Pour la derni�re tranche, cr�er un chemin vers l'entrep�t 
					if (i==tranchesHoraire.size()-1) {
						chemin = creerChemin(pointsLivraison.get(j), tournee.getEntrepot());
						tournee.ajouterChemin(chemin);
					}
				}
			} else {
				throw new ExceptionTranchesHorairesNonOrdonees();
			}
		}
		
		// Remplir la matrice des co�ts
		
		
			
		// Passer le graphe � la classe TSP
		TSP tsp = new TSP(this);
	}
	  
	/**
	 * Cr�e l'objet chemin entre pointLivraisonDepart et pointLivraisonArrivee, en y incluant le chemin le plus court.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArrivee
	 * @param plan
	 * @return Le chemin cr��.
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
		
		// Cr�er le chemin
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
	 * Impl�mente l'algorithme de Dijkstra entre pointLivraisonDepart et pointLivraisonArivee.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArivee
	 * @return La liste des Tron�ons constituant le plus court chemin entre les 2 points.
	 */
	public Vector<Troncon> calculerPlusCourtChemin(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArivee){
		 
		Vector<Troncon> tronconsSortants = new Vector<Troncon>();
		Vector<Pair<Noeud, Double>> noeudsAccessibles = new Vector<Pair<Noeud, Double>>();
		Vector<Noeud> noeudsParcourus = new Vector<Noeud>();
		Vector<Pair<Noeud, Vector<Troncon>>> plusCourtChemins = new Vector<Pair<Noeud, Vector<Troncon>>>();
		
		Pair<Noeud, Double> pairNoeudActuel = new Pair<Noeud, Double>(pointLivraisonDepart.getNoeud(), (double) 0);
		Noeud noeudFin = pointLivraisonArivee.getNoeud();
		noeudsAccessibles.add(pairNoeudActuel);
		plusCourtChemins.add(new Pair<Noeud, Vector<Troncon>>(pairNoeudActuel.getFirst(), new Vector<Troncon>()));
		// Crit�re d'arr�t
		while(pairNoeudActuel.getFirst() != noeudFin){
		//for(int a=0; a<3; a++){
			System.out.println("BOUCLE NOEUD");
			pairNoeudActuel.getFirst().afficher(); /////////////////////////////////////
			tronconsSortants = pairNoeudActuel.getFirst().getTronconsSortants();
			double minTemps = Double.MAX_VALUE;
			
			// Parcourir les troncons sortants et ajouter/remplacer les noeuds acessibles
			for (int i=0; i<tronconsSortants.size(); i++){
				if(!noeudsParcourus.contains(tronconsSortants.get(i).getNoeudDestination())){ // Pas de retour en arri�re
					System.out.println("3 - ajouter/remplacer les noeuds acessible");
					Pair<Noeud, Double> pairNoeudDestination = retournerPairDepuisNoeudAccessibles(noeudsAccessibles, tronconsSortants.get(i).getNoeudDestination());
					double tempsNoeud = Double.MAX_VALUE;
					if(pairNoeudDestination != null){
						tempsNoeud = pairNoeudDestination.getSecond();
					}
					double tempsTroncon = tronconsSortants.get(i).calculerTemps();
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

			// Retirer le noeud de la liste � parcourir
			noeudsParcourus.add(pairNoeudActuel.getFirst());
			noeudsAccessibles.remove(pairNoeudActuel);
			
			// S�lectionne le noeud de poids le plus faible
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
	 * Ajoute ou met � jour le noeud dans noeudsAccessibles.
	 * @param noeudsAccessibles
	 * @param noeud
	 * @param temps
	 * @return l'objet noeudsAccessibles modifi�.
	 */
	public Vector<Pair<Noeud, Double>> AjouterOuRemplacerNoeudsAccessibles(Vector<Pair<Noeud, Double>> noeudsAccessibles, Noeud noeud, Double temps) {
		boolean trouve = false;
		for(int i=0; i<noeudsAccessibles.size(); i++){
			if (noeudsAccessibles.get(i).getFirst().getIdNoeud() == noeud.getIdNoeud()){
				trouve = true;
				noeudsAccessibles.get(i).setSecond(temps);
				break;
			}
		}
		if(!trouve){
			noeudsAccessibles.add(new Pair<Noeud, Double>(noeud, temps));
		}
		
		return noeudsAccessibles;
	}
	
	public Vector<Pair<Noeud, Double>> SupprimerNoeudsAccessibles(Vector<Pair<Noeud, Double>> noeudsAccessibles, Noeud noeud){
		for(int i=0; i<noeudsAccessibles.size(); i++){
			if (noeudsAccessibles.get(i).getFirst().getIdNoeud() == noeud.getIdNoeud()){
				noeudsAccessibles.remove(i);
				break;
			}
		}
		
		return noeudsAccessibles;
	}
	
	/**
	 * Ajoute ou met � jour le noeud dans plusCourtChemin.
	 * @param noeudsAccessibles
	 * @param noeud
	 * @param temps
	 * @return l'objet plusCourtChemin modifi�.
	 */
	public Vector<Pair<Noeud, Vector<Troncon>>> AjouterOuRemplacerPlusCourtChemin(Vector<Pair<Noeud, Vector<Troncon>>> plusCourtChemins, Noeud noeud, Vector<Troncon> troncons) {
		boolean trouve = false;
		System.out.println("modifier plusCourtChemins");
		for(int i=0; i<plusCourtChemins.size(); i++){
			if (plusCourtChemins.get(i).getFirst().getIdNoeud() == noeud.getIdNoeud()){
				trouve = true;
				System.out.println("REMPLACEMENT d'un chemein existant pour :");
				noeud.afficher(); /////////////////////////////////////
				plusCourtChemins.get(i).setSecond(troncons);
				break;
			}
		}
		if(!trouve){
			System.out.println("AJOUT d'un chemin existant pour :");
			noeud.afficher(); /////////////////////////////////////
			plusCourtChemins.add(new Pair<Noeud, Vector<Troncon>>(noeud, troncons));
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
		int[] tab = new int[succ.get(i).size()];
		for(int j=0;j<tab.length;j++){
			tab[j] = succ.get(i).get(j);
		}
		return tab;
	}


	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		if ((i<0) || (i>=nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		return succ.get(i).size();
	}
	
}





