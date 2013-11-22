package com.h4201.prototype.app;


import com.h4201.prototype.test.TestChargementXml;
import com.h4201.prototype.test.TestInterface;
import com.h4201.prototype.test.TestLogic;
import com.h4201.prototype.vue.FeuilleDeRouteEnText;


public class Lanceur
{
	public static void main(String[] argv)
	{
		if(argv.length == 0)
		{
			// TODO Lancement de l'application
		}
		else if(argv[0].equals("testChargementXml"))
		{
			TestChargementXml testChargementXml = new TestChargementXml();
			testChargementXml.executerTests();
		}
		else if(argv[0].equals("supervision")) // Lancement des tests de l'affichage de l'interface
		{
			TestInterface superv = new TestInterface();
			superv.executerTest();
		}
		else if(argv[0].equals("feuilleDeRoute")) // Lancement des tests de l'affichage de l'interface
		{
			new FeuilleDeRouteEnText("feuille_de_route.txt");
		}
		else if(argv[0].equals("logic")) // Lancement des tests de l'affichage de l'interface
		{
			TestLogic testLogic = new TestLogic();
			testLogic.executerTests();
		}
	}
}
