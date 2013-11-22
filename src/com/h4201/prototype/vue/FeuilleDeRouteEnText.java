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
			ArrayList<PointLivraison> lesPointLivraisonsDestination = new ArrayList<PointLivraison>();
			for (int i = 0; i < lesChemin.size(); i++) {

				lesPointLivraisonsOrigine.add(lesChemin.get(i)
						.getPointLivraisonOrigine());
				for (int j = 0; j < lesPointLivraisonsOrigine.size(); j++) {
					pw.println("Le client origine" + "\n");
					pw.println(lesPointLivraisonsOrigine.get(j).getClient()
							+ "\n");
					pw.println(lesPointLivraisonsOrigine.get(j)
							.getIdPointLivraison() + "\n");
					pw.println("Le client destinataire" + "\n");
					pw.println(lesPointLivraisonsDestination.get(j).getTrancheHoraire() + "\n");
			
				}
				}
			
			for (int i = 0; i< lesChemin.size(); i++) {

				lesPointLivraisonsDestination.add(lesChemin.get(i)
						.getPointLivraisonDestination());
				for (int j = 0; j < lesPointLivraisonsDestination.size(); j++) {
					pw.println("Le client destinataire" + "\n");
					pw.println(lesPointLivraisonsDestination.get(j).getClient()
							+ "\n");
					pw.println(lesPointLivraisonsDestination.get(j)
							.getIdPointLivraison() + "\n");
					pw.println("Le client destinataire" + "\n");
					pw.println(lesPointLivraisonsDestination.get(j).getTrancheHoraire() + "\n");
				}			
				
			}
			Vector<Troncon> itineraire = new Vector<Troncon>();
			for (int i = 0; i < lesChemin.size(); i++) {

				itineraire.addAll(lesChemin.get(i).getTroncons());
				for (int j = 0; j < itineraire.size(); j++) {
					pw.println(itineraire.get(j) + "\n");
				}

			}
		}	pw.close();
			}
		
		catch (Exception e){
			e.printStackTrace();}

	}
	
}