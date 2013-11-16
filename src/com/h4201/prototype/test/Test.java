package com.h4201.prototype.test;

import java.io.File;

import com.h4201.prototype.controleur.FabriquePlan;
import com.h4201.prototype.modele.Plan;

public class Test
{
	public Test()
	{
		
	}
	
	public void executerTests()
	{
		System.out.println("Les tests commencent !");
		
		try
		{
			testChargerPlan();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Les tests sont terminés");
	}
	
	public void testChargerPlan() throws Exception
	{
		File planXML = new File("test/plan10x10.xml");
		Plan plan = FabriquePlan.depuisXML(planXML);
		
		System.out.print(plan.toString());
	} 

}
