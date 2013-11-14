package Modele;
import java.util.Date;

public class TourneeFoo {
	
	public CreerTournee() throws TranchesHorairesNonOrdoneesException{
		Date d = new Date();
		
		// Cr�er un nouveau Graphe
		graph = new AppGraph;
		
		// Parcourir les tranches horaires de mani�re ordonn�e
		for (int i=0; i<this.tranchesHoraire.size()-1; i++){
			
			// Parcourir uniquement les tranches horaires du jour
			if (tranchesHoraire[i].getDebut() > "cematin"
				&& tranchesHoraire[i].getFin() < "cesoir"){
				
				// V�rifier que les tranches horaires du jour sont bien ordon�es
				if (i>0 && tranchesHoraire[i-1].getFin() < tranchesHoraire[i].getDebut()
					|| i<this.tranchesHoraire && tranchesHoraire[i+1].getDebut() > tranchesHoraire[i].getFin()){
					
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
						numeroTrancheSuivante = (i == this.tranchesHoraire.size()) ? 0 : i+1; // TODO g�rer le cas ou plusieurs jours de livraison sont charg�s
						pointsDeLivraisonTrancheSuivante = tranchesHoraire[numeroTrancheSuivante].getPointsDeLivraison();
						for (int k=0; k<pointsDeLivraisonTrancheSuivante.size(); k++){
							creerChemin(pointsDeLivraison[j], pointsDeLivraisonTrancheSuivante[k]);
						}
					}
				} else {
					throw new TranchesHorairesNonOrdoneesException();
				}
			}
		}
		// 
		
	}

	public CreerChemin(){
		
	}
}
