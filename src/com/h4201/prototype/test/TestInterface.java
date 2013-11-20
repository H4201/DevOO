package com.h4201.prototype.test;

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
			VueSupervision intSuperv = new VueSupervision(1000, 700);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Les tests sont termines");
	}

}
