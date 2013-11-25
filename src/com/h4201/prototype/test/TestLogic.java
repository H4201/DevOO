package com.h4201.prototype.test;

import java.io.File;

import com.h4201.prototype.modele.Chemin;
import com.h4201.prototype.modele.CreationDemandeLivraison;
import com.h4201.prototype.modele.CreationPlan;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.AppGraphe;

import static org.junit.Assert.*;

public class TestLogic
{
	
	public void executerTests()
	{
		System.out.println("Les tests commencent !!");
		
		try
		{
			testChargerPlan();
			Tournee tournee = testChargerDemandeLivraison();
			testDijkstra(tournee.getTranchesHoraire().get(0).getPointsLivraisons().get(1),
						 tournee.getTranchesHoraire().get(0).getPointsLivraisons().get(2));
			testTSP();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Les tests sont termines.");
	}
	
	public void testChargerPlan() throws Exception
	{
		File planXML = new File("test/plan20x20.xml");
		Plan plan = CreationPlan.depuisXML(planXML);
		
		assertTrue(!plan.getNoeuds().isEmpty());
		assertTrue(!plan.getTroncons().isEmpty());
	} 
	
	public Tournee testChargerDemandeLivraison() throws Exception
	{
		File demandeLivraisonXML = new File("test/livraison20x20-2.xml");
		Tournee tournee = CreationDemandeLivraison.depuisXML(demandeLivraisonXML);
		
		File demandeLivraisonXML2 = new File("test/livraison20x20-1.xml");
		Tournee tournee2 = CreationDemandeLivraison.depuisXML(demandeLivraisonXML2);
		
		
		assertTrue(tournee.getEntrepot() != null);
		assertTrue(!tournee.getTranchesHoraire().isEmpty());
		
		assertTrue(tournee2.getEntrepot() != null);
		assertTrue(!tournee2.getTranchesHoraire().isEmpty());
		
		tournee2.afficher();
		
		return tournee;
	}
	
	public void testDijkstra(PointLivraison pointLivraisonDepart, PointLivraison pointLivraisonArrivee){
		AppGraphe appGraphe = AppGraphe.getInstance();
		Chemin chemin = appGraphe.creerChemin(pointLivraisonDepart, pointLivraisonArrivee);
		
		chemin.afficher();
	}
	
	public void testTSP() throws Exception{
<<<<<<< HEAD
		File demandeLivraisonXML = new File("test/livraison20x20-2.xml");
		Tournee tournee = CreationDemandeLivraison.depuisXML(demandeLivraisonXML);
=======
		File demandeLivraisonXML = new File("test/livraison20x20-1.xml");
		CreationDemandeLivraison.depuisXML(demandeLivraisonXML);
>>>>>>> 86cc240f67082049250f634de78755fc34f0d2d9
		AppGraphe appGraphe = AppGraphe.getInstance();
		appGraphe.genererTournee();
	}

}