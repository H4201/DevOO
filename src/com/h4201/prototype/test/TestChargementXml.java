package com.h4201.prototype.test;

import java.io.File;

import com.h4201.prototype.modele.CreationDemandeLivraison;
import com.h4201.prototype.modele.CreationPlan;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.Tournee;

import static org.junit.Assert.*;

public class TestChargementXml
{
	public TestChargementXml()
	{
		
	}
	
	public void executerTests()
	{
		System.out.println("Les tests commencent !");
		
		try
		{
			testChargerPlan();
			testChargerDemandeLivraison();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Les tests sont termines");
		}
	}
	
	public void testChargerPlan() throws Exception
	{
		File planXML = new File("test/plan20x20.xml");
		Plan plan = CreationPlan.depuisXML(planXML);
		
		assertTrue(!plan.getNoeuds().isEmpty());
		assertTrue(!plan.getTroncons().isEmpty());
	} 
	
	public void testChargerDemandeLivraison() throws Exception
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
	}
}
