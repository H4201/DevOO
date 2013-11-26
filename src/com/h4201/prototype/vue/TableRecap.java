package com.h4201.prototype.vue;

import java.util.Vector;


//import javax.swing.table.AbstractTableModel;


import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.TrancheHoraire;

public class TableRecap {
	
	Vector <String> lesLivraisons;

	public TableRecap(Vector <TrancheHoraire> tranchesHoraires) {
		lesLivraisons = new Vector<String>();
		String listeLivraisonPourTH;
		for(TrancheHoraire trancheHoraire : tranchesHoraires )
		{
			listeLivraisonPourTH = trancheHoraire.toString() + "\n";
			lesLivraisons.add(listeLivraisonPourTH);
			
		    for(PointLivraison pointLivraison : trancheHoraire.getPointsLivraisons())
		    {
		    	listeLivraisonPourTH = "\t" + pointLivraison.toString() + "\n";
		    	lesLivraisons.add(listeLivraisonPourTH);
		    }
		}
	}
	
	
	public Vector<String> getLesLivraisons(){
		return lesLivraisons;
	}
	
	public int getLongueur(){
		return lesLivraisons.size();
	}

	
}
