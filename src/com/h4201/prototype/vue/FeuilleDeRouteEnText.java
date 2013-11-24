package com.h4201.prototype.vue;

import java.io.FileWriter;
import java.io.PrintWriter;


import com.h4201.prototype.modele.Tournee;


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
           // un boucle sur les chemins et un autre sur les tronçon.
			
			
            for(int i=0;i<tournee.getChemins().size();i++){
            	pw.println("Client:");
            	pw.println(tournee.getChemins().get(i).getPointLivraisonOrigine().getClient());
            	pw.println(tournee.getChemins().get(i).getPointLivraisonOrigine().getTrancheHoraire());
            	  pw.println("Suivre l'itinéraire");
            	 for(int j=0;j<tournee.getChemins().get(i).getTroncons().size();j++){
          
            			 pw.println(tournee.getChemins().get(i).getTroncons().get(j).getNomRue());
            		
                 	
                 }
            	 pw.println("\n");
            }
          
           
			pw.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return fw;

	}

}