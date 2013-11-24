package com.h4201.prototype.vue;


import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.Troncon;

public class FeuilleDeRouteEnText {
	
	private Tournee tournee;
	protected String nomfichier;


	public FeuilleDeRouteEnText(String nomfichier) {
		this.nomfichier = nomfichier;
		realisation();
	}

	public void realisation(){
		try{
			
		FileWriter fw = new FileWriter(nomfichier);
		// on manipule les lignes plutôt que des caractuères
		PrintWriter pw = new PrintWriter(fw);

		tournee = Tournee.getInstance();
		while (tournee.getChemins().iterator().hasNext()) {

			ArrayList<Chemin> lesChemin = new ArrayList<Chemin>();
			lesChemin.addAll(tournee.getChemins());
			ArrayList<PointLivraison> lesPointLivraisonsOrigine = new ArrayList<PointLivraison>();
			Vector<Troncon> itineraire = new Vector<Troncon>();
			int indexPointLivraisonOrigine;
			int indexItineraire;
			for(int i=0;i<lesChemin.size();i++){
				
				for (indexPointLivraisonOrigine=0;indexPointLivraisonOrigine<lesPointLivraisonsOrigine.size();indexPointLivraisonOrigine++){
					lesPointLivraisonsOrigine.add(indexPointLivraisonOrigine, lesChemin.get(i).getPointLivraisonOrigine());
					pw.println("Client : " + lesPointLivraisonsOrigine.get(indexPointLivraisonOrigine).getClient()+"\n");
					pw.println("Adresse : " + lesPointLivraisonsOrigine.get(indexPointLivraisonOrigine).getNoeud().getIdNoeud()+"\n");
					pw.println("Heure d'arrivée : " + lesPointLivraisonsOrigine.get(indexPointLivraisonOrigine).getTrancheHoraire().getHeureDebut()+"\n");
					pw.println("Heure de départ : " + lesPointLivraisonsOrigine.get(indexPointLivraisonOrigine).getTrancheHoraire().getHeureFin()+"\n");
					pw.println("\n");
				}
								for(indexItineraire=0;indexItineraire<lesChemin.size();indexItineraire++){
					itineraire.addAll(indexItineraire, lesChemin.get(i).getTroncons());
					pw.println("Suivre l'itinéraire : " + lesChemin.get(i).getTroncons()+"\n");
				}
			}
			
			

		}	pw.close();
			}
		
		catch (Exception e){
			e.printStackTrace();}

	}
	
}