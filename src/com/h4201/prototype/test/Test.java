package com.h4201.prototype.test;

import java.io.File;

import com.h4201.prototype.modele.CreationDemandeLivraison;
import com.h4201.prototype.modele.CreationPlan;
import com.h4201.prototype.modele.Plan;
import com.h4201.prototype.modele.Tournee;

public class Test
{
	public Test()
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
		
		System.out.println("Les tests sont terminés");
	}
	
	public void testChargerPlan() throws Exception
	{
		File planXML = new File("test/plan20x20.xml");
		Plan plan = CreationPlan.depuisXML(planXML);
		
		plan.afficher();
		
		// TODO : Tests avec jUnit
	} 
	
	public void testChargerDemandeLivraison() throws Exception
	{
		File demandeLivraisonXML = new File("test/livraison20x20-2.xml");
		Tournee tournee = CreationDemandeLivraison.depuisXML(demandeLivraisonXML);
		
		tournee.afficher();
		
		// TODO : Tests avec jUnit
	}
}
