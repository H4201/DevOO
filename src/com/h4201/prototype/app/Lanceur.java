package com.h4201.prototype.app;

import com.h4201.prototype.test.Test;

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
	}
}
