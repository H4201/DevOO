package com.h4201.prototype.test;

import com.h4201.prototype.utilitaire.Constante;
import com.h4201.prototype.vue.VueSupervision;

public class TestInterface {
	
	public TestInterface()
	{
		
	}
	
	public void executerTest()
	{
		System.out.println("Les tests commencent !");
		
		try
		{
			VueSupervision intSuperv = new VueSupervision(Constante.LARGEURSUPERV, Constante.HAUTEURSUPERV);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Les tests sont termines");
	}

}
