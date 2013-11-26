package com.h4201.prototype.vue;

import java.util.Vector;

import javax.swing.JTable;


//import javax.swing.table.AbstractTableModel;


import com.h4201.prototype.modele.PointLivraison;
import com.h4201.prototype.modele.Tournee;
import com.h4201.prototype.modele.TrancheHoraire;
import com.h4201.prototype.utilitaire.Date;

public class TableRecap {
	
	private Vector <String> lesLivraisons;
	private JTable tableau;

	public TableRecap()
	{
		Vector <TrancheHoraire> tranchesHoraires = Tournee.getInstance().getTranchesHoraire();
		
		lesLivraisons = new Vector<String>();
		String listeLivraisonPourTH;
		for(TrancheHoraire trancheHoraire : tranchesHoraires )
		{
			listeLivraisonPourTH = "TH" + trancheHoraire.getIdTrancheHoraire() 
					+ " entre " 
					+ Date.getHeureFrSimplifieeDepuisCalendar(trancheHoraire.getHeureDebut()) 
					+ " et " 
					+ Date.getHeureFrSimplifieeDepuisCalendar(trancheHoraire.getHeureFin());
			
			lesLivraisons.add(listeLivraisonPourTH);
			
		    for(PointLivraison pointLivraison : trancheHoraire.getPointsLivraisons())
		    {
		    	listeLivraisonPourTH = "\t\tL" + pointLivraison.getIdPointLivraison();
		    			
		    	if(pointLivraison.getHeureArriveeEstimee() != null)
		    		listeLivraisonPourTH += " a " + Date.getHeureFrSimplifieeDepuisCalendar(
		    				pointLivraison.getHeureArriveeEstimee());
		    	
		    	lesLivraisons.add(listeLivraisonPourTH);
		    }
		}
		
		// Transformation structure de donnees
		Object[][] objets = new Object[this.getLongueur()][1];
		String[] entetes = {"Livraisons par tranche horaire"};
		for(int compte=0; compte<this.getLongueur(); compte++){
			objets[compte][0]=this.getLesLivraisons().get(compte);
		}
		
		tableau = new JTable(objets, entetes) {
			private static final long serialVersionUID = -1070527324413234766L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
	}
	
	public JTable getTableau()
	{
		return tableau;
	}
	
	public Vector<String> getLesLivraisons(){
		return lesLivraisons;
	}
	
	public int getLongueur(){
		return lesLivraisons.size();
	}

	
}
