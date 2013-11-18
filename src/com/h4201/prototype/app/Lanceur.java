package com.h4201.prototype.app;

import com.h4201.prototype.test.Test;
import com.h4201.prototype.test.TestVueFeuilleDeRoute;

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
	}
}
