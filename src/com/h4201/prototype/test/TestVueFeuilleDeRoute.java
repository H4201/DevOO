package com.h4201.prototype.test;

import java.awt.EventQueue;

import com.h4201.prototype.vue.VueFeuilleDeRoute;

public class TestVueFeuilleDeRoute {

	public TestVueFeuilleDeRoute()
	{
		
	}
	public void executerTestVueFeuilleDeRoute()
	{
		System.out.println("Les TestVueFeuilleDeRoute commencent !");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueFeuilleDeRoute frame = new VueFeuilleDeRoute();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Les tests sont terminées");
			}
		});
		}
		
	
	
}
