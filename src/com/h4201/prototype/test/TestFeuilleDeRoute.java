package com.h4201.prototype.test;

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
			TestChargementXml testChargementXml = new TestChargementXml();
			testChargementXml.executerTests();
			new FeuilleDeRouteEnText("feuille_de_route.txt");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Les tests sont termines");
	}
}
