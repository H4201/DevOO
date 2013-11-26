package com.h4201.prototype.test;


import java.awt.EventQueue;

import com.h4201.prototype.modele.Noeud;
import com.h4201.prototype.vue.VueTrancheHoraire;

public class TestVueTrancheHoraire {
	private Noeud noeud;
	public TestVueTrancheHoraire(){
		
	}
	public void executeTest(){
	
	EventQueue.invokeLater(new Runnable() {
	public void run() {
		try {
			VueTrancheHoraire frame = new VueTrancheHoraire( noeud);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	});
}}
