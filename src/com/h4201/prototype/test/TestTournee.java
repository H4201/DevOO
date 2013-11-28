package com.h4201.prototype.test;

import java.io.File;
import java.util.Vector;

import com.h4201.prototype.modele.AppGraphe;
import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.CreationDemandeLivraison;
import com.h4201.prototype.modele.CreationPlan;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Date;

import static org.junit.Assert.*;

/**
 * Test du calcul de la tournee 2.
 * @author Paul
 *
 */
public class TestTournee
{
	public TestTournee()
	{
		System.out.println("Les tests commencent !");
		
		this.executerTests();
		
		System.out.println("Les tests sont termines");
	}
	
	public void executerTests()
	{
		try
		{
			// Chargement et calcul
			CreationPlan.depuisXML(new File("test/plan20x20.xml"));			
			CreationDemandeLivraison.depuisXML(new File("test/livraison20x20-2.xml"));
			AppGraphe.getInstance().genererTournee();
			
			
			Vector<Chemin> chemins = Tournee.getInstance().getChemins();
			
			System.out.println("\nNombre de chemins : " + chemins.size());
			assertTrue(chemins.size() > 0);
			
			for(Chemin chemin : chemins)
			{
//				System.out.println(chemin.getPointLivraisonOrigine().toString());
//				System.out.println(chemin.getPointLivraisonDestination().toString());
				assertTrue(chemin.getTroncons().size() > 0);
				System.out.println("Nombre de troncons : " + chemin.getTroncons().size());
			}
			
			
			Vector<TrancheHoraire> tranchesHoraire = Tournee.getInstance().getTranchesHoraire();
			
			System.out.println("\nNombre de tranches horaires: " + tranchesHoraire.size());
			assertTrue(tranchesHoraire.size() > 0);
			
			for(TrancheHoraire trancheHoraire : tranchesHoraire)
			{
				System.out.println("\nNombre de livraisons : " + trancheHoraire.getPointsLivraisons().size());
				assertTrue(trancheHoraire.getPointsLivraisons().size() > 0);
				for(PointLivraison pointLivraison : trancheHoraire.getPointsLivraisons())
				{
					assertTrue(pointLivraison.getHeureArriveeEstimee() != null);
					System.out.println(Date.getHeureFrDepuisCalendar(pointLivraison.getHeureArriveeEstimee()));
				}
			}
			
			System.out.println("\nAffichage des points de livraison des chemins");
			for(Chemin chemin : chemins)
			{
				assertTrue(chemin.getPointLivraisonOrigine().getHeureArriveeEstimee() != null);
				System.out.println(Date.getHeureFrDepuisCalendar(chemin.getPointLivraisonOrigine().getHeureArriveeEstimee()));
				assertTrue(chemin.getPointLivraisonDestination().getHeureArriveeEstimee() != null);
				System.out.println(Date.getHeureFrDepuisCalendar(chemin.getPointLivraisonDestination().getHeureArriveeEstimee()));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
