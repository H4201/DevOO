package Modele;
import java.util.Date;

public class Tournee {
	
	Tournee(Plan plan, Entrepot entrepot, TrancheHoraire[] tranchesHoraire) throws TranchesHorairesNonOrdoneesException{
		Date d = new Date();
		
		// Créer un nouveau Graphe
		graph = new AppGraph;
		
		// Parcourir les tranches horaires de manière ordonnée
		for (int i=0; i<tranchesHoraire.size(); i++){
			
			// Vérifier que les tranches horaires du jour sont bien ordonées
			if (i>0 && tranchesHoraire[i-1].getFin() < tranchesHoraire[i].getDebut()
				|| i<tranchesHoraire && tranchesHoraire[i+1].getDebut() > tranchesHoraire[i].getFin()){
				
				// Parcourir les points de livraison de cette tranche
				pointsDeLivraison = tranchesHoraire[i].getPointsDeLivraison();
				for (int j=0; j<pointsDeLivraison.size(); j++){
					
					// Créer un chemin vers tous les autres points de livraison de la même tranche
					for (int k=0; k<pointsDeLivraison.size(); k++){
						if (pointsDeLivraison[j] != pointsDeLivraison[k]) {
							creerChemin(pointsDeLivraison[j], pointsDeLivraison[k]);
						}
					}
					
					// Créer un chemin vers tous les points de livraison de la tranche suivante
					numeroTrancheSuivante = (i == this.tranchesHoraire.size()) ? 0 : i+1;
					pointsDeLivraisonTrancheSuivante = tranchesHoraire[numeroTrancheSuivante].getPointsDeLivraison();
					for (int k=0; k<pointsDeLivraisonTrancheSuivante.size(); k++){
						creerChemin(pointsDeLivraison[j], pointsDeLivraisonTrancheSuivante[k]);
					}
					
					// Pour la première tranche, créer un chemin depuis l'entrepôt 
					if (i==0) {
						creerChemin(entrepot, pointsDeLivraison[j]);
					}
					
					// Pour la dernière tranche, créer un chemin vers l'entrepôt 
					if (i==tranchesHoraire.size()-1) {
						creerChemin(pointsDeLivraison[j], entrepot);
					}
				}
			} else {
				throw new TranchesHorairesNonOrdoneesException();
			}
		}
		// 
		
	}

	public creerChemin(Plan plan, PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArivee){
		//Calcule le plus court chemin entre pointLivraisonDepart et pointLivraisonArivee dans G
		Troncon[] troncons = calculerPlusCourtChemin(pointLivraisonDepart, pointLivraisonArivee);

		
		for (int i=0; i<troncons.size(); i++){
			// des trucs
		}
		
		// Créer le chemin
		chemin = new Chemin();
	}
	
	public calculerPlusCourtChemin(Plan plan, PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArivee){

		
	}
}
