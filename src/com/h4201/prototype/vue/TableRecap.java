package com.h4201.prototype.vue;

import java.util.Vector;


//import javax.swing.table.AbstractTableModel;


import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.TrancheHoraire;

public class TableRecap /*extends AbstractTableModel */{
	
	Vector <String> lesTranchesHoraires;
	Vector <String> lesLivraisons;

	public TableRecap(Vector <TrancheHoraire> tranchesHoraires) {
		String dateFormatee;
		lesTranchesHoraires = new Vector<String>();
		lesLivraisons = new Vector<String>();
		Vector <PointLivraison> listeLivraisons = new Vector<PointLivraison>();
		String listeLivraisonPourTH;
		for(TrancheHoraire trancheHoraire : tranchesHoraires )
		{
			dateFormatee = trancheHoraire.toString();
		    lesTranchesHoraires.addElement(dateFormatee);
		    listeLivraisons=trancheHoraire.getPointsLivraisons();
		    
		    listeLivraisonPourTH = "";
		    for(PointLivraison pointLivraison : listeLivraisons){
		    	listeLivraisonPourTH = listeLivraisonPourTH + pointLivraison.toString() + "\n";
		    }
		    lesLivraisons.add(listeLivraisonPourTH);
		}
	}
	
	public Vector <String> getLesTranchesHoraires(){
		return lesTranchesHoraires;
	}
	
	public Vector <String> getLesLivraisons(){
		return lesLivraisons;
	}
	
	public int getLongueur(){
		return lesTranchesHoraires.size();
	}

	
}
