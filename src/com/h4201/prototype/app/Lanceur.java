package com.h4201.prototype.app;

import com.h4201.prototype.test.Test;
import com.h4201.prototype.test.TestChargementXml;
import com.h4201.prototype.test.TestInterface;

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
	}
}
