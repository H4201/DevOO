package com.h4201.prototype.app;


import com.h4201.prototype.test.TestChargementXml;
import com.h4201.prototype.test.TestControleur;
import com.h4201.prototype.test.TestFeuilleDeRoute;
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
		else if(argv[0].equals("chargementXml"))
		{
			TestChargementXml testChargementXml = new TestChargementXml();
			testChargementXml.executerTests();
		}
		else if(argv[0].equals("supervision"))
		{
			TestInterface superv = new TestInterface();
			superv.executerTest();
		}
		else if(argv[0].equals("feuilleDeRoute"))
		{
			TestFeuilleDeRoute feuille = new TestFeuilleDeRoute();
			feuille.executerTests();
		}
		else if(argv[0].equals("logic"))
		{
			TestLogic testLogic = new TestLogic();
			testLogic.executerTests();
		}
		else if(argv[0].equals("controleur")) // Lancement des tests de l'affichage de l'interface
		{
			new TestControleur();
		}
	}
}
