package com.h4201.prototype.vue;

import java.awt.Color;
import java.awt.Component;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


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
			
			Vector<PointLivraison> livraisons = trancheHoraire.getPointsLivraisons();
			
			// Tri
			if(livraisons.get(0).getHeureArriveeEstimee() != null)
			{
				Collections.sort(livraisons, new Comparator<PointLivraison>() {
			        @Override public int compare(PointLivraison p1, PointLivraison p2) {
			            if(p1.getHeureArriveeEstimee().equals(p2.getHeureArriveeEstimee()))
			            	return 0;
			            else if(p1.getHeureArriveeEstimee().before(p2.getHeureArriveeEstimee()))
			            	return -1;
			            else
			            	return 1;
			        }
			    });
			}
			
			// Affichage
		    for(PointLivraison pointLivraison : livraisons)
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
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
		    {
		        Component c = super.prepareRenderer(renderer, row, column);

		        c.setBackground(Color.RED);

		        return c;
		    }
			
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
