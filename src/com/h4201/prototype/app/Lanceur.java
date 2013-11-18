package com.h4201.prototype.app;

import com.h4201.prototype.test.Test;

import com.h4201.prototype.test.TestVueFeuilleDeRoute;

import com.h4201.prototype.test.TestInterface;


public class Lanceur
{
	public static void main(String[] argv)
	{
		if(argv.length == 0)
		{
			// TODO Lancement de l'application
		}
		else if(argv[0].equals("test")) // Lancement des tests
		{
			Test phaseDeTests = new Test();
			phaseDeTests.executerTests();
			
			
		}
		
		else if(argv[0].equals("testVueFeuilleDeRoute")){
			TestVueFeuilleDeRoute phaseDeTestVueFeuilleDeRoute = new TestVueFeuilleDeRoute();
			phaseDeTestVueFeuilleDeRoute.executerTestVueFeuilleDeRoute();
			
		}
		else if(argv[0].equals("supervision")) // Lancement des tests de l'affichage de l'interface
		{
			TestInterface superv = new TestInterface();
			superv.executerTest();
		}
	}
}
