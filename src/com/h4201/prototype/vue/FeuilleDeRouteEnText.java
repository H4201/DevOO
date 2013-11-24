package com.h4201.prototype.vue;


import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.Vector;

import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.PointLivraison;

import com.h4201.prototype.modele.Troncon;

public class FeuilleDeRouteEnText {
	

	protected String nomfichier;
   private FileWriter fw;

	public FeuilleDeRouteEnText(String nomfichier) {
		this.nomfichier = nomfichier;
		realisation(Tournee.getInstance());
	}
/**
 * 
 * @param tournee
 * @return
 */
	public FileWriter realisation( Tournee tournee){
		 	
		try{
			
		 fw = new FileWriter(nomfichier);
		// on manipule les lignes plutôt que des caractuères
		PrintWriter pw = new PrintWriter(fw);


		while (tournee.getChemins().iterator().hasNext()) {
			
			/*ArrayList<Chemin> lesChemin = new ArrayList<Chemin>();
			lesChemin.addAll(tournee.getChemins());*/
			Vector<PointLivraison> lesPointLivraisons = new Vector<PointLivraison>();
			Vector<Troncon> itineraire = new Vector<Troncon>();
			int indexPointLivraisonOrigine;
			int indexItineraire;
			for(int i=0;i<tournee.getChemins().size();i++){
		
				for (indexPointLivraisonOrigine=0;indexPointLivraisonOrigine<lesPointLivraisons.size();indexPointLivraisonOrigine++){
					lesPointLivraisons.add(indexPointLivraisonOrigine, tournee.getChemins().get(i).getPointLivraisonOrigine());
					pw.println("Client : " + lesPointLivraisons.get(indexPointLivraisonOrigine).getClient()+"\n");
					pw.println("Adresse : " + lesPointLivraisons.get(indexPointLivraisonOrigine).getNoeud().getIdNoeud()+"\n");
					pw.println("Heure d'arrivée : " + lesPointLivraisons.get(indexPointLivraisonOrigine).getTrancheHoraire().getHeureDebut()+"\n");
					pw.println("Heure de départ : " + lesPointLivraisons.get(indexPointLivraisonOrigine).getTrancheHoraire().getHeureFin()+"\n");
				
				}
								for(indexItineraire=0;indexItineraire<tournee.getChemins().size();indexItineraire++){
					itineraire.addAll(indexItineraire, tournee.getChemins().get(i).getTroncons());
					pw.println("Suivre l'itinéraire : " + tournee.getChemins().get(i).getTroncons()+"\n");
				}
								pw.println("\n");
			}
			
			

		}	pw.close();
			}
		
		catch (Exception e){
			e.printStackTrace();}
		return fw;

	}
	 
	
}