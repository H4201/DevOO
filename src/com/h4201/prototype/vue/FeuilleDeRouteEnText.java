package com.h4201.prototype.vue;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;

import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Troncon;

public class FeuilleDeRouteEnText {

	private static volatile FeuilleDeRouteEnText instance = null;
	protected String nomfichier;
	private FileWriter fw;

	public FeuilleDeRouteEnText(String nomfichier) {
		this.nomfichier = nomfichier;
		realisation(Tournee.getInstance());
	}

	public final static FeuilleDeRouteEnText getInstance() {
		return FeuilleDeRouteEnText.instance;
	}

	/**
	 * 
	 * @param tournee
	 * @return
	 */
	public FileWriter realisation(Tournee tournee) {

		try {

			fw = new FileWriter(nomfichier);
			// on manipule les lignes plutôt que des caractuères
			PrintWriter pw = new PrintWriter(fw);

			for (int indexChemin = 0; indexChemin < tournee.getChemins().size(); indexChemin++) {

				/*
				 * ArrayList<Chemin> lesChemin = new ArrayList<Chemin>();
				 * lesChemin.addAll(tournee.getChemins());
				 */
				Vector<PointLivraison> lesPointLivraisons = new Vector<PointLivraison>();
				Vector<Troncon> itineraire = new Vector<Troncon>();
				int indexPointLivraison;
				int indexItineraire;
				// for(int i=0;i<tournee.getChemins().size();i++){
				pw.println("Client : " + "\n");
				for (indexPointLivraison = 0; indexPointLivraison < lesPointLivraisons
						.size(); indexPointLivraison++) {
					lesPointLivraisons.add(indexPointLivraison, tournee
							.getChemins().get(indexPointLivraison)
							.getPointLivraisonOrigine());
					pw.println(lesPointLivraisons.get(indexPointLivraison).getClient()+ "\n");
			
					System.out.println(tournee.getChemins().get(indexChemin)
							.getPointLivraisonOrigine().getIdPointLivraison());
				}
				pw.println("Adress : " + "\n");
				for (indexPointLivraison = 0; indexPointLivraison < lesPointLivraisons
						.size(); indexPointLivraison++) {
				pw.println(lesPointLivraisons.get(indexPointLivraison).getNoeud().getIdNoeud()+"\n");}
				
				pw.println("Heure d'arrivée : " + "\n");
				for (indexPointLivraison = 0; indexPointLivraison < lesPointLivraisons
						.size(); indexPointLivraison++) {
				pw.println(lesPointLivraisons.get(indexPointLivraison).getTrancheHoraire().getHeureDebut()+"\n");}

				pw.println("Heure de départ : " + "\n");
				for (indexPointLivraison = 0; indexPointLivraison < lesPointLivraisons
						.size(); indexPointLivraison++) {
				pw.println(lesPointLivraisons.get(indexPointLivraison).getTrancheHoraire().getHeureFin()+"\n");}
				
				pw.println("Suivre l'itinéraire : " + "\n");
				for (indexItineraire = 0; indexItineraire < tournee
						.getChemins().size(); indexItineraire++) {
					itineraire.addAll(indexItineraire, tournee.getChemins()
							.get(indexChemin).getTroncons());

					pw.println(tournee.getChemins().get(indexChemin)
							.getTroncons().get(indexItineraire).getNomRue()
							+ "\n");
				}
				pw.println("\n");
				// }

			}
			pw.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return fw;

	}

}