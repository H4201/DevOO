package com.h4201.prototype.test;

import java.io.File;

import com.h4201.prototype.controleur.Controleur;
import com.h4201.prototype.vue.FeuilleDeRouteEnText;
import com.h4201.prototype.vue.VueSupervision;

public class TestFeuilleDeRoute
{
	public TestFeuilleDeRoute()
	{
		
	}
	
	public void executerTests()
	{
		System.out.println("Les tests commencent !");
		
		try
		{
			Controleur controleur = Controleur.getInstance();
			controleur.chargerPlan(new File("test/plan20x20.xml"));			
			controleur.chargerDemandeLivraison(new File("test/livraison20x20-2.xml"));
			controleur.notifierClicNormal();
			controleur.calculTournee();
			new FeuilleDeRouteEnText("feuille_de_route.txt");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Les tests sont termines");
	}
}
