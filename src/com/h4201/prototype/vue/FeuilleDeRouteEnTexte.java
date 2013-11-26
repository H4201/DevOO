package com.h4201.prototype.vue;

import java.io.FileWriter;
import java.io.PrintWriter;

import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.Troncon;
import com.h4201.prototype.utilitaire.Constante;
import com.h4201.prototype.utilitaire.Date;


public final class FeuilleDeRouteEnTexte {

	private static volatile FeuilleDeRouteEnTexte instance = null;
	private FileWriter fw;

	private FeuilleDeRouteEnTexte()
	{
	}

	public final static FeuilleDeRouteEnTexte getInstance()
    {
        if (FeuilleDeRouteEnTexte.instance == null)
        {
           synchronized(FeuilleDeRouteEnTexte.class)
           {
			 if (FeuilleDeRouteEnTexte.instance == null)
			 {
				 FeuilleDeRouteEnTexte.instance = new FeuilleDeRouteEnTexte();
			 }
           }
        }
        
        return FeuilleDeRouteEnTexte.instance;
    }

	/**
	 * 
	 * @param tournee
	 * @return
	 */
	public FileWriter realisation()
	{
		Tournee tournee = Tournee.getInstance();
		
		try
		{
			fw = new FileWriter(Constante.FICHIER_NOM_FEUILLE_DE_ROUTE, false);
			PrintWriter pw = new PrintWriter(fw, false);
			Chemin cheminCourant;
            for(int i=0;i<tournee.getChemins().size();i++)
            {
            	cheminCourant = tournee.getChemins().get(i);
            	
            	if(cheminCourant.getPointLivraisonOrigine().getTrancheHoraire() == null)
            	{
            		pw.println("Depart de l'entrepot E" + tournee.getEntrepot().getNoeud().getIdNoeud());
            	}
            	else
            	{
            		pw.println("Livraison numero " + i);
            		pw.println("Identifiant de la livraison L" + cheminCourant.getPointLivraisonOrigine().getIdPointLivraison() 
            				+ " du client C" + cheminCourant.getPointLivraisonOrigine().getClient());
            	}
            	pw.println("Adresse : "+ "X = "+ cheminCourant.getPointLivraisonOrigine().getNoeud().getX()+" , Y = "
            			+ cheminCourant.getPointLivraisonOrigine().getNoeud().getY());
            	
            	if(cheminCourant.getPointLivraisonOrigine().getTrancheHoraire() != null)
            	{
            		pw.println("Heure d'arrivee estimee : " + Date.getHeureFrDepuisCalendar(
            				cheminCourant.getPointLivraisonOrigine().getTrancheHoraire().getHeureDebut()));
            	}
            	
            	pw.println("\nItineraire jusqu'a la prochaine livraison : ");
            	pw.println("Longueur de l'itineraire : " + cheminCourant.getLongueur());
            	pw.println("Temps estime : " + cheminCourant.getTemps());
            	
            	pw.println("\nSuivre l'itineraire : ");
            
            	String nomRuePrecedente = "";
            	 for(Troncon troncon : cheminCourant.getTroncons())
            	 {
            		 if(!nomRuePrecedente.equals(troncon.getNomRue()))
            		 {
	            		 if(nomRuePrecedente.equals(""))
	            			 pw.print("\tPrendre ");
	            		 else
	            			 pw.print("\tTourner a ");
	            			 
	            		 pw.print(troncon.getNomRue() +"\n");	
	            		 
	            		 nomRuePrecedente = troncon.getNomRue();
            		 }
            	 } 
            	 pw.println("\tC'est au "+ "X = "+ cheminCourant.getPointLivraisonDestination().getNoeud().getX()+" , Y = "
             			+ cheminCourant.getPointLivraisonDestination().getNoeud().getY());
            	 
            	 pw.println("\n------------------------------------------------------------");
            }
            
            pw.println("\nDe retour a l'entrepot");
            
			pw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return fw;

	}

}