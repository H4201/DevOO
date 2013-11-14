package Modele;
import java.util.Date;

public class Tournee {
	
	Tournee(Plan plan, Entrepot entrepot, TrancheHoraire[] tranchesHoraire) throws TranchesHorairesNonOrdoneesException{
		Date d = new Date();
		
		// Cr�er un nouveau Graphe
		graph = new AppGraph;
		
		// Parcourir les tranches horaires de mani�re ordonn�e
		for (int i=0; i<tranchesHoraire.size(); i++){
			
			// V�rifier que les tranches horaires du jour sont bien ordon�es
			if (i>0 && tranchesHoraire[i-1].getFin() < tranchesHoraire[i].getDebut()
				|| i<tranchesHoraire && tranchesHoraire[i+1].getDebut() > tranchesHoraire[i].getFin()){
				
				// Parcourir les points de livraison de cette tranche
				pointsDeLivraison = tranchesHoraire[i].getPointsDeLivraison();
				for (int j=0; j<pointsDeLivraison.size(); j++){
					
					// Cr�er un chemin vers tous les autres points de livraison de la m�me tranche
					for (int k=0; k<pointsDeLivraison.size(); k++){
						if (pointsDeLivraison[j] != pointsDeLivraison[k]) {
							creerChemin(pointsDeLivraison[j], pointsDeLivraison[k]);
						}
					}
					
					// Cr�er un chemin vers tous les points de livraison de la tranche suivante
					numeroTrancheSuivante = (i == this.tranchesHoraire.size()) ? 0 : i+1;
					pointsDeLivraisonTrancheSuivante = tranchesHoraire[numeroTrancheSuivante].getPointsDeLivraison();
					for (int k=0; k<pointsDeLivraisonTrancheSuivante.size(); k++){
						creerChemin(pointsDeLivraison[j], pointsDeLivraisonTrancheSuivante[k]);
					}
					
					// Pour la premi�re tranche, cr�er un chemin depuis l'entrep�t 
					if (i==0) {
						creerChemin(entrepot, pointsDeLivraison[j]);
					}
					
					// Pour la derni�re tranche, cr�er un chemin vers l'entrep�t 
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
		
		// Cr�er le chemin
		chemin = new Chemin();
	}
	
	public calculerPlusCourtChemin(Plan plan, PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArivee){

		
	}
}
