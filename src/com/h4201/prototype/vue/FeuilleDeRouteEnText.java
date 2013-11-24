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
			// on manipule les lignes plut�t que des caractu�res
			PrintWriter pw = new PrintWriter(fw);
			pw.println("Entrep�t : " + tournee.getEntrepot().getNoeud().getIdNoeud() );
			pw.println("\n");
            for(int i=0;i<tournee.getChemins().size();i++){
            	
            	pw.println("Client : " + tournee.getChemins().get(i).getPointLivraisonOrigine().getClient());
            	pw.println("Adresse : "+ "X = "+ tournee.getChemins().get(i).getPointLivraisonOrigine().getNoeud().getX()+" , Y = "+tournee.getChemins().get(i).getPointLivraisonOrigine().getNoeud().getY());
            	
            	pw.println("Heure d'arriv�e : " + Date.getHeureFrDepuisCalendar(tournee.getChemins().get(i).getPointLivraisonOrigine().getTrancheHoraire().getHeureDebut()));
            	pw.println("Heure de d�part : " +Date.getHeureFrDepuisCalendar(tournee.getChemins().get(i).getPointLivraisonOrigine().getTrancheHoraire().getHeureFin()));
            	pw.println("Suivre l'itin�raire : ");
            
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