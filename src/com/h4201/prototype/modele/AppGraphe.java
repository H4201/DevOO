package com.h4201.prototype.modele;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Vector;

import com.h4201.prototype.utilitaire.Constante;
import com.h4201.prototype.utilitaire.Pair;
import com.h4201.prototype.exception.ExceptionNonInstancie;
import com.h4201.prototype.exception.ExceptionTrancheHoraire;

import fr.insa.lyon.if4.tsp.Graph;
import fr.insa.lyon.if4.tsp.TSP;
import fr.insa.lyon.if4.tsp.SolutionState;

public class AppGraphe implements Graph {

	private static volatile AppGraphe instance = null;

	private int nbVertices;
	private int maxArcCost;
	private int minArcCost;
	private int[][] cost;

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
	 * Genere et cree la tournee optimisee de la journee. Il faut au prealable que le modele soit entierement genere
	 * (tranchesHoraires etc.). Ne verifie pas si les tranches horaires sont respectees
	 * @param entrepot
	 * @param tranchesHoraire
	 * @return La tournee creee.
	 * @throws ExceptionTrancheHoraire Si les tranches horaires ne sont pas dans l'ordre, et sans
	 * chevauchement, la tournee n'est pas calculee.
	 * @throws ExceptionNonInstancie Si le plan n'est pas correctement instancie
	 */
	public void genererTournee()
			throws ExceptionNonInstancie{
		nbVertices = 0;
		maxArcCost = 0;
		minArcCost = Integer.MAX_VALUE;
		Tournee tournee = Tournee.getInstance();
		Vector<Chemin> chemins = new Vector<Chemin>();
		Vector<TrancheHoraire> tranchesHoraire = tournee.getTranchesHoraire();

		// Parcourir les tranches horaires de maniere ordonnee
		for (int i=0; i<tranchesHoraire.size(); i++){
			// Parcourir les points de livraison de cette tranche
			Vector<PointLivraison> pointsLivraison = tranchesHoraire.get(i).getPointsLivraisons();
			for (int j=0; j<pointsLivraison.size(); j++){

				// Cr�er un chemin vers tous les autres points de livraison de la m�me tranche
				for (int k=0; k<pointsLivraison.size(); k++){
					if (pointsLivraison.get(j) != pointsLivraison.get(k)) {
						chemins.add(creerChemin(pointsLivraison.get(j), pointsLivraison.get(k)));
					}
				}

				// Cr�er un chemin vers tous les points de livraison de la tranche suivante (sauf derni�re)
				if (i < tranchesHoraire.size()-1){
					Vector<PointLivraison> pointsLivraisonTrancheSuivante = tranchesHoraire.get(i+1).getPointsLivraisons();
					for (int k=0; k<pointsLivraisonTrancheSuivante.size(); k++){
						chemins.add(creerChemin(pointsLivraison.get(j), pointsLivraisonTrancheSuivante.get(k)));
					}
				}

				// Pour la premi�re tranche, cr�er un chemin depuis l'entrep�t
				if (i==0) {
					chemins.add(creerChemin(tournee.getEntrepot(), pointsLivraison.get(j)));
				}

				// Pour la derni�re tranche, cr�er un chemin vers l'entrep�t
				if (i==tranchesHoraire.size()-1) {
					chemins.add(creerChemin(pointsLivraison.get(j), tournee.getEntrepot()));
				}
			}
		}

		// Calculer nbVertices
		Vector<Pair<PointLivraison, Integer>> match = new Vector<Pair<PointLivraison, Integer>>(); // Permet de faire le lien entre les chemins et leur place dans le tableau de coets
		for(int i = 0; i<chemins.size(); i++){
			int sizeMatch = match.size();
			AjouterMatch(match, chemins.get(i).getPointLivraisonOrigine(), nbVertices);
			if (sizeMatch != match.size()){
				nbVertices++;
			}
			sizeMatch = match.size();
			AjouterMatch(match, chemins.get(i).getPointLivraisonDestination(), nbVertices);
			if (sizeMatch != match.size()){
				nbVertices++;
			}
		}
//		System.out.println("nbVertices : " + nbVertices); // DEBUG

		// Remplir matrice des couts
		cost = new int[nbVertices][nbVertices];
		for(int i=0; i<nbVertices; i++){
			Arrays.fill(cost[i], maxArcCost+1);
		}
		for(int i=0; i<chemins.size(); i++){
			Integer positionXdansMatch = retournerPairDepuisMatch(match, chemins.get(i).getPointLivraisonOrigine()).getSecond();
			Integer positionYdansMatch = retournerPairDepuisMatch(match, chemins.get(i).getPointLivraisonDestination()).getSecond();

			// DEBUG : Afficher les chemins dans chemin
//			System.out.println(positionXdansMatch+"->"+positionYdansMatch + " " + chemins.get(i).getTemps() + " s");
//			System.out.println(retournerPointLivraisonDepuisPositionMatchMatch(match, positionXdansMatch).getIdPointLivraison()+"->"+retournerPointLivraisonDepuisPositionMatchMatch(match, positionYdansMatch).getIdPointLivraison() + " " + chemins.get(i).getTemps() + " s");

			cost[positionXdansMatch][positionYdansMatch] = (int) chemins.get(i).getTemps();
		}

		//DEBUG : AFFICHER COUT
		//for(int i=0; i<nbVertices; i++){
		//        System.out.println( i + " : " + cost[i][0] + " " + cost[i][1] + " " + cost[i][2] +
		//                        " " + cost[i][3] + " " + cost[i][4] + " " + cost[i][5] + " " + cost[i][6]
		//                                        + " " + cost[i][7] + " " + cost[i][8] + " " + cost[i][9] + " " +
		//                        cost[i][10] + " " + cost[i][11]);
		//}


		// Remplir le vecteur PointLivraisonOrdonnes
		PointLivraison[] PointLivraisonOrdonnes = new PointLivraison[nbVertices];
		for(int i=0; i<nbVertices; i++){
			PointLivraisonOrdonnes[match.get(i).getSecond()] = match.get(i).getFirst();
		}

		//DEBUG : AFFICHER getNbSucc
//		for(int i=0; i<nbVertices; i++){
//			System.out.println("getNbSucc : " + i+ " " + getNbSucc(i));
//			int[] succd = getSucc(i);
//			for(int j=0; j<succd.length; j++)
//				System.out.println("Succ : " + succd[j]);
//		}


		// Passer le graphe a la classe TSP
		TSP tsp = new TSP(this);
		SolutionState solutionState = tsp.solve(10000, 1000000);
//		System.out.println("Solution state : " + solutionState);

		if (solutionState.equals(SolutionState.OPTIMAL_SOLUTION_FOUND)){
			// Retrouver les chemins optimaux � partir des pointsLivraison

			int[] next = tsp.getNext();
			Vector<PointLivraison> pointsLivraisonOptimum = new Vector<PointLivraison>();
			//for (int i=0; i<next.length; i++) {
			//        System.out.println(next[i]);
			//        System.out.println("trajet : " + retournerPointLivraisonDepuisPositionMatchMatch(match, next[i]).getIdPointLivraison());
			//        pointsLivraisonOptimum.add(retournerPointLivraisonDepuisPositionMatchMatch(match, next[i]));
			//}
			PointLivraison entrepot = tournee.getEntrepot();
			PointLivraison pointLivraisonActuel = entrepot;
			pointsLivraisonOptimum.add(entrepot);
			do{
				int positionMatch = retournerPairDepuisMatch(match, pointLivraisonActuel).getSecond();
				pointLivraisonActuel = retournerPointLivraisonDepuisPositionMatchMatch(match, next[positionMatch]);

				pointsLivraisonOptimum.add(pointLivraisonActuel);
			}while(entrepot.getIdPointLivraison() != pointLivraisonActuel.getIdPointLivraison());


//			for (int j=0; j<chemins.size(); j++)
//			{
//				System.out.println("chemins : " + chemins.get(j).getPointLivraisonOrigine().getIdPointLivraison() + "->" + chemins.get(j).getPointLivraisonDestination().getIdPointLivraison());
//			}

			Calendar heure = (Calendar) tournee.getTranchesHoraire().firstElement().getHeureDebut().clone();
			// Ajouter les chemins optimaux � la tourn�e
			for (int i=0; i<pointsLivraisonOptimum.size(); i++)
			{
				for (int j=0; j<chemins.size(); j++)
				{
					int iPlus1 = i+1;
					if (i+1 == pointsLivraisonOptimum.size()){iPlus1 = 0;}
					if (chemins.get(j).getPointLivraisonOrigine().equals(pointsLivraisonOptimum.get(i))
							&& chemins.get(j).getPointLivraisonDestination().equals(pointsLivraisonOptimum.get(iPlus1)))
					{
						
						heure.add(Calendar.SECOND, (int) chemins.get(j).getTemps());
						
						// Cas d'attente
						if (!chemins.get(j).getPointLivraisonDestination().equals(entrepot)
								&& heure.before(chemins.get(j).getPointLivraisonDestination().
										getTrancheHoraire().getHeureDebut()))
						{
							heure = (Calendar) chemins.get(j).getPointLivraisonDestination().
									getTrancheHoraire().getHeureDebut().clone();
						}
						
						heure.add(Calendar.SECOND, (int) chemins.get(j).getTemps());
						chemins.get(j).getPointLivraisonDestination().setHeureArriveeEstimee(heure);
//						System.out.println("Trajet Final : " + chemins.get(j).getPointLivraisonOrigine().getIdPointLivraison() + "->" + chemins.get(j).getPointLivraisonDestination().getIdPointLivraison() + " : " + heure);
						heure.add(Calendar.SECOND, Constante.DUREE_LIVRAISON_ESTIMEE);
						
						tournee.ajouterChemin(chemins.get(j));
					}
				}
			}

			tournee.afficher();
		}

	}

	/**
	 * Cree l'objet chemin entre pointLivraisonDepart et pointLivraisonArrivee, en y incluant le chemin le plus court.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArrivee
	 * @param plan
	 * @return Le chemin cree.
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

		// Creer le chemin
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
	 * Implemente l'algorithme de Dijkstra entre pointLivraisonDepart et pointLivraisonArivee.
	 * @param pointLivraisonDepart
	 * @param pointLivraisonArivee
	 * @return La liste des Troneons constituant le plus court chemin entre les 2 points.
	 */
	private Vector<Troncon> calculerPlusCourtChemin(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArivee){

		Vector<Troncon> tronconsSortants = new Vector<Troncon>();
		Vector<Pair<Noeud, Integer>> noeudsAccessibles = new Vector<Pair<Noeud, Integer>>();
		Vector<Noeud> noeudsParcourus = new Vector<Noeud>();
		Vector<Pair<Noeud, Vector<Troncon>>> plusCourtChemins = new Vector<Pair<Noeud, Vector<Troncon>>>();

		Pair<Noeud, Integer> pairNoeudActuel = new Pair<Noeud, Integer>(pointLivraisonDepart.getNoeud(), 0);
		Noeud noeudFin = pointLivraisonArivee.getNoeud();
		noeudsAccessibles.add(pairNoeudActuel);
		plusCourtChemins.add(new Pair<Noeud, Vector<Troncon>>(pairNoeudActuel.getFirst(), new Vector<Troncon>()));
		// Critere d'arret
		while(pairNoeudActuel.getFirst() != noeudFin){
			//System.out.println("BOUCLE NOEUD");
			//pairNoeudActuel.getFirst().afficher(); /////////////////////////////////////
			tronconsSortants = pairNoeudActuel.getFirst().getTronconsSortants();
			int minTemps = Integer.MAX_VALUE;

			// Parcourir les troncons sortants et ajouter/remplacer les noeuds acessibles
			for (int i=0; i<tronconsSortants.size(); i++){
				if(!noeudsParcourus.contains(tronconsSortants.get(i).getNoeudDestination())){ // Pas de retour en arriere
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
							//        meilleursTroncons.get(meilleursTroncons.size()-2).afficher(); /////////////////////////////////////
							//}
						//if (meilleursTroncons.size() >0){
							//        meilleursTroncons.get(meilleursTroncons.size()-1).afficher(); /////////////////////////////////////
							//}
						AjouterOuRemplacerPlusCourtChemin(plusCourtChemins, tronconsSortants.get(i).getNoeudDestination(), meilleursTroncons);
					}

					noeudsAccessibles = AjouterOuRemplacerNoeudsAccessibles(noeudsAccessibles, tronconsSortants.get(i).getNoeudDestination(), tempsNoeud);
				}
			}

			// Retirer le noeud de la liste a parcourir
			noeudsParcourus.add(pairNoeudActuel.getFirst());
			noeudsAccessibles.remove(pairNoeudActuel);

			// Selectionne le noeud de poids le plus faible
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
	 * Ajoute ou met a jour le noeud dans noeudsAccessibles.
	 * @param noeudsAccessibles
	 * @param noeud
	 * @param temps
	 * @return l'objet noeudsAccessibles modifie.
	 */
	private Vector<Pair<Noeud, Integer>> AjouterOuRemplacerNoeudsAccessibles(Vector<Pair<Noeud, Integer>> noeudsAccessibles, Noeud noeud, Integer temps) {
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

	//        private Vector<Pair<Noeud, Integer>> SupprimerNoeudsAccessibles(Vector<Pair<Noeud, Integer>> noeudsAccessibles, Noeud noeud){
	//                for(int i=0; i<noeudsAccessibles.size(); i++){
	//                        if (noeudsAccessibles.get(i).getFirst().getIdNoeud() == noeud.getIdNoeud()){
	//                                noeudsAccessibles.remove(i);
	//                                break;
	//                        }
	//                }
	//                
	//                return noeudsAccessibles;
	//        }

	/**
	 * Ajoute ou met a jour le noeud dans plusCourtChemin.
	 * @param noeudsAccessibles
	 * @param noeud
	 * @param temps
	 * @return l'objet plusCourtChemin modifie.
	 */
	private Vector<Pair<Noeud, Vector<Troncon>>> AjouterOuRemplacerPlusCourtChemin(Vector<Pair<Noeud, Vector<Troncon>>> plusCourtChemins, Noeud noeud, Vector<Troncon> troncons) {
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
	 * Ajoute ou met a jour le noeud dans Match.
	 * @param match
	 * @param pointLivraison
	 * @param position
	 * @return l'objet match modifie
	 */
	private Vector<Pair<PointLivraison, Integer>> AjouterMatch(Vector<Pair<PointLivraison, Integer>> match, PointLivraison pointLivraison, Integer position) {
		boolean trouve = false;
		for(int i=0; i<match.size(); i++){
			if (match.get(i).getFirst().getIdPointLivraison() == pointLivraison.getIdPointLivraison()){
				trouve = true;
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
	private Pair<Noeud, Integer> retournerPairDepuisNoeudAccessibles(Vector<Pair<Noeud, Integer>> noeudsAccessibles, Noeud noeud){
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
	private Pair<Noeud, Vector<Troncon>> retournerPairDepuisPlusCourtChemin(Vector<Pair<Noeud, Vector<Troncon>>> plusCourtChemins, Noeud noeud){
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
	 * @return la paire.
	 */
	private Pair<PointLivraison, Integer> retournerPairDepuisMatch(Vector<Pair<PointLivraison, Integer>> match, PointLivraison pointLivraison){
		Pair<PointLivraison, Integer> pair = null;
		for(int i=0; i<match.size(); i++){
			if (match.get(i).getFirst().getIdPointLivraison() == pointLivraison.getIdPointLivraison()){
				pair = match.get(i);
				break;
			}
		}

		return pair;
	}

	/**
	 * Retourne le point de livraison en prenant le pointdelivraison comme argument pour match a partir de la position.
	 * @param plusCourtChemins
	 * @param noeud
	 * @return le point de livraison.
	 */
	private PointLivraison retournerPointLivraisonDepuisPositionMatchMatch(Vector<Pair<PointLivraison, Integer>> match, int position){
		PointLivraison pointLivraison = null;
		for(int i=0; i<match.size(); i++){
			if (match.get(i).getSecond() == position){
				pointLivraison = match.get(i).getFirst();
				break;
			}
		}

		return pointLivraison;
	}

	@Override
	public int getMaxArcCost() {
		return maxArcCost;
	}

	@Override
	public int getMinArcCost() {
		return minArcCost;
	}

	@Override
	public int getNbVertices() {
		return nbVertices;
	}

	@Override
	public int[][] getCost(){
		return cost;
	}

	@Override
	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException{
		if ((i<0) || (i>=nbVertices))
			throw new ArrayIndexOutOfBoundsException();

		int nb = getNbSucc(i);
		int counter = 0;
		int[] succTab = new int[nb];
		for(int j=0; j<nbVertices; j++){
			if (cost[i][j] <= maxArcCost){
				succTab[counter] = j;
				counter++;
			}
		}

		return succTab;
	}


	@Override
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

