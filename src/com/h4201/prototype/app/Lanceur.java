package com.h4201.prototype.app;

import com.h4201.prototype.test.TestChargementXml;
import com.h4201.prototype.test.TestControleur;
import com.h4201.prototype.test.TestFeuilleDeRoute;
import com.h4201.prototype.test.TestInterface;
import com.h4201.prototype.test.TestLogic;
import com.h4201.prototype.test.TestTournee;
import com.h4201.prototype.vue.VueSupervision;


/**
 * Classe Main
 * @author Paul
 *
 */
public class Lanceur
{
	/**
	 * Lancement de l'application ou de tests.
	 * Ouvre la fenetre de supervision si aucun argv.
	 * sinon l'argument indique en premier parametre.
	 * @param argv eventuellement argv[0] le nom du test a lancer
	 */
	public static void main(String[] argv)
	{
		if(argv.length == 0)
		{
			VueSupervision.getInstance();
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
		else if(argv[0].equals("controleur"))
		{
			new TestControleur();
		}
		else if(argv[0].equals("tournee"))
		{
			new TestTournee();
		}
	}
}
