package com.h4201.prototype.vue;

import java.io.FileWriter;
import java.io.PrintWriter;



import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.utilitaire.Date;


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
			pw.println("Entrepôt : " + tournee.getEntrepot().getNoeud().getIdNoeud() );
			pw.println("\n");
            for(int i=0;i<tournee.getChemins().size();i++){
            	
            	pw.println("Client : " + tournee.getChemins().get(i).getPointLivraisonOrigine().getClient());
            	pw.println("Adresse : "+ "X = "+ tournee.getChemins().get(i).getPointLivraisonOrigine().getNoeud().getX()+" , Y = "+tournee.getChemins().get(i).getPointLivraisonOrigine().getNoeud().getY());
            	
            	pw.println("Heure d'arrivée : " + Date.getHeureFrDepuisCalendar(tournee.getChemins().get(i).getPointLivraisonOrigine().getTrancheHoraire().getHeureDebut()));
            	pw.println("Heure de départ : " +Date.getHeureFrDepuisCalendar(tournee.getChemins().get(i).getPointLivraisonOrigine().getTrancheHoraire().getHeureFin()));
            	pw.println("Suivre l'itinéraire : ");
            
            	 for(int j=0;j<tournee.getChemins().get(i).getTroncons().size();j++){
            		 
            			 pw.print( "--->" +tournee.getChemins().get(i).getTroncons().get(j).getNomRue() +"\n");	
            			
            			
                 } pw.println("\r\n");
            	 
            	 pw.println("----------------------------------------------------------------------------------------------------------------------------------");
            } 
       
          
           
			pw.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return fw;

	}

}